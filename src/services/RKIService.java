package services;

import java.util.Observable;
import java.util.Observer;

import com.ferrari.finances.dk.rki.CreditRator;

import javafx.beans.InvalidationListener;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class RKIService extends Observable implements Runnable {
	
	private String cpr;
	private String rate;
	
	public RKIService(Observer observer) {
		addObserver(observer);
	}

	/**
	 * Connects with the RKI API to perform a CPR number credit check. 
	 * This method sends a request to the RKI API using the provided CPR number,
	 * retrieves the credit information, and returns the result to the observer controller.
	 */
	private void getResponse() {
    	rate = CreditRator.i().rate( cpr ).toString();
    	setChanged();
		notifyObservers();
	}
	
	public void sendRequest(String CPR) {
		
		this.cpr = CPR;
		
		Thread rkiThread = new Thread(this);
    	rkiThread.start();
	}
	
	@Override
	public void run() {
		getResponse();
	}
	
	public String getRate() {
		return rate;
	}

}
