package com.warehouse.product;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Warehouse Product Service API")
                .version("1.0")
                .description("Product Management Service " +
                             "for Warehouse System")
                .contact(new Contact()
                    .name("Warehouse Team")
                    .email("warehouse@example.com")));
    }
}