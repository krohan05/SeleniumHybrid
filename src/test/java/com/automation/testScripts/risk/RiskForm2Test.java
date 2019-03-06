package com.automation.testScripts.risk;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.helper.assertion.AssertionHelper;
import com.automation.helper.dateTime.DateTimeHelper;
import com.automation.helper.reader.AppConfig;
import com.automation.helper.reader.ReadData;
import com.automation.helper.wait.WaitHelper;
import com.automation.pageobjects.HomePage;
import com.automation.pageobjects.LibraryPage;
import com.automation.pageobjects.LoginPage;
import com.automation.pageobjects.MyTasksPage;
import com.automation.pageobjects.RiskFormPage;
import com.automation.pageobjects.RiskReportPage;
import com.automation.testBase.TestBase;

public class RiskForm2Test extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	WaitHelper waitHelper;
	LibraryPage libraryPage;
	MyTasksPage myTasksPage;
	RiskReportPage riskReportPage;
	Actions action;

	@BeforeMethod(alwaysRun=true)
	public void logIn() {

		getApplicationUrl();
		loginPage = new LoginPage(driver);
		waitHelper = new WaitHelper(driver);
		action = new Actions(driver);
	}

	@DataProvider(name = "RiskFormViewers")
	public static String[][] getLoginData() {
		String[][] loginRecords = ReadData.fromExcel.getEntireDataFromSheet("testdata.xlsx", "SITLoginData");
		return loginRecords;
	}

	@DataProvider(name = "RiskFormCreators")
	public String[][] getRiskLoginData() {
		String[][] loginRecords = ReadData.fromExcel.getEntireDataFromSheet("libraryusers.xlsx", "RiskFormUsers");
		return loginRecords;
	}

	@Test(priority = 1, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC007_FormFlow_HeaderVerification() throws IOException {

		logReport("!!!!!!!!!! Risk_TC007_FormFlow_HeaderVerification starts !!!!!!!!!!");
		logReport("Test Description: Verify the 'Risk' form header section");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
		logReport("logged-in to application");
//		homePage.clickOnCIDpopUp();		
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedInUser + " was sucessfull");

		// Goto Risk form Page
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);

		// verify Risk form header section
		AssertionHelper.updateTestStatus(riskFormPage.isHeaderLevelDisplayed());
		logReport("'Level' is displayed at the Header section");
		String levelValue = riskFormPage.getHeaderLevelValue();
		//AssertionHelper.verifyText(levelValue, ApplicationMessages.HEADER_LEVEL_VALUE);
		logReport("'Level' value is displayed as: " + levelValue);

		AssertionHelper.updateTestStatus(riskFormPage.isHeaderStatusDisplayed());
		logReport("'Status' is displayed at the Header section");
		String statusValue = riskFormPage.getHeaderStatusValue();
		//AssertionHelper.verifyText(statusValue, ApplicationMessages.HEADER_STATUS_VALUE);
		logReport("'Status' value is displayed as: " + statusValue);

		AssertionHelper.updateTestStatus(riskFormPage.isFullScreenIconDisplayed());
		logReport("'Full Screen Icon' is displayed at the Header section");
		AssertionHelper.updateTestStatus(riskFormPage.isShowHelpIconDisplayed());
		logReport("'Show Help Icon' is displayed at the Header section");
		AssertionHelper.updateTestStatus(riskFormPage.isSaveButtonDisplayed());
		logReport("'Save button' is displayed at the Header section");
		AssertionHelper.updateTestStatus(riskFormPage.isSendForApprovalButtonDisplayed());
		logReport("'Send For Approval button' is displayed at the Header section");
		AssertionHelper.updateTestStatus(riskFormPage.isCloseButtonDisplayed());
		logReport("'Close button' is displayed at the Header section");
		logReport("All header item verified");
