package log;

public interface Logger {
	
	public void error(String message);
	public void warning(String message);
	public void information(String message);
	public void debugging(String message);
}
