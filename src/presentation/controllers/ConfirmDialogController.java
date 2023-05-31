package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentation.window.Window;

/**
 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 *         
 */
public class ConfirmDialogController extends MessageController {

	/**
	 * If user confirm the action by clicking on yes button this will be true,
	 * otherwise this will be false.
	 */
	private boolean userAction = false;
	
	
	@FXML
    private Button btnNo;

    @FXML
    private Button btnYes;

    public boolean getUserAction() {
    	return userAction;
    }
    
    @FXML
    void handleBtnYesClick(ActionEvent event) {
    	userAction = true;
    	Window.closeWindow(event);
    }

    @FXML
    void handleBtnNoClick(ActionEvent event) {
    	userAction = false;
    	Window.closeWindow(event);
    }

}
