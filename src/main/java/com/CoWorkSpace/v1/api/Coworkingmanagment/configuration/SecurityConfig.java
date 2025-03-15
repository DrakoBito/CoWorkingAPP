package com.CoWorkSpace.v1.api.Coworkingmanagment.configuration;

import com.CoWorkSpace.v1.api.Coworkingmanagment.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Configures the security filter chain for the application.
     * - Disables CSRF protection (not needed for JWT-based authentication).
     * - Disables frame options to allow H2 Console (if used).
     * - Sets the session management to stateless.
     * - Allows unauthenticated access to registration, login, and Swagger documentation.
     * - Requires authentication for all other requests.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF as JWT is used
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Disable frame options (for H2 Console, if applicable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Enforce stateless session
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/register", "/login",
                                "/swagger-ui/**", "/v3/api-docs/**",
                                "/v3/api-docs.yaml", "/v3/api-docs.json",
                                "/swagger-resources/**", "/configuration/ui",
                                "/configuration/security", "/webjars/**"
                        ).permitAll() // Allow public access to authentication and API documentation
                        .anyRequest().authenticated() // All other endpoints require authentication
                );

        // Add JWT authentication filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Provides the authentication manager, which is required for authentication processing.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Defines the password encoder to use BCrypt hashing.
     */
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}
