package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import app.FormData;
import database.entities.Car;
import database.entities.Entity;
import database.entities.LoanApplication;
import database.repositories.LoanApplicationRepository;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with loanApplications.
 * such as retrieving all loanApplication, retrieving a set of loanApplications, 
 * retrieving a single loanApplication by ID, updating a loanApplication, 
 * and deleting a loanApplication.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class LoanApplicationService extends BaseResourceService {

	public LoanApplicationService() {
		repository = new LoanApplicationRepository();
//		entityClass = LoanApplication.class;
	}

	@Override
	public LoanApplication find(int id) {
		LoanApplication loanApplication = (LoanApplication) repository.find(id);
		return loanApplication;
	}

	@Override
	public ArrayList<LoanApplication> getAll() {
		
		return (ArrayList<LoanApplication>) repository.getAll();
	}

	@Override
	public LoanApplication create(FormData data) {
		
		LoanApplication loanApplication = new LoanApplication();
		
		loanApplication.setCustomerID((int)data.input("customerId"));
		loanApplication.sellerID((int)data.input("salesPersonId"));
		loanApplication.setCarID((int)data.input("carId"));
		loanApplication.setApplicationDate((String)data.input("applicationDate"));
		loanApplication.setLoanAmount((int)data.input("loanAmount"));
		loanApplication.setPayment((float)data.input("payment"));
		loanApplication.setMonths((int)data.input("months"));
		loanApplication.setInterestRate((float)data.input("interestRate"));
		loanApplication.setMonthlyPayment((float)data.input("monthlyPayment"));
		loanApplication.setStatus((String)data.input("status"));
		loanApplication.setNote((String)data.input("note"));

		repository.add(loanApplication);

		return loanApplication;
	}

	@Override
	public boolean update(Entity entity) {
		return repository.update((LoanApplication) entity);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((LoanApplication) entity);
	}

}