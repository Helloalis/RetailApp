package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cognixia.jump.filter.JwtRequestFilter;
import com.cognixia.jump.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		
		auth.userDetailsService(myUserDetailsService);
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("{noop}password") // {noop} -> not part of password, stops encoding
			.roles("USER")
			.and()
			.withUser("admin")
			.password("{noop}password")
			.roles("ADMIN");
		
	}
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		
		http.csrf().disable();
//			.authorizeRequests()
//			.antMatchers(HttpMethod.GET, "/api/books").hasAnyRole("USER", "ADMIN")
//			.antMatchers(HttpMethod.GET, "/api/books/author/**").hasAnyRole("USER", "ADMIN")
//			.antMatchers(HttpMethod.GET, "/api/books/longer/**").hasAnyRole("USER", "ADMIN")
//			.antMatchers(HttpMethod.GET, "/api/books/shorter/**").hasAnyRole("USER", "ADMIN")
//			.antMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("USER", "ADMIN")
////			.antMatchers(HttpMethod.GET, "/api/sales/**").hasAnyRole("USER", "ADMIN")
////			.antMatchers(HttpMethod.GET, "/api/books/user/**").hasAnyRole("USER", "ADMIN")
////			.antMatchers(HttpMethod.POST,"/api/sales").hasAnyRole("USER", "ADMIN")
//			.antMatchers("/**").hasRole("ADMIN")
//			.anyRequest().authenticated()
//			.and().sessionManagement()
//				.sessionCreationPolicy( SessionCreationPolicy.STATELESS );

		//http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();		
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
