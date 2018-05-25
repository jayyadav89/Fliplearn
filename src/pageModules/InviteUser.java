package pageModules;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Xls_Reader;

public class InviteUser {
	Xls_Reader xls;
	WebDriver driver;
	GenericFunctions generic;
	public InviteUser(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By manage_lnk=By.xpath("//a[@class='dropbtn']");
	public static By inviteUser_lnk=By.xpath("//a[@href='/home/school/invite-users']");
	public static By studentParent_lnk=By.xpath("//li[@id='stu-par']");
	public static By teacher_lnk=By.xpath("//li[@id='tea']");
	public static By admin_lnk=By.xpath("//li[@id='admin']");
	public static By principle_lnk=By.xpath("//li[@id='prin']");
	public static By mobileNo1_Text = By.id("Smobile1");
	public static By mobileNo2_Text = By.id("Smobile2");
	public static By mobileNo3_Text = By.id("Smobile3");
	public static By send_Btn=By.xpath("//button[@class='btn btn-primary padding-10-30 m-t-5 m-b-15']");
	public static By success_MSg=By.xpath("//h4[contains(text(),'School Invite code has been sent')]");
	public static By ok_Btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-5 m-b-15']");
	public static By error_Msg=By.xpath("//div[@class='ng-active']");
	
	public void clickOnInvite_Link() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, manage_lnk);
		generic.mouseHover(manage_lnk, 1);
		Thread.sleep(2000);
		driver.findElement(inviteUser_lnk).click();
	}
	public void clickonStudentParent_link(){
		driver.findElement(studentParent_lnk).click();
	}
	public void enterMobileNumber1(String mob1){
		driver.findElement(mobileNo1_Text).clear();
		driver.findElement(mobileNo1_Text).sendKeys(mob1);
	}
	public void enterMobileNumber2(String mob2){
		driver.findElement(mobileNo2_Text).clear();
		driver.findElement(mobileNo2_Text).sendKeys(mob2);
	}
	public void enterMobileNumber3(String mob3){
		driver.findElement(mobileNo3_Text).clear();
		driver.findElement(mobileNo3_Text).sendKeys(mob3);
	}
	public void clickonSendSMS_btn(){
		driver.findElements(send_Btn).get(0).click();
	}
	public String getSuccessMsg(){
		return driver.findElement(success_MSg).getText();
	}
	public String getErrorMsg(){
		return driver.findElements(error_Msg).get(0).getText();
	}
	public void clickonOK_btn(){
		driver.findElement(ok_Btn).click();
	}
	public boolean isErrorMsgAppear(String mob1, String errorMsg){
		enterMobileNumber1(mob1);
		generic.scrollPageToBottom();
		clickonSendSMS_btn();
		if(getErrorMsg().equals(errorMsg)){
			return true;
		}else{
		return false;
		}
	}
	public void sendSingleUserInvitation(String mob1) throws InterruptedException{
		enterMobileNumber1(mob1);
		generic.scrollPageToBottom();
		Thread.sleep(10000);
		clickonSendSMS_btn();
	}
	public void sendDoubleUserInvitation(String mob1,String mob2) throws InterruptedException{
		enterMobileNumber1(mob1);
		enterMobileNumber2(mob2);
		generic.scrollPageToBottom();
		Thread.sleep(10000);
		clickonSendSMS_btn();
	}
	public void sendThreeUserInvitation(String mob1, String mob2, String mob3) throws InterruptedException{
		enterMobileNumber1(mob1);
		enterMobileNumber2(mob2);
		enterMobileNumber3(mob3);
		generic.scrollPageToBottom();
		Thread.sleep(10000);
		clickonSendSMS_btn();
	}
	public boolean isSuccessMsgAppear(String SuccessMsg) throws InterruptedException{
		Thread.sleep(2000);
		if(getSuccessMsg().equals(SuccessMsg)){
			return true;
		}else{
			return false;	
		}
	}
	
}
