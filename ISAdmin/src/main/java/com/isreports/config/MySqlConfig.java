package com.isreports.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebMvcSecurity
public class MySqlConfig {
	
	@Value("${datasource.username}")
	String datasourceUsername;

	@Value("${datasource.password}")
	String datasourcePassword;

	@Value("${datasource.drivername}")
	String datasourceDrivername;

	@Value("${datasource.url}")
	String datasourceUrl;

	@Value("${datasource.maxwait}")
	int datasourceMaxWait;

	@Value("${datasource.maxactive}")
	int datasourceMaxActive;

	@Value("${datasource.maxidle}")
	int datasourceMaxIdle;

	@Value("${datasource.minidle}")
	int datasourceMinIdle;

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource factory = new BasicDataSource();
		factory.setUsername(datasourceUsername);
		if ((datasourcePassword != null) || (datasourcePassword == "")) {
			factory.setPassword(datasourcePassword);
		}
		factory.setDriverClassName(datasourceDrivername);
		factory.setUrl(datasourceUrl);
		factory.setMaxWait(datasourceMaxWait);
		factory.setMaxActive(datasourceMaxActive);
		factory.setMaxIdle(datasourceMaxIdle);
		factory.setMinIdle(datasourceMinIdle);
		return factory;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public JdbcUserDetailsManager myUserDetailsService() {
		JdbcUserDetailsManager myUserDetailsService = new JdbcUserDetailsManager();
		myUserDetailsService.setDataSource(dataSource());
		return myUserDetailsService;
	}

}
