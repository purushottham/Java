package com.ex.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.demo.model.Employees;

@Repository
public interface EmployeeRepository {

	
	List<Employees> findAll();
	
	Employees findById(Long empId);

}
