package pageModules;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.Xls_Reader;

public class BulkUpload {
	static WebDriver driver;
	Xls_Reader xls;
	GenericFunctions generic;

	public BulkUpload(WebDriver driver){
		BulkUpload.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By bulkupload_Menu=By.xpath("//div[@href='/home/bulk/download-upload-bulk']");
	public static By downloadExcel_Link = By.xpath("//a[@h4='Download Excel Sheet']");
	public static By uploadExcel_Link = By.xpath("//a[@h4='Select excel sheet']");
	public static By startUpload = By.xpath("//button[@class='btn btn-primary btn-block TenPadding pull-right inviteMore']");

	public void UserOnboarding(){
		
	}

}
