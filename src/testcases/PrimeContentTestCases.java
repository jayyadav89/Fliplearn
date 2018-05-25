package testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.B2C;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;

import helper.DriverSession;
import helper.GenericFunctions;

public class PrimeContentTestCases extends DriverSession {
	LoginPage login;
	B2C b2c;
	Xls_Reader xls;
	LoadEnvProperty envpro = new LoadEnvProperty();
	GenericFunctions generic;
	int Counter_count=2;

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		driver.get(BASE_URL);
		login = new LoginPage(driver);
		b2c = new B2C(driver);
		xls = new Xls_Reader();
		generic = new GenericFunctions(driver);
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
	}
	@Test
	public void B2CTest() throws Throwable{
		String className = (String) readProperty("className");
		String subjectName = (String) readProperty("subjectName");
		String Sheetname="B2CResult";
		String TocName = (String) readProperty("TocName");
		String resourceName=(String) readProperty("resourceName");
		String subjectname=null;
		String tocname,resourcename;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		if(!className.equalsIgnoreCase("")){

			System.out.println("classname: "+className);
			try {
				WebElement classnameElem=driver.findElement(By.xpath("//a[text()='"+className+"']"));
				classnameElem.click();
			} catch (Exception e) {
				scrollDown(1);
				WebElement classnameElem=driver.findElement(By.xpath("//a[text()='"+className+"']"));
				classnameElem.click();
			}		
			Thread.sleep(2000);
			if(!subjectName.equalsIgnoreCase("")){
				//will do later
			}else{
				List<WebElement> Subjects = driver.findElements(By.xpath("//a[text()='"+className+"']/../../..//div[@class='col-sm-4 ng-scope']"));       //changes
				for(int i=0;i<Subjects.size();i++){
					Thread.sleep(3000);
					try{
						subjectname=Subjects.get(i).getText();
						System.out.println("subject name:"+subjectname);					
						Subjects.get(i).click();
					}catch(Exception e3){
						Subjects = driver.findElements(By.xpath("//a[text()='"+className+"']/../../..//div[@class='col-sm-4 ng-scope']"));
						subjectname=Subjects.get(i).getText();
						System.out.println("subject name:"+subjectname);					
						Subjects.get(i).click();
					}
					
					Thread.sleep(3000);
					List<WebElement> toc=driver.findElements(By.xpath("//a[@class='collapsed ng-binding tab-closed']"));      //changes
					System.out.println("toc size: "+toc.size());
					for(int j=0;j<toc.size();j++){
						Thread.sleep(2000);
						if(j>8){
							scrollDown(1);
						}
						try {
							tocname=toc.get(j).getText();
							System.out.println("tocname: "+tocname);
							toc.get(j).click();
							Thread.sleep(2000);
						} catch (Exception e) {
							toc=driver.findElements(By.xpath("//a[@class='collapsed ng-binding tab-closed']"));
							tocname=toc.get(j).getText();
							try {
								toc.get(j).click();
							} catch (Exception e2) {
								scrollDown(1);
								toc.get(j).click();
							}
							
						}
						Thread.sleep(2000);
						List<WebElement> Resources=driver.findElements(By.xpath("//li[@class='col-xs-10 col-sm-11 col-md-11 ng-binding']"));  //changes
						for(int k=0;k<Resources.size();k++){
							Thread.sleep(2000);
							try {
								resourcename=Resources.get(k).getText();
								Resources.get(k).click();
							} catch (Exception  e1) {
								Thread.sleep(2000);
								toc1=driver.findElements(By.xpath("//a[@class='collapsed ng-binding tab-closed']")); //changes
								Thread.sleep(2000);
								toc1.get(j).click();
								Thread.sleep(2000);
								/*if(k>8){
									scrollDown(1);
								}*/
								Resources=driver.findElements(By.xpath("//li[@class='col-xs-10 col-sm-11 col-md-11 ng-binding']")); //changes
								resourcename=Resources.get(k).getText();
								System.out.println("Resources name: "+resourcename);
								try{
									Resources.get(k).click();
								}catch(Exception  e2){
									scrollDown(1);
									Resources.get(k).click();
								}
							
							}
							validateVideoAndPdf(Sheetname, className, subjectname, tocname, resourcename);
							driver.navigate().back();
							Thread.sleep(1000);


						}
					}
					driver.navigate().back();
					Thread.sleep(1000);
					/*try {
						WebElement classnameElem=driver.findElement(By.xpath("//a[text()='"+className+"']"));
						classnameElem.click();
					} catch (Exception e) {
						scrollDown(1);
						WebElement classnameElem=driver.findElement(By.xpath("//a[text()='"+className+"']"));
						classnameElem.click();
					}*/

				}


			}

		}else{
			// will do it later
		}

	}
	public void loginAndClickOnB2C() throws InterruptedException{
		Thread.sleep(2000);
		login.loginInToApplication("vinay2.admin", "123456");
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		b2c.clickOnLearn();
		b2c.clickOnGuestImage();
	}
	public void scrollDown(int k){
		try {
			Robot robot = new Robot();
			for(int i=0; i<k; i++) {
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			}
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}
	public int validateVideoAndPdf(String Sheetname, String classname, String subjectname,
			String tocname,String resourceName ) throws Throwable{
			List<WebElement> Topics=driver.findElements(By.xpath("//div[@class='row learnResourcesInner']"));
			for(int l=0;l<Topics.size();l++){
				String topicName=driver.findElement(By.xpath("(//div[@class='col-md-7 col-sm-8 col-xs-6']//h4)["+(l+1)+"]")).getText();
				xls.setCellData(Sheetname, "ClassName", Counter_count, classname);
				xls.setCellData(Sheetname, "Subject", Counter_count, subjectname);
				xls.setCellData(Sheetname, "Toc", Counter_count, tocname);
				xls.setCellData(Sheetname, "Resource", Counter_count, resourceName);
				xls.setCellData(Sheetname, "TopicName", Counter_count, topicName);
				Thread.sleep(2000);
				String topicType = driver.findElement(By.xpath("(//h6[@class='ng-binding'])["+(l+1)+"]")).getText();
				if(topicType.equals("Animation")){
					Topics.get(l).click();	
					Thread.sleep(6000);						
					WebElement ele= driver.findElement(By.xpath("//div//video[@class='jw-video jw-reset']"));
					Actions actions = new Actions(driver);
					actions.moveToElement(ele).build().perform();
					int i=0;
					String timer="";
					while((timer.equalsIgnoreCase("")||timer.equalsIgnoreCase("00:00")) && i<20){
						Thread.sleep(500);
						timer = driver.findElement(By.xpath("//div[@class='jw-group jw-controlbar-left-group jw-reset']/span")).getText();
						i++;
					}
						
					if(driver.getPageSource().contains("404 Not")){
						xls.setCellData(Sheetname, "Result", Counter_count, "Fail..404");
					}else if(driver.getPageSource().contains(": 403")){
						xls.setCellData(Sheetname, "Result", Counter_count, "Fail..403");
					}else if(!timer.equals("")){
						xls.setCellData(Sheetname, "Result", Counter_count, "Pass");
					}else{
						xls.setCellData(Sheetname, "Result", Counter_count, "Fail..Video not Opened");
					}
					try {
						driver.findElement(By.xpath("(//button[@class='close'])[2]")).click();
					} catch (Exception e) {
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//button[@class='close'])[2]")).click();
					}
				}else {
					Topics.get(l).click();
					Thread.sleep(3000);
					driver.switchTo().frame("myFrame");
					if(topicType.equalsIgnoreCase("Topic Synopsis")||topicType.equalsIgnoreCase("Mind Maps")){
						String contentHeader = driver.findElement(By.xpath("//div[@class='col-lg-12 col-sm-12 col-xs-12 col-md-12']/h1")).getText();
						if(contentHeader.equalsIgnoreCase(topicType)){
							xls.setCellData(Sheetname, "Result", Counter_count, "Pass");	
						}else{
							xls.setCellData(Sheetname, "Result", Counter_count, "Fail");
						}
					}else{
						if(driver.findElement(By.xpath("//embed[@id='plugin']")).isDisplayed()){
							xls.setCellData(Sheetname, "Result", Counter_count, "Pass");
						}else{
							xls.setCellData(Sheetname, "Result", Counter_count, "Fail");
						}
					}
					
					
					try {
						driver.findElement(By.xpath("(//button[@class='close'])[3]")).click();
					} catch (Exception e) {
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//button[@class='close'])[3]")).click();
					}
					
				}
				System.out.println(Counter_count);
				Counter_count++;
				Thread.sleep(1000);
			}	
			return Counter_count;
		
	}
	public String readProperty(String key){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String filepath = System.getProperty("user.dir").split("FlipLearn")[0].trim()+"B2CData.properties";
			input = new FileInputStream(filepath);
			prop.load(input);
			return prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
