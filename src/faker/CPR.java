package faker;

public class CPR extends Provider {

	public static String maleCPR() {
		return getNumericString(9)+getOddDigit();
	}
	
	public static String femaleCPR() {
		return getNumericString(9)+getEvenDigit();
	}
}
