package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;


import database.entities.Entity;
import database.entities.SellerApprovalLimits;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the SellerApprovalLimits table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class SellerApprovalLimitsRepository extends Repository {
	
	
	public SellerApprovalLimitsRepository() {
		setTable("sellerApprovalLimits");
	}
	
	
	/**
	 * Get SellerApprovalLimits by its id from database
	 * @param id
	 * @return
	 */
	public SellerApprovalLimits getById(int id) {
		SellerApprovalLimits sellerApprovalLimits = null;
		
		try {
			ResultSet result = find(id);
			
			if(result.next()) {
				sellerApprovalLimits = new SellerApprovalLimits();
				sellerApprovalLimits.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sellerApprovalLimits;
	}
	

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

		return delete(sql, ((SellerApprovalLimits) entity).getId());
	}

	/**
	 * Updates the specified SellerApprovalLimits in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {
		
		SellerApprovalLimits sellerApprovalLimits = (SellerApprovalLimits)entity;
		
		String sql = "UPDATE [" +table+ "] SET "
				   + "[employeeID] = ?, "
				   + "[maxApprovelLimit] = ?, "
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, 
				sellerApprovalLimits.getEmployeeID(),
				sellerApprovalLimits.getMaxApprovalLimit());
	}

	/**
	 * Insert the specified SellerApprovalLimits into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		SellerApprovalLimits sellerApprovalLimits = (SellerApprovalLimits)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				   + "("
				   + "[employeeID] = ?, "
				   + "[maxApprovelLimit] = ?, "
				   + ") "
				   + "VALUES "
				   + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int id = insertAndGetGeneratedId(sql, 
				sellerApprovalLimits.getEmployeeID(),
				sellerApprovalLimits.getMaxApprovalLimit());
				
		
		if(id > 0) {
			sellerApprovalLimits.setId(id);
			sellerApprovalLimits.setExist(true);
			
			return id;
		}
		
		return 0;
	}
}
