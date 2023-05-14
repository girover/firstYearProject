package database.entities.factory;

import java.util.HashMap;

import database.entities.Entity;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class Factory {

	private static HashMap<String, EntityFactory> entityFactories = new HashMap<>();

	static {
		entityFactories.put("Car", new CarFactory());
		entityFactories.put("Customer", new CustomerFactory());
		entityFactories.put("LocanApplication", new LoanApplicationFactory());
	}


	/**
	 * To make Entity Factory for specified class that extends Entity class.
	 * 
	 * @param Class extends Entity : clazz
	 * @return a class that implements EntityFactory
	 */
	public static EntityFactory of(Class<? extends Entity> clazz) {
		
		EntityFactory entityFactory = entityFactories.get(clazz.getSimpleName());

		if (entityFactory == null) {
			throw new IllegalArgumentException("Invalid subclass: " + clazz.getSimpleName());
		}
		
		return entityFactory;
	}
	
	
}
