package com.dllg.framework.utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonUtils {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static ObjectMapper getObjectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.ALLOW_COMMENTS, true);
		objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		objectMapper.registerModule(new DateSimpleModule());
		return objectMapper;
	}
   

    private JsonUtils() {
    }


    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T readValue(String json, Class<T> valueType) {
    	try {
    		return getObjectMapper().readValue(json, valueType);
    	} catch (IOException e) {
    		logger.error(e);
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param jsonTypeReference
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T readValue(String json, TypeReference<T> typeReference) {
    	try {
    		return (T) getObjectMapper().readValue(json, typeReference);
    	} catch (IOException e) {
    		logger.error(e);
    		e.printStackTrace();
    	}
		return null;
    }
    
    /**
     * serialize any Java value as a String
     */
    public static String toJSONString(Object object) {
    	try {
    		return getObjectMapper().writeValueAsString(object);
    	} catch (JsonProcessingException e) {
    		logger.error(e);
    		e.printStackTrace();
    	}
    	return null;
    }
    

}
 class DateSimpleModule extends SimpleModule {
	protected static final Logger logger = LogManager.getLogger(DateSimpleModule.class);
	private static final long serialVersionUID = -3695931415463414812L;

	public DateSimpleModule() {
		this.addDeserializer(Date.class, new DateJsonDeserializer());
	}

}

 class DateJsonDeserializer extends JsonDeserializer<Date> {

	private final Set<String> patterns;

	public DateJsonDeserializer() {
		patterns = new HashSet<>();
		patterns.add("yyyy-MM-dd EEE aa hh:mm:ss");
		patterns.add("yyyy-MM-dd EEE HH:mm:ss");
		patterns.add("yyyy-MM-dd HH:mm:ss");
		patterns.add("yyyy-MM-dd HH:mm");
		patterns.add("yyyy-MM-dd");
		patterns.add("yyyy/MM/dd EEE aa hh:mm:ss");//2016/12/14 星期三 下午 3:52:49
		patterns.add("yyyy/MM/dd EEE HH:mm:ss");
		patterns.add("yyyy/MM/dd HH:mm:ss");
		patterns.add("yyyy/MM/dd HH:mm");
		patterns.add("yyyy/MM/dd");
	}

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
			throws IOException, JsonProcessingException {
		try {
			String date = jsonparser.getText();
			return DateUtils.parseDate(date, patterns.toArray(new String[] {}));
		} catch (ParseException e) {
			DateSimpleModule.logger.error(e);
			return new Date();
		}

	}
}
