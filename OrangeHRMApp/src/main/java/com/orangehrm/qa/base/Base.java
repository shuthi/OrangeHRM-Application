package com.orangehrm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.orangehrm.qa.utils.TestUtil;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static String reportPath = null;
	public static ExtentReports extent = null;
	static ExtentTest test = null;


	public Base() {
		try {
			FileInputStream fis = new FileInputStream("E:\\Softwares\\Java_projects\\OrangeHRMApp\\src\\main\\resources\\Config\\config.properties");
			prop = new Properties();
			prop.load(fis);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void initialization() {

		String browser = prop.getProperty("browser");
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\softwares\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME_OUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITLY_WAIT, TimeUnit.SECONDS);
	}

	public static void navigateURL(String url) {
		driver.get(url);
		logReport(Status.INFO, "Navigated to the URL - "+url);
	}
	
	public static void implicitlyWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}
	
	public static void hardPause(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public void generateReport() {
		reportPath = System.getProperty("user.dir")+"/Reports/ExecutionReport_"+
				DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now());
		new File(reportPath).mkdir();
		new File(reportPath + "/screenshots").mkdir();
		new File(reportPath + "/files").mkdir();

		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath+"/Report.html");
		extent.attachReporter(spark);
	}
	
	public static void startTest(String testCaseName) {
		test = extent.createTest(testCaseName);
		extentTest.set(test);
	}
	
	public static void endTest(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			logReport(Status.FAIL, result.getThrowable());
		}
		
		extent.flush();
	}

	public static void logStatus(Status status, Object message) {
		if(status == status.PASS || status == status.INFO) {
			Logger.getLogger(Logger.class).info(message);
		}else {
			Logger.getLogger(Logger.class).error(message);
		}
	}

	public static void logReport(Status status, Markup markup) {		
		extentTest.get().log(status, markup);
		logStatus(status, markup);
	}

	public static void logReport(Status status, String message) {		
		extentTest.get().log(status, message);
		logStatus(status, message);
	}

	public static void logReport(Status status, Throwable t) {		
		extentTest.get().log(status, t);
		logStatus(status, t);
	}
	
	public static String takeScreenShot() {
		String screenShotName = UUID.randomUUID().toString();
		
		String path = reportPath + "/screenshots/"+screenShotName +".jpeg";
		String retPath = "/screenshots/"+screenShotName +".jpeg";
		
		try {
			File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileHandler.copy(f, new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch(Exception e) {
			test.log(Status.SKIP, "Cannot take screenshot: "+e);
		}	
		return retPath;
	}
	
	
	
	
	
}
