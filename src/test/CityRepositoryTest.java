package test;

import static database.entities.factory.Factory.cityFactory;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import database.entities.City;
import database.repositories.CityRepository;

/**
 *  @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *      
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class CityRepositoryTest extends BaseTestCase {

	private CityRepository repo = new CityRepository();

	@Test
	void shouldAddNewCityToDatabase() {

		City city = cityFactory().make();
		int insertedId = repo.add(city);

		assertTrue(insertedId > 0);
	}

	@Test
	void shouldAddAListOfCitiesToTheDatabase() {

		ArrayList<City> cities = cityFactory().make(10);

		int createdCities = repo.addAll(cities);

		assertTrue(createdCities == 10);
	}

	@Test
	void shouldFindCityInDatabaseByZipCode() {

		City city = cityFactory().make();
		assertTrue(repo.add(city) > 0);

		City retievedCity = repo.find(city.getZipCode());

		assertTrue(retievedCity != null);
		assertTrue(retievedCity.getZipCode().equals(city.getZipCode()));
	}

	@Test
	void shouldGetAllCitiesFromTheDatabase() {

		ArrayList<City> cities = cityFactory().make(10);
		repo.addAll(cities);

		assertTrue(repo.getAll().size() == 10);
	}

	@Test
	void shouldCountCitiesInTheDatabase() {
		ArrayList<City> cities = cityFactory().make(10);
		repo.addAll(cities);

		assertTrue(repo.count() == 10);
	}

	@Test
	void shouldDeleteCityFromTheDatabase() {
		City city = cityFactory().make();
		repo.add(city);

		assertTrue(repo.count() == 1);

		repo.delete(city);
		assertTrue(repo.count() == 0);
	}

	@Test
	void shouldPerformCorrectCityUpdateInDatabase() {

		City city = cityFactory().make();

		int generatedId = repo.add(city);

		assertTrue(generatedId > 0);

		city.setCity("new city");

		assertTrue(repo.update(city));
		
		City retrievedCity = repo.find(city.getZipCode());
		assertTrue(retrievedCity != null);
		assertTrue(retrievedCity.getCity().equals("new city"));
	}
}
