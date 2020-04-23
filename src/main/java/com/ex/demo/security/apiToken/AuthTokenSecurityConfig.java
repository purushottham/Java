package com.ex.demo.security.apiToken;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
@Order(1)
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${header1}")
	private String authHeaderName;

	// TODO: retrieve this token value from data source
	@Value("${header2}")
	private String empHeader;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(authHeaderName, empHeader);

		filter.setAuthenticationManager(new AuthenticationManager() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				EmployeeAUthData employeeAUthData = (EmployeeAUthData) authentication.getPrincipal();
				Map<String, String> content = BuildToken.getContnet();
				if (employeeAUthData.getEmpId() == null || employeeAUthData.getToken() == null) {
					throw new BadCredentialsException("The API key was not found " + "or not the expected value.");
				}
				boolean valid = true;
				if (employeeAUthData.getToken() == null || employeeAUthData.getEmpId() == null) {
					valid = false;
				} else if (employeeAUthData.getToken().equals(content.get("any"))) {
					valid = true;
				} else if (employeeAUthData.getToken() != null && content.containsKey(employeeAUthData.getEmpId())) {
					String authHeaderValue = employeeAUthData.getEmpId();
					if (!employeeAUthData.getToken().equals(content.get(authHeaderValue))) {
						valid = false;
					}
				}

				if (!valid) {
					throw new BadCredentialsException("The API key was not found " + "or not the expected value.");
				}
				authentication.setAuthenticated(true);
				return authentication;
			}
		});

		httpSecurity.antMatcher("/api/**").csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(filter)
				.addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()), filter.getClass())
				.authorizeRequests().anyRequest().authenticated();
	}

}
