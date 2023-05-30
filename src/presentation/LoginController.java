package presentation;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import app.App;
import database.entities.User;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import services.AuthService;
/**
 *
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public class LoginController extends ValidatableController {

    @FXML
    private Button btnLogin;

    @FXML
    @Rules(field="user name", rules="required|alphaNumeric")
    private TextField inputUserID;

    @FXML
    @Rules(field="password", rules="required|alphaDash")
    private PasswordField inputPassword;

    @FXML
    private Label labelMessage;
    
    private AuthService authService;

    public LoginController() {
    	authService = new AuthService();
    }
    
    @FXML
    void handleInputOnAction(ActionEvent event) throws ValidationException {
    	login(event);
    }
    
    @FXML
    void login(ActionEvent event) throws ValidationException {
    	
		validate("user name");
		
		if(!validator.passes()) {
			showErrorMessage(validator.getErrorMessagesAsString(), "Validation Failed");
			return;
		}
		
		User user = new User();
		user.setUserName(inputUserID.getText());
		user.setPassword(inputPassword.getText());
		
    	if(authService.login(user)) {
    		App.showUserWindow(getAuthenticatedUser());
    	}else {
    		flashErrorMessage("Please provide correct data.", "validation error");
    	}
    }

    @FXML
    void onKeyReleasedInputEmail(KeyEvent event) {

    }

    @FXML
    void onKeyReleasedInputPassword(KeyEvent event) {

    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnLogin.setText(translate("login"));
	}

}
