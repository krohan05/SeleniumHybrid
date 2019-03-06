package com.automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.automation.helper.javascript.JavaScriptHelper;
import com.automation.helper.wait.WaitHelper;
import com.automation.testBase.TestBase;

public class LibraryPage extends TestBase {
	
	private WebDriver driver;
	WaitHelper waitHelper;
	JavaScriptHelper jsHelper;
	Actions action;

	@FindBy(xpath = "//ul[@id='navbar-nav-header']//a[contains(text(),'Libraries')]")
	WebElement GRCLibraries;

	@FindBy(xpath = "//a[@data-name='Manage GRC Libraries_GRC Librariess' and contains(text(),'Manage')]")
	WebElement Manage;

	@FindBy(xpath = "//a[@data-name='Setup GRC Libraries_GRC Librariess' and contains(text(),'Setup')]")
	WebElement Setup;

	@FindBy(xpath = "//span[@class='link-type' and contains(text(),'Forms')]")
	WebElement Forms;
	
	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul//a[@title='Control Related']")
	WebElement controlRelatedForms;	
	
	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul//a[@title='Control']")
	WebElement controlForm;	
	
	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul//a[@title='Asset']")
	WebElement assetForm;
	
	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul//a[@title='IUC']")
	WebElement iucForm;
	
	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul//a[@title='Vendor']")
	WebElement vendorForm;
	
	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul//a[@title='Risk']")
	WebElement riskForm;
	
	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul//a[@title='Risks']")
	WebElement riskRelatedForms;
	
	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul//a[@title='Others']")
	WebElement othersRelatedForms;

	@FindBy(xpath = "(//span[contains(text(),' Reports')])[1]")
	public WebElement reports;
	
	@FindBy(xpath = "//button[@id='btn-ReportsLink' and @aria-expanded='true']/..//ul//a[@title='Control Related']")
	WebElement controlRelatedReports;
	
	@FindBy(xpath = "//button[@id='btn-ReportsLink' and @aria-expanded='true']/..//ul//a[@title='Risks']")
	WebElement risksReports;
	
	@FindBy(xpath = "//button[@id='btn-ReportsLink' and @aria-expanded='true']/..//ul//a[@title='Others']")
	WebElement othersReports;

	@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/following-sibling::div[@class='dropdown-menu page-header-menu link-infoport']//div[@class='tab-pane grouplink-tab active']/li/a[contains(text(),'Risk')]")
	WebElement RiskForm;

