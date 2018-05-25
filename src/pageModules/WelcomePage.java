package pageModules;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import helper.GenericFunctions;

public class WelcomePage {

	GenericFunctions generic;
	static WebDriver driver;

	public WelcomePage(WebDriver driver) {

		this.driver = driver; 
		generic = new GenericFunctions(driver);

	}

	// these xpath for redirect to facebook, linkdin,youtube pages
	public static By facebook_btn = By.xpath("//i[@class='fa fa-facebook']");
	public static By Linkdin_btn = By.xpath("//i[@class='fa fa-linkedin']");
	public static By Youtube_btn = By.xpath("//i[@class='fa fa-youtube']");

	// these xpath for Header buttons
	// index 1
	public static By Home_btn = By.xpath("//a[text()='Home']");
	// index 1
	public static By Our_products_btn = By.xpath("//a[text()='Our Products']");
	// index 1
	public static By career_btn = By.xpath("//a[text()='Careers']");
	// index 1
	public static By contact_btn = By.xpath("//a[text()='Contact Us']");

	// andorid and IOS Application download link xpath
	public static By Android_Appliation_Link_btn = By.xpath("//span[@class='fa fab fa-android']");
	public static By IOS_application_Link_btn = By.xpath("//span[@class='fa fab fa-apple']");

	// Are you a ? button xpath
	// index one
	public static By School_learder_principle_btn = By.xpath(
			"//a[@class='vc_general vc_btn3 vc_btn3-size-sm vc_btn3-shape-rounded vc_btn3-style-outline-custom']");
	// index two
	public static By School_Student_parent_btn = By.xpath(
			"//a[@class='vc_general vc_btn3 vc_btn3-size-sm vc_btn3-shape-rounded vc_btn3-style-outline-custom']");

	// How Fliplearn can benefit you? button xpath

	// index 1
	public static By student_readmore_btn = By.xpath("//a[@class='vc_general vc_btn3 vc_btn3-size-xs vc_btn3-shape-rounded vc_btn3-style-custom']");
	// index 2
	public static By parent_readmore_btn = By
			.xpath("//a[@class='vc_general vc_btn3 vc_btn3-size-xs vc_btn3-shape-rounded vc_btn3-style-custom']");


	// our product

	// index 1
	public static By Fliplearn_smart_school_readmore = By.xpath("//a[@class='btn btn-default btn-plan']");
	// index 2
	public static By Fliplearn_prime_readmore = By.xpath("//a[@class='btn btn-default btn-plan']");
	// index 3
	public static By Fliplearn_flipstore_readmore = By.xpath("//a[@class='btn btn-default btn-plan']");
	// index 4
	public static By Fliplearn_vidyamandir_readmore = By.xpath("//a[@class='btn btn-default btn-plan']");

	// schools'speak scroll
	// right side xpath
	public static By forward_scroll_Btn = By.xpath("//i[@class='fa-icon-stm_icon_chevron_right']");
	// left side xpath
	public static By Backward_scroll_btn = By.xpath("//i[@class='fa-icon-stm_icon_chevron_left']");

	// Quick connect farm xpath

	public static By yourname_txt_field = By.xpath("//input[@class='wpcf7-form-control wpcf7-text wpcf7-validates-as-required form-control']");
	public static By your_emial_txt_field = By.xpath("//input[@class='wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email form-control']");
	public static By your_phone_txt_field = By.xpath("//input[@class='wpcf7-form-control wpcf7-text wpcf7-tel wpcf7-validates-as-required wpcf7-validates-as-tel form-control']");
	public static By Leave_your_message = By.xpath("//textarea[@class='wpcf7-form-control wpcf7-textarea wpcf7-validates-as-required form-control']");
	public static By form_submit_Btn = By.xpath("//button[@class='btn btn-default blue']");

	/**
	 * this method is use for facebook media button
	 */
	public void facebookMediaClickBtn() {
		driver.findElement(facebook_btn).click();
	}

	/**
	 * this method is use for Linkdin media button
	 */
	public void linkdinMediaClickBtn() {
		driver.findElement(Linkdin_btn).click();
	}

	/**
	 * this method is use for youtube media button
	 */
	public void youtubeMediaClickBtn() {
		driver.findElement(Youtube_btn).click();
	}

	public void homeButtonClick() {
		driver.findElements(Home_btn).get(1).click();

	}

	public void ourProductButton() throws InterruptedException {
		driver.findElements(Our_products_btn).get(1).click();
		generic.GoToSleep(3000);
		driver.findElements(By.xpath("//a[text()='Fliplearn Smart School']")).get(1).click();
		generic.GoToSleep(3000);
		generic.scrollPageToBottom();
		generic.GoToSleep(3000);
		driver.navigate().back();
		generic.isPageGotLoad();
		driver.findElements(Our_products_btn).get(1).click();
		generic.GoToSleep(3000);
		driver.findElements(By.xpath("//a[text()='Fliplearn Prime']")).get(1).click();
		generic.GoToSleep(6000);
		generic.scrollPageToBottom();
		generic.GoToSleep(3000);
		driver.navigate().back();
		generic.isPageGotLoad();
		driver.findElements(Our_products_btn).get(1).click();
		generic.GoToSleep(3000);
		driver.findElements(By.xpath("//a[text()='FlipStore']")).get(1).click();
		generic.GoToSleep(6000);
		generic.scrollPageToBottom();
		generic.GoToSleep(2000);
		driver.navigate().back();
		generic.isPageGotLoad();
		driver.findElements(Our_products_btn).get(1).click();
		generic.GoToSleep(3000);
		driver.findElements(By.xpath("//a[text()='Vidyamandir Classes @ Fliplearn']")).get(1).click();
		generic.GoToSleep(6000);
		generic.scrollPageToBottom();
		generic.GoToSleep(2000);
	}

