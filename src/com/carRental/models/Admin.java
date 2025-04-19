package com.carRental.models;

public class Admin implements User {

	private static int currentId = 1;

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String fullName;
	private final String role = "Admin";

	public Admin() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.password = "";
		this.fullName = firstName + " " + lastName;
		this.id = Admin.idGenerator();
	}

	public Admin(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.fullName = firstName + " " + lastName;
		this.id = Admin.idGenerator();
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
		return Admin.currentId++;
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return this.role;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFullName(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub

	}
}
