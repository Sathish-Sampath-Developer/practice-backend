package com.eshop.eshop.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "E-Shop Rest APIs",
                description = "E-Shop Rest API documentation",
                version = "1.0.0",
                contact = @Contact(
                        name = "Sathish Sampath",
                        email = "sathishsmpth@gmail.com",
                        url = "https://sathish.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://sathish.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "E-Shop Core Service docs",
                url = "https://sathish.com"
        )
)
@Configuration
public class ApiDocumentation {

}
