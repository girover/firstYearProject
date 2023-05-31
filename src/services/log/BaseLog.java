package services.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import app.App;
import entities.User;

public abstract class BaseLog {
	
	public static final String INFORMATION = "Infromation";
	public static final String ERROR = "Error";
	public static final String WARNING = "Warning";
	public static final String DEBUGGING = "Debugging";
	
	protected String getCurrentDateTime() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
