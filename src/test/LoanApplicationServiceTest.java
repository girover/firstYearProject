package test;

import org.junit.jupiter.api.Test;
import services.LoanApplicationService;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class LoanApplicationServiceTest extends BaseTestCase {

	private LoanApplicationService service = new LoanApplicationService();
	
	/**
	 * ------------------------------------
	 * Additional Interest Rates
	 * ------------------------------------
	 */
	private static final int A = 1;
	private static final int B = 2;
	private static final int C = 3;
	private static final int lESS_THAN_HALF_THE_PRICE = 1;
	private static final int MORE_THAN_HALF_THE_PRICE = 0;
	private static final int LESS_THAN_THREE_YEARS = 0;
	private static final int MORE_THAN_THREE_YEARS = 1;
	/*
	 * -------------------------------------
	 */
	
	private static final double BANK_TODAYS_INTEREST_RATE = 5.5;
	private static final double CAR_PRICE = 500000;
	private static final int THREE_YEARS = 36; // 36 months

	@Test
	void itShouldCalculateInterestRateForRKICreditA() {
		String rkiResult = "A";
		double downPayment = CAR_PRICE / 2 - 1; // less that half the price
		int months = THREE_YEARS - 1; // less that 3 years
		
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		double expectedInterest = 
					   BANK_TODAYS_INTEREST_RATE
					  +A
					  +lESS_THAN_HALF_THE_PRICE
					  +LESS_THAN_THREE_YEARS
					   ;
		assertTrue(totalInterestRate == expectedInterest);
	}
	
	@Test
	void itShouldCalculateCorrectInterestRateForRKICreditB() {
		String rkiResult = "B";
		double downPayment = CAR_PRICE / 2 - 1; // less that half the price
		int months = THREE_YEARS - 1; // less that 3 years
		
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		double expectedInterest = 
				BANK_TODAYS_INTEREST_RATE
				+B
				+lESS_THAN_HALF_THE_PRICE
				+LESS_THAN_THREE_YEARS
				;
		assertTrue(totalInterestRate == expectedInterest);
	}
	
	@Test
	void itShouldCalculateCorrectInterestRateForRKICreditC() {
		String rkiResult = "C";
		double downPayment = CAR_PRICE / 2 - 1; // less that half the price
		int months = THREE_YEARS - 1; // less that 3 years
		
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		double expectedInterest = 
				BANK_TODAYS_INTEREST_RATE
				+C
				+lESS_THAN_HALF_THE_PRICE
				+LESS_THAN_THREE_YEARS
				;
		assertTrue(totalInterestRate == expectedInterest);
	}
	
	@Test
	void itShouldCalculateCorrectInterestRateIfDownPaymentIsMoreThatHalfPrice() {
		String rkiResult = "A";
		double downPayment = CAR_PRICE/2 + 1000; // more that half the price
		int months = THREE_YEARS - 1; // less that 3 years
		
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		double expectedInterest = 
				BANK_TODAYS_INTEREST_RATE
				+A
				+MORE_THAN_HALF_THE_PRICE
				+LESS_THAN_THREE_YEARS
				;
		assertTrue(totalInterestRate == expectedInterest);
	}
	
	@Test
	void itShouldCalculateCorrectInterestRateIfDownPaymentIsLessThatHalfPrice() {
		String rkiResult = "A";
		double downPayment = CAR_PRICE/2 - 1; // less that half the price
		int months = THREE_YEARS - 1; // less that 3 years
		
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		double expectedInterest = 
				BANK_TODAYS_INTEREST_RATE
				+A
				+lESS_THAN_HALF_THE_PRICE
				+LESS_THAN_THREE_YEARS
				;
		assertTrue(totalInterestRate == expectedInterest);
	}
	
	@Test
	void itShouldCalculateCorrectInterestRateIfRepaymentIsMoreThanThreeYears() {
		String rkiResult = "A";
		double downPayment = CAR_PRICE/2 - 1; // less that half the price
		int months = THREE_YEARS + 1; // more that 3 years
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		
		double expectedInterest = 
				BANK_TODAYS_INTEREST_RATE
				+A
				+lESS_THAN_HALF_THE_PRICE
				+MORE_THAN_THREE_YEARS
				;
		assertTrue(totalInterestRate == expectedInterest);
	}
	void itShouldCalculateCorrectInterestRateIfRepaymentIsExactlyThreeYears() {
		String rkiResult = "A";
		double downPayment = CAR_PRICE / 2 - 1; // less that half the price
		int months = THREE_YEARS; // exactly 3 years
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		
		double expectedInterest = 
				BANK_TODAYS_INTEREST_RATE
				+A
				+lESS_THAN_HALF_THE_PRICE
				+LESS_THAN_THREE_YEARS
				;
		assertTrue(totalInterestRate == expectedInterest);
	}

}
