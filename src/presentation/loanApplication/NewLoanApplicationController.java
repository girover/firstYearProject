package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Car;
import database.entities.Customer;
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
import services.CustomerService;
import services.LoanApplicationService;
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

	// ====== Customer Information ================
	@FXML
	@Rules(field = "cpr", rules = "required|numeric|length:10")
	private TextField inputCpr;

	@FXML
	private Label lbFirstName;

	@FXML
	private Label lbLastName;

	@FXML
	private Label lbPhone;

	@FXML
	private Label lbEmail;

	@FXML
	private Label lbAddress;

	@FXML
	private Label lbZipCode;

	@FXML
	private Label lbCity;

	// =============== Car Information ====================
	@FXML
	@Rules(field = "vin", rules = "required|alphaNumeric")
	private TextField inputVIN;
	
	@FXML
	private Label lbModel;
	
	@FXML
	private Label lbYear;
	
	@FXML
	private Label lbHorsepower;
	
	@FXML
	private Label lbEngineSize;
	
	@FXML
	private Label lbTransmission;
	
	@FXML
	private Label lbFuelType;
	
	@FXML
	private Label lbMileage;
	
	@FXML
	private Label lbKm;
	
	@FXML
	private Label lbSeats;
	
	@FXML
	private Label lbDoors;
	
	@FXML
	private Label lbColor;
    
    @FXML
    private Label lbPrice;
    //==================================================

	private Car selectedCar;
	private Customer selectedCustomer;

	private LoanApplicationService loanAppService = new LoanApplicationService();
	private String rkiRate;
	private double bankInterestRate;

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

		loanAppService.SendRKIRequest(inputCpr.getText(), this);
		
		CustomerService service = new CustomerService();
		
		selectedCustomer = service.findByCPR(inputCpr.getText());
		
		if(selectedCustomer == null) {
			Window customersWindow = new Window("customer/NewCustomer.fxml", "Customer");
			customersWindow.show();
		}else
			fillCustomerInfo();
//		RKIService rkiService = new RKIService(inputCpr.getText(), this);
//		rkiService.sendRequest();
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

			rkiRate = ((RKIService) o).getRate();
			Platform.runLater(() -> setRate(rkiRate));

		} else if (o instanceof BankService) {

			bankInterestRate = ((BankService) o).getInterestRate();
			System.out.println(bankInterestRate);

		} else if (o instanceof CarsController) {

			selectedCar = ((CarsController) o).getSelectedCar();
			fillCarInformation();
		}

	}

	private void fillCarInformation() {
		inputVIN.setText(selectedCar.getVin());
		lbModel.setText(selectedCar.getModel());
		lbYear.setText(Integer.toString(selectedCar.getYear()));
		lbHorsepower.setText(Integer.toString(selectedCar.getHorsepower()));
		lbEngineSize.setText(Double.toString(selectedCar.getEngineSize()));
		lbTransmission.setText(selectedCar.getTransmission());
		lbFuelType.setText(selectedCar.getFuelType());
		lbMileage.setText(Integer.toString(selectedCar.getMileage()));
		lbKm.setText(Double.toString(selectedCar.getKmPerLiter()));
		lbSeats.setText(Integer.toString(selectedCar.getSeats()));
		lbDoors.setText(Integer.toString(selectedCar.getDoors()));
		lbColor.setText(selectedCar.getColor());
		lbPrice.setText(Double.toString(selectedCar.getPrice()));
	}
	
	private void fillCustomerInfo() {
		if(selectedCustomer == null)
			return;
		
		lbAddress.setText(selectedCustomer.getAddress());
		lbFirstName.setText(selectedCustomer.getFirstName());
		lbLastName.setText(selectedCustomer.getLastName());
		lbCity.setText(selectedCustomer.getCity());
		lbEmail.setText(selectedCustomer.getEmail());
		lbPhone.setText(selectedCustomer.getPhone());
		lbZipCode.setText(selectedCustomer.getZipCode());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setRate(String rate) {
		lbRate.setText(rate);
		styleRKILabelRate(rate);
	}

	private void styleLabelForA() {
		lbRate.getStyleClass().add("bg-green-600");
		lbRate.getStyleClass().add("text-white");
	}

	private void styleLabelForB() {
		lbRate.getStyleClass().add("bg-green-300");
		lbRate.getStyleClass().add("text-gray-900");
	}

	private void styleLabelForC() {
		lbRate.getStyleClass().add("bg-yellow-500");
		lbRate.getStyleClass().add("text-white");
	}

	private void styleLabelForD() {
		lbRate.getStyleClass().add("bg-red-600");
		lbRate.getStyleClass().add("text-white");
		showErrorMessage("Må ikke få .......", "asdasd");
	}

	private void styleRKILabelRate(String rkiRate) {

		lbRate.getStyleClass().clear();

		switch (rkiRate) {
		case "A": {
			styleLabelForA();
			break;
		}
		case "B": {
			styleLabelForB();
			break;
		}
		case "C": {
			styleLabelForC();
			break;
		}
		case "D": {
			styleLabelForD();
			break;
		}
		}

		lbRate.getStyleClass().add("border-color-white");
		lbRate.getStyleClass().add("round-50");
		lbRate.getStyleClass().add("shadow-2");
	}

}
