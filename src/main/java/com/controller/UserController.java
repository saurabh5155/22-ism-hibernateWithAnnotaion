package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.RoleBean;
import com.bean.UserBean;
import com.dao.RoleDao;
import com.dao.UserDao;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@PostMapping("/users")
	public ResponseEntity<?> addUser(UserBean user){
		RoleBean role = roleDao.findByRoleName("user");
		user.setRole(role);
		userDao.save(user);
		return ResponseEntity.ok().body(user);
	}
}
