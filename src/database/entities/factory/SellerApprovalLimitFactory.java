package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import database.entities.SellerApprovalLimits;
import faker.Faker;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
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
