package com.example.proyectoIntegrador.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String KEY = "secret";

    public String extractUserName(String token) {
        return extractClaimUsername(token);

    }

    public Date extractExpiration(String token) {
        return extractClaimDate(token);

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();

    }

    public Date extractClaimDate(String token){
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();

    }

    public String extractClaimUsername(String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();

    }

    private String createToken(Map<String, Object> claim, String subject) {
        return Jwts.builder().setClaims(claim).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 + 60 * 10 ))
                .signWith(SignatureAlgorithm.HS256,KEY).compact();

    }

    public String generateToken(UserDetails details) {
        Map<String, Object> claim = new HashMap<>();
        return createToken(claim, details.getUsername());

    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }

    public Boolean validateToken(String token, UserDetails details) {
        final String username = extractUserName(token);
        return (username.equals(details.getUsername()) && !isTokenExpired(token));

    }






}
