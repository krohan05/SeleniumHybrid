package com.automation.helper.exception;

import org.apache.log4j.Logger;

import com.automation.helper.logger.LoggerHelper;


/**
 * @author Kumar Rohan
 *
 */
public class AutomationException extends RuntimeException {
	private static final Logger log = LoggerHelper.getLogger(AutomationException.class);

	/**
	 * Automation framework exception handler
	 */
	private static final long serialVersionUID = 1000L;

	public AutomationException(String message, Throwable cause) {
		super(message, cause);
		log.error("Automation exception with message: " + message + "and cause: " + cause.toString());
	}

	public AutomationException(String message) {
		super(message);
		log.error("Automation exception with message: " + message);
	}

	public AutomationException() {
		super();
		log.error("Automation exception!");
	}


}
