package testcases;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.LoginPage;
import pageModules.ReportCard;
import utils.Xls_Reader;

public class ReportCardTestCases extends DriverSession{
	Xls_Reader xls;
	GenericFunctions generic;
	WallActions wall;
	WebDriverWait wait;
	LoginPage login;
	ReportCard reportCard;
	String Sheetname="ReportCard",expected, ExpectedMsg="ExpectedMsg";
	@BeforeMethod
	public void OpenFliplearn(){
		wait=new WebDriverWait(driver, 40);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		wall = new WallActions(driver);
		login=new LoginPage(driver);
		reportCard=new ReportCard(driver);
		driver.get(BASE_URL);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.Login_link));
		login.ClickOnLogin_link();

	}

	/************************************
	 * Test Case Name : To verify that Manage Student
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=1)
	public void RCT_001() throws Throwable{

		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		generic.GoToSleep(5000);
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnManageStudent_Btn();
		reportCard.clickOnStudentDataBse();
		expected=xls.getCellData(Sheetname, ExpectedMsg, 2);
		String actual = reportCard.actual_ManageStudent();
		Assert.assertEquals(actual,expected);

	}
	/************************************
	 * Test Case Name : To verify file download and Upload of Subject mapping 
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=2)
	public void RCT_002() throws Throwable{

		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnsubjectMapping();
		reportCard.clickOnselectionSubjectMapping_xcelFile();
		expected=xls.getCellData(Sheetname,ExpectedMsg, 2);
		String actual = reportCard.actualResult_SubjectMapping();
		Assert.assertEquals(actual,expected);
		//reportCard.actualResult_SubjectMapping();
	}
	/************************************
	 * Test Case Name : To verify Marksheet link without selecting your term 
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=3)
	public void RCT_003() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnMarksheet_Btn();
		reportCard.download_marksheet_Btn();
		expected=xls.getCellData(Sheetname, ExpectedMsg, 3);
		String actual = reportCard.ActualerrorMessageAppear_Text();
		Assert.assertEquals(actual,expected);
		//reportCard.ActualerrorMessageAppear_Text();
	}
	/************************************
	 * Test Case Name : To verify Marksheet with selecting your term
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=4)
	public void RCT_004() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnMarksheet_Btn();
		reportCard.selectYourTerm_Marksheet("Term 1");
		reportCard.selectClass_Marksheet("Class 1");
		reportCard.selectSection_type_Report("A");
		reportCard.uploadmarksheet_Btn();
		expected=xls.getCellData(Sheetname, ExpectedMsg, 5);
		String actual = reportCard.actualVerificationResult_Marksheet();
		Assert.assertEquals(actual,expected);
		//reportCard.actualVerificationResult_Marksheet();


	}
	/************************************
	 * Test Case Name : To verif Report link without selecting your term
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/

	@Test(priority=5)

	public void RCT_005() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnReport_Lnk();
		reportCard.clickOnExpert_Btn();
		expected=xls.getCellData(Sheetname, ExpectedMsg, 3);
		String actual=reportCard.ActualerrorMessageAppear_Text();
		Assert.assertEquals(actual,expected);
		reportCard.ActualerrorMessageAppear_Text();
	}
	/************************************
	 * Test Case Name : To verify Report link with selecting your term
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=6)
	public void RCT_006() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnReport_Lnk();
		reportCard.selectTerm1_DropDown_Report("Term 1");
		reportCard.selectType_Class_Report("Class 1");
		reportCard.selectSection_type_Report("A");
		reportCard.clickOnExpert_Btn();
		expected=xls.getCellData(Sheetname, ExpectedMsg, 6);
		String actual = reportCard.actualVerification_ReportCard();
		Assert.assertEquals(actual,expected);
		//reportCard.actualVerification_ReportCard();

		//reportCard.clickOnPdf_Btn();
		//reportCard.clickOnNotify_Btn();
	}
	/************************************
	 * Test Case Name : To verify Report link without selecting your term by clicking pdf button
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=7)
	public void RCT_007() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnReport_Lnk();	
		reportCard.clickOnPdf_Btn();
		reportCard.ActualerrorMessageAppear_Text();
		expected=xls.getCellData(Sheetname, ExpectedMsg, 3);
		String actual=reportCard.ActualerrorMessageAppear_Text();
		Assert.assertEquals(actual,expected);

		//reportCard.ActualerrorMessageAppear_Text();

	}
	/************************************
	 * Test Case Name : To verify Report link witt selecting your term by clicking pdf button 
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=8)
	public void RCT_008() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		//reportCard.selectAdminAndProceed_Btn();
		
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnReport_Lnk();
		reportCard.selectTerm1_DropDown_Report("Term 1");
		reportCard.selectType_Class_Report("Class 1");
		reportCard.selectSection_type_Report("A");
		reportCard.clickOnPdf_Btn();
		String actual = reportCard.pdfGenerationText_ReportCard();
		//System.out.println(actual);
		//System.out.println("Your request to generate PDF file has been submitted and it will be processed after sometime.");
		expected=xls.getCellData(Sheetname, ExpectedMsg, 7);
		Assert.assertEquals(actual.trim(),expected);
		reportCard.clickOnCloseBtn_ReportCard();


	}
	/************************************
	 * Test Case Name: To verify Report link without selecting your term by clicking Notify button
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=9)
	public void RCT_009() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnReport_Lnk();
		reportCard.clickOnNotify_Btn();
		reportCard.ActualerrorMessageAppear_Text();
		expected=xls.getCellData(Sheetname, ExpectedMsg, 3);
		String actual=reportCard.ActualerrorMessageAppear_Text();
		Assert.assertEquals(actual,expected);
		
	}
	/************************************
	 * Test Case Name : enter text message by clicking Notify button
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test(priority=10)
	
	public void RCT_010() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnReport_Lnk();
		reportCard.selectTerm1_DropDown_Report("Term 1");
		reportCard.selectType_Class_Report("Class 1");
		reportCard.selectSection_type_Report("A");
		reportCard.clickOnNotify_Btn();
		reportCard.enterSpaceTextMessage_NotifyPage();
		String expected = xls.getCellData(Sheetname, ExpectedMsg, 8);
		String actual=reportCard.errorMessageOnNoitfyPage_ReportLink();
		Assert.assertEquals(actual, expected);
}
@Test(priority=11)
	
	public void RCT_011() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		reportCard.clickOnManage_Lnk();
		reportCard.clickOnReport_Lnk();
		reportCard.selectTerm1_DropDown_Report("Term 1");
		reportCard.selectType_Class_Report("Class 1");
		reportCard.selectSection_type_Report("A");
		reportCard.clickOnNotify_Btn();
		reportCard.enterTextMessage_NotifyPage();
	}
}

