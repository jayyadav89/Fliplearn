package pageModules;

import java.util.List;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.Xls_Reader;

public class BookStore {
	static WebDriver driver;
	Xls_Reader xls;
	GenericFunctions generic;

	public BookStore(WebDriver driver){
		BookStore.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By booksImage=By.xpath("//div[@class='figure']");
	public static By viewDetail_lnk=By.xpath("//div[@class='figcaption']");
	public static By itemName=By.xpath("//h4//a");
	public static By itemPrice=By.xpath("//div[@class='pricefont']");
	public static By itemNameOnProductDetails=By.xpath("//span[@class='base']");
	public static By itemPriceOnProductDetails=By.xpath("//span[@class='price']");

	/*
	 * xpath start
	 */


	public static By buyBook_Btn=By.xpath("//a[text()='Buy Books']");
	//public static By school_DD=By.xpath("//select[@class='school']");// use get
	public static By school_DD=By.xpath("//select[@name='school']");
	public static By proceed_button=By.xpath("//input[@class='submit-button']");
	public static By viewDetails_Btn=By.xpath("//img[@class='imgclass']");//use get()
	public static By priceFont=By.xpath("//div[@class='pricefont']");
	public static By addCard_Btn=By.xpath("//span[text()='Add to Cart']");
	public static By class_DD=By.xpath("//select[@id='classinput']");
	public static By cartArrow_Lnk=By.xpath("//i[@class='uk-icon-cart-arrow-down']");
	public static By book_Lnk=By.xpath("//tbody[@class='cart item']//a[contains(text(),'kkam-kit')]");
	public static By price_ShoppingCart=By.xpath("//td[@class='col price']//span[@class='price']");
	public static By subTotal_ShoppingCart=By.xpath("//td[@class='col subtotal']//span[@class='price']");
	public static By qty_Text=By.xpath("//input[@class='input-text qty']");
	public static By clearShoppingCart_Btn=By.xpath("//span[text()='Clear Shopping Cart']");
	public static By updateShoppingCart_Btn=By.xpath("//span[text()='Update Shopping Cart']");
	public static By continueShopping_Btn=By.xpath("//span[text()='Continue Shopping']");
	public static By errorMessage_ToAddPSBBMiIlennium=By.xpath("//div[contains(text(),'You have added The PSBB MiIle')]");
	public static By delete_Lnk_ShoppingCart=By.xpath("//a[@class='action action-delete']");
	public static By proceedToCheckOut_Btn=By.xpath("//span[text()='Proceed to Checkout']");
	public static By orderTotalPrice_ShpoppingCart=By.xpath("//tr[@class='grand totals']//span[@class='price']");
	public static By flateRate_ShoppingCart=By.xpath("//tr[@class='totals shipping excl']//span[@class='price']");
	public static By shippingAddress=By.xpath("//div[@class='shipping-address-item selected-item']");
	public static By orderSummary_Btn=By.xpath("//div[@class='title']");
	public static By viewDetails_Btn_OrderSummary=By.xpath("//span[text()='View Details']");
	public static By addNewAddress_Btn=By.xpath("//span[contains(@data-bind,'New Address')]");
	public static By firstName_Text=By.xpath("//input[@name='firstname']");
	public static By lastName_Text=By.xpath("//input[@name='lastname']");
	public static By address_Text=By.xpath("//input[@id='FSKNI1L']");
	public static By city_Text=By.xpath("//input[@name='city']");
	public static By state_DD=By.xpath("//select[@name='region_id']");
	public static By postalCode_Text=By.xpath("//input[@name='postcode']");
	public static By country_DD=By.xpath("//select[@name='country_id']");
	public static By PhoneNo_Text=By.xpath("//input[@name='telephone']");
	public static By email_Text=By.xpath("//input[@name='email']");
	public static By saveAddress_Rd=By.xpath("//input[@id='shipping-save-in-address-book']");
	public static By cancel_Btn_ShippingAddress=By.xpath("//span[text()='Cancel']");
	public static By savedAddress_Btn=By.xpath("//span[text()='Save Address']");
	public static By errorMessage_FieldReq=By.xpath("//div[text()='This is a required field.']");//use getText();
	public static By errorMessage_PleaseSelectOpn=By.xpath("//div[text()='Please select an option.']");
	public static By userName_CustomerLogin=By.xpath("//input[@name='login[username]']");
	public static By pwd_CustomerLogin=By.xpath("//input[@name='login[password]']");
	public static By sign_Btn_CustomerLogin=By.xpath("//div[@class='login-container']//span[text()='Sign In']");
	public static By signUp_Btn_CustomerLogin=By.xpath("//a[text()='click here']");
	public static By messageForSignUp=By.xpath("//div[text()='If you do not have fliplearn account, Please ']");
	public static By next_Btn=By.xpath("//button[@class='button action continue primary']");
	public  static By myBillingAndShiping_Rd=By.xpath("//input[@id='billing-address-same-as-shipping-hdfc']");
	public static By myBillingAndShipping_Dd=By.xpath("//select[@name='billing_address_id']");
	public static By firstName_Text_PaymentPage=By.xpath("//fieldset[@id='billing-new-address-form']//input[@name='firstname']");
	public static By lastName_Text_PaymentPage=By.xpath("//fieldset[@id='billing-new-address-form']//input[@name='lastname']");
	public static By Address_Text_PaymentPage=By.xpath("//fieldset[@id='billing-new-address-form']//input[@name='street[0]']");
	public static By city_Text_PaymentPage=By.xpath("//fieldset[@id='billing-new-address-form']//input[@name='city']");
	public static By state_Dd_PaymentPage=By.xpath("//fieldset[@id='billing-new-address-form']//select[@name='region_id']");
	public static By postalCode_Text_PaymentPage=By.xpath("//fieldset[@id='billing-new-address-form']//input[@name='postcode']");
	public static By country_Dd_PaymentPage=By.xpath("//fieldset[@id='billing-new-address-form']//select[@name='country_id']");
	public static By phoneNo_Text=By.xpath("//fieldset[@id='billing-new-address-form']//input[@name='telephone']");
	public static By SavedAddress_Rd_PaymentPage=By.xpath("//fieldset[@id='billing-new-address-form']//input[contains(@id,'billing-')]");
	public static By creditCard_Rd_PaymentPage=By.xpath("//input[@value='creditcard']");
	public static By debitCard_Rd_PaymentPage=By.xpath("//input[@value='debitcard']");
	public static By netBanking_Rd_PaymentPage=By.xpath("//input[@value='netbanking']");
	public static By cashCard_Rd_PaymentPage=By.xpath("//input[@value='cashcard']");
	public static By cancel_Btn_PaymentPage=By.xpath("//span[text()='Cancel']");
	public static By update_Btn_PaymentPage=By.xpath("//span[text()='Update']");
	public static By edit_Btn_paymentPage=By.xpath("//button[@data-bind='click: back']");
	public static By applyDiscountcode_Btn_PaymentPage=By.xpath("//span[text()='Apply Discount Code']");
	public static By discount_Text_PaymentPage=By.xpath("//input[@id='discount-code']");
	public static By applyDiscount_Btn_PaymentPage=By.xpath("//span[text()='Apply Discount']");
	public static By errorDiscountMessage_Payment=By.xpath("//div[text()='This is a required field.']");
	public static By pincode_Text=By.xpath("//input[@id='pincode']");
	public static By check_Lnk=By.xpath("//a[text()='Check']");
	public static By errorPincodeMessage=By.xpath("//div[text()='Please enter a valid pincode.']");
	public static By addToCard_Btn=By.xpath("//span[text()='Add to Cart']");
	public static By buyNow_Btn=By.xpath("//button[@id='buy-now']//span[text()='Buy Now']");
	public static By studentName_Text=By.xpath("//input[@name='studentName']");//use get();
	public static By errorMessageStudentRequired=By.xpath("//div[text()='Student name is required!']");
	public static By increaseQnt_Btn=By.xpath("//li[text()='+']");
	public static By qnty=By.xpath("//input[@id='tmpqty']");
	public static By decreaseQnty=By.xpath("//li[text()='-']");
	public static By studentName_Text_Viedtails=By.xpath("//input[@id='student']");
	public static By addToCart_Btn_ViewDetails=By.xpath("//span[text()='Add to Cart']");
	public static By buyNow_Btn_ViewDetails=By.xpath("//span[text()='Buy Now']");	
	public static By errorMessage_StudentRequired_ViewDetail=By.xpath("//div[text()='Student name is required!']");
	public static By fliplearnSku_viewDetails=By.xpath("//div[@itemprop='sku']");
	public static By inStockMessage=By.xpath("//span[text()='In stock']");
	public static By productAddToshoppingCartMessage=By.xpath("//div[@data-bind='html: message.text']");
	public static By homeText=By.xpath("//a[@ui-sref='prelogin']");// use get()
	public static By homeTextdropdown=By.xpath("//a[@class='dropdown-toggle']");
	public static By emptyCardMessage=By.xpath("//p[text()='You have no items in your shopping cart.']");
	public static By studentName=By.xpath("//b[text()='Student Name']");
	public static By viewDetailsForText=By.xpath("//div[@class='figcaption']");
	public static By getproductName_ShoppingCart=By.xpath("//strong[@class='product-item-name']//a[contains(@href,'.fliple')]");
	public static By getstudentName_ShoppingCart=By.xpath("//strong[contains(text(),'vinay')]");
	public static By productName_Home=By.xpath("//h4//a[contains(@href,'fliplearn.com/books/')]");
	public static By facebook_links=By.xpath("//a[contains(@class,'uk-icon-facebook')]");
	public static By twitter_links=By.xpath("//a[contains(@class,'uk-icon-twitter')]");
	public static By youtube_links=By.xpath("//a[contains(@class,'uk-icon-youtube')]");
	public static By linkedin_links=By.xpath("//a[contains(@class,'uk-icon-linkedin')]");
	public static By contactUs_links=By.xpath("//a[contains(@href,'.com/onboarding/contact')]");
	public static By termandCondition_links=By.xpath("//a[contains(@href,'erms-and-conditions')]");
	public static By license_links=By.xpath("//a[contains(@href,'earn.com/user-license')]");
	public static By payment_links=By.xpath("//a[contains(@href,'ing/fee-payment/fee')]");
	public static By mouseHover=By.xpath("//footer[@id='footer']");
	public static By errorAtleastthreechracter=By.xpath("//div[@class='mage-error']");
	public static By address_Text1And2=By.xpath("//input[@class='input-text']");	
	public static By edit_Btn=By.xpath("//span[text()='Edit']");
	public static By edit_Btn_Payment=By.xpath("//button[@data-bind='click: back']");
	public static By shipHere_Btn_Payment=By.xpath("//span[text()='Ship Here']");
	public static By shipAddressContents=By.xpath("//div[@class='shipping-information-content']");
	public static By shipAddressContentsExp=By.xpath("//div[@class='shipping-address-item selected-item']");
	public static By usnStudentId_Text=By.xpath("//input[@name='usn']");
	public static By errormessage_UsnRequired=By.xpath("//div[@data-bind='html: message.text']");
	public static By usnStudentId_Text_viewDetails=By.xpath("//input[@id='usnName']");
	public static By placeOrder_button=By.xpath("//button[@class='action primary checkout']");



 

	public void clickOnPlaceOrder_Btn() throws InterruptedException{
		generic.waitPageGotLoad();
		driver.findElement(placeOrder_button).click();
	}
	public String geterrormessage_UnsRequired(){
		return driver.findElement(errormessage_UsnRequired).getText();
	}


	public String getExpectedshipAddressContents_ShippingAddress() throws InterruptedException{
		generic.waitPageGotLoad();
		return driver.findElement(shipAddressContentsExp).getText();
	}
	public String getshipAddressContents_ShippingAddress(){
		return driver.findElements(shipAddressContents).get(0).getText();
	}

	public void clickOnShiphere_Btn_Payment(int index){
		driver.findElements(shipHere_Btn_Payment).get(index).click();
	}
	public void clickOnEdit_Btn_Payment(){
		driver.findElement(edit_Btn_Payment).click();
	}
	public void clickOnEdit_Btn_ShippingAddress(){
		driver.findElement(edit_Btn).click();
	}
	public String getEdit_Text_ShippingAddress(){
		return driver.findElement(edit_Btn).getText();
	}
	
	public String geterrorAtleastThreeChracter(){
		return driver.findElements(errorAtleastthreechracter).get(0).getText();
	}
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}


