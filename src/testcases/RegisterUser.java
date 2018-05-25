package testcases;

import java.io.IOException;
import java.sql.SQLException;

import helper.DriverSession;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;

import pageModules.LoginPage;
import pageModules.RegisterPage;
import utils.Xls_Reader;

public class RegisterUser extends DriverSession {
	LoginPage login;
	Xls_Reader xls;
	RegisterPage register;

	@BeforeMethod
	public void OpenURL(){
		System.out.println(BASE_URL);
		driver.get(BASE_URL);
		login=new LoginPage(driver);
		driver.navigate().to(BASE_URL + "/login/");
		xls=new Xls_Reader();
		register=new RegisterPage(driver);
	}

	@Test(priority=1)
	public void RegisterSchlink() throws InterruptedException{
		register.RegisterSchool();
		String AfterSubmit=register.SuccessMessage();
		if(AfterSubmit.equalsIgnoreCase("Your request has been successfully submitted . Our team will contact you soon."))
		{
			Assert.assertTrue(true);
			}else{
			Assert.assertTrue(false);
		}
		
	}
	
	@Test(priority=2)
	public void RegisterAsGuest() throws Exception{
		
         
        String result="",Sheetname="Registration";
 		int i,rowCount=xls.getRowCount(Sheetname);
 		register.RgsAsGuest();
 		for(i=2;i<rowCount+1;i++){
		if (register.RgsAsGuestSuccessMessage()){
			result=result+"Pass,";
	        xls.setCellData(Sheetname, "Actual", i, "Pass");
	          }else{
	        result=result+"Fail,";
	       xls.setCellData(Sheetname, "Actual", i, "Fail");

		}
		}
		
	}
}
		
	





