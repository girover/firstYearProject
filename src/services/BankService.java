package services;

import java.util.Observable;
import java.util.Observer;

import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.rki.CreditRator;

import javafx.beans.InvalidationListener;

public class BankService extends Observable implements Runnable {
	
	private double interestRate;
	
	/**
	 * Constructor
	 * @param observer : Response will be sent to this observer controller.
	 */
	public BankService(Observer observer) {
		addObserver(observer);
	}

	/**
	 * Retrieves today's interest rate from the Bank API. 
	 * This method connects with the Bank API, 
	 * sends a request to retrieve the current interest rate information, 
	 * and returns the result to the observer controller.
	 */
	private void getResponse() {
    	interestRate = InterestRate.i().todaysRate();
    	setChanged();
		notifyObservers();
	}
	
	public void sendRequest() {
		Thread bankThread = new Thread(this);
		bankThread.start();
	}
	
	@Override
	public void run() {
		getResponse();
	}
	
	public double getInterestRate() {
		return interestRate;
	}

}
