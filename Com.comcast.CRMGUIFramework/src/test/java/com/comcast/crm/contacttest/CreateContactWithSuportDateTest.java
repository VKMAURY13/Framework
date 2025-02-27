package com.comcast.crm.contacttest;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import com.comcast.CRM.generic.fileutility.ExcelUtility;
import com.comcast.CRM.generic.fileutility.FileUtility;
import com.comcast.CRM.generic.webdriverutility.JavaUtility;
import com.comcast.CRM.generic.webdriverutility.WebDriverUtility;


public class CreateContactWithSuportDateTest {
	
	@Test
	public void createContact() throws IOException, InterruptedException
	{
		WebDriver driver = null;
		WebDriverUtility wlib = new WebDriverUtility();
		ExcelUtility elib = new ExcelUtility();
		FileUtility flib = new FileUtility();
		JavaUtility jlib = new JavaUtility();
		
		
		//Fetch data from properties file
		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
		try {
		//fetch data from excel Sheet
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
				//Launch Web Application
				wlib.windowMaximize(driver);
				wlib.waitForPageToLoad(driver);
				driver.get(url);
		
               //login into application		
		        driver.findElement(By.name("user_name")).sendKeys(username);
		        driver.findElement(By.name("user_password")).sendKeys(password);
		        driver.findElement(By.id("submitButton")).click();
		
		        // Create Contact
				driver.findElement(By.linkText("Contacts")).click();
				driver.findElement(By.xpath("//img[contains(@title,'Create Contact...')]")).click();
				driver.findElement(By.name("lastname")).sendKeys(lastname);
				
				//Select Date and Format
				String startdate = jlib.getSystemDateYYYYMMDD();
				String enddate = jlib.getRequiredDateYYYYMMDD(30);
				
				//clear and pass current date start date
				WebElement supportstatrdate = driver.findElement(By.name("support_start_date"));
				supportstatrdate.clear();
				supportstatrdate.sendKeys(startdate);
				
				
				//clear and pass 30 days after date from CurrentDate
				WebElement supportenddate = driver.findElement(By.name("support_end_date"));
				supportenddate.clear();
				supportenddate.sendKeys(enddate);
				
				// click on save button
				
				Thread.sleep(3000);
				
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
				
		finally
		{
			driver.quit();
			System.out.println("====================WORKBOOK & SHEET CLOSED successfully===========================");
		}
		
	}

}
