package com.sample.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.sample.automation.utils.Utils;

public class LoginPage extends BasePage {

    private Utils utils;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "pass")
    private WebElement passwordField;

    @FindBy(id = "send2")
    private WebElement loginButton;
    

    public LoginPage(WebDriver driver) {
        super(driver);
        utils = new Utils(driver);
    }

    public void login(String email, String password) {
        utils.waitForElementToBeVisible(emailField);
        emailField.sendKeys(email);
        utils.waitForElementToBeVisible(passwordField);
        passwordField.sendKeys(password);
        utils.waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

}
