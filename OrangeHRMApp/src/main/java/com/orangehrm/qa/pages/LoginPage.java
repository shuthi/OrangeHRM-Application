package com.orangehrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.orangehrm.qa.base.Base;

public class LoginPage extends Base{
	
	@FindBy(name="username")
	static WebElement userName;
	@FindBy(name="password") static WebElement password;
	@FindBy(xpath="//button[@type='submit']") static WebElement submit;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public static String validateTitle() {
		return driver.getTitle();
	}
	public static void login(String un, String pwd) {
		
		userName.sendKeys(un);
		password.sendKeys(pwd);
		submit.click();		
	}

}
