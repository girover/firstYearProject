package database.entities;

import java.sql.ResultSet;

public abstract class Entity {

	protected String table;
	protected String primaryKey = "id";
	// Determines if the entity is retrieved from database
	protected boolean exist = false;
	
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
	
	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public abstract boolean makeFromResultSet(ResultSet result);
}
