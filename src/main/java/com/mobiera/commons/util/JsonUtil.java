package com.mobiera.commons.util;


import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class JsonUtil {

	
	private static ObjectMapper mapper = null;
	
	
	public static String serialize(Object obj, boolean pretty) throws JsonProcessingException {
	    
		
		ObjectMapper mapper = getObjectMapper();

	    
	    if (pretty) {
	        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	    } else {
	    	 return mapper.writeValueAsString(obj);
	    }

	   
	}
	
	
	private static ObjectMapper getObjectMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();

		    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		    SimpleModule module = new SimpleModule();
		    module.addSerializer(Instant.class, new InstantSerializer());
		    mapper.registerModule(module);

		}
		return mapper;
	}


	public static <T> T deserialize(String json, Class<T> class1) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return getObjectMapper().readValue(json, class1);
	}
	
	
	
}
