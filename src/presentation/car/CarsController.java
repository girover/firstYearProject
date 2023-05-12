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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import presentation.BaseController;
import services.CarService;

public class CarsController extends BaseController {

	private CarService carService = new CarService();

	@FXML
    private Button btnClose;

    @FXML
    private ComboBox<String> cbModels;

    @FXML
    private TableColumn<Car, String> colColor;

    @FXML
    private TableColumn<Car, String> colDoors;

    @FXML
    private TableColumn<Car, String> colEngineSize;

    @FXML
    private TableColumn<Car, String> colFuelType;

    @FXML
    private TableColumn<Car, String> colHorsepower;

    @FXML
    private TableColumn<Car, String> colKmPerLiter;

    @FXML
    private TableColumn<Car, String> colMileage;

    @FXML
    private TableColumn<Car, String> colModel;

    @FXML
    private TableColumn<Car, String> colPrice;

    @FXML
    private TableColumn<Car, String> colSeats;

    @FXML
    private TableColumn<Car, String> colTransmission;

    @FXML
    private TableColumn<Car, String> colYear;

    @FXML
    private BorderPane mainView;

    @FXML
    private TableView<Car> tViewCars;
    
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
    }
    
    private void loadCars() {
    	ArrayList<Car> cars = carService.getAll();
    	carsList.addAll(cars);
    	tViewCars.setItems(carsList);
    }
    
    private void initTableColumns() {
    	colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
		colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
		colMileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));
		colTransmission.setCellValueFactory(new PropertyValueFactory<>("transmission"));
		colFuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
		colEngineSize.setCellValueFactory(new PropertyValueFactory<>("engineSize"));
		colHorsepower.setCellValueFactory(new PropertyValueFactory<>("horsepower"));
		colSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
		colDoors.setCellValueFactory(new PropertyValueFactory<>("doors"));
		colKmPerLiter.setCellValueFactory(new PropertyValueFactory<>("kmPerLiter"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
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
		initTableColumns();
		try {
			loadModels();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		loadCars();
	}

}
