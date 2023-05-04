package faker;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
	
	private static String alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
	private static String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	private static String uppercaseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String lowercaseString = "abcdefghijklmnopqrstuvxyz";
	private static String numericString = "0123456789";

	public static String getAlphaString(int n) {
		return getRandumString(alphaString, n);
	}

	public static String getNumericString(int count) {

		return getRandumString(numericString, count);
	}

	public static String getAlphaNumericString(int count) {

		return getRandumString(alphaNumericString, count);
	}

	private static String getRandumString(String string, int n) {
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (string.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(string.charAt(index));
		}

		return sb.toString();
	}

	public static int getRandomInteger(int min, int max) {
		Random r = new Random();
		
		return r.nextInt(max - min) + min;

	}
	
	public static float getRandomFloat(int min, int max) {
		Random r = new Random();
		
		return min + r.nextFloat() * (max - min);
		
	}
	
	public static String getRandomElementFromArrayList(ArrayList<String> list) {
		return list.get(getRandomInteger(0, list.size()));
	}
	
	public static String name() {
		return getRandomElementFromArrayList(Person.names());
	}
	
	public static String lastName() {
		return getRandomElementFromArrayList(Person.lastNames());
	}
	
	public static String email() {
		return Email.getRandomEmail();
	}
}
