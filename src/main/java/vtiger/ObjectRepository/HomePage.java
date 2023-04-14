package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility
{
	//Rule1: Create a seperate POM class for every web page
	
	//Rule 2: Identify all the web elements using annotations
	@FindBy(linkText="Organizations")
	private WebElement OrganizationLink;
	
	@FindBy(linkText="Contacts")
	private WebElement ContactsLink;
	
	@FindBy(linkText="Opportunities")
	private WebElement opportunitiesLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdministratorImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement SignoutLink;
	
	//Rule 3: Initialize these web elements with a constructor
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4: Provide Getters to access these elements
	public WebElement getOrganizationLink() {
		return OrganizationLink;
	}

	public WebElement getContactsLink() {
		return ContactsLink;
	}
	
	public WebElement getSignOut() {
		return SignoutLink;
		
	
	}

	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}

	public WebElement getAdministratorImg() {
		return AdministratorImg;
	}

	//Business libraries - Generic methods- Project specific.
	/**
	 * This method will click on Organization link
	 */
	public void clickOnOrganization()
	{
		OrganizationLink.click();
	}
	
	/**
	 * This method will click on Contacts link
	 */
	public void clickOnContacts()
	{
		ContactsLink.click();
	}
	
	/**
	 * This method will perform sign out operation on web app
	 * @param driver
	 */
	public void logoutOfApp(WebDriver driver)
	{
		mouseHoverAction(driver, AdministratorImg);
		SignoutLink.click();
	}
}
