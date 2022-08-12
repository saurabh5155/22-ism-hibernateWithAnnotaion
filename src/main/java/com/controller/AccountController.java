package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AccountBean;
import com.dao.AccountDao;

@RestController
public class AccountController {
	
	@Autowired
	AccountDao accountDao;
	
	@PostMapping("/accounts")
	public ResponseEntity<?> addAccount(AccountBean account){
		accountDao.save(account);
		return ResponseEntity.ok().body(account);
	}
}
