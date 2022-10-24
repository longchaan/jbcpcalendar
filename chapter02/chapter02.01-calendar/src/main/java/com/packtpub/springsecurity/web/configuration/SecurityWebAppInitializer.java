package com.packtpub.springsecurity.web.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(1)
public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebAppInitializer() {
		super();
	}

}
