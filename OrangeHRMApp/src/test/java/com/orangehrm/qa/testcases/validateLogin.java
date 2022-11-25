package com.orangehrm.qa.testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.Base;
import com.orangehrm.qa.pages.LoginPage;

public class validateLogin extends Base{

	LoginPage login;
	public validateLogin() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		startTest("Login scenario: ");	
		hardPause(2);
		initialization();
		 login = new LoginPage();
	}
	
	@Test
	public void validateLoginPageTitle() {
		navigateURL(prop.getProperty("url"));
		hardPause(6);
		String title = login.validateTitle();
		Assert.assertEquals(title, "OrangeHRM");
	}
	
	@Test
	public void validateloginPageLogin() {
		navigateURL(prop.getProperty("url"));
		hardPause(5);
		login.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException {
		endTest(result);
		Thread.sleep(6000);
		driver.quit();
	}
}
