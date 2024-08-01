package com.sample.automation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.ITestResult;

import com.sample.automation.utils.ExtentReportManager;
import com.sample.automation.utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {
		ExtentReportManager.setupReport();
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

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (driver != null) {
			driver.quit();
		}

		// Log the test result
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentReportManager.getTest().fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentReportManager.getTest().pass("Test passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentReportManager.getTest().skip("Test skipped");
		}

	}

	@AfterSuite
	public void afterSuite() {
		ExtentReportManager.flushReport(); // Generate the report
	}
}
