package com.controller.private_access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AccountBean;
import com.bean.ResponseBean;
import com.dao.AccountDao;

@RestController
@RequestMapping("/private")
public class AccountController {
	
	@Autowired
	AccountDao accountDao;
	
	@PostMapping("/accounts")
	public ResponseEntity<?> addAccount(AccountBean account){
		System.out.println(account.getUser());
		System.out.println(account.getUser().getUserId());
		
		accountDao.save(account);
		
		ResponseBean<AccountBean> resAccount = new ResponseBean<AccountBean>();
		resAccount.setData(account);
		resAccount.setMsg("Account Added");
		return ResponseEntity.ok().body(resAccount);
	}
}
