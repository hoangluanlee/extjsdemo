package com.actsone.extjsdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.actsone.extjsdemo.dao.CourseDAO;
import com.actsone.extjsdemo.sdo.CourseSDO;
import com.actsone.extjsdemo.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseDAO;
	
	@Transactional
	 
	public CourseSDO addCourse(CourseSDO course) {
		return courseDAO.addCourse(course);
	}
	
	@Transactional
	 
	public List<CourseSDO> addCourse(List<CourseSDO> course) {
		return courseDAO.addCourse(course);
	}

	@Transactional
	 
	public CourseSDO updateCourse(CourseSDO course) {
		return courseDAO.updateCourse(course);
	}
	
	@Transactional
	 
	public List<CourseSDO> updateCourse(List<CourseSDO> course) {
		return courseDAO.updateCourse(course);
	}
	
	
	
	@Transactional
	public List<CourseSDO> listCourse() {

		return courseDAO.listCourse();
	}

	@Transactional
	public void removeCourse(Integer id) {
		courseDAO.removeCourse(id);
	}
}
