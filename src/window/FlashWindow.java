package window;

import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import presentation.MessageController;

public class FlashWindow extends Window{

	public FlashWindow(String fxml, String title) {
		super(fxml, title);
	}
	
	
	/**
	 * Used for showing Success or Error messages
	 * Those messages will disappear automatically after given milliseconds. 
	 * @param stage
	 * @param miliseconds
	 */
//	private void closeAutomatically(Stage stage, int seconds) {
	private void closeAutomaticallyAfter(int milliseconds) {

		PauseTransition delay = new PauseTransition(Duration.seconds(milliseconds));
//		delay.setOnFinished(event -> stage.close());
		delay.setOnFinished(event -> this.close());
		delay.play();
	}
	
	public void setMessage(String message){
		MessageController mController = (MessageController)getController();
		mController.setMessage(message);
	}
	
//	@Override
//	public void show() {
//		stage.show();
//		closeAutomatically(stage, 2);
//	}
	public void flash() {
		show();
		closeAutomaticallyAfter(2000);
	}
}
