package com.comcast.crm.basetest2;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.CRM.generic.databaseutility.DataBaseUtility;
import com.comcast.CRM.generic.fileutility.ExcelUtility;
import com.comcast.CRM.generic.fileutility.FileUtility;
import com.comcast.CRM.generic.webdriverutility.WebDriverUtility;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.LoginPage;

public class BaseClass {
	
	WebDriver driver = null;
	
	DataBaseUtility dblib = new DataBaseUtility();
	ExcelUtility elib = new ExcelUtility();
	FileUtility flib = new FileUtility();
	WebDriverUtility wlib = new WebDriverUtility();
	
	@BeforeSuite
	public void configBS() throws SQLException
	{
		System.out.println("=====Connect to Database, Report config=====");
		dblib.getDbConnection();
		
	}
	
	@BeforeClass
	public void configBC() throws IOException
	{
		System.out.println("==========Launch Browser==========");
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}
		else
			System.out.println("Invalid Driver Choosen");
	}
	
	@BeforeMethod
	public void configBM() throws IOException
	{
		System.out.println("==========Login=========");
		LoginPage lp = new LoginPage(driver);
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		lp.loginIntoApp(URL, USERNAME, PASSWORD);
				
	}
	@AfterMethod
	public void configAM() throws InterruptedException
	{
		System.out.println("========LOGOUT========");
		HomePage hp = new HomePage(driver);
		hp.signOut();
	}
	
    @AfterClass
    public void configAC()
    {
    	System.out.println("=======CLOSE BROWSER========");
    	driver.quit();
    }
    @AfterSuite
    public void configAS()
    {
    	System.out.println("=========CLOSE DB, Report BACKUP==========");
    	
    }
	

}
