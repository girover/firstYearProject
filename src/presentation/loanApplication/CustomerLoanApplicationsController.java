package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Customer;
import database.entities.LoanApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import presentation.BaseController;
import presentation.TableCellFormatter;
import services.LoanApplicationService;

/**
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *       
 *        @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class CustomerLoanApplicationsController extends BaseController {

    
    @FXML
    private Button btnShow;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEditLoanApplication;
    
    @FXML Label lbCustomerName;

    @FXML
    private TableColumn<LoanApplication, String> colCarID;

    @FXML
    private TableColumn<LoanApplication, String> colCustomerID;

    @FXML
    private TableColumn<LoanApplication, String> colEmployeeID;

    @FXML
    private TableColumn<LoanApplication, String> colID;

    @FXML
    private TableColumn<LoanApplication, String> colLoanAmount;
    
    @FXML
    private TableColumn<LoanApplication, String> colDownPayment;

    @FXML
    private TableColumn<LoanApplication, String> colMonths;

    @FXML
    private TableColumn<LoanApplication, String> colRate;
    
    @FXML
    private TableColumn<LoanApplication, String> colMonthlyPayment;
    
    @FXML
    private TableColumn<LoanApplication, String> colDate;

    @FXML
    private TableColumn<LoanApplication, String> colStatus;

    @FXML
    private TableView<LoanApplication> tvLoanApplications;
    
    private ObservableList<LoanApplication> loanApplications = FXCollections.observableArrayList();

    private Customer customer;


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		lbCustomerName.setText(customer.getFirstName()+" "+customer.getLastName());
		loadData();
	}

    @FXML
    void handleBtnDeleteClick(ActionEvent event) {

    }

    @FXML
    void handleBtnEditLoanApplicationClick(ActionEvent event) {
    	
    	LoanApplication selectedLoanApplication = tvLoanApplications.getSelectionModel().getSelectedItem();
    	
    	if(selectedLoanApplication == null) {
    		flashErrorMessage("Please choose a loan application to edit.", "Unselected");
    		return;
    	}
    	
    	UpdateLoanApplicationController controller = (UpdateLoanApplicationController)openWindowAndGetController("loanApplication/UpdateLoanApplication.fxml", "Update Loan Application");
    	controller.addObserver(this);
    	controller.setLoanApplication(selectedLoanApplication);
    }
    
    @FXML
    void handleBtnShowClick(ActionEvent event) {
    	LoanApplication loan = tvLoanApplications.getSelectionModel().getSelectedItem();
    	if(loan == null) {
    		flashErrorMessage("No loan application is selected", "Select Item");
    		return;
    	}
    	
    	ShowLoanApplicationController controller = (ShowLoanApplicationController)openWindowAndGetController("loanApplication/ShowLoanApplication.fxml", "Loan Application");
    	controller.setLoanApplication(loan);
    	controller.addObserver(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof ShowLoanApplicationController || o instanceof UpdateLoanApplicationController) {
			tvLoanApplications.refresh();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableViewColumns();
		initTableView();
	}

	private void initTableView() {
		tvLoanApplications.setItems(loanApplications);
	}

	private void loadData() {
		
		LoanApplicationService service = new LoanApplicationService();
		loanApplications.clear();
		loanApplications.addAll(service.getCustomerLoanApplications(customer));
	}

	private void initTableViewColumns() {
		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		colCarID.setCellValueFactory(new PropertyValueFactory<>("carID"));
		colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("sellerID"));
		colLoanAmount.setCellFactory(tableColumn -> TableCellFormatter.getTableCellFormatterForLoanAmount(tableColumn));
		colDownPayment.setCellFactory(tableColumn -> TableCellFormatter.getTableCellFormatterForPayment(tableColumn) );
		colMonths.setCellFactory(tableColumn -> TableCellFormatter.getTableCellFormatterForMonths(tableColumn));
		colDate.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
		colRate.setCellFactory(tableColumn -> TableCellFormatter.getTableCellFormatterForRate(tableColumn));
		colMonthlyPayment.setCellFactory(tableColumn -> TableCellFormatter.getTableCellFormatterForMonthlyPayment(tableColumn));

		colStatus.setCellFactory(tableColumn -> TableCellFormatter.getTableCellFormatterForStatus(tableColumn));
	}
}
