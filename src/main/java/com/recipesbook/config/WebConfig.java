package com.recipesbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/* 
 * @EnableWebMvc in a spring-boot app will dismiss the autoconfiguration of spring boot 
 * so for the cross orgin origin support we just create a simple bean for configuration 

@Configuration
@EnableWebMvc 

public class WebConfig implements WebMvcConfigurer {  */

@Configuration
public class WebConfig {

	
	@Bean
    public WebMvcConfigurer corsConfigurer() 
    {
        return new WebMvcConfigurer() 
        {
            @Override
            public void addCorsMappings(CorsRegistry registry) 
            {
            	registry.addMapping("/**")
            	.allowedOriginPatterns("http://localhost:4200")
            	.allowedMethods("*")
            	.allowCredentials(true)
            	.allowedHeaders("*")
            	.maxAge(3600L);              
            }
        };
    }
	
}



