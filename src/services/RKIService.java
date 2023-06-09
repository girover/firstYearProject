package services;

import java.util.Observable;
import java.util.Observer;

import com.ferrari.finances.dk.rki.CreditRator;

/**
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class RKIService extends Observable implements Runnable {

	public static final String A_RKI_RATE = "A";
	public static final String B_RKI_RATE = "B";
	public static final String C_RKI_RATE = "C";
	public static final String D_RKI_RATE = "D";
	
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
