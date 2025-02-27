package com.comcast.CRM.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;
	
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createneworgbtn;

	public WebElement getCreateneworgbtn() {
		return createneworgbtn;
	}
	
	@FindBy(name="search_field")
	private WebElement orgdropdown;
	
	@FindBy(name="search_text")
	private WebElement searchforTextfield;
	
	@FindBy(name="submit")
	private WebElement searchnowbtn;

	public WebElement getOrgdropdown() {
		return orgdropdown;
	}

	public WebElement getSearchforTextfield() {
		return searchforTextfield;
	}

	public WebElement getSearchnowbtn() {
		return searchnowbtn;
	}
	
  
}
