package com.learning.mongoData.db;

import org.springframework.core.convert.converter.Converter;

import com.learning.mongoData.model.Aircraft;

public class AircraftDbReadConverter implements Converter<String, Aircraft> {

	@Override
	public Aircraft convert(String str) {
		if(str!=null) {
			String[] temp = str.split("/");
			return new Aircraft(temp[0], Integer.parseInt(temp[1]));
		}
		else
			return null;
	}
}