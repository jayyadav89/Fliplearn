package pageModules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.Xls_Reader;

public class B2C {
	WebDriver driver;
	GenericFunctions generic;
	Xls_Reader xls;

	public B2C(WebDriver driver){
		this.driver = driver;
		generic = new GenericFunctions(driver);
		xls = new Xls_Reader();
	}
	String sheetName = "B2CResult";
	public static By learn_lnk = By.id("learn-icon");
	public static By homeGuest_Img = By.xpath("//*[@class='img-responsive W100']");
	public static By SchoolClass_lnk = By.xpath("//a[@class='accordion-toggle collapsed ng-binding']");
	public static By SchoolClassAfterCollapse_lnk = By.xpath("//a[@class='accordion-toggle ng-binding collapsed']");
	public static By subjectList = By.xpath("//ul[@class='learn-list ng-scope']");
	public static By selectSubject = By.xpath("//li[@class='ng-binding']");
	public static By buySubscription_Btn = By.xpath("//button[@class='btn btn-primary']");
	public static By subscriptionPage_Txt = By.xpath("//h4[contains(text(),'Buy Fliplearn Subscription and get:')]");
	public static By couponCode_Txtfield = By.xpath("//input[@placeholder='Enter Coupon']");
	public static By apply_Btn = By.xpath("//button[@class='btn btn-sm remove-coupons']");
	public static By couponcodeError_Msg = By.xpath("//div[@class='popup-content']");
	public static By invalidCouponCodeError_Msg = By.xpath("//span[@class='icon-pane']");
	public static By remove_Btn = By.xpath("//button[@class='btn btn-sm remove-coupons remove-coupn-red']");
	public static By addToCart_Btn = By.xpath("//button[@class='btn btn-default btn-add-cart']");
	public static By cart_Btn = By.xpath("//img[contains(@src,'/images/cart.png')]");
	public static By content_link = By.xpath("//h4[@class='panel-title ng-binding']");
	public static By resource_link = By.xpath("//a[@class='ng-binding']");
	public static By videoPlay_Icon = By.xpath("//img[contains(@src,'/images/latest-icon/Video_Icon.png')]");
	public static By pdf_Icon = By.xpath("//img[contains(@src,'/images/latest-icon/download-icon.png')]");
	public static By close_Btn = By.xpath("//button[@class='close']");
	public static By timer_icon = By.xpath("//span[@class='jw-text jw-reset jw-text-elapsed']");
	public static By topicName_Text = By.xpath("//li[@class='col-sm-8 float-left text-ellipses m-t-10 ng-binding']");
	public static By pdfTopicName_Text = By.xpath("//li[@class='col-sm-8 text-ellipses float-left m-t-10 ng-binding']");
	public static By tableOfContent_Text = By.xpath("//h5[@class='bg-dark-grey b-t-2']");
	public static By Content_Text = By.xpath("//h4[@class='panel-title ng-binding']");
	public static By Resource_link = By.xpath("//a[contains(text(),'Resources')]");
	public static By SubConetnt_text = By.xpath("//span[@class='resource-subject ng-binding']");
	public static By videos1=By.xpath("//img[contains(@src,'http://dwq2qrehawfaj.cloudfront')]");
    public static By Buy_btn=By.xpath("//button[text()='Buy Now']");
    public static By checkOut_btn=By.xpath("//button[text()='Checkout']");
    		




