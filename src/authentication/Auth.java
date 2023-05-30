package authentication;

import database.entities.Entity;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 */
public abstract class Auth extends Entity {
	
	protected static String idField;
	protected static String idFieldType;
	protected static String passwordField;
	
	public static String getIdField() {
		return idField;
	}

	public static void setIdField(String _idField) {
		idField = _idField;
	}
	
	public static String getIdFieldType() {
		return idFieldType;
	}
	
	public static void setIdFieldType(String _idFieldType) {
		idFieldType = _idFieldType;
	}

	public static String getPasswordField() {
		return passwordField;
	}

	public static void setPasswordField(String _passwordField) {
		passwordField = _passwordField;
	}

	public abstract boolean login(String firstCredential, String secondCredential);
	
}
