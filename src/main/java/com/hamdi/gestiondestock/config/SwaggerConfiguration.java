package com.hamdi.gestiondestock.config;

import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;



@Configuration
public class SwaggerConfiguration {

   @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("gestion de stock REST API")
                        .description("gestion de stock api documentation")
                        .version("1.3.4"));

    }


}
