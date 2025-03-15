package com.CoWorkSpace.v1.api.Coworkingmanagment.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class for handling JWT token generation, validation, and parsing.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    // JWT token expiration time (1 day = 86,400,000 milliseconds)
    private final long jwtExpiration = 86400000;

    /**
     * Generates a JWT token for a given user email.
     *
     * @param email The user's email.
     * @return A JWT token as a string.
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date()) // Token issue date
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration)) // Expiration date
                .signWith(SignatureAlgorithm.HS512, secret) // Signing algorithm
                .compact();
    }

    /**
     * Extracts the email from a JWT token.
     *
     * @param token The JWT token.
     * @return The email extracted from the token.
     */
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validates the JWT token.
     *
     * @param token The JWT token to validate.
     * @return True if the token is valid, false otherwise.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Invalid token
        }
    }
}
