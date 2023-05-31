package entities;

import java.sql.ResultSet;

/**
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github Profile</a>
 */
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
	
	public abstract boolean mapFromResultSet(ResultSet result);

}
