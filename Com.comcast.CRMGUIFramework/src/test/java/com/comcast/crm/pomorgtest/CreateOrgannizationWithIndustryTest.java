package com.comcast.crm.pomorgtest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.OrganizationsPage;

public class CreateOrgannizationWithIndustryTest extends BaseClass{
	
	@Test
	public void createOrganization() throws EncryptedDocumentException, IOException, InterruptedException
	{
		String orgname = elib.getDataFromExcel("Organization", 4, 2)+jlib.getRandomNumber();
		//click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.getOrgmoduletab().click();
		
		//Click on Lookup icon
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateneworgbtn().click();
		
		//Enter OrgName
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrganizationname().sendKeys(orgname);
		
		//Select Industry
		String Industryname = elib.getDataFromExcel("Organization", 4, 3);
		WebElement dropdown = cnop.getIndustryDropdown();
		wlib.select(dropdown, Industryname);
		
		//click on save button
		cnop.getSavebtn().click();
		
		//Thread.sleep(5000);
		
	}

}
