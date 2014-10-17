package com.actsone.extjsdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.actsone.extjsdemo.dao.StudentDAO;
import com.actsone.extjsdemo.sdo.StudentSDO;
import com.actsone.extjsdemo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;

	@Transactional
	
	public StudentSDO addStudent(StudentSDO student) {
		return studentDAO.addStudent(student);
	}

	@Transactional
	
	public List<StudentSDO > addStudent(List<StudentSDO> student) {
		return studentDAO.addStudent(student);
	}

	@Transactional
	
	public StudentSDO updateStudent(StudentSDO student) {
		return studentDAO.updateStudent(student);
	}

	@Transactional
	
	public List<StudentSDO> updateStudent(List<StudentSDO> student) {
		return studentDAO.updateStudent(student);
	}

	@Transactional
	public List<StudentSDO> listStudent() {

		return studentDAO.listStudent();
	}

	@Transactional
	public void removeStudent(Integer id) {
		studentDAO.removeStudent(id);
	}
}
