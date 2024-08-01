package com.sample.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class Utils {

	private WebDriver driver;
	private WebDriverWait wait;

	public Utils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public void waitForElementToBeVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static Object[][] readExcelData(String filePath, int sheetIndex) throws IOException {
		FileInputStream fis = new FileInputStream(new File(filePath));
		Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(sheetIndex);
		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount - 1][sheet.getRow(0).getPhysicalNumberOfCells()];

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				data[i - 1][j] = row.getCell(j).toString();
			}
		}
		return data;
	}
	
	public String generateRandomString() {
		return RandomStringUtils.randomAlphabetic(10);
	}
	
	
	public void mouseHoverAndClick(WebElement hoverElement, WebElement targetElement) {
		Actions action = new Actions(driver);
		action.moveToElement(hoverElement).moveToElement(targetElement).click().perform();
	}
	
	public void javaScriptClick(WebElement element)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public WebElement waitForElementPresent(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	
	

}
