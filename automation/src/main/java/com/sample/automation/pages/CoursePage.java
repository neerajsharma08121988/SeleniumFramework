package com.sample.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.sample.automation.utils.Utils;

public class CoursePage extends BasePage {

    private Utils utils;

    @FindBy(xpath = "//button[@data-testid='add-to-cart-button']")
    private WebElement addToCartButton;
    
    @FindBy(xpath = "(//h3[@data-purpose='course-title-url'])[1]")
    private WebElement courseList;
    
    @FindBy(xpath = "//button[@data-purpose='go-to-cart-button']")
    private WebElement gotoCartButton;

    public CoursePage(WebDriver driver) {
        super(driver);
        utils = new Utils(driver);
    }

    public void addToCart() {
        utils.waitForElementToBeClickable(courseList);
        
      //Creating object of an Actions class
        Actions action = new Actions(driver);

        //Performing the mouse hover action on the course list 1st element.
        action.moveToElement(courseList).perform();
        
        utils.waitForElementToBeClickable(addToCartButton);
        
        addToCartButton.click();
        
        clickOnCartButton();
    }
    
    public void clickOnCartButton() {
    	utils.waitForElementToBeClickable(gotoCartButton);
    	gotoCartButton.click();
    }
    
    public String getCourseName() {
    	utils.waitForElementToBeClickable(courseList);
    	return courseList.getText();
    }
    

}
