package com.actsone.extjsdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actsone.extjsdemo.sdo.ScoreSDO;
import com.actsone.extjsdemo.service.ScoreService;
import com.actsone.extjsdemo.util.ScoreUtil;

@Controller
public class ScoreController {

	private ScoreService scoreService;
	@Autowired
	private ScoreUtil scoreUtil;

	@RequestMapping(value = "/score/index.action")
	public String index(@RequestParam(required = false) Integer scoreId,
			ModelMap model) {
		if (scoreId != null) {
			model.addAttribute("scoreId", scoreId);
		}
		return "score";
	}

	@RequestMapping(value = "/score/load.action")
	public @ResponseBody
	Map<String, ? extends Object> load(@RequestParam Integer scoreId)
			throws Exception {

		try {

			ScoreSDO score = scoreService.read(scoreId);

			return getMapSDO(score);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Scores from database.");
		}
	}

	@RequestMapping(value = "/score/view.action")
	public @ResponseBody
	Map<String, ? extends Object> view(@RequestParam Integer studentId)
			throws Exception {

		try {

			List<ScoreSDO> scores = scoreService.listScore(studentId);

			return getMapSDO(scores);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Scores from database.");
		}
	}

	@RequestMapping(value = "/score/create.action")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestParam Object data)
			throws Exception {

		try {

			List<ScoreSDO> scores = scoreService.addScore(scoreUtil
					.getScoresFromRequest(data));

			return getMap(scores);

		} catch (Exception e) {

			return getModelMapError("Error trying to create score.");
		}
	}

	@RequestMapping(value = "/score/update.action")
	public @ResponseBody
	Map<String, ? extends Object> update(@RequestParam Object data)
			throws Exception {
		try {

			List<ScoreSDO> scores = scoreService.updateScore(scoreUtil
					.getScoresFromRequest(data));

			return getMap(scores);

		} catch (Exception e) {

			return getModelMapError("Error trying to update score.");
		}
	}

	@RequestMapping(value = "/score/delete.action")
	public @ResponseBody
	Map<String, ? extends Object> delete(@RequestParam Integer data)
			throws Exception {

		try {
			scoreService.removeScore(data);

			Map<String, Object> modelMap = new HashMap<String, Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete score.");
		}
	}

	@RequestMapping(value = "/score/scorechart.action")
	public String scorechart(@RequestParam Integer studentId, ModelMap model)
			throws Exception {
		model.addAttribute("studentId", studentId);
		return "scorechart";
	}

	@RequestMapping(value = "score/datachart11.action", method = RequestMethod.GET)
	public @ResponseBody
	String plotChartData(@RequestParam Integer studentId)

	{
		System.out.println("SSSSSSS:" + studentId);
		String data = "";
		data = "<chart caption='Monthly Unit Sales' xAxisName='Month' yAxisName='Units'"
				+ "showValues='0' formatNumberScale='0' showBorder='1'>"
				+ "<set label='Jan' value='10' />"
				+ "<set label='Feb' value='857' />"
				+ "<set label='Mar' value='671' />"
				+ "<set label='Apr' value='494' />"
				+ "<set label='May' value='761' />"
				+ "<set label='Jun' value='960' />"
				+ "<set label='Jul' value='629' />"
				+ "<set label='Aug' value='622' />"
				+ "<set label='Sep' value='376' />"
				+ "<set label='Oct' value='494' />"
				+ "<set label='Nov' value='761' />"
				+ "<set label='Dec' value='9600' />" + "</chart>";
		return data;
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param scores
	 * @return
	 */
	private Map<String, Object> getMap(List<ScoreSDO> scores) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", scores.size());
		modelMap.put("data", scores);
		modelMap.put("success", true);

		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param scores
	 * @return
	 */
	private Map<String, Object> getMapSDO(List<ScoreSDO> scores) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", scores.size());
		modelMap.put("data", scores);
		modelMap.put("success", true);

		return modelMap;
	}

	private Map<String, ? extends Object> getMapSDO(ScoreSDO score) {
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("data", score);
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
	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

}
