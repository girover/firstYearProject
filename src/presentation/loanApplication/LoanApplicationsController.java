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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import presentation.BaseController;
import services.LoanApplicationService;

public class LoanApplicationsController extends BaseController {

    @FXML
    private Button btnAll;

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

    }

    @FXML
    void handleBtnNewLoanApplicationClick(ActionEvent event) {
    	openWindow("loanApplication/NewLoanApplication.fxml", "New Loan Application");
    }

    @FXML
    void handleInputSearchOnKeyReleased(KeyEvent event) {

    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
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
		colLoanAmount.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));
		colMonths.setCellValueFactory(new PropertyValueFactory<>("months"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
		colRate.setCellValueFactory(new PropertyValueFactory<>("interestRate"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
	}

}
