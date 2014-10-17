package com.actsone.extjsdemo.service;

import java.util.List;

import com.actsone.extjsdemo.sdo.StudentSDO;

public interface StudentService {

	public StudentSDO addStudent(StudentSDO student);

	public List<StudentSDO> listStudent();

	public void removeStudent(Integer id);

	List<StudentSDO> addStudent(List<StudentSDO> student);

	StudentSDO updateStudent(StudentSDO student);

	List<StudentSDO> updateStudent(List<StudentSDO> student);
}
