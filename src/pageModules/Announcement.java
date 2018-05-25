package pageModules;
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

import utils.Xls_Reader;

public class Announcement {
	WebDriver driver;
	LoginPage login;
	Xls_Reader xls;
	GenericFunctions generic;
	public static String ClassName="";
	public Announcement(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By WallUpdateButtonGeneric=By.xpath(".//*[@id='btn-next']");
	public static By selectTeacher=By.xpath("//p[contains(text(),'teacher')]");
	public static By continueButton=By.xpath("//button[@class='btn btn-primary paddingLR marginR20 ng-scope']");
	public static By clickOnDeletePopScreenGeneric=By.xpath(".//*[@id='delete']");
	public static By Post_Lnk=By.xpath("//div[@class='dropdown']/button[@class='btn btn-primary']");
	public static By Announcement_Lnk=By.xpath("//button[@id='announcement']");
	public static By Delete_Btn=By.xpath("//button[@class='delete-post z-index ng-binding']");
	public static By Title_Txt=By.xpath("(//input[@id='title'])[2]");
	public static By Title_Txt_Alternate=By.xpath("(//input[@id='title'])[1]");
	public static By ShareWith_DD=By.xpath("//span[@class='btn-contact-gallery']");
	public static By SendSMS_Yes=By.xpath("(//div[@class='radiobutton']/label)[3]");
	public static By SendSMS_No=By.xpath("(//div[@class='radiobutton']/label)[4]");
	public static By Description_Txt=By.xpath("(//textarea[@id='textboxIssuereport'])[2]");
	public static By Upload_Attachment=By.xpath("//input[contains(@src,'/imagedrag.png')]");
	public static By Group_Selection_Chk=By.xpath("//input[@name='group_code']");
	public static By Group_Selection_ClassName=By.xpath("//a[@class='ng-binding ng-scope accordion-toggle collapsed']");	
	public static By SaveCloseGroup_Btn=By.xpath("//button[@class='btn btn-primary btn-block']");
	public static By Create_announce_Btn=By.xpath("(//button[@id='btn-next'])[2]");
	public static By filTerDropdown_Lnk=By.xpath("//button[@class='btn btn-default dropdown-toggle class-dropdown']");
	public static By filterTypeHeader=By.xpath("//div[contains(@class,'icon ng-scope')]/span");
	public static By announcementUser=By.xpath("//small[@class='pull-left text-color paddinglr0 ng-binding']");
	public static By loggedInUser=By.xpath("//h4[@class='margin-bottom-4 ng-binding']");
	public static By profileHover=By.xpath("//img[@class='profilePic ng-scope']");
	public static By uploadFileOnWall=By.xpath("//li[@class='ng-binding ng-scope']");
	public static By getTitle=By.xpath("//h4[@class='m-t-20 ng-binding']");
	public static By DeleteAnnouncement=By.xpath("//button[@class='delete-post z-index ng-binding']");
	public static By schoolStaff_lnk=By.xpath("(//a[@data-toggle='tab'])[2]");
	public static By allTeacherGroup_chkbox=By.xpath("(//span//label[@class='css-label radGroup2 checkleft'])[1]");
	public static By createAnnouncementError_msg=By.xpath("//div[@class='errors error-username ng-scope']");
	public static By edit_Btn=By.xpath("//div[contains(@class,'edit-button ng-scope')]/a/span");
	public static By updateAnnouncement_Btn=By.id("btn-next");
	public static By deleteAttachment_icon1= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[1]");
	public static By deleteAttachment_icon2= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[2]");
	public static By deleteAttachment_icon3= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[3]");
	public static By deleteAttachment_icon4= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[4]");
	public static By student_class=By.xpath("(//h3[@class='ng-binding ng-scope'])[2]");
	public static By Del_btn = By.id("delete");
	
	public void selectTeacherProfile()
	{
		driver.findElement(selectTeacher).click();
		driver.findElement(continueButton).click();
	}
	public void clickOnAPostOptions() throws InterruptedException{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver,Post_Lnk);
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(Post_Lnk)).perform();
		builder.moveToElement(driver.findElement(Announcement_Lnk)).click().perform();
	}
	public String getTitleErrorMessage(){
		return driver.findElements(createAnnouncementError_msg).get(0).getText();
	}
	public String getSelectGroupErrorMessage(){
		return driver.findElements(createAnnouncementError_msg).get(1).getText();
	}
	public void fillTitle(String value){
		try {
			if(value.trim().length()==0){
				driver.findElement(Title_Txt).clear();
				return;
			}
			driver.findElement(Title_Txt).clear();
			driver.findElement(Title_Txt).sendKeys(value);
		}catch (Exception e) {
			if(value.trim().length()==0){
				driver.findElement(Title_Txt_Alternate).clear();
				return;
			}
			driver.findElement(Title_Txt_Alternate).clear();
			driver.findElement(Title_Txt_Alternate).sendKeys(value);
		}
	}
	
	public void clickOnShareWithButton(){
		try {
			driver.findElement(ShareWith_DD).click();
		} catch (Exception e) {
			driver.findElement(ShareWith_DD).click();
		}
	}
	public void clickOnSendSMSNO(){
		GenericFunctions.WaitFor_visibility(driver, SendSMS_No);
		driver.findElement(SendSMS_No).click();
	}
	public void fillDescription(String value){
		if(value.trim().length()==0){
			driver.findElement(Description_Txt).clear();
			return;
		}
		driver.findElement(Description_Txt).sendKeys(value);
	}
	public void clickOnCreateButton(){
		GenericFunctions.WaitFor_visibility(driver, Create_announce_Btn);
		driver.findElement(Create_announce_Btn).click();
	}
	public void clickOnCreateButtonWithoutAnyData(String title, String desc) throws Throwable{
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle(title);
		fillDescription(desc);
		generic.scrollPage(Create_announce_Btn);
		clickOnCreateButton();
	}
	public String fillCreateAnnouncementForm() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		ClassName = WallActions.selectFirstClassSection();
		clickOnSendSMSNO();
		fillDescription("Test Description_"+timeStamp);
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}
	
	public String CreateAnnouncementFormToAllClasses() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		WallActions.selectAllClass();
		clickOnSendSMSNO();
		fillDescription("Test Description_"+timeStamp);
		generic.GoToSleep(2000);
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}

	public String fillCreateAnnouncementFormWithDifferentTab() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		WallActions.selectSchoolStaff_AllTeacherGroup();
		clickOnSendSMSNO();
		fillDescription("Test Description_"+timeStamp);
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}

	public String fillCreateAnnouncementFormWithData(String title, String desc) throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Automation Title_" + title +timeStamp);
		clickOnShareWithButton();
		WallActions.selectFirstClassSection();
		clickOnSendSMSNO();
		fillDescription("Test Description_"+desc);
		clickOnCreateButton();
		return "Automation Title_" + title +timeStamp;	
	}

	public String fillCreateAnnouncementWithUploadFile() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		WallActions.selectFirstClassSection();
		clickOnSendSMSNO();
		fillDescription("Test Description_"+timeStamp);
		WallActions.uploadImage();
		clickOnCreateButton();
		generic.GoToSleep(3000);
		return "Qa Web automation Title_" + timeStamp;	
	}
	public String getTitle() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, getTitle);
		return driver.findElement(getTitle).getText();
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
		GenericFunctions.WaitFor_visibility(driver, announcementUser);
		String UserNameWithTime= driver.findElements(announcementUser).get(0).getText();
		return UserNameWithTime.split(",")[0].trim();
	}
	public boolean isAnnouncementCreatorNameSameAsLoggedInUser (String role) throws InterruptedException{
		if(getUserName().equalsIgnoreCase(getloggedInUser(role))){
			return true;
		}else{
			return false;
		}
	}
	public boolean isFileNameSameAsUploadedByUser(){
		if(WallActions.getUploadedFileName1().contains(WallActions.fileName1)&& WallActions.getUploadedFileName1().contains(WallActions.FileExtension1)
				&& WallActions.getUploadedFileName2().contains(WallActions.fileName2)&& WallActions.getUploadedFileName2().contains(WallActions.FileExtension2)){
			return true;
		}else{
			return false;
		}
	}
	public void clickOnEditAnnuncement(){
		List<WebElement> elements = driver.findElements(edit_Btn);
		elements.get(0).click();
	}
	public void scrollInModelDialog(){
		WebElement element = driver.findElement(Delete_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void clickOnDeleteAnnouncement() throws InterruptedException{
		generic.scrollPage(Delete_Btn);
		Thread.sleep(2000);
		WebElement element = driver.findElement(Delete_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	public void ConfirmDeletePop() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, Del_btn);
		driver.findElement(Del_btn).click();
		//WallActions.clickOnDeletePopScreenGeneric(clickOnDeletePopScreenGeneric);
		generic.GoToSleep(1000);
	}
	public void clickOnUpdateButton() throws InterruptedException{
		generic.scrollPage(updateAnnouncement_Btn);
		Thread.sleep(2000);
		WebElement element = driver.findElement(updateAnnouncement_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	}
}
