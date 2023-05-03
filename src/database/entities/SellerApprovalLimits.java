package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the SellerApprovealLimits table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class SellerApprovalLimits extends Entity {
	
	
	public SellerApprovalLimits() {
		setTable("sellerapprovallimits");
	}
	
	
	private int id;
	private int employeeID;
	private int maxApprovalLimit;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getEmployeeID() {
		return employeeID;
	}



	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}



	public int getMaxApprovalLimit() {
		return maxApprovalLimit;
	}



	public void setMaxApprovalLimit(int maxApprovalLimit) {
		this.maxApprovalLimit = maxApprovalLimit;
	}



	@Override
	public boolean makeFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			employeeID = result.getInt("employeeID");
			maxApprovalLimit = result.getInt("maxApprovalLimit");
			
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
