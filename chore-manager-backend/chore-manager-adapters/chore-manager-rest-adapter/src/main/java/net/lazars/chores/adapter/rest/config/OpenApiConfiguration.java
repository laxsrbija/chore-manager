package net.lazars.chores.adapter.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
@OpenAPIDefinition(
    info =
        @Info(
            title = "Chore Manager API",
            contact = @Contact(url = "https://github.com/laxsrbija/chore-manager"),
            license =
                @License(
                    name = "MIT",
                    url = "https://github.com/laxsrbija/chore-manager/blob/main/LICENSE")),
    servers = @Server(url = "/", description = "Default Server URL"),
    security = @SecurityRequirement(name = "basicAuth"))
public class OpenApiConfiguration {}
