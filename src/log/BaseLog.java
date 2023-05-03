package log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseLog {
	
	protected static final String INFORMATION = "Infromation";
	protected static final String ERROR = "Error";
	protected static final String WARNING = "Warning";
	protected static final String DEBUGGING = "Debugging";
	
	protected static BaseLog instance;

	protected String getCurrentDateTime() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
