package helper;

import java.io.IOException;
import java.util.List;
import helper.GenericFunctions;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WallActions{
	static WebDriver driver;
	static GenericFunctions generic;
	public static String fileName1="",fileName2="";
	public static String FileExtension1="", FileExtension2="";
	
	public WallActions(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	
	
	public static By profile=By.xpath("//img[@class='profilePic ng-scope']");
	public static By MyProfile_lnk=By.xpath("//a[@href='/home/account/profile']");
	public static By logout=By.partialLinkText("Logout");
	public static By adminProfile_RdBtn=By.xpath("//div[@class='paddingLoginSchool col-sm-8']");
	public static By proceed_Btn=By.xpath("//button[@class='btn btn-primary padding-10-60 ng-scope']");
	public static By firstClassSection_chkbox=By.xpath("(//label[@class='css-label radGroup2 checkleft'])[2]");
	public static By allClasses_chkbox=By.xpath("(//label[@class='css-label radGroup2 checkleft'])[1]");
	public static By ClassSection_txt = By.xpath("(//span[@class='collapsed ng-binding ng-scope accordion-toggle'])[1]");
	public static By SaveCloseGroup_Btn = By.id("close");
	public static By Upload_Attachment_Icon=By.xpath("(//input[contains(@src,'/imagedrag.png')])[2]");
	public static By deleteAttachment_icon1= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[1]");
	public static By deleteAttachment_icon2= By.xpath("(//i[@class='fa fa-trash ng-pristine ng-untouched ng-valid ng-not-empty'])[2]");
	public static By uploadFileOnWall=By.xpath("//li[@class='ng-binding ng-scope']");
	public static By schoolStaff_lnk=By.xpath("(//a[@data-toggle='tab'])[2]");
	public static By allTeacherGroup_chkbox=By.xpath("(//span//label[@class='css-label radGroup2 checkleft'])[1]");
	
	public static void mouseHoverOnProfile(){
		GenericFunctions.WaitFor_visibility(driver, profile);
		WebElement element = driver.findElement(profile);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();	
	}
	public void selectAdminProfile() throws InterruptedException{
		Thread.sleep(1500);
		GenericFunctions.WaitFor_visibility(driver, adminProfile_RdBtn);
		driver.findElements(adminProfile_RdBtn).get(0).click();
		GenericFunctions.WaitFor_visibility(driver, proceed_Btn);
		generic.scrollPage(proceed_Btn);
		driver.findElement(proceed_Btn).click();
	}
	public void selectProfile(int index) throws InterruptedException{
		Thread.sleep(1500);
		GenericFunctions.WaitFor_visibility(driver, adminProfile_RdBtn);
		driver.findElements(adminProfile_RdBtn).get(index).click();
		GenericFunctions.WaitFor_visibility(driver, proceed_Btn);
		generic.scrollPage(proceed_Btn);
		driver.findElement(proceed_Btn).click();
	}
	public static void clickOnDeletePopScreenGeneric(By Xpath){
		WebElement element = driver.findElement(Xpath);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		driver.findElement(Xpath).click();
	}
	
	public static void ClickEditWallGeneric(By Xpath){
		
		List<WebElement> elements=driver.findElements(Xpath);
		elements.get(0).click();
}
	
	public static void WallUpdateButtonGeneric(By Xpath){
		
		driver.findElement(Xpath).click();;
	}
	
	public static void WallDeleteButtonGeneric(By Xpath){
	
		driver.findElement(Xpath).click();
		
	}
	
	public static void Logout(){
		mouseHoverOnProfile();
		driver.findElement(logout).click();	
	}
	public static String selectFirstClassSection(){
		GenericFunctions.WaitFor_visibility(driver, firstClassSection_chkbox);
		driver.findElement(firstClassSection_chkbox).click();
		String classname= driver.findElement(ClassSection_txt).getText();
		driver.findElement(SaveCloseGroup_Btn).click();
		return classname;
	}
	public static void selectAllClass() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, firstClassSection_chkbox);
		driver.findElement(allClasses_chkbox).click();
		driver.findElement(SaveCloseGroup_Btn).click();
	}
	public static void selectSchoolStaff_AllTeacherGroup(){
		GenericFunctions.WaitFor_visibility(driver, schoolStaff_lnk);
		driver.findElement(schoolStaff_lnk).click();
		GenericFunctions.WaitFor_visibility(driver, allTeacherGroup_chkbox);
		driver.findElement(allTeacherGroup_chkbox).click();
		driver.findElement(SaveCloseGroup_Btn).click();
	}
	public static void uploadImage() throws Throwable{
		String NewFilepath1,NewFilepath2;
		String filePath1=System.getProperty("user.dir") + "/dummyFiles/satya.png";
		String filePath2=System.getProperty("user.dir") + "/dummyFiles/test.pdf";
		String OSName = generic.getCurrentEnvironment();
	//	System.out.println(OSName);
		if(OSName.contains("Windows")){
			NewFilepath1=filePath1.replace( "/","\\");
			NewFilepath2=filePath2.replace( "/","\\");
		}else{
			NewFilepath1=filePath1.replace( "/","/");
			NewFilepath2=filePath2.replace( "/","/");
		}
		generic.scrollPage(Upload_Attachment_Icon);
		generic.GoToSleep(2000);
		WebElement uplaodLink = driver.findElement(Upload_Attachment_Icon);
		Actions actions = new Actions(driver);
		actions.moveToElement(uplaodLink);
		actions.click().build().perform();
		generic.GoToSleep(2000);
		generic.UploadFile(NewFilepath1);
		GenericFunctions.WaitFor_visibility(driver, deleteAttachment_icon1);
		generic.GoToSleep(2000);
		driver.findElement(Upload_Attachment_Icon).click();
		generic.GoToSleep(2000);
		generic.UploadFile(NewFilepath2);
		GenericFunctions.WaitFor_visibility(driver, deleteAttachment_icon2);
		generic.GoToSleep(2000);
		generic.GoToSleep(2000);
		fileName1 = FilenameUtils.getBaseName(NewFilepath1);
		FileExtension1 = FilenameUtils.getExtension(NewFilepath1);
		fileName2 = FilenameUtils.getBaseName(NewFilepath2);
		FileExtension2 = FilenameUtils.getExtension(NewFilepath2);
	}
	public static String getUploadedFileName1(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(0).getText();
		return UploadedFileName;
	}
	public static String getUploadedFileName2(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(1).getText();
		return UploadedFileName;
	}
	
	public static void clickOnMyProfile(){
		mouseHoverOnProfile();
		driver.findElement(MyProfile_lnk).click();
	}
	
	public static void UploadFile(String FileType) throws IOException{
		
		
		String filePath = System.getProperty("user.dir");

		switch(FileType){
			
			case "jpg":
				Runtime.getRuntime().exec(filePath+"\\Image.exe");
				break;
				
			case "pdf":
				Runtime.getRuntime().exec(filePath+"\\Pdf.exe");
				break;
			case "word":
				
				Runtime.getRuntime().exec(filePath+"\\Word.exe");
				break;
				
			case "excel":
				Runtime.getRuntime().exec(filePath+"\\Excel.exe");
				
				break;
				
			default:
				System.out.println("File does not Exist");
		}
		
    }

}





