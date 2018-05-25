package pageModules;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jcraft.jsch.JSchException;

import testcases.LoginTestCases;
import utils.DBConnection;
import utils.Xls_Reader;

public class LoginPage{
	GenericFunctions generic;
	WebDriver driver;
	Xls_Reader xls=new Xls_Reader();

	public static By Login_link=By.xpath("//a[@href='/login']");
	public static By login_Register_lnk=By.xpath("//a[contains(@href,'com/login')]");
	public static By Username_Txt=By.id("Fname");
	public static By Password_Txt=By.xpath("//input[@id='password-lg1']");
	public static By Password_Error=By.xpath("//*[@class='ng-scope']//*[text()='Password cannot be left empty']");
	public static By Login_Error=By.xpath("//*[@class='ng-scope']//*[text()='Login ID cannot be empty']");
	public static By Footer_Error=By.xpath("//span[@class='icon-pane']");
	public static By Skip_btn = By.xpath("//button[contains(text(),'Skip')]");
	public static By Proceed_btn=By.xpath("//button[@class='btn btn-primary padding-10-30 m-t-40 margin-bottom-0']");
	public static By Verify_btn=By.xpath("//button[@class='btn btn-primary padding-10-60 mobilebtnBlock']");
	public static By SelectUser_radiobtn=By.xpath("//input[@name='radio']");
	public static By Submit_btn=By.xpath("//button[@class='btn btn-primary paddingLR z-index mobilebtnBlock ng-scope']");
	public static By Forget_SuccessMsg=By.xpath("//p[@class='margin-tb']");
	public static By BackToLogin_btn=By.xpath("//a[@class='btn btn-primary paddingLR z-index mobilebtnBlock']");
	public static By Enter_OTP=By.xpath("//input[@id='enterOTP']");
	public static By ForgetLogin_OTP=By.xpath("//div//input[@id='enterOTP']");
	public static By ForgotUserId_Lnk=By.id("com.elss.educomp:id/userId");
	public static By ForgotPassword_Lnk=By.id("com.elss.educomp:id/password_edit");
	//public static By Login_Btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-20']");
	public static By Login_Btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-20 mobilebtnBlock mobilemargin0']");
	public static By TermsOfUse_Lnk=By.id("com.elss.educomp:id/termsofuse");
	public static By PrivacyPolicy_Lnk=By.id("com.elss.educomp:id/privacypolicy");
	public static By SignUp_Lnk=By.id("com.elss.educomp:id/signup");
	public static By Banner_Bnr=By.id("com.elss.educomp:id/snackbar_text");
	public static By MobileNumber_Txt=By.xpath("//input[@id='mobileno']");
	public static By Next_Btn=By.id("com.elss.educomp:id/nxt_btn");
	public static By UserId_link=By.xpath("//a[contains(text(),'Login ID')]");
	public static By Forget_Mobile_Input=By.xpath("//input[@id='mobile']");
	public static By ForgetP_LoginId_Input=By.xpath("//input[@id='userid']");
	public static By ForgetP_Enter_OTPCode=By.xpath("//input[@id='mobileVerifyCode']");	
	public static By ForgetP_Enter_NewPass=By.xpath("//input[@id='password']");
	public static By ForgetP_Enter_ConfPass=By.xpath("//input[@id='cpassword']");	
//	public static By Forget_Popup_Nxt_Button=By.xpath("//button[@class='btn btn-primary paddingLR z-index btn-style']");
	public static By Forget_Popup_Nxt_Button=By.xpath("//button[@class='btn btn-primary paddingLR z-index mobilebtnBlock']");
	public static By Forget_sub_Button=By.xpath(".//*[@id='btn-send-text']");
	public static By B_t_Login_Btn=By.xpath("//a[contains(text(),'Back to Login')]");
	public static By Multiple_pro_RadiB=By.xpath("//input[@name='radio']");
	public static By Password_link=By.xpath("//a[contains(text(),'Password')]");
	public static By Forget_UserId_Input=By.xpath("//*[@id='Fname' and @aria-invalid='true']");
	public static By Forget_P_Next_btn=By.xpath("//button[@class='btn btn-primary paddingLR z-index mobilebtnBlock']");
	public static By Forget_P_Verify_btn=By.xpath(".//*[@id='forgot-mobile-edit']/div/div/div/div/div[3]/button");
	public static By Reset_P_Done_btn=By.xpath(".//*[@id='reset-password']/div/div/div/div/div[3]/button");
	public static By Termncon_Link=By.xpath("//a[@href='/terms-and-conditions']");
	public static By Termncon_Text=By.xpath("//h4[contains(text(),'Terms & Conditions')]");
	public static By Privacy_Link=By.partialLinkText("Privacy Policy");
	public static By Privacy_Text=By.xpath(".//h1[contains(text(),'Privacy Policy')]");



