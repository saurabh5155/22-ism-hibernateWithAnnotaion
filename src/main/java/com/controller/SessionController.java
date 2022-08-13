package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
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
		UserBean userBean = userDao.findByEmail(user.getEmail());
		if(userBean==null) {
			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			String encPassword = bCrypt.encode(user.getPassword());
			RoleBean role = roleDao.findByRoleName("user");
			user.setRole(role);
			user.setPassword(bCrypt.encode(encPassword));       
			userDao.save(user);
			return ResponseEntity.ok().body(user);			
		}else {
			return ResponseEntity.ok().body("Email Already Given");
		}
		
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(LoginBean login) {
//		UserBean loginUser = userDao.findByEmailAndPassword(login.getEmail(), login.getPassword());
//		if(loginUser==null) {
//			return ResponseEntity.ok().body("Invalid Email or Password....");
//		}else {
//			return ResponseEntity.ok().body(loginUser); 
//		}
		
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		
		
		UserBean userBean = userDao.findByEmail(login.getEmail());
		
		System.out.println(bCrypt.matches(login.getPassword(), userBean.getPassword()));
		
		if(userBean != null && bCrypt.matches(login.getPassword(), userBean.getPassword()) == true) {
			return ResponseEntity.ok().body(login);
		}else {
			ResponseBean<UserBean> res = new ResponseBean<>();
			res.setData(userBean);
			res.setMsg("Invalid Email or Password....");
			return ResponseEntity.ok().body(res);
		}
		
		
		
//		if(userBean == null || bCrypt.matches(login.getPassword(), userBean.getPassword()) == false) {
//			ResponseBean<UserBean> res = new ResponseBean<>();
//			res.setData(userBean);
//			res.setMsg("Invalid Email or Password....");
//			return ResponseEntity.ok().body(res);
//		}else {
//			return ResponseEntity.ok().body(login);
//		}
	}
}
