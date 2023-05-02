package presentation;

import java.util.Observable;

import app.App;
import business.AuthService;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LoginController extends ValidateableController {

    @FXML
    private Button btnLogin;

    @FXML
    @Rules(field="password", rules="required|alphaDash")
    private PasswordField inputPassword;

    @FXML
    @Rules(field="user name", rules="required|alphaDash")
    private TextField inputUserID;

    @FXML
    private Label labelMessage;
    
    private AuthService authService;

    public LoginController() throws ValidationException {
    	authService = new AuthService();
    }
    
    @FXML
    void login(ActionEvent event) throws ValidationException {
    	
		validate();
		
		if(!validator.passes()) {
			App.showErrorMessage(validator.getErrorMessages().toString(), "Validation Failed");
			return;
		}
		
    	boolean authenticated = authService.login(inputUserID.getText(), inputPassword.getText());
    	
    	if(authenticated) {
    		App.flashSuccessMessage("You are logged in now.", "Logged in");
    	}else {
    		App.flashErrorMessage("Please provide correct data.", "validation error");
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
		// TODO Auto-generated method stub
		
	}

}
