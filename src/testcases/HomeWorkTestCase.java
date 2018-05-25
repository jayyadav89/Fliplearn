package testcases;


import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.Announcement;
import pageModules.Gallery;
import pageModules.HomeWork;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;

public class HomeWorkTestCase extends DriverSession {
	HomeWork homework;
	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	WallActions wall;
	WebDriverWait wait;
	Announcement announce=new Announcement(driver);
	LoadEnvProperty envpro = new LoadEnvProperty();

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		wait=new WebDriverWait(driver, 60);
		driver.get(BASE_URL);
		wall = new WallActions(driver);
		login=new LoginPage(driver);
		homework=new HomeWork(driver);
		announce=new Announcement(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.Login_link));
		generic.waitPageGotLoad();
		login.ClickOnLogin_link();
	}
	@Test(priority=1)
	public void HW_001() throws Throwable {
		String role="Teacher",Sheetname="WallPage";;
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		Thread.sleep(2000);
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"HomeWork Creator name not matched");
		}
		WallActions.Logout();
	}
	@Test(priority=2)
	public void HW_002() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.fillCreateHomeWorkWithUploadFile();
		Thread.sleep(1500);
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title does not matched");
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"User name does not matched");
		}
		if(homework.isFileupNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"File uploaded by user is not matched");
		}
		WallActions.Logout();
	}
	@Test(priority=3)
	public void HW_003() throws InterruptedException, AWTException, IOException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		generic.GoToSleep(2000);
		if(homework.isTitleDisplayed(Title)){
			homework.clickOnEdit_Btn();
			generic.GoToSleep(2000);
			String TitleE=homework.UpdateTitle();
			generic.GoToSleep(2000);
			homework.clickOnUpdate_Btn();
			generic.GoToSleep(2000);
			if(homework.isTitleDisplayed(TitleE)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false,"Edited homework not displayed");
			}
		}else{
			Assert.assertTrue(false,"Created homework not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=4)
	public void HW_004() throws InterruptedException, AWTException, IOException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		generic.GoToSleep(3500);
		if (homework.isTitleDisplayed(Title)){
			homework.clickOnEdit_Btn();	
			generic.GoToSleep(3000);
			homework.clickOnDeleteHomeWork();
			generic.GoToSleep(1000);
			homework.ConfirmDeletePop();
			generic.GoToSleep(2000);
			if(!generic.isElementPresent(Title)){
				Assert.assertTrue(true);
			}else{
				int i=0;
				while((generic.isElementPresent(Title)) && i<10){
					driver.navigate().refresh();
					generic.GoToSleep(1000);
					i++;
				}
				if(!generic.isElementPresent(Title)){
					Assert.assertTrue(true);
				}else{
					Assert.assertTrue(false,"Title is still displaying after deleting/delete action has not been performed");
				}
			}
		}else{
			Assert.assertTrue(false,"Created homework not displayed");
		}
		generic.GoToSleep(1000);
		WallActions.Logout();
	}
	@Test(priority=5)
	public void HW_005() throws InterruptedException, AWTException, IOException {
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		generic.GoToSleep(1000);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Created homework is not displaying/title not matched");
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"homework Created user name not matched with logged in user");
		}
		WallActions.Logout();
	}
	@Test(priority=6)
	public void HW_006() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.fillCreateHomeWorkWithUploadFile();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not matched");
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"homework Created username not matched with logged in user");
		}
		if(homework.isFileupNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing");
		}
		WallActions.Logout();
	}
	@Test(priority=7)
	public void HW_007() throws InterruptedException, AWTException, IOException{
		Thread.sleep(5000);
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		generic.GoToSleep(3000);
		if(homework.isTitleDisplayed(Title)){
			homework.clickOnEdit_Btn();
			generic.GoToSleep(1000);
			String TitleE=homework.UpdateTitle();
			generic.GoToSleep(1000);
			homework.clickOnUpdate_Btn();
			generic.GoToSleep(2000);
			if(homework.isTitleDisplayed(TitleE)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false,"Edited homework not displayed");
			}
		}else{
			Assert.assertTrue(false,"Created homework not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=8)
	public void HW_008() throws InterruptedException, AWTException, IOException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		if (homework.isTitleDisplayed(Title)){
			homework.clickOnEdit_Btn();	
			generic.GoToSleep(3000);
			homework.clickOnDeleteHomeWork();
			generic.GoToSleep(1000);
			homework.ConfirmDeletePop();
			generic.GoToSleep(2000);
			if(!generic.isElementPresent(Title)){
				Assert.assertTrue(true);
			}else{
				int i=0;
				while((generic.isElementPresent(Title)) && i<10){
					driver.navigate().refresh();
					generic.GoToSleep(1000);
					i++;
				}
				if(!generic.isElementPresent(Title)){
					Assert.assertTrue(true);
				}else{
					Assert.assertTrue(false,"created homework is still displaying after deleting/delete action has not been performed");
				}
			}
		}else{
			Assert.assertTrue(false,"Created Album not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=9)
	public void HW_009() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		String HomeWork_TeacherName = homework.getUserName();
		WallActions.Logout();
		generic.GoToSleep(2000);
		driver.navigate().refresh();
		login.ClickOnLogin_link();
		login.loginInToApplication(UtilityTest.getUserIDByRole("Student"),UtilityTest.getUserPasswordByRole("Student"));
		generic.GoToSleep(3000);
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(3000);
		WallActions.clickOnMyProfile();
		Thread.sleep(2000);
		String classname="",section="";
		if(HomeWork.ClassName.split("-").length >2){
			 classname= HomeWork.ClassName.split("-")[0]+"-"+HomeWork.ClassName.split("-")[1];
		}else{
			 classname=HomeWork.ClassName.split("-")[0];
		}
		if(HomeWork.ClassName.split("-").length >2){
			 section= HomeWork.ClassName.split("-")[2];
		}else{
			 section=HomeWork.ClassName.split("-")[1];
		}
		GenericFunctions.WaitFor_visibility(driver, By.xpath("(//img[@class='img-responsive'])[3]"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//img[@class='img-responsive'])[3]")).click();
		Select Sel_class=new Select(driver.findElement(By.xpath("(//select[@id='radiusSelect'])[1]")));
		Sel_class.selectByVisibleText(classname);
		Select Sel_Section=new Select(driver.findElement(By.xpath("(//select[@id='radiusSelect'])[2]")));
		Sel_Section.selectByVisibleText(section);
		driver.findElement(By.xpath("(//button[@class='btn btn-primary padding-10-60 m-t-15 m-b-15'])[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='/home/wall']")).click();
		if(homework.isTitleDisplayed(Title)){
			
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Created Title not displayed");
		}
		if(HomeWork_TeacherName.equalsIgnoreCase(homework.getUserName())){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Teacher Role name mismatched");
		}
		WallActions.Logout();
	}
	@Test(priority=10)
	public void HW_010() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.filTerDropdown_Lnk);
		driver.findElement(Announcement.filTerDropdown_Lnk).click();
		generic.clickOnLinkText("Homework");
		Thread.sleep(3000);
		for(WebElement element : driver.findElements(Announcement.filterTypeHeader)){
			if(!(element.getText().equalsIgnoreCase("HOMEWORK"))){
				Assert.assertTrue(true);
			}
		}
		WallActions.Logout();
	}
	@Test(priority=11)
	public void HW_011() throws Throwable{
		String role="Teacher";
		String Title,title,desc,Sheetname="WallPage";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		for(int i=2;i<xls.getRowCount(Sheetname)+1;i++){
			homework.clickOnAPostOptions();
			title=xls.getCellData(Sheetname, "HomeworkTitle", i);
			desc=xls.getCellData(Sheetname, "HomeworkDesc", i);
			Title = homework.fillCreateAnnouncementFormWithData(title, desc);
			if(homework.isTitleDisplayed(Title)){
				xls.setCellData(Sheetname, "Result_Home", i, "Pass");
				Assert.assertTrue(true);
			}else{
				xls.setCellData(Sheetname, "Result_Home", i, "Fail");
				Assert.assertTrue(false,"Title Not Matched");
			}
			hmap=generic.getMessagesDetailFromDB(Title);
			if(!(hmap.get("title").equalsIgnoreCase(Title))){
				Assert.assertTrue(false,"Title Not Matched from database");
			}
		}
	}

	@Test(priority=12)
	public void HW_012() throws Throwable{
		String role="Teacher";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.CreateHomeWorkDetailsToAllClasses();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Role name not matched");
		}
		hmap=generic.getMessagesDetailFromDB(Title);
		if(!(hmap.get("title").equalsIgnoreCase(Title))){
			Assert.assertTrue(false,"Title Not Matched from database");
		}
	}
	
	@Test(priority=13)
	public void HW_013() throws Throwable{
		String role="Teacher";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, HomeWork.Post_Lnk);
		homework.clickOnAPostOptions();
		String Title = homework.fillCreateAnnouncementFormWithDifferentTab();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Role name not matched");
		}
		hmap=generic.getMessagesDetailFromDB(Title);
		if(!(hmap.get("title").equalsIgnoreCase(Title))){
			Assert.assertTrue(false,"Title Not Matched from database");
		}
	}

}






