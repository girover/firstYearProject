package test;

import static entities.factory.Factory.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.repositories.CarRepository;
import database.repositories.CityRepository;
import database.repositories.CustomerRepository;
import database.repositories.EmployeeRepository;
import database.repositories.LoanApplicationRepository;
import entities.Car;
import entities.City;
import entities.Customer;
import entities.Employee;
import entities.LoanApplication;

/**
 * This test case class is responsible for testing methods in "CarRepository" class.
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class LoanApplicationRepositoryTest extends BaseTestCase {

	private LoanApplicationRepository repo = new LoanApplicationRepository();
	private Car car;
	private Customer customer;
	private Employee employee;
	private City city;
	
	@BeforeEach
	void createRelationships() {
		city = addCityToDatabase();
		car = addCarToDatabase();
		customer = addCustomerToDatabase();
		employee = addEmployeeToDatabase();
	}
	
	@Test
	void shouldAddNewLoanApplicationToDatabase() {
		LoanApplication loan = makeLoanApplication();
		int generatedID = repo.add(loan);
		assertTrue(generatedID > 0);
	}
	
	@Test
	void shouldAddListOfLoanApplicationsToDatabase() {
		
		int generatedRows = repo.addAll(makeLoanApplications(10));
		assertTrue(generatedRows == 10);
	}
	
	@Test
	void shouldFindLoanApplicationInDatabaseByItsId() {
		LoanApplication loanApplication = makeLoanApplication();
		
		assertTrue(repo.add(loanApplication) > 0);
		int id = loanApplication.getId();
		
		LoanApplication retrievedLoanApplication = repo.find(id);
		
		assertTrue(retrievedLoanApplication.getId() == id);
	}
	
	@Test
	void shouldGetAllLoanApplicationsFromDatabase() {

		ArrayList<LoanApplication> loanApplications = makeLoanApplications(10);
		repo.addAll(loanApplications);
		
		assertTrue(repo.getAll().size() == 10);
	}
	
	@Test
	void shouldCountLoanApplicationsInTheDatabase() {
		
		assertTrue(repo.add(makeLoanApplication()) > 0);
		assertTrue(repo.add(makeLoanApplication()) > 0);
		
		assertTrue(repo.count() == 2);
	}
	
	@Test
	void shouldDeleteLoanApplicationFromDatabase() {
		LoanApplication loanApplication = makeLoanApplication();
		assertTrue(repo.add(loanApplication) > 0);
		
		assertTrue(repo.delete(loanApplication));
		assertTrue(repo.count() == 0);
	}
	
	@Test
	void shouldPerformCorrectLoanApplicationUpdateInDatabase() {
		LoanApplication loanApplication = makeLoanApplication();
		assertTrue(repo.add(loanApplication) > 0);
		
		loanApplication.setInterestRate(9);
		loanApplication.setLoanAmount(500000);
		loanApplication.setPayment(500000);

		assertTrue(repo.update(loanApplication));
		
		LoanApplication retreivedLoanApplication = repo.find(loanApplication.getId());
		
		assertTrue(retreivedLoanApplication.getInterestRate() == 9);
		assertTrue(retreivedLoanApplication.getLoanAmount() == 500000);
		assertTrue(retreivedLoanApplication.getPayment() == 500000);
	}
	
	private LoanApplication makeLoanApplication() {
		
		LoanApplication loan = loanApplicationFactory().make();
		
		loan.setCarID(car.getId());
		loan.setCustomerID(customer.getId());
		loan.setSellerID(employee.getId());
		
		return loan;
	}
	
	private ArrayList<LoanApplication> makeLoanApplications(int quantity) {
		
		ArrayList<LoanApplication> loans = new ArrayList<>();
		for (int i = 0; i< quantity; i++) {
			
			LoanApplication loan = loanApplicationFactory().make();
			loan.setCarID(car.getId());
			loan.setCustomerID(customer.getId());
			loan.setSellerID(employee.getId());
			
			loans.add(loan);
		}
		
		return loans;
	}
	
	private Car addCarToDatabase() {
		
		CarRepository repo = new CarRepository();
		Car car = carFactory().make();
		repo.add(car);
		
		return car;
	}
	
	private Customer addCustomerToDatabase() {
		
		CustomerRepository repo = new CustomerRepository();
		Customer customer = customerFactory().make();
		
		customer.setZipCode(city.getZipCode());
		repo.add(customer);
		
		return customer;
	}
	
	private Employee addEmployeeToDatabase() {
		EmployeeRepository repo = new EmployeeRepository();
		Employee employee = employeeFactory().make();
		
		employee.setZipCode(city.getZipCode());
		repo.add(employee);
		
		return employee;
	}
	
	private City addCityToDatabase() {
		
		CityRepository cRepo = new CityRepository();
		City city = cityFactory().make();
		cRepo.add(city);
		
		return city;
	}

}
