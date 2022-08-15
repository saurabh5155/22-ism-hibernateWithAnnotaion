package com.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.AccountBean;
import com.bean.UserBean;

@Repository
public interface AccountDao extends CrudRepository<AccountBean, Integer>{
	List<AccountBean> findByUser(UserBean user);;
}
