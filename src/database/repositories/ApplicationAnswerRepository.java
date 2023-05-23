package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.ApplicationAnswer;
import database.entities.Entity;
import database.entities.User;

/**
 * This class is a part of Data Access Layer. A class that represents a
 * repository responsible for basic CRUD (Create, Read, Update, Delete)
 * operations on a database table [applicationAnswer].
 *
 * This class provides a set of methods that can be used to perform common
 * database operations, such as retrieving all records, retrieving a single
 * record by ID, updating a record, and deleting a record.
 *
 * @version 1.0
 * @author Rasmus Lysgaard Villadsen - <b style="color:red">
 *         mrmaklie@gmail.com</b> -
 *         <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussen Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 * @see <a href=
 *      "https://github.com/girover/firstYearProject/blob/main/src/database/repositories/ApplicationAnswerRepository.java">Class
 *      Code On Github</a>
 */
public class ApplicationAnswerRepository extends Repository {

	public ApplicationAnswerRepository() {
		setTable("ApplicationAnswer");
	}

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE [" + entity.getPrimaryKey() + "] = ?";

		return delete(sql, ((ApplicationAnswer) entity).getId());
	}

	/**
	 * Updates the specified loanApplication in the database with the latest
	 * changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {

		ApplicationAnswer applicationAnswer = (ApplicationAnswer) entity;

		String sql = "UPDATE [" + table + "] SET " + "[applicationID] = ?, " + "[emlpoyeeID] = ?, "
				+ "[answerDate] = ?, " + "[accepted] = ?, " + "[note] = ?, " + "WHERE [" + primaryKey + "] = ?";

		return update(sql, applicationAnswer.getApplicationID(), applicationAnswer.getEmployeeID(),
				applicationAnswer.getAnswerDate(), applicationAnswer.getAccepted(), applicationAnswer.getNote(),
				applicationAnswer.getId());
	}

	/**
	 * Insert the specified applicationAnswer into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {

		ApplicationAnswer applicationAnswer = (ApplicationAnswer) entity;

		String sql = "INSERT INTO [" + table + "] (" + "[applicationID]," + "[userID]," + "[answerDate],"
				+ "[accepted]," + "[note]) " + "VALUES (?, ?, ?, ?, ?)";

		int id = insertAndGetGeneratedId(sql, applicationAnswer.getApplicationID(), applicationAnswer.getEmployeeID(),
				applicationAnswer.getAnswerDate(), applicationAnswer.getAccepted(), applicationAnswer.getNote());

		if (id > 0) {
			applicationAnswer.setId(id);
			applicationAnswer.setExist(true);

			return id;
		}

		return 0;
	}

	@Override
	public ApplicationAnswer first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public ApplicationAnswer find(int id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public ApplicationAnswer last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<ApplicationAnswer> getByCondition(String column, String operation, Object value) {
		return mapResultSetToEntityList(getRowsByACondition(column, operation, value));
	}

	@Override
	public ArrayList<ApplicationAnswer> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}

	@Override
	public ArrayList<ApplicationAnswer> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected ApplicationAnswer mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				ApplicationAnswer applicationAnswer = new ApplicationAnswer();
				applicationAnswer.mapFromResultSet(result);
				return applicationAnswer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<ApplicationAnswer> mapResultSetToEntityList(ResultSet result) {
		ArrayList<ApplicationAnswer> applicationAnswers = new ArrayList<>();

		try {
			while (result.next()) {
				ApplicationAnswer applicationAnswer = new ApplicationAnswer();
				applicationAnswer.mapFromResultSet(result);
				applicationAnswers.add(applicationAnswer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return applicationAnswers;
	}
}
