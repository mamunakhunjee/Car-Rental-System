package com.carRental.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.carRental.exceptions.SignUpException;
import com.carRental.exceptions.UserNotFoundException;
import com.carRental.models.Admin;
import com.carRental.models.Customer;
import com.carRental.models.User;

public class UserRepository {

	private static final UserRepository instance = new UserRepository();
	private final Map<String, User> users = new HashMap<>();

	private UserRepository() {
		defaultAdmin(new Admin("Cristiano ", "Messi", "cristianomessi@carrental.com", "Football@123"));
	}

	private void defaultAdmin(Admin admin) {
		try {
			addUser(admin);
		} catch (SignUpException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public static UserRepository getInstance() {
		return UserRepository.instance;
	}

	public Boolean addUser(User user) throws SignUpException {
		if (user == null || users.containsKey(user.getEmail())) {
			throw new SignUpException("Email already exist.");
		}
		users.put(user.getEmail(), user);
		return true;
	}

	public Boolean removeUser(String email) throws UserNotFoundException {
		Optional<User> user = getUserByEmail(email);
		if (!user.isPresent()) {
			throw new UserNotFoundException("No user registered by the email.");
		}
		users.remove(email);
		return true;
	}

	public List<User> getAllUsers() {
		return new ArrayList<>(users.values());
	}

	public Optional<User> getUserById(int id) {
		for (User user : users.values()) {
			if (user.getId() == id) {
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}

	public Optional<User> getUserByEmail(String email) {
		if (email == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(users.get(email));
	}

	public Boolean updateUser(String email, User updatedUser) throws UserNotFoundException {
		Optional<User> optionalUser = getUserByEmail(email);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("Customer not found.");
		}
		User updateUser = optionalUser.get();
		updateUser.setFirstName(updatedUser.getFirstName());
		updateUser.setLastName(updatedUser.getLastName());
		updateUser.setFullName(updatedUser.getFullName());
		updateUser.setPassword(updatedUser.getPassword());
		return true;
	}
}
