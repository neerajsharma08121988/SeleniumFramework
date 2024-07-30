package com.sample.automation.tests;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.automation.pages.CartPage;
import com.sample.automation.pages.CheckoutPage;
import com.sample.automation.pages.CoursePage;
import com.sample.automation.pages.HomePage;
import com.sample.automation.pages.LoginPage;
import com.sample.automation.pages.ProductsPage;
import com.sample.automation.pages.SignupPage;
import com.sample.automation.utils.ExtentReportManager;
import com.sample.automation.utils.Utils;

public class UdemyTests extends BaseTest {
	
	

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
		return Utils.readExcelData("src/test/java/com/sample/automation/resources/testdata.xlsx", 0);
	}
	
	@Test(dataProvider = "loginData")
	public void testLogin(String email, String password) {
		HomePage homePage = new HomePage(driver);
		ExtentReportManager.createTest("testLogin");
		ExtentReportManager.getTest().info("Navigating to login page");

		ExtentReportManager.getTest().info("Entering login credentials");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		ExtentReportManager.getTest().info("Verifying login success");
		Assert.assertTrue(homePage.isMyAccountPageDisplayed().isDisplayed(),"My Account not displayed");
	}


	@Test
	public void testSignup() {
		ExtentReportManager.createTest("testLogin");
		HomePage homePage = new HomePage(driver);
		Utils utils = new Utils(driver);
		homePage.navigateToCreateAccountPage();
		SignupPage signUpPage = new SignupPage(driver);

		signUpPage.createAccount(utils.generateRandomString(), utils.generateRandomString(), utils.generateRandomString()+"@gmail.com", utils.generateRandomString()+"1aA@");
		// Validate login
		ExtentReportManager.getTest().info("Verifying success msg");
		Assert.assertTrue(homePage.isSuccessmsgDisplayed().isDisplayed(),"Succes msg not displayed");
	}
	
	
	@Test(dataProvider = "loginData")
	public void testAddToCart(String email, String password) throws Throwable {
		HomePage homePage = new HomePage(driver);
		ExtentReportManager.createTest("testLogin");
		ExtentReportManager.getTest().info("Navigating to login page");

		ExtentReportManager.getTest().info("Entering login credentials");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
		
		ExtentReportManager.getTest().info("Verifying login success");
		Assert.assertTrue(homePage.isMyAccountPageDisplayed().isDisplayed(),"My Account not displayed");
		
		
		ExtentReportManager.getTest().info("select Item and Add item to cart and navigate to cart page");
		homePage.selectItemfromMenu();
		ProductsPage productPage=new ProductsPage(driver);
		productPage.selectproductAndClickonAddToCart();
		productPage.NavigateToCart();
		
		ExtentReportManager.getTest().info("Verify cart page with product item");
		CartPage cartPage=new CartPage(driver);
		Assert.assertTrue(cartPage.isCartPageDisplayed().isDisplayed(),"Cart page not displayed");
		Assert.assertTrue(cartPage.isShoppingCartTableDisplayed().isDisplayed(),"Shopping cart table not displayed, hence item not added in the cart");
		
		cartPage.clickOnDeleteCartButton();
	}
	
	@Test(dataProvider = "loginData")
	public void testVerifyProductDetails(String email, String password) throws Throwable {
		HomePage homePage = new HomePage(driver);
		ExtentReportManager.createTest("testLogin");
		ExtentReportManager.getTest().info("Navigating to login page");

		ExtentReportManager.getTest().info("Entering login credentials");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
		
		ExtentReportManager.getTest().info("Verifying login success");
		Assert.assertTrue(homePage.isMyAccountPageDisplayed().isDisplayed(),"My Account not displayed");
		
		
		ExtentReportManager.getTest().info("Add item to cart and navigate to cart page");
		homePage.selectItemfromMenu();
		
		ProductsPage productPage=new ProductsPage(driver);
		
		ExtentReportManager.getTest().info("Verify Product details on the product page");
		Assert.assertTrue(productPage.isProductNameDisplayed().isDisplayed(),"Product name not displayed");
		Assert.assertTrue(productPage.isProductPriceDisplayed().isDisplayed(),"Product price not displayed");
		Assert.assertTrue(productPage.isProductReviewDisplayed().isDisplayed(),"Product review not displayed");

	}

	

}
