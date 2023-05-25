package test;

import static database.entities.factory.Factory.*;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.junit.jupiter.api.Test;

import database.entities.City;
import database.entities.Customer;
import database.repositories.CityRepository;
import database.repositories.CustomerRepository;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class CityRepositoryTest extends BaseTestCase {

	private CityRepository repo = new CityRepository();

	@Test
	void itShouldAddNewCityToDatabase() {

		City city = cityFactory().make();
		int insertedId = repo.add(city);
		
		assertTrue(insertedId > 0);
	}

	@Test
	void shouldFindCityInDatabaseByItsZipCode() {
		City city = cityFactory().make();
		assertTrue(repo.add(city) > 0);
		
		City retievedCity = repo.find(city.getZipCode());
		
		assertTrue(retievedCity != null);
		assertTrue(retievedCity.getZipCode().equals(city.getZipCode()));
	}

}
