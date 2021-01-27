package com.recipesbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.recipesbook.Security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;



@EnableWebSecurity
@EnableAsync
@AllArgsConstructor
/*a slight delay to send email after saving the user in the database ~1400 ms */
/* we send the mail asynch, response time ~200ms */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	private final JwtAuthenticationFilter  jwtAuthenticationFilter ;
    private final UserDetailsService userDetailsService;
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        /*    .antMatchers(HttpMethod.GET,"/api/recipes/**").permitAll()*/
    	/* allow all the requests which match “/auth/**” */
		/* this endpoints are used for authentication and registration */ 
		/* at that point of time we don’t expect the user to be authenticated */ 
        httpSecurity.cors().and().csrf().disable()
        .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/recipe-category/").permitAll()
                .antMatchers(HttpMethod.GET,"/api/recipes/").permitAll()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .anyRequest()
                .authenticated();
        /* add the filter to HttpSecurity  */
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
     
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    	/* As UserDetailsService is an interface, we need to provide an implementation where it fetches the user information from our MySQL Database */

    }

    /* we specify the bean for AuthenticationManager to be used */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
}