package com.automation.testScripts.risk;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.helper.assertion.AssertionHelper;
import com.automation.helper.reader.AppConfig;
import com.automation.helper.wait.WaitHelper;
import com.automation.pageobjects.HomePage;
import com.automation.pageobjects.LibraryPage;
import com.automation.pageobjects.LoginPage;
import com.automation.pageobjects.MyTasksPage;
import com.automation.pageobjects.RiskFormPage;
import com.automation.pageobjects.RiskReportPage;
import com.automation.testBase.TestBase;

public class RiskForm1Test extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	WaitHelper waitHelper;
	LibraryPage libraryPage;
	MyTasksPage myTasksPage;

	@BeforeMethod(alwaysRun=true)
	public void logIn() {

		getApplicationUrl();
		loginPage = new LoginPage(driver);
		waitHelper = new WaitHelper(driver);
	}


	@Test(groups = { "sanity", "regression" },alwaysRun = true)
	public void Risk_TC004_CloseFormValidation() {

		logReport("!!!!!!!!!! Risk_TC004_CloseFormValidation starts !!!!!!!!!!");
		logReport("Test Description: Verify the Close form - 'Cancel', 'Don'tSave' and 'Save' functionality");

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

		// CloseForm- PopUpText Validation
		riskFormPage.closeForm();
		logReport("Clicked on Close Form button");

		String closeFormPopupText = riskFormPage.getCloseFormPopupText();
		//AssertionHelper.verifyText(closeFormPopupText, ApplicationMessages.FORM_CLOSE_POPUP);

		// CloseForm- Cancel Validation
		riskFormPage.closeFormCancel();
		logReport("Clicked on cancel button at the close form popup screen");
		riskFormPage.waitForCloseFormPopUpDisappear();
		String currentPageTitle = driver.getTitle();
		AssertionHelper.comparePartialText(currentPageTitle, "Risk");
		logReport("As expected - Risk Form was not closed by clicking on 'cancel' button");

		// CloseForm- Don't Save Validation
		riskFormPage.closeForm();
		riskFormPage.closeFormDontSave();
		logReport("Clicked on Don't Save button at the close form popup screen");
		waitHelper.waitTillTitleIsNot(driver, "Risk");
		currentPageTitle = driver.getTitle();
		boolean status = !currentPageTitle.contains("Risk");
		AssertionHelper.updateTestStatus(status);
		logReport("As expected - Risk Form got closed by clicking on Don't Save button button");

		// CloseForm- Save Validation
//		homePage.clickOnGRCLibraries();
//		waitHelper.waitForTitle(driver, "Manage");
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		String riskFormName = "RISKAuto" + System.currentTimeMillis();
		riskFormPage.setORTaxonomyName(riskFormName);
		String formSavedText = riskFormPage.saveForm();
		logReport("Clicked Save button button to Save the form");

		//AssertionHelper.verifyText(formSavedText, ApplicationMessages.FORM_SAVED_CONFIRM);
		logReport("Form was saved sucessfully");

		logReport("!!!!!!!!!! Risk_TC004_CloseFormValidation ends !!!!!!!!!!");

	}

	
	@Test(groups = { "sanity", "regression" },alwaysRun = true)
	public void Risk_TC004_RiskFormInitiationByBussAdmin() throws IOException {

		logReport("!!!!!!!!!! Risk_TC004_RiskFormInitiationByBussAdmin starts !!!!!!!!!!");
		logReport(
				"Test Description: Verify if user with Business Admin role is able to initiate and send risk for approval");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
		logReport("logged-in to application");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedInUser + " was sucessfull");

		// Goto Risk form page
//		libraryPage = homePage.clickOnGRCLibraries();
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		// Filling Risk Form
		String riskFormName = "RISKAuto" + System.currentTimeMillis();
		riskFormPage.setORTaxonomyName(riskFormName);
		logReport("Populated the Risk Form Name (i.e. OR Taxonomy Name) with a unique generated name: " + riskFormName);
		riskFormPage.fillRiskForm("Risk_DataSet001");

		// Send the Form for Approval
		String fromSubmitStatus = riskFormPage.sendForApproval("Risk_DataSet001");
		//AssertionHelper.comparePartialText(fromSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);

		logReport("!!!!!!!!!! Risk_TC004_RiskFormInitiationByBussAdmin ends !!!!!!!!!!");

	}

	
	@Test(groups = { "sanity", "regression" },alwaysRun = true)
	public void Risk_TC005_RiskFormInitiationByLibraryOwner() throws IOException {

		logReport("!!!!!!!!!! Risk_TC005_RiskFormInitiationByLibraryOwner starts !!!!!!!!!!");
		logReport(
				"Test Description: Verify if user with Risk Owner role is able to initiate and send risk for approval");

		// Login to application as Risk Owner
		homePage = loginPage.loginAs("RiskOwner");
		logReport("logged-in to application");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedInUser + " was sucessfull");

		// Goto Risk form page
