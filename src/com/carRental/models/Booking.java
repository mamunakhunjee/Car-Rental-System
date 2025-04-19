package com.carRental.models;

import java.time.LocalDate;

public class Booking {
	private static int currentId = 1;
	private static Double totalRevenue = 0.0;

	private int bookingId;
	private String userEmail;
	private Car car;
	private LocalDate startDate;
	private LocalDate endDate;
	private int bookedFor;

	public Booking(String userEmail, Car car, LocalDate startDate, LocalDate endDate, int bookedFor) {
		this.bookingId = Booking.idGenerator();
		this.userEmail = userEmail;
		this.car = car;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bookedFor = bookedFor;
	}

	public Booking(String userEmail, Car car, int bookedFor) {
		this.bookingId = Booking.idGenerator();
		this.userEmail = userEmail;
		this.car = car;
		this.bookedFor = bookedFor;
	}

	public static Double getTotalRevenue() {
		return Booking.totalRevenue;
	}

	public int getBookedFor() {
		return this.bookedFor;
	}

	public static void setTotalRevenue(Double revenue) {
		Booking.totalRevenue += revenue;
	}

	public int getBookingId() {
		return this.bookingId;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public Car getCar() {
		return this.car;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	private static int idGenerator() {
		return Booking.currentId++;
	}
}