	//start from here
    public static By firstName_billingDetails=By.xpath("//input[@id='firstname']");
    public static By lastname_billingDetails=By.xpath("//input[@id='lastname']");
    public static By email_billingDetails=By.xpath("//input[@id='email']");
    public static By mobileNo_billingDetails=By.xpath("//input[@id='mobile']");
    public static By pincode_billingDetails=By.xpath("//input[@id='pincode']");
    public static By Address1_billingDetails=By.xpath("//input[@id='addressline1']");
	public static By buysubscription_lnk=By.xpath("//a[text()='Buy Subscription']");
	public static By contents_links=By.xpath("//div[@class='bottom-download']");	
	public static By class_lnk=By.xpath("//a[@class='accordion-toggle collapsed ng-binding']");
	public static By subject_lnk=By.xpath("//li[@class='ng-binding']");
	public static By subjectName=By.xpath("//a[text()='Nursery']/../../..//li[text()='Mathematics']");
	public static By tableContents=By.xpath("//h4[@class='panel-title ng-binding']");//use get
	public static By topicsTitle=By.xpath("//div[@class='panel-collapse collapse in']//span[@class='resource-subject ng-binding']");
	public static By resources=By.xpath("//div[@class='panel-collapse collapse in']//a[@class='ng-binding']");
	public static By videos=By.xpath("//div[@class='bottom-download']");
	public static By select_subject=By.xpath("(//h3[@class='ng-binding'])[1]");
	public static By BuySubscription_Btn=By.xpath("//a[contains(text(),'Buy Subscription')]");
	public static By content=By.xpath("//h6[@class='ng-binding']");
	public static By getcarttext=By.xpath("//span[@class='badge ng-binding']");
	public static By PayButton=By.xpath("//button[@class='btn btn-primary btn-block padding-12-30 ng-binding']");
	public static By debitcart_Rd=By.xpath("//label[text()='Debit Card']");
	public static By payNow_btn=By.xpath("//button[text()='Pay Now']");
	public static By paywith_Dwn=By.xpath("//li[text()='Pay with']");
	public static By selectbank=By.xpath("//a[@id='Debitlink']");
	public static By cancel_lnk=By.xpath("//a[text()='www.fliplearn.com']");
	public static By getcancelmessage=By.xpath("//h5[text()='Payment Failed!']");
	
	
	
	
	
	
	public String getCancelmessage() {
		return driver.getCurrentUrl();
		//return driver.findElement(getcancelmessage).getText();
	}
	public void clickOncancel_btn() throws InterruptedException {
		generic.scrollPageToBottom();
		generic.waitPageGotLoad();
		driver.findElements(cancel_lnk).get(1).click();
	}

	public void selectBankfrom_Dwn() throws InterruptedException {
		generic.waitForLoad(driver);
	 driver.findElement(paywith_Dwn).click();
	 generic.waitPageGotLoad();
	 driver.findElement(selectbank).click();
	}
	public void selectDebitCart_Rd() {
		driver.findElement(debitcart_Rd).click();
	}
	public void clickOnPayNow_btn(){
		driver.findElement(payNow_btn).click();
	}
	public void clickOnPay_btn(){
		driver.findElement(PayButton).click();
	}
	public void enterAddress1(String address1) {
		driver.findElement(Address1_billingDetails).clear();
		driver.findElement(Address1_billingDetails).sendKeys(address1);
		
	}

	public void enterMobNo(String mobNo) {
		driver.findElement(mobileNo_billingDetails).clear();
		driver.findElement(mobileNo_billingDetails).sendKeys(mobNo);
		
	}
	public void enterpincode(String pincode) {
		driver.findElement(pincode_billingDetails).clear();
		driver.findElement(pincode_billingDetails).sendKeys(pincode);
		
	}

	public void enterEmailid(String emailid) {
		driver.findElement(email_billingDetails).clear();
		driver.findElement(email_billingDetails).sendKeys(emailid);
		
	}
	public void enterLasttName(String LasttName) {
		driver.findElement(lastname_billingDetails).clear();
		driver.findElement(lastname_billingDetails).sendKeys(LasttName);
		
	}
	public void enterFirstName(String firstName) {
		driver.findElement(firstName_billingDetails).clear();
		driver.findElement(firstName_billingDetails).sendKeys(firstName);
		
	}
	
