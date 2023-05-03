package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.entities.ApplicationAnswer;
import database.entities.Entity;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the ApplicationAnswer table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class ApplicationAnswerRepository extends Repository {
	
	
	public ApplicationAnswerRepository() {
		setTable("ApplicationAnswer");
	}
	
	
	/**
	 * Get loanApplication by its id from database
	 * @param id
	 * @return
	 */
	public ApplicationAnswer getById(int id) {
		ApplicationAnswer applicationAnswer = null;
		
		try {
			ResultSet result = find(id);
			
			if(result.next()) {
				applicationAnswer = new ApplicationAnswer();
				applicationAnswer.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return applicationAnswer;
	}
	

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

		return delete(sql, ((ApplicationAnswer) entity).getId());
	}

	/**
	 * Updates the specified loanApplication in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {
		
		ApplicationAnswer applicationAnswer = (ApplicationAnswer)entity;
		
		String sql = "UPDATE [" +table+ "] SET "
				   + "[applicationID] = ?, "
				   + "[userID] = ?, "
				   + "[answerDate] = ?, "
				   + "[accepted] = ?, "
				   + "[note] = ?, "
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, 
				applicationAnswer.getApplicationID(), 
				applicationAnswer.getUserID(), 
				applicationAnswer.getAnswerDate(),
				applicationAnswer.getAccepted(),
				applicationAnswer.getNote(),
				applicationAnswer.getId());
	}

	/**
	 * Insert the specified loanApplication into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		ApplicationAnswer applicationAnswer = (ApplicationAnswer)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				   + "("
				   + "[applicationID] = ?, "
				   + "[userID] = ?, "
				   + "[answerDate] = ?, "
				   + "[accepted] = ?, "
				   + "[note] = ?, "
				   + ") "
				   + "VALUES "
				   + "(applicationID, userID, answerDate, accepted, note)";
		
		int id = insertAndGetGeneratedId(sql, 
				applicationAnswer.getApplicationID(), 
				applicationAnswer.getUserID(), 
				applicationAnswer.getAnswerDate(),
				applicationAnswer.getAccepted(),
				applicationAnswer.getNote());
		
		
		if(id > 0) {
			applicationAnswer.setId(id);
			applicationAnswer.setExist(true);
			
			return id;
		}
		
		return 0;
	}
}
