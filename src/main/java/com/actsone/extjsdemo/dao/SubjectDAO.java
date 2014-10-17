package com.actsone.extjsdemo.dao;

import java.util.List;

import com.actsone.extjsdemo.sdo.SubjectSDO;

public interface SubjectDAO {

	public SubjectSDO addSubject(SubjectSDO subject);

	public List<SubjectSDO> addSubject(List<SubjectSDO> subjectList);

	public List<SubjectSDO> listSubject();

	public void removeSubject(Integer id);

	SubjectSDO updateSubject(SubjectSDO subject);

	List<SubjectSDO> updateSubject(List<SubjectSDO> subjectList);
}
