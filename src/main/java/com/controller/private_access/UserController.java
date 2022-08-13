package com.controller.private_access;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.RoleBean;
import com.bean.UserBean;
import com.dao.RoleDao;
import com.dao.UserDao;

@RestController
@RequestMapping("/private")
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
//	@PostMapping("/users")
//	public ResponseEntity<?> addUser(UserBean user){
//		RoleBean role = roleDao.findByRoleName("user");
//		user.setRole(role);
//		userDao.save(user);
//		return ResponseEntity.ok().body(user);
//	}
	
	@GetMapping("/users")
	public ResponseEntity<?> listUsers(){
		List<UserBean> users = 	(List<UserBean>) userDao.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId){
		Optional<UserBean> userOptional = userDao.findById(userId);		
		if(userOptional.isPresent()) {
			UserBean user = userOptional.get();
			return ResponseEntity.ok().body(user);
		}else {
			return ResponseEntity.unprocessableEntity().body("UserId Not Found");
		}
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId){
		Optional<UserBean> userOptional = userDao.findById(userId);
		if(userOptional.isPresent()) {
			UserBean user = userOptional.get();
			userDao.delete(user);
			return ResponseEntity.ok().body(user);
		}else {
			return ResponseEntity.unprocessableEntity().body("UserId Not Found");
		}
	}
	
	@PutMapping("/users")
	public ResponseEntity<?> updateUser(UserBean user){
		RoleBean role = roleDao.findByRoleName("user");
		user.setRole(role);
		userDao.save(user);
		return ResponseEntity.ok().body(user);
	}
}
