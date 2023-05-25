package database.entities.factory;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class Factory {
	
	public static CarFactory carFactory() {
		return new CarFactory();
	}
	
	public static CustomerFactory customerFactory() {
		return new CustomerFactory();
	}
	
	public static LoanApplicationFactory loanApplicationFactory() {
		return new LoanApplicationFactory();
	}
	
	public static SellerApprovalLimitFactory sellerApprovalLimitFactory() {
		return new SellerApprovalLimitFactory();
	}
	
	public static EmployeeFactory employeeFactory() {
		return new EmployeeFactory();
	}
	
	public static UserFactory userFactory() {
		return new UserFactory();
	}
	
	public static CityFactory cityFactory() {
		return new CityFactory();
	}
}
