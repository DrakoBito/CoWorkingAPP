package com.CoWorkSpace.v1.api.Coworkingmanagment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for user registration requests.
 * This class represents the request payload required for registering a new user.
 */
@Data
public class RegisterRequest {

    /**
     * User's full name.
     * Cannot be blank.
     */
    @NotBlank
    private String name;

    /**
     * User's email address.
     * Must be a valid email format and not blank.
     */
    @NotBlank
    @Email
    private String email;

    /**
     * User's password.
     * Cannot be blank.
     */
    @NotBlank
    private String password;
}
