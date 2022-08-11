package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.RoleBean;
import com.dao.RoleDao;

@RestController
public class RoleController {
	
	@Autowired
	RoleDao roleDao;
	
	@PostMapping("/role")
	public ResponseEntity<?> addRole(RoleBean role){
		roleDao.save(role);
		return ResponseEntity.ok().body(role);
	}
	
	@GetMapping("/role")
	public ResponseEntity<?> getAllRoles(){
		List<RoleBean> roles = roleDao.findAll();
		return ResponseEntity.ok().body(roles);
	}
}
