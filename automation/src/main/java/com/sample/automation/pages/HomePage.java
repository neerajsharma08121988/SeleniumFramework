package com.sample.automation.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sample.automation.utils.Utils;

public class HomePage extends BasePage {

	    private Utils utils;


	    @FindBy(xpath = "//*[contains(text(),'Thank you for registering with Main Website Store.')]")
	    private WebElement successmsg;
	    
	    @FindBy(xpath = "(//a/span[contains(text(),'Create an Account')])[1]")
	    private WebElement createAccount;
	    
	    @FindBy(xpath = "//span[text()='My Account']")
	    private WebElement myAccount;
	    
	    @FindBy(xpath = "//span[text()='Gear']")
	    private WebElement gearMenu;
	    
	    @FindBy(xpath = "//span[text()='Bags']")
	    private WebElement bags;
	    
	    

	    public HomePage(WebDriver driver) {
	        super(driver);
	        utils = new Utils(driver);
	    }
	    
	    public void navigateToCreateAccountPage() {
	    	utils.waitForElementToBeClickable(createAccount);
	    	createAccount.click();
	    }
	    
	    public WebElement isMyAccountPageDisplayed() {
	    	return wait.until(ExpectedConditions.visibilityOf(myAccount));
	    }
	    
	    public WebElement isSuccessmsgDisplayed() {
	    	return wait.until(ExpectedConditions.visibilityOf(successmsg));
	    }
	    
	            
	    public void selectItemfromMenu() throws Throwable {

	    	Actions action=new Actions(driver);
	    	action.moveToElement(gearMenu).moveToElement(bags).click().perform();    	

		}
	    


}
