package com.sample.automation.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import com.sample.automation.utils.Utils;

public class HomePage extends BasePage {

	private Utils utils;
	
	@FindBy(xpath = "//a/span[text()='Log in']")
	private WebElement logInButton;


	@FindBy(xpath = "(//button[@data-testid='user-settings-button'])[1]")
	private WebElement myAccount;

	@FindBy(xpath = "(//li[@data-testid='nav-item-U.S.']/a)[2]")
	private WebElement usMenu;

	@FindBy(xpath = "(//h3[@id='U.S.-links-column-header']/following-sibling::ul/descendant::a[contains(@href,'weather')])[2]")
	private WebElement weatherSubMenu;
	
	@FindBy(xpath = "//p[@data-testid='newsletters-link-description']")
	private WebElement newsLetterLink;
	
	@FindBy(xpath = "//a[@href='/subscription']")
	private WebElement footerSubscribeLink;
	
	@FindBy(xpath = "//a[@href='/account']")
	private WebElement footerManageAccountLink;
	
	@FindBy(xpath = "//a[contains(@href,'/home-delivery')]")
	private WebElement footerHomeDeliveryLink;
	
	@FindBy(xpath = "//a[@href='https://www.nytimes.com/gift']")
	private WebElement footerGiftSubscriptionLink;

	public HomePage(WebDriver driver) {
		super(driver);
		utils = new Utils(driver);
	}


	public WebElement isMyAccountPageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(myAccount));
	}

	public void navigatetoUsWheatherPage() throws Throwable {
		utils.waitForElementToBeClickable(usMenu);
		Actions action = new Actions(driver);
		action.moveToElement(usMenu).build().perform();
		utils.waitForElementToBeClickable(weatherSubMenu);
		weatherSubMenu.click();
	}
	
    public void navigatetoNewsLetterPage() {
    	utils.waitForElementToBeClickable(myAccount);
    	myAccount.click();
    	
    	utils.waitForElementToBeClickable(newsLetterLink);
    	newsLetterLink.click();
    }
    
    public void verifyFooterLinks() {
    	SoftAssert softAssert = new SoftAssert();
    	utils.waitForElementToBeClickable(footerSubscribeLink);
    	softAssert.assertTrue(footerSubscribeLink.isDisplayed(),"Subscription link not displayed in footer");
    	softAssert.assertTrue(footerManageAccountLink.isDisplayed(),"footerManageAccountLink  not displayed in footer");
    	softAssert.assertTrue(footerHomeDeliveryLink.isDisplayed(),"footerHomeDeliveryLink not displayed in footer");
    	softAssert.assertTrue(footerGiftSubscriptionLink.isDisplayed(),"footerGiftSubscriptionLink link not displayed in footer");
    	softAssert.assertAll();
    }

}
