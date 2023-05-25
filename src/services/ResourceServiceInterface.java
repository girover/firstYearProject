package services;

import java.util.ArrayList;

import app.FormData;
import database.entities.Entity;

/**
 * This interface is a part of Service Layer (Business Logic Layer). 
 *
 * It provides a set of methods that can be implemented to perform common 
 * operation on resources,
 * such as retrieving all resources, retrieving a set of resources, 
 * retrieving a single resource by ID, updating a resource, and deleting a resource.
 * 
 * @version 1.0
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public interface ResourceServiceInterface {
	
	/**
	 * Get a list of all resources.
	 * 
	 * @return ArrayList<Entity>
	 */
	public <T extends Entity> ArrayList<T> getAll();
	
	/**
	 * Get a specified number of resources as a list.
	 * 
	 * @param int : current page number
	 * @return services.Paginator
	 */
	public Paginator paginate(int currentPage);

	/**
	 * Getting the resource with specified id
	 * 
	 * @param Id
	 * @return Entity
	 */
	public Entity find(Object id);
	
	/**
	 * This method will create a resource from specified data
	 * 
	 * @param FormData data
	 * @return Entity
	 */
	public Entity create(FormData data);
	
	/**
	 * Updating the specified resource with its updated data.
	 * 
	 * @param Entity entity
	 * @return Entity
	 */
	public boolean update(Entity entity);
	
	/**
	 * Delete the specified resource from database.
	 * 
	 * @param entity
	 * @return boolean
	 */
	public boolean delete(Entity entity);
}
