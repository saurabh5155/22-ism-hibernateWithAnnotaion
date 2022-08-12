package com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.AccountBean;

@Repository
public interface AccountDao extends CrudRepository<AccountBean, Integer>{
	
}
