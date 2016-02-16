package com.baicai.futurekiller;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class ServerLogger {
	private static String LOG4J_XML_FILE = "logConfig.xml";

	private static boolean debugEnabled = false;

	private static Logger logger;

	public static void initLogger() {
		DOMConfigurator.configure(LOG4J_XML_FILE);
		logger = Logger.getLogger("ServerLogger");
		debugEnabled = logger.isDebugEnabled();
	}

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void error(String msg) {
		logger.error(msg);
	}

	public static void error(String msg, Throwable e) {
		logger.error(msg, e);
	}

	public static boolean isDebugEnabled() {
		return debugEnabled;
	}

	public static void debug(String msg, Throwable t) {
		logger.debug(msg, t);
	}

	public static void debug(String msg) {
		logger.debug(msg);
	}

	public static void debugf(String msg, Object... args) {
		debug(String.format(msg, args));
	}
}
