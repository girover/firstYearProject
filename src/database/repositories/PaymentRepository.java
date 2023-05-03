package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;


import database.entities.Entity;
import database.entities.Payment;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the payment table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class PaymentRepository extends Repository {
	
	
	public PaymentRepository() {
		setTable("payment");
	}
	
	
	/**
	 * Get payment by its id from database
	 * @param id
	 * @return
	 */
	public Payment getById(int id) {
		Payment payment = null;
		
		try {
			ResultSet result = find(id);
			
			if(result.next()) {
				payment = new Payment();
				payment.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return payment;
	}
	

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

		return delete(sql, ((Payment) entity).getId());
	}

	/**
	 * Updates the specified payment in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {
		
		Payment payment = (Payment)entity;
		
		String sql = "UPDATE [" +table+ "] SET "
				   + "[applicationID] = ?, "
				   + "[paymentDate] = ?, "
				   + "[amount] = ?, "
				   + "[principal] = ?, "
				   + "[interest] = ?, "
				   + "[remainingBalance] = ?, "
				   + "[lateFee] = ?, "
				   + "[paymentMethod] = ?, "
				   + "[note] = ?, "
				   + "[createdAt] = ?, "
				   + "[updatedAt] = ?, "
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, 
				payment.getApplicationID(), 
				payment.getPaymentDate(), 
				payment.getAmount(),
				payment.getPrincipal(),
				payment.getInterest(),
				payment.getRemainingBalance(),
				payment.getLateFee(),
				payment.getPaymentMethod(),
				payment.getNote(),
				payment.getCreatedAt(),
				payment.getUpdatedAt(),
				payment.getId());
				
	}

	/**
	 * Insert the specified payment into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		Payment payment = (Payment)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				   + "("
				   + "[applicationID] = ?, "
				   + "[paymentDate] = ?, "
				   + "[amount] = ?, "
				   + "[principal] = ?, "
				   + "[interest] = ?, "
				   + "[remainingBalance] = ?, "
				   + "[lateFee] = ?, "
				   + "[paymentMethod] = ?, "
				   + "[note] = ?, "
				   + "[createdAt] = ?, "
				   + "[updatedAt] = ?, "
				   + ") "
				   + "VALUES "
				   + "(applicationID, paymentDate, amount, principal, interest, remainingBlance, lateFee, paymenMethod, note, createdAt, updatedAt)";
		
		int id = insertAndGetGeneratedId(sql, 
				payment.getApplicationID(), 
				payment.getPaymentDate(), 
				payment.getAmount(),
				payment.getPrincipal(),
				payment.getInterest(),
				payment.getRemainingBalance(),
				payment.getLateFee(),
				payment.getPaymentMethod(),
				payment.getNote(),
				payment.getCreatedAt(),
				payment.getUpdatedAt());
		
		
		if(id > 0) {
			payment.setId(id);
			payment.setExist(true);
			
			return id;
		}
		
		return 0;
	}
}
