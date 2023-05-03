package log;

import configs.Config;

public class Log {

	/**
	 * A singleton instance of Log.
	 */
	private static Logger inst = null;
//	private static boolean closed = false;

	public static Logger instance() {

		if (inst != null)
			return inst;

		String driver = Config.get("log.driver");

		if (driver.equals("file"))
			inst = FileLog.instance();
		else
			inst = DatabaseLog.instance();

		return inst;
	}

	public static void error(String message) {
		instance().error(message);
	}

	public static void warning(String message) {
		instance().warning(message);
	}

	public static void information(String message) {
		instance().information(message);
	}

	public static void debugging(String message) {
		instance().debugging(message);
	}
}
