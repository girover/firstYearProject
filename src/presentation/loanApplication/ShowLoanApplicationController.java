package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import app.App;
import database.entities.Car;
import database.entities.Customer;
import database.entities.Employee;
import database.entities.LoanApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import presentation.BaseController;
import services.LoanApplicationService;

public class ShowLoanApplicationController extends BaseController {

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbCPR;

    @FXML
    private Label lbCity;

    @FXML
    private Label lbColor;

    @FXML
    private Label lbDoors;

    @FXML
    private Label lbDownPayment;

    @FXML
    private Label lbEmail;

    @FXML
    private Label lbEngineSize;

    @FXML
    private Label lbFirstName;

    @FXML
    private Label lbFuelType;

    @FXML
    private Label lbHorsepower;

    @FXML
    private Label lbKm;

    @FXML
    private Label lbLastName;

    @FXML
    private Label lbLoanAmount;

    @FXML
    private Label lbMileage;

    @FXML
    private Label lbModel;

    @FXML
    private Label lbMonthlyPayment;

    @FXML
    private Label lbMonths;

    @FXML
    private Label lbPhone;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbRate;

    @FXML
    private Label lbSeats;

    @FXML
    private Label lbSellerName;

    @FXML
    private Label lbTotalInterestRate;

    @FXML
    private Label lbTransmission;

    @FXML
    private Label lbVIN;

    @FXML
    private Label lbYear;
    
    @FXML
    private Label lbStatus;

    @FXML
    private Label lbZipCode;
    
    @FXML
    private Pane paneStatus;
    
    @FXML
    private Button btnApprove;
    
    @FXML
    private Button btnReject;

    @FXML
    private TextArea textAreaNote;

	private LoanApplication loanApplication;
	
	LoanApplicationService service = new LoanApplicationService();

	@FXML
	void handleBtnApproveClick(ActionEvent event) {
		
		if(loanApplication.getStatus().equals(LoanApplication.APPROVED)) {
			flashErrorMessage("The Loan application is already approved.", "Info");
			return;
		}
		
		if(!showCinformDialog("Approving", "Are you sure for approving the loan application?"))
			return;
		
		if(service.approveLoanApplication(loanApplication)) {
			flashSuccessMessage("Loan Application approved successfully.", "Approved");
			updateData();
		}
		else
			flashErrorMessage("Failed to approve loan application.", "Failed");
	}
	
	@FXML
	void handleBtnRejectClick(ActionEvent event) {
		
		if(loanApplication.getStatus().equals(LoanApplication.REJECTED)) {
			flashErrorMessage("The Loan application is already rejected.", "Info");
			return;
		}
		
		if(!showCinformDialog("Rejecting", "Are you sure for rejecting the loan application?"))
			return;
		
		if(service.rejectLoanApplication(loanApplication)) {
			flashSuccessMessage("Loan Application rejected successfully.", "Rejected");
			updateData();
		}
		else
			flashErrorMessage("Failed to reject loan application.", "Failed");
	}
	
    public void setLoanApplication(LoanApplication loanApplication) {
    	this.loanApplication = loanApplication;
    	updateData();
    	checkPermissions();
    }
    
	private void checkPermissions() {
		if(!service.checkSellerApprovalLimit(App.getAuthenticatedUser().getEmployee(), loanApplication.getLoanAmount())) {
			btnApprove.setDisable(true);
			btnReject.setDisable(true);
		}
	}

	private void updateData() {
		Customer customer = service.getCustomer(loanApplication);
		Employee seller = service.getSeller(loanApplication);
		Car car = service.getCar(loanApplication);
		
		fillCustomerData(customer);
		fillSellerData(seller);
		fillCarData(car);
		fillLoanData();
		fillStatus(loanApplication.getStatus());
		textAreaNote.setText(loanApplication.getNote());
	}
	 
	private void fillStatus(String status) {

		switch (status) {
		case "processing": {
			styleProcessingStatus();
			break;
		}
		case "approved": {
			styleApprovedStatus();
			break;
		}
		case "rejected": {
			styleRejectedStatus();
			break;
		}
		}
	}

	private void styleRejectedStatus() {
		paneStatus.getStyleClass().clear();
		paneStatus.getStyleClass().add("bg-red-500");
		paneStatus.getStyleClass().add("round-50");
		paneStatus.getStyleClass().add("shadow");
		paneStatus.getStyleClass().add("text-white");
		lbStatus.setText(LoanApplication.REJECTED);
	}

	private void styleApprovedStatus() {
		paneStatus.getStyleClass().clear();
		paneStatus.getStyleClass().add("bg-green-500");
		paneStatus.getStyleClass().add("round-50");
		paneStatus.getStyleClass().add("shadow");
		paneStatus.getStyleClass().add("text-white");
		lbStatus.setText(LoanApplication.APPROVED);
	}

	private void styleProcessingStatus() {
		paneStatus.getStyleClass().clear();
		paneStatus.getStyleClass().add("bg-yellow-500");
		paneStatus.getStyleClass().add("round-50");
		paneStatus.getStyleClass().add("shadow");
		paneStatus.getStyleClass().add("text-white");
		lbStatus.setText(LoanApplication.PROCESSING);
	}

	private void fillLoanData() {
		lbLoanAmount.setText(formatCurrency(loanApplication.getLoanAmount()));
		lbDownPayment.setText(formatCurrency(loanApplication.getPayment()));
		lbMonths.setText(Integer.toString(loanApplication.getMonths()));
		lbTotalInterestRate.setText(formatNumber(loanApplication.getInterestRate()));
		lbMonthlyPayment.setText(formatCurrency(loanApplication.getMonthlyPayment()));
	}

	private void fillCarData(Car car) {
		lbVIN.setText(car.getVin());
		lbModel.setText(car.getModel());
		lbYear.setText(Integer.toString(car.getYear()));
		lbHorsepower.setText(Integer.toString(car.getHorsepower()));
		lbEngineSize.setText(formatNumber(car.getEngineSize()));
		lbTransmission.setText(car.getTransmission());
		lbFuelType.setText(car.getFuelType());
		lbMileage.setText(Integer.toString(car.getMileage()));
		lbKm.setText(Double.toString(car.getKmPerLiter()));
		lbSeats.setText(Integer.toString(car.getSeats()));
		lbDoors.setText(Integer.toString(car.getDoors()));
		lbColor.setText(car.getColor());
		lbPrice.setText(formatCurrency(car.getPrice()));
	}

	private void fillSellerData(Employee seller) {
		lbSellerName.setText(seller.getFirstName() + " " + seller.getLastName());
	}

	private void fillCustomerData(Customer customer) {
		lbAddress.setText(customer.getAddress());
		lbCity.setText(customer.getCity());
		lbCPR.setText(customer.getCPRHash());
		lbEmail.setText(customer.getEmail());
		lbFirstName.setText(customer.getFirstName());
		lbLastName.setText(customer.getLastName());
		lbPhone.setText(customer.getPhone());
		lbZipCode.setText(customer.getZipCode());
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}