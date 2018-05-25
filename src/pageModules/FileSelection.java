package pageModules;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class FileSelection {
	AppiumDriver driver;
	public FileSelection(AppiumDriver driver)
	{
		this.driver=driver;
	}
	public By rootFolder = By.name("File Manager");
	public By parentFolder = By.name("Files_Automation");
	public By crossButton = By.id("com.elss.educomp:id/itemSelectedAttachmentClose");
	public By cameraButton = By.id("com.android.camera2:id/shutter_button");
	public By cameraDoneButton = By.id("com.android.camera2:id/done_button");
	public By cameraSaveButton = By.id("com.elss.educomp:id/save");
	public String fileId = "com.cyanogenmod.filemanager:id/navigation_view_item_name";


	public void openFolderByFolderName(String foldername)
	{
		driver.findElementByXPath("//*[@text='"+foldername+"']").click();
	}
	@SuppressWarnings("unchecked")
	public void openFolderByFileNameAndId(String filename)
	{
		List<WebElement> elements = driver.findElements(By.id(fileId));
		elements.get(1).click();
	}

	@SuppressWarnings("unchecked")
	public void selectFileByFileExtension(String extnsion)
	{
		List<WebElement> elements = driver.findElements(By.xpath("//*[contains(@text, '"+extnsion+"')]"));
		if(elements.size()>0)
		{
			elements.get(0).click();
		}
		else{
			System.out.println("No such type of file extension");
		}
	}

	public void clickOnCameraShutter()
	{
		driver.findElement(cameraButton).click();
	}

	public void clickOnCameraDoneButton()
	{
		driver.findElement(cameraDoneButton).click();
	}

	public void clickOnCameraSaveButton()
	{
		driver.findElement(cameraSaveButton).click();
	}

	public void clickOnCameraTile()
	{
		//Actions action = new Actions(driver);
		Dimension size = driver.manage().window().getSize();	
		int width = size.getWidth();
		int height= size.getHeight();
		int startx = (int) (width*0.3);
		int starty = (int) (height*0.3);
		TouchAction action = new TouchAction(driver);
		action.tap(startx, starty).perform();		
	}
	public void clickOnAFileFromGallary()
	{
		//Actions action = new Actions(driver);
		Dimension size = driver.manage().window().getSize();	
		int width = size.getWidth();
		int height= size.getHeight();
		int startx = (int) (width*0.5);
		int starty = (int) (height*0.5);
		TouchAction action = new TouchAction(driver);
		//action.tap(540, 763).perform();
		action.tap(startx, starty).perform();
	}
	public void clickOnSelectedFileCrossButton()
	{
		driver.findElement(crossButton).click();
	}
}
