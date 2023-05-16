 package services;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import configs.Config;
import database.entities.Entity;
import database.repositories.Repository;
import database.repositories.RepositoryInterface;

/**
 * This abstract class is a part of Service Layer (Business Logic Layer).
 *
 * This class is a base class that must be inherited by all concrete resource
 * classes. This make it simple to access the repository object that is
 * responsible for those resources.
 * 
 * @version 1.0
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public abstract class BaseResourceService implements ResourceServiceInterface {

	protected RepositoryInterface repository;
//	protected Class<? extends Entity> entityClass;

//	@Override
//	public <T extends Entity> T find(int id) {
//		
//		try(ResultSet result = repository.find(id);) {
//			if(result.next()) {
//				T entity = (T)entityClass.getDeclaredConstructor().newInstance();
//				entity.makeFromResultSet(result);
//				return entity;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//	
//	@Override
//	public <T extends Entity> ArrayList<T> getAll() {
//
//		if(entityClass == null || !Entity.class.isAssignableFrom(entityClass))
//			throw new RuntimeException("No entity class is defined for casting.");
//		
//		ArrayList<T> entities = new ArrayList<>();
//		
//		try(ResultSet result = repository.getAll();) {
//			
//			while (result.next()) {
//				T entity = (T) entityClass.getDeclaredConstructor().newInstance();
//				entity.makeFromResultSet(result);
//				entities.add(entity);
//			}
//
//			return entities;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	public HashMap<String, Integer> getPaginationInfo(int page) {

		HashMap<String, Integer> paginationInfo = new HashMap<>();

		int totalRows = repository.count();
		int rowsPerPage = Integer.parseInt(Config.get("pagination.rowsPerPage"));
		int totalPages = totalRows / rowsPerPage;

		paginationInfo.put("totalRows", totalRows);
		paginationInfo.put("rowsPerPage", rowsPerPage);
		paginationInfo.put("totalPages", totalPages);

		return paginationInfo;
	}

	/**
	 * Get specific amount of rows in this repository.
	 * 
	 * @param Integer offset
	 * @param Integer limit
	 * @return Paginator
	 */
	@Override
	public Paginator paginate(int page) {

		HashMap<String, Integer> info = getPaginationInfo(page);

		ArrayList<? extends Entity> data = repository.paginate(page);

		Paginator paginator = new Paginator(info.get("totalRows"), info.get("totalPages"), page);

		paginator.setData(data);

		return paginator;
	}

}
