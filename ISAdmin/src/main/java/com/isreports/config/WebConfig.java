package com.isreports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.isreports.*" })
@Import({ SecurityConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/app/**").addResourceLocations("/cms/app/");
		registry.addResourceHandler("/login/**").addResourceLocations("/login/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/setup/**").addResourceLocations("/setup/");
		registry.addResourceHandler("/registration/**").addResourceLocations("/registration/");
		registry.addResourceHandler("/content/**").addResourceLocations("/content/");
		registry.addResourceHandler("/changepwd/**").addResourceLocations("/changepwd/");
		registry.addResourceHandler("/servicePlan/**").addResourceLocations("/servicePlan/");
		registry.addResourceHandler("/**").addResourceLocations("/");
	}

}
