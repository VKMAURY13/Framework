package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class CreateOrganizationWithPhoneTest {
	
	@Test
	public void createOrgWithPhone() throws EncryptedDocumentException, IOException
	{
		
		WebDriver driver = null;
		Workbook wb = null;
		Random random = new Random();
		int randomnumber = random.nextInt(10);
		
		//Data fetch from .properties File
		FileInputStream fis  = new FileInputStream("/Users/air/Desktop/JavaTraining/SimpleVtiger/src/test/resources/TestData/vtcommondata.properties");
		Properties p  =new Properties();
		p.load(fis);
		
		
		String browser = p.getProperty("browser");
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		try {
		// Data fetch from Excel File
		FileInputStream exfis = new FileInputStream("/Users/air/Desktop/IMP Folder/TestCaseOrg.xlsx");
		wb = WorkbookFactory.create(exfis);
		Sheet sh = wb.getSheet("Organization");
		
		String orgname = sh.getRow(7).getCell(2).getStringCellValue()+randomnumber;
		String phonenum =Long.toString((long)sh.getRow(7).getCell(3).getNumericCellValue());
		
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		
		
		//Login into WebApplication
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		// Create Organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[contains(@title,'Create Organization...')]")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		
		//Enter phone Number
		driver.findElement(By.id("phone")).sendKeys(phonenum);
		
		//click on save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//wait
		 Thread.sleep(1000);
		
		//Verification Mobile Number
		String actualphonenum = driver.findElement(By.id("mouseArea_Phone")).getText();
		
		
		if(actualphonenum.contains(phonenum))
		{
			System.out.println("=========Organization -  "+orgname+" Created===========> PASS");
		}
		else
			System.out.println("=========Organization -  "+orgname+" NOT Created===========> FAIL");
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