	public LoginPage(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	
	public void Login_RegisterLink_click() throws InterruptedException {
		generic.waitPageGotLoad();
		driver.findElement(login_Register_lnk).click();;
	}

	public void Fill_Username_Txt(String inputdata){
		GenericFunctions.WaitFor_visibility(driver, Username_Txt);
		if(inputdata.trim().length()==0){
			driver.findElement(Username_Txt).clear();
			return;
		}
		driver.findElement(Username_Txt).clear();
		driver.findElement(Username_Txt).sendKeys(inputdata);
	}

	public void Fill_Password_Txt(String inputdata){
		if(inputdata.trim().length()==0){
			driver.findElement(Password_Txt).clear();
			return;
		}
		driver.findElement(Password_Txt).clear();
		driver.findElement(Password_Txt).sendKeys(inputdata);
	}

	public void Fill_Mobile_Txt(String inputdata) throws InterruptedException {
		if(inputdata.trim().length()==0){
			driver.findElement(MobileNumber_Txt).clear();
			return;
		}
		driver.findElement(MobileNumber_Txt).clear();
		driver.findElement(MobileNumber_Txt).sendKeys(inputdata);
		driver.findElement(Proceed_btn).click();
		driver.findElement(Enter_OTP).sendKeys("123456"); //from db
		Thread.sleep(3000);

	}
	public void ClickOnForgotUserId_Lnk(){
		try{
			driver.findElement(ForgotUserId_Lnk).click();
		}catch(Exception e){
			generic.GoToSleep(2000);
			driver.findElement(ForgotUserId_Lnk).click();
		}
	}

	public void ClickOnForgotPassword_Lnk(){
		try{
			driver.findElement(ForgotPassword_Lnk).click();
		}catch(Exception e){
			generic.GoToSleep(2000);
			driver.findElement(ForgotPassword_Lnk).click();
		}
	}

	public void ClickOnLogin_link(){
		GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(Login_link).get(0));
		GenericFunctions.WaitFor_clickable(driver, driver.findElements(Login_link).get(0));
		driver.findElements(Login_link).get(0).click();
	}

	public void ClickOnLogin_Btn(){
		driver.findElement(Login_Btn).click();;
	}

	public void ClickOnTermsOfUse_Lnk(){
		try{
			driver.findElement(TermsOfUse_Lnk).click();
		}catch(Exception e){
			generic.GoToSleep(2000);
			driver.findElement(TermsOfUse_Lnk).click();
		}
	}

	public void ClickOnPrivacyPolicy_Lnk(){
		try{
			driver.findElement(PrivacyPolicy_Lnk).click();
		}catch(Exception e){
			generic.GoToSleep(2000);
			driver.findElement(PrivacyPolicy_Lnk).click();
		}
	}

	public void ClickOnSignUp_Lnk(){
		try{
			driver.findElement(SignUp_Lnk).click();
		}catch(Exception e){
			generic.GoToSleep(2000);
			driver.findElement(SignUp_Lnk).click();
		}
	}

	public void fill_Mobile_number(String value){
		try {
			driver.findElement(MobileNumber_Txt).sendKeys(value);
		} catch (Exception e) {
			driver.findElement(MobileNumber_Txt).sendKeys(value);
		}
	}

	public void ClickOnNextButton(){
		driver.findElement(Next_Btn).click();
	}

	public void FillLoginForm(String username, String password) throws InterruptedException{
		Fill_Username_Txt(username);
		Fill_Password_Txt(password);
		ClickOnLogin_Btn();

	}
	public String getErrorMessage(String username, String password ) throws InterruptedException{
		String ErrorMsg="";
		if(password.equals("")){
			ErrorMsg=driver.findElement(Password_Error).getText();
		}
		else if(username.equals("")){
			ErrorMsg=driver.findElement(Login_Error).getText();
		}
		
		else{
			Thread.sleep(1000);
			ErrorMsg=driver.findElement(Footer_Error).getText();
		}
		return ErrorMsg;
	}

	/*try{
			if(driver.findElement(By.xpath("//p[contains(text(),'Enter Your 10 digit Mobile Number')]")).isDisplayed()==true){
				Fill_Mobile_Txt(mobile);

			}
		}
		catch(Exception e){
			System.out.println("not found");
		}*/



