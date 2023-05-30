package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

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
import javafx.scene.input.KeyEvent;
import presentation.ValidatableController;
import presentation.car.AvailableCarsController;
import presentation.customer.NewCustomerController;
import services.BankService;
import services.CustomerService;
import services.LoanApplicationService;
import services.RKIService;

/**
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *       
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 */
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
	private double bankInterestRate = 0;
	private double totalInterestRate;
	private double monthlyPayment;
	
	
	private Tab[] tabs = new Tab[4];
	private int currentTabIndex = 0;

	@FXML
	void handleBtnCheckRKIClick(ActionEvent event) throws ValidationException {
		
		validate("cpr");
		
		if (!validator.passes()) {
			flashErrorMessage(validator.getErrorMessagesAsString(), "Validation");
			return;
		}

		sendRKIRequest(); // To check customer's CPR.
		
		selectedCustomer = (new CustomerService()).findByCPR(inputCpr.getText());
		fillCustomerInfo();
		/**
		 * If customer not found then open window for adding new customer
		 * with the specified CPR number.
		 */
		if(selectedCustomer == null) {
			NewCustomerController controller = (NewCustomerController)openWindowAndGetController("customer/NewCustomer.fxml", "New Customer");
			controller.addObserver(this);
			controller.setCpr(inputCpr.getText());
		}
	}
	
	@FXML
    void handleInuputCPRAction(ActionEvent event) {
		btnCheckRKI.fire();
    }

	@FXML
	void handleBtnSearchCarClick(ActionEvent event) {
		AvailableCarsController controller = (AvailableCarsController)openWindowAndGetController("car/AvailableCars.fxml", "Cars");
		controller.addObserver(this);
	}
	
	@FXML
    void handleBtnBankFetchClick(ActionEvent event) {
		sendBankRequest();
    }
	
	@FXML
	void handleInputMonthsKeyReleased(KeyEvent event) {
		
		int months;
		
		if(inputMonths.getText().isEmpty())
			months = 0;
		else
			months = Integer.parseInt(inputMonths.getText());
		
		loanApplication.setMonths(months);
		
		calculate();
    }
	
	@FXML
    void handleInputDownPaymentKeyReleased(KeyEvent event) {
		
		int downPayment = 0;
		
		if(inputDownPayment.getText().isEmpty())
			downPayment = 0;
		else
			downPayment = Integer.parseInt(inputDownPayment.getText());
		
		if(downPayment > selectedCar.getPrice()) {
			showErrorMessage("Down payment can not be more than total price.", "Error");
			return;
		}
		
		loanApplication.setPayment(downPayment);
		
		
		loanApplication.setLoanAmount(selectedCar.getPrice() - downPayment);
		
		lbLoanAmount.setText(formatCurrency(loanApplication.getLoanAmount()));
		
		calculate();
    }
	
	@FXML
	void handleBtnNextClick(ActionEvent event) {
		
		boolean isForwardAllowed = false;
		
		switch (currentTabIndex) {
		case 0: {
			if(!(isForwardAllowed = validateCarStep()))
				flashErrorMessage("Please select a car.", "Uncompleted");
			
			break;
		}
		case 1: {
			if(!(isForwardAllowed = validateCustomerStep()))
				flashErrorMessage("Please Select a customer.", "Uncompleted");

			break;
		}
		case 2: {
			if(!(isForwardAllowed = validateLoanStep()))
				flashErrorMessage("Please Complete loan step.", "Uncompleted");
			
			break;
		}
		case 3: {
			if(!(isForwardAllowed = validateNoteStep()))
				flashErrorMessage("Please Complete Note step.", "Uncompleted");
			
			break;
		}
		}
		
		if(isForwardAllowed)
			goToNextStep();
	}

	@FXML
	void handleBtnAddClick(ActionEvent event) {
		
		if(!validateAllSteps()) {
			flashErrorMessage("Please make sure to complete all steps.", "Uncompleted");
			return;
		}
		
		fillLoanApplicationWithData();
		
		LoanApplication loan = loanAppService.create(loanApplication);
		
		if(loan == null) {
			showErrorMessage("Failed to create new loan application", "Creating Failed");
			return;
		}
		else {
			flashSuccessMessage("Loan application is created successfuly", "Success");
			fire();
			btnCancel.fire();
		}
	}

	/**
	 * Send request to the RKI API to check the credit rate of the sent CPR number.
	 * 
	 * Response will be sent to this observer controller.
	 */
	private void sendRKIRequest() {
		RKIService rkiService = new RKIService(this);
		rkiService.sendRequest(inputCpr.getText());
	}

	/**
	 * Send request to the Bank API to get interest rate for today.
	 * 
	 * Response will be sent to this observer controller.
	 */
	private void sendBankRequest() {
		BankService bankService = new BankService(this);
		bankService.sendRequest();
	}

	private boolean validateAllSteps() {
		return validateCarStep()
			 &&validateCustomerStep()
			 &&validateLoanStep()
			 &&validateNoteStep();
	}

	private boolean validateCarStep() {
		return selectedCar != null;
	}
	
	private boolean validateCustomerStep() {
		
		return selectedCustomer != null;
	}
	
	private boolean validateLoanStep() {
		if(bankInterestRate == 0 
				|| inputDownPayment.getText().isEmpty()
				|| lbLoanAmount.getText().isEmpty()
				|| inputMonths.getText().isEmpty()
				|| lbInterestRate.getText().isEmpty()
				|| lbMonthlyPayment.getText().isEmpty()
		) {
			return false;
		}
		return true;
	}

	private boolean validateNoteStep() {
		
		btnNext.setDisable(true);
		return true;
	}
	
	private void goToNextStep() {
		if(currentTabIndex >2) {
			btnNext.setDisable(true);
			return;
		}
		currentTabIndex++;
		tabs[currentTabIndex].setDisable(false);
		tabPane.getSelectionModel().select(currentTabIndex);
	}

	private void calculate() {
		calculateTotalInterestRate();
		calculateMonthlyPayment();
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

	private void handleRKIResponse(RKIService service) {
		rkiRate = service.getRate();
		Platform.runLater(() -> setRate(rkiRate));
		
		if(rkiRate.equals(RKIService.D_RKI_RATE))
			disableAll();
		else
			enableAll();
	}
	
	private void handleBankResponse(BankService service) {
		bankInterestRate = service.getInterestRate();
		Platform.runLater(() -> setBankInterestRate(bankInterestRate));
	}
	
	private void handleAvailableCarsControllerResponse(AvailableCarsController controller) {
		selectedCar = controller.getSelectedCar();
		fillCarInformation();
	}
	
	private void handleNewCustomerControllerResponse(NewCustomerController controller) {
		selectedCustomer = controller.getCustomer();
		fillCustomerInfo();
	}
	
	private void fillLoanApplicationWithData() {
		
		loanApplication.setCarID(selectedCar.getId());
		loanApplication.setCustomerID(selectedCustomer.getId());
		loanApplication.setSellerID(getAuthenticatedUser().getEmployee().getId());
		loanApplication.setLoanAmount(loanApplication.getLoanAmount());
		loanApplication.setPayment(loanApplication.getPayment());
		loanApplication.setMonths(loanApplication.getMonths());
		loanApplication.setInterestRate(totalInterestRate);
		loanApplication.setMonthlyPayment(monthlyPayment);
		loanApplication.setStatus(LoanApplication.PROCESSING);
		loanApplication.setNote(textAreaNote.getText());
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
	}
	
	private void fillCustomerInfo() {
		
		if(selectedCustomer == null) {
			lbAddress.setText(null);
			lbFirstName.setText(null);
			lbLastName.setText(null);
			lbEmail.setText(null);
			lbPhone.setText(null);
			lbZipCode.setText(null);
			lbCity.setText(null);
			
			return;
		}
		
		lbAddress.setText(selectedCustomer.getAddress());
		lbFirstName.setText(selectedCustomer.getFirstName());
		lbLastName.setText(selectedCustomer.getLastName());
		lbEmail.setText(selectedCustomer.getEmail());
		lbPhone.setText(selectedCustomer.getPhone());
		lbZipCode.setText(selectedCustomer.getZipCode());
		lbCity.setText(selectedCustomer.getCity());
		
	}

	/**
	 * This method is invoked when the RKI calls the update method of this controller.
	 */
	public void setRate(String rate) {
		lbRate.setText(rate);
		styleRKILabelRate(rate);
		calculate();
	}

	public LoanApplication getCreatedLoanApplication() {
		return loanApplication;
	}

	@Override
	public void update(Observable o, Object arg) {
	
		if (o instanceof RKIService) {
			handleRKIResponse((RKIService)o);
		} else if (o instanceof BankService) {
			handleBankResponse((BankService)o);
		} else if (o instanceof AvailableCarsController) {
			handleAvailableCarsControllerResponse((AvailableCarsController)o);
		} else if (o instanceof NewCustomerController) {
			handleNewCustomerControllerResponse((NewCustomerController)o);
		}
	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rkiRate = "D";
		tabs[0] = tabCar;
		tabs[1] = tabCustomer;
		tabs[2] = tabLoan;
		tabs[3] = tabNote;
		
		inputMonths.setTextFormatter(allowOnlyDigits());
		inputDownPayment.setTextFormatter(allowOnlyDigits());
		
		tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
		    if (newTab == tabCar) {
		        currentTabIndex = 0;
		    } else if (newTab == tabCustomer) {
		        currentTabIndex = 1;
		    } else if (newTab == tabLoan) {
		    	currentTabIndex = 2;
		    } else if (newTab == tabNote) {
		    	currentTabIndex = 3;
		    }
		});
	}

	private void setBankInterestRate(double bankInterestRate) {
		lbBankInterestRate.setText(formatNumber(bankInterestRate));
		calculate();
	}

	private void disableAll() {
		btnAdd.setDisable(true);
		btnNext.setDisable(true);
	}

	private void enableAll() {
		btnAdd.setDisable(false);
		btnNext.setDisable(false);
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
			showErrorMessage("This customer is not allowed for loan", "Not Allowed");
			disableAll();
			break;
		}
		}

		lbRate.getStyleClass().add("border-color-white");
		lbRate.getStyleClass().add("round-50");
		lbRate.getStyleClass().add("shadow-2");
	}

}
