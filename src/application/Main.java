package application;
	
import app.App;
import faker.Faker;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
//		System.out.println(Faker.car());
		App.run(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