//        captureScreen();
		logReport("!!!!!!!!!! Risk_TC007_FormFlow_HeaderVerification ends !!!!!!!!!!");
	}

	@Test(priority = 2, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC008_FormFlow_AllSectionsVerification() {

		logReport("!!!!!!!!!! Risk_TC008_FormFlow_AllSectionsVerification starts !!!!!!!!!!");
		logReport("Test Description: Verify the sections of Risk form.");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		logReport("logged-in to application");		
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedInUser + " was sucessfull");

		// Goto Risk form Page
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);

		// Verify all the Sections (section heading) of the risk form
		AssertionHelper.updateTestStatus(riskFormPage.isRiskFormSectionDisplayed("DETAILS"));
		AssertionHelper.updateTestStatus(riskFormPage.isRiskFormSectionDisplayed("OWNERSHIP AND SECURITY"));
		AssertionHelper.updateTestStatus(riskFormPage.isRiskFormSectionDisplayed("RELATIONSHIPS"));
		AssertionHelper.updateTestStatus(riskFormPage.isRiskFormSectionDisplayed("ADDITIONAL DETAILS"));

		logReport("!!!!!!!!!! Risk_TC008_FormFlow_AllSectionsVerification ends !!!!!!!!!!");
	}

	@Test(priority = 3, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC013_FormFlow_RelationshipValidation() {

		logReport("!!!!!!!!!! Risk_TC013_FormFlow_RelationshipValidation starts !!!!!!!!!!");
		logReport("Test Description: Verify the 'Relationships' section in 'Risk' form.");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		logReport("logged-in to application");
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedInUser + " was sucessfull");

		// Goto Risk form Page
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);

		// Verify all the relationships (Links) displayed in the risk form
		AssertionHelper.updateTestStatus(riskFormPage.isRelationshipLinkDisplayed("Applies to Organizations"));
		AssertionHelper.updateTestStatus(riskFormPage.isRelationshipLinkDisplayed("Assets"));
		AssertionHelper.updateTestStatus(riskFormPage.isRelationshipLinkDisplayed("Controls"));
		AssertionHelper.updateTestStatus(riskFormPage.isRelationshipLinkDisplayed("Processes"));
		AssertionHelper.updateTestStatus(riskFormPage.isRelationshipLinkDisplayed("Risks"));
		AssertionHelper.updateTestStatus(riskFormPage.isRelationshipLinkDisplayed("Vendors"));

		logReport("!!!!!!!!!! Risk_TC013_FormFlow_RelationshipValidation ends !!!!!!!!!!");
	}

	@Test(priority = 4, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC014_FormFlow_AdditionalDetailsVerification() {

		logReport("!!!!!!!!!! Risk_TC014_FormFlow_AdditionalDetailsVerification starts !!!!!!!!!!");
		logReport("Test Description: Verify the 'Additional Details' section of 'Risk' form");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
		logReport("logged-in to application");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedInUser + " was sucessfull");

		// Goto Risk form Page
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		waitHelper.waitForPageComponentLoad();
		RiskFormPage riskFormPage = new RiskFormPage(driver);

		// verify Additional details section
		String formStatus = riskFormPage.getAdditionalDetailFormStatus();
		//AssertionHelper.verifyText(formStatus, ApplicationMessages.FORM_STATUS);
		logReport("'Status' of the form in Additional Details section is displayed as: " + formStatus);

		AssertionHelper.verifyText(riskFormPage.getCreatedOnDate(),
				DateTimeHelper.getSystemDateInFormat("dd-MMM-yyyy"));
		logReport("'Created On' date is displayed as: " + riskFormPage.getCreatedOnDate());

		AssertionHelper.verifyText(riskFormPage.getCreatedBy(), loggedInUser);
		logReport("'Created By' is displayed as: " + riskFormPage.getCreatedBy());

		AssertionHelper.updateTestStatus(riskFormPage.isAttachFileDisplayed());
		logReport("'Attach File' is displayed in the additional details section");

		logReport("!!!!!!!!!! Risk_TC014_FormFlow_AdditionalDetailsVerification ends !!!!!!!!!!");

	}

	@Test(priority = 5, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC020_FormFlow_CancellationByRO() throws IOException {

		logReport("!!!!!!!!!! Risk_TC020_FormFlow_CancellationByRO starts !!!!!!!!!!");
		logReport("Test Description: Verify if Library Owner is able to cancel the risk Library object");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Goto Risk Form Page
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);

		// Fill the Risk Form
		String riskFormName = "RISKAuto" + System.currentTimeMillis();
		riskFormPage.setORTaxonomyName(riskFormName);
		logReport("Populated the Risk Form Name (i.e. OR Taxonomy Name) with a unique generated name: " + riskFormName);
		riskFormPage.fillRiskForm("Risk_DataSet001");

		// Send the Risk Form for Approval
		String formSubmitStatus = riskFormPage.sendForApproval("Risk_DataSet001");
		//AssertionHelper.comparePartialText(formSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("sent the form for approval");

		homePage.SignOut();
		logReport("logged out as Business Admin");

		// Now login to Application as Risk Owner
		getApplicationUrl();
		loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Navigate to MyTask Page
		myTasksPage = homePage.clickOnMyTasks();

		// search the Risk Form created by Business Admin
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm created by Business Admin and opened it");

		// Cancel the Risk Form
		String cancelSubmissionStatus = riskFormPage.cancelRiskForm("Risk_DataSet001");
		//AssertionHelper.comparePartialText(cancelSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);

		// verify that cancelled Risk form should not appear in the Reports
		logReport(
				"Navigate to Reports section to verify that cancelled Risk form should not appear in the list of published Reports");
//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {				
			libraryPage.clickOnReport("Risks");			
		} else {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForTitle(driver, "Risks");
		riskReportPage = new RiskReportPage(driver);
		logReport("Searched for the cancelled Risk form in the List of published Reports");
		boolean SearchStatus = riskReportPage.searchRiskReport(riskFormName);
		if (!SearchStatus) {
			AssertionHelper.markPass("Cancelled Risk form is NOT present in the List of published Reports");
			logReport("Cancelled Risk form is NOT present in the List of published Reports");
		} else {
			AssertionHelper.markFail("Cancelled Risk form is present in the List of published Reports");
			logReport("Cancelled Risk form is present in the List of published Reports");
		}

		logReport("!!!!!!!!!! Risk_TC020_FormFlow_CancellationByRO ends !!!!!!!!!!");

	}

	@Test(priority = 6, groups = { "regression" }, alwaysRun = true)
	public void FormFlow_RiskFormReinitiationFlowApproved() throws IOException {
		logReport("!!!!!!!!!! FormFlow_RiskFormReinitiationFlowApproved starts !!!!!!!!!!");
		logReport(
				"Verify if Library owner is able to able to approve a re-initaited Risk form post getting the repsone from the initiator for the requested clarification.");

		// *************Pre-condition: Create a Active Risk Form**************
		logReport("========== creating the Pre-condition ==========");
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet001");
		homePage.SignOut();
		logReport("logged out as Business Admin");

		// Now login to Application as Risk Owner to approve the Risk form
		// created by BA
		getApplicationUrl();
		loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		myTasksPage = homePage.clickOnMyTasks();
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm to approve it");
		riskFormPage.approveRiskForm("Risk_DataSet001");
		homePage.SignOut();
		logReport("========== Pre-condition setUp done ==========");
		// *************Pre-condition: Create a Active Risk Form**************

		// Now login to application as BA to re-initiate the existing Risk Form
		getApplicationUrl();
		loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {				
			libraryPage.clickOnReport("Risks");			
		} else {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForTitle(driver, "Risks");
		RiskReportPage riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);
		waitHelper.waitForExactTitle(driver, "Risk - MetricStream M7");		
//		captureScreen();		
		riskFormPage.editApprovedRiskForm("Risk_DataSet003");
		String formSubmissionStatus = riskFormPage.sendForApproval("Risk_DataSet003");
		//AssertionHelper.comparePartialText(formSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Reinitiate the form and sent for approval");
		homePage.SignOut();

		// Now login to Application as Risk Owner in order to request for
		// clarification on the re-initiated form
		getApplicationUrl();
		loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		logReport("logged-in to application");
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		myTasksPage = homePage.clickOnMyTasks();
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm reinitiated by Business Admin and opened it");
		String clarificationSubmitStatus = riskFormPage.requestClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(clarificationSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		homePage.SignOut();

		// Login back to Application as BA to provide response on the requested
		// clarification by RiskOwner on the re-initiated form
		getApplicationUrl();
		loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		myTasksPage = homePage.clickOnMyTasks();
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the reinitiated RiskForm on which Risk Owner has asked for clarification");
		String responseSubmitStatus = riskFormPage.provideClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(responseSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		homePage.SignOut();

		// Login back as RiskOwner to approve the re-initiated risk form by BA
		getApplicationUrl();
		loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");		
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		myTasksPage = homePage.clickOnMyTasks();
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport(
				"searched the reinitiated RiskForm (on which Business Admin has provided clarification) to approve it");

		String finalSubmissionStatus = riskFormPage.approveRiskForm("Risk_DataSet001");
		//AssertionHelper.comparePartialText(finalSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Risk Form approved and submitted sucessfully");

		// verify if approved re-initiated Risk form contains the updated
		// changes
		logReport(
				"Navigating to Report section to verify if approved re-initiated Risk form contains the updated changes");
//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		libraryPage.clickOnReport("Risks");	
//		if (loggedInUser.contains("TestRisk Owner")) {
//			// release 8 patch fix
//			libraryPage.clickOnReport("Risks");			
//		} else {
//			libraryPage.clickOnRisksReport("Risks");
//		}	
		waitHelper.waitForTitle(driver, "Risks");
		riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);
		waitHelper.waitForExactTitle(driver, "Risk - MetricStream M7");

		AssertionHelper.verifyText(riskReportPage.getTaxonomyDescription(),
				ReadData.fromExcel.getFormData("Risk_DataSet003", "ORTaxonomyDescription"));
		AssertionHelper.verifyText(riskReportPage.getValidUntilDate(),
				ReadData.fromExcel.getFormData("Risk_DataSet003", "DateValidUntil"));
		logReport("New changes are present in the approved re-initiated risk form");
//		captureScreen();		
		logReport("!!!!!!!!!! FormFlow_RiskFormReinitiationFlowApproved ends !!!!!!!!!!");
	}

	@Test(priority = 7, groups = { "regression" }, alwaysRun = true)
	public void FormFlow_RiskFormReinitiationFlowCancelled() throws IOException {

		logReport("!!!!!!!!!! FormFlow_RiskFormReinitiationFlowCancelled starts !!!!!!!!!!");
		logReport(
				"Verify if Library owner is able to able to Cancel a re-initaited Risk form post getting the repsone from the initiator for the requested clarification.");

		// *************Pre-condition: Create a Active Risk Form**************
		logReport("========== creating the Pre-condition ==========");
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet001");
		homePage.SignOut();
		logReport("logged out as Business Admin");

		// Now login to Application as Risk Owner to approve the Risk form
		// created by BA
		getApplicationUrl();
		loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		myTasksPage = homePage.clickOnMyTasks();
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm to approve it");
		riskFormPage.approveRiskForm("Risk_DataSet001");
		homePage.SignOut();
		logReport("========== Pre-condition setUp done ==========");
		// *************Pre-condition: Create a Active Risk Form**************

		// Now login to application as BA to re-initiate the existing Risk Form
		getApplicationUrl();
		loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		libraryPage.clickOnReport("Risks");
//		if (loggedInUser.contains("TestRisk Owner")) {			
//			libraryPage.clickOnReport("Risks");
//		} else {
//			// release 8 patch fix
//			libraryPage.clickOnRisksReport("Risks");
//		}	
		waitHelper.waitForTitle(driver, "Risks");
		riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);
		waitHelper.waitForExactTitle(driver, "Risk - MetricStream M7");		
//		captureScreen();		
		riskFormPage.editApprovedRiskForm("Risk_DataSet003");
		String formSubmissionStatus = riskFormPage.sendForApproval("Risk_DataSet003");
		//AssertionHelper.comparePartialText(formSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Reinitiate the form and sent for approval");
		homePage.SignOut();

		// Now login to Application as Risk Owner in order to request for
		// clarification on the re-initiated form
		getApplicationUrl();
		loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		logReport("logged-in to application");
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		myTasksPage = homePage.clickOnMyTasks();
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm reinitiated by Business Admin and opened it");
		String clarificationSubmitStatus = riskFormPage.requestClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(clarificationSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		homePage.SignOut();

		// Login back to Application as BA to provide response on the requested
		// clarification by RiskOwner on the re-initiated form
		getApplicationUrl();
		loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		myTasksPage = homePage.clickOnMyTasks();
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the reinitiated RiskForm on which Risk Owner has asked for clarification");
		String responseSubmitStatus = riskFormPage.provideClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(responseSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		homePage.SignOut();

		// Login back as RiskOwner to Cancel the re-initiated risk form by BA
		getApplicationUrl();
		loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		myTasksPage = homePage.clickOnMyTasks();
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport(
				"searched the reinitiated RiskForm (on which Business Admin has provided clarification) to cancel it");

		String cancelSubmissionStatus = riskFormPage.cancelRiskForm("Risk_DataSet001");
		//AssertionHelper.comparePartialText(cancelSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("reinitiated Risk Form cancelled");

		// verify if cancelled re-initiated Risk form does not contains the
		// updated changes instead it retains the initial approved contents
		logReport(
				"verify if cancelled re-initiated Risk form does not contains the updated changes instead it retains the initial approved contents");
//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {	
			// release 8 patch fix
			libraryPage.clickOnReport("Risks");			
		} else {
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForTitle(driver, "Risks");
		riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);
		waitHelper.waitForExactTitle(driver, "Risk - MetricStream M7");

		AssertionHelper.verifyNotEqualText(riskReportPage.getTaxonomyDescription(),
				ReadData.fromExcel.getFormData("Risk_DataSet003", "ORTaxonomyDescription"));
		AssertionHelper.verifyText(riskReportPage.getTaxonomyDescription(),
				ReadData.fromExcel.getFormData("Risk_DataSet001", "ORTaxonomyDescription"));

		AssertionHelper.verifyNotEqualText(riskReportPage.getValidUntilDate(),
				ReadData.fromExcel.getFormData("Risk_DataSet003", "DateValidUntil"));
		AssertionHelper.verifyText(riskReportPage.getValidUntilDate(),
				ReadData.fromExcel.getFormData("Risk_DataSet001", "DateValidUntil"));
		logReport(
				"New changes are NOT present in the cancelled re-initiated risk form. The Form retains the initail approved content");
//		captureScreen();
		logReport("!!!!!!!!!! FormFlow_RiskFormReinitiationFlowCancelled ends !!!!!!!!!!");

	}

	@Test(priority = 8, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC029_FormFlow_InActiveFormByBA() throws IOException {

		logReport("!!!!!!!!!! Risk_TC029_FormFlow_InActiveFormByBA STARTS !!!!!!!!!!");
		logReport("Verify if Business Admin can create a Risk with 'Inactive' status.");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Goto Risk form page
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		waitHelper.waitForPageComponentLoad();
		RiskFormPage riskFormPage = new RiskFormPage(driver);

		// Filling Risk Form
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet004");

		// Go to Reports to verify if the status of the published Risk Report is
		// "InActive"
		logReport("Navigate to Report section to verify the status of the created form");
		while (!driver.getTitle().contains("Manage")) {
			libraryPage = homePage.goToInfoCenter("Lib");
			waitHelper.waitForPageLoad();
			waitHelper.waitForPageComponentLoad();
		}
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {	
			libraryPage.clickOnReport("Risks");

		} else {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}

		waitHelper.waitForTitle(driver, "Risks");
		riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);
		//AssertionHelper.verifyText(riskFormPage.getHeaderStatusValue(),	ApplicationMessages.INACTIVE_FORM_HEADER_STATUS);
		logReport("The Form status in the published report is displyed as :  " + riskFormPage.getHeaderStatusValue());

		logReport("!!!!!!!!!! Risk_TC029_FormFlow_InActiveFormByBA ENDS !!!!!!!!!!");
	}

	@Test(priority = 9, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC030_FormFlow_InActiveFormByRO() throws IOException {

		logReport("!!!!!!!!!! Risk_TC030_FormFlow_InActiveFormByRO STARTS !!!!!!!!!!");
		logReport("Verify if Risk Owner can create a Risk with 'Inactive' status.");

		// Login to application as Risk Owner
		homePage = loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Goto Risk form page
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);

		// Filling Risk Form
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet005");

		// Go to Reports to verify if the status of the published Risk Report is
		// "InActive"
		logReport("Navigate to Report section to verify the status of the created form");
		while (!driver.getTitle().contains("Manage")) {
			libraryPage = homePage.goToInfoCenter("Lib");
			waitHelper.waitForPageLoad();
		}
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {			
			libraryPage.clickOnReport("Risks");			
		} else {
		   // release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForTitle(driver, "Risks");
		riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);

		//AssertionHelper.verifyText(riskFormPage.getHeaderStatusValue(),	ApplicationMessages.INACTIVE_FORM_HEADER_STATUS);
		logReport("The Form status in the published report is displyed as :  " + riskFormPage.getHeaderStatusValue());

		logReport("!!!!!!!!!! Risk_TC030_FormFlow_InActiveFormByRO ENDS !!!!!!!!!!");
	}
	
	@Test(priority = 10, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC031_FormFlow_EndRiskFormValidityByBA() throws IOException {

		logReport("!!!!!!!!!! Risk_TC031_FormFlow_EndRiskFormValidityByBA STARTS !!!!!!!!!!");
		logReport("Verify if Business Admin can end the validity of Risk form to make it as 'Expired'");

		// *************Pre-condition: Create a Active Risk Form**************
		logReport("========== creating the Pre-condition (i.e. Approved Risk Form) ==========");
		homePage = loginPage.loginAs("BusinessAdmin");
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet002");
		logReport("========== Pre-condition setUp done ==========");
		// *************Pre-condition: Create a Active Risk Form**************

		// Go to Report section to open and edit the Risk Form
		logReport("Navigate to Report section to open the approved Risk form");
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {	
			libraryPage.clickOnReport("Risks");

		} else {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");
		RiskReportPage riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);
		waitHelper.waitForExactTitle(driver, "Risk - MetricStream M7");

		riskFormPage.editToSetPastDate("Risk_DataSet002");
		String formSubmissionStatus = riskFormPage.sendForApproval("Risk_DataSet002");
		//AssertionHelper.comparePartialText(formSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Edited form submitted sucessfully and sent for approval");

		// Go to Report section and open the Risk form to check if the status
		// has changed to 'Expired'
		logReport("Navigate back to Report section to verify if the Risk form status has been changed to 'Expired'");
		/*while (!driver.getTitle().contains("Manage")) {
			homePage.goToInfoCenter("GRC Libraries");
			waitHelper.waitForPageLoad();
		}*/
		
//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Manage");
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {	
			libraryPage.clickOnReport("Risks");
		} else {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");
		riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);

		//AssertionHelper.verifyText(riskFormPage.getHeaderStatusValue(), ApplicationMessages.EXPIRED_FORM_HEADER_STATUS);
		logReport("The Form status in the published report is displyed as :  " + riskFormPage.getHeaderStatusValue());

		logReport("!!!!!!!!!! Risk_TC031_FormFlow_EndRiskFormValidityByBA ENDS !!!!!!!!!!");

	}

	@Test(priority = 11, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC032_FormFlow_EndRiskFormValidityByRO() throws IOException {

		logReport("!!!!!!!!!! Risk_TC032_FormFlow_EndRiskFormValidityByRO STARTS !!!!!!!!!!");
		logReport("Verify if Risk Owner can end the validity of Risk form to make it as 'Expired'");

		// *************Pre-condition: Create a Active Risk Form**************
		logReport("========== creating the Pre-condition (i.e. Approved Risk Form) ==========");
		homePage = loginPage.loginAs("RiskOwner");
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet001");
		logReport("========== Pre-condition setUp done ==========");
		// *************Pre-condition: Create a Active Risk Form**************

		// Go to Report section to open and edit the Risk Form
		logReport("Navigate to Report section to open the approved Risk form");
//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {			
			libraryPage.clickOnReport("Risks");
		} else {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}	
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");
		RiskReportPage riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);
		waitHelper.waitForExactTitle(driver, "Risk - MetricStream M7");

		riskFormPage.editToSetPastDate("Risk_DataSet001");
		String formSubmissionStatus = riskFormPage.sendForApproval("Risk_DataSet001");
		//AssertionHelper.comparePartialText(formSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Edited form submitted sucessfully and sent for approval");

		// Go to Report section and open the Risk form to check if the status
		// has changed to 'Expired'
		logReport("Navigate back to Report section to verify if the Risk form status has been changed to 'Expired'");
		/*while (!driver.getTitle().contains("Manage")) {
			homePage.goToInfoCenter("GRC Libraries");
			waitHelper.waitForPageLoad();
		}*/
//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Manage");
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {			
			libraryPage.clickOnReport("Risks");			
		} else {
		   // release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");
		action.pause(3000).build().perform();
		riskReportPage.searchRiskReportAndClick(riskFormName);

		//AssertionHelper.verifyText(riskFormPage.getHeaderStatusValue(), ApplicationMessages.EXPIRED_FORM_HEADER_STATUS);
		logReport("The Form status in the published report is displyed as :  " + riskFormPage.getHeaderStatusValue());

		logReport("!!!!!!!!!! Risk_TC032_FormFlow_EndRiskFormValidityByRO ENDS !!!!!!!!!!");
	}

	@Test(priority = 12, groups = { "regression" }, alwaysRun = true)
	public void Risk_TC033_FormFlow_ReActivateExpiredFormByBA() throws IOException {

		logReport("!!!!!!!!!! Risk_TC033_FormFlow_ReActivateExpiredFormByBA STARTS !!!!!!!!!!");
		logReport("Verify if Business Admin can reactivate the the expired Risk form");

		// *************Pre-condition: Create a Expired Risk Form**************
		logReport("========== creating the Pre-condition (i.e. Expierd Risk Form) ==========");
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet006");
		logReport("========== Pre-condition setUp done ==========");
		// *************Pre-condition: Create a Expired Risk Form**************

		// Go to Report section to open and edit the expired Risk Form
		logReport("Navigate to Report section to open the expired Risk form");
//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {	
			libraryPage.clickOnReport("Risks");
		} else {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");
		RiskReportPage riskReportPage = new RiskReportPage(driver);
		riskReportPage.searchRiskReportAndClick(riskFormName);
		waitHelper.waitForExactTitle(driver, "Risk - MetricStream M7");

		logReport("Before editing - The status of the Form in the published report is displyed as :  "
				+ riskFormPage.getHeaderStatusValue());
		logReport("Now editing the form to make it active");
		riskFormPage.editToReactivateForm();
		String formSubmissionStatus = riskFormPage.sendForApproval("Risk_DataSet001");
		//AssertionHelper.comparePartialText(formSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Edited form submitted sucessfully and sent for approval");

		// Go to Report section and open the Risk form to check if the status
		// has changed to 'Active'
		logReport("Navigate back to Report section to verify if the Risk form status has been changed to 'Active'");
/*		while (!driver.getTitle().contains("Manage")) {
			homePage.goToInfoCenter("GRC Libraries");
			waitHelper.waitForPageLoad();
		}*/
//		homePage.goToInfoCenter("GRC Libraries");
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Manage");
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {			
			libraryPage.clickOnReport("Risks");			
		} else {
		   // release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");
		//action.pause(3000).build().perform();
		riskReportPage.searchRiskReportAndClick(riskFormName);

		//AssertionHelper.verifyText(riskFormPage.getHeaderStatusValue(), ApplicationMessages.ACTIVE_FORM_HEADER_STATUS);
		logReport("The Form status in the published report is displyed as :  " + riskFormPage.getHeaderStatusValue());

		logReport("!!!!!!!!!! Risk_TC033_FormFlow_ReActivateExpiredFormByBA ENDS !!!!!!!!!!");

	}

	@Test(priority = 13, groups = { "regression" }, alwaysRun = true)
	public void RiskFormByBA_WithALLFieldsPopulated() throws IOException {

		logReport("!!!!!!!!!! RiskFormByBA_WithALLFieldsPopulated STARTS !!!!!!!!!!");
		logReport("Verify if Business Admin can create a Risk form populating all the fileds");

		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet007");

		// verify if approved Risk form appears in the Reports
		logReport(
				"Navigate to Report section to verify if the created Risk form is present in the List of published Reports");
/*		while (!driver.getTitle().contains("Manage")) {
			homePage.goToInfoCenter("GRC Libraries");
			waitHelper.waitForPageLoad();
		}*/
		
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Manage");
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {				
			libraryPage.clickOnReport("Risks");			
		} else {
		   // release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");

		RiskReportPage riskReportPage = new RiskReportPage(driver);
		boolean SearchStatus = riskReportPage.searchRiskReport(riskFormName);
		if (SearchStatus) {
			AssertionHelper.markPass("Approved Risk form is present in the List of published Reports");
			logReport("Approved Risk form is present in the List of published Reports");
		} else {
			AssertionHelper.markFail("Approved Risk form is NOT present in the List of published Reports");
			logReport("Approved Risk form is NOT present in the List of published Reports");
		}

		logReport("!!!!!!!!!! RiskFormByBA_WithALLFieldsPopulated ENDS !!!!!!!!!!");
	}

	@Test(priority = 14, groups = { "regression" }, alwaysRun = true)
	public void RiskFormByRO_WithALLFieldsPopulated() throws IOException {

		logReport("!!!!!!!!!! RiskFormByRO_WithALLFieldsPopulated STARTS !!!!!!!!!!");
		logReport("Verify if Risk Owner can create a Risk form populating all the fileds");

		homePage = loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());
//		libraryPage = homePage.goToInfoCenter("GRC Libraries");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet008");

		// verify if approved Risk form appears in the Reports
		logReport(
				"Navigate to Report section to verify if the created Risk form is present in the List of published Reports");
/*		while (!driver.getTitle().contains("Manage")) {
			homePage.goToInfoCenter("GRC Libraries");
			waitHelper.waitForPageLoad();
		}*/
		
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Manage");
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {			
			libraryPage.clickOnReport("Risks");			
		} else {
		   // release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");

		RiskReportPage riskReportPage = new RiskReportPage(driver);
		boolean SearchStatus = riskReportPage.searchRiskReport(riskFormName);
		if (SearchStatus) {
			AssertionHelper.markPass("Approved Risk form is present in the List of published Reports");
			logReport("Approved Risk form is present in the List of published Reports");
		} else {
			AssertionHelper.markFail("Approved Risk form is NOT present in the List of published Reports");
			logReport("Approved Risk form is NOT present in the List of published Reports");
		}

		logReport("!!!!!!!!!! RiskFormByRO_WithALLFieldsPopulated ENDS !!!!!!!!!!");
	}
	@Test(priority = 15, dataProvider = "RiskFormViewers", groups = { "sanity", "regression" }, alwaysRun = true)
	public void CheckRiskFormEditAccessForUsers(String role, String userName, String password) throws Exception {
		logReport("!!!!!!!!!! CheckRiskFormEditAccessFor " + role + " STARTS !!!!!!!!!!");
		logReport(
				"Test Description: Verify if none of the users can edit the Risk form except for BusinessAdmin and RiskOwner");

		homePage = loginPage.loginToApplication(userName, password);
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());
		// Navigate to Report section to open any Risk Form
		logReport("Navigate to Report section to open any Risk Form");
		if(role.equalsIgnoreCase("ITAdmin")){
			libraryPage = homePage.goToInfoCenter("Operational");
		}else{
			libraryPage = homePage.goToInfoCenter("Lib");
		}
		waitHelper.waitForPageComponentLoad();
		if (loggedInUser.contains("TestIT Admin")) {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		} else {
			libraryPage.clickOnReport("Risks");
		}
		waitHelper.waitForPageComponentLoad();
		waitHelper.waitForTitle(driver, "Risks");
		riskReportPage = new RiskReportPage(driver);
//		captureScreen();	
		riskReportPage.searchRiskReportAndClick("RISKAuto");
		waitHelper.waitForExactTitle(driver, "Risk - MetricStream M7");
		logReport("Check for the edit button in Header section of the Form");
		boolean displayStatus = riskReportPage.isEditButtonDisplayed();

		if (role.equals("BusinessAdmin") || role.equals("RiskOwner")) {
			AssertionHelper.verifyTrue(displayStatus);
			logReport("For " + role + "," + " edit button is displayed. Hence, can edit the Risk Form");
		} else {
			AssertionHelper.verifyFalse(displayStatus);
			logReport("For " + role + "," + " edit button is NOT Present. Hence, CAN'T edit the Risk Form");
		}
//		captureScreen();	
		homePage.SignOut();
		logReport("!!!!!!!!!! CheckRiskFormEditAccessFor " + role + " ENDS !!!!!!!!!!");
		getApplicationUrl();

	}
		
	@AfterMethod(alwaysRun=true)
	public void logOut() {

		if (!driver.getTitle().contains("Signin")) {
			homePage.SignOut();
		}
	}


}
