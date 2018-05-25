package pageModules;

import java.util.List;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.Xls_Reader;

public class ManageUser {
	WebDriver driver;
	LoginPage login;
	Xls_Reader xls;
	WebDriverWait wait;
	GenericFunctions generic;
	String MobileNo = "7011291149";
	public ManageUser(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
		xls=new Xls_Reader();
	}
	//div[@class='dropdown-content left-0']/a/span
	public static By Header_lnk=By.xpath("//div[contains(@class,'mobile-view')]");
	public static By Manage_lnk=By.xpath("//a[@class='dropbtn']");
	public static By ManageSubModule_lnk=By.xpath("//div[@class='dropdown-content left-0']/a");
	public static By Manage_User_lnk=By.xpath("//a[@href='/home/manage/user']");
	public static By Manage_SchoolDetails_lnk=By.xpath("//span[@class='school_Details']");
	public static By Manage_ClassSection_lnk=By.xpath("//span[@class='class-section-img']");
	public static By searchStudentClass_DD = By.xpath("//select[@ng-change='changeClass()']");
	public static By searchStudentClassSection_DD = By.xpath("//select[@class='form-control select ng-pristine ng-untouched ng-valid ng-empty']");
	public static By clearSection_DD = By.xpath("//select[@class='form-control select ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched']");
	public static By clearSectionValue_DD = By.xpath("//select[@class='form-control select ng-valid ng-dirty ng-valid-parse ng-touched ng-empty']");
	public static By searchStudentMobileNumber_Txt = By.id("schooladdress");
	public static By viewUser_Lnk = By.xpath("//div[@ng-bind-html='row.entity[col.field]']");
	public static By removeUser_Lnk = By.xpath("//div[@ng-bind-html='row.entity[col.field]']");
	public static By parentChild_RB = By.xpath("//label[@for='parents']");
	public static By teacher_RB = By.xpath("//label[@for='teacher']");
	public static By admin_RB = By.xpath("//label[@for='admin']");
	public static By principal_RB = By.xpath("//label[@for='principal']");
	
	public boolean isHeaderPresent(){
		List<WebElement> elements = driver.findElements(Header_lnk);
			String Sheetname="ManageUser",HeaderLink;
			for(int i=2;i<elements.size()+2;i++){
				HeaderLink=xls.getCellData(Sheetname, "HeaderLink", i);
				Assert.assertEquals(HeaderLink, elements.get(i-2).getText(), "link not matched");
			}
		return true;
	}
	public void mouseHoverOnManageLnk(){
		WebElement element = driver.findElements(Manage_lnk).get(1);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();	
	}
	public void ManageUserScreen(){
		mouseHoverOnManageLnk();
		driver.findElement(Manage_User_lnk).click();
	}
	public void selectParentStudentRole() throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(parentChild_RB);
	}
	public void selectTeacherRole() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(teacher_RB).click();
	}
	public void selectAdminRole() throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(admin_RB).click();
	}
	public void selectPrincipalRole() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(principal_RB).click();
	}
	public void searchWithClassOnly() throws InterruptedException{
		Select classSelect = new Select(driver.findElement(searchStudentClass_DD));
		driver.findElement(searchStudentMobileNumber_Txt).clear();
		Thread.sleep(2000);
		classSelect.selectByVisibleText("Class 1");
	}
	public void searchWithClassandSection() throws InterruptedException{
		Select classSelect = new Select(driver.findElement(searchStudentClass_DD));
		Select sectionSelect = new Select(driver.findElement(searchStudentClassSection_DD));
		
		driver.findElement(searchStudentMobileNumber_Txt).clear();
		Thread.sleep(2000);
		classSelect.selectByVisibleText("Class 1");
		sectionSelect.selectByVisibleText("A");
	}
	public void searchwithMobileNumberOnly() throws InterruptedException{
		Select clearClass = new Select(driver.findElement(searchStudentClass_DD));
		Thread.sleep(2000);
		clearClass.selectByVisibleText("Select Class");
		driver.findElement(searchStudentMobileNumber_Txt).clear();
		Thread.sleep(2000);
		
		driver.findElement(searchStudentMobileNumber_Txt).sendKeys(MobileNo);
	}
	public void searchwithClassAndMobileNo() throws InterruptedException{
		Select clearClass = new Select(driver.findElement(searchStudentClass_DD));
		Thread.sleep(2000);
		clearClass.selectByVisibleText("Select Class");
		driver.findElement(searchStudentMobileNumber_Txt).clear();
		Thread.sleep(2000);
		
		Select classSelect = new Select(driver.findElement(searchStudentClass_DD));
		driver.findElement(searchStudentMobileNumber_Txt).clear();
		Thread.sleep(2000);
		classSelect.selectByVisibleText("Class 1");
		driver.findElement(searchStudentMobileNumber_Txt).sendKeys(MobileNo);
	}
	public void searchwithClassSectionAndMobileNo() throws InterruptedException{
		Select clearClass = new Select(driver.findElement(searchStudentClass_DD));
		Thread.sleep(2000);
		clearClass.selectByVisibleText("Select Class");
		driver.findElement(searchStudentMobileNumber_Txt).clear();
		Thread.sleep(2000);
		
		Select classSelect = new Select(driver.findElement(searchStudentClass_DD));
		classSelect.selectByVisibleText("Class 1");
		
		Select sectionSelect = new Select(driver.findElement(clearSectionValue_DD));
		sectionSelect.selectByVisibleText("A");
		driver.findElement(searchStudentMobileNumber_Txt).sendKeys(MobileNo);
	}
	public void viewUserInfo() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(viewUser_Lnk).click();
	}	
	public void deleteUser() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(removeUser_Lnk).click();
	}
	public boolean isManageSubModuleLink_present(){
		mouseHoverOnManageLnk();
		List<WebElement> elements = driver.findElements(ManageSubModule_lnk);
		String Sheetname="ManageUser",ManageSubModule;
		for(int i=2;i<elements.size()+1;i++){
			ManageSubModule=xls.getCellData(Sheetname, "ManageSubModule", i).trim();
			Assert.assertEquals(ManageSubModule, elements.get(i-2).getText(), "module not matched");
		}
		return true;
	}
	public void search() throws InterruptedException{
		searchWithClassOnly();
		searchwithMobileNumberOnly();
		searchWithClassandSection();
		searchwithClassAndMobileNo();
		searchwithClassSectionAndMobileNo();
		viewUserInfo();
	}
	public void ViewAndDelete() throws InterruptedException{
		//deleteUser();
		viewUserInfo();
}
}
