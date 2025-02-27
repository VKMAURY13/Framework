package Practice.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.generic.webdriverutility.UtilityClassObject;
import com.comcast.CRM.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.CRM.objectrepositoryutility.OrganizationsPage;

public class Organization_UtilityObjectClass_test_Test extends BaseClass{
	
	@Test
	public void createOrganizationwithOrgName() throws EncryptedDocumentException, IOException
	{
		//Fetch Data From Excel Sheet
		UtilityClassObject.getTest().log(Status.INFO, "Fetch Data From Excel Sheet");
		String ORGNAME =  elib.getDataFromExcel("Organization", 1, 2)+jlib.getRandomNumber();
		
		//click on Organization Link
		UtilityClassObject.getTest().log(Status.INFO, "click on Organization Link");
		HomePage hp = new HomePage(driver);
		hp.getOrgmoduletab().click();
		
		//click on lookup Icon
		UtilityClassObject.getTest().log(Status.INFO, "click on lookup Icon");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateneworgbtn().click();
				
		//Insert Organization name
		UtilityClassObject.getTest().log(Status.INFO, "Insert Organization name");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrganizationname().sendKeys(ORGNAME);
		
		//click on save button
		UtilityClassObject.getTest().log(Status.INFO, "click on save button");
		cnop.getSavebtn().click();
		
		//verification by HeaderText
		UtilityClassObject.getTest().log(Status.INFO, "verification by HeaderText");
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String headertext = oip.getHeaderText().getText();
		boolean status = headertext.contains(ORGNAME);
		AssertJUnit.assertTrue(status);
		
		//Verification by Organization Name text
		UtilityClassObject.getTest().log(Status.INFO, "Verification by Organization Name text");
		String actorgname = oip.getOrgnametext().getText()+" ";
		SoftAssert sa = new SoftAssert();
		AssertJUnit.assertEquals(actorgname, ORGNAME);
		
		sa.assertAll();
		
	}

}
