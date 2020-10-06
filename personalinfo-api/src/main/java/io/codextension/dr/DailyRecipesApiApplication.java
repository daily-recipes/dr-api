package io.codextension.dr;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "io.codextension.dr.repository" })
public class DailyRecipesApiApplication extends SpringBootServletInitializer {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("io.codextension.dr")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());

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
