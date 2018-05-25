package pageModules;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.Xls_Reader;

public class FeePayment {
	GenericFunctions generic;
	WebDriver driver;
	Xls_Reader xls=new Xls_Reader();

	public FeePayment(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}

	public static By payFee_link = By.xpath("//a[@href='/onboarding/fee-payment/fee']");
	public static By selectSchool = By.xpath("//select[@name='schoolName']");
	public static By selectSchoolError = By.xpath("//div[contains(text(),'Please select your school.')]");
	public static By uniqueStudentNumber = By.xpath("//input[@name='identifierName']");
	public static By studentNumberError = By.xpath("//div[@class='ng-binding ng-scope']");
	public static By WrongstudentNumberError = By.xpath("//div[@class='col-sm-12 component']/h5");	
	public static By proceedButton = By.xpath("//button[@class='btn btn-primary mobilebtnBlock m-t-28 mobileTopBottom']");
	public static By confirmProceed = By.xpath("//button[@class='btn btn-primary padding-10-30 m-t-28 margin-bottom-0']");
	public static By parentName = By.id("parentName");
	public static By parentMobileNumber = By.id("parentMobile");
	public static By parentEmailID = By.id("parentEmail");
	public static By parentAddress = By.id("parentAddress");
	public static By parentState = By.xpath("//select[@tabindex='5']");
	public static By paymentMethod = By.xpath("//div[@class='radiobutton']");
	public static By pay_btn = By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-28 margin-bottom-0 ng-binding']");
	public static By payULogo = By.xpath("//div[@id='payu_logo']");
	public static By Hdfc_BillingPage = By.xpath("//h4[text()='Billing Information']");
	public static By PayWith = By.xpath("//li[text()='Pay with']");
	public static By HDFCBANKDEBIT = By.xpath("//a[@data-bank='hdfcBankDebitCard']");
	//public static By flipLearn_link = By.xpath("//span[@class='merchantUrlCSS']");
	public static By flipLearn_link = By.xpath("//a[text()='www.fliplearn.com']");
	
	public static By orderDetails = By.xpath("//h4[@class='ng-binding']");
	public static By paymentStatus = By.xpath("//h3[@class='margin0 m-t-5']");
	//public static By home_link = By.linkText("Home");
	public static By home_link = By.xpath("//a[text()='Home']");
	

	public void ClickOnPayFee_link() throws InterruptedException{
		GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(payFee_link).get(0));
		driver.findElements(payFee_link).get(0).click();
	}

	public void selectSchool(String input) throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, selectSchool);
		if(input.equals("")){
			Select school=new Select(driver.findElement(selectSchool));
			school.selectByVisibleText("Please select school");
		}else{
			Select school=new Select(driver.findElement(selectSchool));
			school.selectByVisibleText(input);
		}
	}

	public void enterStudentNumber(String input) throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver,uniqueStudentNumber);
		if(input.equals("")){
			driver.findElement(uniqueStudentNumber).clear();
		}else{
			driver.findElement(uniqueStudentNumber).clear();
			driver.findElement(uniqueStudentNumber).sendKeys(input);
		}
	}

	public void ClickOnProceed_btn(){
		driver.findElement(proceedButton).click();
	}

	public String getSchoolDetailsErrorMsg(){
		String ActualError="";
		if(generic.isElementPresent("Please select your school.")){
			ActualError = driver.findElement(selectSchoolError).getText();
		}else if(generic.isPresent(studentNumberError)){
			ActualError = driver.findElement(studentNumberError).getText();
		}else if(generic.isPresent(WrongstudentNumberError)){
			ActualError = driver.findElement(WrongstudentNumberError).getText();
		}
		return ActualError;	
	}

	public void fillSchoolDetails(String schoolName, String StudentNumber ) throws InterruptedException{
		Thread.sleep(1500);
		selectSchool(schoolName);
		if(!schoolName.equals("")){
			enterStudentNumber(StudentNumber);
		}
		ClickOnProceed_btn();
	}

	public boolean checkConfirmDetailsPage(){
		if(driver.findElement(confirmProceed).isDisplayed()){
			return true;
		}
		else{
			return false;
		}
	}

	public void confirmDetails() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver,confirmProceed);
		driver.findElement(confirmProceed).click();
	}

	public void fillParentsDetails(String pname, String pMobile, String pEmail, String pAddress, String pState) throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, parentName);
		driver.findElement(parentName).clear();
		driver.findElement(parentName).sendKeys(pname);
		driver.findElement(parentMobileNumber).clear();
		driver.findElement(parentMobileNumber).sendKeys(pMobile);
		driver.findElement(parentEmailID).clear();
		driver.findElement(parentEmailID).sendKeys(pEmail);
		driver.findElement(parentAddress).clear();
		driver.findElement(parentAddress).sendKeys(pAddress);

		Select state=new Select(driver.findElement(parentState));
		state.selectByVisibleText(pState);
	}
	public void selectPaymentMethod(int input) throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, paymentMethod);
		WebElement element =driver.findElements(paymentMethod).get(input-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		element.click();
	}

	public void ClickOnPay_btn() throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(pay_btn).click();
	}

	public void fillParentDetails_PaymentOptions(String pname, String pMobile, String pEmail, String pAddress, String pState, int payment ) throws InterruptedException{
		fillParentsDetails(pname, pMobile, pEmail, pAddress, pState);
		selectPaymentMethod(payment);
		ClickOnPay_btn();
	}

	public boolean checkPaymentGateway() throws InterruptedException{
		GenericFunctions.WaitFor_visibility(driver, payULogo);
		if(driver.findElement(payULogo).isDisplayed())
		{
			return true;
		}
		else{
			return false;
		}

	}

	public void goBackToFlilearn() throws InterruptedException{
		Thread.sleep(2000);
		//driver.findElement(PayWith).click();
		//Thread.sleep(2000);
		//driver.findElement(HDFCBANKDEBIT).click();
		Thread.sleep(2000);
		GenericFunctions.WaitFor_visibility(driver, flipLearn_link);
		driver.findElement(flipLearn_link).click();
	}
	
	public boolean isHomeLinkDisplay(){
		GenericFunctions.WaitFor_visibility(driver, home_link);
		if(driver.findElement(home_link).isDisplayed()){
			return true;
		}
		return false;
	}
	public String verifyPaymentDone() throws InterruptedException{
		/*String parentWindow = driver.getWindowHandle();
		for(String childWindow : driver.getWindowHandles()){
		 driver.switchTo().window(childWindow);
		}*/
		GenericFunctions.WaitFor_visibility(driver, orderDetails);
		GenericFunctions.WaitFor_visibility(driver, paymentStatus);
		String OrderId=driver.findElements(orderDetails).get(0).getText();
		String PaymentStatus = driver.findElement(paymentStatus).getText();
		return PaymentStatus;

	}

}
