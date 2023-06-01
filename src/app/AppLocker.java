package app;

import java.io.File;
import java.io.IOException;

/**
 * This class implements a mechanism to prevent multiple 
 * instances of the application from running simultaneously.
 * It utilizes a lock file to check if an instance of the application is already running.
 * If the lock file exists, it indicates that another instance is running, 
 * and the application will not start.
 * If the lock file does not exist, the application will proceed to run. 
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 */
public class AppLocker {

	private static final String LOCK_FILE = ".application.lock";
	private File file;
	
	public AppLocker(){
		file = new File(LOCK_FILE);
	}
	
	/**
	 * This method checks if there is an instance of application running.
	 * 
	 * @return boolean
	 */
	public boolean isLocked() {
		
		return file.exists();
	}
	
	/**
	 * Here we create a lock file to prevent the application to run multiple.
	 */
	public void lock() {
		try {
			boolean created = file.createNewFile();
			if(created) {
				System.out.println("lock file is created.");
				file.deleteOnExit();
			}
			else
				System.out.println("lock file already exists.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Here we delete the lock file to allow the next running.
	 */
	public void release() {
		if(file.exists())
			file.delete();
	}
}
