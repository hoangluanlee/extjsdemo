package com.actsone.extjsdemo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.actsone.extjsdemo.dao.SubjectDAO;
import com.actsone.extjsdemo.dao.util.TransformerUtil;
import com.actsone.extjsdemo.model.Subject;
import com.actsone.extjsdemo.sdo.SubjectSDO;

@Repository
public class SubjectDAOImpl implements SubjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	 
	public SubjectSDO addSubject(SubjectSDO subject) {
		Subject dto = TransformerUtil.transformToDTO(subject);
		sessionFactory.getCurrentSession().save(dto);

		return TransformerUtil.transformToSDO(dto);
	}

	 
	public List<SubjectSDO> addSubject(List<SubjectSDO> subjectList) {
		List<SubjectSDO> newSubjects = new ArrayList<SubjectSDO>();
		for (SubjectSDO subject : subjectList) {
			newSubjects.add(addSubject(subject));
		}
		return newSubjects;
	}

	 
	public SubjectSDO updateSubject(SubjectSDO subject) {
		Subject dto = TransformerUtil.transformToDTO(subject);
		sessionFactory.getCurrentSession().update(dto);
		return TransformerUtil.transformToSDO(dto);
	}

	 
	public List<SubjectSDO> updateSubject(List<SubjectSDO> subjectList) {
		List<SubjectSDO> newSubjects = new ArrayList<SubjectSDO>();
		for (SubjectSDO subject : subjectList) {
			newSubjects.add(updateSubject(subject));
		}
		return newSubjects;
	}

	@SuppressWarnings("unchecked")
	public List<SubjectSDO> listSubject() {

		return sessionFactory.getCurrentSession().createQuery("from Subject")
				.list();
	}

	public void removeSubject(Integer id) {
		Subject subject = (Subject) sessionFactory.getCurrentSession().load(
				Subject.class, id);
		if (null != subject) {
			sessionFactory.getCurrentSession().delete(subject);
		}

	}
}
