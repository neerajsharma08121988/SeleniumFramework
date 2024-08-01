package com.sample.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sample.automation.utils.Utils;

public class SubMenuPage extends BasePage {

	private Utils utils;


	@FindBy(xpath = "//section[@id='collection-weather']/descendant::h1[@data-component-name='collection-header']")
	private WebElement pageTitle;
	
	@FindBy(xpath = "(//section[@id='collection-highlights-container']/descendant::li)[1]")
	private WebElement productContainer;

	public SubMenuPage(WebDriver driver) {
		super(driver);
		utils = new Utils(driver);
	}

    
    public WebElement isSubMenuPageTitleDisplayed() {
    	return wait.until(ExpectedConditions.visibilityOf(pageTitle));
    } 
    
    public void selectNews() {
    	productContainer.click();
    }

}
