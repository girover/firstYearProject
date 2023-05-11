package faker;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
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
