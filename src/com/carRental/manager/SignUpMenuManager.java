package com.carRental.manager;

import java.util.Scanner;

import com.carRental.exceptions.InvalidDetailsException;
import com.carRental.models.Customer;

public class SignUpMenuManager {
	public Customer getNewUserDetails(Scanner sc) {

		String firstName = "";
		String lastName = "";
		String email = "";
		String password = "";

		while (true) {
			System.out.print("First Name : ");
			try {
				firstName = sc.nextLine().trim();
				if (!validateName(firstName)) {
					throw new InvalidDetailsException("Please enter a valid first name.");
				}
				break;
			} catch (InvalidDetailsException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.print("Last Name : ");
			try {
				lastName = sc.nextLine().trim();
				if (!validateName(lastName)) {
					throw new InvalidDetailsException("Please enter a valid last name.");
				}
				break;
			} catch (InvalidDetailsException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.print("Email : ");
			try {
				email = sc.nextLine().trim();
				if (!validateEmail(email)) {
					throw new InvalidDetailsException("Email must be in the format: example@domain.com");
				}
				break;
			} catch (InvalidDetailsException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.print("Password : ");
			try {
				password = sc.nextLine();
				if (!validatePasswordStrength(password)) {
					throw new InvalidDetailsException(
							"Password must include minimum 8 character and must have at least one uppercase, one lowercase, one number, and one special character");
				}
				break;
			} catch (InvalidDetailsException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.print("Confirm Password : ");
			try {
				String rePassword = sc.nextLine();
				if (!validatePassword(password, rePassword)) {
					throw new InvalidDetailsException("Password Mismatch.");
				}
				break;
			} catch (InvalidDetailsException e) {
				System.out.println(e.getMessage());
			}
		}

		return new Customer(firstName, lastName, email, password);
	}

	private Boolean validateName(String name) {
		return name.matches("[A-Z][a-z]*$");
	}

	private Boolean validateEmail(String email) {
		return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}

	private Boolean validatePasswordStrength(String password) {
		return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
	}

	private Boolean validatePassword(String password1, String password2) {
		return password1.equals(password2);
	}
}
