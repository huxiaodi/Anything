package com.dllg.framework.configurer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

@JsonComponent
public class DateJsonDeserializer extends JsonDeserializer<Date> {
	protected static final Logger logger = LogManager.getLogger(DateJsonDeserializer.class);
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
			throws IOException {
		try {
			String date = jsonparser.getText();
			if (StringUtils.isBlank(date)) {
				return null;
			}
			String utcFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
			if( date.length() == ( utcFormat.length()-4 ) ){
				SimpleDateFormat sdf = new SimpleDateFormat(utcFormat);
				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				try {
					Date parse = sdf.parse(date);
					return parse;
				}catch (ParseException e){
					logger.error(e);
				}
			}
			return DateUtils.parseDate(date, patterns.toArray(new String[]{}));
		} catch (ParseException e) {
			logger.error(e);
			return new Date();
		}

	}
}
