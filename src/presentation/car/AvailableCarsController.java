package presentation.car;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AvailableCarsController extends CarsController {

	@FXML
	void handleSearch(ActionEvent event) {
		String model = cbModels.getSelectionModel().getSelectedItem();

		loadCars(carService.getAvailableCarsByModel(model));
	}
	
	@FXML
    void handleInputSearchVINOnAction(ActionEvent event) {
		loadCars(carService.getAvailableCarsByVIN(inputSearchVIN.getText()));
    }
	
	@FXML
	void handleBtnAllClick(ActionEvent event) {
		loadCars(carService.getAvailableCars());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			loadCars(carService.getAvailableCars());
			loadModels();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
