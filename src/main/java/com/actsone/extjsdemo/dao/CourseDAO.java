package com.actsone.extjsdemo.dao;

import java.util.List;

import com.actsone.extjsdemo.sdo.CourseSDO;

public interface CourseDAO {

	public CourseSDO addCourse(CourseSDO Course);

	public List<CourseSDO> addCourse(List<CourseSDO> Course);

	public CourseSDO updateCourse(CourseSDO Course);

	public List<CourseSDO> updateCourse(List<CourseSDO> Course);

	public List<CourseSDO> listCourse();

	public void removeCourse(Integer id);
}
