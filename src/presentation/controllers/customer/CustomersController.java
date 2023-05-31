package presentation.controllers.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import entities.Customer;
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
import presentation.controllers.BaseController;
import presentation.controllers.loanApplication.CustomerLoanApplicationsController;
import services.CustomerService;

/**
 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 *         
 */
public class CustomersController extends BaseController {

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnEditCustomer;

	@FXML
	private Button btnNewCustomer;

	@FXML
	private Button btnLoanApplication;

	@FXML
	private Button btnRefresh;

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

	private CustomerService customerService = new CustomerService();

	@FXML
	void handleBtnDeleteClick(ActionEvent event) {

	}

	@FXML
	void handleBtnRefreshClick(ActionEvent event) {
		customers.clear();
		customers.addAll(customerService.getAll());
	}

	@FXML
	void handleBtnLoanApplicationClick(ActionEvent event) {
		Customer customer = tvCustomers.getSelectionModel().getSelectedItem();

		if (customer == null) {
			flashErrorMessage("Please Choose a customer first.", "Unselected");
			return;
		}
		CustomerLoanApplicationsController controller = 
				(CustomerLoanApplicationsController) openWindowAndGetController(
				"loanapplication/CustomerLoanApplications.fxml", "Customer Loan Applications");
		
		controller.addObserver(this);
		controller.setCustomer(customer);
	}

	@FXML
	void handleInputSearchOnKeyReleased(KeyEvent event) throws Exception {
		searchCustomers();
	}

	@FXML
	void handleBtnEditCustomerClick(ActionEvent event) {

		Customer customer = tvCustomers.getSelectionModel().getSelectedItem();

		if (customer == null) {
			flashErrorMessage("Please select a customer.", "Select Customer");
			return;
		}

		EditCustomerController controller = (EditCustomerController) openWindowAndGetController(
				"customer/EditCustomer.fxml", "Customers");
		controller.addObserver(this);
		controller.setCustomer(customer);
	}

	@FXML
	void handleBtnNewCustomerClick(ActionEvent event) {
		NewCustomerController controller = (NewCustomerController) openWindowAndGetController(
				"customer/NewCustomer.fxml", "Customers");
		controller.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof EditCustomerController) {
			tvCustomers.refresh();
		}else if (o instanceof NewCustomerController) {
			Customer newCustomer = ((NewCustomerController) o).getCustomer();
			if (newCustomer != null) {
				customers.add(0,newCustomer);
				tvCustomers.getSelectionModel().select(newCustomer);
			}
		}
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
		
		if (searchKey == null || searchKey.isBlank())
			return;
		
		customers.clear();

		ArrayList<Customer> foundCustomers = customerService.search(searchKey);

		if (foundCustomers.size() > 0) 
			customers.addAll(foundCustomers);
	}

}
