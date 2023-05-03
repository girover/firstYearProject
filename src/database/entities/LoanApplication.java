package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the LoanApplication table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class LoanApplication extends Entity {
	
	
	private int id;
	private int customerID;
	private int salesPersonID;
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
		setTable("loanapplication");
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


	public int getSalesPersonID() {
		return salesPersonID;
	}


	public void setSalesPersonID(int salesPersonID) {
		this.salesPersonID = salesPersonID;
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
	public boolean makeFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			customerID = result.getInt("customerID");
			salesPersonID = result.getInt("salesPersonID");
			carID = result.getInt("carID");
			applicationDate = result.getString("applicationDate");
			loanAmount = result.getInt("loanAmount");
			payment = result.getFloat("payment");
			months = result.getInt("months");
			interestRate = result.getFloat("interestRate");
			monthlyPayment = result.getFloat("monthlyPayment");
			status = result.getString("status");
			note = result.getString("note");
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
