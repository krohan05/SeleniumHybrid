package com.automation.helper.browserConfiguration;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.helper.dateTime.DateTimeHelper;
import com.automation.helper.reader.AppConfig;


/**
 * @author Kumar Rohan
 *
 */
public class IEBrowser {

	public InternetExplorerOptions getIExplorerCapabilities() {

		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();

		// cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,
		// ElementScrollBehavior.BOTTOM);
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		cap.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 0);
		cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
		cap.setJavascriptEnabled(true);
		cap.setPlatform(Platform.WINDOWS);

		InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions(cap);

		return internetExplorerOptions;
	}

	public WebDriver getIExplorerDriver(InternetExplorerOptions cap) {

		if (System.getProperty("os.name").contains("WINDOWS")) {
			System.setProperty("webdriver.ie.driver", AppConfig.getConfig("Windows.IEDriver"));
			System.setProperty("webdriver.ie.driver.logfile",
					AppConfig.getConfig("iexplorerlogs") + "ielog" + DateTimeHelper.getCurrentDateTime() + ".log");
			return new InternetExplorerDriver(cap);
		} else if (System.getProperty("os.name").contains("LINUX")) {
			System.setProperty("webdriver.ie.driver", AppConfig.getConfig("Linux.IEDriver"));
			return new InternetExplorerDriver(cap);
		} else if (System.getProperty("os.name").contains("MAC")) {
			System.setProperty("webdriver.ie.driver", AppConfig.getConfig("Mac.IEDriver"));
			return new InternetExplorerDriver(cap);
		}
		return null;
	}

	public WebDriver getIExplorerDriver(String hubUrl, Capabilities cap) throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}

	
}
