package testcases;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.WallActions;
import pageModules.RoleBaseLogin;
import helper.UtilityTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageModules.LoginPage;
import utils.Xls_Reader;

public class RoleBaseLoginTestCases extends DriverSession {
	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	WallActions wall;
	UtilityTest util;
	RoleBaseLogin roleBaseLogin;

	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		driver.get(BASE_URL);
		roleBaseLogin = new RoleBaseLogin(driver);
		login=new LoginPage(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		wall = new WallActions(driver);
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
	}

	@Test(priority = 1)
	public void RBLT_001() throws Throwable {
		String role = "Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role), UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		if (generic.isElementPresent("Select Profile")) {
			wall.selectAdminProfile();
		}
		Assert.assertEquals(roleBaseLogin.getUserROle(), role, "role does not matched");
		roleBaseLogin.Logout();
	}

	@Test(priority = 2)
	public void RBLT_002() throws Throwable {
		String role = "Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role), UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		generic.waitPageGotLoad();
		Assert.assertEquals(roleBaseLogin.getUserROle(), role, "role does not matched");
		roleBaseLogin.Logout();
	}

	@Test(priority = 3)
	public void RBLT_003() throws Throwable {
		String role = "Student";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role), UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		Assert.assertEquals(roleBaseLogin.getUserROle(), role, "role does not matched");
		roleBaseLogin.Logout();
	}

	@Test(priority = 4)
	public void RBLT_004() throws Throwable {
		String role = "Parent";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role), UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		roleBaseLogin.NumberofRoles();
		Assert.assertEquals(roleBaseLogin.getUserROle(), role, "role does not matched");
		roleBaseLogin.Logout();
	}

	@Test(priority = 5)
	public void RBLT_005() throws Throwable {
		String role = "Principal";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role), UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		Assert.assertEquals(roleBaseLogin.getUserROle(), role, "role does not matched");
		roleBaseLogin.Logout();
	}

	@Test(priority = 6)
	public void RBLT_006() throws Throwable {
		login.loginInToApplication(UtilityTest.getUserIDByRole("Guest"), UtilityTest.getUserPasswordByRole("Guest"));
		generic.waitPageGotLoad();
		if (generic.isElementPresent("Enter Your 10 digit Mobile Number")) {
			login.skipMobileNumber();
		}
		Assert.assertTrue(roleBaseLogin.isloggedInUserAsGuest(), "Logged user is not guest user");
		roleBaseLogin.GuestLogout();
	}

}
