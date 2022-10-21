package com.packtpub.springsecurity.web.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.packtpub.springsecurity.configuration.SecurityConfig;

public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebAppInitializer() {
		super(SecurityConfig.class);
	}

}
