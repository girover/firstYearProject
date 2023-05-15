package presentation;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import window.Window;

public class LoadingController extends ValidatableController {

	@FXML
    private Button btnShowMessage;

    @FXML
    private Label lblProcess;

    @FXML
    void handleBtnShowMessageClick(ActionEvent event) {

    	Window errorMessage = new Window("messages/errorMessage.fxml", "ErrorMessage");
//    	BaseController controller = errorMessage.getController();
    	errorMessage.setSenderController(this);
    	
    	((MessageController)errorMessage.getController()).setMessage("Test Message");
    	errorMessage.show();
    }
    @FXML
    void handleInputOnAction(ActionEvent event) {

    }
    
	public void setProcess(String process) {
		lblProcess.setText(process);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String)
			setProcess((String)arg);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
