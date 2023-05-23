package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import app.App;
import app.FormData;
import database.entities.Car;
import database.entities.Customer;
import database.entities.LoanApplication;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Rules;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import presentation.ValidatableController;
import presentation.car.FreeCarsController;
import presentation.customer.NewCustomerController;
import services.BankService;
import services.CustomerService;
import services.LoanApplicationService;
import services.RKIService;

public class NewLoanApplicationController extends ValidatableController {

	@FXML
    private TabPane tabPane;
	
	@FXML
	private Button btnCheckRKI;

	@FXML
	private Label lbRate;

	@FXML
	private Tab tabCar;

	@FXML
	private Tab tabLoan;

	@FXML
	private Tab tabCustomer;

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
	private Button btnSearchCar;
	
	@FXML
	private Label lbVIN;
	
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
    //=============== Loan information =====================    
    @FXML
    private Button btnBankFetch;
    
    @FXML
    @Rules(field = "down payment", rules = "required|numeric")
    private TextField inputDownPayment;

    @FXML
    @Rules(field = "months", rules = "required|numeric|gt:0")
    private TextField inputMonths;
    
    @FXML
    private Label lbBankInterestRate;
    
    @FXML
    private Label lbCarPrice;
    
    @FXML
    private Label lbInterestRate;
    
    @FXML
    private Label lbLoanAmount;
    
    @FXML
    private Label lbMonthlyPayment;
    
    @FXML
    private TextArea textAreaNote;
    
    private LoanApplication loanApplication = new LoanApplication();
    
    //==================================================
    
    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnNext;

	private Car selectedCar;
	private Customer selectedCustomer;

	private LoanApplicationService loanAppService = new LoanApplicationService();
	private String rkiRate;
	private double bankInterestRate;
	private double totalInterestRate;
	private double monthlyPayment;
	
	
	private Tab[] tabs = new Tab[4];
	private int currentTabIndex = 0;
	private boolean[] completedSteps = new boolean[4];

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

		sendRKIRequest();
		
		CustomerService service = new CustomerService();
		
		selectedCustomer = service.findByCPR(inputCpr.getText());
		
