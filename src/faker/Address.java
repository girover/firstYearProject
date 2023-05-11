package faker;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class Address extends Provider {

	private static final String[] streets = {
			"Strandvejen",
			"Frederiksberggade",
			"Vesterbrogade",
			"Nørrebrogade",
			"Østerbrogade",
			"Amagerbrogade",
			"Jagtvej",
			"Gl.Kongevej",
			"Lyngbyvej",
			"Roskildevej",
			"H.C.Andersen Boulevard",
			"Købmagergade",
			"Strøget",
			"Sønderparken",
			"Jyllandsgade",
			"Islandsgade"
	};
	
	private static String[] apartmentPositions = {"Th", "Tv", "St"};
	private static String[] haousePositions = {"A", "B", "C", "D", "E", "F", "G"};
	
	public static String addressApartment() {
		
		String street = getRandomElementFromArray(streets);
		int buildingNumber = getRandomInteger(1, 100);
		int floor = getRandomInteger(0, 6);
		int door  = getRandomInteger(1, 3);
		String position;
		if(floor == 0)
			position = "St";
		else
			position = apartmentPositions[getRandomInteger(0, apartmentPositions.length - 1)];
		
		return street +" "+buildingNumber+" "+floor+". "+position;
		
	}
	
	public static String addressHouse() {
		String street = getRandomElementFromArray(streets);
		int buildingNumber = getRandomInteger(1, 100);
		
		return street +" "+buildingNumber;
	}
}
