package com.example.ecommerce.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
@Service
public class JwtService {
    private final String secretkey="my-secret-key-my-secret-key-my-secret-key-123";

    public String generatetoken(String email){

        SecretKey key = Keys.hmacShaKeyFor(secretkey.getBytes());

        return Jwts.builder().subject(email).signWith(key).compact();

    }

    public String extractemail(String token){

        SecretKey key=Keys.hmacShaKeyFor(secretkey.getBytes());

        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

}
