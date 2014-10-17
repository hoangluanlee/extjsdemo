package com.actsone.extjsdemo.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.actsone.extjsdemo.sdo.CourseSDO;

@Component
public class CourseUtil {

	/**
	 * Get list of Course from request.
	 * 
	 * @param data
	 *            - json data from request
	 * @return list of Course
	 */
	public List<CourseSDO> getCourseFromRequest(Object data) {

		List<CourseSDO> list;

		// it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1) {

			list = getListCourseFromJSON(data);

		} else { // it is only one object - cast to object/bean

			CourseSDO Student = getCourseFromJSON(data);

			list = new ArrayList<CourseSDO>();
			list.add(Student);
		}

		return list;
	}

	/**
	 * Transform json data format into Course object
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	private CourseSDO getCourseFromJSON(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		CourseSDO newCourse = (CourseSDO) JSONObject.toBean(jsonObject,
				CourseSDO.class);
		return newCourse;
	}

	/**
	 * Transform json data format into list of CourseSDO objects
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<CourseSDO> getListCourseFromJSON(Object data) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<CourseSDO> newCourse = (List<CourseSDO>) JSONArray.toList(
				jsonArray, CourseSDO.class);
		return newCourse;
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
		List<Integer> idCourse = (List<Integer>) JSONArray.toList(jsonArray,
				Integer.class);
		return idCourse;
	}
}
