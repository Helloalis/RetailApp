package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {


	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,"/api/authenticate").permitAll()
		.antMatchers(HttpMethod.POST,"/api/register").permitAll()
		.antMatchers(HttpMethod.GET, "/api/users").permitAll()
		.antMatchers("/swagger-ui/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/books").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/books/author/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/books/longer/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/books/shorter/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/books/user/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST,"/api/sales").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/sales/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/sales/users").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST,"/api/sales/**/**").hasAnyRole("USER", "ADMIN")
		.antMatchers("/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
	}
	
}