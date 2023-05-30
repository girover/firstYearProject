package window;

import java.io.IOException;

import configs.Config;
import javafx.fxml.FXMLLoader;
import presentation.BaseController;

/**
 *      
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class Component {
	
	BaseController controller;
	Object component;
	
	public Component(String component) {
		String componentFile = Config.get("gui.components")+component;

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource(componentFile));
			this.component = fxmlLoader.load();
			controller = fxmlLoader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object get() {
		return component;
	}
	
	public BaseController getController() {
		return controller;
	}
}
