package presentation.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Customer;
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
import services.CustomerService;
import window.Window;

public class CustomersController extends BaseController {

    @FXML
    private Button btnCancle;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private Button btnNewCustomer;
    
    @FXML
    private Button btnLoanApplication;
    
    @FXML
    private Button btnAll;
    
    @FXML
    private TextField inputSearch;

    @FXML
    private TableColumn<Customer, String> colAddress;

    @FXML
    private TableColumn<Customer, String> colCity;

    @FXML
    private TableColumn<Customer, String> colEmail;

    @FXML
    private TableColumn<Customer, String> colFirstName;

    @FXML
    private TableColumn<Customer, String> colID;

    @FXML
    private TableColumn<Customer, String> colLastName;

    @FXML
    private TableColumn<Customer, String> colPhone;

    @FXML
    private TableColumn<Customer, String> colZipCode;

    @FXML
    private TableView<Customer> tvCustomers;
    
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    
    private CustomerService  customerService = new CustomerService();

    @FXML
    void handleBtnCancleClick(ActionEvent event) {
    	Window.closeWindow(event);
    }

    @FXML
    void handleBtnDeleteClick(ActionEvent event) {

    }
    
    @FXML
    void handleBtnAllClick(ActionEvent event) {
    	
    }
    
    @FXML
    void handleBtnLoanApplicationClick(ActionEvent event) {
    	
    }
    
    @FXML
    void handleInputSearchOnKeyReleased(KeyEvent event) throws Exception {
    	searchCustomers();
    }

    @FXML
    void handleBtnEditCustomerClick(ActionEvent event) {
    	
    	Customer customer = tvCustomers.getSelectionModel().getSelectedItem();
    	
    	if(customer == null) {
    		flashErrorMessage("Please select a customer.", "Select Customer");
    		return;
    	}
    	Window editCustomersWindow = new Window("customer/EditCustomer.fxml", "Customers");
    	EditCustomerController controller = (EditCustomerController)editCustomersWindow.getController();
    	controller.setCustomer(customer);
    	editCustomersWindow.show();
    }

    @FXML
    void handleBtnNewCustomerClick(ActionEvent event) {
    	Window customersWindow = new Window("customer/NewCustomer.fxml", "Customers");
    	customersWindow.show();
    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableViewColumns();
		initTableView();
		loadCustomers();
	}
	
	private void initTableView() {
		tvCustomers.setItems(customers);
	}
	
	private void initTableViewColumns() {
		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
		colZipCode.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
	}
	
	private void loadCustomers() {
		ArrayList<Customer> customers = customerService.getAll();
		this.customers.addAll(customers);
	}
	
	private void searchCustomers() throws Exception {
		String searchKey = inputSearch.getText();
		if(searchKey == null ||searchKey.isBlank())
			return;
		customers.clear();
		
		ArrayList<Customer> foundCustomers = customerService.search(searchKey);
		
		if(foundCustomers.size() > 0)
			customers.addAll(customerService.search(searchKey));
	}

}
