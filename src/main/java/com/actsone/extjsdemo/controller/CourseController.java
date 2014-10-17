package com.actsone.extjsdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actsone.extjsdemo.sdo.CourseSDO;
import com.actsone.extjsdemo.service.CourseService;
import com.actsone.extjsdemo.util.CourseUtil;

@Controller
public class CourseController {

	private CourseService courseService;
	@Autowired
	private CourseUtil courseUtil;

	@RequestMapping(value = "/course/index.action")
	public String index() {
		return "course";
	}

	@RequestMapping(value = "/course/view.action")
	public @ResponseBody
	Map<String, ? extends Object> view(@RequestHeader("Accept") String s)
			throws Exception {

		try {

			List<CourseSDO> courses = courseService.listCourse();

			return getMap(courses);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Courses from database.");
		}
	}

	@RequestMapping(value = "/course/create.action")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestParam Object data)
			throws Exception {

		try {

			List<CourseSDO> courses = courseService.addCourse(courseUtil
					.getCourseFromRequest(data));

			return getMap(courses);

		} catch (Exception e) {

			return getModelMapError("Error trying to create course.");
		}
	}

	@RequestMapping(value = "/course/update.action")
	public @ResponseBody
	Map<String, ? extends Object> update(@RequestParam Object data)
			throws Exception {
		try {

			List<CourseSDO> courses = courseService.updateCourse(courseUtil
					.getCourseFromRequest(data));

			return getMap(courses);

		} catch (Exception e) {

			return getModelMapError("Error trying to update course.");
		}
	}

	@RequestMapping(value = "/course/delete.action")
	public @ResponseBody
	Map<String, ? extends Object> delete(@RequestParam Integer data)
			throws Exception {

		try {
			// TODO
			courseService.removeCourse(data);

			Map<String, Object> modelMap = new HashMap<String, Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete course.");
		}
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param courses
	 * @return
	 */
	private Map<String, Object> getMap(List<CourseSDO> courses) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", courses.size());
		modelMap.put("data", courses);
		modelMap.put("success", true);

		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView in case of exception
	 * 
	 * @param msg
	 *            message
	 * @return
	 */
	private Map<String, Object> getModelMapError(String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return modelMap;
	}

	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

}
