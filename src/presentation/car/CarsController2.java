package presentation.car;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.BaseController;
import presentation.component.CarItemController;
import presentation.component.PaginationController;
import services.CarService;
import services.Paginator;
import window.Component;
import window.Window;

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
	
	@FXML
    private HBox pagination;

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
	}
	
	private void loadCars(int page) {
		Paginator p = carService.pagination(page);
		ArrayList<Car> cars = p.castDataTo(Car.class);
//		ArrayList<Car> cars = carService.getPage(page);
		carsList.addAll(cars);
	}

	private void renderCars() {
		for (Car car : carsList) {
			Component component = new Component("CarItem.fxml");
			carsContainer.getChildren().add((VBox) component.get());
			CarItemController controller = (CarItemController) component.getController();
			controller.setData(car);
		}
	}

	private void loadModels() throws SQLException {
		ArrayList<String> models = carService.getDistintModels();
		modelsList.addAll(models);
		cbModels.setItems(modelsList);
	}
	
	private void paginate() {
		Component pagination = new Component("Pagination.fxml");
		PaginationController controller = (PaginationController)pagination.getController();
		controller.setTotalPages(10);
		controller.addObserver(this);
		this.pagination.getChildren().add((HBox)pagination.get());
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof PaginationController) {
			int currentPage = (int) arg;
			Window.showSuccessMessage(Integer.toString(currentPage), "Majed");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			loadCars(1);
			loadModels();
			renderCars();
			paginate();
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
