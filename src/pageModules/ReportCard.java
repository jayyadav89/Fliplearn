package pageModules;

import java.io.File;
import java.nio.file.FileSystems;
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


import utils.Xls_Reader;

public class ReportCard {
	WebDriver driver;
	Xls_Reader xls;
	GenericFunctions generic;
	public ReportCard(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By manage_lnk=By.xpath("//a[@id='managereportcard-icon']");
	public static By manageStudent_Btn=By.xpath("//a[@ui-sref='manageStudent']");
	public static By subjectMapping_Btn=By.xpath("//a[@href='/home/admin/section-subject-mapping']");
	public static By marksheet_Btn=By.xpath("//a[@href='/home/admin/marksheet']");
	public static By reportCard_Btn=By.xpath("//a[@href='/home/admin/reportcard']");
	public static By selectYourTerm_DropDown_Report=By.xpath("//select[@ng-model='ReportCardCtrl.selectedTerm']");
	public static By term1text_Report=By.xpath("//option[@label='Term 1']");
	public static By selectClass_DropDown=By.xpath("//select[@name='className']");
	public static By class_name=By.xpath("//option[@label='Class 1']");
	public static By selectSection_DropDown=By.xpath("//select[@name='sectionName']");
	public static By export_Btn=By.xpath("//button[text()='Export']");
	public static By pdf_Btn=By.xpath("//button[text()='Print PDF']");
	public static By closepdf_Btn=By.xpath("//button[text()='Close']");
	public static By notify_Btn=By.xpath("//button[text()='Notify Parents']");
	public static By sectionA=By.xpath("//option[@label='A']");
	public static By studentDatabase_DownloadExcel=By.xpath("//h4[text()='Download student data sheet']");
	public static By sectionSubjectMapping_xcelFile=By.xpath("//h4[text()='Download class/section - subject data sheet']");
	public static By errorMessageAppear=By.xpath("//div[text()='Please select your Term.']");
	public static By selectYourTerm_DropDown_Marksheet=By.xpath("//select[@ng-model='MarksheetCtrl.selectedTerm']");
	public static By selectClassType_Marksheet=By.xpath("//select[@ng-model='MarksheetCtrl.selectedClass']");
	public static By selectSection_Marksheet=By.xpath("//select[@ng-model='MarksheetCtrl.selectedSection']");
	public static By downloadbtn_Marksheet=By.xpath("//button[text()='Download']");
	public static By errorMessageFileUnableUpload =By.xpath("//p[@class='uploderUnable m-t-25']");
	public static By downloadAgain=By.xpath("//button[text()='Download Again']");
	public static By clickHere_Btn=By.xpath("//a[text()='Click here']");
	public static By uploadExcelSheet=By.xpath("//h4[text()='Upload Excel Sheet']");
	public static By UploadTimeMessage=By.xpath("//span[text()='Please wait we are processing your sheet. Do not refresh this page.']"); 
	public static By adminRadio_Btn=By.xpath("//div[@class='radiobutton']");  //("//input[@id='admissionFee10']");
	public static By proceed_Btn=By.xpath("//button[contains(@class,'btn btn-primary padding-10-60 ng-scope')]");
	public static By fileUploadsuccessMessage=By.xpath("//*[@class='uploderUnable m-t-25']");
	public static By fileUploadErrorMessage_Marksheet=By.xpath("//p[text()='Your File has been uploaded and it will be processed in sometime. ']");
	public static By pdfGenerationTextMessage=By.xpath("//h4[contains(text(),'Your request to generate PDF file has been')]");
	public static By enternotifyTextMessage=By.xpath("//textarea[@id='notifyMsg']");
	public static By sendbtnOnNotifyPage=By.xpath("//button[text()='Send']");
	public static By notifyTextMessage=By.xpath("//div[text()='Please enter message.']");


	public void selectAdminAndProceed_Btn(){
		driver.findElements(adminRadio_Btn).get(0).click();
		driver.findElement(proceed_Btn).click();
	}
	public void clickOnManage_Lnk(){
		GenericFunctions.WaitFor_visibility(driver,manage_lnk);
		generic.mouseHover(manage_lnk, 1);
	}
	public void clickOnManageStudent_Btn(){
		driver.findElement(manageStudent_Btn).click();
	}

	public void clickOnStudentDataBse() throws Throwable {

		String NewFilepath1;
		driver.findElement(studentDatabase_DownloadExcel).click();
		String downloadpath = System.getProperty("user.dir") + "\\Download";
		String DownloadFileName = generic.getDownloadedDocumentName(downloadpath,".xlsm");
		System.out.println("DownloadFileName: " + DownloadFileName);
		generic.GoToSleep(5000);
		driver.findElement(uploadExcelSheet).click();
		String OSName = generic.getCurrentEnvironment();
		String filePath1 = System.getProperty("user.dir") + "/Download/"
				+ DownloadFileName;
		System.out.println(filePath1);
		if (OSName.contains("Windows")) {
			NewFilepath1 = filePath1.replace("/", "\\");

		} else {
			NewFilepath1 = filePath1.replace("/", "/");

		}
		generic.GoToSleep(2000);
		generic.UploadFile(NewFilepath1);
		GenericFunctions.WaitFor_invisibility(driver, UploadTimeMessage);
		generic.GoToSleep(5000);
		generic.deleteFile(downloadpath);

	}
	public void clickOnsubjectMapping(){
		driver.findElement(subjectMapping_Btn).click();
	}
	public void clickOnselectionSubjectMapping_xcelFile() throws Throwable{
		driver.findElement(sectionSubjectMapping_xcelFile).click();
		driver.findElement(uploadExcelSheet).click();
		generic.UploadFile("/home/fliplearn/DataColllecrion/10472320-Upload-subject-template(1).xlsm");
		GenericFunctions.WaitFor_invisibility(driver, UploadTimeMessage);

	}
	public void clickOnMarksheet_Btn(){
		driver.findElement(marksheet_Btn).click();
	}
	public void selectYourTerm_Marksheet(String value){
		generic.selectFromDropDownWithValue(selectYourTerm_DropDown_Marksheet, value);

	}
	public void selectClass_Marksheet(String value){
		generic.selectFromDropDownWithValue(selectClassType_Marksheet, value);
	}
	public void selectSection_Marksheet(String value){
		generic.selectFromDropDownWithValue(selectSection_Marksheet, value);
	}
	public void download_marksheet_Btn() throws Throwable{
		driver.findElement(downloadbtn_Marksheet).click();

	}
	public String actualResult_SubjectMapping(){
		//String expected="We were unable to upload 5 row(s) out of 5 due to some errors done";
		String actual1=driver.findElement(fileUploadsuccessMessage).getText();
		String actual=actual1.substring(0, 66);
		return actual;
		//Assert.assertEquals(actual, expected);

	}
	public void uploadmarksheet_Btn() throws Throwable{
		driver.findElement(uploadExcelSheet).click();
		generic.UploadFile("/home/fliplearn/DataColllecrion/term1_Class_1_A_marksheetTemplate1511872128.xlsm");
		generic.scrollPageToBottom();
		GenericFunctions.WaitFor_invisibility(driver, UploadTimeMessage);
		/*if(driver.findElement(errorMessageFileUnableUpload).getText().equalsIgnoreCase("Marksheet template(s) have been created successfully. ")){
			System.out.println("file is Uploaded");
		}
		else
		{
			driver.findElement(downloadAgain).click();
			driver.findElement(uploadExcelSheet).click();
			generic.UploadFile("/home/fliplearn/DataColllecrion/term1_Class_1_A_marksheetTemplate1511872128.xlsm");
			GenericFunctions.WaitFor_invisibility(driver, UploadTimeMessage);

		}*/
	}
	public void clickOnReport_Lnk(){
		driver.findElement(reportCard_Btn).click();
	}
	public void selectTerm1_DropDown_Report(String value){
		generic.selectFromDropDownWithValue(selectYourTerm_DropDown_Report,value);
	}
	public void selectType_Class_Report(String value){
		generic.selectFromDropDownWithValue(selectClass_DropDown, value);
	}
	public void selectSection_type_Report(String value){
		generic.selectFromDropDownWithValue(selectSection_DropDown, value);
	}
	public void clickOnExpert_Btn(){
		driver.findElement(export_Btn).click();
	}
	public void clickOnExpertAndUploadFile_Btn(){
		driver.findElement(export_Btn).click();
		GenericFunctions.WaitFor_invisibility(driver, UploadTimeMessage);
		/*if(driver.findElement(errorMessageFileUnableUpload).getText().equalsIgnoreCase("ReportCard(s) have been created successfully. ")){
			driver.findElements(clickHere_Btn).get(0).click();
		}
		else{
			driver.findElement(downloadAgain).click();
		}*/
	}
	public void clickOnPdf_Btn(){
		driver.findElement(pdf_Btn).click();
	}
	public void clickOnNotify_Btn(){
		driver.findElement(notify_Btn).click();
	}
	public void downLoadStudentExcel_File(){
		driver.findElement(studentDatabase_DownloadExcel).click();
	}
	public void downLoadMappingExcel_File(){
		driver.findElement(sectionSubjectMapping_xcelFile).click();
	}
	public String ActualerrorMessageAppear_Text(){
		//String expected="Please select your Term.";
		GenericFunctions.WaitFor_visibility(driver,errorMessageAppear);
		String actual = driver.findElement(errorMessageAppear).getText();
		return actual;
		//Assert.assertEquals(expected,actual);
	}
	public void uploadExcelSheet_Lnk(){
		driver.findElement(uploadExcelSheet).click();
	}
	public String actual_ManageStudent() throws InterruptedException{
		generic.scrollPageToBottom();
		String actual = driver.findElement(fileUploadsuccessMessage).getText();
		return actual;
		//String expected="We were unable to upload 20 row(s) out of 20 due to some errors done by the uploader.";
		//Assert.assertEquals(actual, expected);
	}
	public String actualVerificationResult_Marksheet(){
		//String expected="Your File has been uploaded and it will be processed in sometime.";
		generic.scrollPageToBottom();
		String actual1=driver.findElement(fileUploadErrorMessage_Marksheet).getText();
		String actual =actual1.substring(0, 65);
		return actual;
		//Assert.assertEquals(actual, expected);

	}
	public String actualVerification_ReportCard() throws InterruptedException{
		//String expected="There is some problem occurred while export the reportcard(s). Click here to view the reason for failure.";
		generic.scrollPageToBottom();
		generic.waitPageGotLoad();
		String actual=driver.findElement(errorMessageFileUnableUpload).getText();
		return actual;
		//Assert.assertEquals(actual, expected);
	}
	public void clickOnCloseBtn_ReportCard()
	{
		driver.findElement(closepdf_Btn).click();	
	}
	public String pdfGenerationText_ReportCard(){
		GenericFunctions.WaitFor_visibility(driver, pdfGenerationTextMessage);
		return driver.findElement(pdfGenerationTextMessage).getText().substring(0, 94);
	}
	public void enterTextMessage_NotifyPage() throws InterruptedException{
		driver.findElement(enternotifyTextMessage).sendKeys("hii hello");
		driver.findElement(sendbtnOnNotifyPage).click();
	}
	public void enterSpaceTextMessage_NotifyPage() throws InterruptedException{
	 generic.waitPageGotLoad();
		driver.findElement(sendbtnOnNotifyPage).click();
	}
	public String errorMessageOnNoitfyPage_ReportLink(){
		return driver.findElement(notifyTextMessage).getText();

	}
	

}
