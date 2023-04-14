package Practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.WebDriverUtility;

public class CreateContactWithOrganization {

	public static void main(String[] args) throws Throwable 
	{
		/* Read the data from property file*/
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties property=new Properties();
		property.load(fis);
		String BROWSER=property.getProperty("browser");
		String URL=property.getProperty("url");
		String USERNAME=property.getProperty("username");
		String PASSWORD=property.getProperty("password");
		
		/*Random class avoid duplicates*/
		Random ran=new Random();
		int ranNum = ran.nextInt(1000);
		
		/*Read the data from excel sheet for organization details*/
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		String orgName=wb.getSheet("Organization").getRow(4).getCell(3).getStringCellValue()+ranNum;
		wb.close();
		
		/*Read the data from excel sheet for contact details*/
		FileInputStream fis2=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb1=WorkbookFactory.create(fis2);
		String lastName=wb1.getSheet("Contact").getRow(4).getCell(2).getStringCellValue()+ranNum;
		wb1.close();
		
		
		WebDriver driver=null;
		
		//Step 1: Launching the browser by using RUNTIME-POLYMORPHISM
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		
		//Step 2: Used to maximize the window
		driver.manage().window().maximize();
		
		//Step 3: Implicitly wait statement
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		//Step 4: Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 5: Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 6: Click on Organization look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step 7: Create organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//Step 8: Save
		driver.findElement(By.name("button")).click();
		
		//Step 9: Validate for Organization
		String orgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains(orgName))
		{
			System.out.println(orgHeader+"-------PASS-------");
		}
		else
		{
			System.out.println("-----Failed------");
		}
		
		//Step 10: Navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 11: Click on contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 12: Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		//Step 13: Click on '+' symbol to select organization
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		
		//Step 14: switch to child window
		WebDriverUtility wUtil=new WebDriverUtility();
		wUtil.switchToWindow(driver, "Accounts&action");
		
		//Step 15: To pass selected organization in search text field
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		
		//Step 16: Click on search button
		driver.findElement(By.name("search")).click();
		
		Thread.sleep(2000);
		
		//Step 17: Used to click on searched organization name by doing concatenation
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		Thread.sleep(2000);
		
		//Step 18: Switch back to parent window and given organization must be selected
		wUtil.switchToWindow(driver, "Contacts&action");
		
		
		//Step 19: Click on save button
		driver.findElement(By.name("button")).click();
		
		//Step 20: Validate for contact page
		String cntHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(cntHeader.contains(lastName))
		{
			System.out.println(cntHeader+"-------PASS-------");
		}
		else
		{
			System.out.println("-----Failed------");
		}
		
		//Step 21: Logout of application
		WebElement img=driver.findElement(By.xpath("(//td[@class='small'])[2]"));
		Actions a=new Actions(driver);
		a.moveToElement(img).perform();
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
