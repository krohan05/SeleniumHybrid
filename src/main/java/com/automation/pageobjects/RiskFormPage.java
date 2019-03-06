package com.automation.pageobjects;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.automation.helper.browserConfiguration.BrowserType;
import com.automation.helper.dropdown.DropDownHelper;
import com.automation.helper.javascript.JavaScriptHelper;
import com.automation.helper.reader.AppConfig;
import com.automation.helper.reader.ReadData;
import com.automation.helper.wait.WaitHelper;
import com.automation.testBase.TestBase;

public class RiskFormPage extends TestBase {
	
	private WebDriver driver;
	WaitHelper waitHelper;
	JavaScriptHelper jsHelper;
	DropDownHelper dropDownHelper;
	Actions action;

	@FindBy(xpath = "//div[contains(@class, 'OBJECT_LEVEL')]/label[@class='control-label']")
	WebElement headerLevel;

	@FindBy(xpath = "//div[contains(@class, 'OBJECT_LEVEL')]/label[contains(@class,'control-label-value')]")
	WebElement headerLevelValue;

	@FindBy(xpath = "//div[contains(@class, 'OBJ_STATUS')]/label[@class='control-label']")
	WebElement headerStatus;

	@FindBy(xpath = "//div[contains(@class, 'OBJ_STATUS')]/label[contains(@class,'control-label-value')]")
	WebElement headerStatusValue;

	@FindBy(xpath = "//button[@id='btn-fullscreen']")
	WebElement fullScreenIcon;

	@FindBy(xpath = "//button[@data-title='Show Help']")
	WebElement showHelpIcon;

	@FindBy(xpath = "//button[@id='btn-cancel' and contains(text(),'Close')]")
	WebElement closeButton;

	@FindBy(xpath = "//button[@id='btn-submit' and contains(text(),'Send for Approval')]")
	WebElement sendForApprovalButton;

	@FindBy(xpath = "//h4[contains(text(),'Send for Approval')]/../..")
	WebElement sendForApprovalPopUp;

	@FindBy(xpath = "//h4[contains(text(),'Send for Approval')]/../..//button[@id='submit']")
	WebElement approvalSubmit;

	@FindBy(xpath = "//textarea[@data-id='ACTION_COMMENTS']")
	WebElement commentBox;

	@FindBy(xpath = "//button[@id='btn-save' and contains(text(),'Save')]")
	WebElement saveButton;

	@FindBy(xpath = "//button[@id='btn-submit']")
	WebElement submitButton;

	@FindBy(xpath = "//button[@id='btn-submit']/..//a[contains(text(),'Approve')]")
	WebElement approveForm;

	@FindBy(xpath = "//h4[contains(text(),'Approve')]/../..")
	WebElement approvePopUpWin;

	@FindBy(xpath = "//h4[contains(text(),'Approve')]/../..//button[@id='submit']")
	WebElement approveSubmitBtn;

	@FindBy(xpath = "//button[@id='btn-submit']/..//a[contains(text(),'Request Clarifications')]")
	WebElement requestClarification;

	@FindBy(xpath = "//h4[contains(text(),'Request Clarifications')]/../..")
	WebElement requestClarificationPopUp;

	@FindBy(xpath = "//h4[contains(text(),'Request Clarifications')]/../..//button[@id='submit']")
	WebElement requestClarificationSubmit;

	@FindBy(xpath = "//button[@id='btn-submit']/..//a[contains(text(),'Cancel')]")
	WebElement cancelForm;

	@FindBy(xpath = "//h4[contains(text(),'Cancel')]/../..//button[@id='submit']")
	WebElement cancelFormSubmit;

	@FindBy(xpath = "//h4[contains(text(),'Submit Clarifications')]/../..")
	WebElement submitClarificationPopUp;

	@FindBy(xpath = "//h4[contains(text(),'Submit Clarifications')]/../..//button[@id='submit']")
	WebElement provideClarificationSubmitBtn;

	@FindBy(xpath = "//div[@id='closeForms']//div[@class='modal-content']")
	WebElement closeFormPopup;

	@FindBy(xpath = "//div[@id='closeForms']//div[@class='modal-footer']//button[contains(text(),'Cancel')]")
	WebElement closeFormPopupCancel;

