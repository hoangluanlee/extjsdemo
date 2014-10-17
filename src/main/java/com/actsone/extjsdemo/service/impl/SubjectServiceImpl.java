package com.actsone.extjsdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.actsone.extjsdemo.dao.SubjectDAO;
import com.actsone.extjsdemo.sdo.SubjectSDO;
import com.actsone.extjsdemo.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDAO subjectDAO;

	
	@Transactional
	public SubjectSDO addSubject(SubjectSDO subject) {
		return subjectDAO.addSubject(subject);

	}

	
	@Transactional
	public List<SubjectSDO> addSubject(List<SubjectSDO> subjects) {
		return subjectDAO.addSubject(subjects);

	}

	@Transactional
	
	public SubjectSDO updateSubject(SubjectSDO subject) {
		return subjectDAO.updateSubject(subject);

	}

	
	@Transactional
	public List<SubjectSDO> updateSubject(List<SubjectSDO> subjects) {
		return subjectDAO.updateSubject(subjects);

	}

	@Transactional
	public List<SubjectSDO> listSubject() {

		return subjectDAO.listSubject();
	}

	@Transactional
	public void removeSubject(Integer id) {
		subjectDAO.removeSubject(id);
	}

	
	public void removeSubject(List<Integer> ids) {
		for (Integer id : ids) {
			subjectDAO.removeSubject(id);
		}
	}
}
