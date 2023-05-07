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
	
	/**
	 * Translate the specified text to the specified language
	 * in the configs file.
	 * 
	 * @param translatableText
	 * @return
	 */
	public String translate(String translatableText) {
		
		return local.get(translatableText);
	}
	
	/**
	 * Translate the specified text to the specified language
	 * in the configs file. If the text not found in the dictionary
	 * then return the specified defaultText.
	 * 
	 * @param translatableText
	 * @param defaultText
	 * @return
	 */
	public String translate(String translatableText, String defaultText) {
		
		return local.get(translatableText, defaultText);
	}
}
