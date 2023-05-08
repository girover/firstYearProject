package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import app.FormData;

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
 */
public class CarImageService extends BaseResourceService {

	public CarImageService() {
		repository = new CarImageRepository();
	}

	@Override
	public CarImage find(int id) {
		CarImage carImage = (CarImage) repository.find(id);
		return carImage;
	}

	@Override
	public ArrayList<CarImage> getAll() {
		
		ArrayList<CarImage> carImages = new ArrayList<>();
		
		ResultSet result = repository.getAll();
		
		try {
			while (result.next()) {
				CarImage carImage = new CarImage();
				if (carImage.makeFromResultSet(result))
					carImages.add(carImage);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carImages;
	}

	@Override
	public ArrayList<CarImage> getPage(int page) {

		
		ArrayList<CarImage> carImages = new ArrayList<>();
		
		ResultSet result = repository.getPage(page);
		try {
			while (result.next()) {
				CarImage carImage = new CarImage();
				if (carImage.makeFromResultSet(result))
					carImages.add(carImage);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carImages;
	}

	@Override
	public CarImage create(FormData data) {
		
		CarImage carImage = new CarImage();
		
		carImage.setCarID((int)data.input("carId"));
		carImage.setImage((String)data.input("image"));

		repository.add(carImage);

		return carImage;
	}

	@Override
	public CarImage update(Entity entity) {
		if (repository.update((CarImage) entity))
			return ((CarImage) entity);
		
		return null;
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((CarImage) entity);
	}

}
