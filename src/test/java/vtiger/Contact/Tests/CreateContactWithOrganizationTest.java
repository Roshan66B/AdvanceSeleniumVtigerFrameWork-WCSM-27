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
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;

@Listeners(vtiger.GenericUtilities.ListenersImplementation.class)
public class CreateContactWithOrganizationTest extends BaseClass
{

	@Test(groups = "RegressionSuite")
	public void createContactWithOrganizationTest() throws IOException
	{
		String ORGNAME = eUtil.readDataFromExcel("Contact", 4, 3)+jUtil.getRandomNumber();
		String LASTNAME= eUtil.readDataFromExcel("Contact", 4, 2)+jUtil.getRandomNumber();
						
		//Step 5: Navigate to organization link
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganization();
							
		//Step 6: Click on Organization look up image
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrganizationLookUpImage();
								
		//Step 7: Create organization with mandatory fields
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createNewOranization(ORGNAME);
			
		//Step 8: Validate for Organization
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String OrgHeader=oip.getOrganizationHeader();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		
		//Step 9: Navigate to contact link
		hp.clickOnContacts();
		
		//Step 10: Click on contact look up image
		ContactPage cp=new ContactPage(driver);
		cp.clickOnCreateContactLookUpImg();
				
		//Step 11: Create contact with mandatory fields
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(driver, LASTNAME, ORGNAME);
		wUtil.takesScreenShot(driver, "Screenshot1");
		
		//Step 12: Validate for contact page
		ContactInfoPage cip=new ContactInfoPage(driver);
		String ContactHeader =cip.getContactHeader();
		Assert.assertTrue(ContactHeader.contains(LASTNAME));
	}

}
