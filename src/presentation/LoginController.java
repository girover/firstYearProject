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
    @Rules(field="user name", rules="required|alpha")
    private TextField inputUserID;

    @FXML
    @Rules(field="password", rules="required|alphaDash")
    private PasswordField inputPassword;

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
			App.showErrorMessage(validator.getErrorMessagesAsString(), "Validation Failed");
			return;
		}
		boolean b =Window.showCinformDialog("Test", "Are you sure");
		
		FormData fromData = new FormData();
		fromData.setData("userId", inputUserID.getText())
				.setData("userPassword", inputPassword.getText());
		
    	if(authService.login(fromData)) {
    		App.flashSuccessMessage("You are logged in now.", "Logged in");
    		Log.information("Loged in the system");
    	}else {
    		App.flashErrorMessage("Please provide correct data.", "validation error");
    		Log.warning("Failed login attempt into the system");
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnLogin.setText(translate("login"));
	}

}
