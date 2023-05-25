package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.Employee;
import database.entities.Entity;
import database.entities.SellerApprovalLimits;
import database.entities.User;
/**
 * This class is a part of Data Access Layer. 
 * A class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database table [sellerApprovalLimit].
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
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/SellerApprovalLimitsRepository.java">Class Code On Github</a>
 */
public class SellerApprovalLimitRepository extends Repository {
	
	
	public SellerApprovalLimitRepository() {
		setTable("sellerApprovalLimit");
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
				sellerApprovalLimits.getMaxApprovalLimit(),
				sellerApprovalLimits.getId());
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
				   + "(?, ?)";
		
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


	@Override
	public SellerApprovalLimits first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public SellerApprovalLimits find(Object id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public SellerApprovalLimits last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<SellerApprovalLimits> getByCondition(String column, String operation, Object value) {
		return mapResultSetToEntityList(getRowsByACondition(column, operation, value));
	}

	@Override
	public ArrayList<SellerApprovalLimits> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}
	
	public SellerApprovalLimits getMaxApprovalLimit(Employee employee) {
		ResultSet reult = getRowsByACondition("employeeID", "=", employee.getId());
		
		return mapResultSetToEntity(reult);
	}

	@Override
	public ArrayList<SellerApprovalLimits> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected SellerApprovalLimits mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				SellerApprovalLimits sellerApprovalLimit = new SellerApprovalLimits();
				sellerApprovalLimit.mapFromResultSet(result);
				return sellerApprovalLimit;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<SellerApprovalLimits> mapResultSetToEntityList(ResultSet result) {
		ArrayList<SellerApprovalLimits> limits = new ArrayList<>();
		
		try {
			while(result.next()) {
				SellerApprovalLimits sellerApprovalLimit = new SellerApprovalLimits();
				sellerApprovalLimit.mapFromResultSet(result);
				limits.add(sellerApprovalLimit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return limits;
	}
}
