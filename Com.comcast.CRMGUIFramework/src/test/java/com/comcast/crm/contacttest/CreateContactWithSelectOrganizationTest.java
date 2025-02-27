package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.CRM.generic.fileutility.ExcelUtility;
import com.comcast.CRM.generic.fileutility.FileUtility;
import com.comcast.CRM.generic.webdriverutility.JavaUtility;
import com.comcast.CRM.generic.webdriverutility.WebDriverUtility;


public class CreateContactWithSelectOrganizationTest {
	
	@Test
	public void createContact() throws EncryptedDocumentException, IOException, InterruptedException
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
		
		//fetch data from excel Sheet
		String lastname = elib.getDataFromExcel("Contact", 1, 2);
		String organizationname = elib.getDataFromExcel("Contact", 4, 3)+jlib.getRandomNumber();
		
		
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
				
				
				try {
               //login into application		
		        driver.findElement(By.name("user_name")).sendKeys(username);
		        driver.findElement(By.name("user_password")).sendKeys(password);
		        driver.findElement(By.id("submitButton")).click();
		
		       Thread.sleep(2000);
		        
		        //create precondition created as organization name present
		        
		      //create organization
				
				driver.findElement(By.linkText("Organizations")).click();
				driver.findElement(By.xpath("//img[contains(@title,'Create Organization...')]")).click();
				driver.findElement(By.name("accountname")).sendKeys(organizationname);
				driver.findElement(By.name("button")).click();
				
				//verification
				String currentorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				
				
				if(currentorgname.contains(organizationname))
				{
					System.out.println("======Organization is Created=====");
					
				}
				else
					System.out.println("======Organization Is NOT Created=====");
		
		        // Create Contact
				driver.findElement(By.linkText("Contacts")).click();
				driver.findElement(By.xpath("//img[contains(@title,'Create Contact...')]")).click();
				driver.findElement(By.name("lastname")).sendKeys(lastname);
				
				//Get Parent Window id
				String parentid = driver.getWindowHandle();
				
				
				//Select Organization 
				driver.findElement(By.xpath("//input[@name='account_name']/..//img")).click();
				
				//Switch to New Window
				wlib.switchToNewBrowserTab(driver, "module=Accounts&action=Popup");
				
				//Select Organization Name as in Search Box
//				Select selectsearchfield = new Select(driver.findElement(By.name("search_field")));
//				selectsearchfield.selectByValue("accountname");
				
				wlib.select(driver.findElement(By.name("search_field")), "Organization Name");
				
				//Insert Organization name to search
				driver.findElement(By.id("search_txt")).sendKeys(organizationname);
				
				// Click on Search Now button
				driver.findElement(By.name("search")).click();
				
				
				//Select Organization name
				
				driver.findElement(By.linkText(organizationname)).click();
				Thread.sleep(2000);
				
				//Switch to main Window
				
				driver.switchTo().window(parentid);
				
				
				//click on save button
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				Thread.sleep(3000);
				
				//Verification
				String headertext = driver.findElement(By.className("dvHeaderText")).getText();
				System.out.println(headertext);
				String lastnametext = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
				System.out.println(lastnametext);
				Thread.sleep(3000);
				String organizartionnametext = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText();
				System.out.println(organizartionnametext);
				
				if(headertext.contains(lastname) && lastnametext.trim().equals(lastname) && organizartionnametext.trim().equals(organizationname))
				{
					System.out.println("===============Contact with "+lastname+" and OrgName "+organizationname+" is Created======> PASSED");
				}
				else
					System.out.println("=============Test Script is Failed...............");
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
