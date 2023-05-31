package services;

import java.util.ArrayList;

import database.entities.CarImage;
import database.entities.Entity;
import database.repositories.CarImageRepository;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with carImages.
 * such as retrieving all carImages, retrieving a set of carImages, 
 * retrieving a single carImage by ID, updating a carImage, 
 * and deleting a carImage.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 */
public class CarImageService extends BaseResourceService {

	public CarImageService() {
		repository = new CarImageRepository();
	}

	@Override
	public CarImage find(Object id) {
		CarImage carImage = (CarImage) repository.find(id);
		return carImage;
	}

	@Override
	public ArrayList<CarImage> getAll() {
		
		return (ArrayList<CarImage>) repository.getAll();
	}

	@Override
	public CarImage create(Entity entity) {
		
		CarImage carImage = (CarImage) entity;
		
		repository.add(carImage);

		return carImage;
	}

	@Override
	public boolean update(Entity entity) {
		return repository.update((CarImage) entity);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((CarImage) entity);
	}

}
