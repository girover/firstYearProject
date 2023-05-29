package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.LoanApplication;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import presentation.ValidatableController;
import services.LoanApplicationService;

/**
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class UpdateLoanApplicationController extends ValidatableController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdate;

    @FXML
    @Rules(field = "down payment", rules = "required|numeric")
    private TextField inputDownPayment;

    @FXML
    @Rules(field = "months", rules = "required|numeric")
    private TextField inputMonths;

    @FXML
    private Label lbCarPrice;

    @FXML
    private Label lbInterestRate;

    @FXML
    private Label lbLoanAmount;

    @FXML
    private Label lbMonthlyPayment;

    @FXML
    private Tab tabCar;

    @FXML
    private Tab tabCustomer;

    @FXML
    private Tab tabLoan;

    @FXML
    private Tab tabNote;

    @FXML
    private TabPane tabPane;
    
    private LoanApplicationService service = new LoanApplicationService();
    
    private LoanApplication loanApplication;
    
    private double totalPrice;
    private double originalTotalInterestRate;
    private int originalAdditionalRateForMonthes;
    private int originalAdditionalRateForPayment;
    
    private int payment;
    private int months;
    private int loanAmount;
    private double monthlyPayment;
    private double newTotalInterestRate;
    
    public void setLoanApplication(LoanApplication loanApplication) {
    	
    	this.loanApplication = loanApplication;
    	
    	extractLoanInfo();
    	
    	fillDataWithLoanApplication();
    }
    
    public LoanApplication getLoanApplication() {
    	return loanApplication;
    }

    private void extractLoanInfo() {
    	totalPrice = loanApplication.getPayment() + loanApplication.getLoanAmount();
    	originalTotalInterestRate = loanApplication.getInterestRate();
    	originalAdditionalRateForMonthes = service.getAddedRateForMonths(loanApplication);
    	originalAdditionalRateForPayment = service.getAddedRateForPayment(loanApplication, totalPrice);
    	payment = loanApplication.getPayment();
    	months = loanApplication.getMonths();
    	monthlyPayment = loanApplication.getMonthlyPayment();
    	loanAmount = loanApplication.getLoanAmount();
    }

	private void fillDataWithLoanApplication() {
    	lbCarPrice.setText(formatCurrency(totalPrice));
    	lbInterestRate.setText(formatNumber(loanApplication.getInterestRate()));
    	lbLoanAmount.setText(formatCurrency(loanApplication.getLoanAmount()));
    	lbMonthlyPayment.setText(formatCurrency(loanApplication.getMonthlyPayment()));
    	inputDownPayment.setText(Integer.toString((int)loanApplication.getPayment()));
    	inputMonths.setText(Integer.toString(loanApplication.getMonths()));
	}

	@FXML
    void handleBtnCancelClick(ActionEvent event) {
    	closeWindow(event);
    }

    @FXML
    void handleBtnUpdateClick(ActionEvent event) throws ValidationException {
    	validate("down payment", "months");
    	if(!validator.passes()) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "Validation Error");
    		return;
    	}
    	
    	updateLoanApplication();
    	if(service.update(loanApplication)) {
    		flashSuccessMessage("Loan Application was updated successfuly", "Success");
    		fire();
    		btnCancel.fire();
    	}else
    		flashErrorMessage("Failed to update the loan application", "Failed");
    }

    private void updateLoanApplication() {
    	loanApplication.setInterestRate(newTotalInterestRate);
    	loanApplication.setLoanAmount((int)loanAmount);
    	loanApplication.setPayment(payment);
    	loanApplication.setMonths(months);
    	loanApplication.setMonthlyPayment(monthlyPayment);
    }

	@FXML
	void handleInputMonthsOnAction(ActionEvent event) {
    	months = Integer.parseInt(inputMonths.getText());
    	recalculate();
	}

	@FXML
    void handleInputDownPaymentOnAction(ActionEvent event) throws ValidationException {
    	validate("down payment");
    	if(!validator.passes()) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "Validation Error");
    		return;
    	}
    		
    	payment = Integer.parseInt(inputDownPayment.getText());
    	
    	if(payment>totalPrice) {
    		showErrorMessage("payment cannot be more than price.", "Input Error");
    		inputDownPayment.setText("");
    		return;
    	}
    	
    	loanAmount = (int)totalPrice - payment;
    	
    	recalculate();
    }

    private void recalculate() {
    	int originalAddedRated = originalAdditionalRateForMonthes 
    							+ originalAdditionalRateForPayment;
    	
    	newTotalInterestRate = originalTotalInterestRate 
    						- originalAddedRated 
    						+ monthsRate()
    						+ paymentRate();
    	
    	monthlyPayment = service.getCalculatedMothlyPayment(loanAmount, months, newTotalInterestRate);
    	refillData();
	}

	private void refillData() {
		lbLoanAmount.setText(formatCurrency(loanAmount));
		lbMonthlyPayment.setText(formatCurrency(monthlyPayment));
		lbInterestRate.setText(formatNumber(newTotalInterestRate));
		inputDownPayment.setText(Integer.toString((int)payment));
		inputMonths.setText(Integer.toString(months));
	}

	private int paymentRate() {
		return service.getDownPaymentInterestRate(payment, totalPrice);
	}

	private double monthsRate() {
		return service.getMonthsInterestRate(months);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inputMonths.setTextFormatter(allowOnlyDigits());
	}

}
