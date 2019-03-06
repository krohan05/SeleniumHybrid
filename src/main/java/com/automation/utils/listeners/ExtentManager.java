package com.automation.utils.listeners;

import com.automation.helper.reader.AppConfig;
import com.automation.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;


/**
 * @author Kumar Rohan
 *
 */
public class ExtentManager extends TestBase {
	private static ExtentReports extent;

	public synchronized static ExtentReports getInstance() {
		if (extent == null) {
			// Set HTML reporting file location
			extent = new ExtentReports(AppConfig.getConfig("ExtentReport"), true);

		}
		return extent;
	}

	public static void extentLog(String message) {
		if (ExtentTestManager.getTest() != null) {
			ExtentTestManager.getTest().log(LogStatus.INFO, message);
		}
	}

}
