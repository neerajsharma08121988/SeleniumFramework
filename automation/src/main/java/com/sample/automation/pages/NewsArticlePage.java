package com.sample.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sample.automation.utils.Utils;

public class NewsArticlePage extends BasePage {
	
	private Utils utils;


	@FindBy(xpath = "//h1[contains(@class,'g-heading')]")
	private WebElement articleHeadline;
	
	@FindBy(xpath = "(//div[@data-testid='share-tools'])[2]/descendant::button[3]")
	private WebElement saveArticleButton;
	
	@FindBy(xpath = "(//button[@data-testid='user-settings-button'])[1]")
	private WebElement myAccount;
	
	@FindBy(xpath = "//p[@data-testid='saved-articles-link-description']")
	private WebElement savedArticleLink;

	public NewsArticlePage(WebDriver driver) {
		super(driver);
		utils = new Utils(driver);
	}

    
    
    public void clickOnSaveArticleButton() {
    	utils.javaScriptClick(saveArticleButton);
    }
    
    public WebElement isArticleTitlePageDisplayed() {
    	return wait.until(ExpectedConditions.visibilityOf(articleHeadline));
    } 
    
    public void navigatetoSavedArticleLink() {
    	utils.waitForElementToBeClickable(myAccount);
    	myAccount.click();
    	
    	utils.waitForElementToBeClickable(savedArticleLink);
    	savedArticleLink.click();
    }
    

}
