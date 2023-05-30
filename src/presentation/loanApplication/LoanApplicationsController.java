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
    void handleBtnEditClick(ActionEvent event) {
    	
    	LoanApplication selectedLoanApplication = tvLoanApplications.getSelectionModel().getSelectedItem();
    	
    	if(selectedLoanApplication == null) {
    		flashErrorMessage("Please choose a loan application to edit.", "Unselected");
    		return;
    	}
    	
    	UpdateLoanApplicationController controller = (UpdateLoanApplicationController)openWindowAndGetController("loanApplication/UpdateLoanApplication.fxml", "Update Loan Application");
    	controller.setLoanApplication(selectedLoanApplication);
    	controller.addObserver(this);
    }

    @FXML
    void handleBtnNewLoanApplicationClick(ActionEvent event) {
    	NewLoanApplicationController controller = (NewLoanApplicationController) openWindowAndGetController("loanApplication/NewLoanApplication.fxml", "New Loan Application");
    	controller.addObserver(this);
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
    	controller.addObserver(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof NewLoanApplicationController) {
			LoanApplication loanApplication = ((NewLoanApplicationController)o).getCreatedLoanApplication();
			if(loanApplication != null) {
				loanApplications.add(0, loanApplication);
				tvLoanApplications.getSelectionModel().select(loanApplication);
			}
		}else if(o instanceof ShowLoanApplicationController 
				|| o instanceof UpdateLoanApplicationController) {
			tvLoanApplications.refresh();
		}
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
