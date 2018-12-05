package com.isreports.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isreports.domain.ErrorMessage;
import com.isreports.service.CreateUserService;

@RestController
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class CreateUserContoller {

	@Autowired
	CreateUserService createUserService;

	@Inject
	private Environment environment;

	final static Logger logger = (Logger) LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAdminView(@RequestParam(value = "name") String name, @RequestParam(value = "pwd") String pwd) {
		try {
			createUserService.createUser(name, pwd);
			return "created";
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage();
			error.setCode(1002);
			error.setErrorMessage(environment.getProperty("1002"));
			logger.error(error.toString() + name, e);
			return " not created";
		}
	}
}
