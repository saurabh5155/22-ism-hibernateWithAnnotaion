package com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.RoleBean;

@Repository
public interface RoleDao extends CrudRepository<RoleBean, Integer>{

}
