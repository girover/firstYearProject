package presentation;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import window.Window;

public class LoadingController extends BaseController {

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
	public void setProcess(String process) {
		lblProcess.setText(process);
	}
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String)
			setProcess((String)arg);
	}
}
