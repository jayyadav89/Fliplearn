package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageModules.LoginPage;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;

public class LoginTestCases extends DriverSession{

	LoginPage login;
	Xls_Reader xls;
	GenericFunctions generic;

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		generic=new GenericFunctions(driver);
		driver.get(BASE_URL);
		login=new LoginPage(driver);
		xls=new Xls_Reader();
	}
	@Test(priority=1)
	public void LN_001() throws InterruptedException{
		//	System.out.println("****Execution Started*****");
		String result="",Sheetname="Login",Expected;
		int i,rowCount=xls.getRowCount(Sheetname);
		String username,password,expectedError;
	//	generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
		for(i=2;i<rowCount+1;i++){
			//	System.out.println(rowCount);
			Expected=xls.getCellData(Sheetname, "Expected", i);
			username=xls.getCellData(Sheetname, "UserName", i);
			password=xls.getCellData(Sheetname, "Password", i);
			expectedError=xls.getCellData(Sheetname, "ExpectedError", i).trim();
			login.FillLoginForm(username, password);
			Thread.sleep(1000);
			if(login.checkForErrorMessage()){
				String ActualError=login.getErrorMessage(username, password);
				//System.out.println("In Error message");
				if(Expected.equalsIgnoreCase("Notsubmitted")){
					if(ActualError.equalsIgnoreCase(expectedError)){
						result=result+"Pass,";
						xls.setCellData(Sheetname, "Result", i, "Pass");
						xls.setCellData(Sheetname, "Actual_Error", i, ActualError);
					}
					else{
						result=result+"Fail,";
						xls.setCellData(Sheetname, "Result", i, "Fail");
						xls.setCellData(Sheetname, "Actual_Error", i, ActualError);
					}
				}

			}else if(login.checkForSuccesfullLogin()){
				//System.out.println("In Success message");
				if(Expected.equalsIgnoreCase("Submitted")){
					result=result+"Pass,";
					xls.setCellData(Sheetname, "Result", i, "Pass");
				}else{
					result=result+"Fail,";
					xls.setCellData(Sheetname, "Result", i, "Fail..Login not succesfull");
				}
			}else{
				result=result+"Fail,";
				xls.setCellData(Sheetname, "Result", i, "Fail..Some error occured");
			}
		}
		if(result.contains("Fail")){
			Assert.assertTrue(false, result);
		}else{
			Assert.assertTrue(true);
		}
	}

	@Test(priority=2)
	public void LN_002() throws Exception{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
		String result="",Sheetname="ForgetUserID", Expected;
		int i,rowCount=xls.getRowCount(Sheetname);
		String MobileNo;
		for(i=2;i<rowCount+1;i++){
			Expected=xls.getCellData(Sheetname, "Expected", i);
			MobileNo=xls.getCellData(Sheetname, "MobileNumber", i);
			login.ForgetUserId(MobileNo);
			String Actual=login.GetSuccessMessage_ForgetUserID_Pass();
			xls.setCellData(Sheetname, "Actual", i, Actual);
			if (Expected.equalsIgnoreCase(Actual)){
				result=result+"Pass,";
				xls.setCellData(Sheetname, "Result", i, "Pass");
			}
			else{
				result=result+"Fail,";
				xls.setCellData(Sheetname, "Result", i, "Fail..Some error occured");
			}
		}

		if(result.contains("Fail")){
			Assert.assertTrue(false, result);
		}else{
			Assert.assertTrue(true);
		}
	}
	@Test(priority=3)
	public void LN_003() throws Exception{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
		String result="",Sheetname="ForgetPassword",Expected;
		int i,rowCount=xls.getRowCount(Sheetname);
		String UserName;
		for(i=2;i<rowCount+1;i++){
			Expected=xls.getCellData(Sheetname, "Expected", i);
			UserName=xls.getCellData(Sheetname, "UserName", i);
			login.ForgetPassword(UserName);
			String Actual=login.GetSuccessMessage_ForgetUserID_Pass();
			xls.setCellData(Sheetname, "Actual", i, Actual);
			if (Expected.equalsIgnoreCase(Actual)){
				result=result+"Pass,";
				xls.setCellData(Sheetname, "Result", i, "Pass");
			}
			else{

				result=result+"Fail,";
				xls.setCellData(Sheetname, "Result", i, "Fail..Some error occured");
			}
		}
		if(result.contains("Fail")){
			Assert.assertTrue(false, result);
		}else{
			Assert.assertTrue(true);
		}
	}

	@Test(priority=4)
	public void LN_004() throws Throwable{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
		String result="",Sheetname="Term&condtion",Expected;
		int i,rowCount=xls.getRowCount(Sheetname);
		for(i=2;i<rowCount+1;i++){
			Expected=xls.getCellData(Sheetname, "Expected", i);
			String Actual=login.TermofuseLink();
			xls.setCellData(Sheetname, "Actual", i, Actual);
			if(Expected.equalsIgnoreCase(Actual)){			
				result=result+"Pass,";
				xls.setCellData(Sheetname, "Result", i, "Pass");
			}
			else{
				result=result+"Fail,";
				xls.setCellData(Sheetname, "Result", i, "Fail..Some error occured");
			}
		}

		if(result.contains("Fail")){
			Assert.assertTrue(false, "Terms and condition Page not opened");
		}else{
			Assert.assertTrue(true);
		}
	}

	@Test(priority=5)
	public void LN_005() throws Throwable{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
		String result="",Sheetname="VerifyPrivacyPolicy",Expected;
		int i,rowCount=xls.getRowCount(Sheetname);
		for(i=2;i<rowCount+1;i++){
			Expected=xls.getCellData(Sheetname, "Expected", i).trim();
			String Actual=login.PrivacyPolicy();
			xls.setCellData(Sheetname, "Actual", i, Actual);
			if(Expected.equalsIgnoreCase(Actual)){			
				result=result+"Pass,";
				xls.setCellData(Sheetname, "Result", i, "Pass");
			}
			else{
				result=result+"Fail,";
				xls.setCellData(Sheetname, "Result", i, "Fail..Some error occured");
			}
		}

		if(result.contains("Fail")){
			Assert.assertTrue(false, "Privacy policy page not opened");
		}else{
			Assert.assertTrue(true);
		}
	}


}


