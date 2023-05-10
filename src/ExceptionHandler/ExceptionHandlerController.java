package ExceptionHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import log.Log;

public class ExceptionHandlerController {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnPrint;

    @FXML
    private TextArea textArea;

    @FXML
    void onClickBtnClose(ActionEvent event) {

    }

    @FXML
    void onClickBtnPrint(ActionEvent event) throws Exception {

    }
    
    public void writeExceptionDeatails(String text) {
    	textArea.setText(text);
    	Log.debugging(text);
    }

}
