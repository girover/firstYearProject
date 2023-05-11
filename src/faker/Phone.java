package faker;

import java.util.HashMap;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class Phone extends Provider {
	
	private static HashMap<String, String> codes = new HashMap<>();
	
	static {
		codes.put("us", "001");   // United States
		codes.put("ca", "001");   // Canada
		codes.put("za", "0027");  // South Africa 
		codes.put("nl", "0031");  // Netherlands 
		codes.put("fr", "0033");  // France
		codes.put("es", "0034");  // Spain 
		codes.put("it", "0039");  // Italy
		codes.put("ch", "0041");  // Switzerland 
		codes.put("gb", "0044");  // United Kingdom
		codes.put("dk", "0045");  // Denmark
		codes.put("se", "0046");  // Sweden
		codes.put("no", "0047");  // Norway
		codes.put("de", "0049");  // Germany
		codes.put("mx", "0052");  // Mexico 
		codes.put("au", "0061");  // Australia
		codes.put("nz", "0064");  // New Zealand 
		codes.put("jp", "0081");  // Japan
		codes.put("kr", "0082");  // South Korea 
		codes.put("cn", "0086");  //China 
		codes.put("in", "0091");  // 
		codes.put("fi", "00358"); // Finland
		codes.put("ae", "00971"); // United Arab Emirates	 
	}

	public static String danishPhoneNumber() {
		return codes.get("dk")+getNumericString(8);
	}
	
	public static String germanPhoneNumber() {
		return codes.get("de")+getNumericString(10);
	}
	
	public static String usPhoneNumber() {
		return codes.get("us")+getNumericString(10);
	}
	
	public static String canadaPhoneNumber() {
		return codes.get("ca")+getNumericString(10);
	}

}
