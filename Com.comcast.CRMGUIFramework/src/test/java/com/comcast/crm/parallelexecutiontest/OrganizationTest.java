package com.comcast.crm.parallelexecutiontest;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;


import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.CRM.objectrepositoryutility.OrganizationsPage;

public class OrganizationTest extends BaseClass{
	
	@Test
	public void createOrganizationwithOrgName() throws EncryptedDocumentException, IOException
	{
		//Fetch Data From Excel Sheet
		String ORGNAME =  elib.getDataFromExcel("Organization", 1, 2)+jlib.getRandomNumber();
		
		//click on Organization Link
		HomePage hp = new HomePage(driver);
		hp.getOrgmoduletab().click();
		
		//click on lookup Icon
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateneworgbtn().click();
				
		//Insert Organization name
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrganizationname().sendKeys(ORGNAME);
		
		//click on save button
		cnop.getSavebtn().click();
		
		//verification by HeaderText
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String headertext = oip.getHeaderText().getText();
		boolean status = headertext.contains(ORGNAME);
		Assert.assertTrue(status);
		
		//Verification by Organization Name text
		String actorgname = oip.getOrgnametext().getText();
		SoftAssert sa = new SoftAssert();
		Assert.assertEquals(actorgname, ORGNAME);
		
		sa.assertAll();
		
	}
	
	@Test
	public void createOrgWithPhonenumTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
		String organizationname = elib.getDataFromExcel("Organization", 4, 2)+jlib.getRandomNumber();
		String phonenum = Long.toString((long)elib.getDataFromExcelNumeric("Organization", 7, 3));
			
		//click on Organization Link
		HomePage hp = new HomePage(driver);
		hp.getOrgmoduletab().click();
		
		//click on Organization lookup icon
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateneworgbtn().click();
		
		//Enter LastName
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrganizationname().sendKeys(organizationname);
		
		//Insert phone Number
		cnop.getPhoneNumberTextField().sendKeys(phonenum);
		
		//click on Save Button
		cnop.getSavebtn().click();
	}
	
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
