package database.repositories;

import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.Entity;

public interface RepositoryInterface {
	
	
	/**
	 * Get the first entity from database
	 * 
	 * @param id
	 * @return ResultSet
	 * @throws SQLException 
	 */
	public Entity first();
	
	
	/**
	 * Get by the primary key
	 * 
	 * @param id
	 * @return ResultSet
	 */
	public Entity find(int id);
	
	/**
	 * Count records in this repository
	 * 
	 * @return Integer
	 */
	public int count();
	
	/**
	 * Delete the specified entity from database
	 * @param entity
	 * @return
	 */
	public boolean delete(Entity entity);
	
	/**
	 * Delete the entity that has the specified id from database.
	 * @param id
	 * @return
	 */
	public boolean delete(int id);

	/**
	 * Updates the specified entity in the database with the latest changes.
	 * @param entity
	 * @return
	 */
	public boolean update(Entity entity);
	
	/**
	 * Inserts the specified entity to the database.
	 * @param entity
	 * @return integer
	 */
	public int add(Entity entity);
	
	public <T extends Entity> int addAll(ArrayList<T> entities);

}
