package com.seed;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bean.RoleBean;
import com.dao.RoleDao;

@Component
public class RoleSeed {
	
	@Autowired
	RoleDao roleDao;
	
	@PostConstruct
	void createRole() {
		RoleBean roles = roleDao.findByRoleName("user");
		
		if(roles == null) {
			RoleBean roleAdmin = new RoleBean();
			roleAdmin.setRoleName("admin");
			roleDao.save(roleAdmin);
			
			RoleBean roleUser = new RoleBean();
			roleUser.setRoleName("user");
			roleDao.save(roleUser);
			
			System.out.println("User and Admin Add");
		}else {
			System.out.println(".......Roled Already Added.......");
		}
	}
}
