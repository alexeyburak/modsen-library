package com.modsen.library.registration.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Registration Service API",
                description = "The registration service allows you to authorize, register and validate a user token",
                version = "${api.common.version}",
                contact = @Contact(
                        name = "${api.common.contact.name}",
                        email = "${api.common.contact.email}",
                        url = "${api.common.contact.url}"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080/", description = "Registration development server")
        },
        tags = {
                @Tag(name = "Auth", description = "API for user authentication")
        }
)
public class SwaggerConfig {
}
