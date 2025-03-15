package com.CoWorkSpace.v1.api.Coworkingmanagment.controller;

import com.CoWorkSpace.v1.api.Coworkingmanagment.dto.LoginRequest;
import com.CoWorkSpace.v1.api.Coworkingmanagment.dto.RegisterRequest;
import com.CoWorkSpace.v1.api.Coworkingmanagment.security.JwtUtil;
import com.CoWorkSpace.v1.api.Coworkingmanagment.service.UserService;
import jakarta.validation.Valid;
import com.CoWorkSpace.v1.api.Coworkingmanagment.dto.AuthResponse;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication operations.
 * - Handles user registration and login.
 * - Uses JWT for authentication.
 */
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user.
     *
     * @param request Contains user details (name, email, password).
     * @return ResponseEntity with success message and user ID.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok("User registered successfully with ID: " + user.getId());
    }

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param request Contains user credentials (email, password).
     * @return ResponseEntity with JWT token or error message.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.getUserByEmail(request.getEmail());

        if (user == null) {
            return ResponseEntity.status(404).body(new AuthResponse("User not found"));
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(new AuthResponse("Invalid credentials"));
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
