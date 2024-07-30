package com.sample.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sample.automation.utils.Utils;

public class CartPage extends BasePage {

    private Utils utils;


    @FindBy(xpath = "//h1/span[contains(text(),'Shopping Cart')]")
    private WebElement cartpageTitle;
    
    @FindBy(xpath = "//*[@id='shopping-cart-table']")
    private WebElement shoppingCartTable;
    
    
    @FindBy(xpath = "//a[@class='action action-delete']")
    private WebElement deleteCartButton;
    
    
    
    public CartPage(WebDriver driver) {
        super(driver);
        utils = new Utils(driver);
    }
    
    
    public WebElement isCartPageDisplayed() {
    	return wait.until(ExpectedConditions.visibilityOf(cartpageTitle));
    }
    
    
    
    public WebElement isShoppingCartTableDisplayed() {
    	return wait.until(ExpectedConditions.visibilityOf(shoppingCartTable));
    }
    
    
    public void clickOnDeleteCartButton() {
    	deleteCartButton.click();
    }
    
    


}
