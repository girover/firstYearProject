package exceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExceptionHandler {
	
	private static Stage owner;
	private static String pathToExceptionFXMLFile = "ExceptionWindow.fxml";

	public static void handleUncaughtException(Thread thread, Throwable throwable){
		try {
			// Create a new stage to display the exception details
			Stage exceptionStage = new Stage();
			exceptionStage.setTitle("Exception Details");
			exceptionStage.initModality(Modality.APPLICATION_MODAL);
			exceptionStage.initOwner(owner);
			
			FXMLLoader loader = new FXMLLoader(ExceptionHandler.class.getResource(pathToExceptionFXMLFile));
			Parent root = loader.load();
			System.out.println("here");
			Scene scene = new Scene(root);
			exceptionStage.setScene(scene);
			exceptionStage.show();
			
			StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        throwable.printStackTrace(pw);
	        String stackTrace = sw.toString();
	        
	        ExceptionHandlerController controller = loader.getController();
			controller.writeExceptionDeatails(stackTrace);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setOwner(Stage ownerStage) {
		owner = ownerStage;
	}
}
