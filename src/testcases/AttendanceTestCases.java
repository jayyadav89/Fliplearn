package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.Attendance;
import pageModules.LoginPage;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;

public class AttendanceTestCases extends DriverSession {
	Attendance attendance;
	WebDriverWait wait;
	LoginPage login;
	GenericFunctions generic;
	Xls_Reader xls;

	@BeforeMethod
	public void OpenFliplearn() {
		xls = new Xls_Reader();
		wait = new WebDriverWait(driver, 60);
		attendance = new Attendance(driver);
		login = new LoginPage(driver);
		generic = new GenericFunctions(driver);
		driver.get(BASE_URL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.Login_link));
		login.ClickOnLogin_link();

	}

	/************************************
	 * Test Case Name : To verify Teacher is able to mark student attendance
	 * 
	 * @author : Rohit Kumar
	 *******************************/
	@Test(priority = 1)
	public void ATDNC_001() throws InterruptedException {
		String role = "Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role), UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		String sheetname = "Attendance";
		String classSection = xls.getCellData(sheetname, "classSection", 2);
		attendance.clickOnAttendancelink();
		attendance.selectClass_student(classSection);
		attendance.markAttendance();
		attendance.clickOnEditAttendance();
		generic.GoToSleep(2000);
		attendance.clickOnSaveAndSendSMS();
		generic.GoToSleep(2000);
		Assert.assertEquals(attendance.getAttendanceStatusFirstRow(), "P", "Present status is not matched");
		Assert.assertEquals(attendance.getAttendanceStatusSecondRow(), "A", "Absent status is not matched");
		Assert.assertEquals(attendance.getAttendanceStatusThirdRow(), "H", "Half day status is not matched");
		Assert.assertEquals(attendance.getAttendanceStatusFourthRow(), "L", "leave status is not matched");

	}

	/************************************
	 * Test Case Name : To verify Student attendance is marked Present.
	 * 
	 * @throws InterruptedException
	 *******************************/
	@Test(priority = 2)
	public void ATDNC_002() throws InterruptedException {

		login.loginInToApplication(UtilityTest.getUserIDByRole("AT_student1"),
				UtilityTest.getUserPasswordByRole("AT_student1"));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		attendance.clickOnAttendancelink();
		Assert.assertEquals(attendance.getCurrentAttendanceStatus(), "present");

	}
	/************************************
	 * Test Case Name : To verify Student attendance is marked Absent.
	 * 
	 * @throws InterruptedException
	 *******************************/
	@Test(priority = 3)
	public void ATDNC_003() throws InterruptedException {
		login.loginInToApplication(UtilityTest.getUserIDByRole("AT_student2"),
				UtilityTest.getUserPasswordByRole("AT_student2"));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		attendance.clickOnAttendancelink();
		Assert.assertEquals(attendance.getCurrentAttendanceStatus(), "absent");
	}
	
	/************************************
	 * Test Case Name : To verify Student attendance is marked HalfDay.
	 * 
	 * @throws InterruptedException
	 *******************************/

	@Test(priority = 4)
	public void ATDNC_004() throws InterruptedException {

		login.loginInToApplication(UtilityTest.getUserIDByRole("AT_student3"),
				UtilityTest.getUserPasswordByRole("AT_student3"));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		attendance.clickOnAttendancelink();
		Assert.assertEquals(attendance.getCurrentAttendanceStatus(), "halfDay");
	}

	/************************************
	 * Test Case Name : To verify Student attendance is marked Leave.
	 * 
	 * @throws InterruptedException
	 *******************************/
	@Test(priority = 5)
	public void ATDNC_005() throws InterruptedException {

		login.loginInToApplication(UtilityTest.getUserIDByRole("AT_student4"),
				UtilityTest.getUserPasswordByRole("AT_student4"));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		attendance.clickOnAttendancelink();
		Assert.assertEquals(attendance.getCurrentAttendanceStatus(), "leave");

	}

}
