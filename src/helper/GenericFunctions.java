package helper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jcraft.jsch.JSchException;

import utils.DBConnection;
import utils.Xls_Reader;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Satya Prakash Solanki on 25/8/16.
 */
public class GenericFunctions{
	WebDriver driver;
	public GenericFunctions(WebDriver driver){
		this.driver = driver;
	}
	public static By continue_Btn=By.xpath("//button[@class='btn btn-primary paddingLR marginR20 ng-scope']");

	//div[@class='paddingLoginSchool col-sm-8']


	/***********************************************************************************************
	 * Function Description : Accepts the alert box message
	 * author: Satya Prakash Solanki, date: 11-Aug-2016
	 * *********************************************************************************************/


	public String SetImplicitWaitInSeconds(int timeOut){
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		return "Timeout set to "+timeOut+" seconds.";
	}


	public void GoToSleep(int TimeInMillis)
	{
		try{Thread.sleep(TimeInMillis);} catch(Exception e){}
	}

	/***********************************************************************************************
	 * Function Description : Sets implicit Wait by accepting timeout in milliseconds
	 * author: Satya Prakash Solanki, date: 11-Aug-2016
	 * *********************************************************************************************/
	public String SetImplicitWaitInMilliSeconds(int timeOut){
	//	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
		return "Timeout set to "+timeOut+" milli seconds.";
	}
	public String AlertBox_Accept(){
		// Get a handle to the open alert, prompt or confirmation
		Alert alert = driver.switchTo().alert();
		// And acknowledge the alert (equivalent to clicking "OK")
		alert.accept();
		return("accepted");
	}

	/***********************************************************************************************
	 * Function Description : Dismisses the alert box message
	 * author: Satya Prakash Solanki, date: 11-Aug-2016
	 * *********************************************************************************************/
	public String AlertBox_Dismiss(){
		// Get a handle to the open alert, prompt or confirmation
		Alert alert = driver.switchTo().alert();
		// And acknowledge the alert (equivalent to clicking "cancel")
		alert.dismiss();
		return("Alert '"+alert.getText()+"' dismissed");
	}

	/***********************************************************************************************
	 * Function Description : Switches to the most recent window opened
	 * author: Satya Prakash Solanki, date: 11-Aug-2016
	 * *********************************************************************************************/
	public void SwitchtoNewWindow(){
		for(String windowsHandle : driver.getWindowHandles()){
			driver.switchTo().window(windowsHandle);
		}
	}

	/***********************************************************************************************
	 * Function Description : Closes the window
	 * author: Satya Prakash Solanki, date: 11-Aug-2016
	 * *********************************************************************************************/
	public void CloseNewWindow(){
		driver.close();
	}


	public String CompareTwoGivenCommaSeperatedList(String ListA , String ListB)
	{
		String result="";
		String ListAresult="";
		String ListBresult="";
		if(!ListA.contains(",") && !ListB.contains(","))
		{
			if(ListA.trim().equals(ListB.trim()))
			{
				return "Pass";
			}
			else
			{
				return "Fail";
			}
		}
		String[] ListArray1=ListA.split(",");
		String[] ListArray2=ListB.split(",");
		String tokenA;
		String tokenB;


		for(int i=0;i<ListArray1.length;i++)
		{
			tokenA=ListArray1[i];

			for(int j=0;j<ListArray2.length;j++)
			{
				if(tokenA.trim().equals(ListArray2[j].trim()))
				{
					break;
				}
				else if(j==ListArray2.length-1)
				{
					ListAresult=ListAresult+"::"+tokenA;
				}
			}

		}

		for(int i=0;i<ListArray2.length;i++)
		{
			tokenB=ListArray2[i];

			for(int j=0;j<ListArray1.length;j++)
			{
				if(tokenB.trim().equals(ListArray1[j].trim()))
				{
					break;
				}
				else if(j==ListArray1.length-1)
				{
					ListBresult=ListBresult+"::"+tokenB;
				}
			}

		}

		if(ListAresult.equals("") && ListBresult.equals(""))
		{
			result="Lists are equal so Pass";
		}
		else
		{
			result = "Extra in List A =>" + ListAresult + "  Extra in List B =>" + ListBresult;
		}
		System.out.println(result);
		return result;
	}

