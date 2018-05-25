package testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.LoginPage;
import pageModules.ReShuffleClass_Section;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;
import utils.Xls_Reader;
import helper.DriverSession;

public class ReShuffleClassTestCases extends DriverSession {
	Xls_Reader xls;
	GenericFunctions generic;
	WebDriverWait wait;
	WallActions wall;
	UtilityTest util;
	LoginPage login;
	ReShuffleClass_Section reshuffle;
	
	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{	
		wait=new WebDriverWait(driver, 40);
		xls=new Xls_Reader();
		wall = new WallActions(driver);
		generic=new GenericFunctions(driver);
		reshuffle = new ReShuffleClass_Section(driver);
		login=new LoginPage(driver);
		driver.get(BASE_URL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(login.Login_link));
		generic.waitPageGotLoad();
		login.ClickOnLogin_link();
	}
	
	@Test(priority=1)
	public void RSFL_001() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		Thread.sleep(3000);
		reshuffle.clickOnReshuffleLink();
		String Sheetname="ManageUser",studentClass, studentSection,reshuffleSection;
		int i;
		for(i=2;i<5;i++){
			studentClass=xls.getCellData(Sheetname, "StudentClass", i).trim();
			studentSection=xls.getCellData(Sheetname, "StudentSection", i).trim();
			reshuffleSection=xls.getCellData(Sheetname, "ReshuffleSection", i).trim();
			Thread.sleep(3000);
			reshuffle.SelectClassAndSection(studentClass, studentSection);
			String StudentName=reshuffle.getStudentname();
			String AdmissionNumber = reshuffle.getAdmissionNumber();
			reshuffle.selectReshfulledSection(reshuffleSection);
			reshuffle.clickOnSubmitButton();
			reshuffle.clickOnConfirmButton();
			Assert.assertTrue(reshuffle.isSuccessMsgMatchWithExpected(studentClass, studentSection), 
					"Success Message not matched");
			Thread.sleep(3000);
			reshuffle.clickOnGoButton();
		Assert.assertTrue(reshuffle.isStudentNameExistAfterReshuffle(StudentName), "student name matched");
		Assert.assertTrue(reshuffle.isStudentAdmissionExistAfterReshuffle(AdmissionNumber), "Admission number matched");
		Thread.sleep(1500);
		driver.navigate().refresh();
		Thread.sleep(2000);
		}
		
	}
	
	
}
