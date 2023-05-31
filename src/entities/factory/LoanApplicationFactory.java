package entities.factory;

import java.util.ArrayList;

import entities.Car;
import entities.LoanApplication;
import faker.Faker;

/**
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 *
 */
public class LoanApplicationFactory implements EntityFactory {
	
	@Override
	public LoanApplication make() {
		return Faker.loanApplication();
	}

	@Override
	public ArrayList<LoanApplication> make(int quantity) {
		
		ArrayList<LoanApplication> loanApplications = new ArrayList<>();
		
		for(int i=0; i<quantity; i++)
			loanApplications.add(Faker.loanApplication());

		return loanApplications;
	}

}