	/***********************************************************************************************
	 * Function Description : It Checks The Presence of a element on page of given path
	 * author: Satya Prakash Solanki, date: 11-Apr-2013
	 * *********************************************************************************************/

	public boolean isPresent(By elementXpath)
	{
		SetImplicitWaitInMilliSeconds(500);
		if(driver.findElements((elementXpath)).size()!=0)
		{
			SetImplicitWaitInMilliSeconds(10000);
			return true;
		}
		else
		{
			SetImplicitWaitInMilliSeconds(10000);
			return false;
		}
	}


	/***********************************************************************************************
	 * Function Description : It Checks The Presence and Visibility of a element on page of given path
	 * author: Satya Prakash Solanki, date: 11-Apr-2013
	 * *********************************************************************************************/


	public Boolean isVisible(By elementXpath)
	{
		SetImplicitWaitInMilliSeconds(3000);
		if(driver.findElements(elementXpath).size()!=0)
		{
			SetImplicitWaitInMilliSeconds(10000);
			if (driver.findElement(elementXpath).isDisplayed())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			SetImplicitWaitInMilliSeconds(10000);
			return false;
		}
	}


	/***********************************************************************************************
	 * Function Description : It Checks The Maximum allowed char limit in the field
	 * author: Satya Prakash Solanki, date: 11-Apr-2013
	 * *********************************************************************************************/


	public String CheckMaxLimit(By elementXpath,int ExpectedMaxLimit)
	{

		for(int count =1;count<=ExpectedMaxLimit+5;count++){
			driver.findElement((elementXpath)).sendKeys("1");
		}

		int ActualMaxLimit =  driver.findElement((elementXpath)).getAttribute("value").length();
		if(ActualMaxLimit==ExpectedMaxLimit){
			return "Pass";
		}
		else{
			return "Fail";
		}

	}



	/***********************************************************************************************
	 * Function Description : It Generates Name using timestamp
	 * author: Satya Prakash Solanki, date: 20-May-2013
	 * *********************************************************************************************/

	public String GetNameAsCurrentTimeStamp(){
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		return Calendar.getInstance().getTime().toString().replaceAll(":", "").replace(" ", "");
	}


	/***********************************************************************************************
	 * Function Description : It returns a unique name derived from Timestamp
	 * author: Satya Prakash Solanki, date: 5-May-2013
	 * *********************************************************************************************/

	public String getNameAsTimeStamp() {
		String TimeStamp = Calendar.getInstance().getTime().toString();
		TimeStamp = TimeStamp.replace(":", "").replace(" ", "").replace("+", "");
		return TimeStamp;
	}

	/***********************************************************************************************
	 * Function Description : It returns the value for the attribute specified
	 * author: Satya Prakash Solanki, date: 14-Jun-2013
	 * *********************************************************************************************/

	public String getAttribute(By by,String attributeName) {
		return driver.findElement((by)).getAttribute(attributeName);
	}


	/***********************************************************************************************
	 * Function Description : It Checks a specific value is present in given list
	 * author: Satya Prakash Solanki, date: 23-July-2013
	 * *********************************************************************************************/

	public Boolean CheckExistenceOfAValueInAList(String Value,List<String> InputList)
	{
		Boolean result=false;
		for(String option : InputList)
		{
			if(option.trim().equals(Value.trim()))
			{
				result=true;
				break;
			}

		}

		return result;
	}

