package presentation.profile;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import app.App;
import database.entities.City;
import database.entities.Employee;
import database.entities.User;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Msg;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import presentation.ValidatableController;
import services.CityService;
import services.EmployeeService;
import services.HashingService;
import services.UserService;

/**
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class EditProfileController extends ValidatableController {

	@FXML
	@Rules(field = "first name", rules = "required|alphaSpace")
	private TextField inputFirstName;

	@FXML
	@Rules(field = "last name", rules = "required|alphaSpace")
	private TextField inputLastName;

	@FXML
	@Rules(field = "address", rules = "required|regex:[\\p{L}\\p{M}\\p{N}\\s.,]+")
	@Msg(rule = "regex", message = "Please provide a valid address.")
	private TextField inputAddress;

	@FXML
	@Rules(field = "city", rules = "required|alpha")
	private TextField inputCity;

	@FXML
	@Rules(field = "zip code", rules = "required|numeric")
	private TextField inputZipCode;

	@FXML
	@Rules(field = "email", rules = "required|email")
	private TextField inputEmail;

	@FXML
	@Rules(field = "phone", rules = "required|numeric|length_min:8|length_max:12")
	@Msg(rule = "numeric", message = "Phone number only enter numbers")
	private TextField inputPhone;

	@FXML
	@Rules(field = "role", rules = "in:manager,seller")
	private TextField inputRole;

	@FXML
	@Rules(field = "username", rules = "required|alphaNumeric")
	private TextField inputUsername;

	@FXML
	@Rules(field = "password", rules = "required|alphaNumeric")
	private PasswordField inputPassword;

	@FXML
	@Rules(field = "new password", rules = "required|alphaNumeric")
	private PasswordField inputNewPassword;

	@FXML
	@Rules(field = "confirm new password", rules = "same:new password")
	private PasswordField inputConfirmNewPassword;

	@FXML
	private Button btnUpdateUser;

	@FXML
	private Button btnUpdateEmployee;

	@FXML
	private Button BtnClose;

	
	private User user = getAuthenticatedUser();
	private Employee employee = user.getEmployee();
	
	private Employee fillEmployeeWithNewData() {
		employee.setFirstName(inputFirstName.getText());
		employee.setLastName(inputLastName.getText());
		employee.setAddress(inputAddress.getText());
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
		inputZipCode.setText(employee.getZipCode());
		inputCity.setText(employee.getCity());
		inputEmail.setText(employee.getEmail());
		inputPhone.setText(employee.getPhone());
		inputRole.setText(employee.getRole());
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
		fillInputsWithEmployee(employee);
	}
	
	@FXML
    void handleInputZipCodeKeyReleased(KeyEvent event) {
    	
    	City city = (new CityService()).find(inputZipCode.getText());
    	
    	if(city != null)
    		inputCity.setText(city.getCity());
    	else
    		inputCity.setText("");
    }

	@FXML
	void handleBtnUpdateEmployeeClick(ActionEvent event) throws ValidationException {
		validate("first name", "last name", "address", "city", "zip code", "email", "phone", "role");

		if (!validator.passes()) {
			showErrorMessage(validator.getErrorMessagesAsString(), "dammit");
			return;
		}

		EmployeeService employeeService = new EmployeeService();

		if (employeeService.update(fillEmployeeWithNewData())) {
			
			getAuthenticatedUser().getEmployee().setCity(inputCity.getText());
			
			flashSuccessMessage("Employee has been updated", "success");
		} else {
			showErrorMessage("Failed to updated employee", "failed");
		}
	}

	@FXML
	void handleBtnUpdateUserClick(ActionEvent event) throws ValidationException {

		validate("username", "password", "new password", "confirm new password");

		if (!validator.passes()) {
			showErrorMessage(validator.getErrorMessagesAsString(), "dammit");
			return;
		}
		
		if(usernameFound() && !user.getUserName().equals(inputUsername.getText())) {
			flashErrorMessage("Username already exists!", "user name");
			return;
		}
		
		if(!HashingService.verify(inputPassword.getText(), user.getPassword())) {
			flashErrorMessage("Please type your correct password!", "Password");
			return;
		}
		
		UserService userService = new UserService();
		
		if (userService.update(fillUserWithNewData())) {
			flashSuccessMessage("User has been updated", "succes");

			return;
		} else {
			showErrorMessage("Failed to updated user", "failed");
		}
	}

	private boolean usernameFound() {
		UserService service = new UserService();
		if(service.findByUsername(inputUsername.getText()) != null)
			return true;
		return false;
	}

	public void setUser(User user) {
		this.user = user;
		fillInputsWithUser(user);
	}

	private User fillUserWithNewData() {
		user.setUserName(inputUsername.getText());
		user.setPassword(HashingService.secureHash(inputNewPassword.getText()));
		
		return user;
	}

	private void fillInputsWithUser(User user) {
		inputUsername.setText(user.getUserName());
	}

	@FXML
	void handleBtnCloseClick(ActionEvent event) {
		closeWindow(event);
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillInputsWithEmployee(employee);
		fillInputsWithUser(user);
	}

}