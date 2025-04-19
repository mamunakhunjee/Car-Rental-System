package com.carRental.models;

public class Customer implements User {

	private static int currentId = 1;

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String fullName;
	private final String role = "Customer";

	public Customer() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.password = "";
		this.fullName = firstName + " " + lastName;
		this.id = Customer.idGenerator();
	}

	public Customer(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.fullName = firstName + " " + lastName;
		this.id = Customer.idGenerator();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return this.lastName;
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return this.fullName;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	private static int idGenerator() {
		return currentId++;
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return this.role;
	}
}
