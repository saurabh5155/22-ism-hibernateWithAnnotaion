package com.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.RoleBean;

@Repository
public interface RoleDao extends CrudRepository<RoleBean, Integer>{
	List<RoleBean> findAll();
}
