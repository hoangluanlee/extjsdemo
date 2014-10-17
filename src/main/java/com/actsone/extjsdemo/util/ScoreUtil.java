package com.actsone.extjsdemo.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.actsone.extjsdemo.sdo.ScoreSDO;

@Component
public class ScoreUtil {

	/**
	 * Get list of Scores from request.
	 * 
	 * @param data
	 *            - json data from request
	 * @return list of Scores
	 */
	public List<ScoreSDO> getScoresFromRequest(Object data) {

		List<ScoreSDO> list;

		// it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1) {

			list = getListScoresFromJSON(data);

		} else { // it is only one object - cast to object/bean

			ScoreSDO Score = getScoreFromJSON(data);

			list = new ArrayList<ScoreSDO>();
			list.add(Score);
		}

		return list;
	}

	/**
	 * Transform json data format into Score object
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	private ScoreSDO getScoreFromJSON(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		ScoreSDO newScore = (ScoreSDO) JSONObject.toBean(jsonObject,
				ScoreSDO.class);
		return newScore;
	}

	/**
	 * Transform json data format into list of Score objects
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ScoreSDO> getListScoresFromJSON(Object data) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<ScoreSDO> newScores = (List<ScoreSDO>) JSONArray.toList(jsonArray,
				ScoreSDO.class);
		return newScores;
	}

	/**
	 * Tranform array of numbers in json data format into list of Integer
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListIdFromJSON(Object data) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Integer> idScores = (List<Integer>) JSONArray.toList(jsonArray,
				Integer.class);
		return idScores;
	}
}
