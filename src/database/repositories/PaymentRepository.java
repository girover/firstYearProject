package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.entities.Entity;
import database.entities.Payment;

/**
 * This class is a part of Data Access Layer. 
 * A class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database table [payment].
 *
 * This class provides a set of methods that can be used to perform common database operations,
 * such as retrieving all records, retrieving a single record by ID, updating a record, and deleting a
 * record.
 *
 * @version 1.0
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/PaymentRepository.java">Class Code On Github</a>
 */
public class PaymentRepository extends Repository {

	public PaymentRepository() {
		setTable("payment");
	}

	/**
	 * Get payment by its id from database
	 * 
	 * @param id
	 * @return
	 */
	public Payment getById(int id) {
		
		Payment payment = null;

		try {
			ResultSet result = find(id);

			if (result.next()) {
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

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE [" + entity.getPrimaryKey() + "] = ?";

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

		Payment payment = (Payment) entity;

		String sql = "UPDATE [" + table + "] SET " 
				+ "[applicationID] = ?," 
				+ "[paymentDate] = ?," 
				+ "[amount] = ?,"
				+ "[principal] = ?," 
				+ "[interest] = ?," 
				+ "[remainingBalance] = ?," 
				+ "[lateFee] = ?,"
				+ "[paymentMethod] = ?," 
				+ "[note] = ?," 
				+ "[createdAt] = ?," 
				+ "[updatedAt] = ? " 
				+ "WHERE [" + primaryKey + "] = ?";

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

		Payment payment = (Payment) entity;

		String sql = "INSERT INTO [" + table + "] (" 
				+ "[applicationID]," 
				+ "[paymentDate],"
				+ "[amount]," 
				+ "[principal]," 
				+ "[interest]," 
				+ "[remainingBalance],"
				+ "[lateFee]," 
				+ "[paymentMethod]," 
				+ "[note]) " 
				+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int id = insertAndGetGeneratedId(sql, 
				payment.getApplicationID(), 
				payment.getPaymentDate(), 
				payment.getAmount(),
				payment.getPrincipal(), 
				payment.getInterest(), 
				payment.getRemainingBalance(), 
				payment.getLateFee(),
				payment.getPaymentMethod(), 
				payment.getNote());

		if (id > 0) {
			payment.setId(id);
			payment.setExist(true);

			return id;
		}

		return 0;
	}
}
