package com.carRental.exceptions;

public class InvalidDetailsException extends Exception {
	public InvalidDetailsException() {
		super("Invalid Type");
	}
	
	public InvalidDetailsException(String message) {
		super(message);
	}
}
