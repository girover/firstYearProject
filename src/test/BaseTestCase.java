package test;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import app.App;
import junit.framework.TestCase;

/**
 * This abstract class is the base test case class. All test classes should
 * extend this abstract class. This class is responsible for initializing test
 * environment.
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
		 * This piece of code will run only one time when the class is loaded to memory.
		 */
		App.runInTestEnvironment();
		System.out.println("Application Testing Environment Is Configured Now.");
	};

	/**
	 * This method is called once before all methods in this test class.
	 */
	@BeforeAll
	public static void setUpApplication() {
		System.out.println("BeforeAll is called.");
	}

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
		System.out.println("TearDown is called.");
		
		// Truncating database tables after each test method.
		truncateDatabaseTables();
	}
	
	/**
	 * This method will truncate all tables in the database.
	 */
	private void truncateDatabaseTables() {
		
		String sql = "" 
				+ "TRUNCATE TABLE [log];"
				+ "TRUNCATE TABLE [carImage];"
				+ "TRUNCATE TABLE [car];"
				+ "TRUNCATE TABLE [sellerApprovalLimit];"
				+ "TRUNCATE TABLE [applicationAnswer];"
				+ "TRUNCATE TABLE [loanApplication];"
				+ "TRUNCATE TABLE [customer];"
				+ "TRUNCATE TABLE [user];"
				+ "TRUNCATE TABLE [employee];";
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