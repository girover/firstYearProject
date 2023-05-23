package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import database.entities.LoanApplication;
import faker.Faker;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
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
