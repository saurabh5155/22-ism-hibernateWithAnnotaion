package com.controller.private_access;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.RoleBean;
import com.dao.RoleDao;

@RestController
@RequestMapping("/private")
public class RoleController {

	@Autowired
	RoleDao roleDao;

	@PostMapping("/role")
	public ResponseEntity<?> addRole(RoleBean role) {
		roleDao.save(role);
		return ResponseEntity.ok().body(role);
	}

	@GetMapping("/role")
	public ResponseEntity<?> getAllRoles() {
		List<RoleBean> roles = roleDao.findAll();
		return ResponseEntity.ok().body(roles);
	}

	@GetMapping("/role/{roleId}")
	public ResponseEntity<?> getRoleById(@PathVariable("roleId") int roleId) {
		Optional<RoleBean> roleById = roleDao.findById(roleId);
		RoleBean role = roleById.get();
		return ResponseEntity.ok().body(role);
	}
	
	@DeleteMapping("/role/{roleId}")
	public ResponseEntity<?> deleteRoleById(@PathVariable("roleId") int roleId){
		roleDao.deleteById(roleId);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/role")
	public ResponseEntity<?> updateRole(RoleBean role){
		roleDao.save(role);
		return ResponseEntity.ok().body(role);
	}
}
