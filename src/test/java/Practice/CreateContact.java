package Practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class CreateContact {

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
		
		/*Read the data from excel sheet*/
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		String lastName=wb.getSheet("Contact").getRow(1).getCell(2).getStringCellValue()+ranNum;
		wb.close();
		
		WebDriver driver=null;
		
		//Step 1: Launching he browser by using RUNTIME-POLYMORPHISM
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		//Step 2: Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4: Click on contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 5: Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		//Step 6: Save
		driver.findElement(By.name("button")).click();
		
		//Step 7: Validate for contact 
		String cntHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(cntHeader.contains(lastName))
		{
			System.out.println(cntHeader+"-------PASS-------");
		}
		else
		{
			System.out.println("-----Failed------");
		}
		
		//Step 8: Logout of application
		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Sign out successful");
	}

}
