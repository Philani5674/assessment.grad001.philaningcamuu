package com.enviro.assessment.grad001.philaningcamu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Info info = new Info()
                .title("Enviro365 Waste Management API")
                .version("1.0")
                .description("This API provides endpoints for the Enviro365 waste management mobile application.");
        return new OpenAPI().info(info);
    }
}
