package testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.B2C;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;

public class B2CTestCases extends DriverSession  {
	B2C b2c;
	LoginPage login;
	GenericFunctions generic;
	Xls_Reader xls;
	LoadEnvProperty envpro = new LoadEnvProperty();
	

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		driver.get(BASE_URL);
		login = new LoginPage(driver);
		generic = new GenericFunctions(driver);
		xls = new Xls_Reader();
		b2c = new B2C(driver);
		generic.waitPageGotLoad();
		login.Login_RegisterLink_click();
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Login_link);
		login.ClickOnLogin_link();
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
		login.loginInToApplication("rohit.admin", "123456");
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		b2c.clickOnLearn();
		b2c.clickOnGuestImage();
	}

	@Test
	public void PreNursery() throws InterruptedException{
		String Sheetname="PreNursery";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(0).getText();
		Classes.get(0).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='15']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='15']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='15']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			Classes.get(0).click();
		}
	}

	@Test
	public void Nursery() throws InterruptedException{
		String Sheetname="Nursery";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(1).getText();
		Classes.get(1).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='14']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='14']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='14']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			Classes.get(1).click();
		}
	}

	@Test
	public void KG() throws InterruptedException{
		String Sheetname="KG";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(2).getText();
		Classes.get(2).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='13']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='13']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='13']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			Classes.get(2).click();
		}
	}

	@Test
	public void Class1() throws InterruptedException{
		String Sheetname="Class1";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(3).getText();
		Classes.get(3).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='1']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='1']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='1']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			Classes.get(3).click();
		}
	}

	@Test
	public void Class2() throws InterruptedException{
		String Sheetname="Class2";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(4).getText();
		Classes.get(4).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='2']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='2']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='2']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			Classes.get(4).click();
		}
	}

	@Test
	public void Class3() throws InterruptedException{
		String Sheetname="Class3";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(5).getText();
		Classes.get(5).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='3']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='3']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='3']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			Classes.get(5).click();
		}
	}

	@Test
	public void Class4() throws InterruptedException{
		String Sheetname="Class4";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(6).getText();
		Classes.get(6).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='4']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='4']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='1']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			Classes.get(6).click();
		}
	}
	@Test
	public void Class5() throws InterruptedException{
		String Sheetname="Class5";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(7).getText();
		Classes.get(7).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='5']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='5']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='5']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			Classes.get(7).click();
		}
	}
	@Test
	public void Class6() throws InterruptedException{
		String Sheetname="Class6";
		String subjectname="";
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(8).getText();
		scrollDown(1);
		Classes.get(8).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='6']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='6']/div/ul/a/li)["+(i+1)+"]")).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='6']/div/ul/a/li)["+(i+1)+"]")).click();
			Thread.sleep(5000);
			List<WebElement> toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
			for(int j=0;j<toc.size();j++){
				Thread.sleep(2000);
				try {
					if((j>3) && (!(toc.get(j).isDisplayed()))){
						scrollDown(1);
					}
					tocname=toc.get(j).getText();
					toc.get(j).click();
					Thread.sleep(2000);
				} catch (Exception e) {
					toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
					if((j>3) && (!(toc.get(j).isDisplayed()))){
						scrollDown(1);
					}
					tocname=toc.get(j).getText();
					toc.get(j).click();
				}
				Thread.sleep(2000);
				List<WebElement> Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					System.out.println(k);
					if(k>10){
						break;
					}
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						if(k>8){
							scrollDown(1);
						}
						Thread.sleep(2000);
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(4000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);
				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			scrollDown(5);
			Classes.get(8).click();
		}
	}
	@Test
	public void Class7() throws InterruptedException{
		String Sheetname="Class7";
		String subjectname="";
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		//	List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(9).getText();
		Classes.get(9).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='7']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='7']/div/ul/a/li)["+(i+1)+"]")).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='7']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){

					try {
						Thread.sleep(2000);
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc.get(j).click();
						Thread.sleep(1000);
						if(k>8){
							scrollDown(1);
						}
						Thread.sleep(2000);
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(4000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);
				}
			}
			driver.navigate().back();
			Thread.sleep(1000);

			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			scrollDown(9);
			Classes.get(9).click();
		}
	}
	@Test
	public void Class8() throws InterruptedException{
		String Sheetname="Class8";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		scrollDown(1);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(10).getText();
		Classes.get(10).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='8']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='8']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='8']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(9000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			scrollDown(7);
			Classes.get(10).click();
		}
	}

	@Test
	public void Class9() throws InterruptedException{
		String Sheetname="Class9";
		String subjectname="";
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(11).getText();
		Classes.get(11).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='9']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='9']/div/ul/a/li)["+(i+1)+"]")).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='9']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					if(k>8){
						scrollDown(1);
					}
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(4000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						System.out.println(counter);
						counter++;
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);
				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			scrollDown(3);
			Classes.get(11).click();
		}
	}

	@Test
	public void Class10() throws InterruptedException{
		String Sheetname="Class10";
		String subjectname="";
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(12).getText();
		Classes.get(12).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='10']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='10']/div/ul/a/li)["+(i+1)+"]")).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='10']/div/ul/a/li)["+(i+1)+"]")).click();
			Thread.sleep(5000);
			List<WebElement> toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
			for(int j=0;j<toc.size();j++){
				Thread.sleep(2000);

				try {
					if((j>3) && (!(toc.get(j).isDisplayed()))){
						scrollDown(1);
					}
					tocname=toc.get(j).getText();
					toc.get(j).click();
					Thread.sleep(2000);
				} catch (Exception e) {
					toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
					if((j>3) && (!(toc.get(j).isDisplayed()))){
						scrollDown(1);
					}
					tocname=toc.get(j).getText();
					toc.get(j).click();
				}
				Thread.sleep(2000);
				List<WebElement> Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					if(k>8){
						scrollDown(1);
					}
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(4000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);
				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			scrollDown(10);
			Classes.get(12).click();
		}
	}

	public void Class11() throws InterruptedException{
		String Sheetname="Class11";
		String subjectname="";
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		//List<WebElement> toc1 = null;
		Thread.sleep(3000);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(13).getText();
		Classes.get(13).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='11']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='11']/div/ul/a/li)["+(i+1)+"]")).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='11']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){

					try {
						Thread.sleep(2000);
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc.get(j).click();
						Thread.sleep(1000);
						if(k>8){
							scrollDown(1);
						}
						Thread.sleep(2000);
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(4000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);
				}
			}
			driver.navigate().back();
			Thread.sleep(1000);

			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			scrollDown(9);
			Classes.get(13).click();
		}
	}
	@Test
	public void Class12() throws InterruptedException{
		String Sheetname="Class12";
		String subjectname;
		String tocname,resourceName,topicName;
		int counter=2;
		loginAndClickOnB2C();
		List<WebElement> toc1 = null;
		Thread.sleep(3000);
		scrollDown(1);
		List<WebElement> Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
		String classname=Classes.get(13).getText();
		Classes.get(13).click();
		Thread.sleep(2000);
		List<WebElement> Subjects = driver.findElements(By.xpath("//div[@id='28']/div/ul/a/li"));
		System.out.println(Subjects.size());
		for(int i=0;i<Subjects.size();i++){
			subjectname=driver.findElement(By.xpath("(//div[@id='28']/div/ul/a/li)["+(i+1)+"]")).getText();
			System.out.println("subject name:"+subjectname);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@id='28']/div/ul/a/li)["+(i+1)+"]")).click();
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
				System.out.println("Resources are "+Resources.size());
				for(int k=0;k<Resources.size();k++){
					Thread.sleep(2000);
					try {
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						Resources.get(k).click();
					} catch (StaleElementReferenceException  e) {
						Thread.sleep(2000);
						toc1=driver.findElements(By.xpath("//h4[@class='panel-title ng-binding']"));
						System.out.println();
						Thread.sleep(2000);
						toc1.get(j).click();
						Thread.sleep(2000);
						if(k>8){
							scrollDown(1);
						}
						Resources=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/small/a"));
						resourceName=driver.findElements(By.xpath("//div[@id='"+(j+1)+"']/div/div/div/span")).get(k).getText();
						System.out.println("Resources2 are "+Resources.size());
						Resources.get(k).click();
					}
					List<WebElement> Topics=driver.findElements(By.xpath("//div[contains(@class,'bottom-download')]/ul"));
					for(int l=0;l<Topics.size();l++){
						topicName=driver.findElement(By.xpath("(//div[contains(@class,'bottom-download')]/ul/li[2])["+(l+1)+"]")).getText();
						xls.setCellData(Sheetname, "ClassName", counter, classname);
						xls.setCellData(Sheetname, "Subject", counter, subjectname);
						xls.setCellData(Sheetname, "Toc", counter, tocname);
						xls.setCellData(Sheetname, "Resource", counter, resourceName);
						xls.setCellData(Sheetname, "TopicName", counter, topicName);
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("((//div[contains(@class,'bottom-download')]/ul/li)/img)["+(l+1)+"]")).
								getAttribute("src").contains("video_recommendation")){
							Topics.get(l).click();
							Thread.sleep(6000);
							if(driver.getPageSource().contains("404 Not")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..404");
							}else if(driver.getPageSource().contains(": 403")){
								xls.setCellData(Sheetname, "Result", counter, "Fail..403");
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
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
										xls.setCellData(Sheetname, "Result", counter, "Pass");
									}
								}catch(Exception e){
									xls.setCellData(Sheetname, "Result", counter, "Fail,XML file found");
								}
								driver.close();
								driver.switchTo().window(browserTab.get(0));
							}else{
								xls.setCellData(Sheetname, "Result", counter, "Pass");
							}
						}
						counter++;
						System.out.println(counter);
						Thread.sleep(1000);
					}
					driver.navigate().back();
					Thread.sleep(1000);

				}
			}
			driver.navigate().back();
			Thread.sleep(1000);
			Classes=driver.findElements(By.xpath("//a[@class='accordion-toggle collapsed ng-binding']"));
			scrollDown(7);
			Classes.get(13).click();
		}
	}

	/************************************
	 * Test Case Name : To verify B2c Purchase flow for Student
	 * @author fliplearn : Vinay Yadav
	 
	 ************************************/
	@Test
	public void B2cPurchaseFlow() throws InterruptedException {
		GenericFunctions.WaitFor_visibility(driver, LoginPage.Username_Txt);
	    login.loginInToApplication(UtilityTest.getUserIDByRole("Student1"),UtilityTest.getUserPasswordByRole("Student1"));
	    generic.waitPageGotLoad();
	    if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
		login.skipMobileNumber();
		
	}
	String sheetName = "B2CResult", colName="Expected";
	String firstName=xls.getCellData(sheetName, colName, 2);
	String lastName=xls.getCellData(sheetName, colName, 3);
	String mobNo=xls.getCellData(sheetName, colName, 4);
	String emailid=xls.getCellData(sheetName, colName, 5);
	String address1=xls.getCellData(sheetName, colName, 6);
	String pincode=xls.getCellData(sheetName, colName, 7);
	String expected=xls.getCellData(sheetName, colName, 8);
	b2c.clickOnLearn();
	b2c.clickOnGuestImage();
	b2c.clickOnSubject();
	b2c.clickOnbuysubscription_lnk();
	b2c.clickOnbuy_btn();
	b2c.clickOncheckout_btn();
	b2c.enterFirstName(firstName);
	b2c.enterLasttName(lastName);
	b2c.enterEmailid(emailid);
	b2c.enterMobNo(mobNo);
	b2c.enterpincode(pincode);
	b2c.enterAddress1(address1);
	b2c.clickOnPay_btn();
	b2c.selectDebitCart_Rd();
	b2c.clickOnPayNow_btn();
	b2c.selectBankfrom_Dwn();
	b2c.clickOncancel_btn();
	Assert.assertEquals(b2c.getCancelmessage(), expected);




	}
	}
