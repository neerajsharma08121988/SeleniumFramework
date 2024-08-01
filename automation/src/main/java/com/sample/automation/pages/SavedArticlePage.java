package com.sample.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sample.automation.utils.Utils;

public class SavedArticlePage extends BasePage {

	private Utils utils;


	@FindBy(xpath = "//article/a/h3")
	private WebElement savedArticleTitle;
	
	@FindBy(xpath = "//a[contains(@aria-label,'Remove')]")
	private WebElement removeArticleLink;


	public SavedArticlePage(WebDriver driver) {
		super(driver);
		utils = new Utils(driver);
	}

    
	 public WebElement isSavedArticleTitleDisplayed() {
	    	return wait.until(ExpectedConditions.visibilityOf(savedArticleTitle));
	    } 
    
    public void clickOnRemoveArticleButton() {
    	removeArticleLink.click();
    }
}
