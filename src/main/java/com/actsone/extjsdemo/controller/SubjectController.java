package com.actsone.extjsdemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actsone.extjsdemo.sdo.SubjectSDO;
import com.actsone.extjsdemo.service.SubjectService;
import com.actsone.extjsdemo.util.SubjectUtil;

@Controller
public class SubjectController {

	private SubjectService subjectService;
	@Autowired
	private SubjectUtil subjectUtil;

	@RequestMapping(value = "/subject/index.action")
	public String index() {
		return "subject";
	}

	@RequestMapping(value = "/subject/load.action")
	public @ResponseBody
	Map<String, ? extends Object> load() throws Exception {

		try {
			SubjectSDO subjects = new SubjectSDO("subject name", "description",
					20);

			return getMap(subjects);

		} catch (Exception e) {

			return null;
		}
	}

	@RequestMapping(value = "/subject/view.action")
	public @ResponseBody
	Map<String, ? extends Object> view(@RequestHeader("Accept") String s)
			throws Exception {

		try {

			List<SubjectSDO> subjects = subjectService.listSubject();
			return getMap(subjects);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Subjects from database.");
		}
	}

	@RequestMapping(value = "/subject/create.action")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestParam Object data)
			throws Exception {

		try {

			List<SubjectSDO> subjects = subjectService.addSubject(subjectUtil
					.getSubjectsFromRequest(data));

			return getMap(subjects);

		} catch (Exception e) {

			return getModelMapError("Error trying to create subject.");
		}
	}

	@RequestMapping(value = "/subject/update.action")
	public @ResponseBody
	Map<String, ? extends Object> update(@RequestParam Object data)
			throws Exception {
		try {

			List<SubjectSDO> subjects = subjectService
					.updateSubject(subjectUtil.getSubjectsFromRequest(data));

			return getMap(subjects);

		} catch (Exception e) {

			return getModelMapError("Error trying to update subject.");
		}
	}

	@RequestMapping(value = "/subject/save.action")
	public @ResponseBody
	Map<String, ? extends Object> save(SubjectSDO subject) throws Exception {
		try {

			List<SubjectSDO> list = new ArrayList<SubjectSDO>();
			list.add(subject);
			return getMap(list);

		} catch (Exception e) {

			return getModelMapError("Error trying to update subject.");
		}
	}

	@RequestMapping(value = "/subject/delete.action")
	public @ResponseBody
	Map<String, ? extends Object> delete(@RequestParam Integer data)
			throws Exception {

		try {
			subjectService.removeSubject(data);

			Map<String, Object> modelMap = new HashMap<String, Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete subject.");
		}
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param subjects
	 * @return
	 */
	private Map<String, Object> getMap(List<SubjectSDO> subjects) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", subjects.size());
		modelMap.put("data", subjects);
		modelMap.put("success", true);

		return modelMap;
	}

	private Map<String, ? extends Object> getMap(SubjectSDO subjects) {
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("data", subjects);
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
	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

}
