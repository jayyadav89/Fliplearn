package testcases;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.InviteUser1;
import pageModules.LoginPage;
import utils.Xls_Reader;

public class InviteUserTestCases1 extends DriverSession {

	Xls_Reader xls;
	GenericFunctions generic;
	WallActions wall;
	WebDriverWait wait;
	LoginPage login;
	InviteUser1 inviteUser1;

	@BeforeMethod
	public void OpenFliplearn(){
		wait=new WebDriverWait(driver, 40);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		wall = new WallActions(driver);
		login=new LoginPage(driver);
		inviteUser1=new InviteUser1(driver);
		driver.get(BASE_URL);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.Login_link));
		login.ClickOnLogin_link();
	}
	@Test(priority=1)
	public void checkValidationOf_Teacher() throws InterruptedException{

		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			generic.clickOnContinue_Btn();
		}
		inviteUser1.clickOnInvite_Btn();
		inviteUser1.clickOnTeacher_Btn();
		String Sheetname="InviteUser",blankMobileNumber,invalidNumber1,invalidNumber2,errorMsg1,errorMsg2,errorMsg3;
		for(int i=2;i<3;i++){
			blankMobileNumber=xls.getCellData(Sheetname, "blankNumber", i);
			invalidNumber1=xls.getCellData(Sheetname, "invalidNumber1", i);
			invalidNumber2=xls.getCellData(Sheetname, "invalidNumber2", i);
			errorMsg1=xls.getCellData(Sheetname, "errorMsg1", i);
			errorMsg2=xls.getCellData(Sheetname, "errorMsg2", i);
			errorMsg3=xls.getCellData(Sheetname, "errorMsg3", i);
			try {
				Assert.assertTrue(inviteUser1.isErrorMsgAppear(blankMobileNumber, errorMsg1),"blank mobile number error does not appear");
				xls.setCellData(Sheetname, "Result1", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result1", i, "Fail");
			}
			try {
				Assert.assertTrue(inviteUser1.isErrorMsgAppear(invalidNumber1, errorMsg2), "invalid mobile number does not appear");
				xls.setCellData(Sheetname, "Result2", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result2", i, "Fail");
			}
			try {
				Assert.assertTrue(inviteUser1.isErrorMsgAppear(invalidNumber2, errorMsg3), "invalid mobile number does not appear");	
				xls.setCellData(Sheetname, "Result3", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result3", i, "Fail");
			}
		}


	}
	@Test(priority=2)
	public void sendInviteCodeToUser() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		inviteUser1.clickOnInvite_Btn();
		inviteUser1.clickOnTeacher_Btn();
		String Sheetname="InviteUser",mobileNumber1,mobileNumber2,mobileNumber3,SuccessMsg;
		for(int i=2;i<3;i++){
			mobileNumber1=xls.getCellData(Sheetname, "MobileNumber1", i);
			mobileNumber2=xls.getCellData(Sheetname, "MobileNumber2", i);
			mobileNumber3=xls.getCellData(Sheetname, "MobileNumber3", i);
			SuccessMsg=xls.getCellData(Sheetname, "successMsg", i);
			inviteUser1.sendSingleUserInvitation(mobileNumber1);
			try {
				Assert.assertTrue(inviteUser1.isSuccessMsgAppear(SuccessMsg), "SuccessMsg does not matched");
				xls.setCellData(Sheetname, "ResultSingleUser", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result3", i, "Pass");	
			}
			inviteUser1.clickOnOkBtn();
			inviteUser1.clickOnInvite_Btn();
			inviteUser1.clickOnTeacher_Btn();
			inviteUser1.sendDoubleUserInvitation(mobileNumber1, mobileNumber2);
			try {
				Assert.assertTrue(inviteUser1.isSuccessMsgAppear(SuccessMsg), "SuccessMsg does not matched");
				xls.setCellData(Sheetname, "ResultDoubleUser", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result3", i, "Pass");	
			}
			inviteUser1.clickOnOkBtn();
			inviteUser1.clickOnInvite_Btn();
			inviteUser1.clickOnTeacher_Btn();
			inviteUser1.sendThreeUserInvitation(mobileNumber1, mobileNumber2, mobileNumber3);
			try {
				Assert.assertTrue(inviteUser1.isSuccessMsgAppear(SuccessMsg), "SuccessMsg does not matched");
				xls.setCellData(Sheetname, "ResultThreeUser", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result3", i, "Pass");	
			}
			inviteUser1.clickOnOkBtn();
			//inviteUser1.clickOnInvite_Btn();

		}
	}

	//For Admin

	@Test(priority=3)
	public void checkValidationOf_Admin() throws InterruptedException{


		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			generic.clickOnContinue_Btn();
		}
		inviteUser1.clickOnInvite_Btn();
		inviteUser1.clickOnAdmin_btn();
		String Sheetname="InviteUser",blankMobileNumber,invalidNumber1,invalidNumber2,errorMsg1,errorMsg2,errorMsg3;
		for(int i=2;i<3;i++){
			blankMobileNumber=xls.getCellData(Sheetname, "blankNumber", i);
			invalidNumber1=xls.getCellData(Sheetname, "invalidNumber1", i);
			invalidNumber2=xls.getCellData(Sheetname, "invalidNumber2", i);
			errorMsg1=xls.getCellData(Sheetname, "errorMsg1", i);
			errorMsg2=xls.getCellData(Sheetname, "errorMsg2", i);
			errorMsg3=xls.getCellData(Sheetname, "errorMsg3", i);
			try {
				Assert.assertTrue(inviteUser1.isErrorMsgAppear(blankMobileNumber, errorMsg1),"blank mobile number error does not appear");
				xls.setCellData(Sheetname, "Result1", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result1", i, "Fail");
			}
			try {
				Assert.assertTrue(inviteUser1.isErrorMsgAppear(invalidNumber1, errorMsg2), "invalid mobile number does not appear");
				xls.setCellData(Sheetname, "Result2", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result2", i, "Fail");
			}
			try {
				Assert.assertTrue(inviteUser1.isErrorMsgAppear(invalidNumber2, errorMsg3), "invalid mobile number does not appear");	
				xls.setCellData(Sheetname, "Result3", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result3", i, "Fail");
			}
		}
	}

	@Test(priority=4)
	public void sendInviteCodeToUserAdmin() throws InterruptedException{

		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		inviteUser1.clickOnInvite_Btn();
		inviteUser1.clickOnAdmin_btn();
		String Sheetname="InviteUser",mobileNumber1,mobileNumber2,mobileNumber3,SuccessMsg;
		for(int i=2;i<3;i++){
			mobileNumber1=xls.getCellData(Sheetname, "MobileNumber1", i);
			mobileNumber2=xls.getCellData(Sheetname, "MobileNumber2", i);
			mobileNumber3=xls.getCellData(Sheetname, "MobileNumber3", i);
			SuccessMsg=xls.getCellData(Sheetname, "successMsg", i);
			inviteUser1.sendSingleUserInvitation(mobileNumber1);
			try {
				Assert.assertTrue(inviteUser1.isSuccessMsgAppear(SuccessMsg), "SuccessMsg does not matched");
				xls.setCellData(Sheetname, "ResultSingleUser", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result3", i, "Pass");	
			}
			inviteUser1.clickOnOkBtn();
			inviteUser1.clickOnInvite_Btn();
			inviteUser1.clickOnAdmin_btn();
			inviteUser1.sendDoubleUserInvitation(mobileNumber1, mobileNumber2);
			try {
				Assert.assertTrue(inviteUser1.isSuccessMsgAppear(SuccessMsg), "SuccessMsg does not matched");
				xls.setCellData(Sheetname, "ResultDoubleUser", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result3", i, "Pass");	
			}
			inviteUser1.clickOnOkBtn();
			inviteUser1.clickOnInvite_Btn();
			inviteUser1.clickOnAdmin_btn();
			inviteUser1.sendThreeUserInvitation(mobileNumber1, mobileNumber2, mobileNumber3);
			try {
				Assert.assertTrue(inviteUser1.isSuccessMsgAppear(SuccessMsg), "SuccessMsg does not matched");
				xls.setCellData(Sheetname, "ResultThreeUser", i, "Pass");
			} catch (AssertionError e) {
				xls.setCellData(Sheetname, "Result3", i, "Pass");	
			}
			inviteUser1.clickOnOkBtn();
			//inviteUser1.clickOnInvite_Btn();

		}

	}

}