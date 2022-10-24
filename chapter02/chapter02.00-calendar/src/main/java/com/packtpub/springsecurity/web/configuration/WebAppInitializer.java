package com.packtpub.springsecurity.web.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.packtpub.springsecurity.configuration.SecurityConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfig.class }; // Root beans of the parent ApplicationContext
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class }; // Child beans of the child ApplicationContext
	}

	@Override
	protected String[] getServletMappings() {
		return null;
	}

}
