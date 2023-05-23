package application;
	
import app.App;
import app.Helper;
import javafx.application.Application;
import javafx.stage.Stage;
import services.HashingService;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(HashingService.secureHash("1234"));
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
