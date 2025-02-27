package com.comcast.CRM.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;
	
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement organizationname;
	
	@FindBy(xpath = "//input[@class='crmbutton small save']")
	private WebElement savebtn;

	
	public WebElement getOrganizationname() {
		return organizationname;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	//Industry drop down
	@FindBy(name="industry")
	private WebElement industrydropdownbtn;
	
	public WebElement getIndustryDropdown()
	{
		return industrydropdownbtn;
	}
	
	//Phone Number
	@FindBy(id="phone")
	private WebElement phonenumtextfield;
	
	public WebElement getPhoneNumberTextField()
	{
		return phonenumtextfield;
	}
	
	

}
