package com.ramirosomavilla.hello_world_app;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hello World Api REST")
                        .version("0.0")
                        .description("Documentación de mi API REST con Spring Boot y OpenAPI")
                        .contact(new Contact()
                                .name("Ramiro Somavilla")
                                .email("somavillarami@gmail.com")));
    }
}