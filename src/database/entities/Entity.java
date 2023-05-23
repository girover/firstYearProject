package database.entities;

import java.sql.ResultSet;

import database.entities.factory.EntityFactory;
import database.entities.factory.Factory;

public abstract class Entity {

	protected String table;
	protected String primaryKey = "id";
	// Determines if the entity is retrieved from database
	protected boolean exists = false;

	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * determine if this entity is retrieved from database.
	 * It exists in database.
	 * @return
	 */
	public boolean exists() {
		return exists;
	}

	public void setExist(boolean exist) {
		this.exists = exist;
	}
// ********************************[ New Implementation ]********************************
	
//	public void generateFromResultSet(ResultSet result) throws SQLException {
//		
//		ResultSetMetaData metaData = result.getMetaData();
//		int columnCount = metaData.getColumnCount();
//		
//		// Prepare attribute names with null values
//		for (int i = 1; i <= columnCount; i++) {
//			attributes.put(metaData.getColumnName(i), null);
//		}
//		// add values to the prepared attributes
//		if (result.next()) {
//			for (String column : attributes.keySet()) {
//				attributes.put(column, result.getObject(column));
//			}
//		}
//	}
//	
//	public Object get(String key) {
//		return attributes.get(key);
//	}
//	
//	public Object set(String key, Object value) {
//		return attributes.put(key, value);
//	}
//********************************************************************************

	public abstract boolean mapFromResultSet(ResultSet result);

}
