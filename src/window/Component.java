package window;

import java.io.IOException;

import configs.Config;
import javafx.fxml.FXMLLoader;
import presentation.BaseController;

public class Component {
	
//	private static String pathToComponents = Config.get("gui.path")+"component/";
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
