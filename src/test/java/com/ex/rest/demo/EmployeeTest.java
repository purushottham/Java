package com.ex.rest.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.ex.demo.model.Employees;
import com.ex.demo.repository.EmployeeRepository;

import junit.framework.TestCase;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class EmployeeTest extends TestCase {

	EmployeeRepository employeeRepository;

	@Before
	public void setup() {
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		List<Employees> emp = new ArrayList<Employees>();
		Employees e = new Employees();
		e.setFirstName("naidu");
		emp.add(e);
		Mockito.when(employeeRepository.findAll()).thenReturn(emp);
	}

	@Test
	public void testGetAllEmployee() {
		List<Employees> emp = employeeRepository.findAll();
		assertNotNull(emp);
	}

}
