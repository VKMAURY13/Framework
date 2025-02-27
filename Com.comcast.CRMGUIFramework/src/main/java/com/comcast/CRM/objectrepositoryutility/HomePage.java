package com.comcast.CRM.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement homemodulename;
	
	//HOME TEXT VERIFICATION
			public WebElement getHomemodulename() {
				return homemodulename;
			}
	//ORGANIZATION LINK
	@FindBy(linkText = "Organizations")
	private WebElement orglink;

	public WebElement getOrgmoduletab() {
		return orglink;
	}
	
	//CONTACT LINK
	@FindBy(linkText = "Contacts")
	private WebElement contactlink;
	
	public WebElement getContactLink()
	{
		return contactlink;
	}
	
	//LOGOUT WEBELEMENT
	@FindBy(xpath="//span[@class='userName']/../..//img")
	private WebElement profileicon;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signoutlnk;
	
	public WebElement getProfileIcon()
	{
		return profileicon;
	}
	
	public WebElement getSignOut()
	{
		return signoutlnk;
	}
	
	
	
	//sign out Action
	public void signOut() throws InterruptedException
	{
		Actions action =  new Actions(driver);
		action.moveToElement(getProfileIcon()).perform();;
		Thread.sleep(2000);
		
		//getProfileIcon().click();
		action.click(getSignOut()).perform();
	}
	
	
	

}
