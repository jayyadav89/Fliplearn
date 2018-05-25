package testcases;

import java.io.IOException;
import java.sql.SQLException;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.jcraft.jsch.JSchException;
import pageModules.LoginPage;
import pageModules.Profile;
import utils.LoadEnvProperty;
import utils.Xls_Reader;

public class ProfileTestCases extends DriverSession{

	LoginPage login;
	GenericFunctions generic;
	WebDriverWait wait;
	Xls_Reader xls;
	WallActions wall;
	UtilityTest util;
	LoadEnvProperty envpro = new LoadEnvProperty();
	Profile profile;

	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		wait=new WebDriverWait(driver, 25);
		driver.get(BASE_URL);
		login=new LoginPage(driver);
		wall = new WallActions(driver);
		profile=new Profile(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.Login_link));
		generic.waitPageGotLoad();
		login.ClickOnLogin_link();
		Thread.sleep(2000);
	}

	@Test(priority=1)
	public void PRO_001() throws Exception {
		login.loginInToApplication(UtilityTest.getUserIDByRole("Student1"),UtilityTest.getUserPasswordByRole("Student1"));
		generic.waitPageGotLoad();
		WallActions.clickOnMyProfile();
		profile.clickOnPersonalInfo_Editbtn();
		String result="",Sheetname="Profile",Expected;
		int i,rowCount=xls.getRowCount(Sheetname);
		String StudentName,AdmissionNo,DateOfBirth, RollNo,EmailId,Gender,MobileNo,StudentClass, Section;
		for(i=2;i<rowCount+1;i++){
			Expected=xls.getCellData(Sheetname, "Expected", i);
			StudentName=xls.getCellData(Sheetname, "StudentName", i);
			AdmissionNo=xls.getCellData(Sheetname, "AdmissionNo", i);
			DateOfBirth="2017-10-01";
			RollNo=xls.getCellData(Sheetname, "RollNo", i);
			EmailId=xls.getCellData(Sheetname, "EmailId", i);
			Gender=xls.getCellData(Sheetname, "Gender", i);
			MobileNo=xls.getCellData(Sheetname, "MobileNumber", i);
			StudentClass=xls.getCellData(Sheetname, "Class", i);
			Section=xls.getCellData(Sheetname, "Section", i);
			Thread.sleep(3000);
			Assert.assertEquals(profile.checkStudentNameMandatory(), true);
			profile.fillPersonalInformation(StudentName, AdmissionNo,DateOfBirth , RollNo, EmailId, Gender);
			profile.clickOnSave_btn();
			Thread.sleep(3000);
			Assert.assertEquals(profile.getStudentName(), StudentName, "Student name not matched");
			Assert.assertEquals(profile.getAdmissionNo(), AdmissionNo, "AdmissionNo not matched");
			Assert.assertEquals(profile.getEmail(), EmailId, "EmailId not matched");
			Assert.assertEquals(profile.getRollNo(), RollNo, "RollNo not matched");
			Assert.assertEquals(profile.getGender(), Gender, "Gender not matched");
			profile.fillMobileAndSave(MobileNo);
			Thread.sleep(3000);
			Assert.assertEquals(profile.getMobile(), MobileNo, "Mobile number matched");
			profile.fillSchoolInfo(StudentClass, Section);
			Thread.sleep(4000);
			WallActions.clickOnMyProfile();
			Thread.sleep(2000);
			GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(Profile.getMobileAndClass).get(1));
			String ClassAndSection = StudentClass + "-" + Section;
			Assert.assertEquals(profile.getClassAndSection(), ClassAndSection, "class section matched");
			
		}
		WallActions.Logout();
	}
	/*@Test(priority=2)
	public void validateSchoolInformation() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Student1"),UtilityTest.getUserPasswordByRole("Student1"));
		generic.waitPageGotLoad();
		WallActions.clickOnMyProfile();
		String result="",Sheetname="Profile";
		int i,rowCount=xls.getRowCount(Sheetname);
		String StudentClass, Section, LoginID, OldPassword, NewPassword ;
		for(i=2;i<rowCount+1;i++){
			StudentClass=xls.getCellData(Sheetname, "Class", i);
			Section=xls.getCellData(Sheetname, "Section", i);
			LoginID = xls.getCellData(Sheetname, "LoginID", i);
			OldPassword = xls.getCellData(Sheetname, "OldPassword", i);
			NewPassword= xls.getCellData(Sheetname, "NewPassword", i);
			profile.fillSchoolInfo(StudentClass, Section);
			Thread.sleep(5000);
			WallActions.clickOnMyProfile();
			Thread.sleep(2000);
			String ClassAndSection = StudentClass + "-" + Section;
			Assert.assertEquals(profile.getClassAndSection(), ClassAndSection, "class section matched");
		//	profile.fillLoginID(LoginID);
		//	profile.fillPassword(OldPassword, NewPassword);
		//	driver.navigate().refresh();
			WallActions.Logout();
			login.loginInToApplication(LoginID,NewPassword);
			if(!generic.isElementPresent("Login")){
				Assert.assertTrue(true, "Login succesfully");
			}
		}

	}*/
	@Test(priority=2)
	public void PRO_002() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin1"),UtilityTest.getUserPasswordByRole("Admin1"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		generic.waitPageGotLoad();
		WallActions.clickOnMyProfile();
		profile.clickOnPersonalInfo_Editbtn();
		String Sheetname="Profile";
		int i,rowCount=xls.getRowCount(Sheetname);
		String AdminName,EmailId,Gender,MobileNo ;
		for(i=2;i<rowCount+1;i++){
			AdminName=xls.getCellData(Sheetname, "StudentName", i);
			Gender=xls.getCellData(Sheetname, "Gender", i);
			EmailId = xls.getCellData(Sheetname, "EmailId", i);
			MobileNo=xls.getCellData(Sheetname, "MobileNumber", i);
			Assert.assertEquals(profile.checkAdminNameMandatory(), true);
			profile.fillAdminPersonalInformation(AdminName, EmailId, Gender);
			profile.clickOnSave_btn();
			Thread.sleep(3000);
			Assert.assertEquals(profile.getAdminName(), AdminName, "Admin name matched");
			Assert.assertEquals(profile.getEmail(), EmailId, "EmailId matched");
			Assert.assertEquals(profile.getAdminEmail(), Gender, "Gender matched");
			profile.fillMobileAndSave(MobileNo);
			Thread.sleep(3000);
			Assert.assertEquals(profile.getMobile(), MobileNo, "Mobile number matched");
		}
	}
	@Test(priority=3)
	public void PRO_003() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher1"),UtilityTest.getUserPasswordByRole("Teacher1"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		generic.waitPageGotLoad();
		WallActions.clickOnMyProfile();
		profile.clickOnPersonalInfo_Editbtn();
		String Sheetname="Profile";
		int i,rowCount=xls.getRowCount(Sheetname);
		String TeacherName,EmailId,Gender,MobileNo,ClassTeacher,Class_Section, Subject ;
		for(i=2;i<rowCount+1;i++){
			TeacherName=xls.getCellData(Sheetname, "StudentName", i);
			Gender=xls.getCellData(Sheetname, "Gender", i);
			EmailId = xls.getCellData(Sheetname, "EmailId", i);
			MobileNo=xls.getCellData(Sheetname, "MobileNumber", i);
			ClassTeacher=xls.getCellData(Sheetname, "ClassTeacher", i);
			Class_Section=xls.getCellData(Sheetname, "Class_Sections", i);
			Subject=xls.getCellData(Sheetname, "Subject", i);
			Assert.assertEquals(profile.checkTeacherNameMandatory(), true);
			profile.fillTeacherPersonalInformation(TeacherName, EmailId, Gender);
			profile.clickOnSave_btn();
			Thread.sleep(3000);
			Assert.assertEquals(profile.getAdminName(), TeacherName, "Teacher name matched");
			Assert.assertEquals(profile.getEmail(), EmailId, "EmailId matched");
			Assert.assertEquals(profile.getAdminEmail(), Gender, "Gender matched");
			profile.fillMobileAndSave(MobileNo);
			Thread.sleep(3000);
			Assert.assertEquals(profile.getMobile(), MobileNo, "Mobile number matched");
			/*profile.scrollInModelDialogToSubjectEditBtn();
			profile.SelectClassTeacherAndSave();
			Assert.assertEquals(profile.getClassName(), ClassTeacher, "class Teacher matched");
			profile.SelectClass_sectionAndSave();
			Assert.assertEquals(profile.getClassName(), Class_Section, "class and section matched");
			profile.SelectSubjectAndSave();
			Assert.assertEquals(profile.getClassName(), Subject, "class and section matched");*/
		}
	}

}
