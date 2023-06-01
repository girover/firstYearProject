package presentation.controllers.customer;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import entities.City;
import entities.Customer;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Msg;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import presentation.controllers.ValidatableController;
import services.CityService;
import services.CustomerService;

/**
 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public class NewCustomerController extends ValidatableController {

    @FXML
    private Button btnAdd;

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
    @Rules(field="persons address", rules="required|regex:[\\p{L}\\p{M}\\p{N}\\s,.]+")
    @Msg(rule="regex", message="Please provide a valid address.")
    private TextField inputAddress;

    @FXML
    @Rules(field="city", rules="required|alphaSpace")
    private TextField inputCity;

    @FXML
    @Rules(field="zip code", rules="required|numeric")
    private TextField inputZipCode;
    
    protected Customer customer = new Customer();

    @FXML
    void handleBtnAddClick(ActionEvent event) throws ValidationException {

    	validate();
    	
    	if(!validator.passes()) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "validation failed");
    		return;
    	}
    	
    	fillCustomerWithData();
    	
    	customer = (new CustomerService()).create(customer);
    	
    	if(customer != null) {
    		flashSuccessMessage("Customer created Successfuly", "Created Customer");
    		customer.setCity(inputCity.getText());
    		fire();
    		btnCancel.fire();
    		return;
    	}else
    		showErrorMessage("Failed to create new customer", "Creaing Customer failed");
    		
    }
    
    protected void fillCustomerWithData() {
    	
    	customer.setCPRHash(inputCPR.getText());
    	customer.setFirstName(inputFirstName.getText());
    	customer.setLastName(inputLastName.getText());
    	customer.setPhone(inputPhone.getText());
    	customer.setEmail(inputEmail.getText());
    	customer.setAddress(inputAddress.getText());
    	customer.setCity(inputCity.getText());
    	customer.setZipCode(Integer.parseInt(inputZipCode.getText()));
    }
    
    public Customer getCustomer() {
    	return customer;
    }
    
    public void setCpr(String cpr) {
    	inputCPR.setText(cpr);
    }
    
    @FXML
    protected void handleInputPostKeyReleased(KeyEvent event) {
    	
    	City city = (new CityService()).find(inputZipCode.getText());
    	
    	if(city != null)
    		inputCity.setText(city.getCity());
    	else
    		inputCity.setText("");
    }

    @FXML
    protected void handleBtnCancleClick(ActionEvent event) {
    	closeWindow(event);
    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
