package presentation.car;

import java.util.ArrayList;

import database.entities.Car;

public class FreeCarsController extends CarsController {

	@Override
	protected void loadCars() {
		ArrayList<Car> cars = carService.getFreeCars();
		carsList.addAll(cars);
	}
}
