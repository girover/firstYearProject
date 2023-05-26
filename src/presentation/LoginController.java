package presentation;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import app.App;
import app.FormData;
import javaFxValidation.ValidationException;
import javaFxValidation.annotations.Rules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import log.Log;
import services.AuthService;
import window.Window;

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
		
		FormData fromData = new FormData();
		
		fromData.setData("userId", inputUserID.getText())
				.setData("userPassword", inputPassword.getText());
		
    	if(authService.login(fromData)) {
    		App.showUserWindow(App.getAuthenticatedUser());
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
