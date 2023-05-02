package window;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentation.BaseController;

public class Window extends Stage {

	protected Stage stage;
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
	
	public void setSenderController(BaseController controller) {
		getController().setSenderController(controller);
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
	
//	public void show() {
//		stage.show();
//	}
//	
//	public void showAndWait() {
//		stage.showAndWait();
//	}
//	
//	public void hide() {
//		stage.hide();
//	}
//	
//	public void close() {
//		stage.close();
//	}
}

