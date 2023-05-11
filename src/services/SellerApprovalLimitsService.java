package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import app.FormData;

import database.entities.Entity;
import database.entities.SellerApprovalLimits;
import database.repositories.SellerApprovalLimitsRepository;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with sellerApprovalLimits's.
 * such as retrieving all sellerApprovalLimits's, retrieving a set of sellerApprovalLimits's, 
 * retrieving a single sellerApprovalLimits by ID, updating a sellerApprovalLimits, 
 * and deleting a sellerApprovalLimits.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class SellerApprovalLimitsService extends BaseResourceService {

	public SellerApprovalLimitsService() {
		repository = new SellerApprovalLimitsRepository();
	}

	@Override
	public SellerApprovalLimits find(int id) {
		SellerApprovalLimits sellerApprovalLimits = (SellerApprovalLimits) repository.find(id);
		return sellerApprovalLimits;
	}

	@Override
	public ArrayList<SellerApprovalLimits> getAll() {
		
		ArrayList<SellerApprovalLimits> sellerApprovalLimitss = new ArrayList<>();
		
		ResultSet result = repository.getAll();
		
		try {
			while (result.next()) {
				SellerApprovalLimits sellerApprovalLimits = new SellerApprovalLimits();
				if (sellerApprovalLimits.makeFromResultSet(result))
					sellerApprovalLimitss.add(sellerApprovalLimits);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sellerApprovalLimitss;
	}

	@Override
	public ArrayList<SellerApprovalLimits> getPage(int page) {

		
		ArrayList<SellerApprovalLimits> sellerApprovalLimitss = new ArrayList<>();
		
		ResultSet result = repository.getPage(page);
		try {
			while (result.next()) {
				SellerApprovalLimits sellerApprovalLimits = new SellerApprovalLimits();
				if (sellerApprovalLimits.makeFromResultSet(result))
					sellerApprovalLimitss.add(sellerApprovalLimits);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sellerApprovalLimitss;
	}

	@Override
	public SellerApprovalLimits create(FormData data) {
		
		SellerApprovalLimits sellerApprovalLimits = new SellerApprovalLimits();
		
		sellerApprovalLimits.setEmployeeID((int)data.input("employeeId"));
		sellerApprovalLimits.setMaxApprovalLimit((int)data.input("maxApprovalLimit"));

		repository.add(sellerApprovalLimits);

		return sellerApprovalLimits;
	}

	@Override
	public boolean update(Entity entity) {
		return repository.update((SellerApprovalLimits) entity);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((SellerApprovalLimits) entity);
	}

}
