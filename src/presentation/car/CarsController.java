package presentation.car;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Car;
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
import presentation.BaseController;
import presentation.component.CarItemController;
import services.CarService;
import window.Component;

public class CarsController extends BaseController {

	protected CarService carService = new CarService();

	@FXML
	protected Button btnClose;

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
	void handleBtnCloseClick(ActionEvent event) {
		closeWindow(event);
	}

	@FXML
	void handleSearch(ActionEvent event) {
		String model = cbModels.getSelectionModel().getSelectedItem();

		carsList.clear();
		carsList.addAll(carService.getByModel(model));
		renderCars();
	}
	
	@FXML
    void handleInputSearchVINOnAction(ActionEvent event) {
		carsList.clear();
		carsList.addAll(carService.getByVIN(inputSearchVIN.getText()));
		renderCars();
    }
	
	protected void loadCars() {
		ArrayList<Car> cars = carService.getAll();
		carsList.addAll(cars);
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
	
//	private void paginate() {
//		Component pagination = new Component("Pagination.fxml");
//		PaginationController controller = (PaginationController)pagination.getController();
//		controller.setTotalPages(10);
//		controller.addObserver(this);
//		this.pagination.getChildren().add((HBox)pagination.get());
//	}
	
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
			loadCars();
			loadModels();
			renderCars();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
