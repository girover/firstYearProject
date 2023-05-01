package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import ExceptionHandler.ExceptionHandler;
import authentication.Auth;
import configs.Config;
import configs.XMLConfigsReader;
import database.connection.SqlServerDatabaseConnection;
import database.entities.User;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import presentation.MessageController;
import window.FlashWindow;
import window.Window;

public class App {

	private static Connection dbConnection;
	private static Auth authenticatedUser;
	private static Stage mainStage;
//	private static String guiPath = "gui/";
	private static String guiPath;

	/**
	 * Start point for the application
	 * @param primaryStage
	 * @throws Exception 
	 */
	public static void run(Stage primaryStage) throws Exception {

		mainStage = primaryStage;
		
		initExceptionHandler();
		loadConfigs();
		initOnCloseApplication();
		initWindowsPath();
		newDatabaseConnection("sql_injection");
		guiPath = Config.get("gui.path");
		
		
//		if (authenticatedUser == null) {
//			showLoginWindow();
//		}
		
		Window loading = new Window("loading.fxml", "Loading Page");
		loading.show();
//		showWindow("loading.fxml");
		
	}

	private static void initWindowsPath() {
		Window.setFXMLFolderPath(Config.get("gui.path"));
	}

	/**
	 * When closing the application,
	 * we should close the database connection
	 */
	private static void initOnCloseApplication() {
			mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				try {
					getDBConnection().close();
					System.out.println("Connection to databsae is closed now.");
				} catch (SQLException e) {
					System.out.println("Faild to close database connection.");
					e.printStackTrace();
				}				
			}
		});
	}
	
	private static void initExceptionHandler() {
		ExceptionHandler.setOwner(mainStage);
		Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler::handleUncaughtException);
	}
	
	private static void loadConfigs() throws Exception {
		Config.addAll(XMLConfigsReader.parse("src/configs/configs.xml"));
	}

	/**
	 * Get a new database connection from Data Access Layer.
	 * @param databaseName
	 */
	private static void newDatabaseConnection(String databaseName) {
		SqlServerDatabaseConnection con = new SqlServerDatabaseConnection(databaseName);
		dbConnection = con.getConnection();
	}
	
	/**
	 * Database that is used in testing is different from the application's database.
	 * Her we decide to start connecting to testing database
	 * when calling App.test() method
	 * @param databaseName
	 */
	public static void setTestingDatabaseConnection(String databaseName) {
		newDatabaseConnection(databaseName);
	}

	public static void showWindow(String sceneName) {
		Stage stage = new Stage();
		setScene(stage, sceneName);
	}

	public static void showOnTopWindow(String sceneName) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(getMainStage());
		setScene(stage, sceneName);
	}

	public static void showOnTopWindow(Parent parent) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(getMainStage());
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}

	public static void showAndWait(String sceneName) {
		try {
			String scenePath = guiPath + sceneName;
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(getMainStage());
			Parent parent = FXMLLoader.load(App.class.getClassLoader().getResource(scenePath));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void showAndWait(Parent parent) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(App.getMainStage());
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.showAndWait();
	}

	public static void showErrorMessqeWindow(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(
					App.class.getClassLoader().getResource(guiPath + "messages/ErrorMessage.fxml"));
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

	public static void showSuccessMessqeWindow(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(
					App.class.getClassLoader().getResource(guiPath + "messages/SuccessMessage.fxml"));
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

	public static void showLoginWindow() {
		setScene(mainStage, "Login.fxml");
	}

	public static void showTherapistWindow() {
		setScene(mainStage, "admin/TherapistDashboard.fxml");
	}

	public static void showStudentWindow() {
		setScene(mainStage, "user/StudentDashboard.fxml");
	}

	public static void showClientWindow() {
		setScene(mainStage, "user/ClientDashboard.fxml");
	}

	private static void setScene(Stage stage, String sceneName) {

		String scenePath = guiPath + sceneName;

		try {
			Parent parent = FXMLLoader.load(App.class.getClassLoader().getResource(scenePath));

			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("path error: " + scenePath);
		}
	}

	public static URL getScene(String fxmlFile) {

		try {

			URL fileUrl = App.class.getResource("/" + guiPath + fxmlFile);
			if (fileUrl == null) {
				throw new FileNotFoundException();
			}

			return fileUrl;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getDBConnection() {
		if (dbConnection == null)
			newDatabaseConnection("resono");

		return dbConnection;
	}

	public static Connection db() {
		return getDBConnection();
	}

	/**
	 * The path to the GUI folder
	 * @return String
	 */
	public static String getGuiPath() {
		return guiPath;
	}

	/**
	 * When a user get logged in, we store this authenticated user
	 * in the application. 
	 * @param user
	 */
	public static void setAuthenticatedUser(User user) {
		authenticatedUser = user;
	}

	public static User getAuthenticatedUser() {
//		return authenticatedUser;
		return null;
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

	/**
	 * Used for showing Success or Error messages
	 * Those messages will disappear automatically after given seconds. 
	 * @param stage
	 * @param seconds
	 */
	private static void closeWindowAutomatically(Stage stage, int seconds) {

		PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
		delay.setOnFinished(event -> stage.close());
		delay.play();
	}

	/**
	 * When assigning an assignment to a user, the application will
	 * send an email to this user.
	 * The process of sending email will be in background by using Threads.
	 * @param user
	 * @param assignment
	 */
//	public static void sendNotificationTo(User user, Assignment assignment) {
// 
//		SendEmailThread thread = new SendEmailThread(user, assignment);
//		thread.start();
//	}

	/**
	 * When assigning an assignment to a team, the application will
	 * send an email to all students in this team.
	 * @param team
	 * @param assignment
	 */
//	public static void sendNotificationToAll(Team team, Assignment assignment) {
//		
//		UserRepository userRepo = new UserRepository();
//		ArrayList<User> members =  userRepo.getAllMembersOf(team);
//		
//		for (User user : members) {
//			(new SendEmailThread(user, assignment)).start();
//		}
//	}

	/**
	 * The main window of application.
	 * @return
	 */
	public static Stage getMainStage() {
		return mainStage;
	}
	
	/**
	 * We can use this method in the Testing to specific window. 
	 * @param stage
	 */
	public static void setMainStage(Stage stage) {
		mainStage = stage;
	}
	
	/**
	 * We have implemented a small UnitTest for testing our classes that are
	 * responsible for dealing with our [data access layer] classes.
	 */
	public static void test() {
		//Test test = new Test();
		//test.run();
	}

	/**
	 * Terminating the application.
	 * Closing database connection.
	 */
	public static void terminate() {
		if(dbConnection != null)
			try {
				dbConnection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