		if(selectedCustomer == null) {
			NewCustomerController controller = (NewCustomerController)openWindowAndGetController("customer/NewCustomer.fxml", "Customer");
			controller.addObserver(this);
		}else
			fillCustomerInfo();
	}

	/**
	 * Send request to the RKI API to check to credit rate the sent cpr number.
	 * 
	 * Response will be sent to this observer controller.
	 */
	private void sendRKIRequest() {
		RKIService rkiService = new RKIService(this);
		rkiService.sendRequest(inputCpr.getText());
	}
	
	/**
	 * Send request to the Bank API to check to get interest rate for today.
	 * 
	 * Response will be sent to this observer controller.
	 */
	private void sendBankRequest() {
		BankService bankService = new BankService(this);
		bankService.sendRequest();
	}

	@FXML
	void handleBtnSearchCarClick(ActionEvent event) {
		FreeCarsController controller = (FreeCarsController)openWindowAndGetController("car/FreeCars.fxml", "Cars");
		controller.addObserver(this);
	}
	
	@FXML
    void handleBtnBankFetchClick(ActionEvent event) {
		sendBankRequest();
    }
	
	@FXML
    void handleInputMonthsOnAction(ActionEvent event) throws ValidationException {

		loanApplication.setMonths(Integer.parseInt(inputMonths.getText()));
		calculateTotalInterestRate();
		calculateMonthlyPayment();
    }
	
	@FXML
    void handleInputDownPaymentAction(ActionEvent event) throws ValidationException {
		
		int downPayment = Integer.parseInt(inputDownPayment.getText());
		
		if(downPayment >= selectedCar.getPrice()) {
			showErrorMessage("Please check the down payment.", "Error");
			return;
		}
		
		loanApplication.setPayment(downPayment);
		
		
		loanApplication.setLoanAmount(selectedCar.getPrice() - downPayment);
		
		lbLoanAmount.setText(formatCurrency(loanApplication.getLoanAmount()));
		
		calculateTotalInterestRate();
		calculateMonthlyPayment();
    }
	
	@FXML
	void handleBtnNextClick(ActionEvent event) {
		if(currentTabIndex >= 3)
			return;
		
		if(currentTabIndex == 1)
			if(!completedSteps[1] || rkiRate.equals("D")) {
				flashErrorMessage("Please complet this step. D can not get loan", "Loan Error");
				return;
			}
		
		if(currentTabIndex == 2)
			if(checkLoanStep())
				completedSteps[currentTabIndex] = true;
		if(currentTabIndex == 3)
			completedSteps[currentTabIndex] = true;
		
		if(completedSteps[currentTabIndex])
			openTab(++currentTabIndex);
		else
			flashErrorMessage("Please complet this step to go the next step", "Uncompleted Step");
	}

	@FXML
	void handleBtnAddClick(ActionEvent event) {
		
		if(!isCompleted()) {
			flashErrorMessage("Please Complet all steps.", "Uncompleted");
			return;
		}
		
		FormData data = new FormData();
		data.setData("carID", selectedCar.getId());
		data.setData("customerID", selectedCustomer.getId());
		data.setData("sellerID", App.getAuthenticatedUser().getEmployee().getId());
		data.setData("loanAmount", loanApplication.getLoanAmount());
		data.setData("payment", loanApplication.getPayment());
		data.setData("months", loanApplication.getMonths());
		data.setData("interestRate", totalInterestRate);
		data.setData("monthlyPayment", monthlyPayment);
		data.setData("status", LoanApplication.PROCESSING);
		data.setData("note", textAreaNote.getText());

		LoanApplication loanApp = loanAppService.create(data);
		if(loanApp == null) {
			showErrorMessage("Failed to create new loan application", "Creating Failed");
			return;
		}
		else
			flashSuccessMessage("Loan application is created successfuly", "Success");
	}

	private boolean isCompleted() {
		for(int i=0; i<4; i++)
			if(completedSteps[i] == false)
				return false;
		
		return true;
	}

	private boolean checkLoanStep() {
		return (
				lbBankInterestRate.getText().isBlank()|
				inputDownPayment.getText().isBlank()|
				lbLoanAmount.getText().isBlank()|
				inputMonths.getText().isBlank()|
				lbInterestRate.getText().isBlank()|
				lbMonthlyPayment.getText().isBlank()
		   ) ? false : true;
	}
	
	private void calculateTotalInterestRate() {
		
		totalInterestRate = loanAppService.getCalculatedInterestRate(
				rkiRate, 
				bankInterestRate, 
				loanApplication.getMonths(), 
				selectedCar.getPrice(), 
				loanApplication.getPayment());
		
		lbInterestRate.setText(formatNumber(totalInterestRate));
	}
	
	private void calculateMonthlyPayment() {
		monthlyPayment = loanAppService.getCalculatedMothlyPayment(
				loanApplication.getLoanAmount(),
				loanApplication.getMonths(),
				totalInterestRate
				);
		
		lbMonthlyPayment.setText(formatCurrency(monthlyPayment));
	}
	
	private void openTab(int currentTabIndex) {
		tabs[currentTabIndex].setDisable(false);
		tabPane.getSelectionModel().select(currentTabIndex);
	}

	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof RKIService) {

			rkiRate = ((RKIService) o).getRate();
			Platform.runLater(() -> setRate(rkiRate));

		} else if (o instanceof BankService) {

			bankInterestRate = ((BankService) o).getInterestRate();
			Platform.runLater(() -> setBankInterestRate(bankInterestRate));

		} else if (o instanceof FreeCarsController) {

			selectedCar = ((FreeCarsController) o).getSelectedCar();
			fillCarInformation();
		} else if (o instanceof NewCustomerController) {

			selectedCustomer = ((NewCustomerController) o).getCreatedCustomer();
			fillCustomerInfo();
		}

	}

	private void fillCarInformation() {
		lbVIN.setText(selectedCar.getVin());
		lbModel.setText(selectedCar.getModel());
		lbYear.setText(Integer.toString(selectedCar.getYear()));
		lbHorsepower.setText(Integer.toString(selectedCar.getHorsepower()));
		lbEngineSize.setText(formatNumber(selectedCar.getEngineSize()));
		lbTransmission.setText(selectedCar.getTransmission());
		lbFuelType.setText(selectedCar.getFuelType());
		lbMileage.setText(Integer.toString(selectedCar.getMileage()));
		lbKm.setText(Double.toString(selectedCar.getKmPerLiter()));
		lbSeats.setText(Integer.toString(selectedCar.getSeats()));
		lbDoors.setText(Integer.toString(selectedCar.getDoors()));
		lbColor.setText(selectedCar.getColor());
		lbPrice.setText(formatCurrency(selectedCar.getPrice()));
		
		lbCarPrice.setText(formatCurrency(selectedCar.getPrice()));
		
		completedSteps[0] = true;
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
		rkiRate = "D";
		tabs[0] = tabCar;
		tabs[1] = tabCustomer;
		tabs[2] = tabLoan;
		tabs[3] = tabNote;
		
		for(int i=0; i<4; i++)
			completedSteps[i] = false;
		completedSteps[3] = true;
		
		inputMonths.setTextFormatter(allowOnlyDigits());
		inputDownPayment.setTextFormatter(allowOnlyDigits());
	}

	public void setRate(String rate) {
		lbRate.setText(rate);
		styleRKILabelRate(rate);
		calculateTotalInterestRate();
		completedSteps[1] = true;
	}

	private void setBankInterestRate(double bankInterestRate) {
		lbBankInterestRate.setText(formatNumber(bankInterestRate));
		calculateTotalInterestRate();
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
