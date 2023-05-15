package application;
	
import app.App;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.run(primaryStage);
//		CustomerService s = new CustomerService();
//		System.out.println(s.find(1000));;
//		System.out.println(Faker.car());
//		System.out.println(Factory.of(Car.class).make(1000));
//		CarRepository carRepo = new CarRepository();
//		int generatedRows = carRepo.addAll(Factory.of(Car.class).make(100));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
