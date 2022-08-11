package com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public interface UserDao extends CrudRepository<UserBean,Integer >{
	
}
