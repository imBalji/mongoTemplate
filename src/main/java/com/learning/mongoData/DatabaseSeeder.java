package com.learning.mongoData;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.learning.mongoData.model.Aircraft;
import com.learning.mongoData.model.FlightInfo;
import com.learning.mongoData.model.FlightType;

// For data entry only
@Component @Order(value = 1)
public class DatabaseSeeder implements CommandLineRunner {
	
	private MongoTemplate mongoTemplate;
	
	public DatabaseSeeder(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		empty();
		seed();
	}
	
	private void empty() {
		this.mongoTemplate.remove(new Query(), FlightInfo.class);
	}

	private void seed() {
		FlightInfo flight1 = new FlightInfo();
		flight1.setDepartureCity("Hyderabad");
		flight1.setDestinationCity("Mumbai");
		flight1.setDescription("An internal flight travelling from Hyderabad to Mumbai");
		flight1.setDurationMin(400);
		flight1.setDelayed(false);
		flight1.setDepartureDate(LocalDate.of(2012, 12, 21));
		flight1.setAircraft(new Aircraft("indiGo", 200));
		flight1.setType(FlightType.Internal);
		
		FlightInfo flight2 = new FlightInfo(); 
		flight2.setDepartureCity("Mumbai");
		flight2.setDestinationCity("Dubai");
		flight2.setDescription("An international flight travelling from Mumbai to Dubai");
		flight2.setDurationMin(1400);
		flight2.setDelayed(false);
		flight2.setDepartureDate(LocalDate.of(2021, 12, 21));
		flight2.setAircraft(new Aircraft("indiGo", 200));
		flight2.setType(FlightType.International);
		
		this.mongoTemplate.insertAll(List.of(flight1, flight2));
	}
}