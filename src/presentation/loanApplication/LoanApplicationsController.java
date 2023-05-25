package presentation.loanApplication;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.LoanApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

public class LoanApplicationsController extends BaseController {

    @FXML
    private Button btnAll;
    
    @FXML
    private Button btnShow;

    @FXML
    private Button btnCancle;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEditLoanApplication;

    @FXML
    private Button btnNewLoanApplication;

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
    private TableColumn<LoanApplication, String> colDate;

    @FXML
    private TableColumn<LoanApplication, String> colStatus;

    @FXML
    private TextField inputSearch;

    @FXML
    private TableView<LoanApplication> tvLoanApplications;
    
    private ObservableList<LoanApplication> loanApplications = FXCollections.observableArrayList();

    @FXML
    void handleBtnAllClick(ActionEvent event) {

    }

    @FXML
    void handleBtnCancleClick(ActionEvent event) {

    }

    @FXML
    void handleBtnDeleteClick(ActionEvent event) {

    }

    @FXML
    void handleBtnEditCustomerClick(ActionEvent event) {
    	
    	LoanApplication selectedLoanApplication = tvLoanApplications.getSelectionModel().getSelectedItem();
    	
    	if(selectedLoanApplication == null) {
    		flashErrorMessage("Please choose a loan application to edit.", "Unselected");
    		return;
    	}
    	
    	UpdateLoanApplicationController controller = (UpdateLoanApplicationController)openWindowAndGetController("loanApplication/UpdateLoanApplication.fxml", "Update Loan Application");
    	controller.setLoanApplication(selectedLoanApplication);
    }

    @FXML
    void handleBtnNewLoanApplicationClick(ActionEvent event) {
    	openWindow("loanApplication/NewLoanApplication.fxml", "New Loan Application");
    }

    @FXML
    void handleInputSearchOnKeyReleased(KeyEvent event) {

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
    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableViewColumns();
		initTableView();
		loadData();
	}

	private void initTableView() {
		tvLoanApplications.setItems(loanApplications);
	}

	private void loadData() {
		
		LoanApplicationService service = new LoanApplicationService();
		loanApplications.clear();
		loanApplications.addAll(service.getAll());
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
		colRate.setCellValueFactory(new PropertyValueFactory<>("interestRate"));

		colStatus.setCellFactory(tableColumn -> TableCellFormatter.getTableCellFormatterForStatus(tableColumn));
	}

}
