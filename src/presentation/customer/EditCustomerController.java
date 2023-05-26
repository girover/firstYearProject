package presentation.customer;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.City;
import database.entities.Customer;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Msg;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import presentation.ValidatableController;
import services.CityService;
import services.CustomerService;

public class EditCustomerController extends ValidatableController {

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCancle;

    @FXML
//    @Rules(field = "cpr number", rules = "required|numeric|length:10")
    private TextField inputCPR;
    
    @FXML
    @Rules(field="first name", rules="required|alphaSpace")
    private TextField inputFirstName;
    
    @FXML
    @Rules(field="last name", rules="required|alphaSpace")
    private TextField inputLastName;
    
    @FXML
    @Rules(field="phone", rules="required|numeric")
    private TextField inputPhone;
    
    @FXML
    @Rules(field="email", rules="required|email")
    private TextField inputEmail;
    
    @FXML
    @Rules(field="persons address", rules="required|regex:^[\\p{L}\\p{M}\\p{N}\\s\\.]+$")
    @Msg(rule="regex", message="Please provide a valid address.")
    private TextField inputAddress;

    @FXML
    @Rules(field="city", rules="required|alpha")
    private TextField inputCity;

    @FXML
    @Rules(field="zip code", rules="required|numeric")
    private TextField inputZipCode;
    
    private Customer customerUnderEditing;

    @FXML
    void handleBtnUpdateClick(ActionEvent event) throws ValidationException {

    	validate();
    	
    	if(!validator.passes()) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "validation failed");
    		return;
    	}
    	
    	CustomerService customerService = new CustomerService();
    	if(customerService.update(fillCustomerWithNewData())) {
    		flashSuccessMessage("Customer updated Successfuly", "Created Customer");
    		customerUnderEditing.setCity(inputCity.getText());
    		// To inform the observer controller that the wanted customer is updated now
    		fire();
    		
    		return;
    	}else
    		showErrorMessage("Failed to update the customer", "Creaing Customer failed");
    		
    }
    
    public void setCustomer(Customer customer) {
    	customerUnderEditing = customer;
    	fillInputsWithCustomer();
    }
    
    public Customer getUpdatedCustomer(){
    	return customerUnderEditing;
    }
    
    private Customer fillCustomerWithNewData() {
    	//customerUnderEditing.setCPRHash(inputCPR.getText());
    	customerUnderEditing.setFirstName(inputFirstName.getText());
    	customerUnderEditing.setLastName(inputLastName.getText());
    	customerUnderEditing.setPhone(inputPhone.getText());
    	customerUnderEditing.setEmail(inputEmail.getText());
    	customerUnderEditing.setAddress(inputAddress.getText());
    	customerUnderEditing.setCity(inputCity.getText());
    	customerUnderEditing.setZipCode(inputZipCode.getText());
    	
    	return customerUnderEditing;
    }
    private void fillInputsWithCustomer() {
    	//inputCPR.setText(customerUnderEditing.getCPRHash());
    	inputFirstName.setText(customerUnderEditing.getFirstName());
    	inputLastName.setText(customerUnderEditing.getLastName());
    	inputPhone.setText(customerUnderEditing.getPhone());
    	inputEmail.setText(customerUnderEditing.getEmail());
    	inputAddress.setText(customerUnderEditing.getAddress());
    	inputCity.setText(customerUnderEditing.getCity());
    	inputZipCode.setText(customerUnderEditing.getZipCode());
    }

    @FXML
    void handleBtnCancleClick(ActionEvent event) {
    	closeWindow(event);
    }
    
    @FXML
    void handleInputPostKeyReleased(KeyEvent event) {
    	
    	CityService service = new CityService();
    	
    	String zipCode = inputZipCode.getText();
    	
    	City city = service.find(zipCode);
    	
    	if(city != null)
    		inputCity.setText(city.getCity());
    	else
    		inputCity.setText("");
    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
