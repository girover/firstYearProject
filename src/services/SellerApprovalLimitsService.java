package services;

import java.util.ArrayList;

import database.entities.Employee;
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
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class SellerApprovalLimitsService extends BaseResourceService {

	public SellerApprovalLimitsService() {
		repository = new SellerApprovalLimitRepository();
	}

	@Override
	public SellerApprovalLimits find(Object id) {
		SellerApprovalLimits sellerApprovalLimits = (SellerApprovalLimits) repository.find(id);
		return sellerApprovalLimits;
	}

	@Override
	public ArrayList<SellerApprovalLimits> getAll() {
		
		return (ArrayList<SellerApprovalLimits>) repository.getAll();
	}

	@Override
	public SellerApprovalLimits create(Entity entity) {
		
		SellerApprovalLimits sellerApprovalLimits = (SellerApprovalLimits)entity;

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
	
	public int getSellerMaxApprovalLimit(Employee seller) {
		ArrayList<SellerApprovalLimits> limits = (ArrayList<SellerApprovalLimits>) repository.getByCondition("employeeID", "=", seller.getId());
		
		if(limits.size() > 0)
			return limits.get(0).getMaxApprovalLimit();
		
		return 0;
	}

}
