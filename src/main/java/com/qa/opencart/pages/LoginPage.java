package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By Locators -- page locators

	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	private By registerLink = By.linkText("Register");

	// 2. Public Page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// 3. Public Page Actions/Methods
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContains(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login page title is : " + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login page url is : " + url);
		return url;
	}

	public Boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();

	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("App credentials are :" + username + ":" + pwd);
		eleUtil.waitForElementVisible(email, AppConstants.MEDIUM_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}

	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink,AppConstants.SHORT_TIME_OUT).click();
		return new RegisterPage(driver);
	}

}
