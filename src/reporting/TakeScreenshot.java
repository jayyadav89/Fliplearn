package reporting;

import io.appium.java_client.AppiumDriver;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import utils.Xslt_XlsReader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.GlobalVar;

public class TakeScreenshot extends TestListenerAdapter {
	WebDriver driver;
	String QualifiedMethodName = "";
	String NewFileNamePath;
	String NewFilePathName;
	public static boolean logged=false;

	@Override
	public void onTestFailure(ITestResult tr) {
		driver = DriverSession.getLastExecutionDriver();
		QualifiedMethodName = tr.getInstanceName() + "." +TestData.TestCaseId;
		SetStatusinTestCaseCreationSheet("Fail");
		xUpdateTestDetails("FAIL");
		xScreenShot();
		addDescription();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		driver = DriverSession.getLastExecutionDriver();
		QualifiedMethodName = tr.getInstanceName() + "." +TestData.TestCaseId;
		SetStatusinTestCaseCreationSheet("Skipped");
		xUpdateTestDetails("SKIPPED");
		xScreenShot();
		addDescription();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		driver = DriverSession.getLastExecutionDriver();
		QualifiedMethodName = tr.getInstanceName() + "." +TestData.TestCaseId;
		SetStatusinTestCaseCreationSheet("Pass");
		xUpdateTestDetails("PASS");
		addDescription();
	}


	public void xScreenShot(){
		try {
			File directory = new File("./AutomationReports/finalReport");
			DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_hh_mm_ssaa");
			Date date = new Date();

			String dateName = dateFormat.format(date);
			NewFileNamePath = directory.getCanonicalPath() + "/screenshots/"+ TestData.TestCaseId + "___" + dateName + "_"+ ".png";
			NewFilePathName = "/screenshots/"+ TestData.TestCaseId + "___" + dateName + "_"+ ".png";

			try{
				File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(f, new File(NewFileNamePath));
			}
			catch(Exception e){
				java.awt.Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
				Rectangle rectangle = new Rectangle(resolution);
				Robot robot = new Robot();
				BufferedImage bi = robot.createScreenCapture(new Rectangle(rectangle));
				ImageIO.write(bi, "png", new File(NewFileNamePath));
			}

			NewFileNamePath = "<a href=." + NewFilePathName + " target='_blank'>Click Here</a>";
			Print("[Snapshot]:" + NewFileNamePath);	

		} catch (Exception e) {

		} 
	}

	public void Print(String Text) {
		System.out.println(Text);
		Reporter.log(Text);
		String Temp = Text;
		TestData.sMessages = TestData.sMessages + Temp.replaceAll(" ", "_") + "#";
	}



	public void xUpdateTestDetails(String Status){
		try{
			if (TestData.TestCaseId == "")
				TestData.TestCaseId = "IdNotDefined";
			String Temp = "[Status]:"+ TestData.TestCaseId + "_" + Status;
			Temp = Temp.replaceAll(" ", "_");
			Print (Temp);
			Print("[End]:" + xGetDateTimeIP());
			//Print (reporting.TestData.sMessages);
		} catch (Exception e){
			e.printStackTrace();
		}

	}	

	public void SetStatusinTestCaseCreationSheet(String Status){
		String[] paths = null;

		if(TestData.xlsPath.contains(","))
			paths = TestData.xlsPath.split(",");

		for(int j=0;j<paths.length;j++){
			if(!paths[j].equals("")){
				Xslt_XlsReader xls = new Xslt_XlsReader(paths[j]);
				for(int i=2;i<=xls.getRowCount("TestCaseSheet");i++){
					String ExpectedQualifiedMethodName = xls.getCellData("TestCaseSheet", "PackageName", i) + "." + xls.getCellData("TestCaseSheet", "ClassFileName", i) + "." + xls.getCellData("TestCaseSheet", "TestCaseID", i);
					if(QualifiedMethodName.equalsIgnoreCase(ExpectedQualifiedMethodName)){
						xls.setCellData("TestCaseSheet", "Result", i, Status);
					}
				}
			}
		}
	}

//	public String xGetDateTimeIP() throws Exception {
//		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaa_dd_MMM_yyyy");
//		Date date = new Date();
//		InetAddress ownIP = InetAddress.getLocalHost();
//		return (dateFormat.format(date) + "_IP" + ownIP.getHostAddress());
//	}
	
	public String xGetDateTimeIP() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ssaa dd-MMM-yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	public void addDescription(){
		String[] paths = null;

		if(TestData.xlsPath.contains(","))
			paths = TestData.xlsPath.split(",");

		for(int j=0;j<paths.length;j++){
			if(!paths[j].equals(""))
				addDescriptionLoop(paths[j]);
		}
	}


	public void addDescriptionLoop(String path){
		Xslt_XlsReader xls = new Xslt_XlsReader(path);
		if(!logged)
		{
			Reporter.log("Device Used = "+GlobalVar.DeviceName);
			logged=true;
		}
		Reporter.log("Satya Prakash = "+GlobalVar.DeviceName);
		for(int i=2;i<=xls.getRowCount("TestCaseSheet"); i++){

			String ExpectedQualifiedMethodName = xls.getCellData("TestCaseSheet", "PackageName", i) + "." + xls.getCellData("TestCaseSheet", "ClassFileName", i) + "." + xls.getCellData("TestCaseSheet", "TestCaseID", i);

			if(QualifiedMethodName.equalsIgnoreCase(ExpectedQualifiedMethodName)){

				/*Reporter.log("[Environment]: " + TestData.TestorLive + " ");
				Reporter.log("[Execution_Type]: " + TestData.Execution_Type + " ");
				Reporter.log("[Browser_Type]: " + TestData.Driver_Type + " ");*/				
				

				if(!xls.getCellData("TestCaseSheet", "TestCase", i).equals(""))
					Reporter.log("[BriefDesc]: "+xls.getCellData("TestCaseSheet", "TestCase", i));
				else
					Reporter.log("[BriefDesc]: Description is not provided");

				if(!xls.getCellData("TestCaseSheet", "TestSteps", i).equals(""))
					Reporter.log("[Steps]: "+xls.getCellData("TestCaseSheet", "TestSteps", i));
				else
					Reporter.log("[Steps]: Test Steps are not provided");

				if(!xls.getCellData("TestCaseSheet", "ExpectedResults", i).equals(""))
					Reporter.log("[Expected]: "+xls.getCellData("TestCaseSheet", "ExpectedResults", i));
				else
					Reporter.log("[Expected]: Expected Result is not provided");

				break;
			}

		}
	}

	public static String getDeviceName(){
		String UDID="Could not find device!!";;
		String DeviceName="Android emulator nexus 5";
		String command = "adb devices";
		Runtime rt = Runtime.getRuntime();		

		/*		String directory = System.getProperty("user.dir");
		String propFileName = directory+"/config.properties";
		File file = new File(propFileName);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String executionType = prop.getProperty("Execution_Type");
		if(!executionType.equalsIgnoreCase("bvt")){
		 */
		try {
			Process proc = rt.exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s;
			while((s = stdInput.readLine()) != null){
				if(s.contains(" [")){
					String temp= s.split(" \\[")[1];
					if(!temp.contains("-")){

						UDID=temp.split("\\]")[0];
					}
				}
				if(s.contains(UDID)){
					String temp= s.split(" \\[")[0];
					DeviceName=temp;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*	}
		else{
			DeviceName="Simulator";
		}
		 */
		return DeviceName;
	}

}
