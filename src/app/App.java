package app;

import java.sql.Connection;
import java.sql.SQLException;

import authentication.Auth;
import configs.Config;
import configs.XMLConfigsReader;
import database.connection.DatabaseConnection;
import database.connection.SqlServerDatabaseConnection;
import database.entities.Employee;
import database.entities.User;
import exception.ExceptionHandler;
import javafx.application.Platform;
import javafx.stage.Stage;
import translate.Translator;
import window.Window;

/**
 * This class is responsible for configuring various aspects of the application:
 * - Loading the configuration file
 * - Generating the database instance
 * - Setting up the authentication fields
 * - Setting up paths for GUI files and components
 * - etc..
 * By centralizing these configuration tasks within this class,
 * the application can easily initialize and set up its components 
 * and settings in a structured and organized manner.  
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class App {

	private static Connection dbConnection;
	private static Auth authenticatedUser;
	private static Stage mainStage;
	private static Translator translator;
	private static boolean booted = false;
	private static AppLocker appLocker;

	/**
	 * Start point for the application
	 * @param primaryStage
	 * @throws Exception 
	 */
	public static void run(Stage primaryStage) throws Exception {
		
		if(!initAppLocker())
			return;
		
		
		mainStage = primaryStage;
		
		boot();
		initExceptionHandler();
		loadConfigs();
		initWindowsPath();
		initAuthFields();
		initLocaleLanguage();
		initDatabaseConnection();
		
		if (authenticatedUser == null) {
			showLoginWindow();
		}
	}

	private static void boot() {
		booted = true;
	}

	/**
	 * We will check if the application is allowed to run.
	 * If application locker is released then we return true to allow running
	 * new instance, otherwise false is returned to prevent running other instances.
	 * @return
	 */
	private static boolean initAppLocker() {
		appLocker = new AppLocker();
		
		if(appLocker.isLocked()) {
			Platform.exit();
			return false;
		}
		
		appLocker.lock();
		
		return true;
	}

	private static void initDatabaseConnection() {
		dbConnection = getNewDatabaseConnection();
	}

	/**
	 * This method loads the translator responsible for translating the GUI 
	 * into multiple languages.
	 * Currently, the supported languages are English and Danish.
 	 * By calling this method, the translator is initialized, 
 	 * enabling the application to display the GUI in the desired language.
	 */
	private static void initLocaleLanguage() {
		translator = new Translator(Config.get("locale"));
	}

	/**
	 * This method configures the fields used for authentication in the User Entity.
	 * The authentication fields can be customized by providing the desired 
	 * field names as arguments to this method.
	 * By calling this method and specifying the appropriate field names, 
	 * the authentication process can be easily adjusted.
	 */
	private static void initAuthFields() {
		Auth.setIdField(Config.get("auth.idField"));
		Auth.setIdFieldType(Config.get("auth.idFieldType"));
		Auth.setPasswordField(Config.get("auth.passwordField"));
	}

	private static void initWindowsPath() {
		Window.setFXMLFolderPath(Config.get("gui.path"));
	}
	
	/**
	 * This method sets up an ExceptionHandler class to catch any uncaught exceptions 
	 * that occur in the JavaFX application.
	 * The ExceptionHandler class is responsible for displaying 
	 * the exception message in a JavaFX graphical window,
     * allowing for a user-friendly presentation of the error information.
 	 * By configuring this exception handling mechanism, the application ensures that 
 	 * any unhandled exceptions are properly captured and presented to the user 
 	 * in a convenient manner and will be saved in database.
	 */
	private static void initExceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler::handleUncaughtException);
	}
	
	private static void loadConfigs() throws Exception {
		Config.addAll(XMLConfigsReader.parse("src/configs/configs.xml"));
	}

	/**
	 * Get a new database connection from Data Access Layer.
	 * @param databaseName
	 */
	public static Connection getNewDatabaseConnection() {
		
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
				throw new IllegalArgumentException("Unexpected database server name: " + databaseServer);
		}
		
		return con.getConnection();
	}
	
	/**
	 * we configure the application to connect to a separate database 
	 * specifically used for testing purposes.
	 * When executing JUnit tests, the application will switch to connecting 
	 * to the testing database instead of the production database.
	 * This separation allows for isolated testing and ensures that 
 	 * tests do not interfere with the data in the application's main database.
	 */
	public static void setTestingDatabaseConnection() {
		
		String databaseDriver = Config.get("database.defaultConnection");
		
		if(databaseDriver.equals("sqlServer"))
			Config.set("database.sqlServer.dbName", Config.get("database.sqlServer.testDbName"));
		else
			Config.set("database.mysql.dbName", Config.get("database.mysql.testDbName"));
		
		dbConnection = getNewDatabaseConnection();
	}

	public static void showLoginWindow() {
		Window loginWindow = new Window("Login.fxml", "Login");
		setMainStage(loginWindow);
		loginWindow.show();
	}
	
	public static void showUserWindow(User user) {
		
		String role = user.getEmployee().getRole();
		
		if(role.equals(Employee.MANAGER))
			showAdminWindow();
		else if(role.equals(Employee.SELLER))
			showSellerWindow();
	}

	public static void showAdminWindow() {
		Window adminWindow = new Window("admin/AdminDashboard.fxml", "Manager Dashboard");
		getMainStage().setScene(adminWindow.getScene());
		getMainStage().setTitle("Manager Dashboard");
	}

	public static void showSellerWindow() {
		Window adminWindow = new Window("seller/SellerDashboard.fxml", "Seller Dashboard");
		getMainStage().setScene(adminWindow.getScene());
		getMainStage().setTitle("Seller Dashboard");
	}

	public static Connection getDBConnection() {
		if (dbConnection == null)
			initDatabaseConnection();

		return dbConnection;
	}

	public static Connection db() throws SQLException {
		return getDBConnection();
	}

	/**
	 * This method stores the authenticated user in the application when a user logs in.
	 * The "user" parameter represents the authenticated user object that will be stored.
	 * By calling this method after successful authentication, 
	 * the application keeps track of the currently logged-in user,
	 * allowing for personalized functionality and access control 
	 * based on the user's identity. 
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
	 * 
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
	 * Configuring application to run in test environment.
	 */
	public static void test() {
		runInTestEnvironment();
	}

	/**
	 * This method configures the application for the test environment.
	 * In the test environment, there is no need to launch any graphical objects 
 	 * or initialize the graphical user interface.
	 */
	public static void runInTestEnvironment() {
		try {
			loadConfigs();
			initAuthFields();
			setTestingDatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is responsible for terminating 
	 * the application and closing the database connection.
	 * When called, it performs the necessary cleanup tasks 
	 * to gracefully shut down the application and release any resources.
	 */
	public static void terminate() {
		
		/**
		 * If application is terminating after is has been booted
		 * if there is another instance of application running, this instance will
		 * not be booted, so the AppLocker will not be released.
		 */
		if(booted)
			appLocker.release();
		
		if(dbConnection != null)
			try {
				dbConnection.close();
				System.out.println("Terminating: Database connection is closed now.");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
