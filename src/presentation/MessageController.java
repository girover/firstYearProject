package presentation;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MessageController extends BaseController {

	@FXML
	private Label lblMessage;

	public void setMessage(String message) {
		lblMessage.setText(message);
	}

	@FXML
	void handleLblClick(MouseEvent event) {
		System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii");
		fire("Hiiiiiiiiiiiiiiiiiiiiiiiiiii");
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
