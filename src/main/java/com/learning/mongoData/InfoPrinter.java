package com.learning.mongoData;

import java.util.List;

import com.learning.mongoData.model.FlightInfo;

public class InfoPrinter {
	List<FlightInfo> flightInfo;

	public InfoPrinter(List<FlightInfo> flightInfo) {
		super();
		this.flightInfo = flightInfo;
		this.flightInfo.stream().forEach(ele -> {
			System.out.println("Id: " + ele.getId());
			System.out.println("Departure: " + ele.getDepartureCity());
			System.out.println("Destination: " + ele.getDestinationCity());
			System.out.println("Description: " + ele.getDescription());
			System.out.println("Type: " + ele.getType().toString());
			System.out.println("Delayed? " + ele.isDelayed());
			System.out.println("Duration: " + ele.getDurationMin());
			System.out.println("Departure Data: " + ele.getDepartureDate().toString());
			System.out.println("Model: " + ele.getAircraft().getModel() + "No. of seats: " + ele.getAircraft().getNbSeats());
		});
	}
}