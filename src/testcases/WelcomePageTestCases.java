/**
 * 
 */
package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

import helper.DriverSession;
import helper.GenericFunctions;
import pageModules.LoginPage;
import pageModules.WelcomePage;

/**
 * @author shubham verma
 *
 */
public class WelcomePageTestCases extends DriverSession {

	GenericFunctions generic;
	WelcomePage welcomepage;
	WebDriverWait wait;

	/**
	 * 
	 */
	@BeforeMethod
	public void OpenWelcomePage() {
		generic = new GenericFunctions(driver);
		welcomepage = new WelcomePage(driver);
		wait = new WebDriverWait(driver, 10000);

		try {
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.get("https://www.fliplearn.com");
		} catch (Exception e) {
		
			System.out.println("");

		}

		System.out.println("web application is open ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(WelcomePage.facebook_btn));
	}

	@Test(priority = 1)
	public void WPTC_01() throws InterruptedException {
		System.out.println("click on Flilearn facebook Page link");
		welcomepage.facebookMediaClickBtn();
		generic.waitPageGotLoad();
	}

	@Test(priority = 2)
	public void WPTC_02() throws InterruptedException {
		System.out.println("click on Fliplearn linkdin Page link");
		welcomepage.linkdinMediaClickBtn();
		generic.waitPageGotLoad();

	}

	@Test(priority = 3)
	public void WPTC_03() throws InterruptedException {
		System.out.println("click on Fliplearn youtube Page link");
		welcomepage.youtubeMediaClickBtn();
		generic.waitPageGotLoad();
	}

	// Header Button test case
	@Test(priority = 4)
	public void WPTC_04() throws InterruptedException {
		System.out.println("click on home button");
		generic.waitPageGotLoad();
		welcomepage.homeButtonClick();
		generic.waitPageGotLoad();
		generic.scrollPageToBottom();

	}

	// Please add more test case in this if in future please resome this comment
	@Test(priority = 5)
	public void WPTC_05() throws InterruptedException {
		System.out.println("click on Our Product button");
		welcomepage.ourProductButton();

	}

	@Test(priority = 6)
	public void WPTC_06() throws InterruptedException {
		System.out.println("click on Career button");
		welcomepage.careerButton();
		generic.waitPageGotLoad();
		generic.scrollPageToBottom();

	}

	@Test(priority = 7)
	public void WPTC_07() throws InterruptedException {
		System.out.println("click on Contact up button");
		welcomepage.contactButton(); 
		generic.waitPageGotLoad();
		generic.scrollPageToBottom();

	}

	@Test(priority = 8)
	public void WPTC_08() throws InterruptedException {
		System.out.println("Android application download link");
		welcomepage.AndoridApplicitonDownload();
		generic.waitPageGotLoad();

	}

	@Test(priority = 9)
	public void WPTC_09() throws InterruptedException {
		System.out.println("IOS application download link");
		welcomepage.IOSApplicitonDownload();
		generic.waitPageGotLoad();

	}

	@Test(priority = 10)
	public void WPTC_10() throws InterruptedException {
		System.out.println("student read more");
		welcomepage.SchoolApplicationProductDetail();
		generic.waitPageGotLoad();

	}

	@Test(priority = 11)
	public void WPTC_11() throws InterruptedException {
		System.out.println("student parent application producat details");
		welcomepage.StudenParentApplicationProductDetail();
		generic.waitPageGotLoad();
	}

	@Test(priority = 12)
	public void WPTC_12() throws InterruptedException {
		System.out.println("student read more");
		welcomepage.studentReadMore();
		generic.waitPageGotLoad();
	}

	@Test(priority = 13)
	public void WPTC_13() throws InterruptedException {
		System.out.println("parent read more");
		welcomepage.parentReadMore();
		generic.waitPageGotLoad();
	}

	@Test(priority = 14)
	public void WPTC_14() throws InterruptedException {
		System.out.println("read more for all product");
		welcomepage.fliplearnSmartSchoolReadmore();
		driver.navigate().back();
		welcomepage.fliplearnPrimeReadmore();
		driver.navigate().back();
		welcomepage.fliplearnFlipstoreReadmore();
		driver.navigate().back();
		welcomepage.fliplearnVidyamandirReadmore();
	}

	@Test(priority = 15)
	public void WPTC_15() throws InterruptedException {
		System.out.println("this Automation for school speek");
		welcomepage.schoolspeek();
		
	}
	
	//Negative test case
	@Test(priority = 16)
	public void WPTC_16() throws InterruptedException {
		System.out.println("this Automation for school speek");
		welcomepage.quickConnectFarm("","FliplearnQATestingDepartment@Fliplearn.com","This message from QA side dont take as a query","9123456789");
	}
	//Negative test case
	@Test(priority = 17)
	public void WPTC_17() throws InterruptedException {
		System.out.println("this Automation for school speek");
		welcomepage.quickConnectFarm("Fliplearn QA Team","","This message from QA side dont take as a query","9123456789");
	}
	//Negative Test case
	@Test(priority = 18)
	public void WPTC_18() throws InterruptedException {
		System.out.println("this Automation for school speek");
		welcomepage.quickConnectFarm("Fliplearn QA Team","FliplearnQATestingDepartment@Fliplearn.com","This message from QA side dont take as a query","");
	}
	@Test(priority = 19)
	public void WPTC_19() throws InterruptedException {
		System.out.println("this Automation for school speek");
		welcomepage.quickConnectFarm("Fliplearn QA Team","FliplearnQATestingDepartment@Fliplearn.com","This message from fliplearn QA side don't take as a query","9123456789");
	}
}
