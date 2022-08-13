package com.dao.Temp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.Temp.StudentBean;

@Repository
public interface StudentDao extends CrudRepository<StudentBean, Integer>{

}
