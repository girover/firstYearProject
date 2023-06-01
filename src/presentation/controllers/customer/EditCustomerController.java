package presentation.controllers.customer;

import entities.Customer;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Msg;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
public class EditCustomerController extends NewCustomerController {

    @FXML
    private Button btnUpdate;

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
    @Rules(field="persons address", rules="required|regex:[\\p{L}\\p{M}\\p{N}\\s,.]+")
    @Msg(rule="regex", message="Please provide a valid address.")
    private TextField inputAddress;

    @FXML
    @Rules(field="city", rules="required|alphaSpace")
    private TextField inputCity;

    @FXML
    @Rules(field="zip code", rules="required|numeric")
    private TextField inputZipCode;


    @FXML
    void handleBtnUpdateClick(ActionEvent event) throws ValidationException {

    	validate();
    	
    	if(!validator.passes()) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "validation failed");
    		return;
    	}
    	
    	fillCustomerWithData();
    	
    	CustomerService customerService = new CustomerService();
    	
    	if(customerService.update(customer)) {
    		flashSuccessMessage("Customer updated Successfuly", "Created Customer");
    		customer.setCity(inputCity.getText());
    		// To inform the observer controller that the wanted customer is updated now
    		fire();
    		btnCancel.fire();
    		return;
    	}else
    		showErrorMessage("Failed to update the customer", "Creaing Customer failed");
    		
    }
    
    public void setCustomer(Customer customer) {
    	this.customer = customer;
    	fillInputsWithCustomer();
    }
    
    @Override
    protected void fillCustomerWithData() {
    	
    	super.fillCustomerWithData();
    	
    	customer.setCPRHash("");
    }
    
    private void fillInputsWithCustomer() {
    	//inputCPR.setText(customerUnderEditing.getCPRHash());
    	inputFirstName.setText(customer.getFirstName());
    	inputLastName.setText(customer.getLastName());
    	inputPhone.setText(customer.getPhone());
    	inputEmail.setText(customer.getEmail());
    	inputAddress.setText(customer.getAddress());
    	inputCity.setText(customer.getCity());
    	inputZipCode.setText(Integer.toString(customer.getZipCode()));
    }

}