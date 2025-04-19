package com.carRental.manager;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.carRental.exceptions.InvalidChoiceException;

public class UserMenuManager {
	public int userChoice(Scanner sc) {
		printMenuOptions();

		int choice = -1;

		while (true) {
			try {
				if (!sc.hasNextInt()) {
					throw new InputMismatchException("Invalid input! Please enter a number.");
				}

				choice = sc.nextInt();
				validateMenuChoice(choice);
				break;
			} catch (InvalidChoiceException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
			sc.nextLine();
		}

		sc.nextLine();
		return choice;
	}

	private void printMenuOptions() {
		System.out.println("1. Sign In");
		System.out.println("2. Sign Up");
	}

	private void validateMenuChoice(int choice) {
		if (choice < 1 || choice > 2) {
			throw new InvalidChoiceException("Please enter a number between 1 and 2.");
		}
	}
}
