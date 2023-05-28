package presentation;

import java.util.Observable;
import java.util.Observer;

import app.App;
import app.Helper;
import database.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextFormatter;
import translate.Translator;
import window.FlashWindow;
import window.Window;

/**
 * This abstract class is part of the Presentation Layer and provides a set of methods
 * that can be utilized by derived classes. It serves as a foundation for implementing
 * common functionality and behavior in the derived classes.
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public abstract class BaseController extends Observable implements Observer, Initializable {

	protected static Translator translator = App.getTranslator();

	protected String translate(String translateableText) {
		return translator.translate(translateableText);
	}

	protected void showErrorMessage(String message, String title) {
		Window.showErrorMessage(message, title);
	}

	protected void showSuccessMessage(String message, String title) {
		Window.showSuccessMessage(message, title);
	}

	protected void flashErrorMessage(String message, String title) {
		FlashWindow.flashErrorMessage(message, title);
	}

	protected void flashSuccessMessage(String message, String title) {
		FlashWindow.flashSuccessMessage(message, title);
	}
	
	protected boolean showConfirmDialog(String title, String message) {
		return Window.showCinformDialog(title, message);
	}

	protected void openWindow(String fxml, String title) {
		Window window = new Window(fxml, title);
		window.show();
	}
	
	protected BaseController openWindowAndGetController(String fxml, String title) {
		Window window = new Window(fxml, title);
		window.show();
		return window.getController();
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
	
	protected void fire() {
		fire(null);
	}
	
	protected User getAuthenticatedUser() {
		return App.getAuthenticatedUser();
	}

	public String formatNumber(int number) {
		return Helper.formatNumber(number);
	}

	public String formatNumber(double number) {
		return Helper.formatNumber(number);
	}

	public String formatCurrency(String number) {
		return Helper.formatCurrency(number);
	}

	public String formatCurrency(double price) {
		return Helper.formatCurrency(price);

	}

	public TextFormatter<String> allowOnlyDigits() {
		return Helper.allowOnlyDigits();
	}
}
