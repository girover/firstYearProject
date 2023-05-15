package presentation.admin;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import presentation.BaseController;
import services.AuthService;
import window.Window;

public class DashboardController extends BaseController {

    @FXML
    private Button btnCars4;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnLoanApplications;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMessages;

    @FXML
    private Button btnMyProfile;

    @FXML
    private Label lblUserName;

    @FXML
    private BorderPane mainView;

    @FXML
    void handleBtnCarsClick(ActionEvent event) {
    	Window carsWindow = new Window("car/Cars2.fxml", "Cars");
    	carsWindow.show();
    }

    @FXML
    void handleBtnCustomersClick(ActionEvent event) {
    	Window customersWindow = new Window("customer/Customers.fxml", "Customers");
    	customersWindow.show();
    }

    @FXML
    void handleBtnLoanApplicationsClick(ActionEvent event) {

    }

    @FXML
    void handleBtnLogout(ActionEvent event) {
    	AuthService.logout();
    }

    @FXML
    void handleBtnMessagesClick(ActionEvent event) {

    }

    @FXML
    void handleBtnMyProfileClick(ActionEvent event) {

    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
