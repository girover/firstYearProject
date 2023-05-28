package test;

import static database.entities.factory.Factory.carFactory;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import database.entities.Car;
import database.repositories.CarRepository;

/**
 * This test case class is responsible for testing methods in "CarRepository" class.
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class CarRepositoryTest extends BaseTestCase {

	private CarRepository repo = new CarRepository();
	
	@Test
	void shouldAddNewCarToDatabase() {
		int generatedID = repo.add(carFactory().make());
		assertTrue(generatedID > 0);
	}
	
	@Test
	void shouldAddListOfCarsToDatabase() {
		
		int generatedRows = repo.addAll(carFactory().make(10));
		assertTrue(generatedRows == 10);
	}
	
	@Test
	void shouldFindCarInDatabaseByItsId() {
		Car car = carFactory().make();
		
		assertTrue(repo.add(car) > 0);
		int id = car.getId();
		
		Car retrievedCar = repo.find(id);
		
		assertTrue(retrievedCar.getId() == id);
	}
	
	@Test
	void shouldGetAllCarsFromDatabase() {

		ArrayList<Car> cars = carFactory().make(10);
		repo.addAll(cars);
		
		assertTrue(repo.getAll().size() == 10);
	}
	
	@Test
	void shouldCountCarsInTheDatabase() {
		
		assertTrue(repo.add(carFactory().make()) > 0);
		assertTrue(repo.add(carFactory().make()) > 0);
		
		assertTrue(repo.count() == 2);
	}
	
	@Test
	void shouldDeleteCarFromDatabase() {
		Car car = carFactory().make();
		assertTrue(repo.add(car) > 0);
		
		assertTrue(repo.delete(car));
		assertTrue(repo.count() == 0);
	}
	
	@Test
	void shouldPerformCorrectCarUpdateInDatabase() {
		Car car = carFactory().make();
		assertTrue(repo.add(car) > 0);
		
		car.setPrice(1120000);
		car.setModel("new model");
		car.setColor("red");
		car.setDoors(2);
		car.setFuelType("diesel");
		car.setHorsepower(200);
		car.setMileage(120000);
		
		assertTrue(repo.update(car));
		
		Car retreivedCar = repo.find(car.getId());
		
		assertTrue(retreivedCar.getPrice() == 1120000);
		assertTrue(retreivedCar.getModel().equals("new model"));
		assertTrue(retreivedCar.getColor().equals("red"));
		assertTrue(retreivedCar.getDoors() == 2);
		assertTrue(retreivedCar.getFuelType().equals("diesel"));
		assertTrue(retreivedCar.getHorsepower() == 200);
		assertTrue(retreivedCar.getMileage() == 120000);
	}

}
