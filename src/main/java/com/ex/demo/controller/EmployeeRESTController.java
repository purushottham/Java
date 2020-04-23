package com.ex.demo.controller;

import java.util.List;

import javax.activation.DataSource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.demo.model.Employees;
import com.ex.demo.repository.EmployeeRepository;

@RestController
@RequestMapping(value = "/employee-management", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class EmployeeRESTController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRESTController.class);
	@Autowired
	private EmployeeRepository repository;

	public EmployeeRepository getRepository() {
		return repository;
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping(value = "/employees")
	public List<Employees> getAllEmployees() {
		logger.info("Get ALL Employees");
		return repository.findAll();
	}

	@GetMapping("/employees/{id}")
	Employees getEmployeeById(@PathVariable Long id) {
		logger.info("Get Employee By Id:" + id);
		return repository.findById(id);
	}

}