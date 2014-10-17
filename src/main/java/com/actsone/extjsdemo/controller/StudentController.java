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

import com.actsone.extjsdemo.sdo.StudentSDO;
import com.actsone.extjsdemo.service.StudentService;
import com.actsone.extjsdemo.util.StudentUtil;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService1;
	@Autowired
	private StudentUtil studentUtil;

	@RequestMapping(value = "/student/index.action")
	public String index() {
		return "student";
	}

	@RequestMapping(value = "/student/view.action")
	public @ResponseBody
	Map<String, ? extends Object> view(@RequestHeader("Accept") String s)
			throws Exception {

		try {
			List<StudentSDO> students = studentService1.listStudent();

			return getMapSDO(students);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Students from database.");
		}
	}

	@RequestMapping(value = "/student/create.action")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestParam Object data)
			throws Exception {

		try {

			List<StudentSDO> students = studentService1.addStudent(studentUtil
					.getStudentsFromRequest(data));

			return getMapSDO(students);

		} catch (Exception e) {

			return getModelMapError("Error trying to create student.");
		}
	}

	@RequestMapping(value = "/student/update.action")
	public @ResponseBody
	Map<String, ? extends Object> update(@RequestParam Object data)
			throws Exception {
		try {
			List<StudentSDO> students = studentService1
					.updateStudent(studentUtil.getStudentsFromRequest(data));

			return getMapSDO(students);

		} catch (Exception e) {

			return getModelMapError("Error trying to update student.");
		}
	}

	@RequestMapping(value = "/student/delete.action")
	public @ResponseBody
	Map<String, ? extends Object> delete(@RequestParam Integer data)
			throws Exception {

		try {
			// TODO
			studentService1.removeStudent(data);

			Map<String, Object> modelMap = new HashMap<String, Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete student.");
		}
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param students
	 * @return
	 */
	private Map<String, Object> getMapSDO(List<StudentSDO> students) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", students.size());
		modelMap.put("data", students);
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

}
