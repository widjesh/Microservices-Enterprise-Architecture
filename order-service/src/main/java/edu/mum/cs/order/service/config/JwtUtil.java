package edu.mum.cs.order.service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

/**
 * The type Jwt util.
 */
@Service
public class JwtUtil {

    private String SECRET_KEY = "GOD";
    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);


    /**
     * Extract username string.
     *
     * @param token the token
     *
     * @return the string
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private void extractExpiration(String token) {
        extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private void isTokenExpired(String token) {
        extractExpiration(token);
    }

    /**
     * Validate token.
     *
     * @param token the token
     */
    public void validateToken(String token) {
        isTokenExpired(token);
    }

}
