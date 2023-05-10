package app;

import java.sql.Connection;
import java.sql.SQLException;

import ExceptionHandler.ExceptionHandler;
import authentication.Auth;
import configs.Config;
import configs.XMLConfigsReader;
import database.connection.DatabaseConnection;
import database.connection.SqlServerDatabaseConnection;
import database.entities.User;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import translate.Translator;
import window.FlashWindow;
import window.Window;

public class App {

	private static Connection dbConnection;
	private static Auth authenticatedUser;
	private static Stage mainStage;
	private static String guiPath;
	private static Translator translator;

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
		initAuthFields();
		initLocaleLanguage();
		newDatabaseConnection();
		
		guiPath = Config.get("gui.path");
		
		
		Window window = new Window("customer/NewCustomer.fxml", "Add New Customer");
		window.show();
		
		if (authenticatedUser == null) {
//			showLoginWindow();
		}
	}

	private static void initLocaleLanguage() {
		translator = new Translator(Config.get("locale"));
	}

	private static void initAuthFields() {
		Auth.setIdField(Config.get("auth.idField"));
		Auth.setIdFieldType(Config.get("auth.idFieldType"));
		Auth.setPasswordField(Config.get("auth.passwordField"));
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
	public static void newDatabaseConnection() {
		
		DatabaseConnection con = null;
		
		String databaseServer = Config.get("database.defaultConnection");
		
		switch (databaseServer) {
		case "sqlServer": {
			con = new SqlServerDatabaseConnection();
			break;
		}
		case "mysql":{
			con = new SqlServerDatabaseConnection();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + databaseServer);
		}
		
		dbConnection = con.getConnection();
	}
	
	/**
	 * Database that is used in testing is different from the application's database.
	 * Her we decide to start connecting to testing database
	 * when calling App.test() method
	 * @param databaseName
	 */
	public static void setTestingDatabaseConnection() {
		String databaseDriver = Config.get("database.defaultConnection");
		if(databaseDriver.equals("sqlserver"))
			Config.set("database.sqlserver.dbName", Config.get("database.sqlserver.testDbName"));
		else
			Config.set("database.mysql.dbName", Config.get("database.mysql.testDbName"));
		
		newDatabaseConnection();
	}

	public static void showLoginWindow() {
		Window loginWindow = new Window("login.fxml", "User Login");
		setMainStage(loginWindow);
		loginWindow.show();
	}
	
	public static void showUserWindow(User user) {
		String role = user.getEmployee().getRole();
		
		if(role.equals("admin"))
			showAdminWindow();
		else if(role.equals("seller"))
			showSellerWindow();
	}

	public static void showAdminWindow() {
		Window adminWindow = new Window("admin/dashboard.fxml", "Admin Dashboard");
		adminWindow.show();
	}

	public static void showSellerWindow() {
		Window adminWindow = new Window("seller/dashboard.fxml", "Seller Dashboard");
		adminWindow.show();
	}

	public static Connection getDBConnection() {
		if (dbConnection == null)
			newDatabaseConnection();

		return dbConnection;
	}
	
	public static void flashErrorMessage(String message, String title) {
		FlashWindow.flashErrorMessage(message, title);
	}
	
	public static void flashSuccessMessage(String message, String title) {
		FlashWindow.flashSuccessMessage(message, title);
	}
	
	public static void showErrorMessage(String message, String title) {
		Window.showErrorMessage(message, title);
	}
	
	public static void showSuccessMessage(String message, String title) {
		Window.showSuccessMessage(message, title);
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
		return (User)authenticatedUser;
	}

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
	
	public static void setTranslator(Translator translatorInstance) {
		translator = translatorInstance;
	}
	
	public static Translator getTranslator() {
		return translator;
	}
	
	/**
	 * We have implemented a small UnitTest for testing our classes that are
	 * responsible for dealing with our [data access layer] classes.
	 */
	public static void test() {
		//Test test = new Test();
		//test.run();
		runForTest();
	}

	private static void runForTest() {
		try {
//			initExceptionHandler();
			loadConfigs();
//			initOnCloseApplication();
			initWindowsPath();
			initAuthFields();
//			initLocaleLanguage();
			setTestingDatabaseConnection();
			
			guiPath = Config.get("gui.path");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
