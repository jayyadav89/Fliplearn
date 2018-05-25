package testcases;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.FeePayment;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;

public class FeePaymentTestCases extends DriverSession{

	LoginPage login;
	GenericFunctions generic;
	WebDriverWait wait;
	Xls_Reader xls;
	LoadEnvProperty envpro = new LoadEnvProperty();
	FeePayment fee;

	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		wait=new WebDriverWait(driver, 25);
		driver.get(BASE_URL);
		fee=new FeePayment(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, FeePayment.payFee_link);
		fee.ClickOnPayFee_link();
		
	}

	@Test(priority=1)
	public void FP_001() throws InterruptedException{
		String result="",Sheetname="Fee";
		int i,rowCount=xls.getRowCount(Sheetname);
		String schoolName, studentNumber, expected; 
		for(i=2;i<6;i++){
			schoolName=xls.getCellData(Sheetname, "SchoolName", i);
			studentNumber=xls.getCellData(Sheetname, "StudentNumber", i);
			expected = xls.getCellData(Sheetname, "Expected", i);
			fee.fillSchoolDetails(schoolName, studentNumber);
			if(fee.checkConfirmDetailsPage()){
				result=result+"Pass,";
				xls.setCellData(Sheetname, "Result", i, "Pass");	
			}else{
				String ActualError = fee.getSchoolDetailsErrorMsg();
				if(expected.equalsIgnoreCase(ActualError)){
					xls.setCellData(Sheetname, "Actual", i, ActualError);
					result=result+"Pass,";
					xls.setCellData(Sheetname, "Result", i, "Pass");
				}else{
					xls.setCellData(Sheetname, "Actual", i, ActualError);
					result=result+"Fail,";
					xls.setCellData(Sheetname, "Result", i, "Fail");
				}
			}
		}
		if(result.contains("Fail")){
			Assert.assertTrue(false, result+"school details does not matched");
		}else{
			Assert.assertTrue(true);
		}
	}
	@Test(priority=2)
	public void FP_002() throws InterruptedException{
		String result="",Sheetname="Fee";
		int i,rowCount=xls.getRowCount(Sheetname);
		String schoolName, studentNumber, parentName, mobileNumber, emailID, address, state, expected,PaymentOption; 
		for(i=6;i<rowCount+1;i++){
			schoolName=xls.getCellData(Sheetname, "SchoolName", i);
			studentNumber=xls.getCellData(Sheetname, "StudentNumber", i);
			parentName=xls.getCellData(Sheetname, "ParentName", i);
			mobileNumber =xls.getCellData(Sheetname, "MobileNumber", i);
			emailID =xls.getCellData(Sheetname, "EmailId", i);
			address =xls.getCellData(Sheetname, "Address", i);
			state =xls.getCellData(Sheetname, "State", i);
			PaymentOption =xls.getCellData(Sheetname, "PaymentOption", i);
			expected = xls.getCellData(Sheetname, "Expected", i);
			int payment = Integer.parseInt(PaymentOption);
			fee.fillSchoolDetails(schoolName, studentNumber);
			fee.confirmDetails();
			GenericFunctions.WaitFor_visibility(driver, FeePayment.parentName);
			fee.fillParentDetails_PaymentOptions(parentName, mobileNumber, emailID, address, state,payment);
			if(fee.checkPaymentGateway()){
				fee.goBackToFlilearn();
				try{
					Assert.assertTrue(fee.isHomeLinkDisplay(), "After payment failed, page does not redirect to home page");
					xls.setCellData(Sheetname, "Result", i, "Pass");
					result=result+"Pass,";
				}catch(AssertionError e){
					xls.setCellData(Sheetname, "Result", i, "Fail");
					result=result+"Fail,";
				}
			}else{
				Assert.assertTrue(false,"Payment page doesnot come");
				xls.setCellData(Sheetname, "Actual", i, "Got Error");
				result=result+"Fail,";
				xls.setCellData(Sheetname, "Result", i, "Fail");
			}
			if(result.contains("Fail")){
				Assert.assertTrue(false, result+"Fee Payment failed");
			}else{
				Assert.assertTrue(true);
			}

		}

	}
}
