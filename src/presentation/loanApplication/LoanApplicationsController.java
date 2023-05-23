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
import javafx.scene.paint.Color;
import javafx.util.Callback;
import presentation.BaseController;
import services.LoanApplicationService;
import window.Window;

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
    
    @FXML
    void handleBtnShowClick(ActionEvent event) {
    	LoanApplication loan = tvLoanApplications.getSelectionModel().getSelectedItem();
    	if(loan == null) {
    		flashErrorMessage("No loan application is selected", "Select Item");
    		return;
    	}
    	Window showLoanApplication = new Window("loanApplication/ShowLoanApplication.fxml", "Loan Application");
    	ShowLoanApplicationController controller = (ShowLoanApplicationController)showLoanApplication.getController();
    	
    	controller.setLoanApplication(loan);
    	showLoanApplication.show();
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
		colLoanAmount.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));
		colMonths.setCellValueFactory(new PropertyValueFactory<>("months"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
		colRate.setCellValueFactory(new PropertyValueFactory<>("interestRate"));
//		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		colStatus.setCellFactory(new Callback<TableColumn<LoanApplication, String>,
                TableCell<LoanApplication, String>>()
        {
            @Override
            public TableCell<LoanApplication, String> call(
                    TableColumn<LoanApplication, String> param) {
                return new TableCell<LoanApplication, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        if (!empty) {
                            int currentIndex = indexProperty()
                                    .getValue() < 0 ? 0
                                    : indexProperty().getValue();
                            String clmStatus = param
                                    .getTableView().getItems()
                                    .get(currentIndex).getStatus();
                            if (clmStatus.equals("rejected")) {
                                setStyle("-fx-background-color: red");
                                
                            }else
                            if (clmStatus.equals("processing")) {
                            	setStyle("-fx-background-color: orange");
                            }else
                        	if (clmStatus.equals("approved")) {
                        		setStyle("-fx-background-color: green");
                        	}
                            setText(clmStatus);
                        }
                    }
                };
            }
        });
	}

}
