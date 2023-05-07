package app;

import java.util.HashMap;

public class FormData {

	private HashMap<String, Object> data = new HashMap<>();;
	
	public Object get(String key) {
		if(data.containsKey(key))
			return data.get(key);
		else
			throw new FormDataException(key + " is not a valid form input.");
	}
	
	public Object input(String key) {
		return get(key);
	}
	
	public FormData setData(String key, Object value) {
		data.put(key, value);
		
		return this;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (String key : data.keySet()) {
			sb.append(key + " = " + data.get(key) + "\n");
		}
		
		return sb.toString();
	}
}
