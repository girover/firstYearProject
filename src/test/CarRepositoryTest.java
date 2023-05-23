package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import database.entities.Car;
import database.entities.factory.Factory;
import database.repositories.CarRepository;
import faker.Faker;

/**
 * This test case class is responsible for testing methods in "CarRepository" class.
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class CarRepositoryTest extends BaseTestCase {

	private CarRepository carRepo = new CarRepository();
	/**
	 * This method is called once before all methods in this test class.
	 */
	@BeforeAll
	public static void setUpCarRepoistoryTest() {

	}
	
	@Test
	void testAddNewCarToDatabase() {
		int generatedID = carRepo.add(Faker.unsoldCar());
		assertTrue(generatedID > 0);
	}
	
	@Test
	void testAddListOfNewCarsToDatabase() {
		
		int generatedRows = carRepo.addAll(Factory.carFactory().make(10));
		assertTrue(generatedRows == 10);
	}
	
	@Test
	void shouldUpdateCarInDatabaseWithNewPrice() {
		Car car = Factory.carFactory().make();
		assertTrue(carRepo.add(car) > 0);
		
		car.setPrice(1120000);
		assertTrue(carRepo.update(car));
	}
	
	@Test
	void shouldDelteCarFromDatabase() {
		Car car = Factory.carFactory().make();
		assertTrue(carRepo.add(car) > 0);
		
		assertTrue(carRepo.delete(car));
	}
	
	@Test
	void shouldCountCarsInDatabase() {
		
		assertTrue(carRepo.add(Factory.carFactory().make()) > 0);
		assertTrue(carRepo.add(Factory.carFactory().make()) > 0);
		
		assertTrue(carRepo.count() == 2);
	}
	
	@Test
	void shouldFindCarInDatabaseByItsId() {
		Car car = Factory.carFactory().make();
		
		assertTrue(carRepo.add(car) > 0);
		int id = car.getId();
		
		Car retrievedCar = new Car();
		retrievedCar = (Car) carRepo.find(id);
		
		assertTrue(retrievedCar.getId() == id);
	}

}
