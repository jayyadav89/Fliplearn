package testcases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;

import pageModules.LoginPage;
import pageModules.UserRegistraionPage;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;

public class UserRegistrationTestCase extends DriverSession {
	
	LoginPage login;
	Xls_Reader xls;
	int i;
	String excelValue;
	GenericFunctions generic;
	WallActions wall;
	HashMap<String, String> inviteCodes;
	HashMap<String, String> roleType;
	UserRegistraionPage userReg;
	WebDriverWait wait;

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		wait=new WebDriverWait(driver, 60);
		userReg = new UserRegistraionPage(driver);
		driver.get(BASE_URL);
		generic=new GenericFunctions(driver);
		Thread.sleep(2000);
		driver.navigate().refresh();
		xls=new Xls_Reader();
		wall = new WallActions(driver);
		login=new LoginPage(driver);
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
	}

	@Test(priority=1)
	public void SchoolRegistration() throws NumberFormatException, Exception{
		
		login.loginInToApplication(UtilityTest.getUserIDByRole("SupAdmin"),UtilityTest.getUserPasswordByRole("SupAdmin"));
		//login.loginInToApplication("qasuperadmin", "qateam@123");  //int3 login
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectAdminProfile();
		}
		userReg.navigateToSchoolCreate();
		userReg.School_Create();
		inviteCodes = userReg.getInviteCodes();
		userReg.setInviteCodesInExcelFile(inviteCodes);
		roleType=userReg.getRoleType();
		userReg.users_logout();

		userReg.userRegistrationAndCreateLoginId(inviteCodes.get("SAD"),roleType.get("SAD"),
				Integer.parseInt(Admin_Count));
		userReg.userRegistrationAndCreateLoginId(inviteCodes.get("STU"),roleType.get("STU"),
				Integer.parseInt(Student_Count));
		userReg.userRegistrationAndCreateLoginId(inviteCodes.get("PAR"),roleType.get("PAR"),
				Integer.parseInt(Parent_Count));
		userReg.userRegistrationAndCreateLoginId(inviteCodes.get("PRI"),roleType.get("PRI"),
				Integer.parseInt(Princi_Count));
		userReg.userRegistrationAndCreateLoginId(inviteCodes.get("TEA"),roleType.get("TEA"),
				Integer.parseInt(Tea_Count));
	}
}