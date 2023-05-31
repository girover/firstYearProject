package services.log;

import java.io.FileWriter;
import java.io.IOException;

import app.App;
import app.configs.Config;

/**
 * @author Majed Hussein Farhan - <b style="color:red">
 *         girover.mhf@gmail.com</b> -
 *         <a href="https://github.com/girover">Github</a>
 */
public class FileLog extends BaseLog implements Logger {

	private String filePath;
	private FileWriter fileWriter;
	
	private static FileLog instance = null;

	private FileLog() {
		try {
			filePath = Config.get("log.file");
			fileWriter = new FileWriter(filePath, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static FileLog instance() {
		
		if(instance != null)
			return instance;
		
		instance = new FileLog();
		
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
			String user = App.getAuthenticatedUser() == null ? "unknown" : App.getAuthenticatedUser().getUserName();
			String msg = String.format("%s : %s : %s : User = %s\n", 
								type,
								getCurrentDateTime(),
								message,
								user
							);
			fileWriter.append(msg);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	private void close() {
		try {
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
