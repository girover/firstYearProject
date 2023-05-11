package faker;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class Ferrari extends Provider {

	private static final String[] models = {
			"F8 Tributo", "812 Superfast", "SF90 Stradale", "Portofino", "Roma", 
			"488 GTB", "488 Spider", "488 Pista", "458 Italia", "458 Spider", "458 Speciale", 
			"California T", "LaFerrari", "Enzo Ferrari", "F430", "F430 Spider", 
			"599 GTB Fiorano", "FF", "F12 Berlinetta", "F12tdf", "GTC4Lusso", "Monza SP1", 
			"Monza SP2", "SF Monza", "Testarossa", "288 GTO", "F40", "F50", "Dino 246 GT", 
			"365 GTB/4 Daytona", "250 GTO", "250 GT Lusso", "250 California Spyder", "330 P4", 
			"512 S", "312 PB", "BB 512", "F512 M", "550 Maranello", "575M Maranello", 
			"612 Scaglietti", "599 GTO", "458 Italia Special Editions", "458 MM Speciale", 
			"458 MM Speciale Spider", "J50", "SP30", "SP38", "SP1JC", "SP2JC", "P80/C", 
			"Omologata"
		};
	
	public static String[] getModels() {
		return models;
	}
}
