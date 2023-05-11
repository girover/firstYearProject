package faker;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class Password extends Provider {

	public static String stringPassword(int length) {
		return getAlphaString(length);
	}
	
	public static String digitPassword(int length) {
		return getNumericString(length);
	}
}
