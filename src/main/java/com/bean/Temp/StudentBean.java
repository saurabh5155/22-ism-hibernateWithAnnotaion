package com.bean.Temp;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class StudentBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID studentId;
	private String studentName;
	private String email;
	private String password;
	
	@ManyToMany(mappedBy = "student")
	private Set<CouseBean> course;
	
	public Set<CouseBean> getCourse() {
		return course;
	}
	public void setCourse(Set<CouseBean> course) {
		this.course = course;
	}
	public UUID getStudentId() {
		return studentId;
	}
	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