	/***********************************************************************************************
	 * Function Description : Set/Write  String List at given excel path sheetname and column.
	 * author: Satya Prakash Solanki, date: 13-Jun-2013
	 * *********************************************************************************************/
	public void SetStringListInXLColumn(Xls_Reader datatable, String sheetname, String colname, List<String> list){

		for(int i=0; i<list.size(); i++){
			datatable.setCellData(sheetname, colname, i+2, list.get(i));
		}

	}

	/***********************************************************************************************
	 * Function Description : Compares two provided xl columns and set the status
	 * author: Satya Prakash Solanki, date: 13-Jun-2013
	 * *********************************************************************************************/
	public void CompareTwoXLColumns(Xls_Reader datatable, String sheetname, String colname1, String colname2, String statuscol){
		for(int i=2; i<datatable.getRowCount(sheetname);i++){
			if(datatable.getCellData(sheetname, colname1, i).equals(datatable.getCellData(sheetname, colname2, i))){
				datatable.setCellData(sheetname, statuscol, i, "pass");
			}else{
				datatable.setCellData(sheetname, statuscol, i, "fail");
			}
		}
	}


	public void CompareTwoXLColumnsSaveResultInOtherXL(Xls_Reader datatable, String sheetname,String resultsheetname, String colname1, String colname2, String statuscol){
		String col1="";
		String col2="";

		for(int i=2; i<datatable.getRowCount(sheetname);i++){
			col1=datatable.getCellData(sheetname, colname1, i);
			col2=datatable.getCellData(sheetname, colname2, i);
			if(col1.equals(col2)){
				datatable.setCellData(resultsheetname, statuscol, i, "pass");
			}else{
				datatable.setCellData(resultsheetname, statuscol, i, "fail : => "+col1+ " "+col2 + "");
			}
		}
	}

	public void CompareTwoDifferentXLColumnsSaveResultInOtherXL(Xls_Reader datatable, String sheetname1,String sheetname2,String resultsheetname, String colname1, String colname2, String statuscol){
		String col1="";
		String col2="";

		for(int i=2; i<datatable.getRowCount(sheetname1);i++){
			col1=datatable.getCellData(sheetname1, colname1, i);
			col2=datatable.getCellData(sheetname2, colname2, i);
			if(col1.equals(col2)){
				datatable.setCellData(resultsheetname, statuscol, i, "pass");
			}else{
				datatable.setCellData(resultsheetname, statuscol, i, "fail : => "+col1+ " "+col2 + "");
			}
		}
	}

	/***********************************************************************************************
	 * Function Description : Will go to provided div/span/xpath and fetch the attribute of provided tagname
	 * Example: Fetching links text from a provided div: input required div xpath, tagname as "a" and attribute as"text"
	 * Example: Fetching links url from a provided div: input required div xpath, tagname as "a" and attribute as"href"
	 * author: Satya Prakash Solanki, date: 13-Jun-2013
	 * *********************************************************************************************/
	public List<String> FetchFromParent(By parent_Xpath, String tagname, String attribute){

		ArrayList<String> list = new ArrayList<String>();
		List<WebElement> elements =driver.findElement((parent_Xpath)).findElements(By.tagName(tagname));
		for(int i=0; i< elements.size(); i++){
			if(attribute.equalsIgnoreCase("text"))
				list.add(elements.get(i).getText());
			else
				list.add(elements.get(i).getAttribute(attribute));
		}
		return list;
	}





	/***********************************************************************************************
	 * Function Description : Navigate through all pages from SRP and fetches provided attribute and return all attributes as list
	 * author: Satya Prakash Solanki, date: 13-Jun-2013
	 * *********************************************************************************************/
	//	public List<String> FetchFromSRP(String xpath_whatToFetch, String xpath_nextbuttonONSRP, String attributeToFetch){
	//
	//		ArrayList<String> list = new ArrayList<String>();
	//		boolean pagesDone=false;
	//		while(pagesDone==false){
	//
	//
	//			List<WebElement> we = driver.findElements(By.xpath(xpath_whatToFetch));
	//			for(int i=0; i< we.size(); i++){
	//				if(attributeToFetch.equalsIgnoreCase("text"))
	//					list.add(we.get(i).getText());
	//				else
	//					list.add(we.get(i).getAttribute(attributeToFetch));
	//
	//			}
	//
	//			if(isVisible(xpath_nextbuttonONSRP)){
	//				driver.findElement(By.xpath(xpath_nextbuttonONSRP)).click();
	//				try {
	//					Thread.sleep(2000);
	//				} catch (InterruptedException e) {
	//					e.printStackTrace();
	//				}
	//			}else{
	//				pagesDone=true;
	//			}
	//
	//		}			return list;
	//
	//	}




