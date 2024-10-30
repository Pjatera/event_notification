package ru.javacourse.event_notificator.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.javacourse.event_notificator.service.security.properties.JwtProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor

public class JwtService {

    private final JwtProperties jwtProperties;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValidToken(String token) {
        var claims = getClaims(token);
        Instant expiration = claims.getExpiration().toInstant();
        Instant now = Instant.now();
        return expiration.isAfter(now);
    }

    public String extractUserLogin(String token) {
        var claims = getClaims(token);
        return claims.getSubject();
    }

    public Long extractUserId(String token) {
        var claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    @SuppressWarnings("unchecked")
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        var claims = getClaims(token);
        List<String> roles = (List<String>) claims.get("roles");
        return roles.stream()

                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
