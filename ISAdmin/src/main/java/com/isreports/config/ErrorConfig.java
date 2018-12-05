package com.isreports.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;

/**
 * 
 * @author LSN SOFT
 *
 */
@Configuration
@ComponentScan(basePackages = "com.isreports", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:error.properties")
public class ErrorConfig {
	@Inject
	private Environment environment;

	@Bean
	public String setProperty() {
		return environment.getProperty("error.properties");
	}
}
