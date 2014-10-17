package com.actsone.extjsdemo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.actsone.extjsdemo.dao.CourseDAO;
import com.actsone.extjsdemo.dao.util.TransformerUtil;
import com.actsone.extjsdemo.model.Course;
import com.actsone.extjsdemo.sdo.CourseSDO;

@Repository
public class CourseDAOImpl implements CourseDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public CourseSDO addCourse(CourseSDO Course) {
		Course dto = TransformerUtil.transformToDTO(Course);
		sessionFactory.getCurrentSession().save(dto);
		return TransformerUtil.transformToSDO(dto);
	}
	
	
	public List<CourseSDO> addCourse(List<CourseSDO> courseList) {
		List<CourseSDO> newCourses = new ArrayList<CourseSDO>();
		for (CourseSDO course : courseList) {
			newCourses.add(addCourse(course));
		}
		return newCourses;
	}

	
	public CourseSDO updateCourse(CourseSDO course) {
		Course dto = TransformerUtil.transformToDTO(course);
		sessionFactory.getCurrentSession().update(dto);
		return TransformerUtil.transformToSDO(dto);
	}

	
	public List<CourseSDO> updateCourse(List<CourseSDO> courseList) {
		List<CourseSDO> newCourses = new ArrayList<CourseSDO>();
		for (CourseSDO course : courseList) {
			newCourses.add(updateCourse(course));
		}
		return newCourses;
	}

	@SuppressWarnings("unchecked")
	public List<CourseSDO> listCourse() {

		List<Course> list = sessionFactory.getCurrentSession()
				.createQuery("from Course").list();
		List<CourseSDO> ret = new ArrayList<CourseSDO>();
		for (Course course : list) {
			ret.add(TransformerUtil.transformToSDO(course));
		}
		return ret;
	}

	public void removeCourse(Integer id) {
		Course course = (Course) sessionFactory.getCurrentSession().load(
				Course.class, id);
		if (null != course) {
			sessionFactory.getCurrentSession().delete(course);
		}

	}
}
