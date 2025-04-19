package com.carRental.exceptions;

public class InvalidChoiceException extends RuntimeException {
	public InvalidChoiceException() {
		super("Invalid choice.");
	}

	public InvalidChoiceException(String message) {
		super(message);
	}
}
