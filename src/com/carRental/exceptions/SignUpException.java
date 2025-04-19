package com.carRental.exceptions;

public class SignUpException extends Exception {
	public SignUpException() {
		super("User already exist.");
	}

	public SignUpException(String message) {
		super(message);
	}
}
