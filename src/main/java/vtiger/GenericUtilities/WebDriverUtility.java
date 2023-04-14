package vtiger.GenericUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of generic methods related to web driver actions
 * @author Asus
 *
 */
public class WebDriverUtility {

	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait for 20 seconds for the page load
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	/**
	 * This method will wait until a particular web element is visible
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait until particular web element is clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handle drop down by index
	 */
	public void handleDropdown(WebElement Element, int index)
	{
		Select sel=new Select(Element);
		sel.selectByIndex(index);
	}
	
	/**
	 * This method will handle drop down by value
	 * @param element
	 * @param value
	 */
	public void handleDropdown(WebElement element, String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	
	/**
	 * This method will handle drop down by visible text
	 * @param Text
	 * @param element
	 */
	public void handleDropdown(String Text, WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(Text);
	}
	
	/**
	 * This method will perform mouse hover action on a web element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform right click anywhere on the page
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * This method will perform right click on particular web element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will perform double click anywhere on the page
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click on the particular web element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop from one element to another
	 * @param driver
	 * @param srcElement
	 * @param dstElement
	 */
	public void dragAndDropAction(WebDriver driver, WebElement srcElement, WebElement dstElement)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(srcElement, dstElement).perform();
	}
	
	/**
	 * This method will press the enter key
	 * @throws AWTException 
	 */
	public void pressEnterKey() throws AWTException
	{
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method will release the enter key
	 * @throws AWTException 
	 */
	public void releaseEnterKey() throws AWTException 
	{
		Robot r=new Robot();
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method will handle frames with index
	 * @param driver
	 * @param index
	 */
	public void handleFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will handle frames with name or id
	 * @param driver
	 * @param nameOrId
	 */
	public void handleFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will handle frames with web element
	 * @param driver
	 * @param element
	 */
	public void handleFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * 
	 * @param driver
	 */
	public void handleParenetFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This
	 */
	public void handleDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will accept alert pop up
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will dismiss alert pop up
	 * @param driver
	 */
	public void dismisAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will capture and return the alert text
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will take screen shot and save it in screenshots folder
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */
	public String  takesScreenShot(WebDriver driver, String screenshotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(".\\Screenshots\\"+screenshotName+".png");
		FileUtils.copyFile(src, dst); // commons io dependency
		
		return dst.getAbsolutePath(); //used in extent reports
	}
	
	/**
	 * This method will switch to window based on partial window title
	 * @param driver
	 * @param PartialWinTitle
	 */
	public void switchToWindow(WebDriver driver,String PartialWinTitle)
	{
		//step 1: capture all window IDs
		Set<String> winIds=driver.getWindowHandles();
		
		//step 2: use for each loop and navigate to each window
		for(String win:winIds)
		{
			//step 3: capture the title of each window
			String currentTitle=driver.switchTo().window(win).getTitle();
			
			//step 4: compare the current title with partial window title
			if(currentTitle.contains(PartialWinTitle))
			{
				break;
			}
		}
	}
	
	/**
	 * This method will scroll randomly downwards
	 * @param driver
	 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scroll(0,500)", "");
	}
	
	/**
	 * This method will scroll down until the particular web element
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	
}
