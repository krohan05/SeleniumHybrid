package com.automation.helper.browserConfiguration;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.helper.dateTime.DateTimeHelper;
import com.automation.helper.reader.AppConfig;


/**
 * @author Kumar Rohan
 *
 */
public class FirefoxBrowser {
	public FirefoxOptions getFirefoxOptions() {

		DesiredCapabilities firefox = DesiredCapabilities.firefox();

		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);

		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionette", true);
		firefox.setPlatform(Platform.WINDOWS);

		FirefoxOptions firefoxOptions = new FirefoxOptions(firefox);
		// Linux
		if (System.getProperty("os.name").contains("LINUX")) {
			firefoxOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return firefoxOptions;
	}

	public WebDriver getFirefoxDriver(FirefoxOptions cap) {

		if (System.getProperty("os.name").contains("WINDOWS")) {
			System.setProperty("webdriver.gecko.driver", AppConfig.getConfig("Windows.FireFoxDriver"));
			System.setProperty("webdriver.gecko.driver.logfile",
					AppConfig.getConfig("firefoxlogs") + "fflog" + DateTimeHelper.getCurrentDateTime() + ".log");
			return new FirefoxDriver(cap);
		} else if (System.getProperty("os.name").contains("LINUX")) {
			System.setProperty("webdriver.gecko.driver", AppConfig.getConfig("Linux.FireFoxDriver"));
			return new FirefoxDriver(cap);
		} else if (System.getProperty("os.name").contains("MAC")) {
			System.setProperty("webdriver.gecko.driver", AppConfig.getConfig("Mac.FireFoxDriver"));
			return new FirefoxDriver(cap);
		}
		return null;
	}

	public WebDriver getFirefoxDriver(String hubUrl, Capabilities cap) throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}

}
