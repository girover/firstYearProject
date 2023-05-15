package presentation.user;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import database.entities.Customer;
import database.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.BaseController;
import services.UserService;
import window.Window;

public class UsersController extends BaseController {

    @FXML
    private Button btnCancle;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEditUser;

    @FXML
    private Button btnNewUser;

    @FXML
    private TableColumn<User, String> colID;

    @FXML
    private TableColumn<User, String> colLastName;

    @FXML
    private TableColumn<User, String> colLastName1;

    @FXML
    private TableColumn<User, String> colLastName11;

    @FXML
    private TableColumn<User, String> colLastName111;

    @FXML
    private TableColumn<User, String> colUserame;

    @FXML
    private TableView<User> tvUsers;
    
    private ObservableList<User> users = FXCollections.observableArrayList();
    
    private UserService userService = new UserService();

    @FXML
    void handleBtnCancleClick(ActionEvent event) {
    	Window.closeWindow(event);
    }

    @FXML
    void handleBtnDeleteClick(ActionEvent event) {

    }

    @FXML
    void handleBtnEditUserClick(ActionEvent event) {

    }

    @FXML
    void handleBtnNewUserClick(ActionEvent event) {

    }

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableViewColumns();
		initTableView();
		loadUsers();
	}
	
	private void initTableView() {
		tvUsers.setItems(users);
	}
	
	private void initTableViewColumns() {
		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colUserame.setCellValueFactory(new PropertyValueFactory<>("userName"));
	}
	
	private void loadUsers() {
		ArrayList<User> users = userService.getAll();
		this.users.addAll(users);
	}

}
