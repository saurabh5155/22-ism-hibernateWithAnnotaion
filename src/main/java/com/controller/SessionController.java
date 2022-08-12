package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.RoleBean;
import com.bean.UserBean;
import com.dao.RoleDao;
import com.dao.UserDao;

@RestController
public class SessionController {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(UserBean user) {
		RoleBean role = roleDao.findByRoleName("user");
		user.setRole(role);
		userDao.save(user);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(LoginBean login) {
		UserBean loginUser = userDao.login(login.getEmail(), login.getPassword());
		if(loginUser==null) {
			return ResponseEntity.ok().body("Invalid Email or Password....m");
		}else {
			return ResponseEntity.ok().body(loginUser); 
		}
	}
}
