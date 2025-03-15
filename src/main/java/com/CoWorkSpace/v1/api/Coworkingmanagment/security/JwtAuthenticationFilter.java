package com.CoWorkSpace.v1.api.Coworkingmanagment.security;

import com.CoWorkSpace.v1.api.Coworkingmanagment.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT Authentication Filter that intercepts HTTP requests and validates JWT tokens.
 * This filter ensures that only authenticated users can access protected endpoints.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    /**
     * Constructor with lazy initialization to avoid circular dependencies.
     *
     * @param jwtUtil      Utility class for handling JWT operations.
     * @param userService  Service to fetch user details.
     */
    public JwtAuthenticationFilter(@Lazy JwtUtil jwtUtil, @Lazy UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * Filters incoming requests to check for a valid JWT token in the Authorization header.
     *
     * @param request     The HTTP request.
     * @param response    The HTTP response.
     * @param filterChain The filter chain to continue processing the request.
     * @throws ServletException If an error occurs while processing the request.
     * @throws IOException      If an input or output error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Allow access to Swagger documentation without authentication
        String path = request.getRequestURI();
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Retrieve the Authorization header from the request
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Check if the Authorization header contains a Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            // Validate the token
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.getEmailFromToken(token);
                var user = userService.getUserByEmail(email);

                // Set authentication in the SecurityContext
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continue the request processing
        filterChain.doFilter(request, response);
    }
}
