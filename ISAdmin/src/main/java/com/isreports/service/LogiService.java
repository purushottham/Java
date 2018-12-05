package com.isreports.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogiService {

	public String getSecureKey(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
