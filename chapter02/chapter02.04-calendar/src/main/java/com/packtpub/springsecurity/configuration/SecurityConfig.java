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
				.withUser("user1@example.com").password("user1").roles("USER") //
				.and() // <user name="admin1@" password="admin1" authorities="ROLE_USER, ROLE_ADMIN" />
				.withUser("admin1@example.com").password("admin1").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // <http>
				// <intercept-url pattern="/resources/**" access="permitAll"/>
				.antMatchers("/resources/**").access("permitAll")
				// <intercept-url pattern="/" access="hasAnyRole('ANONYMOUS','USER')"/>
				// .antMatchers("/").hasAnyRole("ANONYMOUS", "USER")
				.antMatchers("/login/*").access("hasAnyRole('ANONYMOUS', 'USER')") //
				.antMatchers("/logout/*").access("hasAnyRole('ANONYMOUS', 'USER')") //
				.antMatchers("/admin/*").access("hasRole('ADMIN')") //
				.antMatchers("/events/").access("hasRole('ADMIN')") //
				// <intercept-url pattern="/**" access="hasRole('USER')"/>
				.antMatchers("/**").access("hasRole('USER')") //
				.and().formLogin() // <form-login
				/* */ .loginPage("/login/form") // login-page="/login/form"
				/* */ .loginProcessingUrl("/login") // login-processing-url="/login"
				/* */ .failureUrl("/login/form?error") // authentication-failure-url="/login?error"
				/* */ .usernameParameter("username") // username-parameter="username"
				/* */ .passwordParameter("password") // password-parameter="password"
				.and().logout() // <logout
				/* */ .logoutUrl("/logout") // logout-url="/logout"
				/* */ .logoutSuccessUrl("/login?logout") // logout-success-url="/login?logout"
				.and().csrf().disable(); // <csrf disabled="true" />
	}

}
