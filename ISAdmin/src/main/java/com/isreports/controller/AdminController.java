package com.isreports.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.isreports.domain.ErrorMessage;
import com.isreports.service.LogiService;

@RestController
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class AdminController {

	@Autowired(required = false)
	RestTemplate restTemplate;

	@Value("${Logi.logi}")
	String logiServer;

	@Autowired
	LogiService logiService;

	@Inject
	private Environment environment;

	final static Logger logger = (Logger) LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView showAdminView(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Enter the showAdminView method");
		Map<String, Object> myModel = new HashMap<String, Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			String secureKey = logiService.getSecureKey(request, response);
			if (secureKey.equals("hello")) {
				myModel.put("username", auth.getName());
				myModel.put("message",
						"Unable to produce Reports due to some network issues, Please try after sometime");
				logger.info("Exit the showAdminView method");
				if (auth != null) {
					new SecurityContextLogoutHandler().logout(request, response, auth);
				}
				SecurityContextHolder.getContext().setAuthentication(null);
				return new ModelAndView("hello", myModel);
			}
			List<String> reports = new ArrayList<String>();
			reports.add("CustomersInformation");
			reports.add("PaidAccounts");
			reports.add("TrialAccounts");
			reports.add("Dashboard");
			myModel.put("secureKey", secureKey);
			myModel.put("baseUrl", "http://" + logiServer + "/Admin");
			myModel.put("reports", reports);
			logger.info("Exit the showAdminView method");
			return new ModelAndView("admin", myModel);
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage();
			error.setCode(1001);
			error.setErrorMessage(environment.getProperty("1001"));
			logger.error(error.toString() + auth.getName(), e);
			myModel.put("username", auth.getName());
			logger.info("Exit the showAdminView method");
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			SecurityContextHolder.getContext().setAuthentication(null);
			return new ModelAndView("403", myModel);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			if (!auth.getAuthorities().contains(new GrantedAuthorityImpl("ROLE_ANONYMOUS"))) {
				return new ModelAndView("redirect:/admin");
			}
		}
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		return model;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.getContext().setAuthentication(null);
		return model;
	}
}
