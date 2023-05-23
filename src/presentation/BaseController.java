package presentation;

import java.util.Observable;
import java.util.Observer;

import app.App;
import app.FormData;
import app.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextFormatter;
import translate.Translator;
import window.Window;

/**
 * This class is a part of Presentation Layer. This abstract class provides a
 * set of of methods that can be used in derived classes.
 *
 * 
 * @version 1.0
 * 
 * @author Majed Hussen Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public abstract class BaseController extends Observable implements Observer, Initializable {

	/**
	 * The controller that makes request to this controller. It is important for
	 * transferring data between controllers.
	 */
	protected BaseController observerController;

	protected static Translator translator;

	public BaseController() {
		translator = App.getTranslator();
	}

	protected String translate(String translateableText) {
		return translator.translate(translateableText);
	}

	protected void showErrorMessage(String message, String title) {
		App.showErrorMessage(message, title);
	}

	protected void showSuccessMessage(String message, String title) {
		App.showSuccessMessage(message, title);
	}

	protected void flashErrorMessage(String message, String title) {
		App.flashErrorMessage(message, title);
	}

	protected void flashSuccessMessage(String message, String title) {
		App.flashSuccessMessage(message, title);
	}
	
	protected boolean showCinformDialog(String title, String message) {
		return Window.showCinformDialog(title, message);
	}

	public void setObserverController(BaseController controller) {
		observerController = controller;
		addObserver(controller);
	}

	protected void openWindow(String fxml, String title) {
		Window window = new Window(fxml, title);
		window.show();
	}

	/**
	 * To close the window when a button is clicked.
	 * 
	 * @param event
	 */
	protected void closeWindow(ActionEvent event) {
		Window.closeWindow(event);
	}

	protected void fire(Object arg) {
		setChanged();
		if (arg == null)
			notifyObservers();
		else
			notifyObservers(arg);
	}

	public static String formatNumber(int number) {
		return Helper.formatNumber(number);
	}

	public static String formatNumber(double number) {
		return Helper.formatNumber(number);
	}

	public static String formatCurrency(String number) {
		return Helper.formatCurrency(number);
	}

	public static String formatCurrency(double price) {
		return Helper.formatCurrency(price);

	}

	public static TextFormatter<String> allowOnlyDigits() {
		return Helper.allowOnlyDigits();
	}
}
