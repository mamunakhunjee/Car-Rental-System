package com.carRental.models;

public interface Vehicle {
	int getId();

	String getMake();

	String getModel();

	Double getRentalRatePerDay();

	Boolean isAvailable();
}
