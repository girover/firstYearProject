package exception;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import presentation.controllers.MessageController;
import presentation.window.Window;
import services.log.Log;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class ExceptionHandlerController extends MessageController {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnPrint;

    @FXML
    private TextArea textArea;

    @FXML
    void onClickBtnClose(ActionEvent event) {
    	Window.closeWindow(event);
    }

    @FXML
    void onClickBtnPrint(ActionEvent event) throws Exception {

    }
    
    public void writeExceptionDeatails(String text) {
    	textArea.setText(text);
    	
    	// Here we store the exception message in the database or a file to check it later.
    	Log.debugging(text);
    }

}
