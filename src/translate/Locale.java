package translate;

import java.util.HashMap;

/**
 * This abstract class is for using multiple language GUI. 
 * This class provides a set of methods that can be used to use more
 * than one language in graphical user interface.
 *
 * Every class that inherits this class has its own list of
 * translatable text.
 *   
 * @version 1.0
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public abstract class Locale {

	protected static HashMap<String, String> list = new HashMap<>();

	public String get(String translatable) {
		
		String translated = list.get(translatable);
		
		if(translated != null && !translated.isBlank())
			return translated;
		
		return translatable;
	}
}
