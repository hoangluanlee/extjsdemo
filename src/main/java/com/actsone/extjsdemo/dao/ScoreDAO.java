package com.actsone.extjsdemo.dao;

import java.util.List;

import com.actsone.extjsdemo.sdo.ScoreSDO;

public interface ScoreDAO {

	public ScoreSDO addScore(ScoreSDO Score);

	public List<ScoreSDO> addScore(List<ScoreSDO> Score);

	public ScoreSDO updateScore(ScoreSDO Score);

	public List<ScoreSDO> updateScore(List<ScoreSDO> Score);

	public List<ScoreSDO> listScore(Integer studentId);

	public void removeScore(Integer id);

	public ScoreSDO read(Integer scoreId);
}
