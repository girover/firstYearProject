package configs;

import java.util.HashMap;

public class Config {

	private static HashMap<String, String> configs = new HashMap<>();
	
//	public Config(){ 
//		configs = new HashMap<>();
//	}
	
	public static String get(String key) {
		return configs.get(key);
	}
	
	public static String get(String key, String defaultValue) {
		String c = configs.get(key);
		
		return c != null ? c : defaultValue;
	}
	
	public static String set(String key, String value) {
		return configs.put(key,value);
	}
	
	public static HashMap<String, String> getAll() {
		return configs;
	}
	
	public static boolean has(String key) {
		return configs.get(key)!= null;
	}
	
	public static void add(String key, String value) {
		configs.put(key, value);
	}
	
	public static void addAll(HashMap<String, String> configurations) {
		configs.putAll(configurations);
	}
	
	public static int size() {
		return configs.size();
	}
	
	public static void clear() {
		configs.clear();
	}
	
	public static String getAllAsString() {
		String string = "";
		for(String s: configs.keySet()) {
			string += s + " = " + configs.get(s) + "\n";
		}
		
		return string;
	}
}
