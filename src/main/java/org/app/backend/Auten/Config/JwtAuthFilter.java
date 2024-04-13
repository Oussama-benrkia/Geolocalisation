package org.app.backend.Auten.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.app.backend.Auten.Rep.InRepToken;
import org.app.backend.Auten.Service.JwtService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtservice;
    private final UserDetailsService userDetailService;
    private final InRepToken repToken;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader =request.getHeader("Authorization");
        final String jwt;
        final String email;
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authHeader.substring(7);
        email=jwtservice.Extractusername(jwt);
        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails user =this.userDetailService.loadUserByUsername(email);
            var istokenvalid =repToken.findByToken(jwt)
                    .map(t->!t.isExpired() && !t.isRevoked()).orElse(false);
            if(jwtservice.isTokenValid(jwt,user)&& istokenvalid){
                UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(
                        user,null,user.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
