package com.actsone.extjsdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.actsone.extjsdemo.dao.ScoreDAO;
import com.actsone.extjsdemo.sdo.ScoreSDO;
import com.actsone.extjsdemo.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreDAO scoreDAO;

	@Transactional
	 
	public ScoreSDO addScore(ScoreSDO score) {
		return scoreDAO.addScore(score);
	}

	@Transactional
	 
	public List<ScoreSDO> addScore(List<ScoreSDO> score) {
		return scoreDAO.addScore(score);
	}

	@Transactional
	 
	public ScoreSDO updateScore(ScoreSDO score) {
		return scoreDAO.updateScore(score);
	}

	@Transactional
	 
	public List<ScoreSDO> updateScore(List<ScoreSDO> score) {
		return scoreDAO.updateScore(score);
	}

	 
	@Transactional
	public List<ScoreSDO> listScore(Integer studentId) {

		return scoreDAO.listScore(studentId);
	}

	@Transactional
	public void removeScore(Integer id) {
		scoreDAO.removeScore(id);
	}

	 
	@Transactional
	public ScoreSDO read(Integer scoreId) {
		return scoreDAO.read(scoreId);
	}
}
