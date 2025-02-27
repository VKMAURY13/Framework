package Practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.listenerutility.ListenerImpClass;

public class AssertionAndLog extends BaseClass{
	
	@Test
	public void getTest()
	{
		Assert.assertEquals("vivek", "vivek123");
		Assert.assertEquals("Vivek", "Vivek@34", "FAILed.........");
//		if("vivek".equals(BROWSER))
//		{
//		ListenerImpClass.test.log(Status.PASS, "Vivek is a boy");
//		}
//		else
//		ListenerImpClass.test.log(Status.FAIL, "Vivek is a girl");
		
		
		
	}

}
