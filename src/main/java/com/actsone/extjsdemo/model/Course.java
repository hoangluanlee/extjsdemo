package com.actsone.extjsdemo.model;
// Generated Sep 11, 2012 3:28:38 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Course generated by hbm2java
 */
@Entity
@Table(name = "course")
public class Course implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1644677632396977605L;
	private Integer id;
	private String name;
	private String desciption;

	public Course() {
	}

	public Course(String name, String desciption) {
		this.name = name;
		this.desciption = desciption;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCIPTION", length = 100, nullable=true)
	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

}
