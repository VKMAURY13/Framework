package com.comcast.crm.pomorgtest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithPhoneNumberTest extends BaseClass  {
	
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

}
