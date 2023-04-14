package Practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		PropertyFileUtility pUtil=new PropertyFileUtility();
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		
		ExcelFileUtility eUtil=new ExcelFileUtility();
		String value = eUtil.readDataFromExcel("Organization", 1, 2);
		System.out.println(value);
		
		eUtil.writeIntoExcel("Organization", 10, 7, value);
		System.out.println("data added");
		
		JavaUtility jUtil=new JavaUtility();
		System.out.println(jUtil.getRandomNumber());
		
		System.out.println(jUtil.getSystemDate());
		
		System.out.println(jUtil.getSystemDateInFormat());
			
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		WebDriverUtility wUtil=new WebDriverUtility();
		wUtil.maximizeWindow(driver);
		Thread.sleep(2000);
		wUtil.minimizeWindow(driver);
		
		wUtil.waitForPageLoad(driver);
		
	}

}
