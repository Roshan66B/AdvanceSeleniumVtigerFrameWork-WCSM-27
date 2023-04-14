package vtiger.GenericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;

/**
 * This class consists of all basic configuration annotations
 * @author ROSHAN
 *
 */
public class BaseClass {

	//Step 1: Create object of Generic Utilities
	public ExcelFileUtility eUtil=new ExcelFileUtility();
	public PropertyFileUtility pUtil=new PropertyFileUtility();
	public JavaUtility jUtil=new JavaUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	
	public WebDriver driver=null;
	public static WebDriver sDriver; //this is for listeners
	
	@BeforeSuite(groups = {"SmokeSuite","RegressionSuite"})
	public void bsConfig()
	{
		System.out.println("-----database connection successful---- ");
	}
	
	//@Parameters("browser") //chrome or firefox
	//@BeforeTest
	@BeforeClass(groups = {"SmokeSuite","RegressionSuite"})
	public void bcConfig(/*String BROWSER*/) throws IOException
	{
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		
		//Step 3: Launch the Browser - RUNTIME POLYMORPHISM
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
			System.out.println("-----"+BROWSER+" launched---");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
			System.out.println("-----"+BROWSER+" launched---");

		}
		else
		{
			System.out.println("Invalid browser name");
		}
		
		sDriver=driver; //this is for listeners
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
	}
	
	@BeforeMethod(groups = {"SmokeSuite","RegressionSuite"})
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		System.out.println("-----Login to app successful----");
	}
	
	@AfterMethod(groups = {"SmokeSuite","RegressionSuite"})
	public void amConfig()
	{
		HomePage hp=new HomePage(driver);
		hp.logoutOfApp(driver);
		
		System.out.println("-----Logout of app successful----");

	}
	
	//@AfterTest
	@AfterClass(groups = {"SmokeSuite","RegressionSuite"})
	public void acConfig()
	{
		driver.quit();
		System.out.println("-----Browser closed------");
	}
	
	@AfterSuite(groups = {"SmokeSuite","RegressionSuite"})
	public void asConfig()
	{
		System.out.println("------database closed successfully-----");
	}
}
