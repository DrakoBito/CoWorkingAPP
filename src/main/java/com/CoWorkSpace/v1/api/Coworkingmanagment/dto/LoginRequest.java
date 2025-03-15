package com.CoWorkSpace.v1.api.Coworkingmanagment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for login requests.
 * This class represents the request payload required for user authentication.
 */
@Data
public class LoginRequest {

    /**
     * User email address.
     * Must be a valid email format and not blank.
     */
    @Email
    @NotBlank
    private String email;

    /**
     * User password.
     * Cannot be blank.
     */
    @NotBlank
    private String password;
}
