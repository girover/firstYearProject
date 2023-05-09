package faker;

public class Password extends Provider {

	public static String stringPassword(int length) {
		return getAlphaString(length);
	}
	
	public static String digitPassword(int length) {
		return getNumericString(length);
	}
}
