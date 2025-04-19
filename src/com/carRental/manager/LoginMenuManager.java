package com.carRental.manager;

import java.util.Scanner;

import com.carRental.exceptions.InvalidDetailsException;

public class LoginMenuManager {
	public String[] getUserCredential(Scanner sc) {
		String email;

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

		System.out.print("Password: ");
		String password = sc.nextLine();

		return new String[] { email, password };
	}

	private Boolean validateEmail(String email) {
		return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}
}
