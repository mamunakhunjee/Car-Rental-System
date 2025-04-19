package com.carRental.models;

public class Car implements Vehicle {
	private static int currentId = 1;

	private int id;
	private String make;
	private String model;
	private Double rentPerDay;
	private Boolean available = true;

	public Car() {
		this.make = "";
		this.model = "";
		this.rentPerDay = 0.0;
		this.available = true;
		this.id = Car.idGenerator();
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Car(String make, String model, Double rentPerDay) {
		this.make = make;
		this.model = model;
		this.rentPerDay = rentPerDay;
		this.available = true;
		this.id = Car.idGenerator();
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setRentPerDay(Double rentPerDay) {
		this.rentPerDay = rentPerDay;
	}

	@Override
	public String getMake() {
		// TODO Auto-generated method stub
		return this.make;
	}

	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return this.model;
	}

	@Override
	public Double getRentalRatePerDay() {
		// TODO Auto-generated method stub
		return this.rentPerDay;
	}

	@Override
	public Boolean isAvailable() {
		// TODO Auto-generated method stub
		return this.available;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public Double getTotalRent(int days) {
		return days * rentPerDay;
	}

	private static int idGenerator() {
		return Car.currentId++;
	}

}
