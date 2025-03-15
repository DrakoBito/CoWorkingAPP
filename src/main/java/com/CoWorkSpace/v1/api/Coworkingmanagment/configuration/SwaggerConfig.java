package com.CoWorkSpace.v1.api.Coworkingmanagment.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpHeaders;

/**
 * Swagger configuration for API documentation.
 * - Defines the API title and security scheme for authentication.
 * - Uses Bearer Token authentication with JWT.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Coworking Management API"
        ),
        security = @SecurityRequirement(
                name = "Security token"
        )
)
@SecurityScheme(
        name = "Security token",
        description = "Access token for the API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "Bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
