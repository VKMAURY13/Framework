package com.comcast.crm.pomorgtest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.objectrepositoryutility.ContactsPage;
import com.comcast.CRM.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.CRM.objectrepositoryutility.HomePage;

public class CreateContactWithSupportDateTest extends BaseClass{
	
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
