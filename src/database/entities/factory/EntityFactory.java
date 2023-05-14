package database.entities.factory;

import java.util.ArrayList;

import database.entities.Entity;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public interface EntityFactory {

	/**
	 * To make only one entity.
	 * @return Entity
	 */
	public Entity make();
	/**
	 * To male a list of entities.
	 * @param quantity
	 * @return
	 */
	public ArrayList<? extends Entity> make(int quantity);
}
