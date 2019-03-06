package com.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.helper.assertion.AssertionHelper;
import com.automation.helper.javascript.JavaScriptHelper;
import com.automation.helper.wait.WaitHelper;
import com.automation.testBase.TestBase;

public class HomePage extends TestBase {
	private WebDriver driver;
	WaitHelper waitHelper;
	JavaScriptHelper jsHelper;
	AssertionHelper assertionHelper;
	Actions action;

	@FindBy(xpath = "//*[@class='icn icn-home ']")
	WebElement homeIcon;

	@FindBy(xpath = "//div[@class='users-icon']")
	WebElement profileIcon;

	@FindBy(xpath = "//*[@class='dropdown-menu users']//a[contains(text(),'Sign Out')]")
	WebElement signOut;

	@FindBy(xpath = "//button[contains(text(),'Sign Out')]")
	WebElement finalSignOut;

	@FindBy(xpath = "//a[@class='user-name cursorPointer']")
	WebElement LoggedInUser;

	@FindBy(xpath = "//a[@class='navbar-brand  msLogo']")
	WebElement MSLogo;

	@FindBy(xpath = "//ul[@id='navbar-nav-header']//a[contains(text(),'Libraries')]")
	WebElement GRCLibraries;

	@FindBy(xpath = "//a[@class='my-tasks-menu']")
	WebElement MyTasks;

	@FindBy(xpath = "//div[@class='modal-actions']/button[contains(text(),'OK')]")
	WebElement CIDPopUpOkBtn;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		jsHelper = new JavaScriptHelper(driver);
		action = new Actions(driver);

	}

	public void SignOut() {
		waitHelper.WaitForElementVisibleWithPollingTime(profileIcon);
		action.moveToElement(profileIcon).build().perform();
		action.moveToElement(signOut).click().build().perform();
		waitHelper.waitForPageLoad();
		//jsHelper.clickElement(signOut);
		waitHelper.WaitForModalPopUpAppear();
		waitHelper.WaitForElementVisibleWithPollingTime(finalSignOut);
		waitHelper.WaitForElementClickable(finalSignOut);
		finalSignOut.click();
	}

	public String getLoggedInUserDetails() {
		action.moveToElement(profileIcon).moveToElement(LoggedInUser).build().perform();
		String user = LoggedInUser.getText();
		return user;
	}

	public LibraryPage goToInfoCenter(String name) {
		if (driver.findElement(By.xpath("(//a[contains(text(),'" + name + "') and contains(@info-id,'" + name + "')])[1]"))
				.isDisplayed()) {
			driver.findElement(By.xpath("(//a[contains(text(),'" + name + "') and contains(@info-id,'" + name + "')])[1]"))
					.click();
		} else {
			WebElement displayMore = driver.findElement(By.id("displayMore"));
			WebElement wb1 = driver.findElement(By.xpath("(//a[contains(text(),'" + name + "') and contains(@info-id,'" + name + "')])[1]"));
			action.moveToElement(displayMore).moveToElement(wb1).click().build().perform();
		}
		return new LibraryPage(driver);

	}

	public LibraryPage clickOnGRCLibraries() {
		/*GRCLibraries.click();
		logReport("clicked on GRCLibraries");*/
		goToInfoCenter("GRC Libraries");
		return new LibraryPage(driver);
	}

	public MyTasksPage clickOnMyTasks() {
		MyTasks.click();
		logReport("clicked on MyTasks");
		waitHelper.waitForPageLoad();
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Tasks");
		action.pause(800).build().perform();
		return new MyTasksPage(driver);
	}

	public void clickOnCIDpopUp() {
		try {
			waitHelper.WaitForModalPopUpAppear();
			waitHelper.waitForElement(CIDPopUpOkBtn);
			CIDPopUpOkBtn.click();
			waitHelper.WaitForModalPopUpDisAppear();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


}
