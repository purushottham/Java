package com.isreports.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Inject
	JdbcUserDetailsManager jdbcUserDetailsManager;

	public void createUser(String name, String password) {

		SimpleGrantedAuthority userAccountRole = new SimpleGrantedAuthority("ROLE_ISADMIN");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(userAccountRole);
		String pwd = bCryptPasswordEncoder.encode(password);
		System.out.println(name);
		System.out.println(pwd);

		User user = new User(name, pwd, true, true, true, true, authorities);
		jdbcUserDetailsManager.createUser(user);
	}
}
