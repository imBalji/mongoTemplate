package com.learning.mongoData;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Component;

import com.learning.mongoData.model.FlightInfo;
import com.learning.mongoData.queries.FlightInfoQueries;

@Component
public class AppRunner implements CommandLineRunner {
	
	private MongoTemplate mongoTemplate;
	private FlightInfoQueries infoQueries;
	
	public AppRunner(FlightInfoQueries infoQueries, MongoTemplate mongoTemplate) {
		this.infoQueries = infoQueries;
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n---- Query: All flights ordered by departure ----");
		List<FlightInfo> allFlightsOrdered = this.infoQueries.findAll("departure", 0, 3);
		new InfoPrinter(allFlightsOrdered);
		
		System.out.println("\n---- Query: Departure at Hyderabad ----");
		List<FlightInfo> hyderabadDepartures = this.infoQueries.findByDeparture("Hyderabad");
		new InfoPrinter(hyderabadDepartures);
		
		System.out.println("\n---- Query: Delayed departure at given Mumbai ----");
		List<FlightInfo> delayedAtAirport = this.infoQueries.findDelayedAtDeparture("Mumbai");
		new InfoPrinter(delayedAtAirport);
		
		System.out.println("\n---- Query: Duration between 120 and 400 minutes ----");
		List<FlightInfo> durationBetweenOneAndTwoHours = this.infoQueries.findByDurationBetween(120, 400);
		new InfoPrinter(durationBetweenOneAndTwoHours);
		
		System.out.println("\n---- Query: Using a indiGo ----");
		List<FlightInfo> flightsWithIndiGO = this.infoQueries.findByAircraft("indiGo");
		new InfoPrinter(flightsWithIndiGO);
		
//		System.out.println("\n---- Query: Free text search 'Hyderabad' ----");
//		List<FlightInfo> flightsByFreeText = this.infoQueries.findByFreeText("flight");
//		new InfoPrinter(flightsByFreeText);
		
		this.markFlightsToDubaiAsDelayed();
		this.removeFlightsWithDurationLessThanTwoHours();
	}
	
	private void markFlightsToDubaiAsDelayed() {
		Query departingFromDubai = Query.query(Criteria.where("destination").is("Dubai"));
		UpdateDefinition setDelayed =  Update.update("isDelayed",true);
		this.mongoTemplate.updateMulti(departingFromDubai, setDelayed, FlightInfo.class);
	}
	
	private void removeFlightsWithDurationLessThanTwoHours(){
		Query lessThanTwoHours = Query.query(Criteria.where("duration").lt(120));
		this.mongoTemplate.remove(lessThanTwoHours, FlightInfo.class);
	}
}