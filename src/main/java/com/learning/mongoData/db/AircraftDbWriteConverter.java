package com.learning.mongoData.db;

import org.springframework.core.convert.converter.Converter;

import com.learning.mongoData.model.Aircraft;

public class AircraftDbWriteConverter implements Converter<Aircraft, String> {

	@Override
	public String convert(Aircraft aircraft) {
		return aircraft.getModel()+ "/" +aircraft.getNbSeats();
	}
}