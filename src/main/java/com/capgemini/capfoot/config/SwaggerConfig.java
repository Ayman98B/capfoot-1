package com.capgemini.capfoot.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
				.apis(RequestHandlerSelectors.basePackage("com.capgemini.capfoot.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("CapFoot App API",
				"An API documentation for CapFoot App.",
				"Beta", "https://github.com/OumaIntissar/capfoot",
				new Contact("CapFoot team", "https://www.linkedin.com/in/ointissar/", "oumaintissar@gmail.com"),
				"License of API", "https://github.com/OumaIntissar/capfoot",
				Collections.emptyList());
	}
}
