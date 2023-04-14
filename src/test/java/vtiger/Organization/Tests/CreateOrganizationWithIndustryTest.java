package vtiger.Organization.Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.BaseClass;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;

@Listeners(vtiger.GenericUtilities.ListenersImplementation.class)
public class CreateOrganizationWithIndustryTest extends BaseClass {

	@Test
	public void createOrganizationWithIndustryTest() throws IOException
	{			
		String ORGNAME = eUtil.readDataFromExcel("Organization", 4, 2)+jUtil.getRandomNumber();
						
		//Step 5: Navigate to organization link
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganization();
				
		//Step 6: Click on Organization look up image
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrganizationLookUpImage();
		
		//Step 7: Create organization with mandatory fields
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME, "Chemicals");
		
		//Step 8: Validate for Organization
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String OrgHeader=oip.getOrganizationHeader();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
	}

}
