package com.carRental.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.carRental.exceptions.CarNotFoundException;
import com.carRental.models.Car;

public class CarRepository {
	private static final CarRepository instance = new CarRepository();
	private final Map<Integer, Car> cars = new HashMap<>();

	private CarRepository() {

	}

	public static CarRepository getInstance() {
		return CarRepository.instance;
	}

	public Boolean addCar(Car car) throws CarNotFoundException {
		if (car == null || cars.containsKey(car.getId())) {
			throw new CarNotFoundException("Car already exist.");
		}
		cars.put(car.getId(), car);
		return true;
	}

	public Boolean removeCar(int id) throws CarNotFoundException {
		Optional<Car> optionalCar = getCarById(id);
		if (!optionalCar.isPresent()) {
			throw new CarNotFoundException("Car not found.");
		}
		cars.remove(id);
		return true;
	}

	public List<Car> getAllCars() {
		return new ArrayList<>(cars.values());
	}

	public List<Car> getAvailableCar() {
		List<Car> availableCar = new ArrayList<>();
		for (Car car : cars.values()) {
			if (car.isAvailable()) {
				availableCar.add(car);
			}
		}
		return availableCar;
	}

	public Optional<Car> getCarById(int id) {
		for (Car car : cars.values()) {
			if (car.getId() == id) {
				return Optional.of(car);
			}
		}
		return Optional.empty();
	}

	public Boolean updateCar(int updateCarId, Car car) throws CarNotFoundException {
		Optional<Car> optionalCar = getCarById(updateCarId);
		if (!optionalCar.isPresent()) {
			throw new CarNotFoundException("Car not found.");
		}
		Car updateCar = optionalCar.get();
		updateCar.setMake(car.getMake());
		updateCar.setModel(car.getModel());
		updateCar.setRentPerDay(car.getRentalRatePerDay());
		return true;
	}
}
