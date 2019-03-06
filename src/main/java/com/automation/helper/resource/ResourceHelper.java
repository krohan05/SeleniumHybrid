package com.automation.helper.resource;


/**
 * @author Kumar Rohan
 *
 */
public class ResourceHelper {

	public static String getResourcePath(String path) {
		String basePath = System.getProperty("user.dir");
		return basePath + "/" + path;
	}
	
}
