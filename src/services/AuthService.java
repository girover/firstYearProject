package services;

import app.App;
import app.FormData;
import authentication.Auth;
import database.entities.Employee;
import database.entities.User;
import database.repositories.EmployeeRepositroy;
import database.repositories.UserRepository;

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

			EmployeeRepositroy employeeRepo = new EmployeeRepositroy();
			Employee employee = employeeRepo.getById(user.getEmployeeId());

			user.setEmployee(employee);

			App.setAuthenticatedUser(user);
//			App.showUserWindow(user);

			return true;
		}

		return false;
	}

	/**
	 * This method will Logs out the currently authenticated user.
	 */
	public void logout() {
//		App.setAuthenticatedUser(null);
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