package com.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.CategoryBean;

@Repository
public interface CategoryDao extends CrudRepository<CategoryBean, Integer>{
	List<CategoryBean> findAll();
}
