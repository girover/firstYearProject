package authentication;

import java.sql.ResultSet;

import database.entities.Entity;

public abstract class Auth extends Entity {
	public abstract boolean login(String firstCredential, String secondCredential);
	
}
