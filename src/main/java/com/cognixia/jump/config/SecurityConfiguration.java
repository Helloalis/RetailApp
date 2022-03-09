package com.cognixia.jump.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// This class will detail how spring security is going to handle authorization & authentication
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// handle the AUTHENTICATION (who are you?)
	// lookup if the credentials (username and password) passed through the request match any of the 
	// users for this service
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		
		
		auth.inMemoryAuthentication()
			.withUser("user1")
			.password("{noop}pw123") // {noop} -> not part of password, stops encoding
			.roles("USER")
			.and()
			.withUser("admin")
			.password("{noop}password")
			.roles("ADMIN");
		
	}
	
	// AUTHORIZATION (what do you want?)
	// which users have access to which uris (APIs)
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/books").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/books/author/**").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/books/longer/**").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/books/shorter/**").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/sales/**").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/books/user/**").hasAnyRole("USER", "ADMIN")
			
			.antMatchers("/**").hasRole("ADMIN")
			.and().httpBasic();
		
	}
	
	
	
}
