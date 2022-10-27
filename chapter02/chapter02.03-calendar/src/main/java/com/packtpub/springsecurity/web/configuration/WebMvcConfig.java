package com.packtpub.springsecurity.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
//@EnableWebMvc
@Import({ ThymeleafConfig.class })
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login/form")
			.setViewName("login");
	}

}
