package com.dao.Temp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.Temp.CouseBean;

@Repository
public interface CourseDao extends CrudRepository<CouseBean, Integer>{

}
