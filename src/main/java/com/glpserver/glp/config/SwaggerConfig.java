package com.glpserver.glp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {


		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metaInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.glpserver.glp"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo metaInfo() {
		Contact contact = new Contact(
				"Dumitru Săndulache",
				"https://www.linkedin.com/in/dumitru-s%C4%83ndulache/",
				"sandulachedumitru@hotmail.com");
		
		List<VendorExtension> vendorExtensions = new ArrayList<>();
		
		ApiInfo apiInfo = new ApiInfo(
				"GLP Server Back-End REST API",
				"Back-End REST API for Guitar Lesson Planner app",
				"1.0",
				"TODO to be completed with Terms of service",
				contact,
				"Private License",
				"This is a private license for software, granted by a software developer through their agent to a specific user",
				vendorExtensions);

		return apiInfo;
	}
}
