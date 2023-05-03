package log;

import app.App;
import database.entities.User;

public class DatabaseLog extends BaseLog implements Logger {

	private LogRepository logRepo = new LogRepository();
	
	private DatabaseLog() {}
	
	public static DatabaseLog instance() {
		if(instance != null)
			return (DatabaseLog)instance;
		
		return new DatabaseLog();
	}
	
	@Override
	public void error(String message) {
		register(ERROR, message);
	}

	@Override
	public void warning(String message) {
		register(WARNING, message);
	}

	@Override
	public void information(String message) {
		register(INFORMATION, message);
	}

	@Override
	public void debugging(String message) {
		register(DEBUGGING, message);
	}
	
	private void register(String type, String message) {

		try {
			User user = App.getAuthenticatedUser();
			int userId = user != null ? user.getId() : 0;
			
			LogEntity logEntity = new LogEntity();
			logEntity.setLogType(type);
			logEntity.setLogMessage(message);
			logEntity.setUserId(userId);
//			logEntity.setCreatedAt(getCurrentDateTime());
			
			logRepo.add(logEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			close();
		}
	}

}
