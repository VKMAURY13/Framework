package com.comcast.CRM.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	//Launch Browser
	public WebDriver toLaunchBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			return new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			return new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			return new SafariDriver();
		} else {
			System.out.println("You have entered invalid browser");
			return null;
		}
		
	}
	//Maximize window
	public void windowMaximize(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	//implicit wait
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	//Explicit Wait
	public void waitForElementPresent(WebDriver driver, WebElement element)
	{
	WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(20));
	wt.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToNewBrowserTab(WebDriver driver, String partialURL)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		
		while(itr.hasNext())
		{
			String windowID = itr.next();
			driver.switchTo().window(windowID);
			String actURL = driver.getCurrentUrl();
			if(actURL.contains(partialURL))
			{
				break;
			}
		}
	}
	
	public void switchToTabOnTitle(WebDriver driver, String partialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		
		while(itr.hasNext())
		{
			String windowID = itr.next();
			driver.switchTo().window(windowID);
			String actTitle = driver.getTitle();
			if(actTitle.contains(partialTitle))
			{
				break;
			}
		}
	}
	
	//Method to Switch Frame
	
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver, String nameID)
	{
		driver.switchTo().frame(nameID);
	}
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	
	//Switch to Alerts
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	//Handle drop down
	public void select(WebElement element, String text)
	{
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	public void select(WebElement element, int index)
	{
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	
	//Mouse Actions
	public void mouseOnElement(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

}
