package testcases;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.WallActions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.B2C;
import pageModules.BookStore;
import pageModules.LoginPage;
import utils.Xls_Reader;

public class BookStoreTestCases extends DriverSession{
	WebDriverWait wait;
	BookStore bookstore;
	LoginPage login;
	B2C b2c;
	GenericFunctions generic;
	Xls_Reader xls;
	WallActions wall;
	int tem=3,c=0;
	String sheetName="BookStore", expectedResult="ExpectedMsg",studentName,studentId,sheetname="Credentials";
	static String UserName,password, firstName, lastName,email, mobno,pincode, address1, address2, cityName;
	String schooltype;
	
	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		wait=new WebDriverWait(driver, 25);
		bookstore=new BookStore(driver);
		xls=new Xls_Reader();
		b2c=new B2C(driver);
		login=new LoginPage(driver);
		generic=new GenericFunctions(driver);
		wall = new WallActions(driver);
		driver.get(BASE_URL);
		generic.waitPageGotLoad();
		bookstore.clickOnbuyBook_Btn();
	    String schooltype = xls.getCellData(sheetName, expectedResult, 22);
         bookstore.selectShool_DD(schooltype);
		studentName = xls.getCellData(sheetName, expectedResult, 24);
		studentId = xls.getCellData(sheetName, expectedResult, 26);
		String colName="UserId",colname="Password";
		UserName=xls.getCellData(sheetname, colName, 13);
		password=xls.getCellData(sheetname, colname, 13);
		firstName=xls.getCellData(sheetName, expectedResult, 28);
		lastName=xls.getCellData(sheetName, expectedResult, 29);
		mobno=xls.getCellData(sheetName, expectedResult, 33);
		cityName=xls.getCellData(sheetName, expectedResult, 30);
		pincode=xls.getCellData(sheetName, expectedResult, 31);
		email=xls.getCellData(sheetName, expectedResult, 32);
		address1=xls.getCellData(sheetName, expectedResult, 34);
		address2=xls.getCellData(sheetName, expectedResult, 35);



	}
	/************************************
	 * Test Case Name : To verify error message by clicking on add to cart without entering Student name 
	 * @author fliplearn : Vinay Yadav
	 *******************************/
	@Test(priority=1)
	public void BKS_001() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.clickOnAddCard_Btn(index);
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}
		String actual=bookstore.geterrorMessageStudentRequired();
		String expected=xls.getCellData(sheetName, expectedResult, 2);
		Assert.assertEquals(actual,expected);
	}
	/************************************
	 * Test Case Name : To verify Add To cart Button by selecting School, class and entering Student Name
	 * @author fliplearn : Vinay Yadav
	 ***********************************/
	@Test(priority=2)
	public void BKS_002() throws InterruptedException{ 
		String classtype = xls.getCellData(sheetName, expectedResult, 23);
		bookstore.selectClassType_DD(classtype);
		String actualPrice = bookstore.getPriceFont();
		System.out.println(actualPrice);
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}
		String actual = bookstore.getproductAddToshoppingCartMessage().substring(0, 14);
		String expected=xls.getCellData(sheetName, expectedResult, 3);
		Assert.assertEquals(actual,expected);

	}
	/************************************
	 * Test Case Name : To verify Add To cart Button by without entering Student Name by clicking viewDetauils button
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=3)
	public void BKS_003() throws InterruptedException{
		Thread.sleep(1000);
		String actualPrice = bookstore.getPriceFont();
		System.out.println("actual price "+actualPrice);
		bookstore.clickOnViewDetails(0);
		String instock=bookstore.getinStockMessage();
		if(instock.equalsIgnoreCase("In stock")){
			System.out.println(instock);
			System.out.println("fliplearn sku  "+bookstore.getfliplearnSku_viewDetails());
			//bookstore.clickOnIncreaseQnty_Btn();
			String qnty = bookstore.getqnty();
			//System.out.println("qnty after increament "+qnty);
			//bookstore.clickOndecrease_Btn();
			//String dqnty = bookstore.getqnty();
			//System.out.println(dqnty);
			//bookstore.checkItemNameOnProductDetailsWithActualName();
			bookstore.clickOnAddCard_Btn(0);
			String actual = bookstore.geterrorMessage_StudentRequired_ViewDetail();
			String expected=xls.getCellData(sheetName,expectedResult,2);
			Assert.assertEquals(actual, expected);
		}
		else{
			System.out.println("Out of stock");
		}
	}
	/************************************
	 * Test Case Name : To verify Add To cart Button by entering Student Name by clicking viewDetails button
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=4)
	public void BKS_004() throws InterruptedException{
		Thread.sleep(1000);
		String actualPrice = bookstore.getPriceFont();
		System.out.println("actual price "+actualPrice);
		bookstore.clickOnViewDetails(0);
		String instock=bookstore.getinStockMessage();
		if(instock.equalsIgnoreCase("In stock")){
			System.out.println(instock);
			//bookstore.clickOnIncreaseQnty_Btn();
			//String qnty = bookstore.getqnty();
			//System.out.println("qnty after increament "+qnty);
			//bookstore.clickOndecrease_Btn();
			//String dqnty = bookstore.getqnty();
			//System.out.println(dqnty);
			bookstore.enterstudent_Text_viewDetails(studentName);
			bookstore.clickOnAddCard_Btn(0);
			String actualMessage=bookstore.getproductAddToshoppingCartMessage();
			System.out.println(actualMessage);
			if(actualMessage.equalsIgnoreCase("Only 3 quantity allowed for same kit!")){
				for(int i=0; i<=2; i++){
					bookstore.clickOnCartArrow_Lnk();
					bookstore.clickOndeleteShoppingCart_Lnk();
				}

			}
		}
		else{
			System.out.println("Out of stock");
		}
	}
	/************************************
	 * Test Case Name : To verify buy Now Button by without entering Student Name by clicking viewDetails button
	 * @author fliplearn : Vinay Yadav
	 * @throws InterruptedException 

	 ***********************************/
