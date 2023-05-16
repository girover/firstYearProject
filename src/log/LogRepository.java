package log;

import database.entities.Employee;
import database.entities.Entity;
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
	public Entity first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
