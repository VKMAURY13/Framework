package com.comcast.CRM.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	WebDriver driver;
	
	public OrganizationInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgnametext;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerText;

	public WebElement getOrgnametext() {
		return orgnametext;
	}

	public WebElement getHeaderText() {
		return headerText;
	}
	

}
