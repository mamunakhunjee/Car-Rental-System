package com.carRental.dashboard;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.carRental.exceptions.CarNotFoundException;
import com.carRental.exceptions.InvalidChoiceException;
import com.carRental.exceptions.InvalidDetailsException;
import com.carRental.exceptions.SignUpException;
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

public class AdminDashBoard implements DashBoard {
	public void menu(Scanner sc, String email) {
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

	private void printMenuOptions() {
		System.out.println("Please select an option");
		System.out.println("1. Show all cars.");
		System.out.println("2. Show all customers.");
		System.out.println("3. Show active rentals.");
		System.out.println("4. Show available cars.");
		System.out.println("5. Total revenue.");
		System.out.println("6. Add new car.");
		System.out.println("7. Remove car.");
		System.out.println("8. Update car details.");
		System.out.println("9. Add new customer.");
		System.out.println("10. Remove customer.");
		System.out.println("11. Update customer details.");
		System.out.println("12. Log out.");
	}

	private void validateMenuChoice(int choice) {
		if (choice < 1 || choice > 12) {
			throw new InvalidChoiceException("Please enter a number between 1 and 12.");
		}
	}

	private void handleMenuChoice(int choice, Scanner sc, User customer) {
		CarRepository carRepo = CarRepository.getInstance();
		UserRepository userRepo = UserRepository.getInstance();
		BookingRepository bookingRepo = BookingRepository.getInstance();

		SignUpMenuManager signUpMenuManager = new SignUpMenuManager();
		switch (choice) {
		case 1:
			List<Car> cars = carRepo.getAllCars();
			if (cars.isEmpty()) {
				System.out.println("No cars available.");
				break;
			}
			for (Car car : cars) {
				printCar(car);
			}
			break;
		case 2:
			List<User> users = userRepo.getAllUsers();
			int customerCount = 0;
			for (User user : users) {
				if (user.getRole().equals("Customer")) {
					printUser(user);
					customerCount++;
				}
			}
			if (customerCount == 0) {
				System.out.println("No customers is available.");
			}
			break;
		case 3:
			List<Booking> bookings = bookingRepo.getAllBookings();
			if (bookings.isEmpty()) {
				System.out.println("No active bookings available.");
				break;
			}
			for (Booking booking : bookings) {
				printBookingDetails(booking);
			}
			return;
		case 4:
			List<Car> availableCars = carRepo.getAvailableCar();
			if (availableCars.isEmpty()) {
				System.out.println("No cars are available.");
				break;
			}
			for (Car car : availableCars) {
				printCar(car);
			}
			break;
		case 5:
			System.out.println("Total revenue : " + Booking.getTotalRevenue());
			break;
		case 6:
			Car car = getCarDetails(sc);
			try {
				carRepo.addCar(car);
				System.out.println("Car added successfully.");
			} catch (CarNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("Car remove failed.");
			}
			break;
		case 7:
			int carId = getCarId(sc);
			try {
				carRepo.removeCar(carId);
				System.out.println("Car removed successfully.");
			} catch (CarNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("Car remove failed.");
			}
			break;
		case 8:
			int updatedCarId = getCarId(sc);
			Car updatedCar = getCarDetails(sc);
			try {
				carRepo.updateCar(updatedCarId, updatedCar);
				System.out.println("Car updated successfully.");
			} catch (CarNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("Car update failed.");
			}
			break;
		case 9:
			Customer newCustomer = signUpMenuManager.getNewUserDetails(sc);
			try {
				userRepo.addUser(newCustomer);
				System.out.println("Customer added successfully");
			} catch (SignUpException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("Customer add failed.");
			}
			break;
		case 10:
			String email = getUserEmail(sc);
			try {
				userRepo.removeUser(email);
				System.out.println("User successfully removed.");
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("User remove failed.");
			}
			break;
		case 11:
			String updatedUserEmail = getUserEmail(sc);
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
		case 12:
			System.out.println("Logged out successfully");
			Main.main(null);
			return;
		}
		pressEnterToContinue(sc);
		menu(sc, customer.getEmail());
	}

	private Car getCarDetails(Scanner sc) {
		System.out.print("Make : ");
		String make = sc.next();
		System.out.print("Model : ");
		String model = sc.next();
		System.out.print("Rent/Day : ");
		Double rentPerDay = sc.nextDouble();
		sc.nextLine();

		return new Car(make, model, rentPerDay);
	}

	private int getCarId(Scanner sc) {
		int carId = -1;
		while (true) {
			System.out.print("Enter the car Id : ");
			try {
				if (!sc.hasNextInt()) {
					throw new InputMismatchException("Invalid input! Please enter a valid car id.");
				}

				carId = sc.nextInt();
				sc.nextLine();
				break;
			} catch (InvalidChoiceException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}

		return carId;
	}

	private String getUserEmail(Scanner sc) {
		String email = "";
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
		return email;
	}

	private Boolean validateEmail(String email) {
		return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}

	private void printCar(Car car) {
		System.out.println("Id : " + car.getId());
		System.out.println("Make : " + car.getMake());
		System.out.println("Model : " + car.getModel());
		System.out.println("Rent : " + car.getRentalRatePerDay());
//		System.out.println("Available : " + car.isAvailable());
	}

	private void printUser(User user) {
		System.out.println("Id : " + user.getId());
		System.out.println("Name : " + user.getFullName());
		System.out.println("Email : " + user.getEmail());
		System.out.println("Role : " + user.getRole());
	}

	private void printBookingDetails(Booking booking) {
		System.out.println("Booking Id : " + booking.getBookingId());
		System.out.println("Booked by : " + booking.getUserEmail());
		printCar(booking.getCar());
		System.out.println("Booked for : " + booking.getBookedFor() + " days.");
	}

	public static void pressEnterToContinue(Scanner sc) {
		System.out.println("Press Enter to return to menu...");
		sc.nextLine();
	}

}
