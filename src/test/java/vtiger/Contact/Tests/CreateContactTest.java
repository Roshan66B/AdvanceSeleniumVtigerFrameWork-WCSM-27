package vtiger.Contact.Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.BaseClass;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.ContactInfoPage;
import vtiger.ObjectRepository.ContactPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;

@Listeners(vtiger.GenericUtilities.ListenersImplementation.class)
public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws IOException
	{		
		String LASTNAME= eUtil.readDataFromExcel("Contact", 1, 2)+jUtil.getRandomNumber();
		
		//Step 5: Navigate to contact link
		HomePage hp=new HomePage(driver);
		hp.clickOnContacts();
			
		//Step 6: Click on contact look up image
		ContactPage cp=new ContactPage(driver);
		cp.clickOnCreateContactLookUpImg();
			
		//Step 7: Create contact with mandatory fields
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
			
		//Step 8: Validate for contact 
		ContactInfoPage cip=new ContactInfoPage(driver);
		String ContactHeader=cip.getContactHeader();
		Assert.assertTrue(ContactHeader.contains(LASTNAME));
	}
}
