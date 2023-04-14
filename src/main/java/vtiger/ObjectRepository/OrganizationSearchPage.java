package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationSearchPage 
{
	//
	@FindBy(name="search_text")
	private WebElement OrganizationSearchTextField;
	
	@FindBy(name="search")
	private WebElement ClickOnSearchNowButton;
	
	//
	public OrganizationSearchPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//
	public WebElement getOrganizationSearchTextField() {
		return OrganizationSearchTextField;
	}

	public WebElement getClickOnSearchNow() {
		return ClickOnSearchNowButton;
	}
	
	//
	public void organizationSearchTextField(String orgName)
	{
		OrganizationSearchTextField.sendKeys(orgName);
	}
	
	public void searchNowButton()
	{
		ClickOnSearchNowButton.click();
	}
}
