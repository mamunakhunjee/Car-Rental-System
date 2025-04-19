package com.carRental.exceptions;

public class CarNotAvailableException extends Exception {
	public CarNotAvailableException() {
		super("This car is not availabe to book");
	}

	public CarNotAvailableException(String message) {
		super(message);
	}
}
