package com.comcast.crm.pomorgtest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.CRM.generic.fileutility.ExcelUtility;
import com.comcast.CRM.generic.fileutility.FileUtility;
import com.comcast.CRM.generic.webdriverutility.JavaUtility;
import com.comcast.CRM.generic.webdriverutility.WebDriverUtility;
import com.comcast.CRM.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.LoginPage;
import com.comcast.CRM.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.CRM.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationPOMtest {
	
	@Test
	public void CreateOrganization() throws IOException, InterruptedException
	{
		ExcelUtility elib = new ExcelUtility();
		FileUtility flib = new FileUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		JavaUtility jlib = new JavaUtility();
		
		//Access Common Data
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL =  flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//Access Busioness Data
		String ORGNAME =  elib.getDataFromExcel("Sheet1", 1, 2)+jlib.getRandomNumber();
		
		//launch browser
		WebDriver driver = wlib.toLaunchBrowser(BROWSER);
	
		//Launch WebApplication
		driver.get(URL);
		
		//Login Into Application
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(USERNAME, PASSWORD);
		
		//Verification of Home Page
		HomePage hp = new HomePage(driver);
		hp.getOrgmoduletab().click();
		
		//click on lookup Icon
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateneworgbtn().click();
		
		//Insert Organization name
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.getOrganizationname().sendKeys(ORGNAME);
		cno.getSavebtn().click();
		
		//wait
		
		Thread.sleep(2000);
		
		//Verification of Creation of Organization page
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		if(oip.getHeaderText().getText().contains(ORGNAME) && oip.getOrgnametext().getText().trim().contains(ORGNAME))
		{
			System.out.println(ORGNAME +" is CREAED ======> PASS");
		}
		else
			System.out.println("Test Case Failed........");
		
		//click on Organizations
		//HomePage hp1 = new HomePage(driver);
		hp.getOrgmoduletab().click();
			
		//Select as Organization name
		op.getSearchforTextfield().sendKeys(ORGNAME);
		WebElement dropdown= op.getOrgdropdown();
		wlib.select(dropdown, 1);
		op.getSearchnowbtn().click();
		
		//
		Thread.sleep(2000);
		
		//select Dynamic element
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']/../..//a[text()='del']")).click();
		
		//Accept pop up
		wlib.switchToAlertAndAccept(driver);
		
		//Logout
		hp.signOut();
	}

}
