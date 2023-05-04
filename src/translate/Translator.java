package translate;

/**
 * This class is for using multiple language GUI. 
 * This class provides a set of methods that can be used to tuse more
 * than one language in user interface.
 *
 * @version 1.0
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class Translator {

	private static Locale local;
	
	public Translator(String language) {
		
		if(language.equals("en"))
			local = new En();
		else if(language.equals("dk"))
			local = new Dk();
		else {
			local = new En();
		}
	}
	
	public String translate(String translatableText) {
		
		return local.get(translatableText);
	}
}
