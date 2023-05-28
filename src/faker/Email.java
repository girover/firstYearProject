package faker;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class Email extends Provider {

	private static final String[] domains = {
			"yaho.com", "yahoo.ae", "yahoo.at", "yahoo.be", "yahoo.ca", "yahoo.ch", "yahoo.cn", "yahoo.co",
			"gmail.co.za", "gmail.com", "gmail.com.au", "gmail.com.br", "gmail.ru", "gmial.com", "icloud.com",
			"hotmail.com", "hotmail.com.ar", "hotmail.de", "hotmail.co", "hotmail.com.au", "home.no.net", "address.com",
			"allmail.net", "amail.com", "ancestry.com", "animal.net", "boom.com", "buzy.com", "centermail.com",
			"cmail.net", "cmail.org", "e-mail.com", "easy.com", "email.org", "everyone.net", "everymail.net",
			"facebook.com", "geek.com", "godmail.dk", "home-email.com", "inbox.com", "live.com", "love.com", "mail.com",
			"messages.to", "money.net"};
	
	public static String email() {
		return generateEmail(getRandomElementFromArray(Person.names()), getRandomElementFromArray(domains));
	}
	
	public static String gmail() {
		return generateEmail(getRandomElementFromArray(Person.names()), "gmail.com");
	}
	
	public static String hotmail() {
		return generateEmail(getRandomElementFromArray(Person.names()), "hotmail.com");
	}
	
	public static String yahoo() {
		return generateEmail(getRandomElementFromArray(Person.names()), "yahoo.com");
	}
	
	/**
	 * We add
	 * @param name
	 * @param domain
	 * @return
	 */
	private static String generateEmail(String name, String domain) {
		return name + getRandomInteger(1000, 9999) + "@" + domain; 
	}
}
