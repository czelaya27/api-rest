package com.example.czelaya.api_rest.config;

import com.example.czelaya.api_rest.components.utils.constants.APIField;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .openapi("3.0.1")
                .info(new Info().title("API REST")
                        .description("API REST de productos")
                        .version("v1")
                        .contact(new Contact().name("Cristian Zelaya").email("criszel94@gmail.com"))
                        .license(new License().name("Apache 2.0").url("example")))
                .externalDocs(new io.swagger.v3.oas.models.ExternalDocumentation()
                        .description("Documentation API REST")
                        .url("https://github.com/czelaya27/api-rest"));
    }

    @Bean
    public GroupedOpenApi productApi(){
        return GroupedOpenApi.builder()
                .group(APIField.ARTEFACT_ID)
                .packagesToScan("com.example.czelaya.api_rest.controller")
                .build();
    }

}
