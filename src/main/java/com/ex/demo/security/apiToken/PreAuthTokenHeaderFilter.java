package com.ex.demo.security.apiToken;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class PreAuthTokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {

	private String authHeaderName;

	private String empHeader;

	public PreAuthTokenHeaderFilter(String authHeaderName, String empHeader) {
		this.authHeaderName = authHeaderName;
		this.empHeader = empHeader;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		EmployeeAUthData employeeAuthData = new EmployeeAUthData(authHeaderName, request.getHeader(authHeaderName),
				request.getHeader(empHeader));
		return employeeAuthData;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "N/A";
	}

}