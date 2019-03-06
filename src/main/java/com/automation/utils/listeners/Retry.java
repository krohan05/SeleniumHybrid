package com.automation.utils.listeners;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.automation.helper.logger.LoggerHelper;


/**
 * @author Kumar Rohan
 *
 */
public class Retry implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount = 1;

	private Logger log = LoggerHelper.getLogger(Retry.class);

	@Override
	public boolean retry(ITestResult arg0) {
		if (retryCount < maxRetryCount) {
			log.info("Retrying test " + arg0.getName() + " with status " + getResultStatusName(arg0.getStatus())
					+ " for the " + (retryCount + 1) + " times.");
			retryCount++;
			return true;
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1) {
			resultName = "SUCCESS";
		}
		if (status == 2) {
			resultName = "FAILURE";
		}
		if (status == 3) {
			resultName = "SKIP";
		}
		return resultName;
	}

}
