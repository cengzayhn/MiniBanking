package com.cengzayhn.mini_banking_api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Mini Banking API")
                        .description("API Endpoint Decoration")
                        .version("v1.0")
                        .contact(newContact())
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url(""));
    }

    private Contact newContact() {
        Contact contact = new Contact();
        contact.setName("Mehmet Cengiz Ayhan");
        contact.setUrl("https://www.linkedin.com/in/mcengzayhn/");
        contact.setEmail("mcayhan6006@gmail.com");
        return contact;
    }
}
