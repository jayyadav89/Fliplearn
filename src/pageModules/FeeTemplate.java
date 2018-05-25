package pageModules;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FeeTemplate {
	WebDriver driver;
	GenericFunctions generic;

	public FeeTemplate(WebDriver driver) {
		this.driver = driver;
		generic = new GenericFunctions(driver);
	}

	public static By fee_lnk = By.id("feePay_icon");
	public static By getStarted_Btn = By.xpath("//a[@class='btn btn-primary padding-12-40 semiboldText m-t-5']");
	public static By startFeePayment_Btn = By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-30']");
	public static By saveAndProceed_Btn1 = By.xpath("(//button[text()='Save & Proceed'])[1]");
	public static By saveAndProceed_Btn2 = By.xpath("(//button[text()='Save & Proceed'])[2]");
	public static By downloadDateSheet_Btn = By.xpath("//button[@class='btn btn-outline btnPadding']");
	public static By Delete_btn = By.xpath("//button[text()='Delete']");
	public static By yes_btn = By.xpath("//button[@data-target='#finalDelete']");
	public static By confirmdeletE_Btn = By.xpath("//button[text()='Confirm & Delete']");
	public static By Okay_Btn = By.xpath("//button[text()='Okay']");

	public void clickOnFeelink() {
		GenericFunctions.WaitFor_visibility(driver, fee_lnk);
		driver.findElement(fee_lnk).click();
	}

	public void clickOnGetStartedButton() {
		GenericFunctions.WaitFor_visibility(driver, getStarted_Btn);
		driver.findElement(getStarted_Btn).click();
	}

	public void clickOnstartFeePayment() {
		GenericFunctions.WaitFor_visibility(driver, startFeePayment_Btn);
		driver.findElement(startFeePayment_Btn).click();
	}

	public void checkFeeVersionPresent() {

		generic.GoToSleep(2000);
		if (generic.isPresent(Delete_btn)) {
			System.out.println("Fee version Exist");
			FeeVersionDelete();
			clickOnstartFeePayment();
		} else {
			System.out.println("Fee version does not Exist");
			clickOnstartFeePayment();
		}

	}

	public void FeeVersionDelete() {
		generic.GoToSleep(3000);
		driver.findElement(Delete_btn).click();
		generic.GoToSleep(3000);
		driver.findElement(yes_btn).click();
		generic.GoToSleep(3000);
		driver.findElement(confirmdeletE_Btn).click();
		generic.GoToSleep(3000);
		driver.findElement(Okay_Btn).click();
		generic.GoToSleep(3000);
		System.out.println("Version Deleted Successfully");
	}

	public void selectStudentID(String studentID) {
		GenericFunctions.WaitFor_visibility(driver, saveAndProceed_Btn1);
		driver.findElement(By.xpath("//label[text()='" + studentID + "']")).click();
	}

	public void clickOnSaveAndproceedBtn1() {
		driver.findElement(saveAndProceed_Btn1).click();
	}

	public void selectFeeComponent(String feeComponent) {
		GenericFunctions.WaitFor_visibility(driver, saveAndProceed_Btn2);
		driver.findElement(By.xpath("//label[text()='" + feeComponent + "']")).click();
	}

	public void clickOnSaveAndproceedBtn2() {
		driver.findElement(saveAndProceed_Btn2).click();
	}

	public void clickOnDownloadDataSheet() {
		GenericFunctions.WaitFor_visibility(driver, downloadDateSheet_Btn);
		driver.findElement(downloadDateSheet_Btn).click();
	}

	public boolean isfilenameSameInDownloadFolder() {
		String downloadpath = System.getProperty("user.dir") + "\\Download";
		// System.out.println("downloadpath"+downloadpath);
		generic.deleteFile(downloadpath);
		String DownloadFileName = generic.getDownloadedDocumentName(downloadpath, ".xlsm");
		System.out.println("DownloadFileName: " + DownloadFileName);
		if (DownloadFileName.contains("Upload-fee-template")) {
			return true;
		} else {
			return false;
		}

	}

}
