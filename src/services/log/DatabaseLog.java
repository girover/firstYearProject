package services.log;

import app.App;
import entities.User;

/**
 * @author Majed Hussein Farhan - <b style="color:red">
 *         girover.mhf@gmail.com</b> -
 *         <a href="https://github.com/girover">Github</a>
 */
public class DatabaseLog extends BaseLog implements Logger {

	private LogRepository logRepo = new LogRepository();
	
	private static DatabaseLog instance = null;
	
	private DatabaseLog() {}
	
	public static DatabaseLog instance() {
		
		if(instance != null)
			return instance;
		
		instance = new DatabaseLog();
		
		return instance;
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
