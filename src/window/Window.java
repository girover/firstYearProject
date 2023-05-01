package window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.BaseController;

public class Window extends Stage {

	protected Stage stage;
	protected Scene scene;
	protected BaseController controller;
	protected static String fxmlFolderPath;
	
	public Window(Parent root, String title){
//		stage = new Stage();
		scene = new Scene(root);
//		stage.setScene(scene);
		setScene(scene);
	}
	
	public Window(String fxml, String title){
		String path = fxmlFolderPath + fxml;
		try {
//			stage = new Stage();
			FXMLLoader loader = new FXMLLoader(Window.class.getClassLoader().getResource(path));
			Parent root = loader.load();
			controller = loader.getController();
			scene = new Scene(root);
//			stage.setScene(scene);
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
	
	public BaseController getController() {
		return controller;
	}
	
	public void setSenderController(BaseController controller) {
		getController().setSenderController(controller);
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

