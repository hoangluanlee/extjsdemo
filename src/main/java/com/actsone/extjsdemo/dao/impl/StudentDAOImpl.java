package com.actsone.extjsdemo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.actsone.extjsdemo.dao.StudentDAO;
import com.actsone.extjsdemo.dao.util.TransformerUtil;
import com.actsone.extjsdemo.model.Student;
import com.actsone.extjsdemo.sdo.StudentSDO;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	 
	public StudentSDO addStudent(StudentSDO student) {
		Student dto = TransformerUtil.transformToDTO(student);
		sessionFactory.getCurrentSession().save(dto);
		return TransformerUtil.transferToSDO(dto);
	}

	 
	public List<StudentSDO> addStudent(List<StudentSDO> studentList) {
		List<StudentSDO> newStudents = new ArrayList<StudentSDO>();
		for (StudentSDO student : studentList) {
			newStudents.add(addStudent(student));
		}
		return newStudents;
	}

	 
	public StudentSDO updateStudent(StudentSDO student) {
		Student dto = TransformerUtil.transformToDTO(student);
		sessionFactory.getCurrentSession().update(dto);
		return TransformerUtil.transferToSDO(dto);
	}

	 
	public List<StudentSDO> updateStudent(List<StudentSDO> studentList) {
		List<StudentSDO> newStudents = new ArrayList<StudentSDO>();
		for (StudentSDO student : studentList) {
			newStudents.add(updateStudent(student));
		}
		return newStudents;
	}

	@SuppressWarnings("unchecked")
	public List<StudentSDO> listStudent() {
		List<StudentSDO> sdoList = new ArrayList<StudentSDO>();
		List<Student> list = sessionFactory.getCurrentSession()
				.createQuery("from Student").list();
		for (Student student : list) {
			sdoList.add(TransformerUtil.transferToSDO(student));
		}
		return sdoList;
	}

	public void removeStudent(Integer id) {
		Student student = (Student) sessionFactory.getCurrentSession().load(
				Student.class, id);
		if (null != student) {
			sessionFactory.getCurrentSession().delete(student);
		}

	}
}
