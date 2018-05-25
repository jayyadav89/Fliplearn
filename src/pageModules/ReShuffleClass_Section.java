package pageModules;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Xls_Reader;

public class ReShuffleClass_Section {
	WebDriver driver;
	LoginPage login;
	Xls_Reader xls;
	GenericFunctions generic;
	public ReShuffleClass_Section(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By manage_lnk=By.xpath("//a[@class='dropbtn']");
	public static By reshuffleClass_lnk=By.xpath("//a[@href='/home/manage/reshuffle']");
	public static By class_Drpdwn=By.xpath("//div[@class='col-sm-4']/select");
	public static By section_Drpdwn=By.xpath("//div[@class='form-group']/select");
	public static By go_btn=By.xpath("//button[@class='btn btn-primary padding-10-22']");
	public static By reshuffledSection_RdBtn=By.xpath("//div[@class='radiobutton']");
	public static By submit_Btn=By.xpath("//a[@class='btn btn-primary admin-proceed m-t-10 pull-right']");
	public static By confirm_Btn=By.xpath("//div[@class='col-sm-12 m-t-10 text-center submitpadding ng-scope']");
	public static By success_msg=By.xpath("//h3[@class='ng-binding']");
	public static By student_name=By.xpath("//td[@class='ng-binding']");
	public static By admissionNumber=By.xpath("//td[@class='border-table ng-binding']");
	public static By noRecord_msg=By.xpath("//div[@class='popup-content']/span");
	
	public void clickOnReshuffleLink() throws InterruptedException{
		generic.mouseHover(manage_lnk, 1);
		Thread.sleep(2000);
		driver.findElement(reshuffleClass_lnk).click();
	}
	public void selectClass(String value){
		generic.selectFromDropDownWithValue(class_Drpdwn, value);
	}
	public void selectSection(String value) throws InterruptedException{
		Thread.sleep(1500);
		generic.selectFromDropDownWithValue(section_Drpdwn, value);
	}
	public void clickOnGoButton(){
		GenericFunctions.WaitFor_visibility(driver, go_btn);
		GenericFunctions.WaitFor_clickable(driver, driver.findElement(go_btn));
		driver.findElement(go_btn).click();
	}
	public void SelectClassAndSection(String studentClass,String studentSection) throws InterruptedException{
		selectClass(studentClass);
		selectSection(studentSection);
		clickOnGoButton();
	}
	public void selectReshfulledSection(String section){
		if(section.equals("A")){
			driver.findElements(reshuffledSection_RdBtn).get(0).click();
		}
		else if(section.equals("B")){
			driver.findElements(reshuffledSection_RdBtn).get(1).click();
		}
		else if(section.equals("C")){
			driver.findElements(reshuffledSection_RdBtn).get(2).click();
		}
	}
	public void clickOnSubmitButton(){
		generic.scrollPage(submit_Btn);
		driver.findElement(submit_Btn).click();
	}
	public void clickOnConfirmButton() throws InterruptedException{
		Thread.sleep(1500);
		driver.findElement(confirm_Btn).click();
	}
	public String getSuccessMsg() throws InterruptedException{
		Thread.sleep(1500);
		return driver.findElement(success_msg).getText();
	}
	public boolean isSuccessMsgMatchWithExpected(String studentclass, String studentSection) throws InterruptedException{
		if(getSuccessMsg().equalsIgnoreCase("Reshuffling for "+ studentclass +"-"+studentSection+" has been done")){
			return true;
		}
		return false;
	}
	public String getStudentname(){
		return driver.findElements(student_name).get(0).getText();
	}
	public String getAdmissionNumber(){
			return driver.findElements(admissionNumber).get(0).getText();	
	}
	public String getNoRecordMessage(){
		try{
			System.out.println("Error message:"+driver.findElement(noRecord_msg).getText());
			return driver.findElement(noRecord_msg).getText();
		}catch(Exception e){
			return "";
		}
	}	
	public boolean isStudentNameExistAfterReshuffle(String StudentName) throws InterruptedException{
		Thread.sleep(1000);
		if(generic.isElementPresent("Records are not present for this Page Number.")){
			return true;
		}else if(!getStudentname().equals(StudentName)){
			return true;
		}else{
			return false;
		}	
	}
	public boolean isStudentAdmissionExistAfterReshuffle(String AdmissionNumber) throws InterruptedException{
		Thread.sleep(1000);
		if(generic.isElementPresent("Records are not present for this Page Number.")){
			return true;
		}else if(!getAdmissionNumber().equals(AdmissionNumber)){
			return true;
		}else{
			return false;
		}
		
	}
	
	
}