	public void clickOnpaymentLinks() throws InterruptedException{
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		driver.findElement(payment_links).click();

	}



	public void clickOnlicenseLinks() throws InterruptedException{
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		driver.findElement(license_links).click();

	}

	public void clickOntermconditionsLinks() throws InterruptedException{
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		driver.findElement(termandCondition_links).click();

	}
	public void clickOncontactusLinks() throws InterruptedException{
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		driver.findElement(contactUs_links).click();

	}
	public void clickOnlinkedinLinks() throws InterruptedException{
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		driver.findElement(linkedin_links).click();

	}
	public void clickOnyoutubeLinks() throws InterruptedException{
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		driver.findElement(youtube_links).click();

	}
	public void clickOntwitterLinks() throws InterruptedException{
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		driver.findElement(twitter_links).click();

	}
	public void clickOnFaceBookLinks() throws InterruptedException{
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		driver.findElement(facebook_links).click();

	}
	public String getproductName_Home(int index) throws InterruptedException{
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElements(productName_Home).get(index)).perform();
		Thread.sleep(2000);
		return driver.findElements(productName_Home).get(index).getText();
	}
	public String getProductName_ShoppingCart(int index){
		return driver.findElements(getproductName_ShoppingCart).get(1).getText();
	}

	public String getStudentName_ShoppingCart(){
		return driver.findElement(getstudentName_ShoppingCart).getText();
	}
	public static void scrollPageByIndex(int a, int b){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scrollBy("+a+","+b+")");
		//jse.executeScript("scroll(0,500)");

	}
	public void backPage(){
		driver.navigate().back();
	}
	public void refresh(){
		driver.navigate().refresh();
	}
	public String getStudentName(){
		try {
			return driver.findElement(studentName).getText().substring(0, 7);
		} catch (Exception e) {
			return "";
		}

	}
	public int getviewDetails_Btn(){
		return driver.findElements(viewDetails_Btn).size();
	}

	public String getemptyMessage(){
		return driver.findElement(emptyCardMessage).getText();
	}
	public String gethomeTextdropdown(int index){
		return driver.findElements(homeTextdropdown).get(index).getText();
	}
	public String gethomeText(int index){
		return driver.findElements(homeText).get(index).getText();
	}

	public String getproductAddToshoppingCartMessage() throws InterruptedException{
		generic.waitForLoad(driver);
		Thread.sleep(2000);
		return driver.findElement(productAddToshoppingCartMessage).getText();
	}

	public void clickOnbuyBook_Btn(){
		WebElement element = driver.findElement(buyBook_Btn);
		generic.WaitFor_clickable(driver, element);
		element.click();
	}
	public String getinStockMessage(){
		return driver.findElement(inStockMessage).getText();
	}
	public String getfliplearnSku_viewDetails(){
		return driver.findElement(fliplearnSku_viewDetails).getText();
	}
	public String geterrorMessage_StudentRequired_ViewDetail(){
		return driver.findElement(errorMessage_StudentRequired_ViewDetail).getText();
	}
	public void clickOnbuyNow_Btn_ViewDetails(){
		driver.findElement(buyNow_Btn_ViewDetails).click();
	}
	public void clickOnAddToCart_Btn_ViewDetails(){
		driver.findElement(addToCart_Btn_ViewDetails).click();
	}
	public void enterstudent_Text_viewDetails(String value){
		driver.findElement(studentName_Text_Viedtails).sendKeys(value);

	}
	public void clickOndecrease_Btn(){
		driver.findElement(decreaseQnty).click();
	}
	public String getqnty(){
		return driver.findElement(qnty).getText();
	}
	public void clickOnIncreaseQnty_Btn(){
		driver.findElement(increaseQnt_Btn).click();
	}
	public void selectShool_DD(String value){
		WebElement schooldrop = driver.findElements(school_DD).get(0);
		Select select= new Select(schooldrop);
		//select.selectByIndex(0);
		select.selectByVisibleText(value);
		//generic.selectFromDropDownWithValue(schooldrop, value);
		driver.findElement(proceed_button).click();
		
	}
	public String geterrorMessageStudentRequired() throws InterruptedException{
		generic.waitForLoad(driver);
		return driver.findElement(errorMessageStudentRequired).getText();
	}
	public boolean clickOnViewDetails(int index) throws InterruptedException{
		Actions action = new Actions(driver);
		if(isStockAvailable(index)){
			Thread.sleep(2000);
			action.moveToElement(driver.findElements(viewDetails_Btn).get(index)).click().perform();
			return true;
		}else{
			System.out.println("Product was out of stock and index of that product is "+index);
			return false;
		}
	}
	public boolean isProductInStock(int index) throws InterruptedException{
		Actions action = new Actions(driver);
		if(isStockAvailable(index)){
			//	Thread.sleep(1000);
			generic.waitPageGotLoad();
			action.moveToElement(driver.findElements(viewDetails_Btn).get(index)).perform();
			return true;
		}else{
			System.out.println("Product was out of stock and index of that product is "+index);
			return false;
		}
	}

	public boolean isStockAvailable(int index) throws InterruptedException{
		Actions action = new Actions(driver);
		List<WebElement> elements = driver.findElements(viewDetailsForText);
		action.moveToElement(driver.findElements(viewDetails_Btn).get(index)).perform();
		Thread.sleep(2000);
		if(elements.get(index).getText().equalsIgnoreCase("VIEW DETAILS")){
			return true;
		}
		else{
			return false;
		}
	}


	public String getPriceFont(){
		return driver.findElement(priceFont).getText();
	}
	public void clickOnAddCard_Btn(int index){
		driver.findElements(addCard_Btn).get(index).click();
		generic.waitForLoad(driver);
	}
	public void selectClassType_DD(String value){
		WebElement selectclass = driver.findElement(class_DD);
		Select select=new Select(selectclass);
		select.selectByVisibleText(value);
		//generic.selectFromDropDownWithValue(class_DD, value);
	}
	public void clickOnCartArrow_Lnk() throws InterruptedException{
        generic.waitPageGotLoad();
		driver.findElement(cartArrow_Lnk).click();
	}
	public void clickOnBook_Lnk(){
		driver.findElement(book_Lnk).click();
	}
	public String getPrice_ShoppingCart(){
		return driver.findElement(price_ShoppingCart).getText();

	}
	public String getSubTotal_ShoppingCart(){
		return driver.findElement(subTotal_ShoppingCart).getText();

	}
	public void enterQty_Text_ShoppingCart(String value){
		driver.findElement(qty_Text).clear();
		driver.findElement(qty_Text).sendKeys(value);	
	}

	public void clickOnClearShoppingCart_Btn(){
		driver.findElement(clearShoppingCart_Btn).click();
	}

	public void clickOnUpdateShoppingCart_Btn(){
		driver.findElement(updateShoppingCart_Btn).click();
	}
	public void clickOndeleteShoppingCart_Lnk(){
		driver.findElement(delete_Lnk_ShoppingCart).click();
	}	
	public void clickOnContinueShoppingCart_Btn(){
		driver.findElement(continueShopping_Btn).click();
	}
	public String getErrorMessage_ToAddPSBBMiIlennium(){
		return driver.findElement(errorMessage_ToAddPSBBMiIlennium).getText();

	}

	public void clickOnProceedToCheckOut_Btn(){
		driver.findElement(proceedToCheckOut_Btn).click();
	}
	public String getOrderTotalPrice_ShpoppingCard(){
		return driver.findElement( orderTotalPrice_ShpoppingCart).getText();
	}
	public String getFlateRate_ShpoppingCart(){
		return driver.findElement(flateRate_ShoppingCart).getText();
	}
	public String getShippingAdress(){
		return driver.findElement(shippingAddress).getText();
	}

	public void clickOnOrderSummary_Btn(){
		driver.findElement(orderSummary_Btn).click();
	}

	public void clickOnViewDetails_Btn_OrderSummary(){
		driver.findElement(viewDetails_Btn_OrderSummary).click();
	}
	public void clickOnAddNewAddress_Btn() throws InterruptedException{
		generic.scrollPageToBottom();
	    generic.scrollPage(addNewAddress_Btn);
		driver.findElement(addNewAddress_Btn).click();
		Thread.sleep(5000);
	}
	public void enteraddressDetails_Text(String firstName, String lastName){
		driver.findElement(firstName_Text).sendKeys(firstName);
		driver.findElement(lastName_Text).sendKeys(lastName);
	}
		
	public void enterAddress_Text_ShippingAddress(String address1, String address2, String cityName){
			driver.findElements(address_Text1And2).get(7).sendKeys(address1);
			driver.findElements(address_Text1And2).get(8).sendKeys(address2);
			driver.findElement(city_Text).sendKeys(cityName);
	}	
	public void enterAddress_Text(String value){
		driver.findElement(address_Text).sendKeys(value);
	}
	
	public void selectState_DDAndEnterData(String value,String email, String pincode, String mobno){
		generic.selectFromDropDownWithValue(state_DD, value);
		driver.findElement(email_Text).sendKeys(email);
		driver.findElement(postalCode_Text).sendKeys(pincode);
		driver.findElement(PhoneNo_Text).sendKeys(mobno);
		driver.findElement(saveAddress_Rd).click();
		driver.findElement(savedAddress_Btn).click();
	}

	public void selectCountry_DD(String value){
		generic.selectFromDropDownWithValue(country_DD, value);
	}

	public void clickOncancel_Btn_ShippingAddress(){
		driver.findElement(cancel_Btn_ShippingAddress).click();
	}
	public String getErrorMessageFieldReq_ShippingAddress(int value){
		return driver.findElements(errorMessage_FieldReq).get(value).getText();
	}
	public String getErrorMessage_PleaseSelectOpn(){
		return driver.findElement(errorMessage_PleaseSelectOpn).getText();
	}
	public void enterUserName_CustomerLogin(String value){
		driver.findElement(userName_CustomerLogin).sendKeys(value);
	}
	public void enterPassword_CustomerLogin(String value){
		driver.findElement(pwd_CustomerLogin).sendKeys(value);
	}
	public void clickOnSign_Btn(){
		driver.findElement(sign_Btn_CustomerLogin).click();
	}
	public void clickOnSignUp_Btn(){
		driver.findElement(signUp_Btn_CustomerLogin).click();
	}
	public String getmessageForSignUp(){
		return driver.findElement(messageForSignUp).getText();
	}
	public void clickOnnext_Btn() throws InterruptedException{
		generic.waitPageGotLoad();
		driver.findElement(next_Btn).click();
	}
	public void selectmyBillingAndShiping_Rd(){
		driver.findElement(myBillingAndShiping_Rd).click();
	}
	public void selectmyBillingAndShipping_Dd(String value){
		generic.selectFromDropDownWithValue(myBillingAndShipping_Dd, value);
	}
	public void enterfirstName_Text_PaymentPage(String value){
		driver.findElement(firstName_Text_PaymentPage).sendKeys(value);
	}
	public void enterlastName_Text_PaymentPage(String value){
		driver.findElement(lastName_Text_PaymentPage).sendKeys(value);
	}
	public void enterAddress_Text_PaymentPage(String value){
		driver.findElement(Address_Text_PaymentPage).sendKeys(value);
	}
	public void entercity_Text_PaymentPage(String value){
		driver.findElement(city_Text_PaymentPage).sendKeys(value);
	}
	public void enterpostalCode_Text_PaymentPage(String value){
		driver.findElement(postalCode_Text_PaymentPage).sendKeys(value);
	}
	public void selectstate_Dd_PaymentPage(String value){
		generic.selectFromDropDownWithValue(state_Dd_PaymentPage, value);
	}
	public void selectcountry_Dd_PaymentPage(String value){
		generic.selectFromDropDownWithValue(country_Dd_PaymentPage, value);
	}
	public void enterphoneNo_Text(String value){
		generic.scrollPage(phoneNo_Text);
		driver.findElement(phoneNo_Text).sendKeys(value);
	}
	public void selectSavedAddress_Rd_PaymentPage(){
		driver.findElement(SavedAddress_Rd_PaymentPage).click();
	}
	public void selectcreditCard_Rd_PaymentPage(){
		driver.findElement(creditCard_Rd_PaymentPage).click();
	}
	public void selectdebitCard_Rd_PaymentPage(){
		driver.findElement(debitCard_Rd_PaymentPage).click();
	}
	public void selectnetBanking_Rd_PaymentPage(){
		driver.findElement(netBanking_Rd_PaymentPage).click();
	}
	public void selectcashCard_Rd_PaymentPage(){
		driver.findElement(cashCard_Rd_PaymentPage).click();
	}	
	public void clickOncancel_Btn_PaymentPage(){
		driver.findElement(cancel_Btn_PaymentPage).click();
	}
	public void clickOnupdate_Btn_PaymentPage(){
		driver.findElement(update_Btn_PaymentPage).click();
	}
	public void clickOnedit_Btn_paymentPage(){
		driver.findElement(edit_Btn_paymentPage).click();
	}
	public void clickOnapplyDiscountcode_Btn_PaymentPage(){
		driver.findElement(applyDiscountcode_Btn_PaymentPage).click();
	}
	public void enterdiscount_Text_PaymentPage(String value){
		driver.findElement(discount_Text_PaymentPage).sendKeys(value);
	}
	public void clickOnapplyDiscount_Btn_PaymentPage(){
		driver.findElement(applyDiscount_Btn_PaymentPage).click();
	}
	public String geterrorDiscountMessage_Payment(){
		return driver.findElement(errorDiscountMessage_Payment).getText();
	}
	public void enterpincode_Text(String value){
		driver.findElement(pincode_Text).sendKeys(value);
	}
	public void clickOncheck_Lnk(){
		driver.findElement(check_Lnk).click();
	}
	public String geterrorPincodeMessage(){
		return driver.findElement(errorPincodeMessage).getText();
	}
	public void clickOnaddToCard_Btn(){
		driver.findElement(addToCard_Btn).click();
	}
	public void clickOnbuyNow_Btn(){
		driver.findElement(buyNow_Btn).click();
	}
	public void enterStudentName_Text(int index, String value){
		driver.findElements(studentName_Text).get(index).sendKeys(value);
	}
	public void enterusnStudentId_Text(int index, String value){
		driver.findElements(usnStudentId_Text).get(index).sendKeys(value);
	}
	public void enterusnStudentId_Text_viewDetails( String value){
		driver.findElement(usnStudentId_Text_viewDetails).sendKeys(value);
	}














	public String getActualItemName(){
		String ItemName = driver.findElement(itemName).getText();
		return ItemName;
	}
	public String getActualItemPrice(){
		String ItemPrice = driver.findElement(itemPrice).getText();
		return ItemPrice;
	}
	public void clickOnViewdetails(){
		GenericFunctions.WaitFor_visibility(driver, booksImage);
		WebElement element = driver.findElement(booksImage);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		generic.clickFunction(driver.findElement(viewDetail_lnk));
	}
	public String getItemNameOnProductDetails(){
		String ItemNameOnProductDetails = driver.findElement(itemNameOnProductDetails).getText();
		return ItemNameOnProductDetails;
	}
	public String getItemPriceOnProductDetails(){
		String ItemPriceOnProductDetails = driver.findElements(itemPriceOnProductDetails).get(1).getText();
		System.out.println("ItemPriceOnProductDetails:"+ ItemPriceOnProductDetails);
		return ItemPriceOnProductDetails;
	}
	public boolean checkItemNameOnProductDetailsWithActualName(){
		if(getActualItemName().equalsIgnoreCase(getItemNameOnProductDetails())){
			return true;
		}else{
			return false;
		}
	}

}