	/***********************************************************************************************
	 * Function Description : It Validate select date in naukri calender/text box for a given date
	 * author: Satya Prakash Solanki, date: 2-April-2013
	 * *********************************************************************************************/

	public String fnCheckSelectedValueOfCalender(String dateonexcel,String inputdateonCalender)
	{
		String result="";
		String ActualDatetextOnCalender=inputdateonCalender;
		String ActualDateTextOnInput=dateonexcel;
		String dateoncinput;
		String monthoninput;
		String yearoninput;
		String formatteddatofinput;

		Hashtable<String, String> months = new Hashtable();
		months.put("Jan", "01");
		months.put("Feb", "02");
		months.put("Mar", "03");
		months.put("Apr", "04");
		months.put("May", "05");
		months.put("Jun", "06");
		months.put("Jul", "07");
		months.put("Aug", "08");
		months.put("Sep", "09");
		months.put("Oct", "10");
		months.put("Nov", "11");
		months.put("Dec", "12");


		dateoncinput=ActualDateTextOnInput.substring(0,ActualDateTextOnInput.indexOf("-"));
		if(dateoncinput.length()==1)
		{
			dateoncinput="0"+dateoncinput;
		}
		monthoninput=ActualDateTextOnInput.substring(ActualDateTextOnInput.indexOf("-")+1, ActualDateTextOnInput.indexOf("-")+4 );
		yearoninput=ActualDateTextOnInput.substring(ActualDateTextOnInput.lastIndexOf("-")+1, ActualDateTextOnInput.length());
		monthoninput=months.get(monthoninput);

		formatteddatofinput=yearoninput+"-"+monthoninput+"-"+dateoncinput;
		//	System.out.println(formatteddatofinput);
		if(ActualDatetextOnCalender.equals(formatteddatofinput))
		{
			result="Pass";
		}
		else
		{
			result="Fail";
		}

		return result;

	}


