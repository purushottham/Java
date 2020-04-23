package com.ex.demo.security.apiToken;

public class EmployeeAUthData {

	private String headerName;

	private String token;

	private String empId;

	public EmployeeAUthData() {

	}

	public EmployeeAUthData(String headerName, String token, String empId) {
		super();
		this.headerName = headerName;
		this.token = token;
		this.empId = empId;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

}
