package pageModules;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Xls_Reader;

public class InviteUser1 {
	WebDriver driver;
	Xls_Reader xls;
	GenericFunctions generic;
	public InviteUser1(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By manage_lnk=By.xpath("//a[@class='dropbtn']");
	public static By inviteUser_lnk=By.xpath("//a[@href='/home/school/invite-users']");
	public static By teacher=By.xpath("//a[text()='Teachers']");
	public static By mobileNo_Text1=By.xpath("//input[@name='Tmobile1']");
	public static By sendSms=By.xpath("//button[@data-ng-click='inviteUser(TinviteUserFrm)']");
	public static By actualText=By.xpath("//div[@data-ng-message='required']");
	public static By okButton=By.xpath("//button[text()='Ok']");
	public static By mobileNo_Text2=By.xpath("//input[@name='Tmobile2']");
	public static By mobileNo_Text3=By.xpath("//input[@name='Tmobile3']");
	public static By mobileNo_Text4=By.xpath("//input[@name='Tmobile4']");	
	public static By success_MSg=By.xpath("//h4[contains(text(),'School Invite code has been sent')]");
	public static By error_Msg=By.xpath("//div[@class='ng-active']");
	public static By admin_Btn=By.xpath("//a[text()='Admin']");
	public static By mobileText1=By.xpath("//input[@name='Amobile1']");
	public static By sendSms_a=By.xpath("//button[@data-ng-click='inviteUser(AinviteUserFrm)']");
	public static By mobileText2=By.xpath("//input[@name='Amobile2']");
	public static By mobileText3=By.xpath("//input[@name='Amobile3']");
	public static By mobiletext4=By.xpath("//input[@name='Amobile4']");


	public void clickOnInvite_Btn() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, manage_lnk);
		generic.mouseHover(manage_lnk, 1);
		Thread.sleep(2000);
		driver.findElement(inviteUser_lnk).click();
	}
	public void clickOnTeacher_Btn(){
		driver.findElement(teacher).click();
	}
	public void enterMobileText1(String mob1){
		driver.findElement(mobileNo_Text1).clear();
		driver.findElement(mobileNo_Text1).sendKeys(mob1);
	}
	public void enterMobileText2(String mob2){
		driver.findElement(mobileNo_Text2).clear();
		driver.findElement(mobileNo_Text2).sendKeys(mob2);
	}
	public void enterMobileText3(String mob3){
		driver.findElement(mobileNo_Text3).clear();
		driver.findElement(mobileNo_Text3).sendKeys(mob3);
	}
	public void enterMobileText4(String mob4){
		driver.findElement(mobileNo_Text4).clear();
		driver.findElement(mobileNo_Text4).sendKeys(mob4);
	}
	public void clickOnSendSms(){
		driver.findElements(sendSms).get(0).click();
	}
	
	//For Admin
	
	public void clickOnAdmin_btn(){
		driver.findElement(admin_Btn);
	}
	public void enterMobileText1ForAdmin(String mob1){

		driver.findElement(mobileText1).clear();
		driver.findElement(mobileText1).sendKeys(mob1);
	}
	public void enterMobileText2ForAdmin(String mob2){
		driver.findElement(mobileText2).clear();
		driver.findElement(mobileText2).sendKeys(mob2);
	}
	public void enterMobileText3Foradmin(String mob3){
		driver.findElement(mobileText3).clear();
		driver.findElement(mobileText3).sendKeys(mob3);
	}
	public void enterMobileText4ForAdmin(String mob4){
		driver.findElement(mobiletext4).clear();
		driver.findElement(mobiletext4).sendKeys(mob4);
	}
	public void clickOnSendSmsForAdmin(){
		driver.findElements(sendSms_a).get(0).click();
	}
	public String getSuccessMsg(){
		return driver.findElement(success_MSg).getText();
	}
	public String getErrorMsg(){
		return driver.findElements(error_Msg).get(0).getText();
	}
	public void clickOnOkBtn(){
		driver.findElement(okButton).click();
	}
	public boolean isErrorMsgAppear(String mob1, String errorMsg){
		enterMobileText1(mob1);
		generic.scrollPageToBottom();
		clickOnSendSms();
		if(getErrorMsg().equals(errorMsg)){
			return true;
		}else{
			return false;
		}
	}
	public void sendSingleUserInvitation(String mob1) throws InterruptedException{
		enterMobileText1(mob1);
		Thread.sleep(2000);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		clickOnSendSms();
	}
	public void sendDoubleUserInvitation(String mob1,String mob2) throws InterruptedException{
		enterMobileText1(mob1);
		enterMobileText2(mob2);
		Thread.sleep(2000);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		clickOnSendSms();
	}
	public void sendThreeUserInvitation(String mob1, String mob2, String mob3) throws InterruptedException{
		enterMobileText1(mob1);
		enterMobileText2(mob2);
		enterMobileText3(mob3);
		Thread.sleep(2000);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		clickOnSendSms();
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
