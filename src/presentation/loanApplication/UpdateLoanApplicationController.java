package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Car;
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
import javafx.scene.input.KeyEvent;
import presentation.ValidatableController;
import services.CarService;
import services.LoanApplicationService;

/**
 * 
 * @author Majed Hussein Farhan 
 * 	- <b style="color:red">girover.mhf@gmail.com</b>
 *  - <a href="https://github.com/girover">Github</a>
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

	private double originalTotalInterestRate;
	private double bankAndRKIInterestRate;
	
	private Car selectedCar;

	/**
	 * This method extracts loan information including the total interest rate, 
	 * as well as the sum of the bank rate and RKI rate.
	 * These values are used to calculate the new interest rate 
	 * when changing months and down payment.
	 */
	private void extractLoanInfo() {

		selectedCar = (new CarService()).find(loanApplication.getCarID());
		originalTotalInterestRate = loanApplication.getInterestRate();
		
		int monthsRate = service.getAddedRateForMonths(loanApplication);
		int downPaymentRate = service.getAddedRateForPayment(loanApplication, selectedCar.getPrice());
		
		bankAndRKIInterestRate = originalTotalInterestRate - (monthsRate + downPaymentRate);
	}
	
	private void fillDataWithLoanApplication() {
		lbCarPrice.setText(formatCurrency(selectedCar.getPrice()));
		lbInterestRate.setText(formatNumber(loanApplication.getInterestRate()));
		lbLoanAmount.setText(formatCurrency(loanApplication.getLoanAmount()));
		lbMonthlyPayment.setText(formatCurrency(loanApplication.getMonthlyPayment()));
		inputDownPayment.setText(Integer.toString(loanApplication.getPayment()));
		inputMonths.setText(Integer.toString(loanApplication.getMonths()));
	}

	@FXML
	void handleBtnCancelClick(ActionEvent event) {
		closeWindow(event);
	}

	@FXML
	void handleBtnUpdateClick(ActionEvent event) throws ValidationException {
		
		validate("down payment", "months");
		
		if (!validator.passes()) {
			showErrorMessage(validator.getErrorMessagesAsString(), "Validation Error");
			return;
		}

		if (service.update(loanApplication)) {
			flashSuccessMessage("Loan Application was updated successfuly", "Loan Updated");
			fire();
			btnCancel.fire();
		} else
			flashErrorMessage("Failed to update the loan application", "Failed");
	}

	@FXML
	void handleInputMonthsKeyReleased(KeyEvent event) {
		
		int months;

		if (inputMonths.getText().isEmpty())
			months = 0;
		else
			months = Integer.parseInt(inputMonths.getText());

		loanApplication.setMonths(months);
		
		recalculate();
	}

	@FXML
	void handleInputDownPaymentKeyReleased(KeyEvent event) throws ValidationException {
		
		int downPayment = 0;

		if (inputDownPayment.getText().isEmpty())
			downPayment = 0;
		else
			downPayment = Integer.parseInt(inputDownPayment.getText());

		if (downPayment > selectedCar.getPrice()) {
			showErrorMessage("Down payment can not be more than total price.", "Error");
			return;
		}

		loanApplication.setLoanAmount(selectedCar.getPrice() - downPayment);
		loanApplication.setPayment(downPayment);

		recalculate();
	}

	private void recalculate() {
		
		double totalInterestRate = bankAndRKIInterestRate;
		
		totalInterestRate += service.getMonthsInterestRate(loanApplication.getMonths());
		totalInterestRate += service.getDownPaymentInterestRate(loanApplication.getPayment(), selectedCar.getPrice());
		
		loanApplication.setInterestRate(totalInterestRate);
		loanApplication.setMonthlyPayment(service.getCalculatedMothlyPayment(loanApplication.getLoanAmount(), loanApplication.getMonths(), loanApplication.getInterestRate()));
		
		refillData();
	}

	private void refillData() {
		lbLoanAmount.setText(formatCurrency(loanApplication.getLoanAmount()));
		lbMonthlyPayment.setText(formatCurrency(loanApplication.getMonthlyPayment()));
		lbInterestRate.setText(formatNumber(loanApplication.getInterestRate()));
	}

	public void setLoanApplication(LoanApplication loanApplication) {
	
		this.loanApplication = loanApplication;
	
		extractLoanInfo();
	
		fillDataWithLoanApplication();
	}

	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inputMonths.setTextFormatter(allowOnlyDigits());
		inputDownPayment.setTextFormatter(allowOnlyDigits());
	}

}
