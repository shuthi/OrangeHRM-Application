package com.orangehrm.qa.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.orangehrm.qa.base.Base;
import com.orangehrm.qa.pages.HomePage;
import com.orangehrm.qa.pages.LoginPage;

public class validatePIM extends Base{
	
	LoginPage login;
	HomePage home;
	public validatePIM() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		startTest("validatePIM scenario: ");	
		hardPause(2);
		initialization();	
		
		login = new LoginPage();
		home = new HomePage();
	}
	
	
	@Test
	public void validateAddEmployee() {
		
		navigateURL(prop.getProperty("url"));
		implicitlyWait(60);
		
		login.login(prop.getProperty("username"), prop.getProperty("password"));
		takeScreenShot();
		logReport(Status.INFO, "Logged in successfully");
		implicitlyWait(20);
		
		home.addEmp();
		takeScreenShot();
		logReport(Status.INFO, "Added employee successfully");		
		implicitlyWait(40);

		String result = HomePage.searchEmp();
		takeScreenShot();
		logReport(Status.INFO, "Searching employee is completed: ");
		
		implicitlyWait(20);		
		logReport(Status.INFO, result);
		
		if(result.equals("No Records Found")) {
			logReport(Status.INFO, "Emp id is not present");
		}
		else if (result.contains("Records Found") || result.contains("Record Found")) {
			logReport(Status.INFO, "Emp id is present");
		}
		else {
			logReport(Status.INFO, "search results are not displayed");
		}				
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException {
		endTest(result);
		driver.quit();
	}
}
