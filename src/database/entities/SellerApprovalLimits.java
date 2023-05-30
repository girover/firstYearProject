package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a part of Data Access Layer. This class represents a Data
 * Access Object for the SellerApprovealLimits table in the database.
 *
 * @version 1.0
 * 
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 * 
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/entities/SellerApprovalLimits.java">Class Code On Github</a>
 */
public class SellerApprovalLimits extends Entity {

	public SellerApprovalLimits() {
		setTable("sellerApprovalLimits");
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
	public boolean mapFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			employeeID = result.getInt("employeeID");
			maxApprovalLimit = result.getInt("maxApprovalLimit");

			setExist(true);			
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
