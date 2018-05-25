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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.B2C;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;

public class Test1 extends DriverSession {
	B2C b2c;
	LoginPage login;
	GenericFunctions generic;
	Xls_Reader xls;
	LoadEnvProperty envpro = new LoadEnvProperty();
	String sheetName = "B2CResult";
	int Counter_count=2;

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		driver.get(BASE_URL);
		login = new LoginPage(driver);
		generic = new GenericFunctions(driver);
		xls = new Xls_Reader();
		b2c = new B2C(driver);
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
	}
	

	@Test
	public void B2CTest() throws Throwable{
		String className = (String) readProperty("className");
		String subjectName = (String) readProperty("subjectName");
		String Sheetname=className;
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
				System.out.println("Subjectname: "+subjectName);
				driver.findElement(By.xpath("//a[text()='"+className+"']/../../..//li[contains(text(),'"+subjectName+"')]")).click();
				Thread.sleep(5000);
				if(!TocName.equalsIgnoreCase("")){
					System.out.println("TOCName: "+TocName);
					try {
						driver.findElement(By.xpath("//h4[text()='"+TocName+"']")).click();
						Thread.sleep(2000);
					} catch (Exception e) {
						scrollDown(1);
						driver.findElement(By.xpath("//h4[text()='"+TocName+"']")).click();
						Thread.sleep(4000);
					}
					if(!resourceName.equalsIgnoreCase("")){
						System.out.println("Resourcename:"+resourceName);
						String res=driver.findElement(By.xpath("//span[starts-with(text(),'"+resourceName+"')]")).getText();
						try {
							driver.findElement(By.xpath("//span[starts-with(text(),'"+resourceName+"')]/..//a[contains(text(),'Resources')]")).click();
						} catch (Exception e) {
							scrollDown(1);
							driver.findElement(By.xpath("//span[starts-with(text(),'"+resourceName+"')]/..//a[contains(text(),'Resources')]")).click();
						}						
						validateVideoAndPdf(Sheetname, className, subjectName, TocName, res);

					}else{
						Thread.sleep(2000);
						List<WebElement> Resources=driver.findElements(By.xpath("//h4[text()='"+TocName+"']/../../../..//div//div//a"));
						System.out.println("Resources size "+Resources.size());
						for(int k=0;k<Resources.size();k++){
							Thread.sleep(2000);
							try {
								resourcename=driver.findElements(By.xpath("//h4[text()='"+TocName+"']/../../../..//div//div//span")).get(k).getText();
								Resources.get(k).click();
							} catch (StaleElementReferenceException  e) {
								Thread.sleep(2000);
								try {
									driver.findElement(By.xpath("//h4[text()='"+TocName+"']")).click();
									Thread.sleep(2000);
								} catch (Exception e1) {
									scrollDown(1);
									driver.findElement(By.xpath("//h4[text()='"+TocName+"']")).click();
									Thread.sleep(4000);
								}
								Resources=driver.findElements(By.xpath("//h4[text()='"+TocName+"']/../../../..//div//div//a"));
								resourcename=driver.findElements(By.xpath("//h4[text()='"+TocName+"']/../../../..//div//div//span")).get(k).getText();
								System.out.println("Resources are "+resourcename);
								Resources.get(k).click();
							}
							validateVideoAndPdf(Sheetname,className, subjectName, TocName, resourcename);
							System.out.println("counter if resource not available:"+Counter_count);
							driver.navigate().back();
							Thread.sleep(1000);
						}
					}
				}else{
					Thread.sleep(5000);
					List<WebElement> toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
					for(int j=0;j<toc.size();j++){
						Thread.sleep(2000);
						if(j>3){
							scrollDown(1);
						}
						try {
							tocname=toc.get(j).getText();
							toc.get(j).click();
							Thread.sleep(2000);
						} catch (Exception e) {
							toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
							tocname=toc.get(j).getText();
							toc.get(j).click();
						}
						Thread.sleep(2000);
						List<WebElement> Resources=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//a"));
						//	System.out.println("Resources are "+Resources.size());
						for(int k=0;k<Resources.size();k++){
							Thread.sleep(2000);
							if(Resources.get(k).isDisplayed()){
								try {
									resourcename=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//span")).get(k).getText();
									Resources.get(k).click();
								} catch (StaleElementReferenceException  e) {
									Thread.sleep(2000);
									toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
									Thread.sleep(2000);
									toc1.get(j).click();
									Thread.sleep(2000);
									if(k>8){
										scrollDown(1);
									}
									Resources=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//a"));
									resourcename=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//span")).get(k).getText();
									System.out.println("Resources name "+resourcename);
									Resources.get(k).click();
								}
								validateVideoAndPdf(Sheetname, className, subjectName, tocname, resourcename);

								driver.navigate().back();
								Thread.sleep(1000);
							}else{
								System.out.println("FAIL");
								validateVideoAndPdf(Sheetname, className, subjectName, tocname, "resource not found");
							}
						}
					}


				}
			}else{
				List<WebElement> Subjects = driver.findElements(By.xpath("//a[text()='"+className+"']/../../..//div//li"));
				System.out.println(Subjects.size());
				for(int i=0;i<Subjects.size();i++){
					Thread.sleep(3000);
					subjectname=driver.findElement(By.xpath("(//a[text()='"+className+"']/../../..//div//li)["+(i+1)+"]")).getText();
					System.out.println("subject name:"+subjectname);					
					driver.findElement(By.xpath("(//a[text()='"+className+"']/../../..//div//li)["+(i+1)+"]")).click();
					Thread.sleep(5000);
					List<WebElement> toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
					for(int j=0;j<toc.size();j++){
						Thread.sleep(2000);
						if(j>3){
							scrollDown(1);
						}
						try {
							tocname=toc.get(j).getText();
							toc.get(j).click();
							Thread.sleep(2000);
						} catch (Exception e) {
							toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
							tocname=toc.get(j).getText();
							toc.get(j).click();
						}
						Thread.sleep(2000);
						List<WebElement> Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						System.out.println("Resources1 size are "+Resources.size());
						for(int k=0;k<Resources.size();k++){
							Thread.sleep(2000);
							try{
								if(Resources.get(k).isDisplayed()){
									try {
										resourcename=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//span")).get(k).getText();
										Resources.get(k).click();
									} catch (StaleElementReferenceException  e) {
										Thread.sleep(2000);
										toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
										Thread.sleep(2000);
										toc1.get(j).click();
										Thread.sleep(2000);
										if(k>8){
											scrollDown(1);
										}
										Resources=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//a"));
										resourcename=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//span")).get(k).getText();
										System.out.println("Resources name "+resourcename);
										Resources.get(k).click();
									}
									validateVideoAndPdf(Sheetname, className, subjectname, tocname, resourcename);

									driver.navigate().back();
									Thread.sleep(1000);
								}else{
									System.out.println("FAIL");
									validateVideoAndPdf(Sheetname, className, subjectname, tocname, "resource not found");
								}
							}catch (StaleElementReferenceException e) {
								toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
								Thread.sleep(2000);
								toc1.get(j).click();
								Thread.sleep(2000);
								if(k>8){
									scrollDown(1);
								}
								Resources=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//a"));
								if(Resources.get(k).isDisplayed()){
									try {
										resourcename=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//span")).get(k).getText();
										Resources.get(k).click();
									} catch (StaleElementReferenceException  e1) {
										Thread.sleep(2000);
										toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
										Thread.sleep(2000);
										toc1.get(j).click();
										Thread.sleep(2000);
										if(k>8){
											scrollDown(1);
										}
										Resources=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//a"));
										resourcename=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//span")).get(k).getText();
										System.out.println("Resources name "+resourcename);
										Resources.get(k).click();
									}
									validateVideoAndPdf(Sheetname, className, subjectname, tocname, resourcename);

									driver.navigate().back();
									Thread.sleep(1000);
								}else{
									System.out.println("FAIL");
									validateVideoAndPdf(Sheetname, className, subjectname, tocname, "resource not found");
								}
							}

						}
					}
					driver.navigate().back();
					Thread.sleep(1000);
					try {
						WebElement classnameElem=driver.findElement(By.xpath("//a[text()='"+className+"']"));
						classnameElem.click();
					} catch (Exception e) {
						scrollDown(1);
						WebElement classnameElem=driver.findElement(By.xpath("//a[text()='"+className+"']"));
						classnameElem.click();
					}

				}


			}
		}else{
			List<WebElement> Classes1 = null;
			String Sheetname1=null;
			String classname=null;
			List<WebElement> Classes=driver.findElements(By.xpath("//div[@class='panel panel-default ng-scope']/div/h4/a"));
			System.out.println("classes size"+Classes.size());
			for(int x=0;x<Classes.size();x++){			
				try {
					classname=Classes.get(x).getText();
				} catch (Exception e) {
					Classes=driver.findElements(By.xpath("//div[@class='panel panel-default ng-scope']/div/h4/a"));
					classname=Classes.get(x).getText();
				}	
				System.out.println("class: "+classname);
				Sheetname1=classname;
				System.out.println("SheetName: "+Sheetname1);
				Classes.get(x).click();
				Thread.sleep(2000);
				List<WebElement> Subjects = driver.findElements(By.xpath("//a[text()='"+classname+"']/../../..//div//li"));
				for(int i=0;i<Subjects.size();i++){
					String subjectsname = null;
					Thread.sleep(3000);
					subjectsname=driver.findElement(By.xpath("(//a[text()='"+classname+"']/../../..//div//li)["+(i+1)+"]")).getText();
					System.out.println("subject name:"+subjectsname);					
					driver.findElement(By.xpath("(//a[text()='"+classname+"']/../../..//div//li)["+(i+1)+"]")).click();
					Thread.sleep(5000);
					List<WebElement> toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
					for(int j=0;j<toc.size();j++){
						Thread.sleep(2000);
						if(j>3){
							scrollDown(1);
						}
						try {
							tocname=toc.get(j).getText();
							toc.get(j).click();
							Thread.sleep(2000);
						} catch (Exception e) {
							toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
							tocname=toc.get(j).getText();
							toc.get(j).click();
						}
						Thread.sleep(2000);
						List<WebElement> Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						System.out.println("Resources1 size are "+Resources.size());
						for(int k=0;k<Resources.size();k++){
							Thread.sleep(2000);
							if(Resources.get(k).isDisplayed()){
								try {
									resourcename=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//span")).get(k).getText();
									Resources.get(k).click();
								} catch (StaleElementReferenceException  e) {
									Thread.sleep(2000);
									toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
									Thread.sleep(2000);
									toc1.get(j).click();
									Thread.sleep(2000);
									if(k>8){
										scrollDown(1);
									}
									Resources=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//a"));
									resourcename=driver.findElements(By.xpath("//h4[text()='"+tocname+"']/../../../..//div//div//span")).get(k).getText();
									System.out.println("Resources name "+resourcename);
									Resources.get(k).click();
								}
								validateVideoAndPdf(Sheetname, classname, subjectsname, tocname, resourcename);

								driver.navigate().back();
								Thread.sleep(1000);
							}else{
								System.out.println("FAIL");
								validateVideoAndPdf(Sheetname, classname, subjectsname, tocname, "resource not found");
							}
						}

					}
					driver.navigate().back();
					Thread.sleep(1000);
					if(x>5){
						scrollDown(7);
					}
					if(i<Subjects.size()-1){
						try {
							Classes.get(x).click();
						} catch (Exception e) {
							Classes1=driver.findElements(By.xpath("//div[@class='panel panel-default ng-scope']/div/h4/a"));
							Classes1.get(x).click();
						}
					}						
				}
			}
		}
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
	public void loginAndClickOnB2C() throws InterruptedException{
		login.loginInToApplication("priyanka.teacher", "123456");
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		b2c.clickOnLearn();
		b2c.clickOnGuestImage();
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
	public String timeDiffernce(String dateStart,String dateEnd) throws Throwable{
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String TimeDiff= null;
        //HH converts hour in 24 hours format (0-23), day calculation
        Date d1 = format.parse(dateStart);
        Date d2= format.parse(dateEnd);
        //in milliseconds
        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;

        TimeDiff = Long.toString(diffHours) +"H : "+ Long.toString(diffMinutes) +"M : "+ Long.toString(diffSeconds) +"S";
        return TimeDiff;

    }
	public String stateOfJW(){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String s1=(String) jse.executeScript("jwplayer().on('play');");
		return s1;
		
	}
	public int validateVideoAndPdf(String Sheetname, String classname, String subjectname,
			String tocname,String resourceName ) throws Throwable{
		if(resourceName.equalsIgnoreCase("resource not found")){
			xls.setCellData(Sheetname, "ClassName", Counter_count, classname);
			xls.setCellData(Sheetname, "Subject", Counter_count, subjectname);
			xls.setCellData(Sheetname, "Toc", Counter_count, tocname);
			xls.setCellData(Sheetname, "Resource", Counter_count, resourceName);
			xls.setCellData(Sheetname, "Result", Counter_count, "Fail..resource not available");
			Counter_count++;
			return Counter_count;
		}else{
			List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
			for(int l=0;l<Topics.size();l++){
				String topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
				xls.setCellData(Sheetname, "ClassName", Counter_count, classname);
				xls.setCellData(Sheetname, "Subject", Counter_count, subjectname);
				xls.setCellData(Sheetname, "Toc", Counter_count, tocname);
				xls.setCellData(Sheetname, "Resource", Counter_count, resourceName);
				xls.setCellData(Sheetname, "TopicName", Counter_count, topicName);
				Thread.sleep(2000);
				if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
						getAttribute("src").contains("video_recommendation")){
					Topics.get(l).click();
					DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
					Date date = new Date();
					String startTime =dateformat.format(date);
					System.out.println("current time: "+startTime);
					
					
					Thread.sleep(6000);
					JavascriptExecutor jse = (JavascriptExecutor) driver;
				//	jse.executeScript("jwplayer().pause()");
					Thread.sleep(2000);
					
					System.out.println("state:"+stateOfJW());
					
					Thread.sleep(2000);
				//	jse.executeScript("jwplayer().play();");
					Thread.sleep(2000);
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					WebElement ele= driver.findElement(By.xpath("//div//video[@class='jw-video jw-reset']"));
					Actions actions = new Actions(driver);
					actions.moveToElement(ele).build().perform();
					int i=0;
					String timer="";
					while((timer.equalsIgnoreCase("")||timer.equalsIgnoreCase("00:00")) && i<20){
						Thread.sleep(500);
						timer = driver.findElement(By.xpath("//div[@class='jw-group jw-controlbar-left-group jw-reset']/span")).getText();
						//	System.out.println("Timer:"+timer);
						i++;
					}
					System.out.println("Text: "+timer);	
				//	Thread.sleep(6000);
					if(driver.getPageSource().contains("404 Not")){
						xls.setCellData(Sheetname, "Result", Counter_count, "Fail..404");
					}else if(driver.getPageSource().contains(": 403")){
						xls.setCellData(Sheetname, "Result", Counter_count, "Fail..403");
					}else{
						xls.setCellData(Sheetname, "Result", Counter_count, "Pass");
						DateFormat dateformat1 = new SimpleDateFormat("HH:mm:ss");
						Date date1 = new Date();
						String endtime=dateformat1.format(date1);
						System.out.println("current time: "+endtime);
						String bufferTime = timeDiffernce(startTime, endtime);
						System.out.println("Difference of time: "+ bufferTime);
						xls.setCellData(Sheetname, "BufferTime", Counter_count,bufferTime);
					}
					try {
						driver.findElement(By.xpath(".//*[@id='demo-video']/div/div/div[1]/button")).click();
					} catch (Exception e) {
						Thread.sleep(3000);
						driver.findElement(By.xpath(".//*[@id='demo-video']/div/div/div[1]/button")).click();
					}
				}else if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
						getAttribute("src").contains("gallery-pic.png")){
					Topics.get(l).click();
					Thread.sleep(3000);
					List<String> browserTab = new ArrayList<String>(driver.getWindowHandles());
					if(browserTab.size()>1){
						driver.switchTo().window(browserTab.get(1));
						Thread.sleep(1000);
						try{
							if(!generic.isElementPresent("The specified key does not exist.")){
								xls.setCellData(Sheetname, "Result", Counter_count, "Pass");
							}
						}catch(Exception e){
							xls.setCellData(Sheetname, "Result", Counter_count, "Fail,XML file found");
						}
						driver.close();
						driver.switchTo().window(browserTab.get(0));
					}else{
						xls.setCellData(Sheetname, "Result", Counter_count, "Pass");
					}
				}

				System.out.println(Counter_count);
				Counter_count++;
				Thread.sleep(1000);
			}	
			return Counter_count;
		}
	}
}
