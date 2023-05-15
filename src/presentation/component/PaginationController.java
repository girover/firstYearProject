package presentation.component;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import presentation.BaseController;
import window.Component;

public class PaginationController extends BaseController {

    @FXML
    private HBox paginator;
    
    private int totalPages;
    private int currentPage;
    
    
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
		prepareItems();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	private void prepareItems() {
		
		for(int i=0; i<totalPages; i++) {
			Component paginationButton = new Component("PaginationButton.fxml");
			
			((PaginationButtonController)paginationButton.getController()).setPage(i);
			((PaginationButtonController)paginationButton.getController()).addObserver(this);
			
			paginator.getChildren().add((Button)paginationButton.get());
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof PaginationButtonController) {
			currentPage = ((PaginationButtonController)o).getPage();
			fire(currentPage);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}