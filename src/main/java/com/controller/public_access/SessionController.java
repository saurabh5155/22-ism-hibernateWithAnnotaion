package com.controller.public_access;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AccountBean;
import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.RoleBean;
import com.bean.UserBean;
import com.dao.AccountDao;
import com.dao.RoleDao;
import com.dao.UserDao;
import com.service.TokenGenerateService;

@RestController
@RequestMapping("/public")
public class SessionController {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;
	
	@Autowired
	TokenGenerateService tokenGenerateService;
	
	@Autowired
	AccountDao accountDao;

	@Autowired
	BCryptPasswordEncoder bCrypt;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(UserBean user) {
		UserBean userBean = userDao.findByEmail(user.getEmail());
		if(userBean==null) {
			String encPassword = bCrypt.encode(user.getPassword());
			RoleBean role = roleDao.findByRoleName("user");
			user.setRole(role);
			user.setPassword(encPassword);       
			userDao.save(user);
			return ResponseEntity.ok().body(user);			
		}else {
			return ResponseEntity.ok().body("Email Already Given");
		}
		
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(LoginBean login) {
//		UserBean userBean = userDao.findByEmailAndPassword(login.getEmail(), login.getPassword());
//		if(userBean==null && userBean.getPassword().equals(login.getPassword())) {
//			return ResponseEntity.ok().body("Invalid Email or Password....");
//		}else {
//			String authToken = tokenGenerateService.generateTokan(16);
//			userBean.setAuthenticationToken(authToken);
//			userDao.save(userBean);
//			response.setHeader("authToken", authToken);
//			return ResponseEntity.ok().body(userBean); 
//		}
		
		UserBean userBean = userDao.findByEmail(login.getEmail());
				
		if(userBean != null && bCrypt.matches(login.getPassword(), userBean.getPassword()) == true) {
			String authToken = tokenGenerateService.generateTokan(16);
			userBean.setAuthenticationToken(authToken);			
			userDao.save(userBean);
			
			List<AccountBean> accountBean = accountDao.findByUser(userBean);
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("Accounts", accountBean);
			resMap.put("users", userBean);
			
			return ResponseEntity.ok().body(resMap);
		}else {
			ResponseBean<UserBean> res = new ResponseBean<>();
			res.setData(userBean);
			res.setMsg("Invalid Email or Password....");
			return ResponseEntity.ok().body(res);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest req){
		String authToken = req.getHeader("authToken");
		UserBean userBean = userDao.findByAuthenticationToken(authToken);
		userBean.setAuthenticationToken("");
		userDao.save(userBean);
		return ResponseEntity.ok().body("Logout Successfully....");
	}
}
