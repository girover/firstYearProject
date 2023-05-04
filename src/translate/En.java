package translate;

/**
 * This class is a part of translating system for using multiple language GUI. 
 * This class provides a list of translatable texts in English language.
 *
 *   
 * @version 1.0
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class En extends Locale {

	public En() {
		if(list.size() == 0)
		 fillList();
	}

	private void fillList() {
		list.put("login", "Login");
		list.put("logout", "Logout");
	}
}
