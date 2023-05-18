package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Car;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Rules;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import presentation.ValidatableController;
import presentation.car.CarsController;
import services.BankService;
import services.RKIService;
import window.Window;

public class NewLoanApplicationController extends ValidatableController {

	@FXML
	private Button btnCheckRKI;

	@FXML
	private Button btnSearchCar;

	@FXML
	private Label lbRate;

	@FXML
	private Tab tabCar;

	@FXML
	private Tab tabLoan;

	@FXML
	private Tab tabPerson;

	@FXML
	private Tab tabNote;

	private Car selectedCar;

	@FXML
	@Rules(field = "cpr", rules = "required|numeric|length:10")
	private TextField inputCpr;

	@FXML
	void handleBtnCheckRKIClick(ActionEvent event) {
		try {
			validate("cpr");
			if (!validator.passes()) {
				flashErrorMessage(validator.getErrorMessagesAsString(), "Validation");
				return;
			}
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		RKIService rkiService = new RKIService(inputCpr.getText(), this);
		rkiService.sendRequest();
	}

	@FXML
	void handleBtnSearchCarClick(ActionEvent event) {
		Window carsWindow = new Window("car/Cars.fxml", "Cars");
		CarsController controller = (CarsController) carsWindow.getController();
		controller.addObserver(this);
		carsWindow.show();
	}

	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof RKIService) {
			String rate = ((RKIService) o).getRate();
			Platform.runLater(() -> setRate(rate));
		} else if (o instanceof BankService) {
			double interestRate = ((BankService) o).getInterestRate();
			System.out.println(interestRate);
		} else if (o instanceof CarsController) {
			selectedCar = ((CarsController) o).getSelectedCar();
			fillCarInformation();
		}

	}

	private void fillCarInformation() {
		System.out.println("Selected Car is : " + selectedCar.getModel());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setRate(String rate) {
		lbRate.setText(rate);
		switch (rate) {
		case "A": {
			lbRate.setStyle("-fx-background-color: #059669;");
			lbRate.setStyle("-fx-text-fill: #ffffff;");
			tabCar.setDisable(false);
			break;
		}
		case "B": {
			lbRate.setStyle("-fx-background-color: #bef264;");
			lbRate.setStyle("-fx-text-fill: #000000;");
			tabCar.setDisable(false);
			break;
		}
		case "C": {
			lbRate.setStyle("-fx-background-color: #f59e0b;");
			lbRate.setStyle("-fx-text-fill: #000000;");
			tabCar.setDisable(false);
			break;
		}
		case "D": {
			lbRate.setStyle("-fx-background-color: #dc2626;");
			lbRate.setStyle("-fx-text-fill: #ffffff;");
			tabCar.setDisable(true);
			break;
		}
		}
	}

}
