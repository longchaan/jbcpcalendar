package com.packtpub.springsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // <user-service>
				// <user name="user1@example.com" password="user1" authorities="ROLE_USER" />
				.withUser("user1@example.com").password("user1").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // 
				.antMatchers("/**").access("hasRole('USER')") // <intercept-url />
				.and().formLogin() // <form-login
				/* */ .loginPage("/login/form") // login-page="/login/form"
				/* */ .loginProcessingUrl("/login") // login-processing-url="/login"
				/* */ .failureUrl("/login/form?error") // authentication-failure-url="/login?error"
				/* */ .usernameParameter("username") // username-parameter="username"
				/* */ .passwordParameter("password") // password-parameter="password"
		;
	}

}
