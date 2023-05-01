package window;

import java.io.IOException;

import app.App;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import presentation.MessageController;

public class WindowFactory {
	
	Stage mainStage;
	String fxmlFolderPath;
	
	public WindowFactory(Stage mainWindow, String fxmlFolderPath){
		this.mainStage = mainWindow;
		this.fxmlFolderPath = fxmlFolderPath; 
	}

	public void show(String sceneName) {
		Stage stage = newStage();
		setScene(stage, sceneName);
	}

	public void show(Parent parent) {
		Stage stage = newStage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
	
	public void showAndWait(String sceneName) {
		try {
			String scenePath = fxmlFolderPath + sceneName;
			Stage stage = newStage();
			Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(scenePath));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showAndWait(Parent parent) {
		Stage stage = newStage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	private Stage newStage() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(mainStage);
		
		return stage;
	}
	
	public void errorMessqe(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(
					App.class.getClassLoader().getResource(fxmlFolderPath + "messages/ErrorMessage.fxml"));
			Parent parent = loader.load();

			MessageController controller = loader.getController();
			controller.setMessage(message);

			Stage stage = new Stage();
			stage.initOwner(App.getMainStage());
			Scene scene = new Scene(parent);
			stage.setScene(scene);

			stage.show();
			
			closeWindowAutomatically(stage, 2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showSuccessMessqeWindow(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(
					App.class.getClassLoader().getResource(fxmlFolderPath + "messages/SuccessMessage.fxml"));
			Parent parent = loader.load();
			MessageController controller = loader.getController();
			controller.setMessage(message);
			Stage stage = new Stage();
			stage.initOwner(App.getMainStage());
			Scene scene = new Scene(parent);
			stage.setScene(scene);

			stage.show();
			
			closeWindowAutomatically(stage, 2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used for showing Success or Error messages
	 * Those messages will disappear automatically after given seconds. 
	 * @param stage
	 * @param seconds
	 */
	private void closeWindowAutomatically(Stage stage, int seconds) {

		PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
		delay.setOnFinished(event -> stage.close());
		delay.play();
	}
	
	private void setScene(Stage stage, String sceneName) {

		String scenePath = fxmlFolderPath + sceneName;

		try {
			Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(scenePath));

			Scene scene = new Scene(parent);

			stage.setScene(scene);
			
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("path error: " + scenePath);
		}
	}
}
