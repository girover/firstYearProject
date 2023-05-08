package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import app.FormData;

import database.entities.Car;
import database.entities.Entity;
import database.repositories.CarRepository;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with cars.
 * such as retrieving all cars, retrieving a set of cars, 
 * retrieving a single car by ID, updating a car, 
 * and deleting a car.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class CarService extends BaseResourceService {

	public CarService() {
		repository = new CarRepository();
	}

	@Override
	public Car find(int id) {
		Car car = (Car) repository.find(id);
		return car;
	}

	@Override
	public ArrayList<Car> getAll() {
		
		ArrayList<Car> cars = new ArrayList<>();
		
		ResultSet result = repository.getAll();
		
		try {
			while (result.next()) {
				Car car = new Car();
				if (car.makeFromResultSet(result))
					cars.add(car);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cars;
	}

	@Override
	public ArrayList<Car> getPage(int page) {

		
		ArrayList<Car> cars = new ArrayList<>();
		
		ResultSet result = repository.getPage(page);
		try {
			while (result.next()) {
				Car car = new Car();
				if (car.makeFromResultSet(result))
					cars.add(car);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cars;
	}

	@Override
	public Car create(FormData data) {
		
		Car car = new Car();
		
		car.setBrand((String)data.input("brand"));
		car.setModel((String)data.input("model"));
		car.setYear((int)data.input("year"));
		car.setColor((String)data.input("color"));
		car.setMileage((int)data.input("mileage"));
		car.setTransmission((String)data.input("transmission"));
		car.setFuelType((String)data.input("fuelType"));
		car.setEngineSize((String)data.input("engineSize"));
		car.setHorsepower((int)data.input("horsepower"));
		car.setSeats((int)data.input("seats"));
		car.setDoors((int)data.input("doors"));
		car.setPrice((int)data.input("price"));
		car.setDescription((String)data.input("description"));

		repository.add(car);

		return car;
	}

	@Override
	public Car update(Entity entity) {
		if (repository.update((Car) entity))
			return ((Car) entity);
		
		return null;
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((Car) entity);
	}

}