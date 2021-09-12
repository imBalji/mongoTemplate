package com.learning.mongoData.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "flights")
public class FlightInfo {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String internalId;
	
	@Field(name = "departure")
//	@Indexed
	@TextIndexed
	private String departureCity;
	
	@Field(name = "destination")
//	@Indexed
	@TextIndexed
	private String destinationCity;
	
	@TextIndexed(weight = 2)
	private String description;
	
	private FlightType type;
	private boolean isDelayed;
	private int durationMin;
	private LocalDate departureDate;
	private Aircraft aircraft;
	
	@Transient
	private LocalDate createdAt;
	
	public FlightInfo() {
		this.createdAt = LocalDate.now();
		this.internalId = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FlightType getType() {
		return type;
	}

	public void setType(FlightType type) {
		this.type = type;
	}

	public boolean isDelayed() {
		return isDelayed;
	}

	public void setDelayed(boolean isDelayed) {
		this.isDelayed = isDelayed;
	}

	public int getDurationMin() {
		return durationMin;
	}

	public void setDurationMin(int durationMin) {
		this.durationMin = durationMin;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
}