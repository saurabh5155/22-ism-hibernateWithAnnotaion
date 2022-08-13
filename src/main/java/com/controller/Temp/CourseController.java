package com.controller.Temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Temp.CouseBean;
import com.dao.Temp.CourseDao;

@RestController
public class CourseController {
	
	@Autowired
	CourseDao courseDao;
	
	@PostMapping("/couses")
	public ResponseEntity<?> addCourse(CouseBean course){
		courseDao.save(course);
		return ResponseEntity.ok().body(course);
	}
}
