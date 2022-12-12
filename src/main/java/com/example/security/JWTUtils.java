package com.example.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret})")
    private String jwtSecret;

    @Value("${jwt.expired}")
    private long jwtExpired;

    private Algorithm algorithm;

    @PostConstruct
    private void postConstruct() {
        algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
    }

    public String generateToken(UserDetails userDetails){
        return JWT.create()
            .withSubject(userDetails.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpired))
            .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
            .sign(getAlgorithm());
    }

    public String generateRefreshToken(UserDetails userDetails){
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpired * 2))
                .sign(getAlgorithm());
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
