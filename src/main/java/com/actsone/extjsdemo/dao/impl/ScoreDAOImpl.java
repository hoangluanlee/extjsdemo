package com.actsone.extjsdemo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.actsone.extjsdemo.dao.ScoreDAO;
import com.actsone.extjsdemo.dao.util.TransformerUtil;
import com.actsone.extjsdemo.model.Score;
import com.actsone.extjsdemo.sdo.ScoreSDO;

@Repository
public class ScoreDAOImpl implements ScoreDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ScoreSDO addScore(ScoreSDO score) {
		Score dto = TransformerUtil.transformToDTO(score);
		sessionFactory.getCurrentSession().save(dto);
		ScoreSDO sdo = TransformerUtil.transformToSDO(dto);
		return sdo;
	}

	
	public List<ScoreSDO> addScore(List<ScoreSDO> scoreList) {
		List<ScoreSDO> newScores = new ArrayList<ScoreSDO>();
		for (ScoreSDO score : scoreList) {
			newScores.add(addScore(score));
		}
		return newScores;
	}

	
	public ScoreSDO updateScore(ScoreSDO score) {
		Score dto = TransformerUtil.transformToDTO(score);
		sessionFactory.getCurrentSession().update(dto);
		return TransformerUtil.transformToSDO(dto);
	}

	
	public List<ScoreSDO> updateScore(List<ScoreSDO> scoreList) {
		List<ScoreSDO> newScores = new ArrayList<ScoreSDO>();
		for (ScoreSDO score : scoreList) {
			newScores.add(updateScore(score));
		}
		return newScores;
	}

	@SuppressWarnings("unchecked")
	public List<ScoreSDO> listScore(Integer studentId) {

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Score s where s.student.id = :studentId");
		query.setInteger("studentId", studentId);
		List<Score> list = query.list();
		List<ScoreSDO> sdoList = new ArrayList<ScoreSDO>();
		for (Score item : list) {
			sdoList.add(TransformerUtil.transformToSDO(item));
		}
		return sdoList;
	}

	public void removeScore(Integer id) {
		Score score = (Score) sessionFactory.getCurrentSession().load(
				Score.class, id);
		if (null != score) {
			sessionFactory.getCurrentSession().delete(score);
		}

	}

	
	public ScoreSDO read(Integer scoreId) {
		Score score = (Score) sessionFactory.getCurrentSession().load(
				Score.class, scoreId);
		return TransformerUtil.transformToSDO(score);
	}
}
