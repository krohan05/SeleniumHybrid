package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.helper.logger.LoggerHelper;
import com.automation.helper.reader.ReadData;
import com.automation.helper.wait.WaitHelper;
import com.automation.testBase.TestBase;

public class LoginPage extends TestBase {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);

	WaitHelper waitHelper;

	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	WebElement signInButton;

	@FindBy(id = "username")
	WebElement loginUserName;

	@FindBy(xpath = "//input[@type='password']")
	WebElement password;

	@FindBy(xpath = "//div[@class='signin-msg-summary-heading']")
	WebElement invalidLoginText;

	@FindBy(xpath = "//div[@class='modal-actions']/button[contains(text(),'OK')]")
	WebElement CIDPopUp;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}

	public void clickOnSignInLink() {
		signInButton.click();
	}

	public void enterUserName(String loginUserName) {

		this.loginUserName.sendKeys(loginUserName);
	}

	public void enterPassword(String password) {

		this.password.sendKeys(password);
	}

	public HomePage loginToApplication(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnSignInLink();
		waitHelper.waitForTitle(driver, "Home");
		try {
			waitHelper.WaitForModalPopUpAppear();
			waitHelper.waitForElement(CIDPopUp);
			CIDPopUp.click();
			waitHelper.WaitForModalPopUpDisAppear();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HomePage(driver);
	}

	public String getInvalidLoginText() {
		log.info("verifying Invalid login message...");
		waitHelper.waitForElement(invalidLoginText);
		return invalidLoginText.getText();
	}

	public HomePage loginAs(String role) {

		String excelName = "testdata.xlsx";
		String sheetName = "SITLoginData";
		String knownValue = role;

		String uName = ReadData.fromExcel.getDataForKnownVal(excelName, sheetName, "UserName", knownValue);
		String pswd = ReadData.fromExcel.getDataForKnownVal(excelName, sheetName, "Password", knownValue);
		return loginToApplication(uName, pswd);

	}


}
