package faker;

import java.util.HashMap;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class City extends Provider {

	private static HashMap<String, String> cities = new HashMap<>();
	static {
		cities.put("1000","København");
		cities.put("8000","Århus");
		cities.put("5000","Odense");
		cities.put("9000","Ålborg");
		cities.put("6700","Esbjerg");
		cities.put("8900","Randers");
		cities.put("6000","Kolding");
		cities.put("8700","Horsens");
		cities.put("7100","Vejle");
		cities.put("4000","Roskilde");
		cities.put("7400","Herning");
		cities.put("3000","Helsingør");
		cities.put("8600","Silkeborg");
		cities.put("4700","Næstved");
		cities.put("7000","Fredericia");
		cities.put("8800","Viborg");
		cities.put("4600","Køge");
		cities.put("7500","Holstebro");
		cities.put("2630","Tåstrup");
		cities.put("4200","Slagelse");
		cities.put("3400","Hillerød");
		cities.put("6400","Sønderberg");
		cities.put("5700","Svenborg");
		cities.put("9800","Hjøeeing");
		cities.put("2970","Hørsholm");
		cities.put("7430","Ikast");
		cities.put("7330","Brande");
		cities.put("9900","Frederikshavn");
		cities.put("8300","Odder");
		cities.put("9990","Skagen");
		cities.put("3700","Rønne");
		cities.put("6950","Rønkøbing");
		cities.put("6900","Skjern");
	};
	
	public static String getCity(String zipCode) {
		return cities.get(zipCode);
	}
	
	public static String getCity() {
		int index = getRandomInteger(0, cities.size());
		int start = 1;
        for (var entry : cities.entrySet()) {
            if (start == index) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
                break;
            }
            start++;
        }
        
        return cities.get("7400");
	}
	
	public static String getCityWithZipCode() {
		int index = getRandomInteger(0, cities.size());
		int start = 1;
        for (var entry : cities.entrySet()) {
            if (start == index) {
                String key = entry.getKey();
                String value = entry.getValue();
                return key + " " + value;
            }
            start++;
        }
        
        return cities.get("7400");
	}
	
	public static String getZipCode() {
		int index = getRandomInteger(0, cities.size());
		int start = 1;
		for (var entry : cities.entrySet()) {
			if (start == index) {
				return entry.getKey();
			}
			start++;
		}
		
		return cities.get("7400");
	}
}
