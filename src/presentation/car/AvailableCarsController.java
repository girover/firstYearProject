package presentation.car;

import java.util.ArrayList;

import database.entities.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AvailableCarsController extends CarsController {

	@FXML
	void handleSearch(ActionEvent event) {
		String model = cbModels.getSelectionModel().getSelectedItem();

		carsList.clear();
		carsList.addAll(carService.getAvailableCarsByModel(model));
		renderCars();
	}
	
	@FXML
    void handleInputSearchVINOnAction(ActionEvent event) {
		carsList.clear();
		carsList.addAll(carService.getAvailableCarsByVIN(inputSearchVIN.getText()));
		renderCars();
    }
	
	@Override
	protected void loadCars() {
		ArrayList<Car> cars = carService.getAvailableCars();
		carsList.addAll(cars);
	}
}
