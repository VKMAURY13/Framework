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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreateOrgWithIndustryTest {
	
	@Test
    public void createOrgWithIndustry() throws EncryptedDocumentException, IOException
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
		
		String orgname = sh.getRow(4).getCell(2).getStringCellValue()+randomnumber;
		String industryname = sh.getRow(4).getCell(3).getStringCellValue();
		String industrytype = sh.getRow(4).getCell(4).getStringCellValue();
		
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
		
		//select industry
		Select select = new Select(driver.findElement(By.name("industry")));
		select.selectByValue(industryname);
		
		//select type
		Select selecttype = new Select(driver.findElement(By.name("accounttype")));
		selecttype.selectByValue(industrytype);
		
		//click on save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		Thread.sleep(2000);
		
		//Verification through Industry Name text
		String actualindustryname = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		
		System.out.println(actualindustryname);
		
		Thread.sleep(2000);
		
		//verification of Industry Type Text
		String actualindustrytype = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		
		System.out.println(actualindustrytype);
		
		if(actualindustryname.contains(industryname) && actualindustrytype.contains(industrytype))
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
