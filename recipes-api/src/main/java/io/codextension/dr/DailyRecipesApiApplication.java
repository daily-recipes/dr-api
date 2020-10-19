package io.codextension.dr;

import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.HttpAuthenticationBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"io.codextension.dr.repository"})
public class DailyRecipesApiApplication extends SpringBootServletInitializer {

    private static final String AUTH_PROVIDER = "GoogleAuth";
    private static final HttpAuthenticationBuilder JWT_BEARER_BUILDER = new HttpAuthenticationBuilder().scheme("bearer")
            .bearerFormat("JWT").name(AUTH_PROVIDER).description("Access token authentication based on JWT");

    @Bean
    public Docket productApi() {
        Set<String> protocols = new HashSet<>();
        protocols.add("https");

        return new Docket(DocumentationType.OAS_30).select()
                .apis(RequestHandlerSelectors.basePackage("io.codextension.dr")).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo()).protocols(protocols).servers(new Server("yalla.run", "https://yalla.run",
                        "The yalla server", new HashSet<>(), new ArrayList<>()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));

    }

    private ApiKey apiKey() {
        return new ApiKey(AUTH_PROVIDER, "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReferences())
                .build();
    }

    private List<SecurityReference> securityReferences() {
        return Collections.singletonList(
                new SecurityReference(AUTH_PROVIDER,
                        new AuthorizationScope[] { new AuthorizationScope("email", "email and Profile access") }));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Daily Recipes", "Daily Recipes API Specifications", "1.0.0", "http://yalla.run/toc.html",
                new Contact("Elie Khoury", "https://yalla.run/", "elie.kh@gmail.com"), "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }

    public static void main(String[] args) {
        SpringApplication.run(DailyRecipesApiApplication.class, args);
    }

}
