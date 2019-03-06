package com.automation.helper.browserConfiguration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.helper.dateTime.DateTimeHelper;
import com.automation.helper.reader.AppConfig;


/**
 * @author Kumar Rohan
 *
 */
public class ChromeBrowser {
	
	public WebDriver getChromeDriver(ChromeOptions cap) throws IOException {

		if (System.getProperty("os.name").contains("Windows")) {	
			System.setProperty("webdriver.chrome.driver", AppConfig.getConfig("Windows.ChromeDriver"));
			System.setProperty("webdriver.chrome.logfile", AppConfig.getConfig("chromelogs") + "chromelog"
					+ DateTimeHelper.getCurrentDateTime() + ".log");
			return new ChromeDriver(cap);
		} else if (System.getProperty("os.name").contains("LINUX")) {
			System.setProperty("webdriver.chrome.driver", AppConfig.getConfig("Linux.ChromeDriver"));
			return new ChromeDriver(cap);
		} else if (System.getProperty("os.name").contains("MAC")) {
			System.setProperty("webdriver.chrome.driver", AppConfig.getConfig("Mac.ChromeDriver"));
			return new ChromeDriver(cap);
		}
		return null;
	}

	public ChromeOptions getChromeOptions() {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.addArguments("--disable-popup-blocking");
//		option.addArguments("headless");
//        option.addArguments("window-size=1200x600");
		option.setExperimentalOption("useAutomationExtension", false);

		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);

		option.setCapability(ChromeOptions.CAPABILITY, chrome);
		option.setCapability("applicationCacheEnabled", false);
		chrome.setPlatform(Platform.WINDOWS);
		// Linux
		if (System.getProperty("os.name").contains("LINUX")) {
			option.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return option;
	}

	public WebDriver getChromeDriver(String hubUrl, Capabilities cap) throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}


}
