package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public final class JsonUtils {
	
	public static final String toJson(Object model) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.writeValueAsString(model);
	}
	
	public static final String toJsonSafe(Object model) {
		ObjectMapper objectMapper = JsonMapper.builder() // or different mapper for other format
				   .addModule(new ParameterNamesModule())
				   .addModule(new Jdk8Module())
				   .addModule(new JavaTimeModule())
				   .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
				   // and possibly other configuration, modules, then:
				   .build();
		
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
