package com.bcs.todolist.security;

import com.bcs.todolist.person.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:/application.properties")
public class JwtService {
    @Value("${jwt.signing.key}")
    private String jwtKey;

    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean validateToken(
            Claims claims,
            UserDetails userDetails
    ) {
        return claims.getSubject().equals(userDetails.getUsername());
    }

    public String createToken(Person person) {
        Map<String, ?> claims = new HashMap<>(){{
            put("id", person.getId());
            put("role", person.getRole().getName());
        }};

        return Jwts.builder()
                .claims(claims)
                .subject(person.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignKey()).compact();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(jwtKey.getBytes());
    }
}
