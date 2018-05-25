package utils;

import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import helper.DriverSession;

public class APIresponses extends DriverSession{
	
	public APIresponses(WebDriver driver)
	{
		super.driver = driver;
	}

	public int getAPIresponseCode(String apiURL) throws Exception
	{
		int code;
		URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        code = connection.getResponseCode();
     //   System.out.println("Response code of the object is "+code);
        if (code==200)
        {
          //  System.out.println("OK");
        }
        return code;
	}
	
}
