package io.github.emfsilva.restfull.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.github.emfsilva.restfull"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RestFull API With Spring Boot 2.1.3")
                .version("1.0.0")
                .description("Some description about my API.")
                .termsOfServiceUrl("Terms of service URL")
                .licenseUrl("License of URL")
                .license("License of API")
                .contact(new Contact("Emerson Ferreira", "https://emfsilva.github.io" , "emersonfdasilva2@gmail.com"))
                .build();
    }
}


