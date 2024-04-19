package org.app.backend.service;

import lombok.RequiredArgsConstructor;
import org.app.backend.dto.AuthenticaReq;
import org.app.backend.dto.AuthenticationResponse;
import org.app.backend.model.enumm.TokenType;
import org.app.backend.model.Token;
import org.app.backend.rep.InRepToken;
import org.app.backend.dto.BaseUserRequest;
import org.app.backend.model.enumm.Role;
import org.app.backend.model.User;
import org.app.backend.rep.IntRepUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IntRepUser rep;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authmana;
    private final InRepToken repToken;
    private final ImgService imgserv;
    public AuthenticationResponse register(BaseUserRequest req){
        String img="";
        if(req.getImage()!=null){
            img=imgserv.addimage(req.getImage(),"Users");
        }
        var user= User.builder().first_name(req.getFirstname())
                .Last_name(req.getLastname())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(Role.Admin)
                .image(img)
                .build();
        rep.save(user);
        var jwttoken=jwtService.generateToken(user);
        SaveToken(jwttoken, user);
        return AuthenticationResponse.builder().token(jwttoken).message("success").build();
    }

    private void SaveToken(String jwttoken, User user) {
        var token= Token.builder()
                .token(jwttoken)
                .user(user).tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false).build();
        repToken.save(token);
    }

    public AuthenticationResponse authenticate(AuthenticaReq req){
        authmana.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),req.getPassword()
                )
        );
        var user=rep.findByEmail(req.getEmail()).orElseThrow();
        var nmtoken=jwtService.generateToken(user);
        SaveToken(nmtoken,user);
        return AuthenticationResponse.builder().token(nmtoken)
                .build();
    }
    private void revokeallUserToken(User user){
        var validtoken=repToken.findAllValidTokens(user.getId());
        if(validtoken.isEmpty()){
            return;
        }
        validtoken.forEach(t->{
            t.setRevoked(true);
            t.setExpired(true);
        });
        repToken.saveAll(validtoken);
    }
}
