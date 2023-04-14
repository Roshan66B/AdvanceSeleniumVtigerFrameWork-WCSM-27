package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage 
{
	//Declaration
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement CreateContactLookUpImg;
	
	//Initialization
	public ContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCreateContactLookUpImg() {
		return CreateContactLookUpImg;
	}
	
	//Business libraries
	/**
	 * This method will click on create contact look up image
	 */
	public void clickOnCreateContactLookUpImg()
	{
		CreateContactLookUpImg.click();
	}
}
