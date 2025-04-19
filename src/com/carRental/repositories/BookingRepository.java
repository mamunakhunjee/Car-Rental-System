package com.carRental.repositories;

import java.util.ArrayList;
import java.util.List;

import com.carRental.exceptions.CarNotAvailableException;
import com.carRental.models.Booking;

public class BookingRepository {
	private static final BookingRepository instance = new BookingRepository();
	private final List<Booking> bookings = new ArrayList<>();

	private BookingRepository() {

	}

	public Boolean addBooking(Booking booking) throws CarNotAvailableException {
		if (booking == null || !booking.getCar().isAvailable()) {
			throw new CarNotAvailableException("This car is not available to book");
		}
		bookings.add(booking);
		booking.getCar().setAvailable(false);
		Booking.setTotalRevenue(booking.getCar().getTotalRent(booking.getBookedFor()));
		return true;
	}

	public Boolean removeBooking(String userEmail, int bookingId) throws CarNotAvailableException {
		for (Booking booking : bookings) {
			if (booking.getBookingId() == bookingId && booking.getUserEmail().equals(userEmail)) {
				booking.getCar().setAvailable(true);
				bookings.remove(booking);
				return true;
			}
		}
		throw new CarNotAvailableException("Details do not match with the user or car.");
	}

	public List<Booking> getBookingByUserEmail(String email) {
		List<Booking> bookingByUser = new ArrayList<>();
		for (Booking booking : bookings) {
			if (booking.getUserEmail().equals(email)) {
				bookingByUser.add(booking);
			}
		}
		return bookingByUser;
	}

	public List<Booking> getAllBookings() {
		return new ArrayList<>(bookings);
	}

	public static BookingRepository getInstance() {
		return BookingRepository.instance;
	}
}
