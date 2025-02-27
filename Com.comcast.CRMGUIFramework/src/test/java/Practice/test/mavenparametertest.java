package Practice.test;

import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import com.comcast.CRM.basetest.BaseClass;

/**
 * @author air
 * 
 */

public class mavenparametertest extends BaseClass{
	
	/**
	 * Test Script related to delete all product created in Single Page
	 * 
	 */
	
	@Test
	public void mavenpara()
	{
		
		// click on product
		driver.findElement(By.linkText("Products")).click();
		
		//Select All element
		driver.findElement(By.id("selectCurrentPageRec")).click();
		
		//click on delete button
		driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();
		
		//Accept POPup
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

}
