package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public interface UserDao extends CrudRepository<UserBean,Integer >{
	List<UserBean> findAll();
	
	@Query(value = "select * from users where email = ?1 and password =?2",nativeQuery = true)
	UserBean login(String email,String password);
}
