package com.isreports.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.isreports.domain.ErrorMessage;

@Service
public class LogiServiceImpl implements LogiService {

	@Value("${Logi.logi}")
	String logiServer;

	@Inject
	private Environment environment;

	final static Logger logger = (Logger) LoggerFactory.getLogger(LogiServiceImpl.class);

	@Secured("ROLE_ISADMIN")
	public String getSecureKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(logiServer);
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String LogiAppURL = "http://" + logiServer + "/Admin";
			String address = request.getRemoteAddr();
			if (address.equals("0:0:0:0:0:0:0:1")) {
				address = "127.0.0.1";
			}
			String getKeyURL = "/rdTemplate/rdGetSecureKey.aspx?Username=" + URLEncoder.encode(auth.getName())
					+ "&ClientBrowserAddress=" + request.getRemoteAddr();
			String finalURL = LogiAppURL + getKeyURL;
			System.out.println(finalURL);
			URL url = new URL(response.encodeRedirectURL(finalURL));
			// open the connection
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			// get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			return sb.toString();
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage();
			error.setCode(1003);
			error.setErrorMessage(environment.getProperty("1003"));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			logger.error(error.toString() + auth.getName(), e);
			return "hello";
		}
	}

}
