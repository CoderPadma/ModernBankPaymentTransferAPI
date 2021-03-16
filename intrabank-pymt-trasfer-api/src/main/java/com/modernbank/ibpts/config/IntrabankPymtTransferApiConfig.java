package com.modernbank.ibpts.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class IntrabankPymtTransferApiConfig {
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.modernbank.ibpts"))
				.build()
				.apiInfo(apiDetails());
		
	}
	
	
	Contact contact= new Contact("Padma Kumari","https://www.linkedin.com/in/padmathakur/","padmathakur242@gmail.com");
	private ApiInfo apiDetails() {
		return new ApiInfo(
		"Moden Bank Intra Bank Payment Transfer System API",
		"Intra-bank payment transfer system to allow real time payments between internal accounts. ",
		"1.0",
		"Free for first 20 requests. 5 bucks per request after that! :D ",
		contact,
		"API-License",
		"URL in progress ..",
		Collections.emptyList());
	}
}
 