package com.actsone.extjsdemo.service;

import java.util.List;

import com.actsone.extjsdemo.sdo.CourseSDO;

public interface CourseService {

	public CourseSDO addCourse(CourseSDO course);

	public List<CourseSDO> listCourse();

	public void removeCourse(Integer id);

	List<CourseSDO> addCourse(List<CourseSDO> course);

	CourseSDO updateCourse(CourseSDO course);

	List<CourseSDO> updateCourse(List<CourseSDO> course);
}
