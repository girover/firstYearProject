package presentation.component;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentation.BaseController;

public class PaginationButtonController extends BaseController {

    @FXML
    private Button btnPage;
    
    private int pageNumber;

    
    public void setPage(int page) {
    	pageNumber = page;
    	btnPage.setText(Integer.toString(page));
    }
    
    public int getPage() {
    	return pageNumber;
    }
    
    @FXML
    void handleBtnPageClick(ActionEvent event) {
    	fire(pageNumber);
    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}