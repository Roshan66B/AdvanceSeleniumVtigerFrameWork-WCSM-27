package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;

public class POMClassPractice 
{

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver=new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		
		LoginPage lp=new LoginPage(driver);
		//lp.getUserNameEdt().sendKeys("admin");
		//lp.getPasswordEdt().sendKeys("admin");
		//lp.getSubmitBtn().click();
	
		lp.loginToApp("admin", "admin");
		
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganization();
		//hp.clickOnContacts();
		
		

		
	}
}
