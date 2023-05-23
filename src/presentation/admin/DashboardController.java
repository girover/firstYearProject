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
    private Button btnCars;

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
    	openWindow("car/Cars.fxml", "Cars");
    }

    @FXML
    void handleBtnCustomersClick(ActionEvent event) {
    	openWindow("customer/Customers.fxml", "Customers");
    }

    @FXML
    void handleBtnLoanApplicationsClick(ActionEvent event) {
    	openWindow("loanApplication/LoanApplications.fxml", "Loan Applications");
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
    	openWindow("profile/Profile.fxml", "Customers");

    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblUserName.setText(App.getAuthenticatedUser().getUserName());
	}

}