	public boolean checkForSuccesfullLogin(){
		generic.GoToSleep(3000);
		try {
			if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
				skipMobileNumber();
			}
		} catch (Exception e) {
		}
		if(driver.findElement(By.xpath("//div//h3[contains(text(),'Home')]")).isDisplayed()){
		//if(driver.getPageSource().contains("HOME")){
			//System.out.println("User Logged in now");
			return true;
		}
		else{
			//System.out.println("Login was not succesfull");
			return false;
		}

	}
	public boolean checkForErrorMessage(){
		generic.GoToSleep(1000);
		if(generic.isPresent(Footer_Error) || generic.isPresent(Login_Error) || generic.isPresent(Password_Error)){
		//if(generic.isElementPresent("Fname")){
			return true;
		}else{
			return false;
		}
	}

	public boolean alreadyLoggedIn(){
		try{
			driver.findElement(Username_Txt);
			return false;
		}catch(Exception e){
			return true;
		}
	}
	public void logOutFromApp(){
		if(generic.isElementPresent("com.elss.educomp:id/userId")){
			return;
		}else{

		}
	}


	public void loginInToApplication(String uname, String password) throws InterruptedException{
		Fill_Username_Txt(uname);
		Fill_Password_Txt(password);
		ClickOnLogin_Btn();
	}

	public void skipMobileNumber(){
		GenericFunctions.WaitFor_visibility(driver, Skip_btn);
		WebElement element = driver.findElement(Skip_btn);
		element.click();
	}
	
	public void  ForgetUserId(String mobileNo ) throws Exception{
		GenericFunctions.WaitFor_visibility(driver, UserId_link);
		driver.findElement(UserId_link).click();
		Thread.sleep(500);
		driver.findElement(Forget_Mobile_Input).sendKeys(mobileNo+Keys.TAB);
		driver.findElement(Forget_Popup_Nxt_Button).click();
		driver.findElement(Forget_Mobile_Input).sendKeys(mobileNo);
		driver.findElement(Forget_Popup_Nxt_Button).click();
		Thread.sleep(5000);
		//getOTPForForGotPassword(mobileNo);
		driver.findElement(ForgetLogin_OTP).sendKeys(getOTPForForGotPassword(mobileNo));
		driver.findElement(Verify_btn).click();
		Thread.sleep(2000);
		GenericFunctions.WaitFor_visibility(driver, SelectUser_radiobtn);
		driver.findElements(SelectUser_radiobtn).get(0).click();
		//Thread.sleep(1000);
		generic.waitForLoad(driver);
		generic.scrollPage(Submit_btn);
		driver.findElement(Submit_btn).click();
		Thread.sleep(1000);
	}


	public String GetSuccessMessage_ForgetUserID_Pass(){
		String ForgetUser_SuccesMsg;
		generic.GoToSleep(1000);
		ForgetUser_SuccesMsg = driver.findElement(Forget_SuccessMsg).getText();
		generic.GoToSleep(1000);
		driver.findElement(BackToLogin_btn).click();
		return ForgetUser_SuccesMsg ;
	}




	public void  ForgetPassword(String UserName) throws Exception{
		GenericFunctions.WaitFor_visibility(driver, Password_link);
		driver.findElement(Password_link).click();
		Thread.sleep(1000);
		driver.findElement(ForgetP_LoginId_Input).sendKeys(UserName+Keys.TAB);
		driver.findElement(Forget_P_Next_btn).click();
		Thread.sleep(3000);
		String ForgetOTP=generic.getOTPForForGotPassword(UserName);
		driver.findElement(ForgetP_Enter_OTPCode).sendKeys(ForgetOTP);
		GenericFunctions.WaitFor_visibility(driver, Forget_P_Verify_btn);
		driver.findElement(Forget_P_Verify_btn).click();
		Thread.sleep(1000);
		driver.findElement(ForgetP_Enter_NewPass).sendKeys("123456"+Keys.TAB);
		driver.findElement(ForgetP_Enter_ConfPass).sendKeys("123456");
		driver.findElement(Reset_P_Done_btn).click();

	}

	public boolean ForgetPasswordSuccessMessage(){
		generic.GoToSleep(1000);
		if(driver.findElement(Password_link).getText().equalsIgnoreCase("Password?")){
			return true;
		}else{
			return false;
		}
	}



	public String TermofuseLink() throws Throwable{
		Thread.sleep(2000);
		GenericFunctions.WaitFor_visibility(driver, Username_Txt);
		GenericFunctions.WaitFor_visibility(driver, Termncon_Link);
		driver.findElement(Termncon_Link).click();
		Thread.sleep(2000);
		String ActualText = driver.findElement(Termncon_Text).getText();
		return ActualText;
	}


	public String PrivacyPolicy() throws Throwable{
		GenericFunctions.WaitFor_visibility(driver, Username_Txt);
		driver.findElement(Privacy_Link).click();
		Thread.sleep(2000);
		String ActualText = driver.findElement(Privacy_Text).getText().trim();
		return ActualText;
	}

	public String getOTPForForGotPassword(String mobilenumber) throws Exception{
		String OTP="";
		DBConnection.connectDatabase("ums");
		ResultSet rs2;
		try{
			rs2=DBConnection.executeQuery("SELECT * FROM ums_api.user_verification_code where mobile_no='"+mobilenumber+"' "
					+ " AND verification_for='Mobile' order by updated_date desc limit 1;");
			//System.out.println("SELECT * FROM ums_api.user_verification_code where mobile_no='"+mobilenumber+"' "
			//		+ " verification_for='Mobile' order by updated_date desc limit 1");
			while(rs2.next()){
				OTP=rs2.getString("verification_code").trim();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBConnection.disconnectDBConnection();
		}
		return OTP;
	}
}


