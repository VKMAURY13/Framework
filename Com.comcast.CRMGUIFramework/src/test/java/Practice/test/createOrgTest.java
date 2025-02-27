package Practice.test;

import org.testng.annotations.Test;

import org.testng.Assert;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.asserts.SoftAssert;

import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.CRM.objectrepositoryutility.HomePage;
import com.comcast.CRM.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.CRM.objectrepositoryutility.OrganizationsPage;


public class createOrgTest extends BaseClass{
	
	@Test(retryAnalyzer = com.comcast.CRM.listenerutility.RetryImpementationClass.class)
	public void createOrganization() throws EncryptedDocumentException, IOException
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
		String actorgname = oip.getOrgnametext().getText().trim()+" "; //error
		SoftAssert sa = new SoftAssert();
		Assert.assertEquals(actorgname, ORGNAME);
		
		sa.assertAll();
		
	}
	

}
