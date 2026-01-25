package br.com.terracota.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .components(components())
                .addSecurityItem(securityRequirement());
    }


    private Info apiInfo() {
        return new Info()
                .title("Terracota API")
                .description("API de gerenciamento de clientes e artesãos")
                .version("v1")
                .contact(new Contact()
                        .name("Lucas Bernadino")
                        .email("luucasdinoo@gmail.com")
                );
    }

    private Components components() {
        return new Components()
                .addSecuritySchemes("bearerAuth", securityScheme());
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    private SecurityRequirement securityRequirement() {
        return new SecurityRequirement()
                .addList("bearerAuth");
    }
}
