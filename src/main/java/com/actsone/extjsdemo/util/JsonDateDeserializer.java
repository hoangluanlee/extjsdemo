package com.actsone.extjsdemo.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
// TODO Check why Deserializer function don't work
public class JsonDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		System.out.println("Deserialize date from JSON");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = jp.getText();

		try {
			return StringUtils.hasText(date) ? format.parse(date) : null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	public JsonDateDeserializer() {
		super();
	}

}
