package com.recipesbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	/* Docket is a Springfox internal class */
	/* Springfox scans for all the Controllers and related components when starting up the application
	 * Then automatically generates the documentation for our REST API. */
	/* Documentation Type is Swagger2 */

	@Bean
	public Docket recipesBookApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
                .apiInfo(getApiInfo());		
	}
	
    private ApiInfo getApiInfo() {
    	
    	return new ApiInfoBuilder()
    			.title("Recipes book API ")
    			.version("1.0")
    			.description("API for the Recipes Book Application")
    			.build();
    }
}
