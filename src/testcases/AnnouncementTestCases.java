package testcases;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.Announcement;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;
public class AnnouncementTestCases extends DriverSession{
	Announcement announce;
	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	WallActions wall;
	WebDriverWait wait;
	UtilityTest util;
	LoadEnvProperty envpro = new LoadEnvProperty();

	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		
		wait=new WebDriverWait(driver, 60);
		login=new LoginPage(driver);
		wall = new WallActions(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		announce=new Announcement(driver);
		driver.get(BASE_URL);
		generic.waitPageGotLoad();
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.Login_link));
		
		login.ClickOnLogin_link();
	}

	@Test(priority=1)
	public void ANNC_001() throws Throwable{
		String role="Teacher",Sheetname="WallPage";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		Thread.sleep(2000);
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Announcement Creator name not matched");
		}
		WallActions.Logout();
	}
	@Test(priority=2)
	public void ANNC_002() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementWithUploadFile();
		Thread.sleep(2000);
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Creater Role not matched");
		}
		if(announce.isFileNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing");
		}
		WallActions.Logout();
	}
	@Test(priority=3)
	public void ANNC_003() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		Thread.sleep(2000);
		if(announce.isTitleDisplayed(Title)){
			announce.clickOnEditAnnuncement();
			generic.GoToSleep(3000);
			announce.clickOnDeleteAnnouncement();
			announce.ConfirmDeletePop();
			Thread.sleep(3000);
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
					Assert.assertTrue(false,"Title is still displaying after deleting");
				}
			}
		}else{
			Assert.assertTrue(false,"Created Announcement not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=4)
	public void ANNC_004() throws Throwable{
		String timeStamp="";
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		Thread.sleep(2000);
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		Thread.sleep(2000);
		if(announce.isTitleDisplayed(Title)){
			announce.clickOnEditAnnuncement();
			generic.GoToSleep(2000);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			announce.fillTitle("Qa Web updated_"+timeStamp);
			generic.GoToSleep(2000);
			announce.clickOnUpdateButton();
			Thread.sleep(2000);
			if(announce.isTitleDisplayed("Qa Web updated_"+timeStamp)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false,"Edited announcement not displayed");
			}
		}else{
			Assert.assertTrue(false,"Created Announcement not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=5)
	public void ANNC_005() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		Thread.sleep(2000);
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		Thread.sleep(2000);
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Role name not matched");
		}
		WallActions.Logout();
	}
	@Test(priority=6)
	public void ANNC_006() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);;
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementWithUploadFile();
		Thread.sleep(3000);
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Creater Role not matched");
		}
		if(announce.isFileNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing");
		}
		WallActions.Logout();
	}
	@Test(priority=7)
	public void ANNC_007() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		if(announce.isTitleDisplayed(Title)){
			announce.clickOnEditAnnuncement();
			generic.GoToSleep(3000);
			announce.clickOnDeleteAnnouncement();
			announce.ConfirmDeletePop();
			Thread.sleep(2000);
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
					Assert.assertTrue(false,"Title is still displaying after deleting");
				}
			}
		}else{
			Assert.assertTrue(false,"Creted Announcement not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=8)
	public void ANNC_008() throws Throwable{
		String timeStamp="";
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		Thread.sleep(2000);
		if(announce.isTitleDisplayed(Title)){
			announce.clickOnEditAnnuncement();
			generic.GoToSleep(2000);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			announce.fillTitle("Qa Web updated_"+timeStamp);;
			announce.clickOnUpdateButton();
			Thread.sleep(2000);
			if(announce.isTitleDisplayed("Qa Web updated_"+timeStamp)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false,"Edited announcement not displayed");
			}
		}else{
			Assert.assertTrue(false,"Created Announcement not displayed");
		}
	}
	@Test(priority=9)
	public void ANNC_009() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		String Announcement_TeacherName = announce.getUserName();
		WallActions.Logout();
		generic.GoToSleep(2000);
		driver.navigate().refresh();
		login.ClickOnLogin_link();
		login.loginInToApplication(UtilityTest.getUserIDByRole("Student"),UtilityTest.getUserPasswordByRole("Student"));
		generic.GoToSleep(3000);
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Thread.sleep(2000);
		WallActions.clickOnMyProfile();
		Thread.sleep(2000);
		String classname="",section="";
		if(Announcement.ClassName.split("-").length >2){
			 classname= Announcement.ClassName.split("-")[0]+"-"+Announcement.ClassName.split("-")[1];
		}else{
			 classname=Announcement.ClassName.split("-")[0];
		}
		if(Announcement.ClassName.split("-").length >2){
			 section= Announcement.ClassName.split("-")[2];
		}else{
			 section=Announcement.ClassName.split("-")[1];
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
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Created Title not displayed");
		}
		if(Announcement_TeacherName.equalsIgnoreCase(announce.getUserName())){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Teacher Role name mismatched");
		}
		WallActions.Logout();
	}
	@Test(priority=10)
	public void ANNC_010() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Thread.sleep(4000);
		GenericFunctions.WaitFor_visibility(driver, Announcement.filTerDropdown_Lnk);
		driver.findElement(Announcement.filTerDropdown_Lnk).click();
		generic.clickOnLinkText("Announcement");
		Thread.sleep(3000);
		for(WebElement element : driver.findElements(Announcement.filterTypeHeader)){
			if(!(element.getText().equalsIgnoreCase("ANNOUNCEMENT"))){
				Assert.assertTrue(true);
			}
		}	
		WallActions.Logout();
	}
	@Test(priority=11)
	public void ANNC_011() throws Throwable{
		String role="Teacher";
		String Title,title,desc,Sheetname="WallPage";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		for(int i=3;i<xls.getRowCount(Sheetname)+1;i++){
			GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
			announce.clickOnAPostOptions();
			title=xls.getCellData(Sheetname, "AnnouncementTitle", i);
			desc=xls.getCellData(Sheetname, "AnnounceDesc", i);
			Title = announce.fillCreateAnnouncementFormWithData(title, desc);
			Thread.sleep(2000);
			if(announce.isTitleDisplayed(Title)){
				xls.setCellData(Sheetname, "Result_Announce", i, "Pass");
				Assert.assertTrue(true);
			}else{
				xls.setCellData(Sheetname, "Result_Announce", i, "Fail");
				Assert.assertTrue(false,"Title Not Matched");
			}
			hmap=generic.getMessagesDetailFromDB(Title);
			if(!(hmap.get("title").equalsIgnoreCase(Title))){
				Assert.assertTrue(false,"Title Not Matched from database");
			}
		}
	}
	@Test(priority=12)
	public void ANNC_012() throws Throwable{
		String role="Teacher";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.CreateAnnouncementFormToAllClasses();
		Thread.sleep(2000);
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
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
	public void ANNC_013() throws Throwable{
		String role="Teacher";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementFormWithDifferentTab();
		Thread.sleep(2000);
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
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
