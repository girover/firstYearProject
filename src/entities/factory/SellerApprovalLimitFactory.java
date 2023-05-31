package entities.factory;

import java.util.ArrayList;

import entities.Car;
import entities.SellerApprovalLimits;
import faker.Faker;

/**
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 *
 */
public class SellerApprovalLimitFactory implements EntityFactory {
	
	@Override
	public SellerApprovalLimits make() {
		return Faker.sellerApprovalLimit();
	}

	@Override
	public ArrayList<SellerApprovalLimits> make(int quantity) {
		
		ArrayList<SellerApprovalLimits> sellerApprovalLimits = new ArrayList<>();
		
		for(int i=0; i<quantity; i++)
			sellerApprovalLimits.add(Faker.sellerApprovalLimit());

		return sellerApprovalLimits;
	}

}
