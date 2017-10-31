package com.ctrk.michca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Simple class to start up the application.
 *
 * @SpringBootApplication adds:
 * @Configuration
 * @EnableAutoConfiguration
 * @ComponentScan
 */
@ComponentScan(basePackages = {"com.ctrk.michca.*"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.anyRequest().fullyAuthenticated().and()
		.httpBasic();//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}