package de.mare.mobile.pages;

import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class App extends PageObject {

	@FindBy(xpath = "//*[@id='home']/a")
	private WebElement menuItemHome;

	@FindBy(xpath = "//*[@id='users']/a")
	private WebElement menuItemUsers;

	@FindBy(xpath = "//*[@id='settings']/a")
	private WebElement menuItemSettings;

	public void clickHomeItem() {
		element(menuItemHome).waitUntilVisible();
		element(menuItemHome).click();
	}

	public void clickUsersItem() {
		element(menuItemUsers).waitUntilVisible();
		element(menuItemUsers).click();
	}

	public void clickSettingsItem() {
		element(menuItemSettings).waitUntilVisible();
		element(menuItemSettings).click();
	}

}