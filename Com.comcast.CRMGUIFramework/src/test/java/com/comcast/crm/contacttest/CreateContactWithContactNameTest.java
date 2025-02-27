package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import com.comcast.CRM.generic.fileutility.ExcelUtility;
import com.comcast.CRM.generic.fileutility.FileUtility;
import com.comcast.CRM.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithContactNameTest {
	
	@Test
	public void createContactWithName() throws IOException
	{
		WebDriver driver = null;
		Workbook wb = null;
		
		WebDriverUtility wlib = new WebDriverUtility();
		ExcelUtility elib = new ExcelUtility();
		FileUtility flib = new FileUtility();
		
		//Data fetch from .properties File	
		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
		
		try {
		// Data fetch from Excel File
		String lastname = elib.getDataFromExcel("Contact", 1, 2);
		
		//launch webApplication
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}
		else
			System.out.println("Invalid Driver Choosen");
		
		//
		wlib.windowMaximize(driver);
		wlib.waitForPageToLoad(driver);
		driver.get(url);
		
		//Login into WebApplication
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		// Create Contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[contains(@title,'Create Contact...')]")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		
		//click on save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//wait
		 Thread.sleep(1000);
		 
		//verification through header
		String headertextmessage = driver.findElement(By.className("dvHeaderText")).getText();
		
		//Verification LastName
		String actuallastname = driver.findElement(By.id("mouseArea_Last Name")).getText();
		
		
		if(headertextmessage.contains(lastname) && actuallastname.contains(lastname))
		{
			System.out.println("=========Contact -  "+lastname+" Created===========> PASS");
		}
		else
			System.out.println("=========Contact -  "+lastname+" NOT Created===========> FAIL");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{		
			wb.close();
			driver.quit();
			System.out.println("===========WorkBook closed Successfully===============");
			System.out.println("===========Driver closed Successfully===============");
		}
		
	}

}
