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

import com.bean.ProductBean;
import com.dao.ProductDao;

@RestController
@CrossOrigin
@RequestMapping("/private")
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	
	@PostMapping("/products")
	public ResponseEntity<?> addProduct(@RequestBody ProductBean product){
		productDao.save(product);
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> listProduct(){
		List<ProductBean> products =	productDao.findAll();
		return ResponseEntity.ok().body(products);
	}
}
