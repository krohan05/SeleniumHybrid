package com.automation.helper.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.automation.helper.logger.LoggerHelper;

/**
 * @author Kumar Rohan
 *
 */
public class AppConfig {
	private static Logger log = LoggerHelper.getLogger(AppConfig.class);

	public static Map<String, String> getConfig() {

		Properties prop = new Properties();
		Map<String, String> map = new HashMap<String, String>();

		try {
			prop.load(AppConfig.class.getResourceAsStream("/config/application.properties"));
		} catch (Exception ex) {
			log.info("Couldn't load property correctly :" + ex.getMessage());
		}

		for (Entry<Object, Object> entry : prop.entrySet()) {
			map.put((String) entry.getKey(), (String) entry.getValue());
		}

		return map;

	}

	public static String getConfig(String key) {

		Properties prop = new Properties();
		Map<String, String> map = new HashMap<String, String>();

		try {
			prop.load(AppConfig.class.getResourceAsStream("/config/application.properties"));
		} catch (Exception ex) {
			System.out.println("Couldn't load property correctly :" + ex.getMessage());
		}

		for (Entry<Object, Object> entry : prop.entrySet()) {
			map.put((String) entry.getKey(), (String) entry.getValue());
			if (map.containsKey(key) == true) {
				return map.get(key);

			}
		}

		return null;
	}
}
