package com.carRental.models;

public interface User {
	int getId();

	String getFirstName();

	String getLastName();

	String getFullName();

	String getEmail();

	String getPassword();

	String getRole();

	void setFirstName(String firstName);

	void setLastName(String lastName);

	void setFullName(String fullName);

	void setEmail(String email);

	void setPassword(String password);
}
