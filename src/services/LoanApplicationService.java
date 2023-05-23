package services;

import java.util.ArrayList;
import java.util.Observer;

import app.FormData;
import database.entities.Car;
import database.entities.Customer;
import database.entities.Employee;
import database.entities.Entity;
import database.entities.LoanApplication;
import database.entities.SellerApprovalLimits;
import database.repositories.CarRepository;
import database.repositories.CustomerRepository;
import database.repositories.EmployeeRepository;
import database.repositories.LoanApplicationRepository;
import database.repositories.SellerApprovalLimitRepository;
import exception.LoanException;

/**
 * This class is a part of Service Layer (Business Logic Layer).
 *
 * This class provides a set of methods that can be used to deal with
 * loanApplications. such as retrieving all loanApplication, retrieving a set of
 * loanApplications, retrieving a single loanApplication by ID, updating a
 * loanApplication, and deleting a loanApplication.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen - <b style="color:red">
 *         mrmaklie@gmail.com</b> -
 *         <a href="https://github.com/MrMaklie">Github</a>
 */
public class LoanApplicationService extends BaseResourceService {
	
	public static final int MONTHS_WITHOUT_INTEREST_RATE = 36;

	public LoanApplicationService() {
		repository = new LoanApplicationRepository();
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

		loanApplication.setCustomerID((int) data.input("customerID"));
		loanApplication.sellerID((int) data.input("sellerID"));
		loanApplication.setCarID((int) data.input("carID"));
//		loanApplication.setApplicationDate((String) data.input("applicationDate"));
		loanApplication.setLoanAmount((int) data.input("loanAmount"));
		loanApplication.setPayment((double) data.input("payment"));
		loanApplication.setMonths((int) data.input("months"));
		loanApplication.setInterestRate((double)data.input("interestRate"));
		loanApplication.setMonthlyPayment((double)data.input("monthlyPayment"));
		loanApplication.setStatus((String) data.input("status"));
		loanApplication.setNote((String) data.input("note"));

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

	public double getCalculatedInterestRate(String cprCreditRate, double bankInteretRate, int months,
			double totalAmount, double downPaymeny) {
		
		int interestRateForRKI = getInterestRateForRKIRate(cprCreditRate);
		int interestRateForDownPayment = getInterestRateForDownPayment(totalAmount,downPaymeny);
		int nterestRateRepaymentPeriod = getInterestRateRepaymentPeriod(months);

		return interestRateForRKI
			  +interestRateForDownPayment
			  +nterestRateRepaymentPeriod
			  +bankInteretRate;
	}
	
	public double getCalculatedMothlyPayment(double loanAmount, int months, double rateOfYear) {
		
		double r = (rateOfYear / 12) / 100;
		
		return (loanAmount * r)/(1-Math.pow((1+r), -months));
	}

	private int getInterestRateForRKIRate(String RKIRate) {
		
		switch (RKIRate) {
			case "A": {
				return 1;
			}
			case "B": {
				return 2;
			}
			case "C": {
				return 3;
			}default:
				return 0;
		}
	}
	
	private int getInterestRateForDownPayment(double totalAmount, double downPayment) {
		
		if((downPayment*2) < totalAmount )
			return 1;
		
		return 0;
	}
	
	private int getInterestRateRepaymentPeriod(int months) {
		
		if(months > MONTHS_WITHOUT_INTEREST_RATE)
			return 1;
		
		return 0;
	}

	public Customer getCustomer(LoanApplication loanApplication) {
		CustomerRepository repo = new CustomerRepository();
		return repo.find(loanApplication.getCustomerID());
	}

	public Employee getSeller(LoanApplication loanApplication) {
		EmployeeRepository repo = new EmployeeRepository();
		return repo.find(loanApplication.getSellerID());
	}

	public Car getCar(LoanApplication loanApplication) {
		CarRepository repo = new CarRepository();
		return repo.find(loanApplication.getCarID());
	}

	public boolean approveLoanApplication(LoanApplication loanApplication) {
		loanApplication.setStatus(LoanApplication.APPROVED);
		
		return repository.update(loanApplication);
	}

	public boolean rejectLoanApplication(LoanApplication loanApplication) {
		loanApplication.setStatus(LoanApplication.REJECTED);
		return repository.update(loanApplication);
	}

	public boolean checkSellerApprovalLimit(Employee employee, double loanAmount) {
		// if the seller is manager
		if(employee.getRole().equals(Employee.MANAGER))
			return true;
		
		SellerApprovalLimitRepository repo = new SellerApprovalLimitRepository();
//		ArrayList<SellerApprovalLimits> sellerLimit = repo.getByCondition("", "=", employee.getId());
		SellerApprovalLimits sellerLimit = repo.getMaxApprovalLimit(employee);
		
		if(sellerLimit == null)
			return false;
		
		int max = sellerLimit.getMaxApprovalLimit();
		
		return max >= loanAmount;
	}
	
	

}