package presentation.controllers;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public class MessageController extends BaseController {

	@FXML
	private Label lblMessage;

	public void setMessage(String message) {
		lblMessage.setText(message);
	}

	@FXML
	void handleLblClick(MouseEvent event) {
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
}
