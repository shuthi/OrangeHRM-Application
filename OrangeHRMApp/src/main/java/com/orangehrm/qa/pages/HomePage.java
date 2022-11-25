package com.orangehrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.qa.base.Base;

public class HomePage extends Base{

	@FindBy(xpath="//a[@href='/web/index.php/pim/viewPimModule']") static WebElement PIM;
	@FindBy(xpath="//a[text()='Add Employee']") static WebElement AddEmpBtn;
	@FindBy(name="firstName") static WebElement firstName;
	@FindBy(name="lastName") static WebElement lastName; 
	@FindBy(xpath="//div[contains(@class,'oxd-form-row')][1]/div[2]/div/div/div[2]/input") static WebElement empId;
	@FindBy(xpath="//button[@type='submit']") static WebElement save;
	@FindBy(xpath="//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]") 
	static WebElement fetchEmpId;
	@FindBy(xpath="//a[text()='Employee List']") static WebElement empListBtn; 
	@FindBy(xpath="//div[contains(@class,'oxd-form-row')]/div/div[2]/div/div[2]/input") static WebElement empID;
	@FindBy(xpath="//button[@type='submit']") static WebElement search;
	@FindBy(xpath="//div[@id='app']/div/div[2]/div[2]/div/div[2]/div[2]/div/span[1]") static WebElement searchResult;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public static void addEmp() {
		PIM.click();
		AddEmpBtn.click();
		firstName.sendKeys("Vinay");
		lastName.sendKeys("M R");
		empId.clear();
		empId.sendKeys("8888");
		save.click();
		
	}
	
	
	public static String searchEmp() {
		
		empListBtn.click();
		empID.sendKeys("02818888");
		search.click();
		String result = searchResult.getText();
		return result;
	}
}
