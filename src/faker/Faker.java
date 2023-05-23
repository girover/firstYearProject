package faker;

import database.entities.Car;
import database.entities.Customer;
import database.entities.Employee;
import database.entities.LoanApplication;
import database.entities.SellerApprovalLimits;
import database.entities.User;

/**
 * This static class is responsible for generating fake data.
 * Like fake car, employee, customer, user loan application etc.
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 *
 */
public class Faker extends Provider {

	/**
	 * -----------------------------------------------------------------------
	 * Generating fake Users section
	 * -----------------------------------------------------------------------
	 */
	public static User userMale() {
		String name = Person.firstNameMale();
		return generateUser(name);
	}

	public static User userFemale() {
		String name = Person.firstNameFemale();
		return generateUser(name);
	}
	
	public static User user() {
		String username = Person.firstName();
		return generateUser(username);
	}

	private static User generateUser(String name) {

		User user = new User();

		String password = Password.digitPassword(getRandomInteger(4, 8));

		user.setUserName(name);
		user.setPassword(password);
		user.setEmployeeId(0);

		return user;
	}

	/**
	 * -----------------------------------------------------------------------
	 * Generating fake Employees section
	 * -----------------------------------------------------------------------
	 */
	public static Employee employee() {

		return generateEmployee(Person.firstName(), getOneOf("admin", "seller"));
	}

	public static Employee employeeMale() {

		return generateEmployee(Person.firstNameMale(), getOneOf("admin", "seller"));
	}

	public static Employee employeeFemale() {

		return generateEmployee(Person.firstNameFemale(), getOneOf("admin", "seller"));
	}

	public static Employee employeeMaleAdmin() {

		return generateEmployee(Person.firstNameMale(), "admin");
	}

	public static Employee employeeFemaleAdmin() {

		return generateEmployee(Person.firstNameFemale(), "admin");
	}

	private static Employee generateEmployee(String firstName, String role) {

		Employee employee = new Employee();

		employee.setFirstName(firstName);
		employee.setLastName(Person.lastName());
		employee.setPhone(Phone.danishPhoneNumber());
		employee.setEmail(Email.email());
		employee.setAddress(Address.addressApartment());
		employee.setCity(City.getCity());
		employee.setZipCode(City.getZipCode());
		employee.setHireDate(DateTime.dateBetween(2000, 2023));
		employee.setDepartment("sales");
		employee.setRole(role);

		return employee;
	}

	/**
	 * -----------------------------------------------------------------------
	 * Generating fake Cars section
	 * -----------------------------------------------------------------------
	 */
	public static Car car() {
		return generateCar((byte) 0);
	}

	public static Car soldCar() {
		return generateCar((byte) 1);
	}

	public static Car unsoldCar() {
		return generateCar((byte) 0);
	}

	private static Car generateCar(byte sold) {
		Car car = new Car();
		car.setBrand("ferrari");
		car.setModel(getRandomElementFromArray(Ferrari.getModels()));
		car.setYear(getRandomInteger(2000, 2022));
		car.setColor(Color.color());
		car.setMileage(getRandomInteger(0, 200000));
		car.setTransmission(getOneOf("manual", "automatic"));
		car.setFuelType(getOneOf("benzin", "diesel", "biodiesel", "hybrid", "el"));
		car.setEngineSize(getRandomFloat(2, 10));
		car.setHorsepower(getRandomInteger(200, 901));
		car.setSeats(getOneOf(2, 4));
		car.setDoors(getOneOf(2, 4));
		car.setPrice(getRandomInteger(500000, 4000000));
		car.setVin(getAlphaNumericString(17).toUpperCase());
		car.setSold(sold);
		car.setDescription(text(getRandomInteger(10, 200)));

		return car;
	}

	/**
	 * -----------------------------------------------------------------------
	 * Generating fake Customer section
	 * -----------------------------------------------------------------------
	 */
	public static Customer customer() {
		return generateCustomer();
	}

	private static Customer generateCustomer() {

		Customer customer = new Customer();

		customer.setCPRHash(getAlphaNumericString(64));
		customer.setFirstName(Person.firstName());
		customer.setLastName(Person.lastName());
		customer.setPhone(Phone.danishPhoneNumber());
		customer.setEmail(Email.gmail());
		customer.setAddress(Address.addressApartment());
		String zipCode = City.getZipCode();
		customer.setCity(City.getCity(zipCode));
		customer.setZipCode(zipCode);

		return customer;
	}

	/**
	 * -----------------------------------------------------------------------
	 * Generating fake SellerApprovalLimit section
	 * -----------------------------------------------------------------------
	 */
	public static SellerApprovalLimits sellerApprovalLimit() {
		return sellerApprovalLimit(1000, getRandomThousandBetween(200000, 1000000));
	}

	public static SellerApprovalLimits sellerApprovalLimit(int employeeID) {
		return sellerApprovalLimit(employeeID, getRandomThousandBetween(200000, 1000000));
	}

	public static SellerApprovalLimits sellerApprovalLimit(int employeeID, int maxApprovalLimt) {

		SellerApprovalLimits sellerAppLimit = new SellerApprovalLimits();

		sellerAppLimit.setEmployeeID(employeeID);
		sellerAppLimit.setMaxApprovalLimit(maxApprovalLimt);

		return sellerAppLimit;
	}

	/**
	 * -----------------------------------------------------------------------
	 * Generating fake LoanApplication Section
	 * -----------------------------------------------------------------------
	 */
	public static LoanApplication loanApplication() {
		LoanApplication loanApplication = new LoanApplication();

		loanApplication.setCarID(0);
		loanApplication.setCustomerID(0);
		loanApplication.setInterestRate(getRandomFloat(3, 7));
		loanApplication.setLoanAmount(getRandomThousandBetween(200000, 2000000));
		loanApplication.setMonths(getRandomInteger(36, 288));
		loanApplication.setPayment(getRandomFloat(200000, 500000));
		loanApplication.setApplicationDate(DateTime.dateBetween(2021, 2024));
		loanApplication.setMonthlyPayment(getRandomFloat(3000, 26000));
		loanApplication.setNote(text(getRandomInteger(100, 200)));
		loanApplication.setStatus(getOneOf("pending", "processing", "approved", "rejected"));
		
		return loanApplication;
	}

}
