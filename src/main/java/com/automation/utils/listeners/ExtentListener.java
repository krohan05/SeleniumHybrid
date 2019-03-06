package com.automation.utils.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.automation.testBase.TestBase;


/**
 * @author Kumar Rohan
 *
 */
public class ExtentListener extends TestBase implements ITestListener {
	WebDriver driver;

	public void onTestStart(ITestResult result) {

		ExtentTestManager.startTest(result.getMethod().getMethodName(), "");

	}

	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");

	}

	public void onTestFailure(ITestResult result) {

		if (!result.isSuccess()) {
			try {
				String methodName = result.getName();

				Object currentClass = result.getInstance();
				this.driver = ((TestBase) currentClass).getDriver();
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat formater = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");

				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				String location = new File(System.getProperty("user.dir")).getAbsolutePath() + "/error-screenshots/";
				File destFile = new File(location + methodName + "_" + formater.format(calendar.getTime()) + ".png");
				FileHandler.copy(scrFile, destFile);

				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
						+ "' height='80' width='80'/> </a>");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
						ExtentTestManager.getTest().addScreenCapture(destFile.toString()));
				// Shutterbug.shootPage(driver,
				// ScrollStrategy.BOTH_DIRECTIONS,500,true).save();
				// Shutterbug.shootPage(driver,
				// ScrollStrategy.BOTH_DIRECTIONS).save();
				// Shutterbug.shootPage(driver).withName(methodName).save();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}

	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();

	}

}
