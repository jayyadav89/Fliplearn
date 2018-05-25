package testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.FeeTemplate;
import pageModules.LoginPage;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;

public class FeeTemplateTestCases extends DriverSession {
	FeeTemplate feetemplate;
	WebDriverWait wait;
	LoginPage login;
	GenericFunctions generic;
	
	@BeforeMethod
	public void OpenFliplearn(){
		wait=new WebDriverWait(driver, 60);
		feetemplate = new FeeTemplate(driver);
		login = new LoginPage(driver);
		generic = new GenericFunctions(driver);
		driver.get(BASE_URL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.Login_link));
		login.ClickOnLogin_link();
	}
	
	/************************************
	 * Test Case Name : To verify Template is getting download or not
	 * @author : Rohit Kumar
	 * @throws InterruptedException 
	 *******************************/
	@Test
	public void FTMP_01() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		feetemplate.clickOnFeelink();
		feetemplate.checkFeeVersionPresent();		
		feetemplate.selectStudentID("Enrollment Number");
		feetemplate.clickOnSaveAndproceedBtn1();
		feetemplate.selectFeeComponent("Tuition Fee");
		feetemplate.clickOnSaveAndproceedBtn2();
		feetemplate.clickOnDownloadDataSheet();
		Assert.assertTrue(feetemplate.isfilenameSameInDownloadFolder(), "File not downloaded");
		
	}

}
