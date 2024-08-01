package com.sample.automation.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	public static ExtentReports extent;
	public ExtentTest logger;
	
	
	@BeforeSuite
	public void beforeEachTest() {
		 getInstance();
	}

	
	public ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(System.getProperty("user.dir") +"/target/ExtentReports/index.html", true);
			extent.loadConfig(new File(System.getProperty("user.dir") + "//extent-config.xml"));
		}
		return extent;
	}
	
	public void log(String data) {
		Reporter.log(data);
		logger.log(LogStatus.INFO, data);
	}
	
	public void StartTesting(String TestName) {
		ExtentReports rep = getInstance();
		logger = rep.startTest(TestName);
	}
	

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser, Method method) {
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser: " + browser);
		}
		driver.manage().window().maximize();
		driver.get("https://www.nytimes.com/international/");
		StartTesting(method.getName());

	}
	
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		
		
		if (logger==null){
			logger=extent.startTest("Starting Test");
		}			 		
		if (result.getStatus() == ITestResult.FAILURE) {		
			logger.log(LogStatus.FAIL, "Test Case " + result.getName() + " is Failed");
			String screenshot_path = getScreenShot(result.getName());
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "Title verification", image);
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case " + result.getName() + " is Skipped");
			String screenshot_path = getScreenShot(result.getName());
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.SKIP, "Title verification", image);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case " + result.getName() + " is passed");
			String screenshot_path = getScreenShot(result.getName());
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.PASS, "Title verification", image);
		}
		extent.endTest(logger);
		driver.quit();
	}
	
	public String getScreenShot(String fileName) throws IOException {
		String pathScreenShotsFolder = System.getProperty("user.dir");

		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(pathScreenShotsFolder + "/target/ExtentReports/Screenshots/" + fileName + ".jpg"));
			fileName = pathScreenShotsFolder + "/" + fileName + ".jpg";
			return fileName;
		} catch (Exception e) {
		}
		return fileName;
	}
	
    @AfterTest
    public void afterTest() {
    	extent.flush();
    	extent.close();
    }
}
