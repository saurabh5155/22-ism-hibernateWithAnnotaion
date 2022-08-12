package com.bean.Temp;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "couses")
public class CouseBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID couseId;
	
	private String couseName;

	@ManyToMany(targetEntity = CouseBean.class)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns = @JoinColumn(name = "courseId"))
	private Set<StudentBean> student;
	
	public UUID getCouseId() {
		return couseId;
	}

	public void setCouseId(UUID couseId) {
		this.couseId = couseId;
	}

	public String getCouseName() {
		return couseName;
	}

	public void setCouseName(String couseName) {
		this.couseName = couseName;
	}
	
	
}
