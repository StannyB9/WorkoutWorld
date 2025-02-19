package com.workoutapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .openapi("3.0.3")
                .info(new Info()
                        .title("Workout World API")
                        .version("1.0")
                        .description("API Documentation for Workout World application"));
    }
}
