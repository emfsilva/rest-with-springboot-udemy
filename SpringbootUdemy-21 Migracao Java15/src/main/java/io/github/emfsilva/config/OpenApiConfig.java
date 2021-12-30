package io.github.emfsilva.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI custonOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("RestFull API With java 15 and Spring Boot 2.4.1")
						.version("v1")
						.description("")
						.termsOfService("http://swaggwer.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
						.contact(new Contact().name("Emerson Ferreira").url("https://emfsilva.github.io").email("emersonfdasilva2@gmail.com")));
	}
}
