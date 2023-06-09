package services;

import java.util.ArrayList;

import database.repositories.CarRepository;
import database.repositories.CustomerRepository;
import database.repositories.EmployeeRepository;
import database.repositories.LoanApplicationRepository;
import entities.Car;
import entities.Customer;
import entities.Employee;
import entities.Entity;
import entities.LoanApplication;
import entities.User;

/**
 * This class is a part of Service Layer (Business Logic Layer).
 *
 * This class provides a set of methods that can be used to deal with
 * loanApplications. such as retrieving all loanApplication, retrieving a set of
 * loanApplications, retrieving a single loanApplication by ID, updating a
 * loanApplication, and deleting a loanApplication etc..
 * 
 * 
 * @author Rasmus Lysgaard Villadsen - <b style="color:red">
 *         mrmaklie@gmail.com</b> -
 *         <a href="https://github.com/MrMaklie">Github</a>
 *         
 * @author Majed Hussein Farhan - <b style="color:red">
 *         girover.mhf@gmail.com</b> -
 *         <a href="https://github.com/girover">Github</a>
 */
public class LoanApplicationService extends BaseResourceService {
	
	public static final int THREE_YEARS_MONTHS = 36;
	public static final int A_RKI_INTEREST_RATE = 1;
	public static final int B_RKI_INTEREST_RATE = 2;
	public static final int C_RKI_INTEREST_RATE = 3;
	public static final int LESS_THAT_HALF_PRICE_RATE = 1;
	public static final int MORE_THAT_THREE_YEARS_RATE = 1;

	public LoanApplicationService() {
		repository = new LoanApplicationRepository();
	}

	@Override
	public LoanApplication find(Object id) {
		LoanApplication loanApplication = (LoanApplication) repository.find(id);
		return loanApplication;
	}

	@Override
	public ArrayList<LoanApplication> getAll() {

		return (ArrayList<LoanApplication>) repository.getAll();
	}

	@Override
	public LoanApplication create(Entity entity) {

		LoanApplication loanApplication = (LoanApplication)entity;

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
	
	public double parseBankInterestRateFromTotalRate(LoanApplication loanApplication) {
		double totalInterestRate = loanApplication.getInterestRate();
		if(loanApplication.getMonths() < THREE_YEARS_MONTHS)
			totalInterestRate--;
		
		if((loanApplication.getPayment() * 2) < loanApplication.getLoanAmount())
			totalInterestRate--;
		
		return totalInterestRate;
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
				return A_RKI_INTEREST_RATE;
			}
			case "B": {
				return B_RKI_INTEREST_RATE;
			}
			case "C": {
				return C_RKI_INTEREST_RATE;
			}default:
				return 0;
		}
	}
	
	private int getInterestRateForDownPayment(double totalAmount, double downPayment) {
		
		if((downPayment*2) < totalAmount )
			return LESS_THAT_HALF_PRICE_RATE;
		
		return 0;
	}
	
	public int getMonthsInterestRate(int months) {
		if(months > THREE_YEARS_MONTHS)
			return MORE_THAT_THREE_YEARS_RATE;
		
		return 0;
	}
	
	public int getDownPaymentInterestRate(double downPayment, double totalAmount) {
		if((downPayment * 2)  < totalAmount)
			return LESS_THAT_HALF_PRICE_RATE;
		
		return 0;
	}
	
	private int getInterestRateRepaymentPeriod(int months) {
		
		if(months > THREE_YEARS_MONTHS)
			return MORE_THAT_THREE_YEARS_RATE;
		
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
	
	public ArrayList<LoanApplication> getCustomerLoanApplications(Customer customer){
		return (ArrayList<LoanApplication>) repository.getByCondition("customerID", "=", customer.getId());
	}

	public boolean approveLoanApplication(LoanApplication loanApplication) {
		loanApplication.setStatus(LoanApplication.APPROVED);
		
		return repository.update(loanApplication);
	}

	public boolean rejectLoanApplication(LoanApplication loanApplication) {
		loanApplication.setStatus(LoanApplication.REJECTED);
		return repository.update(loanApplication);
	}

	public boolean checkSellerApprovalLimit(User user, double loanAmount) {
		// if the seller is manager
		if(user.getEmployee().getRole().equals(Employee.MANAGER))
			return true;
		
		int sellerLimit = user.getMaxApprovalLimit();
		
		if(sellerLimit == 0)
			return false;
		
		return sellerLimit >= loanAmount;
	}
	
	public ArrayList<String[]> getLoanApplicationDataAsListForCSV(LoanApplication loanApplication){
		
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		data.add(new String[]{"Loan Application Information\n"});
		data.addAll(loanApplication.getAsCsvList());
		data.add(new String[]{"\n"});
		
		CustomerRepository customerRepo = new CustomerRepository();
		Customer customer = customerRepo.find(loanApplication.getCustomerID());
		
		data.add(new String[]{"Customer Information\n"});
		data.addAll(customer.getAsCsvList());
		data.add(new String[]{"\n"});
		
		CarRepository carRepo = new CarRepository();
		Car car = carRepo.find(loanApplication.getCarID());
		
		data.add(new String[]{"Car Information\n"});
		data.addAll(car.getAsCsvList());
		
		return data;
	}

	public int getAddedRateForMonths(LoanApplication loanApplication) {
		return (loanApplication.getMonths() > THREE_YEARS_MONTHS) ? 1 : 0;
	}

	public int getAddedRateForPayment(LoanApplication loanApplication, double price) {
		if(loanApplication.getPayment() * 2 < price)
			return 1;
		
		return 0;
	}

}