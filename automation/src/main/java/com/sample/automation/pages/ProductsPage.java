package com.sample.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sample.automation.utils.Utils;

public class ProductsPage extends BasePage {

	private Utils utils;

	@FindBy(xpath = "(//span[@class='product-image-container'])[1]")
	private WebElement product;

	@FindBy(xpath = "(//button[@title='Add to Cart'])[1]")
	private WebElement addToCart;

	@FindBy(xpath = "//a[@class='action showcart']")
	private WebElement cartIcon;

	@FindBy(xpath = "//span[contains(@data-bind,'View and Edit Cart')]")
	private WebElement viewAndEditCartLink;

	@FindBy(xpath = "//*[@class='product name product-item-name']")
	private List<WebElement> productName;

	@FindBy(xpath = "//*[@class='rating-result']")
	private List<WebElement> productReview;

	@FindBy(xpath = "//*[contains(@id,'product-price')]")
	private List<WebElement> productPrice;

	public ProductsPage(WebDriver driver) {
		super(driver);
		utils = new Utils(driver);
	}

	public void selectproductAndClickonAddToCart() {
		Actions action = new Actions(driver);
		action.moveToElement(product).moveToElement(addToCart).click().perform();
	}

	public void NavigateToCart() {
		utils.waitForElementToBeClickable(addToCart);
		cartIcon.click();

		utils.waitForElementToBeClickable(viewAndEditCartLink);
		viewAndEditCartLink.click();
	}

    public WebElement isProductNameDisplayed() {
    	return wait.until(ExpectedConditions.visibilityOf(productName.get(0)));
    } 
    
    public WebElement isProductReviewDisplayed() {
    	return wait.until(ExpectedConditions.visibilityOf(productReview.get(0)));
    } 
    
    public WebElement isProductPriceDisplayed() {
    	return wait.until(ExpectedConditions.visibilityOf(productPrice.get(0)));
    } 

}
