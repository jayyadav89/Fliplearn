package testcases;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.Announcement;
import pageModules.Gallery;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;

public class GalleryTestCases extends DriverSession{


	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	WebDriverWait wait;
	Gallery gallery;
	WallActions wall;
	LoadEnvProperty envpro = new LoadEnvProperty();
	Announcement announce=new Announcement(driver);
	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		wait=new WebDriverWait(driver, 60);
		driver.get(BASE_URL);
		wall = new WallActions(driver);
		generic=new GenericFunctions(driver);
		login=new LoginPage(driver);
		xls=new Xls_Reader();
		gallery=new Gallery(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.Login_link));
		generic.waitPageGotLoad();
		login.ClickOnLogin_link();
	}

	@Test(priority=1)
	public void GL_001() throws Throwable{
		String role="Teacher",Sheetname="WallPage";;
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Album Creater name not matched");
		}
		if(gallery.isImageNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing or Image name not matched");
		}
		WallActions.Logout();
	}

	@Test(priority=2)
	public void GL_002() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			gallery.clickOnEditAlbum();
			generic.GoToSleep(2000);
			gallery.clickOnDeleteAlbum();
			gallery.ConfirmDeletePop();
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
			Assert.assertTrue(false,"Created Album not displayed");
		}
		WallActions.Logout();
	}

	@Test(priority=3)
	public void GL_003() throws Throwable{
		String timeStamp="";
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			gallery.clickOnEditAlbum();
			generic.GoToSleep(2000);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			gallery.fillTitle("Qa Web updated_"+timeStamp);
			generic.GoToSleep(2000);
			gallery.clickOnAlbumUpdateButton();
			Thread.sleep(2000);
			if(gallery.isTitleDisplayed("Qa Web updated_"+timeStamp)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false,"Edited album not displayed");
			}
		}else{
			Assert.assertTrue(false,"Created Album not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=4)
	public void GL_004() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.CreateAlbumWithMultipleAttachment();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Created album is not displaying");
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Album Creater name not matched with logged in user");
		}
		if(gallery.isFileNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing/attachement name not matched with uploaded attachment");
		}
		WallActions.Logout();
	}
	@Test(priority=5)
	public void GL_005() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Created album is not displaying");
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Album Creater name not matched with logged in user");
		}
		WallActions.Logout();
	}
	@Test(priority=6)
	public void GL_006() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			gallery.clickOnEditAlbum();
			generic.GoToSleep(2000);
			gallery.clickOnDeleteAlbum();
			gallery.ConfirmDeletePop();
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
					Assert.assertTrue(false,"Title is still displaying after deleting/delete has not been performed");
				}
			
			}
		}else{
			Assert.assertTrue(false,"Created Album not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=7)
	public void GL_007() throws Throwable{
		String timeStamp="";
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			gallery.clickOnEditAlbum();
			generic.GoToSleep(2000);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			gallery.fillTitle("Qa Web updated_"+timeStamp);
			Thread.sleep(2000);
			gallery.clickOnAlbumUpdateButton();
			Thread.sleep(2000);
			if(gallery.isTitleDisplayed("Qa Web updated_"+timeStamp)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false,"Edited album not displayed");
			}
		}else{
			Assert.assertTrue(false,"Created Album not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=8)
	public void GL_008() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.CreateAlbumWithMultipleAttachment();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Created album is not displaying");
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Album Creater name not matched with logged in user");
		}
		if(gallery.isFileNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing/attachement name not matched with uploaded attachment");
		}
		WallActions.Logout();
	}
	@Test(priority=9)
	public void GL_009() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		String Album_TeacherName = gallery.getUserName();
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
		if(Gallery.ClassName.split("-").length >2){
			 classname= Gallery.ClassName.split("-")[0]+"-"+Gallery.ClassName.split("-")[1];
		}else{
			 classname=Gallery.ClassName.split("-")[0];
		}
		if(Gallery.ClassName.split("-").length >2){
			 section= Gallery.ClassName.split("-")[2];
		}else{
			 section=Gallery.ClassName.split("-")[1];
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
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Created Title not displayed");
		}
		if(Album_TeacherName.equalsIgnoreCase(gallery.getUserName())){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Teacher Role name mismatched");
		}
		WallActions.Logout();
	}
	@Test(priority=10)
	public void GL_010() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Announcement.filTerDropdown_Lnk);
		driver.findElement(Announcement.filTerDropdown_Lnk).click();
		generic.clickOnLinkText("Album");
		Thread.sleep(2000);
		for(WebElement element : driver.findElements(Announcement.filterTypeHeader)){
			if(!(element.getText().equalsIgnoreCase("ALBUM"))){
				Assert.assertTrue(true);
			}
		}
		WallActions.Logout();
	}
	@Test(priority=11)
	public void GL_011() throws Throwable{
		String role="Teacher";
		String Title,title,desc,Sheetname="WallPage";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		for(int i=3;i<xls.getRowCount(Sheetname)+1;i++){
			gallery.clickOnAPostOptions();
			title=xls.getCellData(Sheetname, "GalleryTitle", i);
			desc=xls.getCellData(Sheetname, "GalleryDesc", i);
			Title = gallery.fillCreateAlbum_FormWithData(title, desc);
			Thread.sleep(2000);
			if(gallery.isTitleDisplayed(Title)){
				xls.setCellData(Sheetname, "Result_Gallery", i, "Pass");
				Assert.assertTrue(true);
			}else{
				xls.setCellData(Sheetname, "Result_Gallery", i, "Fail");
				Assert.assertTrue(false,"Title Not Matched");
			}
			hmap=generic.getMessagesDetailFromDB(Title);
			if(!(hmap.get("title").equalsIgnoreCase(Title))){
				Assert.assertTrue(false,"Title Not Matched from database");
			}
		}
	}
	@Test(priority=12)
	public void GL_012() throws Throwable{
		String role="Teacher";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.createAlbumFormToAllClasses();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
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
	public void GL_013() throws Throwable{
		String role="Teacher";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		GenericFunctions.WaitFor_visibility(driver, Gallery.Post_Lnk);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbumWithDifferentTab();
		Thread.sleep(2000);
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
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
