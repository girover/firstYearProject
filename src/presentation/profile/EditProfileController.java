package presentation.profile;


import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import app.App;
import database.entities.Employee;
import database.entities.User;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Msg;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.ValidatableController;
import services.EmployeeService;
import services.UserService;

public class EditProfileController extends ValidatableController {

    @FXML
	@Rules(field="first name", rules="alpha")
	private TextField inputFirstName;

	@FXML
	@Rules(field="last name", rules="alpha")
	private TextField inputLastName;

	@FXML
	@Rules(field="address", rules="required|regex:^[\\p{L}\\p{M}\\p{N}\\s\\.]+$")
	@Msg(rule="regex", message="Please provide a valid address.")
	private TextField inputAddress;

	@FXML
    @Rules(field="city", rules="alpha")
    private TextField inputCity;

    @FXML
	@Rules(field="zip code", rules="required|numeric")
	private TextField inputZipCode;

	@FXML
	@Rules(field="email", rules="required|email")
	private TextField inputEmail;

	@FXML
	@Rules(field="phone", rules="required|numeric|min:8|max:10")
	@Msg(rule="numeric", message="Phone number only enter numbers")
	private TextField inputPhone;

	@FXML
	@Rules(field="role", rules="in:admin,seller")
	private TextField inputRole;

	@FXML
	@Rules(field="username", rules="required|alphaNumeric")
	private TextField inputUsername;

	@FXML
    @Rules(field="password", rules="required|alphaNumeric")
    private TextField inputPassword;

    @FXML
	@Rules(field="new password", rules="required|alphaNumeric")
	private TextField inputNewPassword;

	@FXML
	@Rules(field="confirm new password", rules="same:new password")
	private TextField inputConfirmNewPassword;

	@FXML
	private Button btnUpdateUser;

	@FXML
	private Button btnUpdateEmployee;

	@FXML
	private Button BtnClose;


	
	private User editUser;
	private Employee employee;
	private EmployeeService employeeService = new EmployeeService();
	
	
	private Employee fillEmployeeWithNewData() {
    	employee.setFirstName(inputFirstName.getText());
    	employee.setLastName(inputLastName.getText());
    	employee.setAddress(inputAddress.getText());
    	employee.setCity(inputCity.getText());
    	employee.setZipCode(inputZipCode.getText());
    	employee.setEmail(inputEmail.getText());
    	employee.setPhone(inputPhone.getText());
    	employee.setRole(inputRole.getText());
		return employee;
	}

	private void fillInputsWithEmployee(Employee employee) {
		inputFirstName.setText(employee.getFirstName());
		inputLastName.setText(employee.getLastName());
		inputAddress.setText(employee.getAddress());
		inputCity.setText(employee.getCity());
		inputZipCode.setText(employee.getZipCode());
		inputEmail.setText(employee.getEmail());
		inputPhone.setText(employee.getPhone());
		inputRole.setText(employee.getRole());
	}
	
	 public void setEmployee(Employee employee) {
	    	this.employee = employee;
	    	fillInputsWithEmployee(employee);
	    }



    @FXML
    void handleBtnUpdateEmployeeClick(ActionEvent event) throws ValidationException {
    	validate();
    	
    	if(!validator.passes(true)) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "dammit");
    	return;
    	}
    	EmployeeService employeeService = new EmployeeService();
    	if(employeeService.update(fillEmployeeWithNewData())) {
    		showSuccessMessage("Employee has been updated", "succes");
    		
    		fire(employee);
        	
        	return;
    	}else {showErrorMessage("Failed to updated employee", "failed");
    	
    	}
    }
    
   
    
    	
    

    

	@FXML
    void handleBtnUpdateUserClick(ActionEvent event) throws ValidationException {
	validate();
    	
    	if(!validator.passes()) {
    		showErrorMessage(validator.getErrorMessagesAsString(), "dammit");
    	return;
    	}
    	UserService userService = new UserService();
    	if(userService.update(fillUserWithNewData())) {
    		showSuccessMessage("User has been updated", "succes");
    		
    		fire(editUser);
        	
        	return;
    	}else {showErrorMessage("Failed to updated euser", "failed");
    	
    	}
    }
	
	public void setUser(User user) {
		editUser = user;
		fillInputsWithUser();
	}
    
    
    private User fillUserWithNewData() {
    	editUser.setUserName(inputUsername.getText());
    	editUser.setPassword(inputNewPassword.getText());
		return editUser;
	}

	private void fillInputsWithUser() {
		inputUsername.setText(editUser.getUserName());
		inputNewPassword.setText(editUser.getPassword());
	}

	@FXML
    void handleBtnCloseClick(ActionEvent event) {
    	closeWindow(event);

    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	 User user = App.getAuthenticatedUser();
	 int employeeId = user.getEmployeeId();
	 employee = employeeService.find(employeeId);
		
	}

}
