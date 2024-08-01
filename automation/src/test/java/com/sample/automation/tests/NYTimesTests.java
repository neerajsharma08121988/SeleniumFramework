package com.sample.automation.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.automation.pages.HomePage;
import com.sample.automation.pages.LoginPage;
import com.sample.automation.pages.NewsArticlePage;
import com.sample.automation.pages.NewsLetterPage;
import com.sample.automation.pages.SavedArticlePage;
import com.sample.automation.pages.SubMenuPage;
import com.sample.automation.utils.Utils;

public class NYTimesTests extends BaseTest {

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
		return Utils.readExcelData("src/test/java/com/sample/automation/resources/testdata.xlsx", 0);
	}
	

	@Test(dataProvider = "loginData")
	public void testLogin(String email, String password) {
		HomePage homePage = new HomePage(driver);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		Assert.assertTrue(homePage.isMyAccountPageDisplayed().isDisplayed(), "My Account not displayed");
	}

	@Test(dataProvider = "loginData")
	public void testVerifySavedArticle(String email, String password) throws Throwable {
		HomePage homePage = new HomePage(driver);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
		homePage.navigatetoUsWheatherPage();

		SubMenuPage subMenuPage = new SubMenuPage(driver);

		Assert.assertTrue(subMenuPage.isSubMenuPageTitleDisplayed().isDisplayed(), "Sub menu page not opened");
		Assert.assertTrue(subMenuPage.isSubMenuPageTitleDisplayed().getText().contains("Weather"),
				"Sub menu page titile not matched");
		Assert.assertTrue(driver.getCurrentUrl().contains("weather"), "Sub menu page not opened");

		subMenuPage.selectNews();

		NewsArticlePage newsArticlePage = new NewsArticlePage(driver);
		Assert.assertTrue(newsArticlePage.isArticleTitlePageDisplayed().isDisplayed(),
				"selected article page not displayed");
		String articleTitle = newsArticlePage.isArticleTitlePageDisplayed().getText();
		newsArticlePage.clickOnSaveArticleButton();
		newsArticlePage.navigatetoSavedArticleLink();

		SavedArticlePage savedArticlePage = new SavedArticlePage(driver);

		Assert.assertTrue(savedArticlePage.isSavedArticleTitleDisplayed().isDisplayed(), "saved article not displayed");
		Assert.assertEquals(savedArticlePage.isSavedArticleTitleDisplayed().getText(), articleTitle,
				"saved article title not matched");
		savedArticlePage.clickOnRemoveArticleButton();

	}

	 @Test(dataProvider = "loginData")
	public void testVerifyNewsLetterPage(String email, String password) throws Throwable {
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		homePage.navigatetoNewsLetterPage();

		NewsLetterPage newsLetterPage = new NewsLetterPage(driver);
		Assert.assertTrue(newsLetterPage.isNewsLetterTitleDisplayed().getText().equals("Newsletters"),
				"New letter title not displayed");
		Assert.assertTrue(newsLetterPage.isNewsLetterDisplayed().isDisplayed(), "New letter not displayed");
		Assert.assertTrue(newsLetterPage.isNewsLetterRemoveButtonDisplayed().isDisplayed(),
				"New letter remove button not displayed");

	}

	@Test(dataProvider = "loginData")
	public void testFooterLinks(String email, String password) throws Throwable {
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		homePage.verifyFooterLinks();

	}

}