	public void clickOncheckout_btn(){
		driver.findElement(checkOut_btn).click();
	}
	public void clickOnbuy_btn(){
		driver.findElement(Buy_btn).click();
		if(driver.findElement(getcarttext).getText().equalsIgnoreCase("1")) {
			driver.findElement(cart_Btn).click();
			
		}
		else {
			driver.findElement(Buy_btn).click();
		}
	}
	public void clickOnbuysubscription_lnk(){
		driver.findElement(buysubscription_lnk).click();
	}
	public void clickOnSubject(){
		driver.findElement(select_subject).click();
	}
	public boolean isBuySubscription_Btn_Display(){
		if(driver.findElements(BuySubscription_Btn).size()>0){
			if(driver.findElement(BuySubscription_Btn).isDisplayed()){
				System.out.println("BuySubscription_Btn is displaying");
				return true;
			}
		}
		return false;
	}
	public String validateContent() throws InterruptedException{
		String result="";
		int contentSize = driver.findElements(content).size();
		if(!(contentSize==0)){
			int counter=0;
			if(contentSize>10){
				counter=10;
			}else{
				counter=contentSize;
			}
			int l;
			for(l=0;l<counter;l++){
				String topicType=""; 
				try {
					topicType = driver.findElement(By.xpath("(//h6[@class='ng-binding'])["+(l+1)+"]")).getText();
				} catch (Exception e) {
					scrollDown(1);
					topicType = driver.findElement(By.xpath("(//h6[@class='ng-binding'])["+(l+1)+"]")).getText();
				}
				
				if(topicType.equalsIgnoreCase("Animation")){
					try {
						driver.findElement(By.xpath("(//h6[@class='ng-binding'])["+(l+1)+"]")).click();
					} catch (Exception e) {
						scrollDown(1);
						driver.findElement(By.xpath("(//h6[@class='ng-binding'])["+(l+1)+"]")).click();
					}					
					Thread.sleep(3000);						
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
					try {
						driver.findElement(By.xpath("(//button[@class='close'])[2]")).click();
					} catch (Exception e) {
						Thread.sleep(2000);
						driver.findElement(By.xpath("(//button[@class='close'])[2]")).click();
					}
					if(!timer.equals("")){
						result=result+" Pass";
					}else{
						result=result+" Failed";
						System.out.println("video failed ---- content number" +"+(l+1)+");
					}
				}else{
					try {
						driver.findElement(By.xpath("(//h6[@class='ng-binding'])["+(l+1)+"]")).click();	
					} catch (Exception e) {
						scrollDown(1);
						driver.findElement(By.xpath("(//h6[@class='ng-binding'])["+(l+1)+"]")).click();	
					}
					
					driver.switchTo().frame("myFrame");
					try {
						if( driver.findElement(By.xpath("//div[@class='col-lg-12 col-sm-12 col-xs-12 col-md-12']/h1")).isDisplayed()){
							result=result+" Pass";
						}else{
							result=result+" Failed";
							System.out.println("Pdf content not displaying-----content number" +1+l);
						}
					} catch (Exception e) {					
						result=result+" Failed";
						System.out.println("Pdf not loaded-----content number" +1+l);
					}
					
					driver.switchTo().defaultContent();
					try {
						driver.findElement(By.xpath("(//button[@class='close'])[3]")).click();
					} catch (Exception e) {
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//button[@class='close'])[3]")).click();
					}
				}
				
			Thread.sleep(2000);
			}
		
		}
		return result;	
	}
	public void clickOnFirstSubjectChapter(){
		if(driver.findElements(By.xpath("//h4[@class='panel-title']")).size()>0){
			driver.findElement(By.xpath("(//h4[@class='panel-title'])[1]")).click();
		}
	}
	public void clickOnFirstResource(){
		if(driver.findElements(By.xpath("//li[@class='col-xs-10 col-sm-11 col-md-11 ng-binding']")).size()>0){
			driver.findElement(By.xpath("(//li[@class='col-xs-10 col-sm-11 col-md-11 ng-binding'])[1]")).click();
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




	public void tableContents() throws InterruptedException{
		int size=driver.findElements(tableContents).size();
		for(int i=0; i<1; i++){
			if(i>2){
				scrollDown(2);
			}
			driver.findElements(tableContents).get(i).click();
			Thread.sleep(4000);
			int sizeR = driver.findElements(resources).size();
			int count=0;
			for(int j=0; j<sizeR; j++){

				if(count>sizeR-1){
					//driver.findElements(tableContents).get(i).click();
					generic.scrollPageToBottom();
					System.out.println("vinay");
					Thread.sleep(10000);
					break;
				}
				count++;

				Thread.sleep(4000);
				driver.findElements(resources).get(j).click();
				int size2 = driver.findElements(videos).size();
				System.out.println(size2);
				for(int a=0; a<size2; a++){
					Thread.sleep(5000);
					driver.findElements(videos).get(a).click();
					Thread.sleep(10000);
					driver.findElements(close_Btn).get(1).click();
				}
				Thread.sleep(5000);
				driver.navigate().back();
				Thread.sleep(5000);
				driver.findElements(tableContents).get(i).click();

			}
		}
	}

	public void clickOnsubject_Btn(int index){
		GenericFunctions.WaitFor_visibility(driver, subjectName);
		driver.findElement(subjectName).click();
	}
	public void clickOncontents_lnk() throws InterruptedException{
		int size = driver.findElements(contents_links).size();
		System.out.println("size of contents avaible "+size);
		for(int i=0; i<size; i++){
			if(i>4){
				generic.scrollPage(contents_links);
			}
			try{
				Thread.sleep(3000);
				driver.findElements(contents_links).get(i).click();
				Thread.sleep(10000);
				driver.findElements(close_Btn).get(1).click();
			}
			catch(Exception e){
				System.out.println(" element is not clickable "+i);
				driver.findElements(close_Btn).get(1).click();

			}
		}

	}

	public void clickOnsubject_lnk(int index) throws InterruptedException{
		Thread.sleep(2500);
		GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(subject_lnk).get(index));
		driver.findElements(subject_lnk).get(index).click();
	}
	public void clickOnclass_lnk(int index) throws InterruptedException{
		generic.waitPageGotLoad();
		driver.findElements(class_lnk).get(index).click();
	}





	public void clickOnLearn(){
		GenericFunctions.WaitFor_visibility(driver, learn_lnk);
		driver.findElement(learn_lnk).click();
	}
	public void clickOnGuestImage() throws InterruptedException{
		generic.waitPageGotLoad();
		WebElement b2cimg = driver.findElements(homeGuest_Img).get(0);
		GenericFunctions.WaitFor_visibilityOfElements(driver, b2cimg);
		b2cimg.click();
	}
	public void selctClassAndSubject() throws InterruptedException{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, SchoolClass_lnk);
		int TotalClassInSchool = driver.findElements(SchoolClass_lnk).size();
		driver.findElements(SchoolClass_lnk).get(0).click();
		for(int i=0;i<TotalClassInSchool;i++){
			Thread.sleep(1000);
			if(!(driver.findElements(subjectList).get(i).getText()).equalsIgnoreCase("No Content Available!")){
				driver.findElement(selectSubject).click();
				break;
			}
			driver.findElements(SchoolClassAfterCollapse_lnk).get(i).click();
		}
	}
	public void clickOnBuyButton(){
		GenericFunctions.WaitFor_visibility(driver, buySubscription_Btn);
		driver.findElements(buySubscription_Btn).get(0).click();
	}
	public boolean isPageLandOnSubscription(){
		try{
			driver.findElements(subscriptionPage_Txt).get(0).isDisplayed();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public boolean isBuyButtonDisplay(){
		try{
			driver.findElement(buySubscription_Btn).isDisplayed();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public void clickOnApplyButton(){
		GenericFunctions.WaitFor_visibility(driver, apply_Btn);
		driver.findElement(apply_Btn).click();
	}
	public String getCouponErrorMsg(){
		return driver.findElement(couponcodeError_Msg).getText();
	}
	public void enterCouponCode(String Coupon){
		//GenericFunctions.WaitFor_visibility(driver, couponCode_Txtfield);
		driver.findElement(couponCode_Txtfield).clear();
		driver.findElement(couponCode_Txtfield).sendKeys(Coupon);
	}
	public String getInvalidCouponErrorMsg(){
		System.out.println(driver.findElement(invalidCouponCodeError_Msg).getText());
		return driver.findElement(invalidCouponCodeError_Msg).getText();
	}
	public void clickOnRemoveButton(){
		driver.findElement(remove_Btn).click();
	}
	public void clickOnAddToCartButton(){
		driver.findElement(addToCart_Btn).click();
	}
	public void clickOnCartIcon(){
		driver.findElement(cart_Btn).click();
	}
	public boolean isEmptyCouponErrorDisplay(String couponError){
		clickOnApplyButton();
		if(getCouponErrorMsg().equals(couponError)){
			return true;
		}else{
			return false;
		}
	}
	public boolean isInvalidCouponErrorDisplay(String couponCode, String invalidCouponError) throws InterruptedException{
		Thread.sleep(3000);
		enterCouponCode(couponCode);
		clickOnApplyButton();
		if(getInvalidCouponErrorMsg().equals(invalidCouponError)){
			return true;
		}else{
			return false;
		}
	}
	public String selctClass(int index) throws InterruptedException{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, SchoolClass_lnk);
		int TotalClassInSchool = driver.findElements(SchoolClass_lnk).size();
		System.out.println("TotalClassInSchool:"+TotalClassInSchool);
		String ClassName = driver.findElements(SchoolClass_lnk).get(index).getText();
		if(TotalClassInSchool > index){
			driver.findElements(SchoolClass_lnk).get(index).click();
		}
		return ClassName;		
	}
	public String clickOnSubject(int index){
		driver.findElements(selectSubject).get(index).click();
		return driver.findElements(selectSubject).get(index).getText();
	}
	public int clickOnResourceItem(int startRow) throws InterruptedException{
		String sheetName = "B2CResult";
		int counter=0;
		int k= startRow;
		if(driver.findElements(videoPlay_Icon).size()!=0){

			List<WebElement> list = driver.findElements(videoPlay_Icon);
			for(int x=0; x<list.size();x++){
				if(x>8){
					generic.scrollPageForMultipleElement(list.get(x));
				}			
				xls.setCellData(sheetName, "TopicName", k, driver.findElements(topicName_Text).get(x).getText());
				Thread.sleep(1000);
				list.get(x).click();
				Thread.sleep(12000);
				if(generic.isElementPresent("404 Not Found")){
					xls.setCellData(sheetName, "Result", k, "Fail, 404");
				}else if(generic.isElementPresent("403")){
					xls.setCellData(sheetName, "Result", k, "Fail, 403");
				}
				else{
					xls.setCellData(sheetName, "Result", k, "Pass");
				}
				k++;
				driver.findElements(close_Btn).get(1).click();
			}
			//return k;
		}
		if(driver.findElements(pdf_Icon).size()!=0){

			Thread.sleep(2000);
			List<WebElement> list = driver.findElements(pdf_Icon);
			for(int x=0; x<list.size();x++){
				xls.setCellData(sheetName, "TopicName", k, driver.findElements(pdfTopicName_Text).get(x).getText());
				list.get(x).click();
				Thread.sleep(1500);
				List<String> browserTab = new ArrayList<String>(driver.getWindowHandles());
				if(browserTab.size()>1){
					driver.switchTo().window(browserTab.get(1));
					Thread.sleep(1000);
					try{
						if(!generic.isElementPresent("The specified key does not exist.")){
							xls.setCellData(sheetName, "Result", k, "Pass");
						}
					}catch(Exception e){
						xls.setCellData(sheetName, "Result", k, "Fail,XML file found");
					}
					/*if(generic.isElementPresent("The specified key does not exist.")){
						xls.setCellData(sheetName, "Result", k, "Fail,XML file found");
					}else{
						xls.setCellData(sheetName, "Result", k, "Pass");
					}*/

					driver.close();
					driver.switchTo().window(browserTab.get(0));
				}else{
					xls.setCellData(sheetName, "Result", k, "Fail");
				}
				//System.out.println("the value"+k);
				k++;
			}
			//return k;		
		}
		return k;
	}
	public void selectContentClassAndClick(int startIndex, int totalTopic, int index, int startRow) throws InterruptedException{		
		int k= startRow;		
		for(int j=startIndex;j<totalTopic;j++){
			Thread.sleep(1000);
			xls.setCellData(sheetName, "ContentName", k, driver.findElements(SubConetnt_text).get(j).getText());
			//generic.scrollPageToBottom();
			Thread.sleep(1500);
			//	generic.scrollPageForMultipleElement(driver.findElements(Resource_link).get(j));
			driver.findElements(Resource_link).get(j).click();
			Thread.sleep(500);
			k=clickOnResourceItem(k);
			driver.navigate().back();
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(2000);
			GenericFunctions.WaitFor_visibility(driver, tableOfContent_Text);
			GenericFunctions.WaitFor_visibilityOfElements(driver, driver.findElements(content_link).get(index));
			driver.findElements(content_link).get(index).click();
			System.out.println(j);
			//k++;
		}
	}
}
