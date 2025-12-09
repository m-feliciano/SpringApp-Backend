package com.feliciano.store.config;import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${application.name:Store API}")
    private String appName;
    @Value("${application.version:1.0.0}")
    private String appVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .version(appVersion)
                        .description("API managing products of store")
                        .contact(new Contact()
                                .name("Store Team")
                                .email("team@store_api.com.br")
                                .url("https://www.atore_api.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .tags(List.of(
                        new Tag().name("Authentication").description("Authentication and password recovery endpoints"),
                        new Tag().name("Clients").description("Client management endpoints"),
                        new Tag().name("Products").description("Product management endpoints"),
                        new Tag().name("Categories").description("Category management endpoints"),
                        new Tag().name("Orders").description("Order management endpoints"),
                        new Tag().name("States").description("State and city management endpoints")
                ))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT auth token"))
                );
    }
}

