package com.sample.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sample.automation.utils.Utils;

public class CheckoutPage extends BasePage {

    private Utils utils;

    @FindBy(xpath = "//main[contains(@class,'marketplace-checkout--checkout-overview')]")
    private WebElement checkoutLabel;
    

    public CheckoutPage(WebDriver driver) {
        super(driver);
        utils = new Utils(driver);
    }
    
    public WebElement isCheckoutPageDisplayed() {
    	return wait.until(ExpectedConditions.elementToBeClickable(checkoutLabel));
    }

}
