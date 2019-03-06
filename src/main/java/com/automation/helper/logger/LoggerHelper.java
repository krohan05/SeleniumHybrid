package com.automation.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * @author Kumar Rohan
 *
 */
public class LoggerHelper {
	
	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class cls) {

		PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/config/log4j.properties");
		return Logger.getLogger(cls);
	}

}
