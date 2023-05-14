package presentation.component;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.BaseController;
import window.Window;

public class CarItemController extends BaseController {

	private Car car;
	
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
    	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String formattedPrice = numberFormat.format(car.getPrice());

    	carName.setText(car.getModel());
    	mileage.setText(Integer.toString(car.getMileage()) + " km.");
    	modelYear.setText(Integer.toString(car.getYear()));
    	price.setText(formattedPrice + " kr.");
    }

    @FXML
    void handleBtnShowClick(ActionEvent event) {
    	Window.showCinformDialog("Title", car.getBrand() + " " + car.getModel());
    }
	
	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
