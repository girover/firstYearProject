package faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class Person extends Provider {

	private static final String[] firstNameMale = {
				"Majed","Rasmus", "Dennis", "Christoffer", "Emil", "Simone", "Mikkel",
				"Benjamin", "Christian", "Stefan", "Shahana", "Casper", "Jonas", "Ulrikke",
				"Philip", "Marc", "Jimmi", "Adam", "Adrian", "Agner", "Albert", "Alexander", 
				"Alfred", "Allan", "Anders", "Andreas", "Anker", "Anton", "Arne", "Arthur", 
				"Asger", "August", "Axel", "Benjamin", "Bent", "Bernhard", "Bjarke", "Bo", 
				"Borge", "Brian", "Bruno", "Carl", "Carsten", "Casper", "Charles", 
				"Christian", "Christopher", "Claus", "Clemens", "Conrad", "Dag", "Daniel", 
				"Dennis", "Dirch", "Ebbe", "Egon", "Eilif", "Einar", "Ejvind", "Elias", 
				"Elof", "Emil", "Erik", "Esben", "Eskild", "Evald", "Felix", "Ferdinand", 
				"Finn", "Flemming", "Frans", "Frederik", "Frode", "Gabriel", "Georg", "Gert", 
				"Gorm", "Gregers", "Gunnar", "Gustav", "Hakon", "Halfdan", "Hans", "Harald", 
				"Harry", "Helge", "Henning", "Henrik", "Herbert", "Hjalmar", "Holger", "Hugo", 
				"Ib", "Ivar", "Jacob", "Jakob", "Jan", "Jannik", "Jens", "Jesper", "Johannes", 
				"Jonas", "Jorgen", "Jorn", "Juel", "Julius", "Jussi", "Jytte", "Kaj", "Karl", 
				"Kasper", "Keld", "Kenneth", "Kim", "Kjeld", "Klaus", "Knud", "Kristian", 
				"Kurt", "Lars", "Laust", "Laurits", "Lennart", "Leo", "Leopold", "Liam", 
				"Ludvig", "Mads", "Magnus", "Malte", "Marcus", "Martin", "Mathias", "Mats", 
				"Mikkel", "Mogens", "Morten", "Niklas", "Niels", "Nils", "Noah", "Ole", 
				"Oliver", "Oskar", "Ove", "Palle", "Patrick", "Paul", "Per", "Peter", "Poul", 
				"Rasmus", "René", "Richard", "Robert", "Robin", "Rolf", "Ruben", "Sander", 
				"Søren", "Sebastian", "Sigurd", "Simon", "Sven", "Svend", "Søren", "Terje", 
				"Theodor", "Thor", "Thorvald", "Tobias", "Tom", "Tommy", "Torben", "Torkild", 
				"Torkil", "Torsten", "Troels", "Ulrik", "Valdemar", "Vidar", "Viggo", 
				"Vilhelm", "Villy", "Vincent", "Vitus", "Walter", "William", "Åge", "Åke", 
				"Åmund", "Ørjan", "Øyvind"
			}; 
	
	private static final String[] firstNameFemale = {
			"Agnes", "Alberte", "Alexandra", "Alma", "Amanda", "Amalie", "Anna", "Anne", 
			"Annelise", "Annika", "Asta", "Astrid", "Augusta", "Beate", "Bente", "Birgit", 
			"Birgitta", "Bodil", "Britta", "Camilla", "Carina", "Carla", "Cathrine", 
			"Charlotte", "Christa", "Christel", "Christiane", "Christina", "Clara", 
			"Dagmar", "Dorthe", "Edith", "Eleanor", "Elin", "Eline", "Elisabeth", "Ella", 
			"Ellen", "Else", "Emilie", "Emma", "Ena", "Esther", "Eva", "Fie", "Frederikke", 
			"Frida", "Gerda", "Gisela", "Gitte", "Grethe", "Gudrun", "Gurli", "Gyda", "Hanne", 
			"Harriet", "Heidi", "Helle", "Henriette", "Ida", "Inga", "Inger", "Ingrid", 
			"Irene", "Isabella", "Janne", "Jannie", "Jensine", "Jessica", "Johanne", "Jonna", 
			"Jytte", "Karen", "Karina", "Karla", "Katarina", "Kathrine", "Kirsten", 
			"Kirstine", "Klara", "Kirsti", "Krista", "Kristen", "Kristiane", "Kristina", 
			"Lena", "Lilian", "Lillian", "Lilly", "Lise", "Lisbeth", "Lise-Lotte", "Liva", 
			"Liv", "Lotte", "Louise", "Maj", "Maja", "Majbritt", "Malene", "Maren", 
			"Marianne", "Marie", "Marta", "Martine", "Mathilde", "Merete", "Meta", "Mette", 
			"Mia", "Michaela", "Mie", "Mille", "Mona", "Monica", "Mette-Marie", "Nadia", 
			"Nanna", "Natasja", "Nicoline", "Nina", "Olga", "Oline", "Pernille", "Petra", 
			"Ragna", "Ragnhild", "Randi", "Rigmor", "Rikke", "Rita", "Rosa", "Rose", "Sanne", 
			"Sara", "Sarah", "Signe", "Silke", "Simone", "Sofia", "Sofie", "Sonja", "Solveig", 
			"Sonia", "Susanne", "Sussi", "Svendborg", "Sylvi", "Sylvia", "Tanya", "Tenna", 
			"Therese", "Tina", "Tove", "Trine", "Ulla", "Ulrikka", "Ulrikke", "Vera", "Vibeke", 
			"Victoria", "Viktoria", "Vilhelmine", "Vivi", "Vivian", "Yrsa", "Åse", 
			"Åshild"
			}; 
	private static final String[] lastName = {
			"Christensen", "Jensen", "Nielsen", "Hansen", "Pedersen", "Andersen", 
			"Larsen", "Sørensen", "Rasmussen", "Petersen", "Madsen", "Olsen", "Jørgensen", 
			"Johansen", "Eriksen", "Mortensen", "Lund", "Schmidt", "Frederiksen", "Møller", 
			"Kristensen", "Jakobsen", "Mikkelsen", "Christiansen", "Ravn", "Madsen", 
			"Thomsen", "Mogensen", "Kjeldsen", "Holm", "Damgaard", "Kjær", "Hald", "Bach", 
			"Søgaard", "Svendsen", "Højgaard", "Bonde", "Lassen", "Jeppesen", "Meyer", 
			"Rosenberg", "Simonsen", "Thomsen", "Bruun", "Vestergaard", "Bachmann", "Toft", 
			"Villadsen", "Torp", "Søndergaard", "Fogh", "Carlsen", "Enevoldsen", "Lauridsen", 
			"Juhl", "Hegelund", "Klausen", "Leth", "Kofoed", "Boesen", "Mølgaard", "Buhl", 
			"Friis", "Lassen", "Laursen", "Krogh", "Frandsen", "Kjærsgaard", "Hendriksen", 
			"Kjeldgaard", "Bundgaard", "Lange", "Poulsen", "Wagner", "Dahl", "Riis", 
			"Therkelsen", "Borg", "Laustsen", "Brodersen", "Hermansen", "Larsen", "Bager", 
			"Bak", "Andreasen", "Gundersen", "Bisgaard", "Koch", "Mathiasen", "Holst", 
			"Andersson", "Møller", "Kjærgaard", "Lindberg", "Mogensen", "Lind", "Holt", 
			"Nicolaisen", "Jessen", "Sørensen", "Dalgaard", "Nørregaard", "Bjerregaard", 
			"Johannessen", "Elleby", "Petersson", "Bentsen", "Lau", "Jensen", "Mouridsen", 
			"Mathiesen", "Svane", "Bager", "Andreasen", "Hansen", "Krogsgaard", "Fogh", 
			"Jepsen", "Lynggaard", "Bisgaard", "Kjær", "Lauritzen", "Gade", "Hedegaard", 
			"Thygesen", "Henriksen", "Johansen", "Thygesen", "Pedersen", "Schultz", 
			"Kjeldsen", "Kjær", "Bjørn", "Eskildsen", "Hemmingsen", "Iversen", "Enevoldsen", 
			"Larsen", "Mikkelsen", "Højbjerg", "Nissen", "Vestergaard", "Svendsen", 
			"Kjærsgaard", "Jensen", "Sloth", "Nielsen", "Haugaard", "Lassen", "Jessen", "Holm"
			};
	
	
	public static String[] names(){
		return firstNameMale;
	}
	
	public static String[] lastNames(){
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
	
	public static String firstNameMale() {
		return getRandomElementFromArray(firstNameMale);
	}
	
	public static String firstNameFemale() {
		return getRandomElementFromArray(firstNameFemale);
	}
	
	public static String firstName() {
		Random random = new Random();
		boolean male = random.nextBoolean();
		
		if(male)
			return firstNameMale();
		else
			return firstNameFemale();
	}
	
	public static String lastName() {
		return getRandomElementFromArray(lastName);
	}
}
