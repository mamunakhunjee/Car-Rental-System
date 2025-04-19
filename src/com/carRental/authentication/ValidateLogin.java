package com.carRental.authentication;

import java.util.Optional;

import com.carRental.exceptions.InvalidUserException;
import com.carRental.models.User;
import com.carRental.repositories.UserRepository;

public class ValidateLogin {
	public Boolean validate(String[] credential, String role) throws InvalidUserException {
		UserRepository userRepo = UserRepository.getInstance();
		Optional<User> userOptional = userRepo.getUserByEmail(credential[0]);
		if (!userOptional.isPresent()) {
			throw new InvalidUserException("Email not found.");
		}
		User user = userOptional.get();
		if (!user.getPassword().equals(credential[1])) {
			throw new InvalidUserException("Wrong Password.");
		}
		if (!user.getRole().equals(role)) {
			throw new InvalidUserException("You are not authorized to login as " + role + ".");
		}
		return true;
	}
}
