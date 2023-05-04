package faker;

import java.util.ArrayList;
import java.util.Arrays;

public class Person extends Provider {

	private static ArrayList<String> firstNameMale = (ArrayList<String>) Arrays.asList(
				"Majed","Rasmus", "Dennis", "Christoffer", "Emil", "Simone", "Mikkel",
				"Benjamin", "Christian", "Stefan", "Shahana", "Casper", "Jonas", "Ulrikke",
				"Philip", "Marc", "Jimmi"
			); 
	
	private static ArrayList<String> firstNameFemale = (ArrayList<String>) Arrays.asList(
			"Simone", "Ulrikke"
			); 
	private static ArrayList<String> lastName = (ArrayList<String>) Arrays.asList(
			"Farhan","Sandholm", "Nørgaard", "Raaberg", "Andersen", "Thomsen", "Stigsen",
			"Arensbak", "Birkegaard", "Jepsen", "Kortsen", "Villadsen", "Thirukkumar", "Kristensen",
			"Pedersen", "Stendorf", "Olesen", "Jensen", "Larsen"
			);
	
	
	public static ArrayList<String> names(){
		return firstNameMale;
	}
	
	public static ArrayList<String> lastNames(){
		return lastName;
	}
	
	public static String CPRMale() {
		String s = getNumericString(9);
		return s + getEvenDigit();
	}
	
	public static String CPRFemale() {
		String s = getNumericString(9);
		return s + getOddDigit();
	}
	
	public static String nameMale() {
		return getRandomElementFromArrayList(firstNameMale);
	}
	
	public static String nameFemale() {
		return getRandomElementFromArrayList(firstNameFemale);
	}
	
	public static String lastName() {
		return getRandomElementFromArrayList(lastName);
	}
}