	@FindBy(xpath = "//div[@id='closeForms']//div[@class='modal-footer']//button[contains(text(),\"Don't Save\")]")
	WebElement closeFormPopupDontSave;

	@FindBy(xpath = "//input[@placeholder='OR Taxonomy Name']")
	WebElement oRTaxonomyName;

	@FindBy(xpath = "//label[contains(text(),'OR Taxonomy Name')]/../following-sibling::div//div[@id='s2id_OBJECT_LEVEL']")
	WebElement level;

	By parents = By.xpath("//span[contains(text(),'Parents')]");

	@FindBy(xpath = "//div[@class='modal-content']//table[@class='backgrid table table-striped table-bordered']//tbody/tr[1]/td[1]/input")
	WebElement parentRoadioBtn;

	@FindBy(xpath = "//span[contains(text(),'Parents')]/../..")
	WebElement parentsPopUpWin;

	@FindBy(xpath = "//span[contains(text(),'Parents')]/../..//input[@id='done']")
	WebElement parentsPopUpDone;

	@FindBy(xpath = "//textarea[@data-id='A_OR_TAXONOMY_DESCRIPTION']")
	WebElement oRTaxonomyDes;

	@FindBy(xpath = "//input[@data-id='A_INTERNAL_OR_TAXONOMY_ID']")
	WebElement internalORTaxonomyID;

	@FindBy(xpath = "//label[contains(text(),'Categories')]/..//input[@class='select2-input select2-default']")
	WebElement categories;

	@FindBy(xpath = "//input[@id='VALID_FROM']")
	WebElement dateValidFrom;

	@FindBy(xpath = "//input[@id='VALID_UNTIL']")
	WebElement dateValidUntil;

	@FindBy(xpath = "//div[contains(text(),'Owner Organizations')]")
	WebElement ownerOrganizations;
	// label[contains(text(),'Business Hierarchies')]/..//i[@class='icn
	// icn-plus-t']

	@FindBy(xpath = "//span[contains(text(),'Owner Organizations')]/../..")
	WebElement bussHierarPopUpWindow;

	@FindBy(xpath = "//span[contains(text(),'Organizations')]/../../..//span[contains(text(),'All Functions')]/../following-sibling::span")
	WebElement allOrgRadioBtn;
	// span[contains(text(),'Organizations')]/../..//span[contains(text(),'All
	// Functions')]/../..//label

	@FindBy(xpath = "//span[contains(text(),'Location')]/../../..//span[contains(text(),'Regional')]/../following-sibling::span")
	WebElement allLocationRadioBtn;
	// span[contains(text(),'Location')]/../..//span[contains(text(),'Regional')]/../..//label

	@FindBy(id = "addTuple")
	WebElement bussHierarPopUpAddBtn;

	@FindBy(xpath = "//label[contains(text(),'Owners')]/..//span[contains(text(),'Owners')]")
	WebElement owners;

	@FindBy(xpath = "//*[@id='searchTab']/form/input")
	WebElement ownersSearch;

	@FindBy(xpath = "//span[text()='Owners' and @class='modalHeading']/../..")
	WebElement ownersPopupWindow;

	@FindBy(id = "done")
	WebElement ownersPopUpDone;

	@FindBy(xpath = "//label[contains(text(),'Restrict Access To')]/..//span[@class='select2-chosen']")
	WebElement restrictAccessTo;

	@FindBy(xpath = "//label[contains(text(),'Restrict Access To')]/..//div[@id='select2-drop']")
	WebElement restrictAccessDDsection;

	@FindBy(xpath = "//select[@id='RESTRICT_ACCESS_TO']")
	WebElement restrictAccessDrpDwnElements;

	@FindBy(xpath = "//div[@class='alert alert-info']")
	WebElement formSavedText;

	@FindBy(xpath = "//div[@data-action='formSubmitSuccess']")
	WebElement formSubmittedText;

	@FindBy(xpath = "//button[@id='btn-edit']")
	WebElement editButton;

	@FindBys(@FindBy(xpath = "//section[contains(@class,'form-section')]//legend[contains(@class,'section-legend  accordion')]"))
	List<WebElement> riskFormSections;

