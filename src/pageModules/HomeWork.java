package pageModules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import org.openqa.selenium.support.ui.Select;





public class HomeWork {

	WebDriver driver;

	GenericFunctions generic;
	public static String ClassName="";
	public HomeWork(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}

	public static By Post_Lnk=By.xpath("//div[@class='dropdown']/button[@class='btn btn-primary']");
	public static By Homework_Lnk=By.xpath("//button[@id='homework']");
	public static By Title_Txt_Fill=By.xpath("(//input[@id='title'])[2]");
	public static By Title_Txt_Edit=By.xpath("(//input[@id='title'])[1]");
	public static By ShareWith_DD=By.xpath("//span[@class='btn-contact-gallery']");
	public static By Group_Selection_Chk=By.xpath("//input[@name='group_code']");
	public static By SaveCloseGroup_Btn=By.xpath("//button[@class='btn btn-primary btn-block']");
	public static By selectSubject_DrpDwn=By.xpath("//div[@class='dropdown registration-form col-md-12 padding-0 ng-scope']");
	public static By Class_Subject=By.xpath(".//*[@id='mCSB_1_container']/div/div[1]/form[1]/div[3]/ul/li/span/input");
	public static By Sub_date=By.xpath(".//*[@id='endDate']");
	public static By Calender_date=By.xpath("html/body/div[6]/div[1]/table/tbody/tr[1]/td[7]");
	public static By SendSMS_Yes=By.xpath("(//div[@class='radiobutton']/label)[3]");
	public static By SendSMS_No=By.xpath("(//div[@class='radiobutton']/label)[4]");
	public static By Description_Txt=By.xpath("(//textarea[@id='textboxIssuereport'])[2]");
	public static By Upload_Attachment=By.xpath("//input[contains(@src,'/imagedrag.png')]");
	public static By Create_homework_Btn=By.xpath("(//button[@id='btn-next'])[2]");
	public static By Delete_Btn=By.xpath("//button[contains(@class,'delete-post')]");
    public static By update_btn=By.xpath(".//*[@id='btn-next']");
    public static By edit_Btn=By.xpath("//div[contains(@class,'edit-button ng-scope')]/a/span");
    public static By update_Btn=By.id("btn-next");
    public static By WallDeleteButtonGeneric=By.xpath("//button[contains(@class,'delete-post')]");
    public static By clickOnDeletePopScreenGeneric=By.xpath(".//*[@id='delete']");
    public static By HomeWorkFillTitleE=By.xpath("//*[@id='title' and @aria-invalid='true']");
    public static By ClickHomeworkEdit=By.xpath("//div[contains(@class,'edit-button ng-scope')]");
    public static By HomeWorkWallTitle=By.xpath("//h4[@class='m-t-20 ng-binding']");
    public static By profileHover=By.xpath("//img[@class='profilePic ng-scope']");
    public static By announcementUser=By.xpath("//small[@class='pull-left text-color paddinglr0 ng-binding']");
	public static By loggedInUser=By.xpath("//h4[@class='margin-bottom-4 ng-binding']");
	public static By uploadFileOnWall=By.xpath("//li[@class='ng-binding ng-scope']");
	public static By Username_Txt=By.id("Fname");
	public static By Password_Txt=By.id("password-lg1");
	public static By Login_Btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-20']");
	public static By schoolStaff_lnk=By.xpath("//a[@data-toggle='tab']");
	public static By allTeacherGroup_chkbox=By.xpath("//span[@class='custom-checkbox ng-scope']/input[contains(@class,'group_code')]");
	public static By createHomeworkError_msg=By.xpath("//div[@class='errors error-username ng-scope']");
	public static By getTitle=By.xpath("//h4[@class='m-t-20 ng-binding']");
	public static By deleteAttachment_icon1= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[1]");
	public static By deleteAttachment_icon2= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[2]");
	public static By deleteAttachment_icon3= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[3]");
	public static By deleteAttachment_icon4= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[4]");
	public static By upload_icon= By.xpath("(//input[contains(@src,'/imagedrag.png')])[2]"); 
	public static By Del_btn = By.id("delete");
	
