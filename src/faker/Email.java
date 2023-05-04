package faker;

import java.util.ArrayList;
import java.util.Arrays;

public class Email {

	private static ArrayList<String> domains = new ArrayList<>(Arrays.asList(

			"yaho.com", "yahoo.ae", "yahoo.at", "yahoo.be", "yahoo.ca", "yahoo.ch", "yahoo.cn", "yahoo.co",
			"gmail.co.za", "gmail.com", "gmail.com.au", "gmail.com.br", "gmail.ru", "gmial.com", "icloud.com",
			"hotmail.com", "hotmail.com.ar", "hotmail.de", "hotmail.co", "hotmail.com.au", "home.no.net", "address.com",
			"allmail.net", "amail.com", "ancestry.com", "animal.net", "boom.com", "buzy.com", "centermail.com",
			"cmail.net", "cmail.org", "e-mail.com", "easy.com", "email.org", "everyone.net", "everymail.net",
			"facebook.com", "geek.com", "godmail.dk", "home-email.com", "inbox.com", "live.com", "love.com", "mail.com",
			"messages.to", "money.net"));
	
	public static String getRandomEmail() {
		String dom = Generator.getRandomElementFromArrayList(domains);
		String name = Generator.getRandomElementFromArrayList(Person.names());
		
		return name + "@" +dom;
	}
}
