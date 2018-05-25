package pageModules;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import helper.GenericFunctions;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DBConnection;
import utils.Xls_Reader;

import com.jcraft.jsch.JSchException;

public class UserRegistraionPage {
	LoginPage login;
	WebDriver driver;
	Xls_Reader xls;
	GenericFunctions generic;
	public UserRegistraionPage(WebDriver driver){
		this.driver=driver;
		xls = new Xls_Reader();
		login = new LoginPage(driver);
		generic = new GenericFunctions(driver);
		
	}

	String mobile = "7011291149";
	String password = "123456";
	String Email_Id = "priyanka@fliplearn.com";
	String STU_PAR, TEA, SAD, PRI;

	//public static By Logout1 =By.xpath("//ul[@class='b-t-1 header-dd']/li/a/img[@src='http://ugcproduction.fliplearn.com/2web-app/build/images/logout_profile.png']");
	//public static By Logout1 =By.xpath("//ul[@class='b-t-1 header-dd']/li/a/img[@src='http://ugc.fliplearn.com/web-app7/build/images/logout_profile.png']");
	public static By Logout1 =By.partialLinkText("Logout");
	public static By Login =By.xpath("//a[@href='/login']");
	public static By School_Name = By.id("schoolName");
	public static By Registration = By.id("registration");
	public static By Address = By.id("schooladdress");
	public static By Board_Name = By.xpath("//select[@id='radiusSelect']");
	public static By Get_City =  By.xpath("//select[@id='sel13 cityId']");
	public static By Get_State = By.xpath("//select[@id='state']");
	public static By Email = By.id("email");
	public static By Mobile_No = By.id("mobile");
	public static By Done =By.xpath("//button[@class='btn btn-primary padding-10-60']");
	public static By Full_Name = By.xpath("//input[@id='username']");
	public static By Login_Id = By.xpath("//input[@id='userid']");
	public static By Create_Password = By.id("password");
	public static By Confirm_Password = By.id("Cpassword");
	public static By Create_Login_Id = By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-30']");
	public static By Join_Fliplearn = By.xpath("//div[@class='login-footerMain']/p[1]/a[1]");
	public static By Confirm = By.xpath("//form[@name='Frm']/div[2]/div[1]/button[1]");
	public static By login_screen = By.xpath("//h3[@class='m-t-20']");
	public static By Fill_Invite_Code = By.xpath("//input[@id='enterSchoolCode']");
	public static By Get_Started = By.xpath("//button[@class='btn btn-primary padding-10-30 m-t-28 mobilet-10 mobileb-10 margin-bottom-0 mobilebtnBlock']");
	public static By Role_Confirm = By.xpath("//button[@class='btn btn-primary padding-10-60']");
	public static By School_Confirm = By.xpath("//button[@class='btn btn-primary padding-10-60 mobilebtnBlock']");
	public static By Enter_Mobile =By.id("mobileno");
	//public static By Get_Otp = By.xpath("//button[@class='btn btn-primary padding-10-30 m-t-40 margin-bottom-0']");
	public static By Get_Otp = By.xpath("//button[@class='btn btn-primary padding-10-30 m-t-40 margin-bottom-0 mobilebtnBlock']");
	public static By Enter_Otp = By.xpath("//input[@id='enterOTP']");
	//public static By Verify = By.xpath("//div[@class='modal-body head_bottom']/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/button[@class='btn btn-primary padding-10-60 m-t-15 m-b-15']");
	public static By Verify = By.xpath("//div[@class='modal-body head_bottom']/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/button[@class='btn btn-primary padding-10-60 mobilebtnBlock']");
	public static By roleSelect_RB = By.xpath("//label[@class='css-label radGroup2']");
	public static By SchoolAdmin_Create_Done = By.xpath("//button[contains(@class,'btn btn-primary')]");
	public static By Button_Name = By.xpath("Button");
	public static By Continue = By.xpath("//button[@class='btn btn-primary mobilebtnBlock paddingLR marginR20 ng-scope']");
	public static By Manage_User = By.xpath("(//div[@class='mobile-view']/a[1])[3]"); //Int2
	public static By New_School_Create = By.xpath("//div[@id='navbar']/ul/li[4]/div[2]/a[3]");
	public static By STU_PAR_INVITE = By.xpath("//tr[@class='ng-scope'][1]/td[2]");
	public static By TEA_INVITE = By.xpath("//tr[@class='ng-scope'][2]/td[2]");
	public static By SAD_INVITE = By.xpath("//tr[@class='ng-scope'][3]/td[2]");
	public static By PRI_INVITE = By.xpath("//tr[@class='ng-scope'][4]/td[2]");
	public static By Mobile_Already_Exist = By.xpath("//div[@class='yourAccount']/div/div/p");
	public static By Profile_icon = By.xpath("//div[@class='pull-right dropdown']/button[@class='dropbtn_nav btn-dd dropbtn']");
	public static By class_section_selection = By.xpath("//table[@id='class-section']/thead[1]/tr[1]/th[1]/label[1]");
	public static By class_section_next = By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-30']");
	public static By class_names = By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-15 m-b-15']");
	public static By class_section_names = By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-30']");
	public static By class_subject = By.xpath("//div[@class='ng-scope'][2]/div/div[2]/div[1]/div[3]/div[1]/button[2]");
	public static By final_invite = By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-30']");
	public static By alreadyExistUser = By.xpath("//h4[@class='margin0']");
	public static By Skip = By.xpath("//button[@class='btn btn-outline padding-10-30 m-t-40 margin-bottom-0']");
	public static By Proceed = By.xpath("//button[@class='btn btn-primary padding-10-60 ng-scope']");
	public static By Confirm_Account =By.xpath("//label[@class='css-label radGroup2']");
	
