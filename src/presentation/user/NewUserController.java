package presentation.user;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Customer;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Msg;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.ValidatableController;
import services.CustomerService;

public class NewUserController extends ValidatableController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancle;

    @FXML
    @Rules(field = "cpr number", rules = "required|numeric|length:10")
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
    
    private Customer customer;

    @FXML
    void handleBtnAddClick(ActionEvent event) throws ValidationException {

    	validate();
    	
    	if(!validator.passes()) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "validation failed");
    		return;
    	}
    	
    	fillCustomerWithData();
    	
    	CustomerService customerService = new CustomerService();
    	
    	customer = customerService.create(customer);
    	
    	if(customer != null) {
    		flashSuccessMessage("Customer created Successfuly", "Created Customer");
    		customer.setCity(inputCity.getText());
    		fire();
    		return;
    	}else
    		showErrorMessage("Failed to create new customer", "Creaing Customer failed");
    			
    }
    
    private void fillCustomerWithData() {
    	
    	customer.setCPRHash(inputCPR.getText());
    	customer.setFirstName(inputFirstName.getText());
    	customer.setLastName(inputLastName.getText());
    	customer.setPhone(inputPhone.getText());
    	customer.setEmail(inputEmail.getText());
    	customer.setAddress(inputAddress.getText());
    	customer.setCity(inputCity.getText());
    	customer.setZipCode(inputZipCode.getText());
    }

    @FXML
    void handleBtnCancleClick(ActionEvent event) {
    	closeWindow(event);
    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
