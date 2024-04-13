package org.app.backend.Auten.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.app.backend.User.repo.IntRepUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${spring.security.user.password}")
    private String Secrite_Key;
    private final IntRepUser req;
    //extract email from PAYLOAD ---> PAYLOAD is claims
    public String Extractusername(String token){
        return extractClain(token,Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClain(token,Claims::getExpiration);
    }

   // extract all PAYLOAD
    private Claims extratAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // change secrite key string to class Key and decode of 64bit
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Secrite_Key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // is generic function which extract claim specific from all claims or payload
    public <T> T extractClain(String token , Function<Claims,T> ClaimResolver ){
        final  Claims claims=extratAllClaims(token);
        return ClaimResolver.apply(claims);
    }
    public String generateToken(
            Map<String, Object> extraClaim,
            UserDetails user
    ){
        return Jwts.builder().setClaims(extraClaim).setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))

                .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // Use HS256 for HMAC-SHA algorithm
                .compact();
    }
    public String generateToken(UserDetails user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",user.getAuthorities());
        return generateToken(claims,user);
    }
    public Boolean isTokenValid(String token ,UserDetails userDetails){
        final String username=Extractusername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExp(token);
    }
    public Boolean isTokenExp(String token){
        return extractExpiration(token).before(new Date());
    }

}