/*	@Test(priority=5)
	public void BKS_005() throws InterruptedException{
		String actualPrice = bookstore.getPriceFont();
		System.out.println("actual price "+actualPrice);
		bookstore.clickOnViewDetails(0);
		String instock=bookstore.getinStockMessage();
		if(instock.equalsIgnoreCase("In stock")){
			System.out.println(instock);
			bookstore.clickOnIncreaseQnty_Btn();
			bookstore.clickOnbuyNow_Btn_ViewDetails();
			String actual = bookstore.geterrorMessage_StudentRequired_ViewDetail();
			String expected=xls.getCellData(sheetName,expectedResult,2);
			Assert.assertEquals(actual, expected);
		}
		else{
			System.out.println("Out of stock");
		}

	}
	
	
	
	
	/************************************
	 * Test Case Name : To verify buy now Button by entering Student Name by clicking viewDetails button
	 * @author fliplearn : Vinay Yadav
	 * @throws InterruptedException 

	 ***********************************/
/*	@Test(priority=6)
	public void BKS_006() throws InterruptedException{
		String actualPrice = bookstore.getPriceFont();
		System.out.println("actual price "+actualPrice);
		//bookstore.isStockAvailable(0);
		//return;

		bookstore.clickOnViewDetails(0);
		String instock=bookstore.getinStockMessage();
		if(instock.equalsIgnoreCase("In stock")){
			System.out.println(instock);
			//bookstore.clickOnIncreaseQnty_Btn();
			bookstore.enterstudent_Text_viewDetails(studentName);
			bookstore.clickOnbuyNow_Btn_ViewDetails();
			Thread.sleep(3000);
			String subTotal = bookstore.getSubTotal_ShoppingCart();
			System.out.println(subTotal);
			String totalflate=bookstore.getFlateRate_ShpoppingCart();
			System.out.println("total flate rate "+totalflate);
			String totalorder=bookstore.getOrderTotalPrice_ShpoppingCard();
			System.out.println("Total order price "+totalorder);
			bookstore.clickOnProceedToCheckOut_Btn();
			String actual = bookstore.gethomeText(1);
			String expected=xls.getCellData(sheetName, expectedResult, 4);
			Assert.assertEquals(actual, expected);
		}
		else{
			System.out.println("Out of stock");
		}
	}
	
	*/
	
	
	/************************************
	 * Test Case Name : To verify clear shopping  Button
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=7)
	public void BKS_007() throws InterruptedException{
		String actualPrice = bookstore.getPriceFont();
		System.out.println("actual price "+actualPrice);
		bookstore.clickOnViewDetails(0);
		String instock=bookstore.getinStockMessage();
		if(instock.equalsIgnoreCase("In stock")){
			System.out.println(instock);
			//bookstore.clickOnIncreaseQnty_Btn();
			bookstore.enterstudent_Text_viewDetails(studentName);
			bookstore.enterusnStudentId_Text_viewDetails(studentId);
			//bookstore.clickOnbuyNow_Btn_ViewDetails();
			bookstore.clickOnAddToCart_Btn_ViewDetails();
			bookstore.clickOnCartArrow_Lnk();
			String subTotal = bookstore.getSubTotal_ShoppingCart();
			System.out.println(subTotal);
			String totalflate=bookstore.getFlateRate_ShpoppingCart();
			System.out.println("total flate rate "+totalflate);
			String totalorder=bookstore.getOrderTotalPrice_ShpoppingCard();
			System.out.println("Total order price "+totalorder);
			bookstore.clickOnClearShoppingCart_Btn();
			Thread.sleep(2000);
			String actual=bookstore.getemptyMessage();
			System.out.println(actual);
			String expected=xls.getCellData(sheetName, expectedResult, 6);
			Assert.assertEquals(actual, expected);
		}
		else{
			System.out.println("Out of stock");
		}
	}
	/************************************
	 * Test Case Name : To verify Update shopping button
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	/*@Test(priority=8)
	public void BKS_008() throws InterruptedException{
		String actualPrice = bookstore.getPriceFont();
		System.out.println("actual price "+actualPrice);
		bookstore.clickOnViewDetails(0);
		String instock=bookstore.getinStockMessage();
		if(instock.equalsIgnoreCase("In stock")){
			System.out.println(instock);
			//bookstore.clickOnIncreaseQnty_Btn();
			bookstore.enterstudent_Text_viewDetails(studentName);
			bookstore.clickOnbuyNow_Btn_ViewDetails();
			String subTotal = bookstore.getSubTotal_ShoppingCart();
			System.out.println(subTotal);
			String totalflate=bookstore.getFlateRate_ShpoppingCart();
			System.out.println("total flate rate "+totalflate);
			String totalorder=bookstore.getOrderTotalPrice_ShpoppingCard();
			System.out.println("Total order price "+totalorder);
			bookstore.clickOnUpdateShoppingCart_Btn();
			Thread.sleep(2000);
			String actual=bookstore.getproductAddToshoppingCartMessage();
			String expected=xls.getCellData(sheetName, expectedResult, 5);
			Assert.assertEquals(actual, expected);
		}
		else{
			System.out.println("Out of stock");
		}
	}
	/************************************
	 * Test Case Name : To click On countinue shopping button
	 * @author fliplearn : Vinay Yadav
	 ***********************************/
	@Test(priority=9)
	public void BKS_009() throws InterruptedException{
		String actualPrice = bookstore.getPriceFont();
		System.out.println("actual price "+actualPrice);
		bookstore.clickOnViewDetails(0);
		String instock=bookstore.getinStockMessage();
		if(instock.equalsIgnoreCase("In stock")){
			System.out.println(instock);
			//bookstore.clickOnIncreaseQnty_Btn();
			bookstore.enterstudent_Text_viewDetails(studentName);
			bookstore.enterusnStudentId_Text_viewDetails(studentId);
			//bookstore.clickOnbuyNow_Btn_ViewDetails();
			bookstore.clickOnAddToCart_Btn_ViewDetails();
			bookstore.clickOnCartArrow_Lnk();
			String subTotal = bookstore.getSubTotal_ShoppingCart();
			System.out.println(subTotal);
			String totalflate=bookstore.getFlateRate_ShpoppingCart();
			System.out.println("total flate rate "+totalflate);
			String totalorder=bookstore.getOrderTotalPrice_ShpoppingCard();
			System.out.println("Total order price "+totalorder);
			bookstore.clickOnContinueShoppingCart_Btn();
			String actual = bookstore.gethomeTextdropdown(0);
			String expected=xls.getCellData(sheetName, expectedResult, 7);
			Assert.assertEquals(actual, expected);
		}
		else{
			System.out.println("Out of stock");
		}
	}
	/************************************
	 * Test Case Name : To verify to delete product from cart
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=10)
	public void BKS_10() throws InterruptedException{
		bookstore.clickOnViewDetails(0);
		String instock=bookstore.getinStockMessage();
		if(instock.equalsIgnoreCase("In stock")){
			System.out.println(instock);
			//bookstore.clickOnIncreaseQnty_Btn();
			String qnty = bookstore.getqnty();
			//System.out.println("qnty after increament "+qnty);
			//bookstore.clickOndecrease_Btn();
			//String dqnty = bookstore.getqnty();
			//System.out.println(dqnty);
			bookstore.enterstudent_Text_viewDetails(studentName);
			bookstore.enterusnStudentId_Text_viewDetails(studentId);
			bookstore.clickOnAddCard_Btn(0);
		}
		else{
			System.out.println("Out of stock");
		}

		Thread.sleep(2000);
		bookstore.clickOnCartArrow_Lnk();
		bookstore.clickOndeleteShoppingCart_Lnk();
		String actual = bookstore.getemptyMessage();
		String expected=xls.getCellData(sheetName, expectedResult, 6);
		Assert.assertEquals(actual, expected);
	}
	/************************************
	 * Test Case Name : To verify each and every product by clicking on viewDetails button
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=11)
	public void BKS_011() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<size; index++){
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;

			}
			if(bookstore.clickOnViewDetails(index)){
				bookstore.refresh();
				if(bookstore.getStudentName().equalsIgnoreCase("Student")){
					bookstore.backPage();
				}
				else{
					System.out.println("student name text is not visible "+index);
					bookstore.backPage();
				}

			}
		}
	}
	/************************************
	 * Test Case Name : To verify by clicking on add cart button by comparing student Name
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=12)
	public void BKS_012() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<size; index++){
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				Thread.sleep(4000);
				String actual = bookstore.getStudentName_ShoppingCart();
				String expected = xls.getCellData(sheetName, expectedResult, 8);
				Assert.assertEquals(actual, expected);
				break;
			}
		}
	}
	/************************************
	 * Test Case Name : To verify by clicking on add cart button by comparing product Name
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=13)
	public void BKS_013() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<size; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				System.out.println(index);
				String expected =bookstore.getproductName_Home(index);
				bookstore.clickOnAddCard_Btn(index);
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				Thread.sleep(2000);
				String actual = bookstore.getProductName_ShoppingCart(0);
				System.out.println(actual);
				Assert.assertEquals(actual, expected);
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;

			}

			break;
		}
	}
	/************************************
	 * Test Case Name : To verify  Update button by entering two Quantity of product
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
/*	@Test(priority=14)
	public void BKS_014() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<size; index++){
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				generic.waitPageGotLoad();
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				String expected1=bookstore.getPrice_ShoppingCart();
				StringBuffer sbf = new StringBuffer(expected1);
				sbf.deleteCharAt(0);
				sbf.deleteCharAt(1);
				String expected2 = sbf.toString();
				expected1=expected2.split("\\.")[0].trim();
				int expected=Integer.parseInt(expected1);
				expected = expected * 2;
				System.out.println(expected);
				System.out.println(String.valueOf(expected));
				///String expected4=String.valueOf(expected);

				bookstore.enterQty_Text_ShoppingCart("2");
				bookstore.clickOnUpdateShoppingCart_Btn();
				Thread.sleep(4000);
				String actual1=bookstore.getSubTotal_ShoppingCart();
				StringBuffer sbf1 = new StringBuffer(actual1);
				sbf1.deleteCharAt(0);
				sbf1.deleteCharAt(1);
				String actual2 = sbf1.toString();
				String actual= actual2.split("\\.")[0].trim();
				//int actual=Integer.parseInt(actual3);
				System.out.println("expected "+expected);
				System.out.println("actual   "+actual);
				Assert.assertEquals(actual,String.valueOf(expected));
			}

			break;
		}

	}*/

	/************************************
	 * Test Case Name : To verify  facebook link
	 * @author fliplearn : Vinay Yadav

	 ***********************************/

	@Test(priority=15)
	public void BKS_015() throws InterruptedException{
		bookstore.clickOnCartArrow_Lnk();
		generic.waitPageGotLoad();
		Thread.sleep(5000);
		bookstore.clickOnFaceBookLinks();
		generic.waitPageGotLoad();
		generic.SwitchtoNewWindow();
		generic.waitPageGotLoad();
		String actual=bookstore.getCurrentUrl();
		String expected=xls.getCellData(sheetName, expectedResult, 9);
		Assert.assertEquals(actual, expected);

	}//a[contains(@te)]
	/************************************
	 * Test Case Name : To verify  twitter link
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=16)
	public void BKS_016() throws InterruptedException{
		bookstore.clickOnCartArrow_Lnk();
		generic.scrollPageToBottom();
		bookstore.clickOntwitterLinks();
		generic.waitPageGotLoad();
		generic.SwitchtoNewWindow();
		String actual=bookstore.getCurrentUrl();
		String expected=xls.getCellData(sheetName, expectedResult, 10);
		Assert.assertEquals(actual, expected);

	}
	/************************************
	 * Test Case Name : To verify  youtube link
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=17)
	public void BKS_017() throws InterruptedException{
		bookstore.clickOnCartArrow_Lnk();
		generic.scrollPageToBottom();
		bookstore.clickOnyoutubeLinks();;
		generic.waitPageGotLoad();
		generic.SwitchtoNewWindow();
		String actual=bookstore.getCurrentUrl();
		String expected=xls.getCellData(sheetName, expectedResult, 11);
		Assert.assertEquals(actual, expected);

	}
	/************************************
	 * Test Case Name : To verify  linkedin link
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=18)
	public void BKS_018() throws InterruptedException{
		bookstore.clickOnCartArrow_Lnk();
		generic.scrollPageToBottom();
		bookstore.clickOnlinkedinLinks();
		generic.waitPageGotLoad();
		generic.SwitchtoNewWindow();
		String actual=bookstore.getCurrentUrl().substring(0,32);
		String expected=xls.getCellData(sheetName, expectedResult, 12);
		Assert.assertEquals(actual, expected);

	}
	/************************************
	 * Test Case Name : To verify  contactus link
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=19)
	public void BKS_019() throws InterruptedException{
		bookstore.clickOnCartArrow_Lnk();
		generic.scrollPageToBottom();
		bookstore.clickOncontactusLinks();
		generic.waitPageGotLoad();
		generic.SwitchtoNewWindow();
		String actual=bookstore.getCurrentUrl();
		String expected=xls.getCellData(sheetName, expectedResult, 19);
		Assert.assertEquals(actual, expected);

	}

	/************************************
	 * Test Case Name : To verify  term and condition link
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=20)
	public void BKS_020() throws InterruptedException{
		bookstore.clickOnCartArrow_Lnk();
		generic.scrollPageToBottom();
		bookstore.clickOntermconditionsLinks();
		generic.waitPageGotLoad();
		generic.SwitchtoNewWindow();
		String actual=bookstore.getCurrentUrl();
		String expected=xls.getCellData(sheetName, expectedResult, 20);
		Assert.assertEquals(actual, expected);

	}

	/************************************
	 * Test Case Name : To verify license link
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=21)
	public void BKS_021() throws InterruptedException{
		bookstore.clickOnCartArrow_Lnk();
		generic.scrollPageToBottom();
		bookstore.clickOnlicenseLinks();
		generic.waitPageGotLoad();
		generic.SwitchtoNewWindow();
		String actual=bookstore.getCurrentUrl().substring(0, 40);
		String expected=xls.getCellData(sheetName, expectedResult, 17);
		Assert.assertEquals(actual, expected);

	}

	/************************************
	 * Test Case Name : To verify  save adress button withought entering first name
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=22)
	public void BKS_022() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}

		generic.waitPageGotLoad();
		login.Fill_Username_Txt(UserName);
		login.Fill_Password_Txt(password);
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(4000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text("", lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,email,pincode,mobno);
		String actual=bookstore.getErrorMessageFieldReq_ShippingAddress(0);
		String expected=xls.getCellData(sheetName, expectedResult, 13);
		Assert.assertEquals(actual, expected);
	}

	/************************************
	 * Test Case Name : To verify chractere limit of first name text
	 * @author fliplearn : Vinay Yadav

	 ***********************************/

	@Test(priority=23)
	public void BKS_023() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}

		generic.waitPageGotLoad();
		login.Fill_Username_Txt(UserName);
		login.Fill_Password_Txt(password);
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(3000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text(firstName.substring(0, 1), lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,email,pincode,mobno);
		Thread.sleep(2000);
		String actual=bookstore.geterrorAtleastThreeChracter();
		String expected=xls.getCellData(sheetName, expectedResult, 16);
		Assert.assertEquals(actual, expected);
	}
	/************************************
	 * Test Case Name : To verify  payment links
	 * @author fliplearn : Vinay Yadav

	 ***********************************/
	@Test(priority=24)
	public void BKS_024() throws InterruptedException{
		bookstore.clickOnCartArrow_Lnk();
		generic.scrollPageToBottom();
		bookstore.clickOnpaymentLinks();
		generic.waitPageGotLoad();
		generic.SwitchtoNewWindow();
		String actual=bookstore.getCurrentUrl();
		String expected=xls.getCellData(sheetName, expectedResult, 19);
		Assert.assertEquals(actual, expected);

	}

	/************************************
	 * Test Case Name : To verify  save address button without entering state name
	 * @author fliplearn : Vinay Yadav

	 ***********************************/

	@Test(priority=25)
	public void BKS_025() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}

		generic.waitPageGotLoad();
		Thread.sleep(4000);
		login.Fill_Username_Txt(UserName);
		login.Fill_Password_Txt(password);
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(3000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text(firstName, lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,email,pincode,mobno);
		Thread.sleep(2000);
		String actual=bookstore.geterrorAtleastThreeChracter();;
		String expected=xls.getCellData(sheetName, expectedResult, 13);
		Assert.assertEquals(actual, expected);
	}
	/************************************
	 * Test Case Name : To verify Email text without entering email id
	 * @author fliplearn : Vinay Yadav

	 ***********************************/	
	@Test(priority=26)
	public void BKS_026() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}

		generic.waitPageGotLoad();
		login.Fill_Username_Txt(UserName);
		login.Fill_Password_Txt(password);
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(3000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text(firstName, lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,"",pincode,mobno);
		Thread.sleep(2000);
		String actual=bookstore.geterrorAtleastThreeChracter();;
		String expected=xls.getCellData(sheetName, expectedResult, 13);
		Assert.assertEquals(actual, expected);
	}


	/************************************
	 * Test Case Name : To verify  save address button
	 * @author flipleaBKS_025rn : Vinay Yadav

	 ***********************************/	
	@Test(priority=27)
	public void BKS_027() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}

		generic.waitPageGotLoad();
		login.Fill_Username_Txt("cyrus.broacha");
		login.Fill_Password_Txt("123456");
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(3000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text(firstName, lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,email,pincode,mobno);
		Thread.sleep(2000);
		generic.scrollPageToBottom();
		String actual=bookstore.getEdit_Text_ShippingAddress();
		String expected=xls.getCellData(sheetName, expectedResult, 21);
		Assert.assertEquals(actual, expected);
	}


	/************************************
	 * Test Case Name : To verify edit button by editing shipping address
	 * @author fliplearn : Vinay Yadav

	 ***********************************/	
	@Test(priority=28)
	public void BKS_028() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}

		generic.waitPageGotLoad();
		Thread.sleep(4000);
		login.Fill_Username_Txt(UserName);
		login.Fill_Password_Txt(password);
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(3000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text(firstName, lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,email,pincode,mobno);
		Thread.sleep(2000);
		generic.scrollPageToBottom();
		bookstore.clickOnEdit_Btn_ShippingAddress();
		bookstore.enteraddressDetails_Text(firstName.substring(0, 3), lastName);
		bookstore.selectState_DDAndEnterData("Delhi",email,pincode,mobno);
		generic.scrollPageToBottom();
		Thread.sleep(3000);
		String actual=bookstore.getEdit_Text_ShippingAddress();
		String expected=xls.getCellData(sheetName, expectedResult, 21);
		Assert.assertEquals(actual, expected);
	}
	/************************************
	 * Test Case Name : To verify change of ship to address
	 * @author fliplearn : Vinay Yadav

	 ***********************************/	
	@Test(priority=29)
	public void BKS_029() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}

		generic.waitPageGotLoad();
		login.Fill_Username_Txt(UserName);
		login.Fill_Password_Txt(password);
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(4000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text(firstName, lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,email,pincode,mobno);
		Thread.sleep(2000);
		generic.scrollPageToBottom();
		bookstore.clickOnnext_Btn();
		generic.scrollPageToBottom();
		Thread.sleep(5000);
		bookstore.clickOnedit_Btn_paymentPage();
		String expected=bookstore.getExpectedshipAddressContents_ShippingAddress().substring(0, 15);
		generic.scrollPageToBottom();
		Thread.sleep(3000);
		bookstore.clickOnnext_Btn();		
		Thread.sleep(5000);
		String actual=bookstore.getshipAddressContents_ShippingAddress().substring(0, 15);
		Assert.assertEquals(actual, expected);
	}
	/************************************
	 * Test Case Name : To verify error message by  entering Student Name and without entering student id
	 * @author fliplearn : Vinay Yadav
	 ***********************************/
	@Test(priority=30)
	public void BKS_030() throws InterruptedException{ 
		String actualPrice = bookstore.getPriceFont();
		System.out.println(actualPrice);
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.clickOnAddCard_Btn(index);
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}
		String actual = bookstore.geterrormessage_UnsRequired();
		String expected=xls.getCellData(sheetName, expectedResult, 25);
		Assert.assertEquals(actual,expected);

	}
	/************************************
	 * Test Case Name : To verify pincode text
	 * @author fliplearn : Vinay Yadav
	 ***********************************/
	
	@Test(priority=31)
	public void BKS_031() throws InterruptedException{ 
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}
		login.Fill_Username_Txt(UserName);
		login.Fill_Password_Txt(password);
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(3000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text(firstName, lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,email,pincode,mobno);
		generic.scrollPageToBottom();
		String actual=bookstore.getEdit_Text_ShippingAddress();
		String expected=xls.getCellData(sheetName, expectedResult, 21);
		Assert.assertEquals(actual, expected);

		
	}
	/************************************
	 * Test Case Name : To verify payment gateway
	 * @author fliplearn : Vinay Yadav
	 ***********************************/
	@Test(priority=32)
	public void BKS_032() throws InterruptedException{
		int size=bookstore.getviewDetails_Btn();
		System.out.println(size);
		for(int index=0; index<=size-1; index++){
			if(bookstore.isProductInStock(index)){
				bookstore.enterStudentName_Text(index,studentName);
				bookstore.enterusnStudentId_Text(index, studentId);
				bookstore.clickOnAddCard_Btn(index);
				Thread.sleep(4000);
				bookstore.clickOnCartArrow_Lnk();
				bookstore.clickOnProceedToCheckOut_Btn();
				break;
			}
			if(tem==index){
				BookStore.scrollPageByIndex(0, 250);
				tem=tem+3;
			}
		}

		generic.waitPageGotLoad();
		login.Fill_Username_Txt(UserName);
		login.Fill_Password_Txt(password);
		login.ClickOnLogin_Btn();
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
			generic.waitPageGotLoad();
		}
		if(generic.isElementPresent("Select Profile")){
			wall.selectProfile(2);
		}
		Thread.sleep(4000);
		bookstore.clickOnAddNewAddress_Btn();
		bookstore.enteraddressDetails_Text(firstName, lastName);
		bookstore.enterAddress_Text_ShippingAddress(address1,address2, cityName);
		generic.scrollPageToBottom();
		Thread.sleep(2000);
		bookstore.selectState_DDAndEnterData(cityName,email,pincode,mobno);
		Thread.sleep(2000);
		generic.scrollPageToBottom();
		bookstore.clickOnnext_Btn();
		generic.scrollPageToBottom();
		bookstore.clickOnPlaceOrder_Btn();
		b2c.selectBankfrom_Dwn();
		b2c.clickOncancel_btn();
		String sheetName = "B2CResult", colName="Expected";
		String expected=xls.getCellData(sheetName, colName, 8);
		Assert.assertEquals(b2c.getCancelmessage(), expected);
	}
}
