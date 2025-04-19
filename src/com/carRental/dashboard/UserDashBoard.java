package com.carRental.dashboard;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.carRental.exceptions.CarNotAvailableException;
import com.carRental.exceptions.InvalidChoiceException;
import com.carRental.exceptions.UserNotFoundException;
import com.carRental.main.Main;
import com.carRental.manager.SignUpMenuManager;
import com.carRental.models.Booking;
import com.carRental.models.Car;
import com.carRental.models.Customer;
import com.carRental.models.User;
import com.carRental.repositories.BookingRepository;
import com.carRental.repositories.CarRepository;
import com.carRental.repositories.UserRepository;

public class UserDashBoard implements DashBoard {

	@Override
	public void menu(Scanner sc, String email) {
		// TODO Auto-generated method stub

		UserRepository userRepo = UserRepository.getInstance();
		Optional<User> optUser = userRepo.getUserByEmail(email);
		User user = optUser.get();

		System.out.println("Hi " + user.getFirstName() + ",");

		printMenuOptions();

		int choice = getMenuChoice(sc);

		handleMenuChoice(choice, sc, user);
	}

	private int getMenuChoice(Scanner sc) {
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
		return choice;
	}

	private int getCarChoice(Scanner sc) {
		int choice = -1;
		while (true) {
			try {
				if (!sc.hasNextInt()) {
					throw new InputMismatchException("Invalid input! Please enter a valid car Id.");
				}

				choice = sc.nextInt();
				sc.nextLine();
				validateCarChoice(choice);
				break;
			} catch (InvalidChoiceException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		return choice;
	}

	private void validateCarChoice(int choice) {
		// TODO Auto-generated method stub
		CarRepository carRepo = CarRepository.getInstance();
		List<Car> cars = carRepo.getAvailableCar();
		for (Car car : cars) {
			if (car.getId() == choice) {
				return;
			}
		}
		throw new InvalidChoiceException("Please enter a valid car id");
	}

	private void printMenuOptions() {
		System.out.println("Please select an option");
		System.out.println("1. Show available cars.");
		System.out.println("2. My booking.");
		System.out.println("3. Update profile.");
		System.out.println("4. Rent a car.");
		System.out.println("5. Return a car.");
		System.out.println("6. Log out.");
	}

	private void validateMenuChoice(int choice) {
		if (choice < 1 || choice > 6) {
			throw new InvalidChoiceException("Please enter a number between 1 and 6.");
		}
	}

	private void handleMenuChoice(int choice, Scanner sc, User customer) {
		CarRepository carRepo = CarRepository.getInstance();
		UserRepository userRepo = UserRepository.getInstance();
		BookingRepository bookingRepo = BookingRepository.getInstance();

		SignUpMenuManager signUpMenuManager = new SignUpMenuManager();

		switch (choice) {
		case 1:
			List<Car> cars = carRepo.getAvailableCar();
			if (cars.isEmpty()) {
				System.out.println("No cars are available.");
			} else {
				for (Car car : cars) {
					printCar(car);
				}
			}
			break;
		case 2:
			List<Booking> bookings = bookingRepo.getBookingByUserEmail(customer.getEmail());
			if (bookings.isEmpty()) {
				System.out.println("No booking found");
			} else {
				for (Booking booking : bookings) {
					System.out.println("Booking Id : " + booking.getBookingId());
					printCar(booking.getCar());
					System.out.println("Booked for : " + booking.getBookedFor() + " days.");
					System.out.println("Total rent : " + booking.getCar().getTotalRent(booking.getBookedFor()));
				}
			}
			break;
		case 3:
			String updatedUserEmail = customer.getEmail();
			Customer updateCustomer = signUpMenuManager.getNewUserDetails(sc);
			try {
				userRepo.updateUser(updatedUserEmail, updateCustomer);
				System.out.println("Customer Updated successfully.");
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("Customer update failed.");
			}
			break;
		case 4:
			List<Car> carsAvailable = carRepo.getAvailableCar();
			if (carsAvailable.isEmpty()) {
				System.out.println("No cars are available.");
			} else {
				for (Car car : carsAvailable) {
					printCar(car);
				}
				System.out.println("Please enter the car Id you want to book");
				int bookingChoice = getCarChoice(sc);
				Optional<Car> optBookingCar = carRepo.getCarById(bookingChoice);
				int bookingDays = 0;
				System.out.print("Please enter total rent days : ");
				while (true) {
					try {
						if (!sc.hasNextInt()) {
							throw new InputMismatchException("Invalid input! Please enter valid day.");
						}

						bookingDays = sc.nextInt();
						sc.nextLine();
						break;
					} catch (InvalidChoiceException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
				}
				if (optBookingCar.isPresent()) {
					Car bookingCar = optBookingCar.get();
					System.out.println("Total rent will be : " + bookingCar.getTotalRent(bookingDays));
					System.out.print("Do you wish to continue? (Y/N) : ");
					String confirmation = sc.next();

					if (confirmation.equals("Y") || confirmation.equals("y")) {
						try {
							bookingRepo.addBooking(new Booking(customer.getEmail(), bookingCar, bookingDays));
							System.out.println("Booking successful.");
						} catch (CarNotAvailableException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("Booking failed because of not approval.");
					}
				}
			}
			break;
		case 5:
			System.out.println("Please enter the booking id : ");
			int returnBookingId = -1;
			while (true) {
				try {
					if (!sc.hasNextInt()) {
						throw new InputMismatchException("Please enter a valid booking id.");
					}
					returnBookingId = sc.nextInt();
					sc.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
			}
			try {
				bookingRepo.removeBooking(customer.getEmail(), returnBookingId);
				System.out.println("Car successfully returned.");
			} catch (CarNotAvailableException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("Car return unsuccessful.");
			}
			break;
		case 6:
			System.out.println("Logged out successfully");
			Main.main(null);
			return;
		}
		pressEnterToContinue(sc);
		menu(sc, customer.getEmail());
	}

	private void printCar(Car car) {
		System.out.println("Id : " + car.getId());
		System.out.println("Make : " + car.getMake());
		System.out.println("Model : " + car.getModel());
		System.out.println("Rent : " + car.getRentalRatePerDay());
		System.out.println("Available : " + car.isAvailable());
	}

	public static void pressEnterToContinue(Scanner sc) {
		System.out.println("Press Enter to return to menu...");
		sc.nextLine();
	}

}
