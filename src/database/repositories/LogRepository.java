package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;


import database.entities.Entity;
import database.entities.Log;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the log table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class LogRepository extends Repository {
	
	
	public LogRepository() {
		setTable("log");
	}
	
	
	/**
	 * Get log by its id from database
	 * @param id
	 * @return
	 */
	public Log getById(int id) {
		Log log = null;
		
		try {
			ResultSet result = find(id);
			
			if(result.next()) {
				log = new Log();
				log.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return log;
	}
	

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

		return delete(sql, ((Log) entity).getId());
	}

	/**
	 * Updates the specified log in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {
		
		Log log = (Log)entity;
		
		String sql = "UPDATE [" +table+ "] SET "
				 + "[userID] = ?, "
				   + "[logType] = ?, "
				   + "[logMessage] = ?, "
				   + "[createdAt] = ?, "
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, 
				log.getUserID(), 
				log.getLogType(),
				log.getLogMessage(),
				log.getCreatedAt());
	}

	/**
	 * Insert the specified log into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		Log log = (Log)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				   + "("
				   + "[userID] = ?, "
				   + "[logType] = ?, "
				   + "[logMessage] = ?, "
				   + "[createdAt] = ?, "
				   + ") "
				   + "VALUES "
				   + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int id = insertAndGetGeneratedId(sql, 
				log.getUserID(), 
				log.getLogType(),
				log.getLogMessage(),
				log.getCreatedAt());
		
		if(id > 0) {
			log.setId(id);
			log.setExist(true);
			
			return id;
		}
		
		return 0;
	}
}
