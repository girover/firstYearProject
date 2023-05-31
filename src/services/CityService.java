package services;

import java.util.ArrayList;

import database.repositories.CityRepository;
import entities.City;
import entities.Entity;

/**
 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 *         
 */
public class CityService extends BaseResourceService {

	private CityRepository repo = new CityRepository();
	
	@Override
	public ArrayList<City> getAll() {
		
		return null;
	}

	@Override
	public City find(Object id) {
		return repo.find(id);
	}

	@Override
	public City create(Entity entity) {
		
		City city = (City) entity;
		
		repo.add(city);
		
		return city;
	}

	@Override
	public boolean update(Entity entity) {
		return false;
	}

	@Override
	public boolean delete(Entity entity) {
		return false;
	}
	
	public City getByCity(String city) {
		
		ArrayList<City> cities = (ArrayList<City>) repository.getByCondition("city", "=", city);
		
		if(cities.size()==0)
			return null;
		
		return cities.get(0);
	}

}
