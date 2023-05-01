package presentation;

import java.util.Observable;
import java.util.Observer;

public abstract class BaseController extends Observable implements Observer {

	/**
	 * The controller that makes request to this controller.
	 * It is important for transferring data between controllers. 
	 */
	protected BaseController sender;
	
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
