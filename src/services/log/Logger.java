package services.log;

/**
 * @author Majed Hussein Farhan - <b style="color:red">
 *         girover.mhf@gmail.com</b> -
 *         <a href="https://github.com/girover">Github</a>
 */
public interface Logger {
	
	public void error(String message);
	public void warning(String message);
	public void information(String message);
	public void debugging(String message);
}
