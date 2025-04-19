package com.carRental.authentication;

import java.util.Scanner;

import com.carRental.dashboard.AdminDashBoard;
import com.carRental.exceptions.InvalidUserException;
import com.carRental.manager.LoginMenuManager;

public class AdminAuthentication implements Authentication {
	public void main(Scanner sc) {
		LoginMenuManager loginMenuManager = new LoginMenuManager();
		String[] credential = loginMenuManager.getUserCredential(sc);
		ValidateLogin validateLogin = new ValidateLogin();
		boolean isValid = false;
		try {
			isValid = validateLogin.validate(credential, "Admin");
			if (isValid) {
				System.out.println("Login successful.\n\n");
				AdminDashBoard adminDashBoard = new AdminDashBoard();
				adminDashBoard.menu(sc, credential[0]);
			}
		} catch (InvalidUserException e) {
			System.out.println(e.getMessage() + "\nLogin failed.");
			main(sc);
		}
	}
}
