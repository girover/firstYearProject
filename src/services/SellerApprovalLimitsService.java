package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import app.FormData;
import database.entities.Car;
import database.entities.Entity;
import database.entities.SellerApprovalLimits;
import database.repositories.SellerApprovalLimitRepository;


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
		repository = new SellerApprovalLimitRepository();
//		entityClass = SellerApprovalLimits.class;
	}

	@Override
	public SellerApprovalLimits find(int id) {
		SellerApprovalLimits sellerApprovalLimits = (SellerApprovalLimits) repository.find(id);
		return sellerApprovalLimits;
	}

	@Override
	public ArrayList<SellerApprovalLimits> getAll() {
		
		return (ArrayList<SellerApprovalLimits>) repository.getAll();
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
