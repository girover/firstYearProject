package database.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import app.App;
import configs.Config;
import database.entities.Car;
import database.entities.Entity;
import services.Paginator;

/**
 * An abstract class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database.
 *
 * This abstract class provides a set of methods that can be used to perform common database operations,
 * such as retrieving all records, retrieving a single record by ID, updating a record, and deleting a
 * record. Concrete repository classes can extend this class and provide their own implementation of
 * the abstract methods to interact with a specific database.
 *
 * @version 1.0
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github Profile</a>
 * 
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/Repository.java">Class Code On Github</a>
 */
public abstract class Repository {

	protected Connection dbConnection;

	protected String table;
	protected String primaryKey = "id";
	protected int rowsPerPage = Integer.parseInt(Config.get("pagination.rowsPerPage"));;

	public Repository() {
		dbConnection = App.getDBConnection();
//		dbConnection = App.getNewDatabaseConnection();
	}
	
//	public void finalize() {
//		closeDbConnection();
//	}

	protected String getTable() {
		return table;
	}

	protected void setTable(String table) {
		this.table = table;
	}

	protected String getPrimaryKey() {
		return primaryKey;
	}

	protected void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Closing the database connection
	 * 
	 */
	protected void closeDbConnection() {
		try {
			if(dbConnection != null && !dbConnection.isClosed()) {
				dbConnection.close();
				System.out.println("Database connection is closed from repo.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute query to retrieve data from database
	 * 
	 * @param sql
	 * @param bindings
	 * @return ResultSet
	 */
	protected ResultSet select(String sql, Object... bindings) {

		try {
			PreparedStatement statement = preparedStatement(sql, bindings);

			return statement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error in sql");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Execute Query for updating rows in database
	 * 
	 * @param sql
	 * @param bindings
	 * @return boolean
	 */
	protected boolean update(String sql, Object... bindings) {
		try {

			PreparedStatement statement = preparedStatement(sql, bindings);

			int affectedRows = statement.executeUpdate();

			return (affectedRows > 0);
		} catch (SQLException e) {
			System.out.println("executeUpdate(): Error");
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * Execute Insert statement, and returns generated primary key
	 * 
	 * @param sql
	 * @return integer : the new generated id.
	 */
	protected int insertAndGetGeneratedId(String sql, Object... bindings) {
		try {

			PreparedStatement statement = preparedStatement(sql, bindings);

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();

				if (resultSet.next())
					return resultSet.getInt(1);
			}

			return 0;
		} catch (SQLException e) {
			System.out.println("executeInsert(): Error");
			e.printStackTrace();
			throw new RuntimeException("SQLException: "+e.getMessage());
//			return 0;
		}
	}

	/**
	 * Execute Insert statement, Use this on join tables.
	 * 
	 * @param sql
	 * @return boolean
	 */
	protected boolean insert(String sql, Object... bindings) {
		try {

			PreparedStatement statement = preparedStatementWithoutGeneratedKeys(sql, bindings);

			int affectedRows = statement.executeUpdate();

			return (affectedRows > 0);
		} catch (SQLException e) {
			System.out.println("executeInsert(): Error");
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * Execute DELETE statement, and return true if deleted, otherwise return false
	 * 
	 * @param sql
	 * @return boolean : entity deleted or not.
	 */
	protected boolean delete(String sql, Object... bindings) {
		
		try {
			PreparedStatement statement = preparedStatement(sql, bindings);

			int affectedRows = statement.executeUpdate();

			return (affectedRows > 0);
		} catch (SQLException e) {
			System.out.println("executeDelete(): Error");
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * Count records in this repository
	 * 
	 * @return Integer
	 */
	public int count() {
		String sql = "SELECT COUNT(*) AS [total] FROM [" + getTable() + "]";

		try {
			ResultSet result = select(sql);
			if (result.next()) {
				return result.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	/**
	 * Get by the primary key
	 * 
	 * @param id
	 * @return ResultSet
	 */
	public ResultSet find(int id) {
		String sql = "SELECT * FROM [" + getTable() + "] WHERE [" + getPrimaryKey() + "] = ?";

		return select(sql, id);
	}

	/**
	 * Get the first entity from database
	 * 
	 * @param id
	 * @return ResultSet
	 */
	public ResultSet first() {
		String sql = "SELECT TOP 1 * FROM [" + table + "] ORDER BY " + primaryKey;

		return select(sql);
	}

	/**
	 * Get All records in this repository
	 * 
	 * @param id
	 * @return ResultSet
	 */
	public ResultSet getAll() {
		String sql = "SELECT * FROM [" + getTable() + "]";

		return select(sql);
	}

	/**
	 * Get specific amount of rows in this repository
	 * 
	 * @param Integer offset
	 * @param Integer limit
	 * @return ResultSet
	 */
	public ResultSet pagination(int currentPage) {
		int offset = (currentPage - 1) * rowsPerPage;
		String sql = "SELECT * FROM [" + getTable() + "] " + "ORDER BY [" + getPrimaryKey() + "] " + "OFFSET "
				+ offset + " ROWS " + "FETCH NEXT " + rowsPerPage + " ROWS ONLY;";

		return select(sql);
	}
	
	public ResultSet getByACondition(String column, String operation, String value) {
		String sql = "SELECT * FROM [" + getTable() + "] WHERE [" + column + "] " + operation + " ?;";

		return select(sql, value);
	}
	
	/**
	 * Delete the entity that has the specified id from database.
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		String sql = "SELECT * FROM [" + getTable() + "] WHERE [" + primaryKey + "] = ?;";

		return delete(sql, id);
	}

	/**
	 * Get Prepared Statement to execute it in other methods
	 * 
	 * @param sql
	 * @param bindings
	 * @return
	 */
	private PreparedStatement preparedStatement(String sql, Object... bindings) {

		PreparedStatement statement;
		try {
			statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			bindParameters(statement, bindings);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			statement = null;
		}

		return statement;
	}

	/**
	 * Get Prepared Statement to execute it in other methods
	 * 
	 * @param sql
	 * @param bindings
	 * @return
	 */
	private PreparedStatement preparedStatementWithoutGeneratedKeys(String sql, Object... bindings) {

		PreparedStatement statement;
		try {
			statement = dbConnection.prepareStatement(sql);

			bindParameters(statement, bindings);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			statement = null;
		}

		return statement;
	}

	private void bindParameters(PreparedStatement statement, Object... parameters) {
		int i = 1;

		if (parameters != null)
			for (Object parameter : parameters) {
				bind(statement, i, parameter);
				i++;
			}
	}

	private void bind(PreparedStatement statement, int index, Object parameter) {

		if (parameter instanceof String) {
			try {
				statement.setString(index, (String) parameter);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (parameter instanceof Integer) {
			try {
				statement.setInt(index, (Integer) parameter);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (parameter instanceof Double) {
			try {
				statement.setDouble(index, (Double) parameter);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (parameter instanceof Float) {
			try {
				statement.setFloat(index, (Float) parameter);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (parameter instanceof Timestamp) {
			try {
				statement.setTimestamp(index, (Timestamp) parameter);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (parameter instanceof Long) {
			try {
				statement.setLong(index, (Long) parameter);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * if the specified is less that 1 then an exception will be thrown
	 * 
	 * @param int page
	 * @throws RuntimeException
	 */
	protected void validatePage(int page) {
		if(page < 1)
			throw new RuntimeException("Page cannot be less than 1.");
	}

	/**
	 * Delete the specified entity from database
	 * @param entity
	 * @return
	 */
	public abstract boolean delete(Entity entity);
	
	/**
	 * Updates the specified entity in the database with the latest changes.
	 * @param entity
	 * @return
	 */
	public abstract boolean update(Entity entity);
	
	/**
	 * Inserts the specified entity to the database.
	 * @param entity
	 * @return integer
	 */
	public abstract int add(Entity entity);
	
	/**
	 * Inserts a list of entities to the database.
	 * @param entity
	 * @return integer
	 */
	public <T extends Entity> int addAll(ArrayList<T> entities) {
		
		if(entities.size() == 0)
			return 0;
		
		try{			
			dbConnection.setAutoCommit(false);
			
			Class<? extends Entity> cls = entities.get(0).getClass();
			
			for (Entity entity : entities) {
				if(add(cls.cast(entity)) == 0) {
					dbConnection.rollback();
					throw new SQLException(getClass().getName() + " . addAll() method. Failed to create Car.");
				}
			}
			
			dbConnection.commit();
			
			return entities.size();
			
		}catch(SQLException e){
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return 0;
	}
}