//		libraryPage = homePage.clickOnGRCLibraries();
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		// Fill the Risk Form
		String riskFormName = "RISKAuto" + System.currentTimeMillis();
		riskFormPage.setORTaxonomyName(riskFormName);
		logReport("Populated the Risk Form Name (i.e. OR Taxonomy Name) with a unique generated name: " + riskFormName);
		riskFormPage.fillRiskForm("Risk_DataSet001");

		// Send the Form for Approval
		String formSubmitStatus = riskFormPage.sendForApproval("Risk_DataSet001");
		//AssertionHelper.comparePartialText(formSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);

		logReport("!!!!!!!!!! Risk_TC005_RiskFormInitiationByLibraryOwner ends !!!!!!!!!!");

	}

	@Test(groups = { "sanity", "regression" },alwaysRun = true)
	public void Risk_TC006_RiskFormClarificationReqByLibOwner() throws IOException {

		logReport("!!!!!!!!!! Risk_TC006_RiskFormClarificationReqByLibOwner starts !!!!!!!!!!");
		logReport(
				"Test Description: Verify if Library Owner is able request for clarification on the Risk form created by Business Admin");

		// Login to application as BusinessAdmin
		homePage = loginPage.loginAs("BusinessAdmin");
		logReport("logged-in to application");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedInUser + " was sucessfull");

		// Goto Risk Form Page
