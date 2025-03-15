package com.CoWorkSpace.v1.api.Coworkingmanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for authentication response.
 * This class holds the JWT token returned after a successful login.
 */
@Data
@AllArgsConstructor
public class AuthResponse {

    /**
     * JWT authentication token.
     */
    private String token;
}
