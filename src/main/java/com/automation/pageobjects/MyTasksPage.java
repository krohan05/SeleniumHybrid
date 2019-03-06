package com.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.helper.wait.WaitHelper;
import com.automation.testBase.TestBase;

public class MyTasksPage extends TestBase {
	private WebDriver driver;
	WaitHelper waitHelper;
	Actions action;

	@FindBy(xpath = "//section[@class='tasks-filters']//input[@placeholder='Search']")
	WebElement searchTask;

	@FindBy(xpath = "//button[@title='Apply Filters']")
	WebElement applyFilter;

	public MyTasksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		action = new Actions(driver);
	}

	public void searchTaskAndClick(String taskName) {
		waitHelper.waitForPageLoad();
		waitHelper.waitForPageComponentLoad();
		action.moveToElement(searchTask).click().build().perform();
		searchTask.click();
		action.pause(700).build().perform();
		try {
			WebElement task = driver
					.findElement(By.xpath("//div[@class='task-title']/a[contains(@title,'" + taskName + "')]"));

			waitHelper.WaitForElementVisibleWithPollingTime(task);

			waitHelper.WaitForElementClickable(task);
			task.click();
			waitHelper.waitForPageLoad();
			waitHelper.waitForPageComponentLoad();
			waitHelper.waitTillTitleIsNot(driver, "Tasks");
		} catch (Exception e) {
			searchTask.clear();
			action.click(searchTask).sendKeys(taskName).pause(500).sendKeys(Keys.ENTER).build().perform();
			action.pause(1000).build().perform();
			waitHelper.waitForPageComponentLoad();

			WebElement searchResultItem = driver
					.findElement(By.xpath("//section[@class='tasks-inbox']//a[contains(@title,'" + taskName + "')]"));

			waitHelper.WaitForElementVisibleWithPollingTime(searchResultItem);
			searchResultItem.click();
			waitHelper.waitForPageLoad();
			waitHelper.waitForPageComponentLoad();
			waitHelper.waitTillTitleIsNot(driver, "Tasks");

		}

	}


}
