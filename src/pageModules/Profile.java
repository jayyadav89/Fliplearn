package pageModules;

import java.io.IOException;
import java.sql.SQLException;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.jcraft.jsch.JSchException;

import utils.Xls_Reader;

public class Profile {
	GenericFunctions generic;
	WebDriver driver;
	Xls_Reader xls=new Xls_Reader();
	
	public Profile(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By edit_btn=By.xpath("//a//img[@class='img-responsive']");
	public static By studentName=By.id("stuName");
	public static By AdminName_txt=By.id("adminName");
	public static By TeacherName_txt=By.id("teaName");
	public static By admission_No=By.id("admissionno");
	public static By DateofBirth=By.id("datepicker");
	public static By RollNo=By.id("rollno");
	public static By email=By.xpath("//input[@name='profileEmail']");
	public static By gender=By.xpath("//span[@class='radiobutton']");
	public static By save_btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-15 m-b-15 mobilebtnBlock']");
	public static By getPersonalInfo=By.xpath("//h3[@class='ng-binding']");
	public static By enterMobileNo=By.id("mobileno");
	public static By sendOTP=By.xpath("//button[@class='btn btn-primary padding-10-60 mobilebtnBlock margin-bottom-0 letterSp']");
	public static By enterOTP=By.id("enterOTP");
	public static By verify_btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-15 m-b-15']");	
	public static By getMobileAndClass=By.xpath("//h3[@class='ng-binding ng-scope']");
	public static By selectClassSection=By.xpath("//select[@id='radiusSelect']");
	public static By change_btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-15 m-b-15']");
	public static By enterNewLoginID=By.id("loginId");
	public static By submit_btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-15 m-b-15']");
	public static By oldPassword=By.id("oldPassword");
	public static By newPassword=By.id("newPassword");
	public static By confirmPassword=By.id("confirmPassword");
	public static By done_btn=By.xpath("//button[@class='btn btn-primary z-index btn-style m-b-28']");
	public static By logout=By.partialLinkText("Logout");
	public static By StudentName_errorMsg=By.xpath("//*[text()='Name cannot be empty']");
	public static By classTeacher_lnk=By.xpath("//img[@class='img-responsive pull-right']");
	public static By SelectClass_chkbx=By.xpath("//span[@class='pull-right checkbox']");
	public static By SelectClass_Savebtn=By.xpath("//button[@class='btn btn-primary padding-10-60']");
	public static By getClassName=By.xpath("//li[@class='ng-binding ng-scope']");
	
	
	public void clickOnPersonalInfo_Editbtn() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, edit_btn);
		driver.findElements(edit_btn).get(0).click();
	}
	public void clickOnMobile_Editbtn(){
		driver.findElements(edit_btn).get(1).click();
	}
	public void clickOnClassTeacher_Editbtn(){
		driver.findElement(classTeacher_lnk).click();
	}
	public void clickOnClass_section_Editbtn(){
		driver.findElements(edit_btn).get(4).click();
	}
	public void scrollInModelDialogToSubjectEditBtn(){
		WebElement element = driver.findElements(edit_btn).get(5);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void clickOnSubject_Editbtn(){
		driver.findElements(edit_btn).get(5).click();
	}
	public void clickOnSchoolInfo_Editbtn() throws InterruptedException{
		Thread.sleep(5000);
		driver.findElements(edit_btn).get(2).click();
	}
	public void clickOnLoginID_Editbtn() throws InterruptedException{
		Thread.sleep(3000);
		WebElement element = driver.findElements(edit_btn).get(3);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	public void clickOnPassword_Editbtn() throws InterruptedException{
		Thread.sleep(2000);
		WebElement element = driver.findElements(edit_btn).get(4);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	public void enterPassword(String oldPass, String NewPass) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(oldPassword).clear();
		driver.findElement(oldPassword).sendKeys(oldPass);
		driver.findElement(newPassword).clear();
		driver.findElement(newPassword).sendKeys(NewPass);
		driver.findElement(confirmPassword).clear();
		driver.findElement(confirmPassword).sendKeys(NewPass);
	}
	public void clickOnDone_btn(){
		driver.findElement(done_btn).click();
	}
	public void enterLoginID(String input) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(enterNewLoginID).clear();
		driver.findElement(enterNewLoginID).sendKeys(input);
	}
	public void clickOnSubmit_btn(){
		driver.findElements(submit_btn).get(3).click();
	}
	public void fillMobileNo(String input) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(enterMobileNo).clear();
		driver.findElement(enterMobileNo).sendKeys(input);
	}
	public void selectClass() throws InterruptedException{
		Thread.sleep(2000);
		if(driver.findElements(SelectClass_chkbx).get(3).isSelected()){
			driver.findElements(SelectClass_chkbx).get(3).click();
			Thread.sleep(1000);
			driver.findElements(SelectClass_chkbx).get(3).click();
		}else{
			driver.findElements(SelectClass_chkbx).get(3).click();
		}
		
	}
	public void selectClass_Section() throws InterruptedException{
		Thread.sleep(2000);
		if(driver.findElements(SelectClass_chkbx).get(0).isSelected()){
			driver.findElements(SelectClass_chkbx).get(0).click();
			Thread.sleep(1000);
			driver.findElements(SelectClass_chkbx).get(0).click();
		}else{
			driver.findElements(SelectClass_chkbx).get(0).click();
		}
		
	}
	public void select_Subject() throws InterruptedException{
		Thread.sleep(2000);
		if(driver.findElements(SelectClass_chkbx).get(0).isSelected()){
			driver.findElements(SelectClass_chkbx).get(0).click();
			Thread.sleep(1000);
			driver.findElements(SelectClass_chkbx).get(0).click();
		}else{
			driver.findElements(SelectClass_chkbx).get(0).click();
		}
		
	}
	public void clickOnSendOTP_btn(){
		driver.findElement(sendOTP).click();
	}
	public void clickSelectClass_Savebtn(){
		WebElement element = driver.findElements(SelectClass_Savebtn).get(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	public void clickSelectClass_Section_Savebtn(){
		WebElement element = driver.findElements(SelectClass_Savebtn).get(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	public void clickSubject_Savebtn(){
		WebElement element = driver.findElements(SelectClass_Savebtn).get(0);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	public void enterOTP(String Mobile) throws Exception{
		driver.findElement(enterOTP).sendKeys(generic.getOTPUsingMobile2(Mobile));
	}
	public void clickOnVerify_btn(){
		driver.findElements(verify_btn).get(2).click();
	}
	public void selectClass(String input){
		Select Sel_class=new Select(driver.findElements(selectClassSection).get(0));
		Sel_class.selectByVisibleText(input);
	}
	public void selectSection(String section){
		Select Sel_Section=new Select(driver.findElements(selectClassSection).get(1));
		Sel_Section.selectByVisibleText(section);
	}
	public void clickOnchange_btn(){
		driver.findElements(change_btn).get(0).click();
	}
	public void fillPassword(String oldPass, String NewPass) throws InterruptedException{
		clickOnPassword_Editbtn();
		enterPassword(oldPass, NewPass);
		clickOnDone_btn();
	}
	public void fillLoginID(String userID) throws InterruptedException{
		clickOnLoginID_Editbtn();
		enterLoginID(userID);
		clickOnSubmit_btn();
	}
	public void fillSchoolInfo(String input, String Section) throws InterruptedException{
		clickOnSchoolInfo_Editbtn();
		selectClass(input);
		selectSection(Section);
		clickOnchange_btn();
	}
	public void fillMobileAndSave(String mobile) throws Exception{
		GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(edit_btn).get(1));
		clickOnMobile_Editbtn();
		fillMobileNo(mobile);
		clickOnSendOTP_btn();
		String OTP=generic.getOTPUsingMobile2(mobile);
		Thread.sleep(2000);
		enterOTP(mobile);
		generic.GoToSleep(2000);
		clickOnVerify_btn();
	}
	public void SelectClassTeacherAndSave() throws InterruptedException{
		clickOnClassTeacher_Editbtn();
		Thread.sleep(2000);
		selectClass();
		clickSelectClass_Savebtn();
	}
	public void SelectClass_sectionAndSave() throws InterruptedException{
		clickOnClass_section_Editbtn();
		Thread.sleep(2000);
		selectClass_Section();
		clickSelectClass_Section_Savebtn();
	}
	public void SelectSubjectAndSave() throws InterruptedException{
		clickOnSubject_Editbtn();
		Thread.sleep(2000);
		select_Subject();
		clickSubject_Savebtn();
	}
	public boolean checkStudentNameMandatory() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, studentName);
		driver.findElement(studentName).clear();
		clickOnSave_btn();
		String ActualError = driver.findElement(StudentName_errorMsg).getText();
		if(ActualError.equalsIgnoreCase("Name cannot be empty")){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean checkAdminNameMandatory() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, AdminName_txt);
		GenericFunctions.WaitFor_clickable(driver, driver.findElement(AdminName_txt));
		driver.findElement(AdminName_txt).clear();
		clickOnSave_btn();
		String ActualError = driver.findElement(StudentName_errorMsg).getText();
		if(ActualError.equalsIgnoreCase("Name cannot be empty")){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean checkTeacherNameMandatory() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, TeacherName_txt);
		GenericFunctions.WaitFor_clickable(driver, driver.findElement(TeacherName_txt));
		driver.findElement(TeacherName_txt).clear();
		clickOnSave_btn();
		String ActualError = driver.findElement(StudentName_errorMsg).getText();
		if(ActualError.equalsIgnoreCase("Name cannot be empty")){
			return true;
		}
		else{
			return false;
		}
	}
	public void fillPersonalInformation(String StudentName, String Admission, String DOB, String Roll, String Email, String Gender) throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, studentName);
		driver.findElement(studentName).clear();
		driver.findElement(studentName).sendKeys(StudentName);
		driver.findElement(admission_No).clear();
		driver.findElement(admission_No).sendKeys(Admission);
		driver.findElement(DateofBirth).clear();
		driver.findElement(DateofBirth).sendKeys(DOB);
		driver.findElement(RollNo).clear();
		driver.findElement(RollNo).sendKeys(Roll);
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(Email);
		if(Gender.equalsIgnoreCase("Male")){
			driver.findElements(gender).get(0).click();
		}else if(Gender.equalsIgnoreCase("Female")){
			driver.findElements(gender).get(1).click();
		}else{
			System.out.println("Gender not found");
		}
	}
	public void fillAdminPersonalInformation(String AdminName, String Email, String Gender) throws InterruptedException{
		driver.findElement(AdminName_txt).clear();
		driver.findElement(AdminName_txt).sendKeys(AdminName);
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(Email);
		if(Gender.equalsIgnoreCase("Male")){
			driver.findElements(gender).get(0).click();
		}else if(Gender.equalsIgnoreCase("Female")){
			driver.findElements(gender).get(1).click();
		}else{
			System.out.println("Gender not found");
		}
	}
	public void fillTeacherPersonalInformation(String AdminName, String Email, String Gender) throws InterruptedException{
		driver.findElement(TeacherName_txt).clear();
		driver.findElement(TeacherName_txt).sendKeys(AdminName);
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(Email);
		if(Gender.equalsIgnoreCase("Male")){
			driver.findElements(gender).get(0).click();
		}else if(Gender.equalsIgnoreCase("Female")){
			driver.findElements(gender).get(1).click();
		}else{
			System.out.println("Gender not found");
		}
	}
	public void clickOnSave_btn() throws InterruptedException{
		Thread.sleep(1000);
		GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(save_btn).get(0));
		driver.findElements(save_btn).get(0).click();
	}
	public String getStudentName() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, getPersonalInfo);
		String StudentName="";
		StudentName = driver.findElements(getPersonalInfo).get(0).getText();
		int i=0;
		while((StudentName.equalsIgnoreCase(""))&& i<=10){
			Thread.sleep(1000);
			i++;
			StudentName = driver.findElements(getPersonalInfo).get(0).getText();
		}
		return StudentName;
	}
	public String getAdminName() throws InterruptedException{
		String StudentName="";
		int i=0;
		StudentName = driver.findElements(getPersonalInfo).get(0).getText();
		while((StudentName.equalsIgnoreCase(""))&& i<10){
			Thread.sleep(1000);
			i++;
			StudentName = driver.findElements(getPersonalInfo).get(0).getText();
		}
		return StudentName;
	}
	public String getAdmissionNo(){
		GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(getPersonalInfo).get(1));
		String AdmissionNo = driver.findElements(getPersonalInfo).get(1).getText();
		return AdmissionNo;
	}
	public String getEmail(){
		String Email = driver.findElements(getPersonalInfo).get(2).getText();
		return Email;
	}
	public String getAdminEmail(){
		String Email = driver.findElements(getPersonalInfo).get(1).getText();
		return Email;
	}
	public String getRollNo(){
		String RollNo = driver.findElements(getPersonalInfo).get(3).getText();
		return RollNo;
	}
	public String getGender(){
		String Gender = driver.findElements(getPersonalInfo).get(4).getText();
		return Gender;
	}
	public String getClassName() throws InterruptedException{
		Thread.sleep(2000);
		String ClassName = driver.findElements(getClassName).get(0).getText();
		return ClassName;
	}
	public String getDOB(){
		String DOB = driver.findElements(getPersonalInfo).get(5).getText();
		return DOB;
	}
	public String getMobile(){
		String Mobile = driver.findElements(getMobileAndClass).get(0).getText();
		return Mobile;
	}
	public String getClassAndSection(){
		String ClassAndSection = driver.findElements(getMobileAndClass).get(1).getText();
		return ClassAndSection;
	}
	public String getLoginID(){
		String LoginID = driver.findElements(getMobileAndClass).get(2).getText();
		return LoginID;
	}
	public void Logout(){
		WebElement element = driver.findElement(By.xpath(".//*[@id='book-store']/header/div/div[3]/div[1]/div/ul/li/div/button"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		driver.findElement(logout).click();	
	}
}