	@FindBys(@FindBy(xpath = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//div[@class='tab-pane grouplink-tab active']/li/a"))
	List<WebElement> AllForms;

	@FindBys(@FindBy(xpath = "//button[@id='btn-ReportsLink' and @aria-expanded='true']/following-sibling::div[@class='dropdown-menu page-header-menu link-infoport']//div[@class='tab-pane grouplink-tab active']/li"))
	List<WebElement> AllReports;

	@FindBy(xpath = "//a[@class='my-tasks-menu']")
	WebElement TaskIcon;
	

	public LibraryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		jsHelper = new JavaScriptHelper(driver);
		action = new Actions(driver);

	}

	public void clickOnGRCLibraries() {
		GRCLibraries.click();
	}

	public void clickOnForms() {
		Forms.click();
	}
	
	public List<WebElement> getListOfAllForms() {
		Forms.click();
		List<WebElement> elements = AllForms;
		logReport("Following Forms were displayed");
		for (WebElement element : elements) {
			logReport(element.getText());
		}
		return AllForms;
	}
	
	
	public void getAssetForm() {
		assetForm.click();

	}
	
	public void getControlForm() {
		controlForm.click();
	}
	
	public void getIUCForm() {	
		iucForm.click();
	}
	
	public void getRiskForm() {	
		riskForm.click();		
	}
		
	public void getVendorForm() {	
		vendorForm.click();		
	}
	
	public List<WebElement> getOtherForms() {
		othersRelatedForms.click();
		List<WebElement> elements = AllForms;
		logReport("Following Forms were displayed");
		for (WebElement element : elements) {
			logReport(element.getText());
		}
		return AllForms;
	}
	
	public void clickOnReports() {
		reports.click();
	}
	
	public List<WebElement> getListOfAllReports() {
		reports.click();
		List<WebElement> elements = AllReports;
		logReport("Following Reports were displayed");
		for (WebElement element : elements) {
			logReport(element.getText());
		}
		return AllReports;
	}
	
	public List<WebElement> getControlRelatedReports() {
		controlRelatedReports.click();
		List<WebElement> elements = AllReports;
		logReport("Following Reports were displayed");
		for (WebElement element : elements) {
			logReport(element.getText());
		}
		return AllReports;
	}
	
	public List<WebElement> getRisksReport() {
		risksReports.click();
		List<WebElement> elements = AllReports;
		logReport("Following Reports were displayed");
		for (WebElement element : elements) {
			logReport(element.getText());
		}
		return AllReports;
	}
	
	public List<WebElement> getOthersReport() {	
		othersReports.click();
		List<WebElement> elements = AllReports;
		logReport("Following Reports were displayed");
		for (WebElement element : elements) {
			logReport(element.getText());
		}
		return AllReports;
	}

	public boolean isFormDisplayed(String formName) {
		boolean flag = false;
		List<WebElement> elements = AllForms;
		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(formName)) {
				flag = true;
				logReport("'" + formName + "'" + " Form was present in the list of Forms displayed above");
				break;
			}
		}
		return flag;

	}

	public boolean isReportDisplayed(String reportName) {
		boolean flag = false;
		List<WebElement> elements = AllReports;
		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(reportName)) {
				flag = true;
				logReport("'" + reportName + "'" + " Report was present in the list of Reports displayed above");
				break;
			}
		}
		return flag;

	}

	public void clickOnForm(String formName) {
		Forms.click();
		logReport("Clicked on Forms button");
		String infoPortTab = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//ul[@class='nav nav-tabs']/li/a[contains(text(),'"
 				+ formName + "')]";
		WebElement formTab = driver.findElement(By.xpath(infoPortTab));
		waitHelper.waitForElement(formTab);
		formTab.click();
		String infoPortItem = "//button[@id='btn-FormsLink' and @aria-expanded='true']/..//div[@class='tab-content']//li/a[contains(text(),'"
		+ formName + "')]";
		WebElement formItem = driver.findElement(By.xpath(infoPortItem));
		waitHelper.waitForElement(formItem);
		formItem.click();
		logReport("clicked on " + formName + " Form link");
		waitHelper.waitForPageComponentLoad();

	}
	
	public void clickOnReport(String reportName) {
		reports();		
		logReport("Clicked on Reports button");
		clickReport(reportName);
		logReport("clicked on " + reportName + " Report link");

	}	
	
	public void clickOnRisksReport(String reportName) {
		reports();
		risksReports.click();
		logReport("Clicked on Risks Reports button");
		clickReport(reportName);
		logReport("clicked on " + reportName + " Risks Reports link");
	}
	
	public void clickOnOthersReport(String reportName) {
		reports();
		othersReports.click();
		logReport("Clicked on Others Reports button");
		clickReport(reportName);
		logReport("clicked on " + reportName + " Others Reports link");
	}

	/**
	 * @param reportName
	 */
	private void clickReport(String reportName) {
		String xpath = "//button[@id='btn-ReportsLink' and @aria-expanded='true']/following-sibling::div[@class='dropdown-menu page-header-menu link-infoport']//div[@class='tab-pane grouplink-tab active']/li/a[contains(text(),'"
				+ reportName + "')]";
		WebElement report = driver.findElement(By.xpath(xpath));
		waitHelper.WaitForElementVisibleWithPollingTime(report);
		waitHelper.WaitForElementClickable(report);
		waitHelper.waitForPageLoad();
		report.click();
	}

	/**
	 * 
	 */
	private void reports() {
		waitHelper.waitForPageLoad();
		waitHelper.WaitForElementVisibleWithPollingTime(reports);
		waitHelper.WaitForElementClickable(reports);
//	    jsHelper.clickElement(reports);
		reports.click();
	}	


}
