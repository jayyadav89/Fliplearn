package testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.B2C;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;

public class ContentAccess extends DriverSession {
	B2C b2c;
	LoginPage login;
	GenericFunctions generic;
	Xls_Reader xls;
	LoadEnvProperty envpro = new LoadEnvProperty();
	String sheetName = "B2CResult";

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		driver.get(BASE_URL);
		login = new LoginPage(driver);
		generic = new GenericFunctions(driver);
		xls = new Xls_Reader();
		b2c = new B2C(driver);
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
	}
	public void scrollDown(int k){
		try {
			Robot robot = new Robot();
			for(int i=0; i<k; i++) {
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			}
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}


	public void loginAndClickOnB2C(String role) throws InterruptedException{
		Thread.sleep(2000);
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		b2c.clickOnLearn();
		b2c.clickOnGuestImage();
	}

	@Test()
	public void nonPurchaseContents() throws InterruptedException{
		loginAndClickOnB2C("NonPurchase");
		b2c.clickOnSubject();
		Assert.assertTrue(b2c.isBuySubscription_Btn_Display(), "Buy Subscription button not displayed");
		String content_status=b2c.validateContent();
		if(content_status.contains("Fail")){
			Assert.assertTrue(false, content_status);
		}else{
			Assert.assertTrue(true);
		}
	}
	@Test()
	public void purchaseContents() throws InterruptedException{
		loginAndClickOnB2C("Purchase");
		b2c.clickOnSubject();
		Assert.assertFalse(b2c.isBuySubscription_Btn_Display(), "Buy Subscription button is displaying");
		b2c.clickOnFirstSubjectChapter();
		b2c.clickOnFirstResource();
		String content_status=b2c.validateContent();
		if(content_status.contains("Fail")){
			Assert.assertTrue(false, content_status);
		}else{
			Assert.assertTrue(true);
		}
	}


}
