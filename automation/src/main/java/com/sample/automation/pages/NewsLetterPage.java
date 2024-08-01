package com.sample.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sample.automation.utils.Utils;

public class NewsLetterPage  extends BasePage {

	private Utils utils;


	@FindBy(xpath = "//h1[@data-testid='newsletters:section-headline']")
	private WebElement newsLetterTitle;
	
	@FindBy(xpath = "//div[@data-testid='active-newsletter-list']/div[contains(@data-testid,'newsletter')]")
	private WebElement newsLetter;
	
	@FindBy(xpath = "//button[@aria-label='Remove newsletter']")
	private WebElement newsLetterRemoveLink;


	public NewsLetterPage(WebDriver driver) {
		super(driver);
		utils = new Utils(driver);
	}

    
	 public WebElement isNewsLetterTitleDisplayed() {
	    	return wait.until(ExpectedConditions.visibilityOf(newsLetterTitle));
	 } 
	 
	 public WebElement isNewsLetterDisplayed() {
	    	return wait.until(ExpectedConditions.visibilityOf(newsLetter));
	 } 
	 
	 public WebElement isNewsLetterRemoveButtonDisplayed() {
	    	return wait.until(ExpectedConditions.visibilityOf(newsLetterRemoveLink));
	 } 
}
