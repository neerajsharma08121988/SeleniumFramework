package com.sample.automation.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sample.automation.utils.Utils;

public class SignupPage extends BasePage  {

	 private Utils utils;

	 @FindBy(xpath = "//input[@id='firstname']")
		WebElement firstName;

		@FindBy(xpath = "//input[@id='lastname']")
		WebElement lastName;

		@FindBy(xpath = "//input[@id='email_address']")
		WebElement emailAddress;

		@FindBy(xpath = "//input[@id='password']")
		WebElement password;

		@FindBy(xpath = "//input[@id='password-confirmation']")
		WebElement confirmPassword;

		@FindBy(xpath = "//button//span[contains(text(),'Create an Account')]")
		WebElement createAnAccount;

		@FindBy(xpath = "//span[@data-ui-id='page-title-wrapper']")
		WebElement createNewCustomerTitle;

		@FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
		WebElement accountCreationMessage;

	    public SignupPage(WebDriver driver) {
	        super(driver);
	        utils = new Utils(driver);
	    }

	    public void createAccount(String frstName, String lstName, String email, String pwd) {
	    	utils.waitForElementToBeVisible(firstName);
	    	firstName.sendKeys(frstName);
	    	utils.waitForElementToBeClickable(lastName);
	    	lastName.sendKeys(lstName);
	    	utils.waitForElementToBeVisible(emailAddress);
	    	emailAddress.sendKeys(email);
	    	utils.waitForElementToBeVisible(password);
	    	password.sendKeys(pwd);
	    	utils.waitForElementToBeVisible(confirmPassword);
	    	confirmPassword.sendKeys(pwd);
	    	createAnAccount.click();
	       
	    }
}
