package faker;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public abstract class Provider {

	private static String alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
	private static String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	private static String uppercaseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String lowercaseString = "abcdefghijklmnopqrstuvxyz";
	private static String numericString = "0123456789";
	private static String oddDigitString = "13579";
	private static String evenDigitString = "02468";

	protected static String getAlphaString(int n) {
		return getRandumString(alphaString, n);
	}

	protected static String getNumericString(int digits) {

		return getRandumString(numericString, digits);
	}

	protected static String getAlphaNumericString(int length) {

		return getRandumString(alphaNumericString, length);
	}

	protected static String text(int words) {

		StringBuilder sb = new StringBuilder();
		int lineLength = 60;
		int currentLine = 0;


		for (int i = 0; i <= words; i++) {
			
			int wordLength = getRandomInteger(3, 10);
			
			String word = getAlphaString(wordLength).toLowerCase();
			currentLine += wordLength;

			if (currentLine > lineLength) {
				sb.append("\n");
				currentLine = 0;
			}

			sb.append(word + " ");
		}

		return sb.toString();
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

	protected static int getRandomInteger(int min, int max) {
		Random r = new Random();

		return r.nextInt(max - min) + min;

	}

	protected static String getOddDigit() {
		return getRandumString(oddDigitString, 1);
	}

	protected static String getEvenDigit() {
		return getRandumString(evenDigitString, 1);
	}

	protected static float getRandomFloat(int min, int max) {
		Random r = new Random();

		return min + r.nextFloat() * (max - min);

	}

	protected static String getRandomElementFromArrayList(ArrayList<String> list) {
		return list.get(getRandomInteger(0, list.size()));
	}

	protected static String getRandomElementFromArray(String[] array) {
		return array[getRandomInteger(0, array.length)];
	}

	protected static String getOneOf(String... elements) {

		return elements[getRandomInteger(0, elements.length)];
	}

	protected static int getOneOf(int... elements) {

		return elements[getRandomInteger(0, elements.length)];
	}

}
