package com.carRental.manager;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.carRental.authentication.AdminAuthentication;
import com.carRental.authentication.CustomerAuthentication;
import com.carRental.exceptions.InvalidChoiceException;

public class MenuManager {
	public void menu(Scanner sc) {
		printMenuOptions();

		int choice = -1;

		while (true) {
			try {
				if (!sc.hasNextInt()) {
					throw new InputMismatchException("Invalid input! Please enter a number.");
				}

				choice = sc.nextInt();
				sc.nextLine();
				validateMenuChoice(choice);
				break;
			} catch (InvalidChoiceException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}

		handleMenuChoice(choice, sc);
	}

	private void printMenuOptions() {
		System.out.println("Please select an option");
		System.out.println("1. Admin");
		System.out.println("2. Customer");
		System.out.println("3. Exit");
	}

	private void validateMenuChoice(int choice) {
		if (choice < 1 || choice > 3) {
			throw new InvalidChoiceException("Please enter a number between 1 and 3.");
		}
	}

	private void handleMenuChoice(int choice, Scanner sc) {
		switch (choice) {
		case 1:
			AdminAuthentication adminAuthentication = new AdminAuthentication();
			adminAuthentication.main(sc);
			break;
		case 2:
			CustomerAuthentication customerAuthentication = new CustomerAuthentication();
			customerAuthentication.main(sc);
			break;
		case 3:
			System.out.println("Thank You for using Car Rental Service");
			return;
		}
	}
}
