package com.actsone.extjsdemo.service;

import java.util.List;

import com.actsone.extjsdemo.sdo.SubjectSDO;

public interface SubjectService {

	public SubjectSDO addSubject(SubjectSDO Subject);

	public List<SubjectSDO> addSubject(List<SubjectSDO> subject);

	public List<SubjectSDO> listSubject();

	public void removeSubject(Integer id);

	public void removeSubject(List<Integer> ids);

	SubjectSDO updateSubject(SubjectSDO subject);

	List<SubjectSDO> updateSubject(List<SubjectSDO> subjects);
}
