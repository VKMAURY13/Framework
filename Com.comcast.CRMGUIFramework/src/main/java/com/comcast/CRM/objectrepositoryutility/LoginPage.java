package com.comcast.CRM.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.CRM.generic.webdriverutility.WebDriverUtility;

public class LoginPage {
	
WebDriver driver;
WebDriverUtility wblib = new WebDriverUtility();
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernameedt;
	
	@FindBy(name="user_password")
	private WebElement userpasswordedt;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;
	
	public WebElement getUsernameedt()
	{
		return usernameedt;
	}
	
	public WebElement getUserpassword()
	{
		return userpasswordedt;
	}
	
	public WebElement getLoginbtn()
	{
		return loginbtn;
	}
	
	//Busioness method
	public void loginIntoApp(String Username, String password)
	{
		wblib.windowMaximize(driver);
		wblib.waitForPageToLoad(driver);
		usernameedt.sendKeys(Username);
		userpasswordedt.sendKeys(password);
		loginbtn.click();
	}
	
	//Busioness method
		public void loginIntoApp(String URL,String Username, String password)
		{
			wblib.windowMaximize(driver);
			wblib.waitForPageToLoad(driver);
			driver.get(URL);
			usernameedt.sendKeys(Username);
			userpasswordedt.sendKeys(password);
			loginbtn.click();
		}

}