    public void clickOnShareWithButton(){
		try {
			driver.findElement(ShareWith_DD).click();
		} catch (Exception e) {
			driver.findElement(ShareWith_DD).click();
		}

	}
    public String getTitleErrorMessage(){
    	System.out.println("Title Error:"+driver.findElements(createHomeworkError_msg).get(0).getText());
		return driver.findElements(createHomeworkError_msg).get(0).getText();
	}
	public String getSelectGroupErrorMessage(){
		System.out.println("Share with Error:"+driver.findElements(createHomeworkError_msg).get(1).getText());
		return driver.findElements(createHomeworkError_msg).get(1).getText();
	}
  
	public void clickOnAPostOptions() throws InterruptedException{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver,Post_Lnk);
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(Post_Lnk)).perform();
		builder.moveToElement(driver.findElement(Homework_Lnk)).click().perform();
	}
	
	public String FillCreateHomeWorkDetails() throws InterruptedException, AWTException{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Qa web Homework Title_"+timeStamp);
		clickOnShareWithButton();
		ClassName = WallActions.selectFirstClassSection();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Qa web Homework Description "+timeStamp);
		Thread.sleep(2000);
		generic.scrollPage(Create_homework_Btn);
		Thread.sleep(2000);
		HomeWorkClickCreate();
		Thread.sleep(4000);
		return "Qa web Homework Title_"+timeStamp;
	}
	public void clickOnCreateButtonWithoutAnyData(String title, String desc) throws Throwable{
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle(title);
		DescriptionHomeWork(desc);
		generic.scrollPage(Create_homework_Btn);
		HomeWorkClickCreate();
	}
	public String CreateHomeWorkDetailsToAllClasses() throws InterruptedException, AWTException{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Qa web Homework Title_"+timeStamp);
		clickOnShareWithButton();
		WallActions.selectAllClass();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Qa web Homework Description "+timeStamp);
		generic.scrollPage(Create_homework_Btn);
		HomeWorkClickCreate();
		Thread.sleep(3000);
		return "Qa web Homework Title_"+timeStamp;
	}
	public String fillCreateAnnouncementFormWithData(String title, String desc) throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Automation Title_" + title +timeStamp);
		clickOnShareWithButton();
		WallActions.selectFirstClassSection();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Test Description_"+desc);
		generic.scrollPage(Create_homework_Btn);
		HomeWorkClickCreate();
		Thread.sleep(4000);
		return "Automation Title_" + title +timeStamp;	
	}
	public String fillCreateAnnouncementFormWithDifferentTab() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		WallActions.selectSchoolStaff_AllTeacherGroup();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Test Description_"+timeStamp);
		generic.scrollPage(Create_homework_Btn);
		HomeWorkClickCreate();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}
	public String fillCreateHomeWorkWithUploadFile() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		WallActions.selectFirstClassSection();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Test Description_"+timeStamp);
		Thread.sleep(2000);
		WallActions.uploadImage();
		generic.GoToSleep(1000);
		HomeWorkClickCreate();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
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
		String UserName= driver.findElements(announcementUser).get(0).getText();
		return UserName.split(",")[0].trim();
	}
	public boolean isHomeWorkCreatorNameSameAsLoggedInUser (String role) throws InterruptedException{
		if(getUserName().equalsIgnoreCase(getloggedInUser(role))){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isFileupNameSameAsUploadedByUser(){
		if(WallActions.getUploadedFileName1().contains(WallActions.fileName1)&& WallActions.getUploadedFileName1().contains(WallActions.FileExtension1)
				&& WallActions.getUploadedFileName2().contains(WallActions.fileName2)&& WallActions.getUploadedFileName2().contains(WallActions.FileExtension2)){
			return true;
		}else{
			return false;
		}
	}
	public void HomeWorkFillTitle(String title) throws InterruptedException{
		

		if(title.trim().length()==0){
			driver.findElement(Title_Txt_Fill).clear();
			return;
		}
		Thread.sleep(2000);
	    driver.findElement(Title_Txt_Fill).clear();
		driver.findElement(Title_Txt_Fill).sendKeys(title);

	}

	public void SelectSubject() throws InterruptedException, AWTException{
		Thread.sleep(2000);
		driver.findElement(selectSubject_DrpDwn).click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		
	}

	public void SelectDate(){

		driver.findElement(Sub_date).click();
		driver.findElement(Sub_date).click();
		driver.findElement(Calender_date).click();

	}
	public void DescriptionHomeWork(String Description){

		driver.findElement(Description_Txt).click();
		driver.findElement(Description_Txt).clear();
		driver.findElement(Description_Txt).sendKeys(Description);
	//	driver.findElement(SendSMS_Yes).click();



	}

	public void HomeWorkClickCreate() throws InterruptedException{
		WebElement element = driver.findElement(Create_homework_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		element.click();
	}

	public String HomeWorkWallTitle(){
		return driver.findElement(HomeWorkWallTitle).getText();

	}
	public String getTitle() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, getTitle);
		return driver.findElement(getTitle).getText();
	}
	public void Fill_Username_Txt(String inputdata){
		GenericFunctions.WaitFor_visibility(driver, Username_Txt);
		driver.findElement(Username_Txt).clear();
		driver.findElement(Username_Txt).sendKeys(inputdata);
	}
	public void Fill_Password_Txt(String inputdata){
		driver.findElement(Password_Txt).clear();
		driver.findElement(Password_Txt).sendKeys(inputdata);
	}
	public void ClickOnLogin_Btn(){
		driver.findElement(Login_Btn).click();;
	}
	public void loginInToApplication(String uname, String password) throws InterruptedException{
		Fill_Username_Txt(uname);
		Fill_Password_Txt(password);
		Fill_Username_Txt(uname);
		ClickOnLogin_Btn();
	}
	public boolean isTitleDisplayed(String Title) throws InterruptedException{
		int i=0;
		String ActualTitle=getTitle();
		while(!(ActualTitle.equalsIgnoreCase(Title)) && i<20){
			driver.navigate().refresh();
			ActualTitle=getTitle();
			i++;
			generic.GoToSleep(1500);
		}
		if(!(ActualTitle.equalsIgnoreCase(Title))){
			return false;
		}else{
			return true;
		}

	}

	public void ClickHomeworkEdit() throws InterruptedException{

		List<WebElement> elements=driver.findElements(ClickHomeworkEdit);
		elements.get(0).click();



	}

	public void DeleteButtonHomework() throws InterruptedException{
		Thread.sleep(4000);
		driver.findElement(Delete_Btn).click();
		Thread.sleep(3000);
	}


	
	
	public void HomeWorkFillTitleE(String title) throws InterruptedException{
		Thread.sleep(1000);
		if(title.trim().length()==0){
			driver.findElement(Title_Txt_Fill).clear();
			return;
		}
	    driver.findElement(Title_Txt_Edit).clear();
		driver.findElement(Title_Txt_Edit).sendKeys(title);
	}
	
	public String UpdateTitle() throws InterruptedException{

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		HomeWorkFillTitleE("Qa Homework updated_"+timeStamp);
		return "Qa Homework updated_"+timeStamp;
	}
	
	public void clickOnEdit_Btn(){
		List<WebElement> elements = driver.findElements(edit_Btn);
		elements.get(0).click();
	}
	
	public void clickOnUpdate_Btn() throws InterruptedException{
		generic.scrollPage(update_btn);
		Thread.sleep(500);
		WebElement element = driver.findElement(update_btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		generic.GoToSleep(1000);
	}
	public void clickOnDeleteHomeWork() throws InterruptedException{
		generic.scrollPage(Delete_Btn);
		Thread.sleep(2000);
		WebElement element = driver.findElement(Delete_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	public void ConfirmDeletePop(){
		GenericFunctions.WaitFor_visibility(driver, Del_btn);
		driver.findElement(Del_btn).click();
		//WallActions.clickOnDeletePopScreenGeneric(clickOnDeletePopScreenGeneric);
		generic.GoToSleep(1000);
	}
	
	
}

