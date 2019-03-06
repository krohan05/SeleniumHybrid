package com.automation.helper.browserConfiguration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.helper.dateTime.DateTimeHelper;
import com.automation.helper.reader.AppConfig;


/**
 * @author Kumar Rohan
 *
 */
public class PhantomJsBrowser {

	public PhantomJSDriverService getPhantomJsService() {
		return new PhantomJSDriverService.Builder()
				.usingAnyFreePort()
				.usingPhantomJSExecutable(
						new File(AppConfig.getConfig("Windows.PhantomJs")))
				.withLogFile(
						new File(AppConfig.getConfig("phantomjslogs")
								+ "phantomjslogs" + DateTimeHelper.getCurrentDateTime() + ".log")).build();

	}

	public Capabilities getPhantomJsCapability() {
		@SuppressWarnings("deprecation")
		DesiredCapabilities cap = DesiredCapabilities.phantomjs();
		cap.setJavascriptEnabled(true);
		cap.setPlatform(Platform.WINDOWS);
		return cap;
	}

	public WebDriver getPhantomJsDriver(PhantomJSDriverService sev, Capabilities cap) {

		return new PhantomJSDriver(sev, cap);
	}

	public WebDriver getPhantomJsDriver(String hubUrl, PhantomJSDriverService sev, Capabilities cap)
			throws MalformedURLException {

		return new RemoteWebDriver(new URL(hubUrl), cap);
	}

	
}
