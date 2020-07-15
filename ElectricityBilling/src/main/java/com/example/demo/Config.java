package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic() 
			.and()
			.antMatcher("/users")
	        .authorizeRequests()
	        .antMatchers(HttpMethod.GET ,"/user/**").hasAnyRole("admin","user")
	        .and()
	        .csrf().disable()
	        ;
			
			
//		super.configure(http);
	}

}
