package com.modsen.account.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Account Service API",
                description = "The account service allows you to perform operations with user accounts",
                version = "${api.common.version}",
                contact = @Contact(
                        name = "${api.common.contact.name}",
                        email = "${api.common.contact.email}",
                        url = "${api.common.contact.url}"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080/", description = "Account development server"),
        },
        tags = {
                @Tag(name = "Users", description = "API for user account"),
        }
)
public class SwaggerConfig {
}