	public void School_Create () throws InterruptedException{
		String regCode="FLIP_12345_" + new Date().toGMTString();
		String schoolName = "Fliplearn Education School_" + (int)Math.floor(Math.random()*1111111);
		driver.findElement(School_Name).sendKeys(schoolName);
		driver.findElement(Registration).sendKeys(regCode);
		driver.findElement(Address).sendKeys("Gurgaon");
		Select Board = new Select(driver.findElement(Board_Name));
		Board.selectByVisibleText("CBSE");
		Select state = new Select(driver.findElement(Get_State));
		state.selectByVisibleText("Delhi");
		Select city = new Select(driver.findElement(Get_City));
		city.selectByVisibleText("Delhi NCR");
		driver.findElement(Email).sendKeys(Email_Id);
		driver.findElement(Mobile_No).sendKeys(mobile);
		Thread.sleep(2000);
		driver.findElement(Done).click();
		Thread.sleep(2000);
	}
	public HashMap<String, String> getInviteCodes(){
		HashMap<String, String> hmap = new HashMap<>();
		hmap.put("STU", driver.findElement(STU_PAR_INVITE).getText());
		hmap.put("PAR", driver.findElement(STU_PAR_INVITE).getText());
		hmap.put("TEA", driver.findElement(TEA_INVITE).getText());
		hmap.put("SAD",driver.findElement(SAD_INVITE).getText());
		hmap.put("PRI", driver.findElement(PRI_INVITE).getText());
		return hmap;
	}
	public void setInviteCodesInExcelFile(HashMap<String, String> hmap){
		getInviteCodes();
		xls.setCellData("InviteCode", "Role", 2, "STU");
		xls.setCellData("InviteCode", "Code", 2, hmap.get("STU"));
		xls.setCellData("InviteCode", "Role", 3, "PAR");
		xls.setCellData("InviteCode", "Code", 3, hmap.get("PAR"));
		xls.setCellData("InviteCode", "Role", 4, "TEA");
		xls.setCellData("InviteCode", "Code", 4, hmap.get("TEA"));
		xls.setCellData("InviteCode", "Role", 5, "SAD");
		xls.setCellData("InviteCode", "Code", 5, hmap.get("SAD"));
		xls.setCellData("InviteCode", "Role", 6, "PRI");
		xls.setCellData("InviteCode", "Code", 6, hmap.get("PRI"));
	}
	public HashMap<String, String> getRoleType(){
		HashMap<String, String> hmap = new HashMap<>();
		hmap.put("STU", xls.getCellData("InviteCode", "Role", 2));
		hmap.put("PAR", xls.getCellData("InviteCode", "Role", 3));
		hmap.put("TEA", xls.getCellData("InviteCode", "Role", 4));
		hmap.put("SAD",xls.getCellData("InviteCode", "Role", 5));
		hmap.put("PRI", xls.getCellData("InviteCode", "Role", 6));
		return hmap;
	}
	public String getOTPForForGotPassword(String mobilenumber) throws ClassNotFoundException, SQLException, IOException, JSchException{
		String OTP="";
		DBConnection.CreateDBConnection();
		ResultSet rs2;
		try{
			rs2=DBConnection.executeQuery("SELECT * FROM ums_api.user_verification_code where mobile_no='"+mobilenumber+"' "
					+ "order by updated_date desc limit 1;");
			while(rs2.next()){
				OTP=rs2.getString("verification_code").trim();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBConnection.disconnectDBConnection();
		}
		return OTP;
	}
	public int Create_Login_Id(int countr, String User_Role,String inviteCode) throws InterruptedException, ClassNotFoundException, SQLException, IOException, JSchException{
		int loginid = 0;
		switch(User_Role){
		case "SAD":
			loginid = (int)Math.floor(Math.random()*789456123);
			driver.findElement(Full_Name).sendKeys("Admin");
			driver.findElement(Login_Id).sendKeys("admin."+loginid);
			fill_password();
			admin_school_setup();
			break;
		case "STU":
			loginid = (int)Math.floor(Math.random()*789456123);
			driver.findElement(Full_Name).sendKeys("Student");
			driver.findElement(Login_Id).sendKeys("student."+loginid);
			fillPasswordandLogout();
			break;
		case "PAR":
			loginid = (int)Math.floor(Math.random()*789456123);
			driver.findElement(Full_Name).sendKeys("Parent");
			driver.findElement(Login_Id).sendKeys("parent."+loginid);
			fillPasswordandLogout();
			break;
		case "TEA":
			loginid = (int)Math.floor(Math.random()*789456123);
			driver.findElement(Full_Name).sendKeys("Teacher");
			driver.findElement(Login_Id).sendKeys("teacher."+loginid);
			fillPasswordandLogout();
			break;
		case "PRI":
			loginid = (int)Math.floor(Math.random()*789456123);
			driver.findElement(Full_Name).sendKeys("Principal");
			driver.findElement(Login_Id).sendKeys("principal."+loginid);
			fillPasswordandLogout();
			break;
		}
		return loginid;
	}
	public void fillPasswordandLogout() throws InterruptedException{
		fill_password();
		users_logout();
	}
	public void fill_password (){
		driver.findElement(Create_Password).sendKeys(password);
		driver.findElement(Confirm_Password).sendKeys(password);
		driver.findElement(Create_Login_Id).click();
	}
	public void users_logout () throws InterruptedException{
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(Profile_icon)).perform();
		builder.moveToElement(driver.findElement(Logout1)).click().perform();
		Thread.sleep(2000);
		//driver.findElement(Login).click();
	}
	public void userRegistrationAndCreateLoginId(String inviteCode, String roleType, int userCount) throws Exception{
		for(int i=0;i<userCount;i++){
			int loginId=0;
			Thread.sleep(2000);
			driver.findElement(Join_Fliplearn).click();
			List<WebElement> elements = driver.findElements(By.xpath("//label[@class='css-label radGroup2']"));
			Thread.sleep(2000);
			elements.get(0).click();
			driver.findElement(Confirm).click();
			Thread.sleep(2000);

			switch(roleType){
			case "SAD":
				loginId = admin_creation(inviteCode,roleType,userCount);
				xls.setCellData("Users", "Admin Login", i+2, "admin."+loginId);
				xls.setCellData("Users", "Password", i+2, password);
				break;
			case "STU":
				loginId = student_creation(inviteCode, roleType,userCount);
				xls.setCellData("Users", "Student Login", i+2, "student."+loginId);
				xls.setCellData("Users", "Password", i+2, password);
				break;
			case "PAR":
				loginId = parent_creation(inviteCode, roleType,userCount);
				xls.setCellData("Users", "Parent Login", i+2, "parent."+loginId);
				xls.setCellData("Users", "Password", i+2, password);
				break;
			case "PRI":
				loginId = teacher_principal_creation(inviteCode, roleType,userCount);
				xls.setCellData("Users", "Principal Login", i+2, "principal."+loginId);
				xls.setCellData("Users", "Password", i+2, password);
				break;
			case "TEA":
				loginId = teacher_principal_creation(inviteCode, roleType,userCount);
				xls.setCellData("Users", "Teacher Login", i+2, "teacher."+loginId);
				xls.setCellData("Users", "Password", i+2, password);
				break;
			}
		}
	}
	public void navigateToSchoolCreate() throws InterruptedException{
		Thread.sleep(3000);
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
//		if(generic.isElementPresent("Continue")){
//			driver.findElement(Continue).click();
//		}
		if(generic.isElementPresent("Proceed")){
			Thread.sleep(2000);
			WebElement element = driver.findElements(roleSelect_RB).get(0);
			element.click();
			driver.findElement(Proceed).click();
		}
		
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(Manage_User)).perform();
		builder.moveToElement(driver.findElement(New_School_Create)).click().perform();
		Thread.sleep(3000);
	}
	public void school_confirmation_to_create_role() throws Exception{
		driver.findElement(Enter_Mobile).sendKeys(mobile);
		driver.findElement(Get_Otp).click();
		String OTP=generic.getOTPUsingMobile2(mobile);
		driver.findElement(Enter_Otp).sendKeys(OTP);
		driver.findElement(Verify).click();
		generic.waitPageGotLoad();
		try{
			GenericFunctions.WaitFor_visibility(driver, alreadyExistUser);	
			try{
				GenericFunctions.WaitFor_visibility(driver, Confirm_Account);
			}
			catch(Exception e){

			}
		}catch(Exception e){

		}		
		try {
//			Thread.sleep(4000);
			if(driver.getPageSource().contains("None of the above")){
					Thread.sleep(1000);
					driver.findElement(alreadyExistUser).click();
			}
			else if(driver.getPageSource().contains("Is this your Account?")){
					Thread.sleep(1000);
					List<WebElement> Confirm_Acnt = driver.findElements(Confirm_Account);
					Thread.sleep(2000);
					Confirm_Acnt.get(1).click();
			}
		}
		catch (NoSuchElementException e) {
		}
		Thread.sleep(1000);
		generic.waitPageGotLoad();
		driver.findElement(SchoolAdmin_Create_Done).click();
	}
	public int admin_creation(String inviteCode,String roleType,int userCount) throws Exception{
		Thread.sleep(2000);
		driver.findElement(Fill_Invite_Code).sendKeys(inviteCode);
		driver.findElement(Get_Started).click();
		List<WebElement> Confirm_Admin = driver.findElements(By.xpath("//label[@class='css-label radGroup2']"));
		Thread.sleep(2000);
		Confirm_Admin.get(0).click();
		driver.findElement(School_Confirm).click();
		school_confirmation_to_create_role();
		Thread.sleep(3000);
		driver.findElement(SchoolAdmin_Create_Done).click();
		Thread.sleep(2000);
		int loginId = Create_Login_Id(userCount,roleType,inviteCode);
		return loginId;
	}
	public int student_creation(String inviteCode, String roleType, int userCount) throws Exception{
		List<WebElement> Confirm_Radio_Button;
		driver.findElement(Fill_Invite_Code).sendKeys(inviteCode);
		driver.findElement(Get_Started).click();
		Confirm_Radio_Button = driver.findElements(By.xpath("//label[@class='css-label radGroup2']"));
		Thread.sleep(2000);
		Confirm_Radio_Button.get(0).click();
		driver.findElement(School_Confirm).click();
		Thread.sleep(2000);
		Confirm_Radio_Button = driver.findElements(By.xpath("//label[@class='css-label radGroup2']"));
		Confirm_Radio_Button.get(0).click();
		driver.findElement(Role_Confirm).click();
		school_confirmation_to_create_role();
		int loginId = Create_Login_Id(userCount,roleType,inviteCode);
		return loginId;
	}
	public int parent_creation(String inviteCode, String roleType, int userCount) throws Exception{
		List<WebElement> Confirm_Radio_Button;
		driver.findElement(Fill_Invite_Code).sendKeys(inviteCode);
		driver.findElement(Get_Started).click();
		Confirm_Radio_Button = driver.findElements(By.xpath("//label[@class='css-label radGroup2']"));
		Thread.sleep(3000);
		Confirm_Radio_Button.get(0).click();
		driver.findElement(School_Confirm).click();
		Thread.sleep(2000);
		Confirm_Radio_Button = driver.findElements(By.xpath("//label[@class='css-label radGroup2']"));
		Thread.sleep(2000);
		Confirm_Radio_Button.get(1).click();
		driver.findElement(Role_Confirm).click();
		school_confirmation_to_create_role();
		int loginId = Create_Login_Id(userCount,roleType,inviteCode);
		return loginId;
	}
	public int teacher_principal_creation(String inviteCode, String roleType, int userCount) throws Exception{
		List<WebElement> Confirm_Radio_Button;
		Thread.sleep(1000);
		driver.findElement(Fill_Invite_Code).sendKeys(inviteCode);
		driver.findElement(Get_Started).click();
		Confirm_Radio_Button = driver.findElements(By.xpath("//label[@class='css-label radGroup2']"));
		Thread.sleep(2000);
		Confirm_Radio_Button.get(0).click();
		driver.findElement(School_Confirm).click();
		school_confirmation_to_create_role();
		int loginId = Create_Login_Id(userCount,roleType,inviteCode);
		return loginId;
	}
	public void admin_school_setup() throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(class_section_selection).click();
		Thread.sleep(1000);
		driver.findElement(class_section_selection).click();
		Thread.sleep(1000);
		driver.findElement(class_section_selection).click();
		Thread.sleep(2000);

		WebElement element = driver.findElement(class_section_next);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element); 
		Thread.sleep(2000);
		driver.findElement(class_section_next).click();

		WebElement elementCheck = driver.findElement(class_names);
		JavascriptExecutor jsCheck = (JavascriptExecutor)driver;
		jsCheck.executeScript("arguments[0].scrollIntoView();", elementCheck); 
		Thread.sleep(2000);
		driver.findElement(class_names).click();

		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		WebElement elementChk = driver.findElement(class_section_names);
		JavascriptExecutor jsChk = (JavascriptExecutor)driver;
		jsChk.executeScript("arguments[0].scrollIntoView();", elementChk); 
		Thread.sleep(2000);
		driver.findElement(class_section_names).click();
		Thread.sleep(3000);
		driver.findElement(class_section_selection).click();
		Thread.sleep(3000);
		driver.findElement(class_section_selection).click();
		Thread.sleep(3000);

		WebElement subjectElement = driver.findElement(class_subject);
		JavascriptExecutor subjectJs = (JavascriptExecutor)driver;
		subjectJs.executeScript("arguments[0].scrollIntoView();", subjectElement); 
		Thread.sleep(3000);
		driver.findElement(class_subject).click();
		
		Thread.sleep(2000);
		driver.findElement(final_invite).click();
		Thread.sleep(2000);
		users_logout();
	}
}

