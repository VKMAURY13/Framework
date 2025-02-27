package com.comcast.CRM.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name="lastname")
	private WebElement lastnametextfiled;
	
	public WebElement getLastNameTextField()
	{
		return lastnametextfiled;
	}
	
	@FindBy(name="support_start_date")
	private WebElement startdate;
	
	public WebElement getStartDate()
	{
		return startdate;
	}
	
	@FindBy(name="support_end_date")
	private WebElement enddate;
	
	public WebElement getEndDate()
	{
		return enddate;
	}
	
	@FindBy(xpath = "//input[@name='account_name']/..//img[@title='Select']")
	private WebElement orglookupicon;
	
	public WebElement getOrgLookupIcon()
	{
		return orglookupicon;
	}
	
	//SAVE BUTTON
	@FindAll({@FindBy(xpath = "//input[@class='crmButton small save']"), @FindBy(xpath = "//input[@class='crmbutton small save']")})
	private WebElement savebtn;
	
	public WebElement getSavebtn() {
		return savebtn;
	}
	
	//New Window Related Element
	@FindBy(id="search_txt")
	private WebElement searchorgnametextfield;
	
	public WebElement getSearchOrgText()
	{
		return searchorgnametextfield;
	}
	
	@FindBy(name="search_field")
	private WebElement selecttextdropdown;
	
	public WebElement getSelectTextDropdown()
	{
		return selecttextdropdown;
	}
	
	@FindBy(name="search")
	private WebElement searchnowbtn;
	
	public WebElement getSearchNowBtn()
	{
		return searchnowbtn;
	}
	

}
