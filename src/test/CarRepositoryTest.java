package test;

import static database.entities.factory.Factory.carFactory;
import org.junit.jupiter.api.BeforeAll;
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

	private CarRepository carRepo = new CarRepository();
	/**
	 * This method is called once before all methods in this test class.
	 */
	@BeforeAll
	public static void setUpCarRepoistoryTest() {

	}
	
	@Test
	void testAddNewCarToDatabase() {
		int generatedID = carRepo.add(carFactory().make());
		assertTrue(generatedID > 0);
	}
	
	@Test
	void testAddListOfNewCarsToDatabase() {
		
		int generatedRows = carRepo.addAll(carFactory().make(10));
		assertTrue(generatedRows == 10);
	}
	
	@Test
	void shouldUpdateCarInDatabaseWithNewPrice() {
		Car car = carFactory().make();
		assertTrue(carRepo.add(car) > 0);
		
		car.setPrice(1120000);
		assertTrue(carRepo.update(car));
	}
	
	@Test
	void shouldDelteCarFromDatabase() {
		Car car = carFactory().make();
		assertTrue(carRepo.add(car) > 0);
		
		assertTrue(carRepo.delete(car));
	}
	
	@Test
	void shouldCountCarsInDatabase() {
		
		assertTrue(carRepo.add(carFactory().make()) > 0);
		assertTrue(carRepo.add(carFactory().make()) > 0);
		
		assertTrue(carRepo.count() == 2);
	}
	
	@Test
	void shouldFindCarInDatabaseByItsId() {
		Car car = carFactory().make();
		
		assertTrue(carRepo.add(car) > 0);
		int id = car.getId();
		
		Car retrievedCar = new Car();
		retrievedCar = (Car) carRepo.find(id);
		
		assertTrue(retrievedCar.getId() == id);
	}

}
