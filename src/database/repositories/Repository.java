package database.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import app.App;

public abstract class Repository {

	protected Connection dbConnection;

	protected String table;
	protected String primaryKey = "id";

	protected Repository() {
		dbConnection = App.getDBConnection();
	}

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

	protected void closeDbConnection() {
		try {
			dbConnection.close();
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
			System.out.println(sql);

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
			System.out.println(sql);

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

			return 0;
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
			System.out.println(sql);

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
			System.out.println(sql);

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
	protected int count() {
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
	protected ResultSet find(int id) {
		String sql = "SELECT * FROM [" + getTable() + "] WHERE [" + getPrimaryKey() + "] = ?";

		return select(sql, id);
	}

	/**
	 * Get the first entity from database
	 * 
	 * @param id
	 * @return ResultSet
	 */
	protected ResultSet first() {
		String sql = "SELECT TOP 1 * FROM [" + table + "] ORDER BY " + primaryKey;

		return select(sql);
	}

	/**
	 * Get All records in this repository
	 * 
	 * @param id
	 * @return ResultSet
	 */
	protected ResultSet getAll() {
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
	protected ResultSet get(int offset, int limit) {
		String sql = "SELECT TOP * FROM [" + getTable() + "] " + "ORDER BY [" + getPrimaryKey() + "] " + "OFFSET "
				+ offset + " ROWS " + "FETCH NEXT " + limit + " ROWS ONLY";

		return select(sql);
	}

	protected ResultSet getByACondition(String column, String operation, String value) {
		String sql = "SELECT * FROM [" + getTable() + "] WHERE [" + column + "] " + operation + " ?";

		return select(sql, value);
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

}
