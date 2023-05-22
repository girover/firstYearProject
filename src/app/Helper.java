package app;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.scene.control.TextFormatter;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 */
public class Helper {
	
	private static Locale danishLocale = new Locale.Builder().setLanguage("da").setRegion("DK").build();
	
	private static NumberFormat numberFormat() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(danishLocale);
		return numberFormat;
	}
	
	public static String formatNumber(int number) {
		return numberFormat().format(number);
	}
	
	public static String formatNumber(double number) {
		return numberFormat().format(number);
	}
	
	private static NumberFormat currencyFormat() {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(danishLocale);
		return numberFormat;
	}
	
	public static String formatCurrency(String number) {
		return currencyFormat().format(number);
	}
	
	public static String formatCurrency(double price) {
		return currencyFormat().format(price);
		
	}
	
	public static TextFormatter<String> allowOnlyDigits(){
		
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        });
        
        return textFormatter;
	}

}
