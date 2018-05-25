package testcases;

import pageModules.Announcement;
import pageModules.BookStore;
import pageModules.BulkUpload;
import pageModules.UserRegistraionPage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;

public class BulkUploadTestCase extends DriverSession{
	WebDriverWait wait;
	BulkUpload bulkupload;
	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	Announcement announce;

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		bulkupload = new BulkUpload(driver);
		driver.get(BASE_URL);
		Thread.sleep(2000);
		driver.navigate().refresh();
		xls=new Xls_Reader();
		login=new LoginPage(driver);
		login.ClickOnLogin_link();
	}

	public void Login() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.GoToSleep(3000);
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			
		}
	}
}

