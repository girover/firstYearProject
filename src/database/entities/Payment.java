package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the Payment table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class Payment extends Entity {
	
	private int id;
	private int applicationID;
	private String paymentDate;
	private float amount;
	private float principal;
	private float interest;
	private float remainingBalance;
	private float lateFee;
	private String paymentMethod;
	private String note;
	private String createdAt;
	private String updatedAt;
	
	
	public Payment() {
		setTable("payment");
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getApplicationID() {
		return applicationID;
	}


	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}


	public String getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public float getPrincipal() {
		return principal;
	}


	public void setPrincipal(float principal) {
		this.principal = principal;
	}


	public float getInterest() {
		return interest;
	}


	public void setInterest(float interest) {
		this.interest = interest;
	}


	public float getRemainingBalance() {
		return remainingBalance;
	}


	public void setRemainingBalance(float remainingBalance) {
		this.remainingBalance = remainingBalance;
	}


	public float getLateFee() {
		return lateFee;
	}


	public void setLateFee(float lateFee) {
		this.lateFee = lateFee;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public String getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public boolean makeFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			applicationID = result.getInt("applicationID");
			paymentDate = result.getString("paymentDate");
			amount = result.getFloat("amount");
			principal = result.getFloat("principal");
			interest = result.getFloat("interest");
			remainingBalance = result.getFloat("remainingBalance");
			lateFee = result.getFloat("lateFee");
			paymentMethod = result.getString("paymentMethod");
			note = result.getString("note");
			createdAt = result.getString("status");
			updatedAt = result.getString("updatedAt");
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
