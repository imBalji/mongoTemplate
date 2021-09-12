package com.learning.mongoData.model;

public class Aircraft {
	private String model;
	private int nbSeats;
	
	public Aircraft(String model, int nbSeats) {
		super();
		this.model = model;
		this.nbSeats = nbSeats;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getNbSeats() {
		return nbSeats;
	}
	public void setNbSeats(int nbSeats) {
		this.nbSeats = nbSeats;
	}
}