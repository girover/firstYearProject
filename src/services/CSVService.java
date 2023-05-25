package services;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CSVService {

    public static void writeCSV(List<String[]> data, String filePath) throws IOException {
    	FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8);
        try {
            for (String[] row : data) {
                writer.append(toCSV(row));
                writer.append("\n");
            }
            writer.flush();
        	writer.close();
        }
        catch (Exception e) {
        	throw new IOException("Something went wrong writing the CSV file.");
        }
    }

    private static String toCSV(String[] data) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String field : data) {
        	if (field == null) {
        		throw new Exception("Fields can't be null");
        	}
        	sb.append('"');
            sb.append(field.replace("\"", "\"\"").replace("\n", " "));
            sb.append('"');
            sb.append(';');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
