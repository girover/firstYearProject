package presentation.window;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.controllers.BaseController;
import presentation.controllers.ConfirmDialogController;

/**
 *      
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class Window extends Stage {

	protected Scene scene;
	protected BaseController controller;
	protected static String fxmlFolderPath;
	
	public Window(Parent root, String title){
		scene = new Scene(root);
		setScene(scene);
	}
	
	public Window(String fxml, String title){
		String path = fxmlFolderPath + fxml;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
			Parent root = loader.load();
			controller = loader.getController();
			scene = new Scene(root);
			setScene(scene);
			setTitle(title);
			initOwner(App.getMainStage());
			initModality(Modality.APPLICATION_MODAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Window fromFXML(String fxmlFile, String title) {
		String path = fxmlFolderPath + fxmlFile;
		try {
			FXMLLoader loader = new FXMLLoader(Window.class.getResource(path));
			Parent root = loader.load();
			
			return new Window(root, title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void setFXMLFolderPath(String FXMLFolderPath) {
		fxmlFolderPath = FXMLFolderPath;
	}
	
	public static String getFXMLFolderPath() {
		return fxmlFolderPath;
	}
	
	public BaseController getController() {
		return controller;
	}	

	/**
	 * When clicking close button of a stage we get an event,
	 * and we use this event to close this stage.
	 * We do that to prevent redundancy in Controller classes.
	 * @param event
	 */
	public static void closeWindow(ActionEvent event) {
		Scene scene = ((Button) event.getSource()).getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}

	public static void showErrorMessage(String message, String title) {
		FlashWindow errorMessage = new FlashWindow("messages/errorMessage.fxml", title);
		errorMessage.setMessage(message);
		errorMessage.show();
	}
	
	public static void showSuccessMessage(String message, String title) {
		FlashWindow errorMessage = new FlashWindow("messages/successMessage.fxml", title);
		errorMessage.setMessage(message);
		errorMessage.show();
	}
	
	/**
	 * This method uses to show dialog box to the user to confirm an action
	 * or cancel it.
	 * 
	 * @param String title
	 * @param String message
	 * @return boolean
	 */
	public static boolean showCinformDialog(String title, String message) {
		
		Window confirmDialog = new Window("messages/ConfirmationDialog.fxml", title);
		
		ConfirmDialogController controller = (ConfirmDialogController)confirmDialog.getController();
		
		controller.setMessage(message);
		confirmDialog.showAndWait();
		
		return controller.getUserAction();
	}
}

