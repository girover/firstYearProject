package application;
	
import app.App;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.run(primaryStage);
	}
	
	@Override
	public void stop() {
		App.terminate();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
