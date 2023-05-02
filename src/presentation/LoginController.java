package presentation;

import java.util.Observable;

import business.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import window.FlashWindow;
import window.Window;

public class LoginController extends BaseController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUserID;

    @FXML
    private Label labelMessage;
    
    private AuthService authService;

    public LoginController() {
    	authService = new AuthService();
    }
    
    @FXML
    void login(ActionEvent event) {
    	
    	boolean authenticated = authService.login(inputUserID.getText(), inputPassword.getText());
    	
    	if(authenticated) {
    		Window s = new Window("messages/errorMessage.fxml", "loged in");
    		s.show();
    	}else {
    		FlashWindow w = new FlashWindow("messages/errorMessage.fxml", "Login Failed");
    		w.setMessage("Please provide correct data.");
    		w.flash();
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
