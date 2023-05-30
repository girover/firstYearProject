package services.log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.Employee;
import database.entities.Entity;
import database.entities.User;
import database.repositories.Repository;

public class LogRepository extends Repository {

	public LogRepository() {
		setTable("log");
	}

	@Override
	public boolean delete(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int add(Entity entity) {
		LogEntity logEntity = (LogEntity) entity;

		String sql = "INSERT INTO [" + table + "] " + "(" 
				+ "[userID], " 
				+ "[logType], " 
				+ "[logMessage]) " 
				+ "VALUES " + "(?, ?, ?)";

		int id = insertAndGetGeneratedId(sql, 
				logEntity.getUserId(), 
				logEntity.getLogType(), 
				logEntity.getLogMessage());

		if (id > 0) {
			logEntity.setId(id);
			logEntity.setExist(true);

			return id;
		}

		return 0;
	}

	@Override
	public LogEntity first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public LogEntity find(Object id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public LogEntity last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<LogEntity> getByCondition(String column, String operation, Object value) {
		return mapResultSetToEntityList(getRowsByACondition(column, operation, value));
	}

	@Override
	public ArrayList<LogEntity> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}

	@Override
	public ArrayList<LogEntity> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected LogEntity mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				LogEntity log = new LogEntity();
				log.mapFromResultSet(result);
				return log;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<LogEntity> mapResultSetToEntityList(ResultSet result) {
		ArrayList<LogEntity> logs = new ArrayList<>();
		
		try {
			while(result.next()) {
				LogEntity log = new LogEntity();
				log.mapFromResultSet(result);
				logs.add(log);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return logs;
	}

}
