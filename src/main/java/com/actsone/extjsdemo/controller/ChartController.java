package com.actsone.extjsdemo.controller;

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.actsone.extjsdemo.sdo.ScoreSDO;
import com.actsone.extjsdemo.service.ScoreService;

@Controller
public class ChartController {
	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping(value = "score/datachart.action", method = RequestMethod.GET)
	public @ResponseBody
	String generateScoreChartXMLData(@RequestParam Integer studentId)
			throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("chart");
		rootElement.setAttribute("caption", "Score of student inventory");

		rootElement.setAttribute("xAxisName", "Subject");
		rootElement.setAttribute("yAxisName", "Score");
		rootElement.setAttribute("showValues", "1");
		rootElement.setAttribute("formatNumberScale", "1");
		rootElement.setAttribute("showBorder", "1");
		doc.appendChild(rootElement);

		List<ScoreSDO> scores =  scoreService.listScore(studentId);
		for (ScoreSDO score : scores) {
			Element set = doc.createElement("Set");
			rootElement.appendChild(set);
			Attr attr = doc.createAttribute("label");
			attr.setValue(score.getSubject());
			set.setAttributeNode(attr);
			set.setAttribute("value", score.getScore().toString());
		}

		// write the content into string
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);

		transformer.transform(source, result);

		return writer.toString();

	}
}
