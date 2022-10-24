package com.packtpub.springsecurity.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // <user-service>
				// <user name="user" password="password" authorities="ROLE_USER" />
				.withUser("user").password("password").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // <http>
				// <intercept-url pattern="/**" access="hasRole('USER')"/>
				.antMatchers("/**").access("hasRole('USER')")
				// equivalent to <http auto-config="true">
				.and().formLogin() // <form-login />
				.and().httpBasic() // <http-basic />
				.and().logout() // <logout />
				// CSRF is enabled by default (will discuss later)
				.and().csrf().disable(); // <csrf disabled="true" />
	}

}
