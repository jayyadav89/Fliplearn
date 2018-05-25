package pageModules;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Xls_Reader;

public class Registrations {

	static WebDriver driver;
	Xls_Reader xls;
	String sheetName="InviteCode",colName="Code";
	GenericFunctions generic;

	public Registrations(WebDriver driver){
		BookStore.driver=driver;
		generic = new GenericFunctions(driver);
		xls=new Xls_Reader();
	}
	public static By registration_link=By.xpath("//a[text()='Register']");
	public static By registration_Rd=By.xpath("//label[@class='css-label radGroup2']");
	public static By confirm_Btn=By.xpath("//button[contains(@class,'btn btn')]");
	public static By schoolcode_Text=By.xpath("//input[@id='enterSchoolCode']");









	public void enterSchoolCode_Text(){
		String name =xls.getCellData(sheetName, colName, 2);	
		driver.findElement(schoolcode_Text).sendKeys("");
	}		



	public void clickOnConfirm_Btn(){
		driver.findElement(confirm_Btn).click();
	}
	public void selectregistration_Rd(int index){
		driver.findElements(registration_Rd).get(index).click();
	}
	public void clickOnRegistration_Lnk(){
		driver.findElement(registration_link).click();
	}




}
