package com.carRental.exceptions;

public class InvalidUserException extends Exception {
	public InvalidUserException() {
		super("Invalid credentials.");
	}

	public InvalidUserException(String message) {
		super(message);
	}
}
