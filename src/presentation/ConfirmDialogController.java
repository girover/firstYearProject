package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import window.Window;

/**
 * This class is a part of Presentation Layer. 
 *
 * This class is responsible for showing confirmation dialog box to the user
 * 
 * @version 1.0
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
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
