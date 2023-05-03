package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import authentication.Auth;
import database.entities.Entity;
import database.entities.User;

public class UserRepository extends Repository {

	public UserRepository() {
		setTable("user");
	}

	/**
	 * Get user by its primaryKey's value
	 * 
	 * @param id : the id of the user
	 * @return User entity
	 */
	public User getById(int id) {

		User user = null;
//		String sql = "SELECT * FROM [" + table + "] WHERE [" + primaryKey + "] = ?";

		try {
//			ResultSet result = select(sql, id);
			ResultSet result = find(id);

			if (result.next()) {
				user = new User();
				user.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Get user by its primaryKey's value
	 * 
	 * @param id : the id of the user
	 * @return User entity
	 */
	public User getById(String id) {

		User user = null;

		try {
			ResultSet result = getByACondition(primaryKey, "=", id);

			if (result.next()) {
				user = new User();
				user.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Get user by its primaryKey's value
	 * 
	 * @param value : the id of the user
	 * @return User entity
	 */
	public User getByAuthenticationField(String field, String value) {

		User user = null;

		try {
			ResultSet result = getByACondition(field, "=", value);

			if (result.next()) {
				user = new User();
				user.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Delete the given user from database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

		return delete(sql, ((User) entity).getId());
	}

	/**
	 * Update the given user in database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {
		
		User user = (User)entity;
		
		String sql = "UPDATE [" +table+ "] SET [userName] = ?, [password] = ?, "
				   + "[employeeId] = ? "
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, user.getUserName(), user.getPassword(), user.getEmployeeId(), user.getId());
	}

	/**
	 * Insert the given user into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		User user = (User)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				+ "([employeeId], [userName], [password]) VALUES "
				+ "(?, ?, ?)";
		
		int id = insertAndGetGeneratedId(sql, user.getEmployeeId(), user.getUserName(), user.getPassword());
		
		if(id > 0) {
			user.setId(id);
			user.setExist(true);
			
			return id;
		}
		
		return 0;
	}

	/**
	 * Get first user in the database
	 * 
	 * @return User entity
	 */
//	public User getFirst() {
//		
//		User user = null;
//		String sql = "SELECT TOP 1 * FROM [" + table + "] ORDER BY " + primaryKey;
//		
//		try {
//			ResultSet result = select(sql);
//			while(result.next()) {
//				user = newUserFromResultSet(result);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return user;
//	}

	/**
	 * Get user by its email
	 * 
	 * @param id : the id of the user
	 * @return User
	 */
//	public User getUserByEmail(String email) {
//		
//		User user = null;
//		String sql = "SELECT * FROM [" + table + "] WHERE [email] = ?";
//		
//		try {
//			ResultSet result = select(sql, email);
//			
//			while(result.next()) {
//				user = newUserFromResultSet(result);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return user;
//	}

	/**
	 * Retrieve all users from database
	 * 
	 * @return ArrayList<User>
	 */
//	public ArrayList<User> getAllUsers(){
//		
//		ArrayList<User> users = new ArrayList<>();
//		String sql = "SELECT * FROM [" + getTable() + "] WHERE [role] <> ? ORDER BY [name]";
//		
//		try {
//			ResultSet result = select(sql, UserRole.ADMIN);
//			while(result.next()) {
//				users.add(newUserFromResultSet(result));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return users;
//	}

	/**
	 * Retrieve all users from database
	 * 
	 * @return ArrayList<User>
	 */
//	public ArrayList<User> getAllUsersWithAdmins(){
//		
//		ArrayList<User> users = new ArrayList<>();
//		String sql = "SELECT * FROM [" + getTable() + "] ORDER BY [name]";
//		
//		try {
//			ResultSet result = select(sql);
//			while(result.next()) {
//				users.add(newUserFromResultSet(result));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return users;
//	}
//	
//	public ArrayList<User> getAllUsersByAssignmentId(int assignmentId){
//		
//		ArrayList<User> users = new ArrayList<>();
//		String sql = "{call spGetAssignmentUsers(?)}";
//		
//		try {
//			ResultSet result = select(sql, assignmentId);
//			while(result.next()) {
//				users.add(newUserFromResultSet(result));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return users;
//	}

//	public ArrayList<User> getClientsHowHasNotAssignment(Assignment assignment){
//		
//		ArrayList<User> clients = new ArrayList<>();
//		String sql = "{call spGetClientsWhoHasNotAssignment(?)}";
//		
//		try {
//			ResultSet result = select(sql, assignment.getId());
//			while(result.next()) {
//				clients.add(newUserFromResultSet(result));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return clients;
//	}
//	
//	public ArrayList<User> getAllMembersOf(Team team){
//		
//		ArrayList<User> members = new ArrayList<>();
//		
//		String sql = "SELECT [user].* FROM [user] JOIN [teamMember]"
//				+ " On [user].id = [teamMember].userId"
//				+ " JOIN [team] ON [team].id = [teamMember].teamId"
//				+ " WHERE [team].id = ? ORDER BY [user].[name]";
//		
//		try {
//			ResultSet result = select(sql, team.getId());
//			while(result.next()) {
//				members.add(newUserFromResultSet(result));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return members;
//	}
//	
//	public ArrayList<User> getAllUsersNotIn(Team team){
//		
//		ArrayList<User> members = new ArrayList<>();
//		
//		String sql = "SELECT [user].* from [user] WHERE id NOT IN "
//					+ "(SELECT [user].id FROM [user] JOIN [teamMember]"
//					+ " ON [user].id = [teamMember].userId"
//					+ " JOIN [team] ON [team].id = [teamMember].teamId"
//					+ " WHERE [team].id = ?)"
//					+ " AND [user].role <> 'therapist'"
//					+ " ORDER BY [user].[name]";
//		
//		try {
//			ResultSet result = select(sql, team.getId());
//			while(result.next()) {
//				members.add(newUserFromResultSet(result));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return members;
//	}
//	
//	public ArrayList<User> getAllWhoHaveAnswered(Assignment assignment){
//		
//		ArrayList<User> users = new ArrayList<>();
//		
//		String sql = "SELECT [user].* from [user]"
//				   + " JOIN [assignmentAnswer]"
//				   + " ON [user].id = [assignmentAnswer].userId"
//				   + " JOIN [assignment]"
//				   + " ON [assignment].id = [assignmentAnswer].assignmentId"
//				   + " WHERE [assignment].id = ?"
//				   + " ORDER BY [user].[name]";
//		
//		try {
//			ResultSet result = select(sql, assignment.getId());
//			while(result.next()) {
//				users.add(newUserFromResultSet(result));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return users;
//	}
//	
//	public int insert(User user) {
//		
//		String sql = "INSERT INTO [" + table + "] "
//				+ "([name], [email], [phone], [address], [cpr], [role], [password]) VALUES "
//				+ "(?, ?, ?, ?, ?, ?, ?)";
//		
//		int id = executeInsertAndGetId(sql, user.getName(), user.getEmail()
//				, user.getPhone(), user.getAddress(), user.getCpr(), user.getRole(), user.getPassword());
//		
//		if(id > 0) {
//			user.setId(id);
//			return id;
//		}
//		
//		return 0;
//	}
//	
//	/**
//	 * Update the given user's data
//	 * @param user
//	 * @return boolean
//	 */
//	public boolean update(User user) {
//		
//		String sql = "UPDATE [" +table+ "] SET [name] = ?, email = ?, "
//				   + "phone = ?, [address] = ?, cpr = ?, [role] = ?, [password] = ? "
//				   + "WHERE " + primaryKey + " = ?";
//		
//		return executeUpdate(sql, user.getName(), user.getEmail(), user.getPhone(), 
//							 user.getAddress(), user.getCpr(), user.getRole(), 
//							 user.getPassword(), user.getId());
//	}
//	
//	/**
//	 * Delete the given User from database
//	 * @param user
//	 * @return boolean
//	 */
//	public boolean delete(User user) {
//		return delete("DELETE FROM [" + table + "] WHERE [" + primaryKey + "] = ?", user.getId());
//	}
//	
//	/**
//	 * create new instance of User from ResultSet, and 
//	 * fill its instance variables with data.
//	 * @param ResultSet result
//	 * @return User
//	 */
//	private User newUserFromResultSet(ResultSet result) {
//		
//		User user = null;
//		
//		try {
//			int id          = result.getInt(primaryKey);
//			String name     = result.getString("name");
//			String email    = result.getString("email");
//			String phone    = result.getString("phone");
//			String address  = result.getString("address");
//			String cpr      = result.getString("cpr");
//			String role     = result.getString("role");
//			String password = result.getString("password");
//			
//			user = new User(id, name, email, phone, address, cpr, role, password);
//			
//			assignTeamName(result, user);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return user;
//	}
//	
//	private void assignTeamName(ResultSet result, User user) {
//
//		try {
//			boolean columnFound = false;
//			ResultSetMetaData rsmd = result.getMetaData();
//		    int columns = rsmd.getColumnCount();
//		    for (int x = 1; x <= columns; x++) {
//		        if ("team".equals(rsmd.getColumnName(x))) {
//		            columnFound = true;
//		        }
//		    }
//			if(!columnFound)
//				return;
//			
//			user.setInTeam(result.getString("team"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
