package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility
{
	//Declaration
	@FindBy(name="accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name="industry")
	private WebElement IndustryDropdown;
	
	@FindBy(name="accounttype")
	private WebElement TypeDropdown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//Initialization
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndustryDropdown() {
		return IndustryDropdown;
	}

	public WebElement getTypeDropdown() {
		return TypeDropdown;
	}
	
	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	//Business Libraries
	/**
	 * This method will create Organization with mandatory information
	 * @param ORGNAME
	 */
	public void createNewOranization(String ORGNAME)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		SaveBtn.click();
	}
	
	/**
	 * This method will create Organization with industry drop down
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createNewOrganization(String ORGNAME,String INDUSTRY)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		handleDropdown(IndustryDropdown, INDUSTRY);
		SaveBtn.click();
	}
	
	public void createNewOrganization(String ORGNAME,String INDUSTRY,String TYPE)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		handleDropdown(IndustryDropdown, INDUSTRY);
		handleDropdown(TypeDropdown, TYPE);
		SaveBtn.click();
	}
}
