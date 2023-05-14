package presentation.car;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Car;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import presentation.BaseController;
import presentation.component.CarItemController;
import services.CarService;
import window.Component;

public class CarsController2 extends BaseController {

	private CarService carService = new CarService();

	@FXML
	private Button btnClose;

	@FXML
	private FlowPane carsContainer;

	@FXML
	private ComboBox<String> cbModels;

	@FXML
	private BorderPane mainView;

	private ObservableList<Car> carsList = FXCollections.observableArrayList();
	private ObservableList<String> modelsList = FXCollections.observableArrayList();

	@FXML
	void handleBtnCloseClick(ActionEvent event) {
		closeWindow(event);
	}

	@FXML
	void handleSearch(ActionEvent event) {
		String model = cbModels.getSelectionModel().getSelectedItem();

		carsList.clear();
		carsList.addAll(carService.getByModel(model));
		carsContainer.getChildren().clear();
		renderCars();
	}

	private void loadCars() {
		ArrayList<Car> cars = carService.getAll();
		carsList.addAll(cars);
//    	tViewCars.setItems(carsList);
	}

	private void renderCars() {
		for (Object car : carsList) {
			Component component = new Component("CarItem.fxml");
			carsContainer.getChildren().add((VBox) component.get());
			CarItemController controller = (CarItemController) component.getController();
			controller.setData((Car) car);
		}
	}

	private void loadModels() throws SQLException {
		ArrayList<String> models = carService.getDistintModels();
		modelsList.addAll(models);
		cbModels.setItems(modelsList);
	}

	@Override
	public void update(Observable o, Object arg) {

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
		modelsList.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                System.out.println("List changed: " + change.getList());
            }
        });
	}

}
