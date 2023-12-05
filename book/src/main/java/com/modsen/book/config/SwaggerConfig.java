package com.modsen.book.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Book Service API",
                description = "The book service allows you to perform operations with library and books",
                version = "${api.common.version}",
                contact = @Contact(
                        name = "${api.common.contact.name}",
                        email = "${api.common.contact.email}",
                        url = "${api.common.contact.url}"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080/", description = "Book development server"),
        },
        tags = {
                @Tag(name = "Books", description = "API for books logic"),
                @Tag(name = "Libraries", description = "API for library logic")
        }
)
public class SwaggerConfig {
}
