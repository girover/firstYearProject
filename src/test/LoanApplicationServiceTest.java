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
	private static final int A_RATE = 1;
	private static final int B_RATE = 2;
	private static final int C_RATE = 3;
	private static final int LESS_THAN_HALF_THE_PRICE_RATE = 1;
	private static final int MORE_THAN_HALF_THE_PRICE_RATE = 0;
	private static final int LESS_THAN_THREE_YEARS_RATE = 0;
	private static final int MORE_THAN_THREE_YEARS_RATE = 1;
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
					  +A_RATE
					  +LESS_THAN_HALF_THE_PRICE_RATE
					  +LESS_THAN_THREE_YEARS_RATE
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
				+B_RATE
				+LESS_THAN_HALF_THE_PRICE_RATE
				+LESS_THAN_THREE_YEARS_RATE
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
				+C_RATE
				+LESS_THAN_HALF_THE_PRICE_RATE
				+LESS_THAN_THREE_YEARS_RATE
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
				+A_RATE
				+MORE_THAN_HALF_THE_PRICE_RATE
				+LESS_THAN_THREE_YEARS_RATE
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
				+A_RATE
				+LESS_THAN_HALF_THE_PRICE_RATE
				+LESS_THAN_THREE_YEARS_RATE
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
				+A_RATE
				+LESS_THAN_HALF_THE_PRICE_RATE
				+MORE_THAN_THREE_YEARS_RATE
				;
		assertTrue(totalInterestRate == expectedInterest);
	}
	
	@Test
	void itShouldCalculateCorrectInterestRateIfRepaymentIsLessThanThreeYears() {
		String rkiResult = "A";
		double downPayment = CAR_PRICE/2 - 1; // less that half the price
		int months = THREE_YEARS - 1; // more that 3 years
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		
		double expectedInterest = 
				BANK_TODAYS_INTEREST_RATE
				+A_RATE
				+LESS_THAN_HALF_THE_PRICE_RATE
				+LESS_THAN_THREE_YEARS_RATE
				;
		assertTrue(totalInterestRate == expectedInterest);
	}
	
	@Test
	void itShouldCalculateCorrectInterestRateIfRepaymentIsExactlyThreeYears() {
		String rkiResult = "A";
		double downPayment = CAR_PRICE / 2 - 1; // less that half the price
		int months = THREE_YEARS; // exactly 3 years
		
		double totalInterestRate = service.getCalculatedInterestRate(rkiResult, BANK_TODAYS_INTEREST_RATE, months, CAR_PRICE, downPayment);
		
		double expectedInterest = 
				BANK_TODAYS_INTEREST_RATE
				+A_RATE
				+LESS_THAN_HALF_THE_PRICE_RATE
				+LESS_THAN_THREE_YEARS_RATE
				;
		assertTrue(totalInterestRate == expectedInterest);
	}
	
	@Test
	void itShouldGetCorrectRateForMonthsIfMonthesIsLessThanThreeYears() {
		int months = THREE_YEARS - 1;
		int monthsRate = service.getMonthsInterestRate(months);
		assertTrue(monthsRate == LESS_THAN_THREE_YEARS_RATE);
	}
	
	@Test
	void itShouldGetCorrectRateForMonthsIfMonthesIsMoreThanThreeYears() {
		int months = THREE_YEARS + 1;
		int monthsRate = service.getMonthsInterestRate(months);
		assertTrue(monthsRate == MORE_THAN_THREE_YEARS_RATE);
	}
	
	@Test
	void itShouldGetCorrectRateForDownPaymentIfDownPaymentIsMoreThanHalfThePrice() {
		double downPayment = (CAR_PRICE / 2) + 1000;
		double totalPrice = CAR_PRICE;
		int downPaymentRate  = service.getDownPaymentInterestRate(downPayment, totalPrice);
		
		assertTrue(downPaymentRate == MORE_THAN_HALF_THE_PRICE_RATE);
	}
	
	@Test
	void itShouldGetCorrectRateForDownPaymentIfDownPaymentIsLessThanHalfThePrice() {
		double downPayment = (CAR_PRICE / 2) - 1;
		double totalPrice = CAR_PRICE;
		int downPaymentRate  = service.getDownPaymentInterestRate(downPayment, totalPrice);
		
		assertTrue(downPaymentRate == LESS_THAN_HALF_THE_PRICE_RATE);
	}

}
