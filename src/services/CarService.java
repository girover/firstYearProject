package services;

import java.sql.SQLException;
import java.util.ArrayList;

import database.repositories.CarImageRepository;
import database.repositories.CarRepository;
import entities.Car;
import entities.CarImage;
import entities.Entity;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with cars.
 * such as retrieving all cars, retrieving a set of cars, 
 * retrieving a single car by ID, updating a car, 
 * and deleting a car.
 * 
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class CarService extends BaseResourceService {

	public CarService() {
		repository = new CarRepository();
	}

	@Override
	public Car find(Object id) {
		Car car = (Car) repository.find(id);
		return car;
	}

	@Override
	public ArrayList<Car> getAll() {
		
		return (ArrayList<Car>) repository.getAll();
	}

	@Override
	public Car create(Entity entity) {
		
		Car car = (Car) entity;

		repository.add(car);

		return car;
	}

	@Override
	public boolean update(Entity entity) {
		return repository.update((Car) entity);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((Car) entity);
	}
	
	public ArrayList<CarImage> getImages(Car car) throws SQLException{
		CarImageRepository repo = new CarImageRepository();
		
		return repo.getByCar(car);
	}
	
	public ArrayList<String> getDistintModels() throws SQLException{
		ArrayList<String> models = new ArrayList<>();
		
		ArrayList<Car> carsWithDistintModels = ((CarRepository)repository).getDistinctModels();
		for (Car car : carsWithDistintModels) {
			models.add(car.getModel());
		}
		
		return models;
	}
	
	public ArrayList<Car> getAvailableCars(){
		return ((CarRepository)repository).getAvailableCars();
	}
	
	public ArrayList<Car> getByModel(String model){
		
		return ((CarRepository)repository).getByModel(model);
	}
	
	public ArrayList<Car> getAvailableCarsByModel(String model){
		
		return ((CarRepository)repository).getAvailableCarsByModel(model);
	}
	
	public ArrayList<Car> getByVIN(String vin){		
		return (ArrayList<Car>) repository.getByCondition("VIN", "=", vin);
	}
	
	public ArrayList<Car> getAvailableCarsByVIN(String vin){		
		return (ArrayList<Car>) ((CarRepository)repository).getAvailableCarsByVIN(vin);
	}
	

}
