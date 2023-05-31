package presentation.controllers.car;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import entities.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import presentation.controllers.BaseController;
import presentation.controllers.component.CarItemController;
import presentation.window.Component;
import services.CarService;
/**
 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *	
 * @author Majed Hussein Farhan
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
 */
public class CarsController extends BaseController {

	protected CarService carService = new CarService();

	@FXML
	protected Button btnAll;
	
	@FXML
	protected FlowPane carsContainer;

	@FXML
	protected ComboBox<String> cbModels;
	
	@FXML
	protected TextField inputSearchVIN;

	@FXML
	protected BorderPane mainView;

	protected ObservableList<Car> carsList = FXCollections.observableArrayList();
	protected ObservableList<String> modelsList = FXCollections.observableArrayList();
	
	protected Car selectedCar;

	@FXML
	void handleSearch(ActionEvent event) {
		String model = cbModels.getSelectionModel().getSelectedItem();

		loadCars(carService.getByModel(model));
	}
	
	@FXML
    void handleInputSearchVINOnAction(ActionEvent event) {
		loadCars(carService.getByVIN(inputSearchVIN.getText()));
    }
	
	@FXML
	void handleBtnAllClick(ActionEvent event) {
		loadCars(carService.getAll());
	}
	
	protected void loadCars(ArrayList<Car> cars) {
		carsList.clear();
		carsList.addAll(cars);
		renderCars();
	}

	protected void renderCars() {
		carsContainer.getChildren().clear();
		for (Car car : carsList) {
			Component component = new Component("CarItem.fxml");
			carsContainer.getChildren().add((VBox) component.get());
			CarItemController controller = (CarItemController) component.getController();
			controller.setData(car);
			controller.addObserver(this);
		}
	}

	protected void loadModels() throws SQLException {
		ArrayList<String> models = carService.getDistintModels();
		modelsList.addAll(models);
		cbModels.setItems(modelsList);
	}
	
	public void setSelectedCar(Car car) {
		selectedCar = car;
	}
	
	public Car getSelectedCar() {
		return selectedCar;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof CarItemController) {
			setSelectedCar(((CarItemController)o).getCar());
			fire();
			if(arg instanceof Integer && (int)arg == 2) {
				btnClose.fire();
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			loadCars(carService.getAll());
			loadModels();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
