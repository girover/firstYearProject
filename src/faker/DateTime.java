package faker;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class DateTime extends Provider {

	private static String randomDate(LocalDate minDate, LocalDate maxDate) {
		
	    Random random = new Random();
	    
	    int days = Period.between(minDate, maxDate).getDays();
	    
	    return minDate.plusDays(random.nextInt(days + 1)).toString();
	}
	
	public static String date() {
	    
	    return randomDate(LocalDate.of(1900, 1, 1), LocalDate.now());
	}
	
	public static String dateBetween(int minYear, int maxYear) {
		
		return randomDate(LocalDate.of(minYear, 1, 1), LocalDate.of(maxYear, 12, 28));
	}
	
	public static String futureDate(int maxYear) {
		
		return randomDate(LocalDate.now(), LocalDate.of(maxYear, 12, 31));
	}
}
