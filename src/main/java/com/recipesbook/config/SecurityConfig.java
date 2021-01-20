package com.recipesbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/* allow all the requests which match “/auth/**” */
		/* this endpoints are used for authentication and registration */ 
		/* at that point of time we don’t expect the user to be authenticated */ 
		http.csrf().disable().authorizeRequests().antMatchers("/auth/**").permitAll().anyRequest().authenticated();
		}

	/*for password encryption in DB */
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
}
