package business;

import app.App;
import authentication.Auth;
import database.entities.Employee;
import database.entities.User;
import database.repositories.EmployeeRepositroy;
import database.repositories.UserRepository;

/**
 * This class is a part of business logic layer.
 * It is responsible for authentication.
 *
 * @author Majed Hussein Farhan
 * @email girover.mhf@gmail.com
 * @see <a href="https://github.com/girover">https://github.com/girover</a>
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
	 * @param id
	 * @param passowrd
	 * @return
	 */
	public boolean login(String id, String passowrd) {
		
		UserRepository userRepo = new UserRepository();
		User user = null;
		
		user = userRepo.getByAuthenticationField(userIdField, id);
		
		if(user != null && user.getPassword().equals(passowrd)) {
			
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
		
	}
	
	/**
	 * Redirects the authenticated user to the appropriate dashboard 
	 * based on their assigned role.
	 * 
	 * @param String fxml
	 * @param String title
	 */
	private void redirectTo(String fxml, String title) {
		
	}
}
