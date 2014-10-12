package de.mare.mobile.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.WebElement;

public class App extends PageObject {

	@FindBy(id = "navbar")
	private WebElement navBar;

	@FindBy(xpath = "//*[@id='home']/a")
	private WebElement menuItemHome;

	@FindBy(xpath = "//*[@id='users']/a")
	private WebElement menuItemUsers;

	@FindBy(xpath = "//*[@id='settings']/a")
	private WebElement menuItemSettings;

	public void openNavBarIfNeeded() {
		if (element(navBar).isVisible()) {
			element(menuItemHome).click();
		}
	}

	public void clickHomeItem() {
		openNavBarIfNeeded();
		element(menuItemHome).waitUntilVisible();
		element(menuItemHome).click();
	}

	public void clickUsersItem() {
		openNavBarIfNeeded();
		element(menuItemUsers).waitUntilVisible();
		element(menuItemUsers).click();
	}

	public void clickSettingsItem() {
		openNavBarIfNeeded();
		element(menuItemSettings).waitUntilVisible();
		element(menuItemSettings).click();
	}

}