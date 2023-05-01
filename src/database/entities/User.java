package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import authentication.Auth;
import authentication.Authenticatable;

public class User extends Auth implements Authenticatable{

	private int id;
	private String name;
	private String email;
	private String password;
	
	@Override
	public void login(Authenticatable user) {

		
	}

	@Override
	public boolean makeFromResultSet(ResultSet result) {
		
		try {
			id = result.getInt("id");
			name = result.getString("name");
			email = result.getString("email");
			password = result.getString("password");
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean login(String firstCredential, String secondCredential) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
