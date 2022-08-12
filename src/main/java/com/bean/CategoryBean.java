package com.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "categories")
public class CategoryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catgoryId;
	
	private String categoryName;
		
	public Integer getCatgoryId() {
		return catgoryId;
	}

	public void setCatgoryId(Integer catgoryId) {
		this.catgoryId = catgoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
