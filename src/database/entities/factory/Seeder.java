package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import database.entities.Customer;
import database.entities.Employee;
import database.entities.LoanApplication;
import database.entities.SellerApprovalLimits;
import database.entities.User;
import database.repositories.CarRepository;
import database.repositories.CustomerRepository;
import database.repositories.EmployeeRepository;
import database.repositories.LoanApplicationRepository;
import database.repositories.SellerApprovalLimitRepository;
import database.repositories.UserRepository;

public class Seeder {

	public void seed() {
		
	}
	
	public void seedCarTable(int rows) {
		
		CarRepository repo = new CarRepository();
		
		ArrayList<Car> cars = Factory.carFactory().make(rows);

		repo.addAll(cars);
	}
	
	public void seedEmployeeTable(int rows) {
		
		EmployeeRepository repo = new EmployeeRepository();
		
		ArrayList<Employee> cars = Factory.employeeFactory().make(rows);

		repo.addAll(cars);
	}
	
	public void seedUserTable(int rows) {
		
		UserRepository repo = new UserRepository();
		
		ArrayList<User> cars = Factory.userFactory().make(rows);
		
		repo.addAll(cars);
	}
	
	public void seedCustomerTable(int rows) {
		
		CustomerRepository repo = new CustomerRepository();
		
		ArrayList<Customer> cars = Factory.customerFactory().make(rows);
		
		repo.addAll(cars);
	}
	
	public void seedLoanApplicationTable(int rows) {
		
		LoanApplicationRepository repo = new LoanApplicationRepository();
		
		ArrayList<LoanApplication> cars = Factory.loanApplicationFactory().make(rows);
		
		repo.addAll(cars);
	}
	
	public void seedSellerApprovalLimitTable(int rows) {
		
		SellerApprovalLimitRepository repo = new SellerApprovalLimitRepository();
		
		ArrayList<SellerApprovalLimits> cars = Factory.sellerApprovalLimitFactory().make(rows);
		
		repo.addAll(cars);
	}
}
