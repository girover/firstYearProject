package test;

import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import app.App;
import junit.framework.TestCase;

/**
 * This abstract class serves as the base test case for 
 * all test classes to extend from. 
 * Its primary responsibility is to setup the test environment.
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public abstract class BaseTestCase extends TestCase {

	static {
		/**
		 * SetUp the application environment for testing. 
		 * example: loading application's configurations, preparing testing database etc.
		 * 
		 * This piece of code will run only once when the class is loaded to memory.
		 */
		System.out.println("Setting up test environment...");
		App.runInTestEnvironment();
		System.out.println("Testing environment ready.");
	};

	/**
	 * This method is invoked before every method in the test is invoked.
	 */
	@BeforeEach
	public void setUp() {
		System.out.println("BeforeEach is called");
	}
	
	/**
	 * This method is invoked after all methods in test classes.
	 */
	@AfterEach
	public void tearDown() {		
		// Truncating database tables after each test method.
		truncateDatabaseTables();
	}
	
	@AfterAll
	public static void afterAll() {

	}
	
	/**
	 * This method will truncate all tables in the database.
	 * Here we use built in stored procedure 'sp_MSforeachtable'
	 */
	private void truncateDatabaseTables() {
		
		String sql = "EXEC sp_MSforeachtable 'DELETE FROM ?';";
		
		Statement stm;
		
		try {
			stm = App.getDBConnection().createStatement();
			stm.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}