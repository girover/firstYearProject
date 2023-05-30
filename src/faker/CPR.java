package faker;

/**
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 *
 */
public class CPR extends Provider {

	public static String maleCPR() {
		return getNumericString(9)+getOddDigit();
	}
	
	public static String femaleCPR() {
		return getNumericString(9)+getEvenDigit();
	}
}
