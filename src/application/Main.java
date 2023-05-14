package application;
	
import app.App;
import database.entities.Car;
import database.entities.factory.Factory;
import database.repositories.CarRepository;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
//		System.out.println(Faker.car());
		App.run(primaryStage);
//		System.out.println(Factory.of(Car.class).make(1000));
//		CarRepository carRepo = new CarRepository();
//		int generatedRows = carRepo.addAll(Factory.of(Car.class).make(100));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
