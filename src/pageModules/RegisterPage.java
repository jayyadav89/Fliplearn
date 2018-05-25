package pageModules;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Xls_Reader;

import com.jcraft.jsch.JSchException;

public class RegisterPage {
	GenericFunctions generic;
	WebDriver driver;
	
	HomeWork homework;
	Xls_Reader xls=new Xls_Reader();
	LoginPage login;
	WebDriverWait wait;
	
	public static By Register_btn=By.xpath("//button[@class='btn btn-submit']");
    public static By Registerschlink=By.xpath("//a[@href='/onboarding/register-school']");
    public static By Field=By.xpath(".//*[@id='name']");
    public static By Mobile=By.xpath(".//*[@id='mobile']");
    public static By Email=By.xpath(".//*[@id='email']");
    public static By School=By.xpath(".//*[@id='schoolName']");
    public static By Address=By.xpath(".//*[@id='address']");
    public static By Designation=By.xpath(".//*[@id='designation']");
    public static By Submit=By.xpath("//button[@type='submit']");
    public static By LeaveM=By.xpath("//textarea[@name='message']");
    public static By State=By.xpath(".//*[@id='state']");
    public static By City=By.xpath(".//*[@id='sel13 cityId']");
    public static By SuccessM=By.xpath("//p[@class='m-t-10']");
    public static By Rgs_guest_btn=By.xpath("//a[@href='/onboarding/register-guest']");
    public static By F_name=By.xpath(".//*[@id='Fname']");
    public static By L_name=By.xpath(".//*[@id='Lname']");
    public static By Lg_Name=By.xpath(".//*[@id='loginId']");
    public static By calender=By.xpath(".//*[@id='datepicker']");
    public static By Cal_date=By.xpath("html/body/div[5]/div[1]/table/tbody/tr[1]/td[7]");
    public static By Addre=By.xpath(".//*[@id='Address']");
    public static By MobileN=By.xpath(".//*[@id='Mobile']");
    public static By submt=By.xpath("//button[@ng-click='submitInformation(registerForm)']");
    public static By caln=By.xpath("//input[@name='profiledob']");
    public static By State1=By.xpath(".//*[@id='sel12 stateId']");
    public static By City1=By.xpath(".//*[@id='sel13 cityId']");
    public static By EmailL=By.xpath(".//*[@id='email']");
    public static By Entercode=By.xpath("//*[@id='Fname' and @aria-invalid='true']");
    public static By Veri_btn=By.xpath("//button[@ng-click='verfiyOtpCode(verfiyCode)']");
    public static By Proceed_btn=By.xpath(".//*[@id='proceed']");
    public static By OnAccept_btn=By.xpath("//a[@data-ng-click='onAccept()']");
    public static By Pass_field=By.xpath(".//*[@id='password']");
    public static By conf_Pass_field=By.xpath(".//*[@id='Cpassword']");
    public static By Proc_btn=By.xpath("//a[contains(text(),'Proceed')]");
    public static By Get_start=By.xpath("//button[@ng-click='onGetStarted()']");
    public static By UserId_link=By.xpath("//a[contains(text(),'User ID')]");
  public String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
  
	public RegisterPage(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
		
		
	}
	
	public void RegisterSchool() throws InterruptedException{
		
		driver.findElement(Register_btn).click();
		String Rgslink=driver.findElement(Registerschlink).getText();
		if(Rgslink.equalsIgnoreCase("Register your school")){
			driver.findElement(Registerschlink).click();
			EnterText(Field,"Mukesh");
			EnterText(Mobile,"8447271671");
			EnterText(Email,"mukesh@gmail.com");
			EnterText(School,"DPS School");
			EnterText(Address,"Mall Road");
			SelectState(State,3);
			Thread.sleep(1000);
			SelectCity(City,3);
			EnterText(Designation,"DPS School");
			scrollInModelDialog(Submit);
			EnterText(LeaveM, "School of Dps");
			Thread.sleep(1000);
			driver.findElement(Submit).click();
			Thread.sleep(9000);
		}else{
			System.out.println("Register Your School Link not Found");
		}
		
		
		
		
	}
	
	public  void SelectState(By Xpath,int Index) throws InterruptedException{

		driver.findElement(Xpath).click();
		Select Section = new Select(driver.findElement(Xpath));
		Section.selectByIndex(Index);

	}
	
	public  void SelectCity(By Xpath,int Index) throws InterruptedException{

		driver.findElement(Xpath).click();
		Select Section = new Select(driver.findElement(Xpath));
		Section.selectByIndex(Index);
	}
	
	public void EnterText(By Xpath,String value){
		
		driver.findElement(Xpath).sendKeys(value);
	}
	
	public void scrollInModelDialog(By Xpath){
		WebElement element = driver.findElement(submt);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public String SuccessMessage(){
		
		return driver.findElement(SuccessM).getText();
	}
	
	public void  RgsAsGuest() throws Exception{
		
		String Sheetname="Registration";
		int i,rowCount=xls.getRowCount(Sheetname);
		String firstname,lastname,calenderdate,address,mobileno,password,confirmpassword;
		for(i=2;i<rowCount+1;i++){
			firstname=xls.getCellData(Sheetname, "FirstName", i);
			lastname=xls.getCellData(Sheetname, "LastName", i);
			calenderdate=xls.getCellData(Sheetname, "CalenderDate", i);
			address=xls.getCellData(Sheetname, "Address", i);
			mobileno=xls.getCellData(Sheetname, "MobileNumber", i);
			password=xls.getCellData(Sheetname, "Password", i);
			confirmpassword=xls.getCellData(Sheetname, "Cpassword", i);
			driver.findElement(Register_btn).click();
			driver.findElement(Rgs_guest_btn).click();
			driver.findElement(F_name).sendKeys(firstname);
			driver.findElement(L_name).sendKeys(lastname);
			String LoginId="automation"+timeStamp;
			driver.findElement(Lg_Name).sendKeys(LoginId);
			Thread.sleep(1000);
			driver.findElement(caln).click();
			Thread.sleep(1000);
		    driver.findElement(caln).sendKeys(calenderdate);
		    Thread.sleep(1000);
		    driver.findElement(Addre).sendKeys(address);
		    SelectState(State1,3);
			Thread.sleep(1000);
			SelectCity(City1,3);
			scrollInModelDialog(submt);
			driver.findElement(MobileN).sendKeys(mobileno);
			driver.findElement(EmailL).sendKeys(timeStamp+"@gmail.com");
			driver.findElement(submt).click();
			Thread.sleep(9000);
			String OTP=generic.getOTPForForGotPassword(LoginId);
			driver.findElement(Entercode).sendKeys(OTP);
			Thread.sleep(9000);
			driver.findElement(Veri_btn).click();
			Thread.sleep(5000);
			driver.findElement(Proceed_btn).click();
			driver.findElement(OnAccept_btn).click();
			driver.findElement(Pass_field).sendKeys(password);
			driver.findElement(conf_Pass_field).sendKeys(confirmpassword);
			driver.findElement(Proc_btn).click();
			driver.findElement(Get_start).click();
			}
				
}
	
	public boolean RgsAsGuestSuccessMessage(){
		
		
		if(driver.findElement(UserId_link).getText().equalsIgnoreCase("User ID")){
			return true;
		}
			else {
			return false;
			}
		}
	}
	

