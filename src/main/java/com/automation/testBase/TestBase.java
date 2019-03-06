package com.automation.testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.helper.browserConfiguration.BrowserType;
import com.automation.helper.browserConfiguration.ChromeBrowser;
import com.automation.helper.browserConfiguration.FirefoxBrowser;
import com.automation.helper.browserConfiguration.IEBrowser;
import com.automation.helper.browserConfiguration.PhantomJsBrowser;
import com.automation.helper.exception.AutomationException;
import com.automation.helper.logger.LoggerHelper;
import com.automation.helper.reader.AppConfig;
import com.automation.helper.reader.ExcelReader;
import com.automation.helper.reader.FromExcelImpl;
import com.automation.helper.reader.ReadData;
import com.automation.helper.wait.WaitHelper;
import com.automation.utils.listeners.ExtentManager;
import com.automation.utils.listeners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Kumar Rohan
 *
 */
public class TestBase {

	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public WebDriver driver;
	ExcelReader excel;

	@BeforeClass(alwaysRun = true)
	@Parameters(value = { "hubUrl", "browser" })
	public void setUp(@Optional String hubUrl, @Optional String browser) throws Exception {

		// ObjectReader.reader = new ConfigReaderImpl();
		// ProcessKiller.killRunningApps();
		ReadData.fromExcel = new FromExcelImpl();
		if (hubUrl != null && browser != null)
			this.driver = gridSetUp(hubUrl == null ? "http://localhost:4444/wd/hub" : hubUrl,
					browser == null ? "Chrome" : browser);
		else
			this.driver = standAloneStepUp(browser);

		//log.info("Initialize Web driver: " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait();
		wait.pageLoadTime();
		driver.manage().window().maximize();
		//getApplicationUrl();

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws IOException {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver getBrowserObject(String browser) throws Exception {

		if (browser.equals("chrome")) {
			logReport("Running tests in Chrome Browser :" + browser);
			ChromeBrowser chrome = ChromeBrowser.class.newInstance();
			ChromeOptions option = chrome.getChromeOptions();
			return chrome.getChromeDriver(option);
		} else if (browser.equals("Iexplorer")) {
			logReport("Running tests in IE Browser :" + browser);
			IEBrowser ie = IEBrowser.class.newInstance();
			InternetExplorerOptions cap = ie.getIExplorerCapabilities();
			return ie.getIExplorerDriver(cap);
		} else if (browser.equals("Firefox")) {
			logReport("Running tests in Firefox Browser :" + browser);
			FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
			FirefoxOptions options = firefox.getFirefoxOptions();
			return firefox.getFirefoxDriver(options);
		} else if (browser.equals("PhantomJs")) {
			logReport("Running tests in PhantomJs Browser :" + browser);
			PhantomJsBrowser jsBrowser = PhantomJsBrowser.class.newInstance();
			return jsBrowser.getPhantomJsDriver(jsBrowser.getPhantomJsService(), jsBrowser.getPhantomJsCapability());
		}

		return driver;
	}

	public WebDriver setUpDriver(String browser) throws Exception {
		driver = getBrowserObject(browser);
		log.info("Initialize Web driver: " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait();
		wait.pageLoadTime();
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver standAloneStepUp(String browser) throws Exception {
		Object browserType = browser;

		if (browserType == null) {
			browserType = AppConfig.getConfig("browserType");

		}
		switch (browserType.toString().toLowerCase()) {
		case "chrome":
			ChromeBrowser chrome = ChromeBrowser.class.newInstance();
			return chrome.getChromeDriver(chrome.getChromeOptions());

		case "iexplorer":
			IEBrowser iExplore = IEBrowser.class.newInstance();
			return iExplore.getIExplorerDriver(iExplore.getIExplorerCapabilities());
		case "firefox":
			FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
			return firefox.getFirefoxDriver(firefox.getFirefoxOptions());

		case "phantomjs":
			PhantomJsBrowser jsBrowser = PhantomJsBrowser.class.newInstance();
			return jsBrowser.getPhantomJsDriver(jsBrowser.getPhantomJsService(), jsBrowser.getPhantomJsCapability());

		default:
			throw new AutomationException(" Driver Not Found : " + AppConfig.getConfig("browserType"));

		}

	}

	public WebDriver gridSetUp(String hubUrl, String browser) throws Exception {
		log.info(hubUrl + " : " + browser);

		switch (BrowserType.valueOf(browser)) {

		case Chrome:
			ChromeBrowser chrome = new ChromeBrowser();
			return chrome.getChromeDriver(hubUrl, chrome.getChromeOptions());

		case Iexplorer:
			IEBrowser iExplore = new IEBrowser();
			return iExplore.getIExplorerDriver(hubUrl, iExplore.getIExplorerCapabilities());

		case Firefox:
			FirefoxBrowser firefox = new FirefoxBrowser();
			return firefox.getFirefoxDriver(hubUrl, firefox.getFirefoxOptions());

		case PhantomJs:
			PhantomJsBrowser jsBrowser = new PhantomJsBrowser();
			return jsBrowser.getPhantomJsDriver(hubUrl, jsBrowser.getPhantomJsService(),
					jsBrowser.getPhantomJsCapability());

		default:
			throw new AutomationException(" Driver Not Found : " + BrowserType.valueOf(browser));
		}

	}

	public void getApplicationUrl() {
		driver.get(AppConfig.getConfig("applicationUrl"));
				
	}

	public void logReport(String data) {
		log.info(data);
		Reporter.log(data);
		ExtentManager.extentLog(data);

	}

	public void captureScreen() throws IOException {
		String pageName = driver.getTitle();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String location = new File(System.getProperty("user.dir")).getAbsolutePath() + "/test-screenshots/";
		File destFile = new File(location + pageName + "_" + formater.format(calendar.getTime()) + ".png");
		FileHandler.copy(scrFile, destFile);

		Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
				+ "' height='80' width='80'/> </a>");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Screenshot",
				ExtentTestManager.getTest().addScreenCapture(destFile.toString()));

	}

}
