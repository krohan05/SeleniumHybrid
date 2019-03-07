package com.automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.automation.helper.javascript.JavaScriptHelper;
import com.automation.helper.wait.WaitHelper;
import com.automation.testBase.TestBase;

public class RiskReportPage extends TestBase {
	
	private WebDriver driver;
	WaitHelper waitHelper;
	Actions action;
	JavaScriptHelper jsHelper;

	@FindBy(xpath = "//span[contains(text(),'Show Filters')]")
	WebElement showFilter;

	@FindBy(xpath = "//span[contains(text(),'Hide Filters')]")
	WebElement hideFilter;

	@FindBy(xpath = "//button[@title='Apply Filters']")
	WebElement applyFilter;

	@FindBy(xpath = "//label[contains(text(),'OR Taxonomy Name ')]/following-sibling::input")
	WebElement taxonomyNameFilter;

	@FindBy(xpath = "//label[@data-id='A_OR_TAXONOMY_DESCRIPTION']")
	WebElement reportTaxonomyDes;

	@FindBy(xpath = "//label[@data-id='VALID_UNTIL']")
	WebElement reportDateValidUntil;	
	
	@FindBy(xpath = "//input[contains(@id, 'Created_From')]")
	WebElement createdAfter_Date;

	@FindBys(@FindBy(xpath = "//div[@class='page-actions']/div[@data-bootstro-title = 'Toolbar']/button"))
	List<WebElement> AllButtons;
	// div[@class='page-actions']/div[@data-bootstro-title =
	// 'Toolbar']/button[not(@data-action='popout') and not(@style='display:
	// none;')]

	@FindBys(@FindBy(xpath = "//div[@id='filter-body']//div[contains(@class,'filter-field')]//label"))
	List<WebElement> AllFilterLabels;

	@FindBys(@FindBy(xpath = "//li[@class='column-names']//span[contains(@class,'column-label')]"))
	List<WebElement> AllColumnNames;
	
	@FindBy(xpath = "//th[@class='EDIT']//span[@data-toggle='dropdown']")
	WebElement formNameFilterDD;
	
	@FindBy(xpath = "//th[@class='EDIT']//span[@data-toggle='dropdown' and @aria-expanded='true']/following-sibling::ul//form//input")
	public WebElement planNameFilter;

/*	@FindBy(xpath = "//span[@class='drill-down hc-text']")
	public WebElement planSearchResults;*/
	
	By planSearchResults = By.xpath("//span[@class='drill-down hc-text']");

	public RiskReportPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		action = new Actions(driver);
		jsHelper = new JavaScriptHelper(driver);

	}
	
	public void searchRiskFormReportAndClick(String formName) {
		waitHelper.waitForPageComponentLoad();
		waitHelper.WaitForElementVisibleWithPollingTime(formNameFilterDD);
		waitHelper.WaitForElementClickable(formNameFilterDD);
		formNameFilterDD.click();
//		jsHelper.clickElement(formNameFilterDD);
		//action.click(planNameFilter).sendKeys(formName, Keys.ENTER).build().perform();
		planNameFilter.sendKeys(formName, Keys.ENTER);
		action.pause(600).build().perform();
		waitHelper.WaitForElementVisibleWithPollingTime(planSearchResults);
		WebElement searchResult = driver.findElement(planSearchResults);
		waitHelper.WaitForElementClickable(searchResult);
//		searchResult.click();
		jsHelper.clickElement(searchResult);
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForExactTitle(driver, "Risk");
	}
	
	public boolean searchRiskFormReport(String formName) {
		waitHelper.waitForPageComponentLoad();
		waitHelper.WaitForElementVisibleWithPollingTime(formNameFilterDD);
		waitHelper.WaitForElementClickable(formNameFilterDD);
		formNameFilterDD.click();
		action.click(planNameFilter).pause(100).sendKeys(formName, Keys.ENTER).pause(400).build().perform();
		waitHelper.waitForPageLoad();
		action.pause(2500).build().perform();
		boolean searchResultItem = driver.findElements(By.xpath("//div[@class='report-body']//tbody/tr[1]/td")).size() > 1;
		return searchResultItem;
	}
	
	public void searchRiskReportAndClick(String riskReportName) {
		waitHelper.waitForPageComponentLoad();
		waitHelper.WaitForElementClickable(showFilter);
		showFilter.click();
		waitHelper.WaitForElementVisibleWithPollingTime(hideFilter);
		waitHelper.WaitForElementVisibleWithPollingTime(createdAfter_Date);
		waitHelper.WaitForElementClickable(createdAfter_Date);
		waitHelper.WaitForElementVisibleWithPollingTime(taxonomyNameFilter);
		waitHelper.WaitForElementClickable(taxonomyNameFilter);
		taxonomyNameFilter.sendKeys(riskReportName, Keys.ENTER);
		waitHelper.waitForPageLoad();
		WebElement searchResultItem = driver.findElement(
				By.xpath("//div[@class='report-body']//tbody/tr[1]//span[contains(text(),'" + riskReportName + "')]"));
		// span[text()='" + riskReportName + "']

		waitHelper.WaitForElementVisibleWithPollingTime(searchResultItem);
		waitHelper.WaitForElementClickable(searchResultItem);

		searchResultItem.click();
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForExactTitle(driver, "Risk");
		logReport("searched for the Risk Form in reports and opened it");
	}

	public boolean searchRiskReport(String riskReportName) {
		waitHelper.waitForPageComponentLoad();
		waitHelper.WaitForElementClickable(showFilter);
		showFilter.click();
		waitHelper.WaitForElementVisibleWithPollingTime(hideFilter);
		waitHelper.WaitForElementVisibleWithPollingTime(taxonomyNameFilter);
		waitHelper.WaitForElementClickable(taxonomyNameFilter);
		action.pause(400).build().perform();
		taxonomyNameFilter.sendKeys(riskReportName, Keys.ENTER, Keys.ENTER);
		waitHelper.waitForPageLoad();
		action.pause(2500).build().perform();

		boolean searchResultItem = driver.findElements(By.xpath("//div[@class='report-body']//tbody/tr[1]/td"))
				.size() > 1;
		if (!searchResultItem) {
			taxonomyNameFilter.clear();
			action.pause(2000).build().perform();
			taxonomyNameFilter.sendKeys(riskReportName, Keys.ENTER, Keys.RETURN);
			waitHelper.waitForPageLoad();
			action.pause(2500).build().perform();
			searchResultItem = driver.findElements(By.xpath("//div[@class='report-body']//tbody/tr[1]/td")).size() > 1;

		}
		if (searchResultItem) {
			return driver.findElements(By.xpath("//span[text()='" + riskReportName + "']")).size() > 0;
		} else {
			return false;
		}

	}

	public String getTaxonomyDescription() {
		return reportTaxonomyDes.getText();
	}

	public String getValidUntilDate() {
		return reportDateValidUntil.getText();
	}

	public boolean isEditButtonDisplayed() {
		boolean flag = false;
		List<WebElement> elements = AllButtons;
		for (WebElement element : elements) {
			if (element.getText().contains("Edit")) {
				flag = true;
				logReport("Edit button is displayed on the opened Risk Form");
				break;
			}
		}
		if (!flag) {
			logReport("Edit button is NOT present on the opened Risk Form");
		}
		return flag;
	}


}
