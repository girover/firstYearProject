package application;
	
import app.App;
import app.Helper;
import javafx.application.Application;
import javafx.stage.Stage;
import services.HashingService;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.run(primaryStage);
		System.out.println(Helper.formatNumber(1525252.123222));
	}
	
	@Override
	public void stop() {
		App.terminate();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
