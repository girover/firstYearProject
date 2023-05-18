package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a part of Data Access Layer. This class represents a Data
 * Access Object for the LoanApplication table in the database.
 *
 * @version 1.0
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/entities/LoanApplication.java">Class Code On Github</a>
 */
public class LoanApplication extends Entity {

	private int id;
	private int customerID;
	private int sellerID;
	private int carID;
	private String applicationDate;
	private int loanAmount;
	private float payment;
	private int months;
	private float interestRate;
	private float monthlyPayment;
	private String status;
	private String note;

	public LoanApplication() {
		setTable("loanApplication");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getSellerID() {
		return sellerID;
	}

	public void sellerID(int salesPersonID) {
		this.sellerID = salesPersonID;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public float getPayment() {
		return payment;
	}

	public void setPayment(float payment) {
		this.payment = payment;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public float getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(float monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public boolean mapFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			customerID = result.getInt("customerID");
			sellerID = result.getInt("sellerID");
			carID = result.getInt("carID");
			applicationDate = result.getString("applicationDate");
			loanAmount = result.getInt("loanAmount");
			payment = result.getFloat("payment");
			months = result.getInt("months");
			interestRate = result.getFloat("interestRate");
			monthlyPayment = result.getFloat("monthlyPayment");
			status = result.getString("status");
			note = result.getString("note");
			applicationDate = result.getString("applicationDate");

			setExist(true);
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
