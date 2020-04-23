package com.ex.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.ex.demo.controller.EmployeeRESTController;
import com.ex.demo.model.Employees;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

	private JdbcTemplate jdbcTemplate;

	public EmployeeRepositoryImpl() {

	}

	public EmployeeRepositoryImpl(javax.sql.DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Employees> findAll() {
		logger.info("Find All");
		String sql = "SELECT * FROM employees limit 10";
		List<Employees> listContact = jdbcTemplate.query(sql, new RowMapper<Employees>() {

			@Override
			public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employees emp = new Employees();

				emp.setEmp_no(rs.getInt("emp_no"));
				emp.setLastName(rs.getString("last_name"));
				emp.setGender(rs.getString("gender"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setBirth_date(rs.getDate("birth_date"));
				emp.setHire_date(rs.getDate("hire_date"));
				return emp;
			}

		});

		return listContact;
	}

	@Override
	public Employees findById(Long empId) {
		logger.info("Find By ID: " + empId);
		String sql = "SELECT * FROM employees WHERE emp_no=" + empId;
		Employees emp = jdbcTemplate.query(sql, new ResultSetExtractor<Employees>() {

			@Override
			public Employees extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Employees emp = new Employees();
					emp.setEmp_no(rs.getInt("emp_no"));
					emp.setLastName(rs.getString("last_name"));
					emp.setGender(rs.getString("gender"));
					emp.setFirstName(rs.getString("first_name"));
					emp.setBirth_date(rs.getDate("birth_date"));
					emp.setHire_date(rs.getDate("hire_date"));
					return emp;
				}

				return null;
			}

		});
		if (emp != null) {
			getCurrentDepartment(emp);
			getCurrentSlalryHistory(emp);
		}
		return emp;
	}

	private void getCurrentSlalryHistory(Employees emp) {
		String query = "SELECT d.salary FROM salaries d INNER JOIN dept_emp_latest_date l "
				+ "	ON d.emp_no=l.emp_no AND d.from_date=l.from_date AND l.to_date = d.to_date where d.emp_no="
				+ emp.getEmp_no();
		int salary=jdbcTemplate.query(query, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getInt("salary");
				}
				return 0;
			}
			
		});
		emp.setCurrentSalary(salary);

	}

	private void getCurrentDepartment(Employees emp) {
		String query = "SELECT dept_no\r\n" + "    FROM dept_emp d\r\n"
				+ "        INNER JOIN dept_emp_latest_date l\r\n"
				+ "        ON d.emp_no=l.emp_no AND d.from_date=l.from_date AND l.to_date = d.to_date where l.emp_no="
				+ emp.getEmp_no();
		String deptName = jdbcTemplate.query(query, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("dept_no");
				}
				return null;
			}

		});
		emp.setDeptName(deptName);

	}

}
