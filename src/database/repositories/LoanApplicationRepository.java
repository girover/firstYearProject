package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.Entity;
import database.entities.LoanApplication;
import database.entities.User;

/**
 * This class is a part of Data Access Layer. 
 * A class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database table [loanApplication].
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
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/LoanApplicationRepository.java">Class Code On Github</a>
 */
public class LoanApplicationRepository extends Repository {

	public LoanApplicationRepository() {
		setTable("loanApplication");
	}

	/**
	 * Get loanApplication by its id from database
	 * 
	 * @param id
	 * @return
	 */
	public LoanApplication getById(int id) {
		
		LoanApplication loanApplication = null;

		try {
			ResultSet result = findById(id);

			if (result.next()) {
				loanApplication = new LoanApplication();
				loanApplication.mapFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loanApplication;
	}

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE [" + entity.getPrimaryKey() + "] = ?";

		return delete(sql, ((LoanApplication) entity).getId());
	}

	/**
	 * Updates the specified loanApplication in the database with the latest
	 * changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {

		LoanApplication loanApplication = (LoanApplication) entity;

		String sql = "UPDATE [" + table + "] SET " 
				+ "[customerID] = ?," 
				+ "[sellerID] = ?," 
				+ "[carID] = ?,"
//				+ "[applicationDate] = ?," 
				+ "[loanAmount] = ?," 
				+ "[payment] = ?," 
				+ "[months] = ?,"
				+ "[interestRate] = ?," 
				+ "[monthlyPayment] = ?," 
				+ "[status] = ?," 
				+ "[note] = ? " 
				+ "WHERE [" + primaryKey + "] = ?";

		return update(sql, 
				loanApplication.getCustomerID(), 
				loanApplication.getSellerID(),
				loanApplication.getCarID(), 
//				loanApplication.getApplicationDate(), 
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

		LoanApplication loanApplication = (LoanApplication) entity;

		String sql = "INSERT INTO [" + table + "] " + "(" 
				+ "[customerID]," 
				+ "[sellerID],"
				+ "[carID]," 
//				+ "[applicationDate]," 
				+ "[loanAmount]," 
				+ "[payment],"
				+ "[months]," 
				+ "[interestRate]," 
				+ "[monthlyPayment]," 
				+ "[status],"
				+ "[note]) " 
				+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int id = insertAndGetGeneratedId(sql, 
				loanApplication.getCustomerID(), 
				loanApplication.getSellerID(),
				loanApplication.getCarID(), 
//				loanApplication.getApplicationDate(), 
				loanApplication.getLoanAmount(),
				loanApplication.getPayment(), 
				loanApplication.getMonths(), 
				loanApplication.getInterestRate(),
				loanApplication.getMonthlyPayment(), 
				loanApplication.getStatus(), 
				loanApplication.getNote());

		if (id > 0) {
			loanApplication.setId(id);
			loanApplication.setExist(true);

			return id;
		}

		return 0;
	}

	@Override
	public LoanApplication first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public LoanApplication find(Object id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public LoanApplication last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<LoanApplication> getByCondition(String column, String operation, Object value) {
		return mapResultSetToEntityList(getRowsByACondition(column, operation, value));
	}

	@Override
	public ArrayList<LoanApplication> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}

	@Override
	public ArrayList<LoanApplication> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected LoanApplication mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				LoanApplication loanApplication = new LoanApplication();
				loanApplication.mapFromResultSet(result);
				return loanApplication;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<LoanApplication> mapResultSetToEntityList(ResultSet result) {
		ArrayList<LoanApplication> loanApplications = new ArrayList<>();
		
		try {
			while(result.next()) {
				LoanApplication loanApplication = new LoanApplication();
				loanApplication.mapFromResultSet(result);
				loanApplications.add(loanApplication);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loanApplications;
	}
}
