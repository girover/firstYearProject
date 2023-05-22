package presentation.component;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;

import app.Helper;
import database.entities.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import presentation.BaseController;
import window.Window;

public class CarItemController extends BaseController {

	private Car car;

	@FXML
	private VBox carItem;

	@FXML
	private Button btnShow;

	@FXML
	private Label carName;

	@FXML
	private Label mileage;

	@FXML
	private Label modelYear;

	@FXML
	private Label price;

	public void setData(Car car) {
		this.car = car;

		carName.setText(car.getModel());
		mileage.setText(Integer.toString(car.getMileage()) + " km.");
		modelYear.setText(Integer.toString(car.getYear()));
		price.setText(Helper.formatCurrency(car.getPrice()));
	}

	@FXML
	void handleBtnShowClick(ActionEvent event) {
		Window.showCinformDialog("Title", car.getBrand() + " " + car.getModel());
	}

	@FXML
	void handleVBoxOnMouseClicked(MouseEvent event) {
		
		if (event.getClickCount() == 2) {
			fire(2);
		}
	}

	@FXML
	void handleOnMouseEntered(MouseEvent event) {
		carItem.setStyle("-fx-background-color:#fde047;");
	}

	@FXML
	void handleOnMouseExited(MouseEvent event) {
		carItem.setStyle("-fx-background-color:#ffffff;");
	}

	public Car getCar() {
		return car;
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
