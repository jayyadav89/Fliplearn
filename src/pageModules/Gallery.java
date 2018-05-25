package pageModules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import helper.GenericFunctions;
import helper.WallActions;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Gallery {
	
	WebDriver driver;

	GenericFunctions generic;
	public static String ClassName="";
	public Gallery(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By selectTeacher=By.xpath("//p[contains(text(),'teacher')]");
	public static By continueButton=By.xpath("//button[@class='btn btn-primary paddingLR marginR20 ng-scope']");
	public static By updateGallery_Btn=By.id("btn-next");
	public static By clickOnDeletePopScreenGeneric=By.xpath(".//*[@id='delete']");
	public static By Post_Lnk=By.xpath("//div[@class='dropdown']/button[@class='btn btn-primary']");
	public static By Album_Lnk=By.xpath("//button[@id='album']");
	public static By Delete_Btn=By.xpath("//button[contains(@class,'delete-post')]");
	public static By Title_Txt=By.xpath("(//input[@id='title'])[2]");
	public static By Title_Txt_Alternate=By.xpath("(//input[@id='title'])[1]");
	public static By ShareWith_DD=By.xpath("//span[@class='btn-contact-gallery']");
	public static By SendSMS_Yes=By.xpath("(//div[@class='radiobutton']/label)[3]");
	public static By SendSMS_No=By.xpath("(//div[@class='radiobutton']/label)[4]");
	public static By Description_Txt=By.xpath("(//textarea[@id='textboxIssuereport'])[2]");
	public static By Upload_Attachment=By.xpath("//input[contains(@src,'/imagedrag.png')]");
	public static By Group_Selection_Chk=By.xpath("//input[@name='group_code']");
	public static By SaveCloseGroup_Btn=By.xpath("//button[@class='btn btn-primary btn-block']");
	public static By Create_album_Btn=By.xpath("(//button[@id='btn-next'])[2]");
	public static By profileHover=By.xpath("//img[@class='profilePic ng-scope']");
	public static By loggedInUser=By.xpath("//h4[@class='margin-bottom-4 ng-binding']");
	public static By AlbumUser=By.xpath("//small[@class='pull-left text-color paddinglr0 ng-binding']");
	public static By uploadFileOnWall=By.xpath("//li[@class='ng-binding ng-scope']");
	public static By schoolStaff_lnk=By.xpath("//a[@data-toggle='tab']");
	public static By allTeacherGroup_chkbox=By.xpath("//span[@class='custom-checkbox ng-scope']/input[contains(@class,'group_code')]");
	public static By createAlbumError_msg=By.xpath("//div[@class='errors error-username ng-scope']");
	public static By getTitle=By.xpath("//h4[@class='m-t-20 ng-binding']");
	public static By edit_Btn=By.xpath("//div[contains(@class,'edit-button ng-scope')]/a/span");
	public static By deleteAttachment_icon1= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[1]");
	public static By deleteAttachment_icon2= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[2]");
	public static By deleteAttachment_icon3= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[3]");
	public static By deleteAttachment_icon4= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[4]");
	
	public void clickOnAPostOptions() throws InterruptedException{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, Post_Lnk);
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(Post_Lnk)).perform();
		builder.moveToElement(driver.findElement(Album_Lnk)).click().perform();
	}
	
	public void selectTeacherProfile()
	{
		driver.findElement(selectTeacher).click();
		driver.findElement(continueButton).click();
	}

	public void fillTitle(String value){
		try {
			if(value.trim().length()==0){
				driver.findElement(Title_Txt).clear();
				return;
			}
			driver.findElement(Title_Txt).clear();
			driver.findElement(Title_Txt).sendKeys(value);
		} catch (Exception e) {
			if(value.trim().length()==0){
				driver.findElement(Title_Txt_Alternate).clear();
				return;
			}
			driver.findElement(Title_Txt_Alternate).clear();
			driver.findElement(Title_Txt_Alternate).sendKeys(value);
		}

	}

	public void clickOnSendSMSNO(){
		driver.findElement(SendSMS_No).click();
	}

	public void fillDescription(String value){
		if(value.trim().length()==0){
			driver.findElement(Description_Txt).clear();
			return;
		}
		driver.findElement(Description_Txt).sendKeys(value);
	}


	public void clickOnCreateButton() throws InterruptedException{
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(1000);
		driver.findElement(Create_album_Btn).click();
	}

	String fileName ="";
	public void uploadSingleImage() throws Throwable{
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
		generic.GoToSleep(3000);
		WebElement element=driver.findElements(Upload_Attachment).get(1);
		String filePath=System.getProperty("user.dir") + "/dummyFiles/satya.png";
		String NewFilepath=filePath.replace( "/","\\");
		element.click();
		Thread.sleep(2000);
		generic.UploadFile(NewFilepath);
		fileName = FilenameUtils.getBaseName(NewFilepath);
	}
	String fileName1 ="",fileName2 ="",fileName3 ="",fileName4 ="";
	String FileExtension1 = "", FileExtension2 = "", FileExtension3 = "", FileExtension4 = "";
	public void uploadMultipleImage() throws Throwable{
		String NewFilepath1,NewFilepath2,NewFilepath3,NewFilepath4="";
		String filePath1=System.getProperty("user.dir") + "/dummyFiles/download.jpg";
		String filePath2=System.getProperty("user.dir") + "/dummyFiles/images.jpg";
		String filePath3=System.getProperty("user.dir") + "/dummyFiles/Test1.JPG";
		String filePath4=System.getProperty("user.dir") + "/dummyFiles/Test2.jpg";
		String OSName = generic.getCurrentEnvironment();
		System.out.println(OSName);
		if(OSName.contains("Windows")){
			 NewFilepath1=filePath1.replace( "/","\\");
			 NewFilepath2=filePath2.replace( "/","\\");
			 NewFilepath3=filePath3.replace( "/","\\");
			 NewFilepath4=filePath4.replace( "/","\\");
		}else{
			 NewFilepath1=filePath1.replace( "/","/");
			 NewFilepath2=filePath2.replace( "/","/");
			 NewFilepath3=filePath3.replace( "/","/");
			 NewFilepath4=filePath4.replace( "/","/");
		}
		
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
		generic.GoToSleep(3000);
		driver.findElements(Upload_Attachment).get(1).click();
		generic.GoToSleep(2000);
		generic.UploadFile(NewFilepath1);
		GenericFunctions.WaitFor_visibility(driver, deleteAttachment_icon1);
		generic.GoToSleep(2000);
		driver.findElements(Upload_Attachment).get(1).click();
		generic.GoToSleep(2000);
		generic.UploadFile(NewFilepath2);
		GenericFunctions.WaitFor_visibility(driver, deleteAttachment_icon2);
		generic.GoToSleep(2000);
		driver.findElements(Upload_Attachment).get(1).click();
		generic.GoToSleep(2000);
		generic.UploadFile(NewFilepath3);
		GenericFunctions.WaitFor_visibility(driver, deleteAttachment_icon3);
		generic.GoToSleep(2000);
		driver.findElements(Upload_Attachment).get(1).click();
		generic.GoToSleep(2000);
		generic.UploadFile(NewFilepath4);
		GenericFunctions.WaitFor_visibility(driver, deleteAttachment_icon4);
		generic.GoToSleep(2000);
		fileName1 = FilenameUtils.getBaseName(NewFilepath1);
		FileExtension1 = FilenameUtils.getExtension(NewFilepath1);
		fileName2 = FilenameUtils.getBaseName(NewFilepath2);
		FileExtension2 = FilenameUtils.getExtension(NewFilepath2);
		fileName3 = FilenameUtils.getBaseName(NewFilepath3);
		FileExtension3 = FilenameUtils.getExtension(NewFilepath3);
		fileName4 = FilenameUtils.getBaseName(NewFilepath4);
		FileExtension4 = FilenameUtils.getExtension(NewFilepath4);
	}

	public void clickOnShareWithButton(){
		try {
			driver.findElement(ShareWith_DD).click();
		} catch (Exception e) {
			driver.findElement(ShareWith_DD).click();
		}

	}
	public String getTitleErrorMessage(){
		System.out.println("TitleError:"+driver.findElements(createAlbumError_msg).get(0).getText());
		return driver.findElements(createAlbumError_msg).get(0).getText();
	}
	public String getSelectGroupErrorMessage(){
		System.out.println("SharewithError:"+driver.findElements(createAlbumError_msg).get(1).getText());
		return driver.findElements(createAlbumError_msg).get(1).getText();
	}
	public void clickOnEditAlbum(){
		List<WebElement> elements = driver.findElements(edit_Btn);
		elements.get(0).click();
	}
	public void clickOnDeleteAlbum() throws InterruptedException{
		generic.scrollPage(Delete_Btn);
		Thread.sleep(2500);
		WebElement element = driver.findElement(Delete_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	public String getloggedInUser(String role) throws InterruptedException{
		WebElement element = driver.findElement(profileHover);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		String UserName;
		UserName=driver.findElement(loggedInUser).getText();
		UserName=UserName.split(role.toLowerCase())[0].trim();
		return UserName;
	}
	public String getUserName() throws InterruptedException{
		GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(AlbumUser).get(0));
		String UserName= driver.findElements(AlbumUser).get(0).getText();
		return UserName.split(",")[0].trim();
	}
	public boolean isAlbumCreatorNameSameAsLoggedInUser (String role) throws InterruptedException{
		if(getUserName().equalsIgnoreCase(getloggedInUser(role))){
			return true;
		}else{
			return false;
		}
	}

	public String fillCreateAlbum_Form() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		ClassName = WallActions.selectFirstClassSection();
		Thread.sleep(2000);
		fillDescription("Test Description_"+timeStamp);
		uploadSingleImage();
		clickOnCreateButton();
		Thread.sleep(3000);
	//	System.out.println("Qa Web automation Title_" + timeStamp);
		return "Qa Web automation Title_" + timeStamp;
	}
	public void clickOnCreateButtonWithoutAnyData(String title, String desc) throws Throwable{
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle(title);
		fillDescription(desc);
		generic.scrollPage(Create_album_Btn);
		clickOnCreateButton();
		Thread.sleep(2000);
		generic.newScroll();
		Thread.sleep(2000);
	}
	public String createAlbumFormToAllClasses() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		WallActions.selectAllClass();
		Thread.sleep(2000);
		fillDescription("Test Description_"+timeStamp);
		uploadSingleImage();
		clickOnCreateButton();
		Thread.sleep(4000);
	//	System.out.println("Qa Web automation Title_" + timeStamp);
		return "Qa Web automation Title_" + timeStamp;
	}
	public String CreateAlbumWithMultipleAttachment() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		WallActions.selectFirstClassSection();
		Thread.sleep(1000);
		fillDescription("Test Description_"+timeStamp);
		uploadMultipleImage();
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;
	}
	public String fillCreateAlbum_FormWithData(String title, String desc) throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Automation Title_" + title +timeStamp);
		clickOnShareWithButton();
		WallActions.selectFirstClassSection();
		Thread.sleep(2000);
		fillDescription("Test Description_"+desc);
		uploadSingleImage();
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Automation Title_" + title +timeStamp;	
	}
	public String fillCreateAlbumWithDifferentTab() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		WallActions.selectSchoolStaff_AllTeacherGroup();
		Thread.sleep(2000);
		fillDescription("Test Description_"+timeStamp);
		uploadSingleImage();
		generic.GoToSleep(2000);
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}
	public String getTitle() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, getTitle);
		return driver.findElement(getTitle).getText();
	}
	public String getUploadedFileName(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(0).getText();
		return UploadedFileName;
	}
	public boolean isImageNameSameAsUploadedByUser(){
		if(getUploadedFileName().contains(fileName)){
			return true;
		}else{
			return false;
		}
	}
	public String getUploadedFileName1(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(0).getText();
		return UploadedFileName;
	}
	public String getUploadedFileName2(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(1).getText();
		return UploadedFileName;
	}
	public String getUploadedFileName3(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(2).getText();
		return UploadedFileName;
	}
	public String getUploadedFileName4(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(3).getText();
		return UploadedFileName;
	}
	public boolean isFileNameSameAsUploadedByUser(){
		if(getUploadedFileName1().contains(fileName1)&&getUploadedFileName1().contains(FileExtension1)&&
			getUploadedFileName2().contains(fileName2)&&getUploadedFileName1().contains(FileExtension1)&&
			getUploadedFileName3().contains(fileName3)&&getUploadedFileName1().contains(FileExtension1)&&
			getUploadedFileName4().contains(fileName4)&&getUploadedFileName1().contains(FileExtension1)){
			return true;
		}else{
			return false;
		}
	}
	public boolean isTitleDisplayed(String Title) throws InterruptedException{
		int i=0;
		String ActualTitle=getTitle();
		while(!(ActualTitle.equalsIgnoreCase(Title)) && i<20){
			driver.navigate().refresh();
			ActualTitle=getTitle();
			i++;
			generic.GoToSleep(1000);
		}
		if(!(ActualTitle.equalsIgnoreCase(Title))){
			return false;
		}else{
			return true;
		}
	}
	public void scrollInModelDialog(){
		WebElement element = driver.findElement(Delete_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void ConfirmDeletePop() throws InterruptedException{
		Thread.sleep(2000);
		WallActions.clickOnDeletePopScreenGeneric(clickOnDeletePopScreenGeneric);
		generic.GoToSleep(3000);
	}

	public void clickOnAlbumUpdateButton() throws InterruptedException{
		generic.scrollPage(updateGallery_Btn);
		Thread.sleep(2000);
		WebElement element = driver.findElement(updateGallery_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		generic.GoToSleep(1000);
	}

	
}
