package com.actsone.extjsdemo.dao;

import java.util.List;

import com.actsone.extjsdemo.sdo.StudentSDO;

public interface StudentDAO {

	public StudentSDO addStudent(StudentSDO Student);

	public List<StudentSDO> addStudent(List<StudentSDO> Student);

	public StudentSDO updateStudent(StudentSDO Student);

	public List<StudentSDO> updateStudent(List<StudentSDO> Student);

	public List<StudentSDO> listStudent();

	public void removeStudent(Integer id);
}
