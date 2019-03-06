package com.automation.helper.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.automation.helper.logger.LoggerHelper;
import com.automation.helper.reader.AppConfig;

public class WaitHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		// log.info("WaitHelper object created..");
	}

	/**
	 * This is ImplicitWait method
	 *
	 */
	public void setImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Long.parseLong(AppConfig.getConfig(("implicitwait"))),
				TimeUnit.SECONDS);
	}

	public void reSetImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/**
	 * This will help us to get WebDriverWait object
	 * 
	 * @return
	 */
	private WebDriverWait getWait() {
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.pollingEvery(Duration.ofMillis(Integer.parseInt(AppConfig.getConfig("pollingTimeInMiliSec"))));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	/**
	 * This method will make sure element is visible
	 * 
	 * @param element
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element) {
		Wait<WebDriver> wait = getfluentWait();
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver webdriver) {
				return element;
			}
		});
	}

	public WebElement WaitForElementVisibleWithPollingTime(By locator) {
		Wait<WebDriver> wait = getfluentWait();
		WebElement waitElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver webdriver) {
				return driver.findElement(locator);
			}
		});
		return waitElement;
	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * 
	 */
	public void WaitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is clickable now");
	}

	public void WaitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		log.info("element is clickable now");
	}

	/**
	 * This method will make sure invisibilityOf element
	 * 
	 * @param element
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisibile now");
		return status;
	}

	/**
	 * This will wait for until the element becomes invisible
	 * 
	 * @param element
	 */
	public void WaitForElementInvisibilityWithPollingTime(WebElement element) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		log.info("element is not visible now");
	}

	public void WaitForElementInvisibilityWithPollingTime(By locator) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		log.info("element is not visible now");
	}

	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * 
	 * @param element
	 */
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switched");
	}

	/**
	 * This method will give is fluentWait object
	 * 
	 * @return
	 */
	private Wait<WebDriver> getfluentWait() {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Long.parseLong(AppConfig.getConfig("explicitwait"))))
				.pollingEvery(Duration.ofMillis(Integer.parseInt(AppConfig.getConfig("pollingTimeInMiliSec"))))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(TimeoutException.class);

		return fWait;
	}

	public void pageLoadTime() {
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(AppConfig.getConfig("pageloadtime")),
				TimeUnit.SECONDS);
		log.info("page is loaded");
	}

	public void setScriptTimeout() {
		driver.manage().timeouts().setScriptTimeout(Long.parseLong(AppConfig.getConfig("scriptTimeout")),
				TimeUnit.SECONDS);
		log.info("page is loaded");
	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * 
	 */
	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}

	/**
	 * This will wait for till the page title is loaded/displayed
	 * 
	 * @param pageTitle
	 */
	public void waitForTitle(WebDriver driver, String pageTitle) {

		log.info("waiting for Page title:" + "'" + pageTitle + "'" + " to load");
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.titleContains(pageTitle));
		log.info("Page title " + "'" + pageTitle + "'" + " loaded successfully");
	}

	public void waitForExactTitle(WebDriver driver, String pageTitle) {

		log.info("waiting for Page title:" + "'" + pageTitle + "'" + " to load");
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.titleIs(pageTitle));
		log.info("Page title " + "'" + pageTitle + "'" + " loaded successfully");
	}

	public void waitTillTitleIsNot(WebDriver driver, String pageTitle) {

		log.info("waiting untill the page title doesn't contain:" + "'" + pageTitle + "'");
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.not(ExpectedConditions.titleContains(pageTitle)));
		log.info("Landed to a page that dosn;t conatin title as - " + "'" + pageTitle + "'");
	}

	/**
	 * This will wait until page loads and document ready state is 'complete'
	 */
	public void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(pageLoadCondition);
	}

	/*
	 * This will wait for Bootstrap modal popUp window
	 */
	public void WaitForModalPopUpAppear() {
		reSetImplicitWait();
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'modal-backdrop')]")));
		setImplicitWait();
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	/*
	 * This will wait for Bootstrap modal popUp to fade
	 */
	public void WaitForModalPopUpDisAppear() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions
					.stalenessOf(driver.findElement(By.xpath("//div[contains(@class,'modal-backdrop')]"))));
		} catch (NoSuchElementException e) {

		}
		setImplicitWait();

	}

	public void waitForPageComponentLoad() {
		reSetImplicitWait();
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='pace-progress' and contains(@data-progress-text,'100%')]")));
		setImplicitWait();
	}

	/*
	 * This will wait for Bootstrap modal popUp window
	 */
	public void WaitForFormStatus() {
		reSetImplicitWait();
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getConfig("explicitwait")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='msiStatus']")));
		setImplicitWait();
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	public void waitForStalenessOfElement(By locator) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
		} catch (NoSuchElementException e) {

		}
		setImplicitWait();

	}

}
