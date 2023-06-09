package database.repositories;

import java.util.ArrayList;

import entities.Entity;

/**
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github Profile</a>
 */
public interface RepositoryInterface {
	
	
	/**
	 * Get the first entity from database
	 * 
	 * @param id
	 * @return Entity
	 */
	public Entity first();
	
	/**
	 * Get the first entity from database
	 * 
	 * @param id
	 * @return Entity
	 */
	public Entity last();
	
	
	/**
	 * Get by the primary key
	 * 
	 * @param id
	 * @return Entity
	 */
	public Entity find(Object id);
	
	/**
	 * 
	 */
	public ArrayList<? extends Entity> getAll();
	
	/**
	 * Get by the primary key
	 * 
	 * @param id
	 * @return Entity
	 */
	public ArrayList<? extends Entity> getByCondition(String column, String operation, Object value);
	
	/**
	 * 
	 */
	public ArrayList<? extends Entity> paginate(int pageNumber);
	
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
	 * @param Entity
	 * @return
	 */
	public boolean update(Entity entity);
	
	/**
	 * Inserts the specified entity to the database.
	 * @param entity
	 * @return integer
	 */
	public int add(Entity entity);
	
	/**
	 * Add a set of entities to the repository.
	 * @param ArrayList<Entity>
	 * @param entities
	 * @return Entity
	 */
	public <T extends Entity> int addAll(ArrayList<T> entities);
	

}
