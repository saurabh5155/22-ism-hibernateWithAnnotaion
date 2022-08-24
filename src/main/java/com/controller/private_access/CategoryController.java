package com.controller.private_access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CategoryBean;
import com.dao.CategoryDao;

@RestController
@CrossOrigin
@RequestMapping("/private")
public class CategoryController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@PostMapping("/categories")
	public ResponseEntity<?> addCategory(@RequestBody CategoryBean category){
		categoryDao.save(category);
		return ResponseEntity.ok().body(category);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<?> listCategory(){
		List<CategoryBean> categories = categoryDao.findAll();
		return ResponseEntity.ok().body(categories);
	}
}
