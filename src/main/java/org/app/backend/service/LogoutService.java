package org.app.backend.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.app.backend.rep.InRepToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final InRepToken repToken;
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader =request.getHeader("Authorization");
        final String jwt;
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            return;
        }
        jwt=authHeader.substring(7);
        var stored=repToken.findByToken(jwt).orElse(null);
        if(stored!=null){
            stored.setExpired(true);
            stored.setRevoked(true);
        }
        repToken.save(stored);
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
