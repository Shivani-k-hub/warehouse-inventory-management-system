package com.warehouse.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory
    .annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // ✅ Reads from application.properties
    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        byte[] keyBytes =
            Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(
            String username, String role) {
        Map<String, Object> claims =
            new HashMap<>();
        claims.put("role", role);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(
                System.currentTimeMillis()
                + (long) 1000 * 60 * 60 * 24))
            .signWith(getSigningKey(),
                SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUsername(
            String token) {
        return extractClaims(token)
            .getSubject();
    }

    public String extractRole(
            String token) {
        return extractClaims(token)
            .get("role", String.class);
    }

    public boolean isTokenValid(
            String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extractClaims(
            String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}