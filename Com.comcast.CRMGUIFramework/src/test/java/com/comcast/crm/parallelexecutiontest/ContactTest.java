package com.comcast.crm.parallelexecutiontest;

import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;


import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.objectrepositoryutility.ContactsPage;
import com.comcast.CRM.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.CRM.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.OrganizationsPage;

public class ContactTest extends BaseClass{
	@Test
	public void createContactTest() throws EncryptedDocumentException, IOException
	{
		//click on contact link
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		//click contact lookup icon
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLooupIcon().click();
		
		//Enter LastName
		String lastname = elib.getDataFromExcel("Contact", 1, 2);
		CreatingNewContactPage cwc = new CreatingNewContactPage(driver);
		cwc.getLastNameTextField().sendKeys(lastname);
		
		//click on save button
		cwc.getSavebtn().click();
	}
	
	@Test
	public void createContactOrgTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
		//click on Organization link
		HomePage hp = new HomePage(driver);
		hp.getOrgmoduletab().click();
		
		//click on Organization lookup icon
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateneworgbtn().click();
				
		//Enter LastName
		String organizationname = elib.getDataFromExcel("Contact", 7, 3)+jlib.getRandomNumber();
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrganizationname().sendKeys(organizationname);
				
		//click on Save Button
		cnop.getSavebtn().click();
		
		Thread.sleep(2000);
		
		//click on contact link
		hp.getContactLink().click();
		
		//click on Contacts lookup Icon
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLooupIcon().click();
		
		//Enter Last Name into Last Name Text Field
		String lastname = elib.getDataFromExcel("Contact", 1, 2);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(lastname);
		
		//Click on Organization look up Icon
		cncp.getOrgLookupIcon().click();
		
		//Switch Parent to Child window
		String Text = "module=Accounts&action=Popup";
		wlib.switchToNewBrowserTab(driver, Text);
		
		//Select Organization Name As Selection
		String text = "Organization Name";
		wlib.select(cncp.getSelectTextDropdown(), text);
		
		//Enter used Organization name in Search Text Field
		cncp.getSearchOrgText().sendKeys(organizationname);
		
		//click on Search Now Button
		cncp.getSearchNowBtn().click();
		
		//Select Organization
		driver.findElement(By.xpath("//a[text()='"+organizationname+"']")).click();
		
		
		//Switch Parent to Child window
		String partialurl = "module=Contacts&action=EditView";
		wlib.switchToNewBrowserTab(driver, partialurl);
		
		//click on save button
		cncp.getSavebtn().click();
	}

	@Test
	public void createContactWithDate() throws EncryptedDocumentException, IOException
	{
		//click on contact link
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
				
		//click contact lookup icon
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLooupIcon().click();
				
		//Enter LastName
		String lastname = elib.getDataFromExcel("Contact", 1, 2);
		CreatingNewContactPage cwc = new CreatingNewContactPage(driver);
		cwc.getLastNameTextField().sendKeys(lastname);
		
		//Select CurrentDate in Support Start Date
		String curdate = jlib.getSystemDateYYYYMMDD();
		cwc.getStartDate().clear();
		cwc.getStartDate().sendKeys(curdate);
		
		//Select 30 Days After in  Support End Date
		String requireddate = jlib.getRequiredDateYYYYMMDD(30);
		cwc.getEndDate().clear();
		cwc.getEndDate().sendKeys(requireddate);
				
		//click on save button
		cwc.getSavebtn().click();
	}

}
