package window;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import presentation.MessageController;

/**     
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */

public class FlashWindow extends Window{

	public FlashWindow(String fxml, String title) {
		super(fxml, title);
	}
	
	
	/**
	 * Used for showing Success or Error messages
	 * Those messages will disappear automatically after given seconds. 
	 * @param stage
	 * @param miliseconds
	 */
	private void closeAutomaticallyAfter(int seconds) {

		PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
		
		delay.setOnFinished(event -> this.close());
		delay.play();
	}
	
	public void setMessage(String message){
		MessageController mController = (MessageController)getController();
		mController.setMessage(message);
	}

	public void flash() {
		show();
		closeAutomaticallyAfter(2);
	}
	
	public static void flashErrorMessage(String message, String title) {
		FlashWindow errorMessage = instance("messages/flashErrorMessage.fxml", title);
		errorMessage.setMessage(message);
		errorMessage.flash();
	}
	
	public static void flashSuccessMessage(String message, String title) {
		FlashWindow errorMessage = instance("messages/flashSuccessMessage.fxml", title);
		errorMessage.setMessage(message);
		errorMessage.flash();
	}
	
	private static FlashWindow instance(String fxml, String title) {
		return new FlashWindow(fxml, title);
	}
}
