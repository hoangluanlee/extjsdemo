package com.actsone.extjsdemo.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.actsone.extjsdemo.sdo.SubjectSDO;

@Component
public class SubjectUtil {

	/**
	 * Get list of Subjects from request.
	 * 
	 * @param data
	 *            - json data from request
	 * @return list of Subjects
	 */
	public List<SubjectSDO> getSubjectsFromRequest(Object data) {

		List<SubjectSDO> list;

		// it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1) {

			list = getListSubjectsFromJSON(data);

		} else { // it is only one object - cast to object/bean

			SubjectSDO Student = getSubjectFromJSON(data);

			list = new ArrayList<SubjectSDO>();
			list.add(Student);
		}

		return list;
	}

	/**
	 * Transform json data format into SubjectSDO object
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	private SubjectSDO getSubjectFromJSON(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		SubjectSDO newSubject = (SubjectSDO) JSONObject.toBean(jsonObject,
				SubjectSDO.class);
		return newSubject;
	}

	/**
	 * Transform json data format into list of SubjectSDO objects
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SubjectSDO> getListSubjectsFromJSON(Object data) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<SubjectSDO> newSubjects = (List<SubjectSDO>) JSONArray.toList(
				jsonArray, SubjectSDO.class);
		return newSubjects;
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
		List<Integer> idSubjects = (List<Integer>) JSONArray.toList(jsonArray,
				Integer.class);
		return idSubjects;
	}
}
