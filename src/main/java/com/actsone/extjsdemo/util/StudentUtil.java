package com.actsone.extjsdemo.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Component;

import com.actsone.extjsdemo.sdo.StudentSDO;

@Component
public class StudentUtil {

	/**
	 * Get list of Students from request.
	 * 
	 * @param data
	 *            - json data from request
	 * @return list of Students
	 */
	public List<StudentSDO> getStudentsFromRequest(Object data) {

		List<StudentSDO> list;

		// it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1) {

			list = getListStudentsFromJSON(data);

		} else { // it is only one object - cast to object/bean

			StudentSDO Student = getStudentFromJSON(data);

			list = new ArrayList<StudentSDO>();
			list.add(Student);
		}

		return list;
	}

	/**
	 * Transform json data format into Student object
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	private StudentSDO getStudentFromJSON(Object data) {

		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd" }));// 2012-09-24T00:00:00
		JSONObject jsonObject = JSONObject.fromObject(data);
		StudentSDO newStudent = (StudentSDO) JSONObject.toBean(jsonObject,
				StudentSDO.class);
		return newStudent;
	}

	/**
	 * Transform json data format into list of Student objects
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<StudentSDO> getListStudentsFromJSON(Object data) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<StudentSDO> newStudents = (List<StudentSDO>) JSONArray.toList(
				jsonArray, StudentSDO.class);
		return newStudents;
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
		List<Integer> idStudents = (List<Integer>) JSONArray.toList(jsonArray,
				Integer.class);
		return idStudents;
	}
}
