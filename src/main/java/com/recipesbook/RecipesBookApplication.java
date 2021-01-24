package com.recipesbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.recipesbook.config.SwaggerConfiguration;

@SpringBootApplication
@Import(SwaggerConfiguration.class) /* import SwaggerConfiguration configuration class*/
public class RecipesBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesBookApplication.class, args);
	}

}