	@FindBys(@FindBy(xpath = "//ul[@class='orb-Links col-md-11 col-xs-11']/li"))
	List<WebElement> allRelationships;

	@FindBy(xpath = "//label[@data-id='OBJ_STATUS']")
	WebElement additionalDetailsStatus;

	@FindBy(xpath = "//label[@data-id='OBJ_CREATED_ON']")
	WebElement createdOn;

	@FindBy(xpath = "//label[@data-id='OBJ_CREATED_BY']")
	WebElement createdBy;

	@FindBy(xpath = "//label[@data-id='OBJ_MODIFIED_ON']")
	WebElement modifiedOn;

	@FindBy(xpath = "//label[@data-id='OBJ_MODIFIED_BY']")
	WebElement modifiedBy;

	@FindBy(xpath = "//label[contains(text(),'Attach Files')]/..//div[@title='Upload New']/..")
	WebElement attachFiles;

	@FindBy(xpath = "//legend[contains(text(),'Relationships')]/..//li/a[contains(text(),'Applies to Organizations')]")
	WebElement relationshipAppliesToBH;

	@FindBy(xpath = "//legend[contains(text(),'Relationships')]/..//li/a[contains(text(),'Assets')]")
	WebElement relationshipAssets;

	@FindBy(xpath = "//legend[contains(text(),'Relationships')]/..//li/a[contains(text(),'Controls')]")
	WebElement relationshipControls;

	@FindBy(xpath = "//legend[contains(text(),'Relationships')]/..//li/a[contains(text(),'Processes')]")
	WebElement relationshipProcesses;

	@FindBy(xpath = "//legend[contains(text(),'Relationships')]/..//li/a[contains(text(),'Risks')]")
	WebElement relationshipRisks;

	@FindBy(xpath = "//legend[contains(text(),'Relationships')]/..//li/a[contains(text(),'Vendors')]")
	WebElement relationshipVendors;

	@FindBy(xpath = "//div[contains(@class, 'modal-header-wrapper')]")
	WebElement modalHeader;

	@FindBy(xpath = "//span[@class='modalHeading' and contains(text(),'Assets')]/../..//tbody/tr[1]/td[1]/input")
	WebElement assetCheckbox;

	@FindBy(xpath = "//span[@class='modalHeading' and contains(text(),'Controls')]/../..//tbody/tr[1]/td[1]/input")
	WebElement controlCheckbox;

	@FindBy(xpath = "//span[@class='modalHeading' and contains(text(),'Processes')]/../..//tbody/tr[1]/td[1]/input")
	WebElement processCheckbox;

	@FindBy(xpath = "//span[@class='modalHeading' and contains(text(),'Risks')]/../..//tbody/tr[1]/td[1]/input")
	WebElement riskCheckbox;

	@FindBy(xpath = "//span[@class='modalHeading' and contains(text(),'Vendors')]/../..//tbody/tr[1]/td[1]/input")
	WebElement vendorCheckbox;

	@FindBy(id = "done")
	WebElement modalPopUpDone;

	By headerAlert = By.id("msiStatus");

