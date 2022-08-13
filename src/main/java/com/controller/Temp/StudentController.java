package com.controller.Temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Temp.StudentBean;
import com.dao.Temp.StudentDao;

@RestController
public class StudentController {
	
	@Autowired
	StudentDao studentDao;
	
	
	
	@PostMapping("/students")
	public ResponseEntity<?> addStudent(StudentBean student){
		studentDao.save(student);
		return ResponseEntity.ok().body(student);
	}
	
	
}
