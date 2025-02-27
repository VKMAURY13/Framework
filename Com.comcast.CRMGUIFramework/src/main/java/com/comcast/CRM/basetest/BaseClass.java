package com.comcast.CRM.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.CRM.generic.databaseutility.DataBaseUtility;
import com.comcast.CRM.generic.fileutility.ExcelUtility;
import com.comcast.CRM.generic.fileutility.FileUtility;
import com.comcast.CRM.generic.webdriverutility.JavaUtility;
import com.comcast.CRM.generic.webdriverutility.UtilityClassObject;
import com.comcast.CRM.generic.webdriverutility.WebDriverUtility;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.LoginPage;

public class BaseClass {
	
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	
	public DataBaseUtility dblib = new DataBaseUtility();
	public ExcelUtility elib = new ExcelUtility();
	public FileUtility flib = new FileUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public JavaUtility jlib = new JavaUtility();
	public static String sbrowser = null;
	public String BROWSER = null;
	
	@BeforeSuite
	public void configBS() throws SQLException
	{
		Reporter.log("=====Connect to Database, Report config=====",true);
		dblib.getDbConnection();
		
	}
	
	@Parameters("BROWSER")
	@BeforeClass
	public void configBC(String BROWSER) throws IOException
	//public void configBC() throws IOException
	{
		Reporter.log("==========Launch Browser==========",true);
		//BROWSER = flib.getDataFromPropertiesFile("browser");
		
		//BROWSER=System.getProperty("browser",flib.getDataFromPropertiesFile("browser"));
		
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
			Reporter.log("Invalid Driver Choosen",true);
		
	sdriver = driver;
	UtilityClassObject.setDriver(driver);
	sbrowser = BROWSER;
	UtilityClassObject.setBrowser(BROWSER);
	Reporter.log(BROWSER,true);
	}
	
	@BeforeMethod
	public void configBM() throws IOException
	{
		Reporter.log("==========Login=========",true);
		LoginPage lp = new LoginPage(driver);
//		String URL = flib.getDataFromPropertiesFile("url");
//		String USERNAME = flib.getDataFromPropertiesFile("username");
//		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		String URL=System.getProperty("url",flib.getDataFromPropertiesFile("url"));
		String USERNAME = System.getProperty("username", flib.getDataFromPropertiesFile("username"));
		String PASSWORD = System.getProperty("password", flib.getDataFromPropertiesFile("password"));
		lp.loginIntoApp(URL, USERNAME, PASSWORD);
				
	}
	@AfterMethod
	public void configAM() throws InterruptedException
	{
		Thread.sleep(2000);
		HomePage hp = new HomePage(driver);
		hp.getProfileIcon().click();
		hp.getSignOut().click();
		Reporter.log("========LOGOUT========",true);
		
	}
	
    @AfterClass
    public void configAC()
    {
    	driver.quit();
    	Reporter.log("=======CLOSE BROWSER========",true);
    }
    @AfterSuite
    public void configAS()
    {
    	dblib.closeDBConnection();
    	Reporter.log("=========CLOSE DB, Report BACKUP==========",true);
    	
    }
	

}
