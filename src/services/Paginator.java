package services;

import java.sql.ResultSet;
import java.util.ArrayList;

import configs.Config;
import database.entities.Entity;

/**
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class Paginator {
	
	private int totalModels;
	private int totalPages;
	private int currentPage;
	private int totalLinks;
	
	private ResultSet data;
	
	public Paginator(){
		totalLinks = Integer.parseInt(Config.get("pagination.links"));
	}
	
	public Paginator(int totalPages, int currentPage) {
		this();
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}
	
	public Paginator(int totalModels, int totalPages, int currentPage) {
		this(totalPages, currentPage);
		this.totalModels = totalModels;
	}
	
	public int getTotalModels() {
		return totalModels;
	}

	public void setTotalModels(int totalModels) {
		this.totalModels = totalModels;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public ResultSet getData() {
		return data;
	}

	public void setData(ResultSet data) {
		this.data = data;
	}

	public int getTotalLinks() {
		return totalLinks;
	}

	public void setTotalLinks(int totalLinks) {
		this.totalLinks = totalLinks;
	}
	
	public <T extends Entity> ArrayList<T> castDataTo(Class<T> clazz){
		
		ArrayList<T> castedData = new ArrayList<>();
		try {
			while(data.next()) {
				T entity = clazz.getDeclaredConstructor().newInstance();
				entity.makeFromResultSet(data);
				castedData.add(entity);
			}
			
			return castedData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