	public void careerButton() {
		driver.findElements(career_btn).get(1).click();
	}

	public void contactButton() {
		driver.findElements(contact_btn).get(1).click();
	}

	// Andorid application download button
	public void AndoridApplicitonDownload() {
		driver.findElement(Android_Appliation_Link_btn).click();
	}

	// IOs applicaiton button
	public void IOSApplicitonDownload() {
		driver.findElement(IOS_application_Link_btn).click();
	}

	public void SchoolApplicationProductDetail() {
		driver.findElements(School_learder_principle_btn).get(1).click();
		generic.isPageGotLoad();
		generic.GoToSleep(2000);
		generic.scrollPageToBottom();
		generic.GoToSleep(3000);

	}

	public void StudenParentApplicationProductDetail() {
		driver.findElements(School_Student_parent_btn).get(1).click();
		generic.isPageGotLoad();
		generic.GoToSleep(2000);
		generic.scrollPageToBottom();
		generic.GoToSleep(3000);
	}
	
	// Readmore Functionality
	public void studentReadMore() {

		generic.scrollPage(By.xpath("//img[@class='vc_single_image-img attachment-medium']"));
		generic.GoToSleep(4000);
		driver.findElements(student_readmore_btn).get(0).click();
		generic.scrollPageToBottom();
		generic.GoToSleep(3000);
	}

	public void parentReadMore() {
		generic.scrollPage(By.xpath("//img[@class='vc_single_image-img attachment-medium']"));
		generic.GoToSleep(4000);
		driver.findElements(parent_readmore_btn).get(1).click();
		generic.scrollPageToBottom();
		generic.GoToSleep(3000);
		
	}


	public void fliplearnSmartSchoolReadmore() {
		generic.scrollPage(By.xpath("//img[@class='alignnone size-full wp-image-3458']"));
		generic.GoToSleep(4000);
		driver.findElements(Fliplearn_smart_school_readmore).get(0).click();

	}

	public void fliplearnPrimeReadmore() {
		generic.isPageGotLoad();
		generic.scrollPage(By.xpath("//img[@class='alignnone size-full wp-image-3458']"));
		generic.GoToSleep(4000);
		driver.findElements(Fliplearn_prime_readmore).get(1).click();
		generic.isPageGotLoad();

	}

	public void fliplearnFlipstoreReadmore() {
		generic.isPageGotLoad();
		generic.scrollPage(By.xpath("//img[@class='alignnone size-full wp-image-3458']"));
		generic.GoToSleep(4000);
		driver.findElements(Fliplearn_flipstore_readmore).get(2).click();
		generic.isPageGotLoad();

	}

	public void fliplearnVidyamandirReadmore() {
		generic.isPageGotLoad();
		generic.scrollPage(By.xpath("//img[@class='alignnone size-full wp-image-3458']"));
		generic.GoToSleep(4000);
		driver.findElements(Fliplearn_vidyamandir_readmore).get(3).click();
		generic.isPageGotLoad();

	}

	public void schoolspeek() {
		generic.scrollPage(By.xpath("//div[@class='vc_custom_heading vc_custom_1521011858716']"));
		
		driver.findElement(forward_scroll_Btn).click();
		generic.GoToSleep(1000);
		driver.findElement(forward_scroll_Btn).click();
		generic.GoToSleep(1000);
		driver.findElement(forward_scroll_Btn).click();
		generic.GoToSleep(1000);		
		driver.findElement(forward_scroll_Btn).click();
		generic.GoToSleep(1000);
		driver.findElement(Backward_scroll_btn).click();
		generic.GoToSleep(1000);
		driver.findElement(Backward_scroll_btn).click();
		generic.GoToSleep(1000);
		driver.findElement(Backward_scroll_btn).click();
		generic.GoToSleep(1000);
		driver.findElement(Backward_scroll_btn).click();
	}

	public void quickConnectFarm(String Name, String Email, String message , String Phone) {

		
		generic.scrollPage(By.xpath("//h3[text()='QUICK CONNECT']"));
	
		//generic.scrollPageToBottom();
		generic.GoToSleep(3000);
		
		
		System.out.println("this is a Query farm for fliplearn shubham verma");
	
		driver.findElement(yourname_txt_field).sendKeys(Name);
		
		
		driver.findElement(your_emial_txt_field).sendKeys(Email);

		driver.findElement(your_phone_txt_field).sendKeys(Phone);
		
		driver.findElement(Leave_your_message).sendKeys(message);
		
		generic.GoToSleep(3000);
		
		
		driver.findElement(form_submit_Btn).click();

		System.out.println("Form submit success full");
	}

}
