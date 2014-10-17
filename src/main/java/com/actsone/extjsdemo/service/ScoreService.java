package com.actsone.extjsdemo.service;

import java.util.List;

import com.actsone.extjsdemo.sdo.ScoreSDO;

public interface ScoreService {

	ScoreSDO addScore(ScoreSDO score);

	List<ScoreSDO> listScore(Integer studentId);

	void removeScore(Integer id);

	List<ScoreSDO> addScore(List<ScoreSDO> score);

	ScoreSDO updateScore(ScoreSDO score);

	List<ScoreSDO> updateScore(List<ScoreSDO> score);

	ScoreSDO read(Integer scoreId);
}
