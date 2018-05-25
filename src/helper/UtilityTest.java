package helper;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import utils.Xls_Reader;

public class UtilityTest {
	GenericFunctions generic;
	WebDriver driver;
	Xls_Reader xls=new Xls_Reader();

	public static  String getUserIDByRole(String roles){
		Xls_Reader xls=new Xls_Reader();
		String userId = null ;
		String Sheetname="Credentials";
		String role = "Role";
		String userIdColum = "UserId";
		int rowCount=xls.getRowCount(Sheetname);
		for(int i= 0; i<rowCount+1;i++){
			if(roles.equalsIgnoreCase(xls.getCellData(Sheetname, role, i))){
				userId= xls.getCellData(Sheetname, userIdColum, i);
				return userId;
			}
		}
		return userId;
	}

	public static  String getUserPasswordByRole(String roles){
		Xls_Reader xls=new Xls_Reader();
		String password = null ;
		String Sheetname="Credentials";
		String role = "Role";
		String passwordColum = "Password";
		int rowCount=xls.getRowCount(Sheetname);
		for(int i= 2; i<rowCount+1;i++){
			if(roles.equalsIgnoreCase(xls.getCellData(Sheetname, role, i))){
				password= xls.getCellData(Sheetname, passwordColum, i);
				return password;
			}
		}
		return password;
	}


	public static Object setCustomDownloadDirectory(String browserType){
		Object obj = null;
		DesiredCapabilities cap = null;
		FirefoxProfile profile = null;
		String downloadFilepath = System.getProperty("user.dir") + "\\Download";
		switch (browserType) {
		case "chrome":
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			WebDriver driver = new ChromeDriver(cap);
			obj=cap;
			break;
		case "FF":    
			profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderlist", 2);
			profile.setPreference("browser.helperapps.neverAsk.saveToDisk","jpeg");
			profile.setPreference("browser.download.dir", downloadFilepath);
			obj=profile;
			break;
		default:
			System.out.println("Incorrect browser name passed");	
			break;

		}
		return obj;

	}

}