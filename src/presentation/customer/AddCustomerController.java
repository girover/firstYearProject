package presentation.customer;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import app.FormData;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Msg;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.ValidatableController;
import services.CustomerService;

public class AddCustomerController extends ValidatableController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancle;

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
    @Rules(field="address", rules="required|regex:^[\\p{L}\\p{M}\\p{N}\\s\\.]+$")
    @Msg(rule="regex", message="Please provide a valid address.")
    private TextField inputAddress;

    @FXML
    @Rules(field="city", rules="required|alpha")
    private TextField inputCity;

    @FXML
    @Rules(field="zip code", rules="required|numeric")
    private TextField inputZipCode;

    @FXML
    void handleBtnAddClick(ActionEvent event) throws ValidationException {

    	validate();
    	
    	if(!validator.passes()) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "validation failed");
    		return;
    	}
    	
    	CustomerService customerService = new CustomerService();
    	if(customerService.create(getFormData()) != null) {
    		showSuccessMessage("Customer created Successfuly", "Created Customer");
    		return;
    	}else
    		showErrorMessage("Failed to create new customer", "Creaing Customer failed");
    		
    }
    
    private FormData getFormData() {
    	FormData formData = new FormData();
    	
    	formData.setData("firstName", inputFirstName.getText());
    	formData.setData("lastName", inputLastName.getText());
    	formData.setData("phone", inputPhone.getText());
    	formData.setData("email", inputEmail.getText());
    	formData.setData("address", inputAddress.getText());
    	formData.setData("city", inputCity.getText());
    	formData.setData("zipCode", inputZipCode.getText());
    	
    	return formData;
    }

    @FXML
    void handleBtnCancleClick(ActionEvent event) {

    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
