package com.warehouse.auth;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Warehouse Auth Service API")
                .version("1.0")
                .description("Authentication and Authorization " +
                             "Service for Warehouse Management System")
                .contact(new Contact()
                    .name("Warehouse Team")
                    .email("warehouse@example.com"))
                .license(new License()
                    .name("Apache 2.0")))
            .addSecurityItem(
                new SecurityRequirement()
                    .addList("Bearer Authentication"))
            .components(new Components()
                .addSecuritySchemes(
                    "Bearer Authentication",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("Enter JWT token here")));
    }
}