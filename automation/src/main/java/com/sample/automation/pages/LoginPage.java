package com.sample.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.sample.automation.utils.Utils;

public class LoginPage extends BasePage {

    private Utils utils;
    
    @FindBy(xpath = "//span[text()='Log in']")
    private WebElement logiinButton;

    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(xpath = "//button[@data-testid='submit-email']")
    private WebElement continueButton;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-testid='login-button']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//a[@data-testid='continue-button']")
    private WebElement continueWithoutSubscribe;
    

    public LoginPage(WebDriver driver) {
        super(driver);
        utils = new Utils(driver);
    }

    public void login(String email, String password) {
    	 utils.waitForElementToBeVisible(logiinButton);
    	 logiinButton.click();
        utils.waitForElementToBeVisible(emailField);
        emailField.sendKeys(email);
        utils.waitForElementToBeVisible(continueButton);
        continueButton.click();
        utils.waitForElementToBeVisible(passwordField);
        passwordField.sendKeys(password);
        utils.waitForElementToBeClickable(loginButton);
        loginButton.click();
        utils.waitForElementToBeVisible(continueWithoutSubscribe);
        continueWithoutSubscribe.click();
    }

}
