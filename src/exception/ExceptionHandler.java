package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import window.Window;

/**
 * This class used to catch any uncaught exceptions 
 * that occur in the JavaFX application.
 * The ExceptionHandler class is responsible for displaying 
 * the exception message in a JavaFX graphical window,
 * allowing for a user-friendly presentation of the error information and storing 
 * the exception message in a file or database.
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class ExceptionHandler {
	

	public static void handleUncaughtException(Thread thread, Throwable throwable){
		try {
			Window exceptionWindow = new Window("ExceptionWindow.fxml", "Exception Handler");
			ExceptionHandlerController controller = (ExceptionHandlerController)exceptionWindow.getController();
			exceptionWindow.show();
			
			StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        throwable.printStackTrace(pw);
	        String stackTrace = sw.toString();
	        
			controller.writeExceptionDeatails(stackTrace);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
