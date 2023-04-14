package vtiger.Organization.Tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
public class CreateMultipleOrgTest extends BaseClass
{
	ExcelFileUtility eUtil=new ExcelFileUtility();
	@Test(dataProvider="OrgWithIndustry")
	public void createOrgTest(String ORG, String INDUSTRY) throws IOException
	{
		String ORGNAME = ORG+jUtil.getRandomNumber();
				
		//Step 5: Navigate to organization link
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganization();
					
		//Step 6: Click on Organization look up image
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrganizationLookUpImage();
					
		//Step 7: Create organization with mandatory fields
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME, INDUSTRY);
			
		//Step 8: Validate for Organization 
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String OrganizationHeader=oip.getOrganizationHeader();
		Assert.assertTrue(OrganizationHeader.contains(ORGNAME));		
	}
	
	@DataProvider(name="OrgWithIndustry")
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		Object[][] data = eUtil.readDataFromExcelToDataProvider("dataProviderOrg");
		return data; 
	}
}
