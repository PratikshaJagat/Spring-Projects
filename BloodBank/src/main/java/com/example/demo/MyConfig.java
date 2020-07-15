package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public JWTAuthFilter JWTAuthenticationFilter() {
		return new JWTAuthFilter();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers("/login")
		.permitAll()
		.antMatchers("/signup")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/users")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/donors")
		.permitAll()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
		.antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/donors/**").hasRole("USER")
		.antMatchers(HttpMethod.PUT, "/donors/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/donors/**").hasRole("ADMIN")
		.and()	
		.csrf().disable()
		.addFilterBefore(JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
	}

}