	/***********************************************************************************************
	 * Function Description : It Select Given Date from Naukri Calender
	 * author: Satya Prakash Solanki, date: 2-April-2013
	 * Date Should be in date-month-year format (ie- 21-Jan-2013)
	 * *********************************************************************************************/
	// ================== Select Dates in Calender =============
	public void fnSelectDatefromCalender(String CalIconId,String calId,String dateToSelect)
	{

		String result="";

		//int date=Integer.parseInt(dateToSelect.substring(0, 2));
		String[] s = dateToSelect.split("-");
		String date=s[0];
		//int year=Integer.parseInt(dateToSelect.substring(6, dateToSelect.length()));
		int year=Integer.parseInt(s[2]);
		WebElement calContainer;
		Date currentdate;
		List<WebElement> AllRows;
		String calenderMonth;
		int calenderYear;

		Hashtable<String, Integer> months = new Hashtable();
		months.put("Jan", 1);
		months.put("Feb", 2);
		months.put("Mar", 3);
		months.put("Apr", 4);
		months.put("May", 5);
		months.put("Jun", 6);
		months.put("Jul", 7);
		months.put("Aug", 8);
		months.put("Sep", 9);
		months.put("Oct", 10);
		months.put("Nov", 11);
		months.put("Dec", 12);
		int month=months.get(s[1]);
		//System.out.println(month);
		int i=1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		currentdate=Calendar.getInstance().getTime();
		//System.out.println(dateFormat.format(currentdate));

		driver.findElement(By.id(CalIconId)).click();
		calContainer=driver.findElement(By.xpath(("//div[@id='"+calId+"']/table/tbody")));
		AllRows = calContainer.findElements(By.xpath("./tr"));
		int numberofrows=AllRows.size();

		for(int a=1;a<=numberofrows;a++)
		{
			if(i==1)
			{
				int yeardiff;
				int monthdiff;
				String calderData=driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]/td[3]")).getText();
				System.out.println(calderData);
				calenderMonth=calderData.substring(0,3);
				System.out.println(calenderMonth);
				int calenderindex=months.get(calenderMonth);
				calenderYear=Integer.parseInt(calderData.substring(4,calderData.length()));
				System.out.println(calenderYear);
				if(calenderYear>year)
				{
					yeardiff=calenderYear-year;
					System.out.println("year diff is : "+yeardiff);
					for(int j=0;j<yeardiff;j++)
					{
						driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText("<<")).click();
					}
					if(month>calenderindex)
					{
						monthdiff=month-calenderindex;
						System.out.println("month diff is "+monthdiff);
						for(int k=0;k<monthdiff;k++)
						{
							driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText(">")).click();
						}
					}
					else if(month<calenderindex)
					{
						monthdiff=calenderindex-month;
						System.out.println("month diff is "+monthdiff);
						for(int k=0;k<monthdiff;k++)
						{
							driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText("<")).click();
						}
					}
				}
				else if(calenderYear<year)
				{
					yeardiff=year-calenderYear;
					for(int j=0;j<yeardiff;j++)
					{
						driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText(">>")).click();
					}

					if(month>calenderindex)
					{
						monthdiff=month-calenderindex;
						System.out.println("month diff is "+monthdiff);
						for(int k=0;k<monthdiff;k++)
						{
							driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText(">")).click();
						}
					}
					else if(month<calenderindex)
					{
						monthdiff=calenderindex-month;
						System.out.println("month diff is "+monthdiff);
						for(int k=0;k<monthdiff;k++)
						{
							driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText("<")).click();
						}
					}

				}
				else
				{

					if(month>calenderindex)
					{
						monthdiff=month-calenderindex;
						System.out.println("month diff is "+monthdiff);
						for(int k=0;k<monthdiff;k++)
						{
							driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText(">")).click();
						}
					}
					else if(month<calenderindex)
					{
						monthdiff=calenderindex-month;
						System.out.println("month diff is "+monthdiff);
						for(int k=0;k<monthdiff;k++)
						{
							driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText("<")).click();
						}
					}


				}

				System.out.println(months.get(calenderMonth));

			}
			else if(i>2)
			{
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
				if(driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElements(By.linkText(date)).size()!=0)

				{
					driver.findElement(By.xpath("//div[@id='"+calId+"']/table/tbody/tr["+a+"]")).findElement(By.linkText(date)).click();
					break;
				}
			}



			i=i+1;
		}


		//return result;
	}


	/***********************************************************************************************
	 * Function Description : It Checks The Presence of a element on page of given path
	 * author: Satya Prakash Solanki, date: 11-Apr-2013
	 * *********************************************************************************************//*
 public Boolean isPresent1(String elementXpath)
 {
        SetImplicitWaitInMilliSeconds(500);
        if(driver.findElements((elementXpath)).size()!=0)
        {
               return true;
        }
        else
        {
               return false;
        }
 }*/


	/***********************************************************************************************
	 * Function Description : Compares two given String Lists
	 * author: Satya Prakash Solanki, date: 13-Jun-2013
	 * *********************************************************************************************/
	public String CompareTwoStringLists(List<String> list1, List<String> list2){
		String result ="";
		if(list1.size() != list2.size()){
			return "fail count is not same";
		}else{

			for(int i=0; i<list1.size(); i++){
				if(!(list1.get(i).equals(list2.get(i)))){
					result= result+list1.get(i)+"is not same as:"+list2.get(i)+", ";
				}

				if(result.equals("")){ result= "pass";}else{return "Fail, "+result;}
			}
		}


		return result;
	}


	/***********************************************************************************************
	 * Function Description : Returns the current directory absolute path
	 * author: Ankur, date: 18-Apr-2014
	 * *********************************************************************************************/
	public String GetCurrentDirectoryAbsolutePath() {
		String current = "";
		try {
			current = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return current;
	}

	/***********************************************************************************************
	 * Function Description : It returns the value for the provided key from properties file
	 * author: Satya Prakash Solanki, date: 10-Mar-2015
	 * *********************************************************************************************/
	public String getPropertyValue(String propertyName) {

		String directory = System.getProperty("user.dir");

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
		String propertyValue = prop.getProperty(propertyName);

		return propertyValue;
	}


	/***********************************************************************************************
	 * Function Description : It returns the directory path for the current project
	 * author: Satya Prakash Solanki, date: 11-Mar-2015
	 * *********************************************************************************************/
	public String getUserDirectoryPath() {
		return System.getProperty("user.dir");
	}



	//////////////////Satya Added///////////////

	public String SetKIOSKEmailasCurrentTimeStamp(){
		String Email = "";

		String Date = String.valueOf(Calendar.getInstance().getTime().getDate());
		String Month = String.valueOf(Calendar.getInstance().getTime().getMonth());
		String Year = String.valueOf(Calendar.getInstance().getTime().getYear()+1900);
		String Hours = String.valueOf(Calendar.getInstance().getTime().getHours());
		String Minutes = String.valueOf(Calendar.getInstance().getTime().getMinutes());
		String Seconds = String.valueOf(Calendar.getInstance().getTime().getSeconds());


		if(Date.length() < 2)
			Date = "0" + Date;

		if(Month.length() < 2)
			Month = "0" + Month;

		if(Year.length() < 4)
			Year = "0000";

		if(Hours.length() < 2)
			Hours = "0" + Hours;

		if(Minutes.length() < 2)
			Minutes = "0" + Minutes;

		if(Seconds.length() < 2)
			Seconds = "0" + Seconds;

		Email = "KIOSK-" + Date + Month + Year + "-" + Hours + Minutes + Seconds + "@yopmail.com";

		return Email;
	}

	public void ClickElementInADivHavingButton(By parent_Xpath, String inputdata){

		if(inputdata.equalsIgnoreCase("Reset"))
			driver.findElement((parent_Xpath)).findElement(By.tagName("button")).click();

		else{
			driver.findElement((parent_Xpath)).findElement(By.linkText(inputdata.trim())).click();
		}


	}


	public void ClickElementInADiv(String parent_Xpath, String inputdata){
		driver.findElement(By.xpath(parent_Xpath)).findElement(By.linkText(inputdata.trim())).click();
	}


	public void ClickElementInA_MutliSelectDiv(By parent_Xpath, String inputdata){
		String[] Str=inputdata.split(";");
		for(int j=0;j<Str.length;j++){
			driver.findElement((parent_Xpath)).findElement(By.linkText(Str[j].trim())).click();
		}
	}


	public void ClickElementInA_MutliSelectDiv2(By parent_Xpath, String inputdata){
		String[] Str=inputdata.split(";");
		for(int k=0;k<Str.length;k++)
			System.out.println(Str[k]);
		List<WebElement> elements =driver.findElement((parent_Xpath)).findElements(By.tagName("li"));
		for(int j=0;j<Str.length;j++){
			for(int i=0; i<elements.size(); i++){
				String actual_data =elements.get(i).getText();
				if(actual_data.trim().equalsIgnoreCase(Str[j].trim()))
					elements.get(i).click();
			}
		}
	}



	//	public void waitForGivenTime(int seconds, By xpath)
	//	{
	//		WebDriverWait wait = new WebDriverWait(driver, seconds);
	//		try
	//		{
	//			wait.until(ExpectedConditions.visibilityOfElementLocated((xpath)));
	//		}
	//		catch(TimeoutException e)
	//		{
	//		}
	//	}
	//
	//	public void waitTillElementVisibility(int seconds, By xpath)
	//	{
	//		WebDriverWait wait = new WebDriverWait(driver, seconds);
	//		try
	//		{
	//			wait.until(ExpectedConditions.visibilityOfElementLocated((xpath)));
	//		}
	//		catch(TimeoutException e)
	//		{
	//		}
	//	}


	public static String getCurrentDirectory()
	{
		String directory= System.getProperty("user.dir");
		return directory;
	}

	public Boolean isElementPresent(String elementName)
	{
		SetImplicitWaitInMilliSeconds(3000);
		if(driver.getPageSource().contains(elementName))
		{	
			SetImplicitWaitInMilliSeconds(10000);
			return true;
		}
		else
		{
			return false;
		}
	}
	public void clickOnContinue_Btn(){
		driver.findElement(continue_Btn).click();
	}
	public void mouseHover(By elementLocator,int index){
		WebElement element;
		if(driver.findElements(elementLocator).size()>1){
			element = driver.findElements(elementLocator).get(index);
		}
		else{
			element = driver.findElements(elementLocator).get(0);
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();	
	}
	public void selectFromDropDownWithValue(By elementLocator, String value){
		Select element=new Select(driver.findElement(elementLocator));
		element.selectByVisibleText(value);
	}
	public void selectFromDropDownWithIndex(By elementLocator, int index){
		Select element=new Select(driver.findElement(elementLocator));
		element.selectByIndex(index);
	}

	public void ClearAppData(String packageName)
	{
		Runtime rt = Runtime.getRuntime();
		String command = "adb shell pm clear "+packageName+"";
		try
		{
			rt.exec(command);
			System.out.println("App Data Cleared!!!");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public String getOTPUsingMobile(String mobilenumber) throws Exception{
		String OTP="";
		DBConnection.connectDatabase("ums");
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
	public String getOTPUsingMobile2(String mobilenumber) throws Exception{
		String OTP="";
		DBConnection.connectDatabase("ums");
		ResultSet rs2;
		try{
			rs2=DBConnection.executeQuery("SELECT * FROM ums_api.user_verification_code where mobile_no='"+mobilenumber+"' "
					+ " AND verification_for='Mobile' order by updated_date desc limit 1;");
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
	public String getOTPForForGotPassword(String loginId) throws Exception{
		String OTP="";String user_id="";
		//DBConnection con=new DBConnection();
		DBConnection.connectDatabase("ums");
		ResultSet rs1;
		ResultSet rs2;
		try{
			rs1 = DBConnection.executeQuery("SELECT * FROM ums_api.user_master where login_id='"+loginId+"';");
			while(rs1.next()){
				user_id=rs1.getString("id").trim();
			}
			rs2=DBConnection.executeQuery("SELECT * FROM ums_api.user_verification_code where user_id='"+user_id+"' "
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
	public HashMap<String, String> getMessagesDetailFromDB(String title) throws Exception{
		HashMap<String, String> hmap = new HashMap<String, String>();
		DBConnection.connectDatabase("school");
		try {
			ResultSet Rs = DBConnection.executeQuery("SELECT * FROM school_api.messages where title ='"+title+"'  order by updated limit 1");
			while(Rs.next()){
				hmap.put("title", Rs.getString("title"));
				hmap.put("description", Rs.getString("message_text"));
				hmap.put("assetCode", Rs.getString("asset_set_code"));
				hmap.put("sendsms", Rs.getString("sendsms"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DBConnection.disconnectDBConnection();
		}
		return hmap;
	}

	public static void WaitFor_visibility(WebDriver driver, By elementLocator) {
		(new WebDriverWait(driver, 120)).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
	}
	public static void WaitFor_invisibility(WebDriver driver, By elementLocator) {
		(new WebDriverWait(driver, 120)).until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
	}
	public static void WaitFor_visibilityOfElements(WebDriver driver, WebElement element) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(element));
	}
	public static void WaitFor_presenceOfAllElements(WebDriver driver, WebElement element) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) element));
	}
	public static void WaitFor_clickable(WebDriver driver, WebElement element) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(element));
	}
	public boolean isPageGotLoad() {
		return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	}

	public void waitPageGotLoad() throws InterruptedException{
		int count=6;
		while(isPageGotLoad() && count>1){
			Thread.sleep(1000);
			count--;
		}
	}

	public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                    	System.out.println(((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
	
	public void UploadFile(String FilePath) throws Throwable{
		StringSelection ss = new StringSelection(FilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public void clickFunction(WebElement element){
		element.click();
	}
	public String getCurrentEnvironment(){
		String OSName = System.getProperty("os.name");
		return OSName;
	}
	public void scrollPage(By elementlocator){
		WebElement element = driver.findElement(elementlocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void scrollPageForMultipleElement(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void scrollPageToBottom(){
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public void scrollPageUp(){
		((JavascriptExecutor)driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	public void newScroll() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll inside web element vertically (e.g. 100 pixel)
		js.executeScript("arguments[0].scrollTop = arguments[1];",driver.findElements(By.xpath("//div[@class='modal-content']")).get(4), 100);
	}

	public void clickOnLinkText(String link){
		try {
			driver.findElement(By.linkText(link)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public  String getDownloadedDocumentName(String downloadDir, String fileExtension)
	{	
		String downloadedFileName ="";
		boolean valid = true;
		boolean found = false;

		//default timeout in seconds
		long timeOut = 20; 
		try 
		{					
			Path downloadFolderPath = Paths.get(downloadDir);
			WatchService watchService = FileSystems.getDefault().newWatchService();
			downloadFolderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			long startTime = System.currentTimeMillis();
			do 
			{
				WatchKey watchKey;
				watchKey = watchService.poll(timeOut,TimeUnit.SECONDS);
				long currentTime = (System.currentTimeMillis()-startTime)/1000;
				if(currentTime>timeOut)
				{
					System.out.println("Download operation timed out.. Expected file was not downloaded");
					return downloadedFileName;

				}

				for ( WatchEvent<?> event : watchKey.pollEvents())
				{
					WatchEvent.Kind<?> kind = event.kind();
					if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) 
					{
						String fileName = event.context().toString();
					//	System.out.println("New File Created:" + fileName);
						if(fileName.endsWith(fileExtension))
						{
							downloadedFileName = fileName;
							System.out.println("Downloaded file found with extension " + fileExtension + ". File name is " + fileName);
							Thread.sleep(500);
							found = true;
							break;
						}
					}
				}
				if(found)
				{
					return downloadedFileName;
				}
				else
				{
					currentTime = (System.currentTimeMillis()-startTime)/1000;
					if(currentTime>timeOut)
					{
						System.out.println("Failed to download expected file");
						return downloadedFileName;
					}
					valid = watchKey.reset();
				}
			} while (valid);
		} 

		catch (InterruptedException e) 
		{
			System.out.println("Interrupted error - " + e.getMessage());
			e.printStackTrace();
		}
		catch (NullPointerException e) 
		{
			System.out.println("Download operation timed out.. Expected file was not downloaded");
		}
		catch (Exception e)
		{
			System.out.println("Error occured - " + e.getMessage());
			e.printStackTrace();
		}
		return downloadedFileName;
	}



public void deleteFile(String path){
	File file = new File(path);
	if (file.exists()) {
		for (File file1 : file.listFiles()) {
		    if (file1.getName().endsWith((".xlsm"))) {
		      file1.delete();
		    }
		  }
	}else{
		System.out.println("file not exist");
	}
}

}