//		libraryPage = homePage.clickOnGRCLibraries();
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
		logReport("logged-in to application");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedUser + " was sucessfull");

		// Navigate to MyTask Page
		myTasksPage = homePage.clickOnMyTasks();

		// Search the Risk Form created by Business Admin
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm created by Business Admin and opened it");

		// Request for clarification
		String clarificationSubmitStatus = riskFormPage.requestClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(clarificationSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);

		logReport("!!!!!!!!!! Risk_TC006_RiskFormClarificationReqByLibOwner ends !!!!!!!!!!");

	}


	@Test(groups = { "sanity", "regression" },alwaysRun = true)
	public void Risk_TC007_RiskFormClarificationProvidedByBA() throws IOException {

		logReport("!!!!!!!!!! Risk_TC007_RiskFormClarificationProvidedByBA starts !!!!!!!!!!");
		logReport(
				"Test Description: For the Risk form created by Business Admin, Verify if Business Admin is able to provide justification for requested clarification by Risk Owner");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
		logReport("logged-in to application");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("Login as :" + loggedInUser + " was sucessfull");

		// Goto Risk Form Page
//		libraryPage = homePage.clickOnGRCLibraries();
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
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Navigate to MyTask Page
		myTasksPage = homePage.clickOnMyTasks();

		// search the Risk Form created by Business Admin
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm created by Business Admin and opened it");

		// Request for clarification
		String clarificationRequestStatus = riskFormPage.requestClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(clarificationRequestStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);

		homePage.SignOut();
		logReport("logged out as Risk Owner");

		// Now login to Application as Business Admin
		getApplicationUrl();
		loginPage.loginAs("BusinessAdmin");
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Navigate to MyTask Page
		myTasksPage = homePage.clickOnMyTasks();

		// search the Risk Form on which Risk Owner has asked for clarification
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm on which Risk Owner has asked for clarification");

		// Provide clarification
		String clarificationSubmitStatus = riskFormPage.provideClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(clarificationSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);

		logReport("!!!!!!!!!! Risk_TC007_RiskFormClarificationProvidedByBA ends !!!!!!!!!!");

	}

	
	@Test(groups = { "sanity", "regression" },alwaysRun = true)
	public void Risk_TC008_RiskFormApprovalByRiskOwner() throws IOException {

		logReport("!!!!!!!!!! Risk_TC008_RiskFormApprovalByRiskOwner starts !!!!!!!!!!");
		logReport(
				"Test Description: For the Risk form created by Business Admin, Verify if Risk owner is able to approve it post recieving the justification for the requested clarification");

		// Login to application as Business Admin
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Goto Risk Form Page
//		libraryPage = homePage.clickOnGRCLibraries();
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
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Navigate to MyTask Page
		myTasksPage = homePage.clickOnMyTasks();

		// search the Risk Form created by Business Admin
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm created by Business Admin and opened it");

		// Request for clarification
		String clarificationRequestStatus = riskFormPage.requestClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(clarificationRequestStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);

		homePage.SignOut();
		logReport("logged out as Risk Owner");

		// Now login to Application as Business Admin
		getApplicationUrl();
		loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		logReport("logged-in to application as " + homePage.getLoggedInUserDetails());

		// Navigate to MyTask Page
		homePage.clickOnMyTasks();

		// search the Risk Form on which Risk Owner has asked for clarification
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm on which Risk Owner has asked for clarification");

		// Provide clarification
		String clarificationSubmitStatus = riskFormPage.provideClarification("Risk_DataSet001");
		//AssertionHelper.comparePartialText(clarificationSubmitStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);

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
		homePage.clickOnMyTasks();

		// search the Risk Form (on which Business Admin has provided
		// clarification) to approve it
		myTasksPage.searchTaskAndClick(riskFormName);
		waitHelper.waitForTitle(driver, "Risk");
		logReport("searched the RiskForm (on which Business Admin has provided clarification) to approve it");

		// Approve the Risk Form
		String finalSubmissionStatus = riskFormPage.approveRiskForm("Risk_DataSet001");
		//AssertionHelper.comparePartialText(finalSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Risk Form approved and submitted sucessfully");

		// verify if approved Risk form appears in the Reports
		logReport(
				"Navigate to Report section to verify if the approved Risk form is present in the List of published Reports");
//		homePage.clickOnGRCLibraries();
		libraryPage = homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
		waitHelper.WaitForElementVisibleWithPollingTime(libraryPage.reports);
		waitHelper.WaitForElementClickable(libraryPage.reports);
		if ((loggedInUser.contains("TestRisk Owner")) || (loggedInUser.contains("TestBusiness Admin"))) {				
			libraryPage.clickOnReport("Risks");			
		} else {
			// release 8 patch fix
			libraryPage.clickOnRisksReport("Risks");
		}	
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
//		  captureScreen();	

		logReport("!!!!!!!!!! Risk_TC008_RiskFormApprovalByRiskOwner ends !!!!!!!!!!");

	}

	
	@Test(groups = { "sanity" },alwaysRun = true)
	public void Risk_TC009_ActiveRiskFormUpdateByBA() throws IOException {

		logReport("!!!!!!!!!! Risk_TC009_ActiveRiskFormUpdateByBA starts !!!!!!!!!!");
		logReport(
				"Test Description: Verify if user with Business Admin role can update existing risk via GUI and send for approval");

		// *************Pre-condition: Create a Active Risk Form**************
		logReport("========== creating the Pre-condition ==========");
		homePage = loginPage.loginAs("BusinessAdmin");
//		homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
//		libraryPage = homePage.clickOnGRCLibraries();
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet002");
		logReport("========== Pre-condition setUp done ==========");
		// *************Pre-condition: Create a Active Risk Form**************

//		homePage.clickOnGRCLibraries();
		libraryPage=homePage.goToInfoCenter("Lib");	
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
		riskFormPage.editApprovedRiskForm("Risk_DataSet003");
		String formSubmissionStatus = riskFormPage.sendForApproval("Risk_DataSet003");
		//AssertionHelper.comparePartialText(formSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Edited form submitted sucessfully and sent for approval");

		logReport("!!!!!!!!!! Risk_TC009_ActiveRiskFormUpdateByBA ends !!!!!!!!!!");
	}

	
	@Test(groups = { "sanity" },alwaysRun = true)
	public void Risk_TC010_ActiveRiskFormUpdateByRO() throws IOException {

		logReport("!!!!!!!!!! Risk_TC010_ActiveRiskFormUpdateByRO starts !!!!!!!!!!");
		logReport(
				"Test Description: Verify if user with Risk Owner role can update existing risk via GUI and send for approval");

		// *************Pre-condition: Create a Active Risk Form**************
		// //
		logReport("========== creating the Pre-condition ==========");
		homePage = loginPage.loginAs("RiskOwner");
//		homePage.clickOnCIDpopUp();		
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport(loggedInUser + " logged IN sucessfully");
//		libraryPage = homePage.clickOnGRCLibraries();
//		libraryPage.clickOnForm("Risk");
//		waitHelper.waitForTitle(driver, "Risk");
		driver.navigate().to(AppConfig.getConfig("applicationUrl") + AppConfig.getConfig("navigateToRiskForm"));
		RiskFormPage riskFormPage = new RiskFormPage(driver);
		String riskFormName = riskFormPage.createRiskForm("Risk_DataSet001");
		logReport("========== Pre-condition setUp done ==========");
		// *************Pre-condition: Create a Active Risk Form**************
		// //

//		homePage.clickOnGRCLibraries();
		libraryPage=homePage.goToInfoCenter("Lib");
		waitHelper.waitForPageComponentLoad();
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
		riskFormPage.editApprovedRiskForm("Risk_DataSet003");
		String formSubmissionStatus = riskFormPage.sendForApproval("Risk_DataSet003");
		//AssertionHelper.comparePartialText(formSubmissionStatus, ApplicationMessages.FORM_SUBMIT_CONFIRM);
		logReport("Edited form submitted sucessfully and sent for approval");

		logReport("!!!!!!!!!! Risk_TC010_ActiveRiskFormUpdateByRO ends !!!!!!!!!!");
	}

	@AfterMethod(alwaysRun = true)
	public void logOut() {

		if (!driver.getTitle().contains("Signin")) {
			homePage.SignOut();
		}
	}


}
