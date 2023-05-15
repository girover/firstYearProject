package services;

import app.App;
import app.FormData;
import authentication.Auth;
import database.entities.Employee;
import database.entities.User;
import database.repositories.EmployeeRepository;
import database.repositories.UserRepository;
import log.Log;

/**
 * This class is a part of Service Layer (Business Logic Layer). It is
 * responsible for authentication.
 *
 * @author Majed Hussein Farhan - <b style="color:red">
 *         girover.mhf@gmail.com</b> -
 *         <a href="https://github.com/MrMaklie">Github</a>
 */
public class AuthService {

	private String userIdField;
	private String userIdFieldType;
	private String userPasswordField;

	public AuthService() {
		userIdField = Auth.getIdField();
		userIdFieldType = Auth.getIdFieldType();
		userPasswordField = Auth.getPasswordField();
	}

	/**
	 * Log the User in the system using the provided credentials.
	 * 
	 * @param userID
	 * @param userPassowrd
	 * @return
	 */
	public boolean login(FormData formData) {

		UserRepository userRepo = new UserRepository();
		User user = null;

		user = userRepo.getByAuthenticationField(userIdField, (String)formData.input("userId"));

		if (user != null && user.getPassword().equals((String)formData.input("userPassword"))) {

			EmployeeRepository employeeRepo = new EmployeeRepository();
			Employee employee = employeeRepo.getById(user.getEmployeeId());

			user.setEmployee(employee);

			App.setAuthenticatedUser(user);
			
			Log.information("Successfully logged in to the system using credentials.");
			
			return true;
		}
		
		Log.information("Failed to log in to the system with credentials.");

		return false;
	}

	/**
	 * This method will Logs out the currently authenticated user.
	 */
	public static void logout() {
		App.setAuthenticatedUser(null);
		App.getMainStage().close();
		App.showLoginWindow();
	}

	/**
	 * Redirects the authenticated user to the appropriate dashboard based on their
	 * assigned role.
	 * 
	 * @param String fxml
	 * @param String title
	 */
	private void redirectTo(String fxml, String title) {

	}
}
