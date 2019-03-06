package com.automation.testScripts.commonTestCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.helper.assertion.AssertionHelper;
import com.automation.helper.exception.AutomationException;
import com.automation.helper.reader.ReadData;
import com.automation.helper.wait.WaitHelper;
import com.automation.pageobjects.HomePage;
import com.automation.pageobjects.LibraryPage;
import com.automation.pageobjects.LoginPage;
import com.automation.testBase.TestBase;

public class VerifyLandingPg_DiffUsers extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	LibraryPage libraryPage;
	WaitHelper waitHelper = new WaitHelper(driver);

	/*
	 * @BeforeMethod public void logIn() { loginPage = new LoginPage(driver); }
	 */

	@DataProvider(name = "SITLogin")
	public String[][] getLoginData() {
		String[][] loginRecords = ReadData.fromExcel.getEntireDataFromSheet("testdata.xlsx", "SITLoginData");
		return loginRecords;
	}

	
	@Test(dataProvider = "SITLogin", description = "Verify the landing page of the user with Business Admin role", groups = {
			"sanity" })
	public void Sanity_VerifyingLandingPgForDiffUsers(String role, String userName, String password) {

		logReport("!!!!!!!!!! Sanity_VerifyingLandingPgFor " + role + " starts !!!!!!!!!!");

		getApplicationUrl();
		loginPage = new LoginPage(driver);
		homePage = loginPage.loginToApplication(userName, password);
		logReport("logged-in to application");
		// homePage.clickOnCIDpopUp();
		waitHelper.waitForTitle(driver, "Home");
		String loggedInUser = homePage.getLoggedInUserDetails();
		logReport("login as " + loggedInUser + " was sucessfull");
		if(role.equalsIgnoreCase("ITAdmin")){
			libraryPage = homePage.goToInfoCenter("Operational");
		}else{
			libraryPage = homePage.goToInfoCenter("Lib");
		}
		
//		waitHelper.waitForTitle(driver, "Manage");

		if (!(role.equals("ITAdmin")) && !(role.equals("GRCViewer"))) {
			switch (role) {
			case "BusinessAdmin":			
				libraryPage.clickOnForms();
				// Asset Form:				
				libraryPage.getAssetForm();
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Asset"));
				// Control Form:
				libraryPage.getControlForm();
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Control"));
				// IUC Form:
				libraryPage.getIUCForm();
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("IUC"));				
				// Risk Form:
				libraryPage.getRiskForm();
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Risk"));				
				// Vendor Form:
				libraryPage.getVendorForm();
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Vendor"));
				
//				// Others :
//				libraryPage.getOtherForms();
//				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Asset"));
//				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Regulatory Body"));
//				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Regulatory Rule"));				
//				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Vendor"));				
				// AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Process"));
				break;

			case "RiskOwner":
				List<WebElement> roFormList = libraryPage.getListOfAllForms();
				AssertionHelper.verifyText(roFormList.size() + "", "1");
				logReport(loggedInUser + " has access to only one Form i.e. " + roFormList.get(0).getText());
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Risk"));
				break;

			case "ControlOwner":
				List<WebElement> coFormList = libraryPage.getListOfAllForms();
				AssertionHelper.verifyText(coFormList.size() + "", "1");
				logReport(loggedInUser + " has access to only one Form i.e. " + coFormList.get(0).getText());
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Control"));
				break;

			case "AssetOwner":
				List<WebElement> aoFormList = libraryPage.getListOfAllForms();
				AssertionHelper.verifyText(aoFormList.size() + "", "1");
				logReport(loggedInUser + " has access to only one Form i.e. " + aoFormList.get(0).getText());
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Asset"));
				break;

			case "IUCOwner":
				List<WebElement> iucFormList = libraryPage.getListOfAllForms();
				AssertionHelper.verifyText(iucFormList.size() + "", "1");
				logReport(loggedInUser + " has access to only one Form i.e. " + iucFormList.get(0).getText());
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("IUC"));				
				break;

			case "VendorOwner":
				List<WebElement> voFormList = libraryPage.getListOfAllForms();
				AssertionHelper.verifyText(voFormList.size() + "", "1");
				logReport(loggedInUser + " has access to only one Form i.e. " + voFormList.get(0).getText());
				AssertionHelper.updateTestStatus(libraryPage.isFormDisplayed("Vendor"));
				break;

			default:
				throw new AutomationException("Logged in user was not expected : " + loggedInUser);
			}
		}

		switch (role) {		
		case "ITAdmin":		
			libraryPage.clickOnReports();
			// Control Related :			
			List<WebElement> controlsList = libraryPage.getControlRelatedReports();			
			AssertionHelper.verifyText(controlsList.size() + "", "2");			
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Controls"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("IUC"));
			// AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Processes"));
			
			// Risks :
			List<WebElement> riskReportList = libraryPage.getRisksReport();
			AssertionHelper.verifyText(riskReportList.size() + "", "1");	
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Risks"));
			
			// Others :	
			List<WebElement> othersReportList = libraryPage.getOthersReport();
			AssertionHelper.verifyText(othersReportList.size() + "", "4");	
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Assets"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Regulatory Rules"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Regulatory Bodies"));			
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Vendors"));
			break;
	    case "BusinessAdmin":
			List<WebElement> bAReportList = libraryPage.getListOfAllReports();
			AssertionHelper.verifyText(bAReportList.size() + "", "5");
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Assets"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Controls"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("IUC"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Risks"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Vendors"));
			break;

		case "RiskOwner":
			List<WebElement> roReportList = libraryPage.getListOfAllReports();
			AssertionHelper.verifyText(roReportList.size() + "", "5");
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Assets"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Controls"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("IUC"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Risks"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Vendors"));
			break;

		case "ControlOwner":
			List<WebElement> coReportList = libraryPage.getListOfAllReports();
			AssertionHelper.verifyText(coReportList.size() + "", "5");
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Assets"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Controls"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("IUC"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Risks"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Vendors"));
			break;

		case "AssetOwner":
			List<WebElement> aoReportList = libraryPage.getListOfAllReports();
			AssertionHelper.verifyText(aoReportList.size() + "", "5");
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Assets"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Controls"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("IUC"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Risks"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Vendors"));
			break;

		case "IUCOwner":
			List<WebElement> iucReportList = libraryPage.getListOfAllReports();
			AssertionHelper.verifyText(iucReportList.size() + "", "5");
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Assets"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Controls"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("IUC"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Risks"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Vendors"));
			break;

		case "VendorOwner":
			List<WebElement> voReportList = libraryPage.getListOfAllReports();
			AssertionHelper.verifyText(voReportList.size() + "", "5");
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Assets"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Controls"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("IUC"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Risks"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Vendors"));
			break;

		case "GRCViewer":
			List<WebElement> gvReportList = libraryPage.getListOfAllReports();
			AssertionHelper.verifyText(gvReportList.size() + "", "5");
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Assets"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Controls"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("IUC"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Risks"));
			AssertionHelper.updateTestStatus(libraryPage.isReportDisplayed("Vendors"));
			break;

		default:
			throw new AutomationException("Logged in user was not expected : " + loggedInUser);
		}

		homePage.SignOut();
		logReport("user: " + loggedInUser + " loggedOut");

		logReport("!!!!!!!!!! Sanity_VerifyingLandingPgFor " + role + " Ends !!!!!!!!!!");

	}

	/*
	 * @AfterMethod public void logOut() {
	 * 
	 * if (!driver.getTitle().contains("Signin")) { homePage.SignOut(); } }
	 */


}
