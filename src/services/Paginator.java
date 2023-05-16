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
	
	private int totalRows;
	private int totalPages;
	private int currentPage;
	private int totalLinks; // How many buttons should be in pagination.
	
	private ArrayList<? extends Entity> data;
	
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
		this.totalRows = totalModels;
	}
	
	public int getTotalModels() {
		return totalRows;
	}

	public void setTotalModels(int totalModels) {
		this.totalRows = totalModels;
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

	public ArrayList<? extends Entity> getData() {
		return data;
	}

	public void setData(ArrayList<? extends Entity> data) {
		this.data = data;
	}

	public int getTotalLinks() {
		return totalLinks;
	}

	public void setTotalLinks(int totalLinks) {
		this.totalLinks = totalLinks;
	}
	
	public <T extends Entity> ArrayList<T> castDataTo(Class<T> clazz){
		
		return (ArrayList<T>) data;
	}

}