	public RiskFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		jsHelper = new JavaScriptHelper(driver);
		dropDownHelper = new DropDownHelper(driver);
		action = new Actions(driver);
	}

	public void closeForm() {
		closeButton.click();
	}

	public String getCloseFormPopupText() {
		waitHelper.waitForElement(closeFormPopup);
		return closeFormPopup.getText();
	}

	public void closeFormCancel() {
		waitHelper.waitForElement(closeFormPopupCancel);
		jsHelper.clickElement(closeFormPopupCancel);
	}

	public void closeFormDontSave() {
		waitHelper.waitForElement(closeFormPopupDontSave);
		jsHelper.clickElement(closeFormPopupDontSave);
	}

	public void waitForCloseFormPopUpDisappear() {
		waitHelper.waitForElementNotPresent(closeFormPopup);
	}

	public void setORTaxonomyName(String formName) {
		oRTaxonomyName.sendKeys(formName);
	}

	public String saveForm() {
		saveButton.click();
		waitHelper.WaitForElementVisibleWithPollingTime(formSavedText);
		return formSavedText.getText();
	}

	public String getRiskFormName() {
		return oRTaxonomyName.getText();
	}

	public void fillRiskForm(String dataSet) throws IOException {
		fillDetailsSecOfRiskForm(dataSet);
		fillOwnrshpAndSecuritySec(dataSet);
		fillRelationshipSecion(dataSet);
		fillAdditionalDetailsSection(dataSet);
	}

	public String createRiskForm(String dataSet) throws IOException {
		String RiskFormName = "RISKAuto" + System.currentTimeMillis();
		oRTaxonomyName.sendKeys(RiskFormName);
		fillDetailsSecOfRiskForm(dataSet);
		fillOwnrshpAndSecuritySec(dataSet);
		fillRelationshipSecion(dataSet);
		fillAdditionalDetailsSection(dataSet);
		sendForApproval(dataSet);
		logReport("created Risk Form name is: " + RiskFormName);
		return RiskFormName;
	}

	public void fillDetailsSecOfRiskForm(String dataSet) {
		logReport("Started filling the Details Section Of the RiskForm");
		action.click(level).pause(800).sendKeys(ReadData.fromExcel.getFormData(dataSet, "Level"), Keys.ENTER).build()
				.perform();
		logReport("Populated the 'Level' field");

		if (!ReadData.fromExcel.getFormData(dataSet, "Level").equals("Level 1")) {
			waitHelper.WaitForElementVisibleWithPollingTime(parents);
			waitHelper.WaitForElementClickable(parents);
			driver.findElement(parents).click();
			waitHelper.WaitForModalPopUpAppear();
			waitHelper.WaitForElementClickable(parentRoadioBtn);
			jsHelper.clickElement(parentRoadioBtn);
			parentsPopUpDone.click();
			waitHelper.WaitForModalPopUpDisAppear();
			logReport("Populated the 'Parents' field");
		}

		waitHelper.waitForElement(oRTaxonomyDes);
		oRTaxonomyDes.sendKeys(ReadData.fromExcel.getFormData(dataSet, "ORTaxonomyDescription"));
		logReport("Populated the 'TaxonomyDes' field");

		if (!ReadData.fromExcel.getFormData(dataSet, "InternalORTaxonomyID").equalsIgnoreCase("NA")) {
			internalORTaxonomyID.sendKeys(ReadData.fromExcel.getFormData(dataSet, "InternalORTaxonomyID"));
		}

		action.click(categories).sendKeys(ReadData.fromExcel.getFormData(dataSet, "Categories"), Keys.ENTER, Keys.TAB)
				.build().perform();
		logReport("Populated the 'Categories' field");

		jsHelper.scrollIntoView(dateValidFrom);
		if (!ReadData.fromExcel.getFormData(dataSet, "DateValidFrom").equalsIgnoreCase("NA")) {
			action.click(dateValidFrom).sendKeys(ReadData.fromExcel.getFormData(dataSet, "DateValidFrom"), Keys.ENTER)
					.build().perform();
			logReport("Populated the 'ValidFrom' date");
		}

		jsHelper.scrollIntoView(dateValidUntil);
		if (!ReadData.fromExcel.getFormData(dataSet, "dateValidUntil").equalsIgnoreCase("NA")) {
			action.click(dateValidUntil).sendKeys(ReadData.fromExcel.getFormData(dataSet, "DateValidUntil"), Keys.ENTER)
					.build().perform();
			logReport("Populated the 'ValidUntil' date");
		}

	}

	public void fillOwnrshpAndSecuritySec(String dataSet) {
		logReport("Started filling the OwnerShip and Security Section Of the RiskForm");

		ownerOrganizations.click();
		logReport("clicked on Bussiness Hierarchy");

		waitHelper.WaitForModalPopUpAppear();

		allOrgRadioBtn.click();
		logReport("selected organisation");

		allLocationRadioBtn.click();
		logReport("selected Loctaion");

		bussHierarPopUpAddBtn.click();
		logReport("clicked on add button to add Organization and Location");

		waitHelper.WaitForModalPopUpDisAppear();

		waitHelper.WaitForElementClickable(owners);
		action.moveToElement(owners).click().pause(2000).build().perform();
		logReport("clicked on Owners field");

		waitHelper.waitForPageLoad();
		waitHelper.WaitForModalPopUpAppear();

		action.click(ownersSearch).sendKeys(ReadData.fromExcel.getFormData(dataSet, "OwnersUserName"), Keys.ENTER)
				.pause(100).build().perform();
		logReport("Populated the 'Owners list'");

		WebElement OwnerRadioBtn = driver.findElement(
				By.xpath("//table[@class='backgrid table table-striped table-bordered']//span[contains(text(),'"
						+ ReadData.fromExcel.getFormData(dataSet, "OwnersUserName")
						+ "')]/../preceding-sibling::td/input[@type='radio']"));
		waitHelper.waitForElement(OwnerRadioBtn);
		OwnerRadioBtn.click();
		logReport("Selected the Owner");
		ownersPopUpDone.click();

		waitHelper.WaitForModalPopUpDisAppear();

		if (!ReadData.fromExcel.getFormData(dataSet, "RestrictAccessTo").equalsIgnoreCase("No Restriction")) {

			waitHelper.WaitForElementClickable(restrictAccessTo);

			restrictAccessTo.click();
			waitHelper.waitForElement(restrictAccessDDsection);
			dropDownHelper.selectUsingVisibleText(restrictAccessDrpDwnElements,
					ReadData.fromExcel.getFormData(dataSet, "RestrictAccessTo"));
			action.pause(800).build().perform();
		}
		logReport("Populated the 'Restrict Access To' field");
	}

	public void fillRelationshipSecion(String dataSet) {
		if (ReadData.fromExcel.getFormData(dataSet, "FillRelationship").toLowerCase().equalsIgnoreCase("yes")) {
			logReport("	Started filling the Relationship Section Of the RiskForm");
			selectRelationshipBusinessHierarchy();
			logReport("selected the Relationship - 'Applies to Organizations' ");
			selectRelationshipAssets();
			logReport("selected the Relationship - 'Assets' ");
			selectRelationshipControls();
			logReport("selected the Relationship - 'Controls' ");
			// selectRelationshipProcess();
			// logReport("selected the Relationship - 'Processes' ");
			selectRelationshipRisks();
			logReport("selected the Relationship - 'Risks' ");
			selectRelationshipVendors();
			logReport("selected the Relationship - 'Vendors' ");
		}
	}

	public void fillAdditionalDetailsSection(String dataSet) throws IOException {

		if (ReadData.fromExcel.getFormData(dataSet, "AttachFiles").toLowerCase().equalsIgnoreCase("yes")) {

			waitHelper.WaitForElementClickable(attachFiles);
			File file = new File(AppConfig.getConfig("FileUploadFolder"));
			File[] files = file.listFiles();
			for (File f : files) {
				String filename = f.getName();
				action.click(attachFiles).pause(1000).build().perform();
				action.pause(1000).build().perform();
				if (BrowserType.valueOf("Chrome") != null) {
					Runtime.getRuntime().exec(AppConfig.getConfig("AutoItExe.ChromeBrowser") + " "
							+ AppConfig.getConfig("FilesUpload") + filename);
				} else if (BrowserType.valueOf("Iexplorer") != null) {
					Runtime.getRuntime().exec(AppConfig.getConfig("AutoItExe.IEBrowser") + " "
							+ AppConfig.getConfig("FilesUpload") + filename);
				}
				action.pause(1000).build().perform();
			}

			List<WebElement> loadedFiles = driver
					.findElements(By.xpath("//div[@class='file-caption-name']//span[@class='kv-file-size']"));
			SoftAssert softAssert = new SoftAssert();
			for (WebElement element : loadedFiles) {
				boolean upLoadStatus = element.getText().contains("B)");
				softAssert.assertTrue(upLoadStatus, "FileUploadFailed");
				if (upLoadStatus) {
					logReport(element.findElement(By.xpath("..")).getAttribute("title") + " loaded successfully");
				} else {
					logReport(
							element.findElement(By.xpath("..")).getAttribute("title") + " -------FAILED to load------");

				}
			}
			softAssert.assertAll();
		}
	}

	public void selectRelationshipBusinessHierarchy() {
		waitHelper.waitForElement(relationshipAppliesToBH);
		relationshipAppliesToBH.click();
		waitHelper.WaitForModalPopUpAppear();
		allOrgRadioBtn.click();
		allLocationRadioBtn.click();
		bussHierarPopUpAddBtn.click();
		waitHelper.WaitForModalPopUpDisAppear();
	}

	public void selectRelationshipAssets() {

		waitHelper.WaitForElementClickable(relationshipAssets);
		relationshipAssets.click();
		waitHelper.WaitForModalPopUpAppear();
		waitHelper.waitForElement(modalHeader);
		action.pause(600).build().perform();
		jsHelper.clickElement(assetCheckbox);
		while (!assetCheckbox.isSelected()) {
			jsHelper.clickElement(assetCheckbox);
		}
		modalPopUpDone.click();
		waitHelper.WaitForModalPopUpDisAppear();
	}

	public void selectRelationshipControls() {

		waitHelper.WaitForElementClickable(relationshipControls);
		relationshipControls.click();
		waitHelper.WaitForModalPopUpAppear();
		waitHelper.waitForElement(modalHeader);
		action.pause(600).build().perform();
		jsHelper.clickElement(controlCheckbox);
		while (!controlCheckbox.isSelected()) {
			jsHelper.clickElement(controlCheckbox);
		}
		modalPopUpDone.click();
		waitHelper.WaitForModalPopUpDisAppear();
	}

	public void selectRelationshipProcess() {

		waitHelper.WaitForElementClickable(relationshipProcesses);
		relationshipProcesses.click();
		waitHelper.WaitForModalPopUpAppear();
		waitHelper.waitForElement(modalHeader);
		action.pause(600).build().perform();
		jsHelper.clickElement(processCheckbox);
		while (!processCheckbox.isSelected()) {
			jsHelper.clickElement(processCheckbox);
		}
		modalPopUpDone.click();
		waitHelper.WaitForModalPopUpDisAppear();
	}

	public void selectRelationshipRisks() {

		waitHelper.WaitForElementClickable(relationshipRisks);
		relationshipRisks.click();
		waitHelper.WaitForModalPopUpAppear();
		waitHelper.waitForElement(modalHeader);
		action.pause(600).build().perform();
		jsHelper.clickElement(riskCheckbox);
		while (!riskCheckbox.isSelected()) {
			jsHelper.clickElement(riskCheckbox);
		}
		modalPopUpDone.click();
		waitHelper.WaitForModalPopUpDisAppear();
	}

	public void selectRelationshipVendors() {

		waitHelper.WaitForElementClickable(relationshipVendors);
		relationshipVendors.click();
		waitHelper.WaitForModalPopUpAppear();
		waitHelper.waitForElement(modalHeader);
		action.pause(600).build().perform();
		jsHelper.clickElement(vendorCheckbox);
		while (!vendorCheckbox.isSelected()) {
			jsHelper.clickElement(vendorCheckbox);
		}
		modalPopUpDone.click();
		waitHelper.WaitForModalPopUpDisAppear();
	}

	public String sendForApproval(String dataSet) {
		waitHelper.WaitForElementClickable(sendForApprovalButton);
		sendForApprovalButton.click();
		waitHelper.WaitForModalPopUpAppear();

		waitHelper.WaitForElementClickable(commentBox);
		commentBox.sendKeys(ReadData.fromExcel.getFormData(dataSet, "SendApprovalComment"));
		if (approvalSubmit.isDisplayed()) {
			approvalSubmit.click();
		} else {
			approvalSubmit.click();
		}
		// waitHelper.waitForElement(formSubmittedText);
		waitHelper.WaitForElementVisibleWithPollingTime(formSubmittedText);
		String formSubmissionStatus = formSubmittedText.getText();
		waitHelper.waitForStalenessOfElement(headerAlert);
		waitHelper.waitForPageComponentLoad();
		return formSubmissionStatus;

	}

	public String requestClarification(String dataSet) {
		waitHelper.WaitForElementClickable(submitButton);
		submitButton.click();
		requestClarification.click();

		logReport("clicked on Request Clarification");

		waitHelper.WaitForModalPopUpAppear();

		waitHelper.WaitForElementClickable(commentBox);
		logReport("Added the required clarification in the comment box");
		commentBox.sendKeys(ReadData.fromExcel.getFormData(dataSet, "RequestClarificationComment"));
		requestClarificationSubmit.click();
		logReport("submitted the form seeking clarification");
		// waitHelper.waitForElement(formSubmittedText);
		waitHelper.WaitForElementVisibleWithPollingTime(formSubmittedText);
		String formSubmissionStatus = formSubmittedText.getText();
		waitHelper.waitForStalenessOfElement(headerAlert);
		waitHelper.waitForPageComponentLoad();
		return formSubmissionStatus;

	}

	public String provideClarification(String dataSet) {
		waitHelper.WaitForElementClickable(submitButton);
		submitButton.click();
		logReport("clicked on submit Clarification");

		waitHelper.WaitForModalPopUpAppear();

		waitHelper.WaitForElementClickable(commentBox);
		commentBox.sendKeys(ReadData.fromExcel.getFormData(dataSet, "SubmitJustificationComment"));
		logReport("Added the justification comment in the comment box");
		provideClarificationSubmitBtn.click();
		logReport("submitted the form providing the justification for the asked clarification");
		// waitHelper.waitForElement(formSubmittedText);
		waitHelper.WaitForElementVisibleWithPollingTime(formSubmittedText);
		String formSubmissionStatus = formSubmittedText.getText();
		waitHelper.waitForStalenessOfElement(headerAlert);
		waitHelper.waitForPageComponentLoad();
		return formSubmissionStatus;

	}

	public String approveRiskForm(String dataSet) {
		waitHelper.WaitForElementClickable(submitButton);
		submitButton.click();
		approveForm.click();
		logReport("clicked on Approve button");

		waitHelper.WaitForModalPopUpAppear();

		waitHelper.WaitForElementClickable(commentBox);
		commentBox.sendKeys(ReadData.fromExcel.getFormData(dataSet, "ApprovalComment"));
		logReport("Added the approval comment in the comment box");

		approveSubmitBtn.click();
		logReport("Approved the Risk Form");
		// waitHelper.waitForElement(formSubmittedText);
		waitHelper.WaitForElementVisibleWithPollingTime(formSubmittedText);
		String formSubmissionStatus = formSubmittedText.getText();
		waitHelper.waitForStalenessOfElement(headerAlert);
		waitHelper.waitForPageComponentLoad();
		return formSubmissionStatus;

	}

	public String cancelRiskForm(String dataSet) {
		waitHelper.WaitForElementClickable(submitButton);
		submitButton.click();
		cancelForm.click();
		logReport("clicked on cancel button to Cancel the Risk Form");

		waitHelper.WaitForModalPopUpAppear();

		waitHelper.WaitForElementClickable(commentBox);
		commentBox.sendKeys(ReadData.fromExcel.getFormData(dataSet, "CancelComment"));
		logReport("Added the comments for Form cancellation");

		cancelFormSubmit.click();
		logReport("Cancelled the Form and submitted");
		waitHelper.WaitForElementVisibleWithPollingTime(formSubmittedText);
		String formSubmissionStatus = formSubmittedText.getText();
		waitHelper.waitForStalenessOfElement(headerAlert);
		waitHelper.waitForPageComponentLoad();
		return formSubmissionStatus;
	}

	public void editApprovedRiskForm(String dataSet) {
		editButton.click();
		logReport("clicked on edit button to edit the approved Risk Form");
		waitHelper.waitForPageLoad();

		waitHelper.WaitForElementVisibleWithPollingTime(oRTaxonomyDes);
		waitHelper.WaitForElementClickable(oRTaxonomyDes);
		oRTaxonomyDes.clear();

		oRTaxonomyDes.sendKeys(ReadData.fromExcel.getFormData(dataSet, "ORTaxonomyDescription"));
		logReport("edited the 'ORTaxonomy Description' of the Risk Form");

		dateValidUntil.clear();
		action.click(dateValidUntil).sendKeys(ReadData.fromExcel.getFormData(dataSet, "DateValidUntil"), Keys.ENTER)
				.build().perform();
		logReport("edited the 'dateValidUntil' of the Risk Form");
		waitHelper.WaitForElementVisibleWithPollingTime(sendForApprovalButton);
	}

	public void editToSetPastDate(String dataSet) {
		waitHelper.WaitForElementClickable(editButton);
		editButton.click();
		logReport("clicked on edit button to change the 'valid until date' of the approved Risk Form");
		waitHelper.waitForPageLoad();

		waitHelper.WaitForElementVisibleWithPollingTime(dateValidFrom);
		waitHelper.WaitForElementClickable(dateValidFrom);
		dateValidFrom.clear();
		logReport("removed the value from 'dateValidFrom' field");

		dateValidUntil.clear();
		// String previousMonthDate =
		// DateTimeHelper.getPreviousMonthDateInFormat("dd-MMM-yyyy");
		action.click(dateValidUntil).sendKeys(ReadData.fromExcel.getFormData(dataSet, "PastValidUntil"), Keys.ENTER)
				.build().perform();
		logReport("changed the 'dateValidUntil' to the past date");
		waitHelper.WaitForElementVisibleWithPollingTime(sendForApprovalButton);
	}

	public void editToReactivateForm() {
		editButton.click();
		logReport("clicked on edit button to change the 'valid until date' of the expired Risk Form");
		waitHelper.waitForPageLoad();

		waitHelper.WaitForElementVisibleWithPollingTime(dateValidFrom);
		waitHelper.WaitForElementClickable(dateValidFrom);
		dateValidFrom.clear();

		dateValidUntil.clear();
		logReport("removed the values from 'valid from' and 'valid until' fields");
		waitHelper.WaitForElementVisibleWithPollingTime(sendForApprovalButton);
	}

	public boolean isHeaderLevelDisplayed() {
		return headerLevel.isDisplayed();
	}

	public String getHeaderLevelValue() {
		return headerLevelValue.getText();
	}

	public boolean isHeaderStatusDisplayed() {
		return headerStatus.isDisplayed();
	}

	public String getHeaderStatusValue() {
		return headerStatusValue.getText();
	}

	public boolean isFullScreenIconDisplayed() {
		return fullScreenIcon.isDisplayed();
	}

	public boolean isShowHelpIconDisplayed() {
		return showHelpIcon.isDisplayed();
	}

	public boolean isSaveButtonDisplayed() {
		return saveButton.isDisplayed();
	}

	public boolean isSendForApprovalButtonDisplayed() {
		return sendForApprovalButton.isDisplayed();
	}

	public boolean isCloseButtonDisplayed() {
		return closeButton.isDisplayed();
	}

	public boolean isRiskFormSectionDisplayed(String sectionName) {
		boolean flag = false;
		List<WebElement> elements = riskFormSections;
		for (WebElement element : elements) {
			if (element.getText().toLowerCase().equalsIgnoreCase(sectionName)) {
				flag = true;
				logReport("'" + sectionName + "'" + " section is displayed in the Risk Form");
				break;
			}
		}
		return flag;
	}

	public boolean isRelationshipLinkDisplayed(String relationshipLinkName) {
		boolean flag = false;
		List<WebElement> elements = allRelationships;
		for (WebElement element : elements) {
			if (element.getText().toLowerCase().equalsIgnoreCase(relationshipLinkName)) {
				flag = true;
				logReport("'" + relationshipLinkName + "'"
						+ " relationship Link is displayed in the Relationship section of the Risk Form");
				break;
			}
		}
		return flag;
	}

	public String getAdditionalDetailFormStatus() {
		waitHelper.waitForPageLoad();
		waitHelper.WaitForElementVisibleWithPollingTime(additionalDetailsStatus);
		jsHelper.scrollIntoView(additionalDetailsStatus);
		return additionalDetailsStatus.getText();
	}

	public String getCreatedOnDate() {
		jsHelper.scrollIntoView(createdOn);
		return createdOn.getText();
	}

	public String getCreatedBy() {
		jsHelper.scrollIntoView(createdBy);
		return createdBy.getText();
	}

	public String getModifiedOnDate() {
		jsHelper.scrollIntoView(modifiedOn);
		return modifiedOn.getText();
	}

	public String getModifiedby() {
		jsHelper.scrollIntoView(modifiedBy);
		return modifiedBy.getText();
	}

	public boolean isAttachFileDisplayed() {
		return attachFiles.isDisplayed();
	}


}
