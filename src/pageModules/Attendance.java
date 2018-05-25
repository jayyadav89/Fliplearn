package pageModules;
import java.util.List;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Attendance {
	WebDriver driver;
	GenericFunctions generic;
	
	public Attendance(WebDriver driver){
		this.driver = driver;
		generic = new GenericFunctions(driver);
	}
	
	public static By attendance_lnk=By.id("attendance-icon");
	public static By class_drpdwn = By.xpath("//button[@class='btn btn-primary dropdown-toggle class-dropdown dropdowntext ng-binding']");
	public static By classSection = By.xpath("//a[text()='Pre-Nursery A']");
	public static By presentMark = By.xpath("//tr//td//span//div[@class='ng-binding ng-scope presentDay']");
	public static By present = By.xpath("(//tr//td//div[@class='col-xs-3 presentColor text-center'])[1]");
	public static By absent = By.xpath("(//tr//td//div[@class='col-xs-3 absentColor text-center'])[2]");
	public static By halfday = By.xpath("(//tr//td//div[@class='col-xs-3 halfColor text-center'])[3]");
	public static By leave = By.xpath("(//tr//td//div[@class='col-xs-3 leaveColor text-center'])[4]");
	public static By edit_attendance_btn = By.xpath("//button[@class='btn btn-primary btn-style ng-binding ng-scope']"); 
	public static By saveAndSendSMS_btn = By.id("confirm-button ");
	public static By currentdate_text = By.xpath("//td[@class='monthview-dateCell ng-scope text-center monthview-current monthview-selected']/preceding-sibling::td");
	public static By currentdate_status = By.xpath("//td[@class='monthview-dateCell ng-scope text-center monthview-selected']/following-sibling::td");
	
	
	public void clickOnAttendancelink(){
		GenericFunctions.WaitFor_visibility(driver, attendance_lnk);
		driver.findElement(attendance_lnk).click();
	}
	public void selectClass_student(String class_section){
		GenericFunctions.WaitFor_visibility(driver, class_drpdwn);
		driver.findElement(class_drpdwn).click();
		driver.findElement(By.xpath("//*[@data-value='"+class_section.trim()+"']")).click();
		
		
	}
	public void markAttendance() throws InterruptedException{
		List<WebElement> presentMark= driver.findElements(By.xpath("//tr//td//span//div[@class='ng-binding ng-scope presentDay']"));
		for(int i=0;i<presentMark.size();i++){
			if(i==4){
				break;
			}
			Thread.sleep(1000);
			presentMark.get(i).click();
			Thread.sleep(2000);
			if(i==0){
				driver.findElement(present).click();
			}else if(i==1){
				driver.findElement(absent).click();
			}else if(i==2){
				driver.findElement(halfday).click();
			}else if(i==3){
				driver.findElement(leave).click();
			}
						
		}	
	}
	public void clickOnEditAttendance(){
		GenericFunctions.WaitFor_visibility(driver, edit_attendance_btn);
		driver.findElement(edit_attendance_btn).click();
	}
	public void clickOnSaveAndSendSMS(){
		GenericFunctions.WaitFor_visibility(driver, saveAndSendSMS_btn);
		driver.findElement(saveAndSendSMS_btn).click();
	}
	public String getAttendanceStatusFirstRow() throws InterruptedException{
		Thread.sleep(1000);
		String attendanceStatusFirstRow = driver.findElement(By.xpath("(//tr//td[9]//span//div)[1]")).getText();
		return attendanceStatusFirstRow;	
	}
	public String getAttendanceStatusSecondRow() throws InterruptedException{
		Thread.sleep(1000);
		String attendanceStatusSecondRow = driver.findElement(By.xpath("(//tr//td[9]//span//div)[2]")).getText();
		return attendanceStatusSecondRow;	
	}
	public String getAttendanceStatusThirdRow() throws InterruptedException{
		Thread.sleep(1000);
		String attendanceStatusThirdRow = driver.findElement(By.xpath("(//tr//td[9]//span//div)[3]")).getText();
		return attendanceStatusThirdRow;	
	}
	public String getAttendanceStatusFourthRow() throws InterruptedException{
		Thread.sleep(1000);
		String attendanceStatusFourthRow = driver.findElement(By.xpath("(//tr//td[9]//span//div)[4]")).getText();
		return attendanceStatusFourthRow;	
	}
	public String getCurrentAttendanceStatus() throws InterruptedException{
		Thread.sleep(5000);
		String Status="";
		String Currentdate=driver.findElement(By.xpath("//td[@class='monthview-dateCell ng-scope text-center monthview-current monthview-selected']")).getText();
		System.out.println(Currentdate);
		if(Currentdate.equalsIgnoreCase("01")){
			driver.findElement(By.xpath("(//td[contains(@class,'monthview-dateCell')])[2]/div")).click();	
		}else{
			driver.findElement(By.xpath("(//td[contains(@class,'monthview-dateCell')])[5]/div")).click();
		}		
		Thread.sleep(2000);		
		String attribute = driver.findElement(By.xpath("(//td//div[@class='ng-binding'])["+Currentdate+"]/..")).getAttribute("class");
	//	System.out.println("Attribute: "+attribute);
		if(attribute.contains("absent")){
			Status="absent";
		}else if(attribute.contains("halfDay")){
			Status= "halfDay";
		}else if(attribute.contains("leave")){
			Status= "leave";
		}else if(attribute.contains("present")){
			Status="present";
		}
		return Status;
	}
	

}
