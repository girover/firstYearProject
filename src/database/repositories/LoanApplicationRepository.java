package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;


import database.entities.Entity;
import database.entities.LoanApplication;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the LoanApplication table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class LoanApplicationRepository extends Repository {
	
	
	public LoanApplicationRepository() {
		setTable("loanApplication");
	}
	
	
	/**
	 * Get loanApplication by its id from database
	 * @param id
	 * @return
	 */
	public LoanApplication getById(int id) {
		LoanApplication loanApplication = null;
		
		try {
			ResultSet result = find(id);
			
			if(result.next()) {
				loanApplication = new LoanApplication();
				loanApplication.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loanApplication;
	}
	

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

		return delete(sql, ((LoanApplication) entity).getId());
	}

	/**
	 * Updates the specified loanApplication in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {
		
		LoanApplication loanApplication = (LoanApplication)entity;
		
		String sql = "UPDATE [" +table+ "] SET "
				   + "[customerID] = ?, "
				   + "[salesPersonID] = ?, "
				   + "[carID] = ?, "
				   + "[applicationDate] = ?, "
				   + "[loanAmount] = ?, "
				   + "[payment] = ?, "
				   + "[months] = ?, "
				   + "[interestRate] = ?, "
				   + "[monthlyPayment] = ?, "
				   + "[status] = ?, "
				   + "[note] = ?,"
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, 
				loanApplication.getCustomerID(), 
				loanApplication.getSalesPersonID(), 
				loanApplication.getCarID(),
				loanApplication.getApplicationDate(),
				loanApplication.getLoanAmount(),
				loanApplication.getPayment(),
				loanApplication.getMonths(),
				loanApplication.getInterestRate(),
		        loanApplication.getMonthlyPayment(),
		        loanApplication.getStatus(),
		        loanApplication.getNote(),
		        loanApplication.getId());
	}

	/**
	 * Insert the specified loanApplication into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		LoanApplication loanApplication = (LoanApplication)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				   + "("
				   + "[customerID] = ?, "
				   + "[salesPersonID] = ?, "
				   + "[carID] = ?, "
				   + "[applicationDate] = ?, "
				   + "[loanAmount] = ?, "
				   + "[payment] = ?, "
				   + "[months] = ?, "
				   + "[interestRate] = ?, "
				   + "[monthlyPayment] = ?, "
				   + "[status] = ?, "
				   + "[note] = ?, "
				   + ") "
				   + "VALUES "
				   + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int id = insertAndGetGeneratedId(sql, 
				loanApplication.getCustomerID(), 
				loanApplication.getSalesPersonID(), 
				loanApplication.getCarID(),
				loanApplication.getApplicationDate(),
				loanApplication.getLoanAmount(),
				loanApplication.getPayment(),
				loanApplication.getMonths(),
				loanApplication.getInterestRate(),
		        loanApplication.getMonthlyPayment(),
		        loanApplication.getStatus(),
		        loanApplication.getNote());
		
		
		if(id > 0) {
			loanApplication.setId(id);
			loanApplication.setExist(true);
			
			return id;
		}
		
		return 0;
	}
}
