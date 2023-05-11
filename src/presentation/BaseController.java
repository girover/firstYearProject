package presentation;

import java.util.Observable;
import java.util.Observer;

import app.App;
import app.FormData;
import javafx.fxml.Initializable;
import translate.Translator;


/**
 * This class is a part of Presentation Layer. 
 * This abstract class provides a set of of methods that can be used
 * in derived classes.
 *
 *   
 * @version 1.0
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public abstract class BaseController extends Observable implements Observer, Initializable {

	/**
	 * The controller that makes request to this controller.
	 * It is important for transferring data between controllers. 
	 */
	protected BaseController sender;
	
	protected static Translator translator;
	
	public BaseController() {
		translator = App.getTranslator();
	}
	
	public String translate(String translateableText) {
		return translator.translate(translateableText);
	}
	
	public void showErrorMessage(String message, String title) {
		App.showErrorMessage(message, title);
	}
	
	public void showSuccessMessage(String message, String title) {
		App.showSuccessMessage(message, title);
	}
	
	public void flashErrorMessage(String message, String title) {
		App.flashErrorMessage(message, title);
	}
	
	public void flashSuccessMessage(String message, String title) {
		App.flashSuccessMessage(message, title);
	}
	
	public void setSenderController(BaseController controller) {
		sender = controller;
		addObserver(controller);
	}
	
	public void fire(Object arg) {
		setChanged();
		if(arg == null)
			notifyObservers();
		else
			notifyObservers(arg);
	}
}
