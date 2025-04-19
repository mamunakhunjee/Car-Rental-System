package com.carRental.authentication;

import java.util.Scanner;

import com.carRental.dashboard.UserDashBoard;
import com.carRental.exceptions.InvalidUserException;
import com.carRental.exceptions.SignUpException;
import com.carRental.manager.LoginMenuManager;
import com.carRental.manager.SignUpMenuManager;
import com.carRental.manager.UserMenuManager;
import com.carRental.models.User;
import com.carRental.repositories.UserRepository;

public class CustomerAuthentication implements Authentication {
	public void main(Scanner sc) {
		UserMenuManager userMenuManager = new UserMenuManager();
		int choice = userMenuManager.userChoice(sc);

		handleMenuChoice(choice, sc);
	}

	private void handleMenuChoice(int choice, Scanner sc) {
		switch (choice) {
		case 1:
			LoginMenuManager loginMenuManager = new LoginMenuManager();
			String[] credential = loginMenuManager.getUserCredential(sc);
			ValidateLogin validateLogin = new ValidateLogin();
			boolean isValid = false;
			try {
				isValid = validateLogin.validate(credential, "Customer");
				if (isValid) {
					System.out.println("Login succesful.\n\n");
					UserDashBoard userDashBoard = new UserDashBoard();
					userDashBoard.menu(sc, credential[0]);
				}

			} catch (InvalidUserException e) {
				System.out.println(e.getMessage() + "\nLogin failed.");
			}
			break;
		case 2:
			SignUpMenuManager signUpMenuManager = new SignUpMenuManager();
			User customer = signUpMenuManager.getNewUserDetails(sc);
			UserRepository userRepo = UserRepository.getInstance();
			try {
				if (userRepo.addUser(customer)) {
					System.out.println("Sign up successful.");
				}
			} catch (SignUpException e) {
				System.out.println(e.getLocalizedMessage() + "\nSign Up failed.");
			}
			main(sc);
			break;
		}
	}
}
