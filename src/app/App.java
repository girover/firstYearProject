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
import javafx.stage.Stage;
import translate.Translator;
import window.Window;

/**
 * This class is responsible for configuring the application
 * like loading configuration file, generating database instance,
 * setting up the authentication fields,
 * setting up paths for GUI files and components etc..  
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
		initDatabaseConnection();
		
		if (authenticatedUser == null) {
			showLoginWindow();
		}
	}

	private static void initDatabaseConnection() {
		dbConnection = getNewDatabaseConnection();
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
//			mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			
//			@Override
//			public void handle(WindowEvent arg0) {
//				try {
//					getDBConnection().close();
//					System.out.println("Connection to databsae is closed now.");
//				} catch (SQLException e) {
//					System.out.println("Faild to close database connection.");
//					e.printStackTrace();
//				}				
//			}
//		});
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
	 * Database that is used in testing is different from the application's database.
	 * Her we decide to start connecting to testing database
	 * when calling App.test() method
	 * @param databaseName
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
	 * The path to the GUI folder
	 * @return String
	 */
//	public static String getGuiPath() {
//		return guiPath;
//	}

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
		runInTestEnvironment();
	}

	/**
	 * Configure the application for the test environment
	 * There is no need for launching any graphical objects
	 */
	public static void runInTestEnvironment() {
		try {
			loadConfigs();
			initWindowsPath();
			initAuthFields();
			setTestingDatabaseConnection();
			
//			guiPath = Config.get("gui.path");
		} catch (Exception e) {
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
				System.out.println("Terminating: Database connection is closed now.");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
