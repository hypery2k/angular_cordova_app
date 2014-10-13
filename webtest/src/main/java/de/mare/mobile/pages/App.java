package de.mare.mobile.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.WebElement;

public class App extends PageObject {

	@FindBy(id = "navbar")
	private WebElement navBarButton;

	@FindBy(id = "mainSidebar")
	private WebElement navBar;

	@FindBy(xpath = "//*[@id='home']/a")
	private WebElement menuItemHome;

	@FindBy(xpath = "//*[@id='users']/a")
	private WebElement menuItemUsers;

	@FindBy(xpath = "//*[@id='settings']/a")
	private WebElement menuItemSettings;

	public void openNavBarIfNeeded() {
		try {
			element(navBarButton).click();
			element(navBar).waitUntilVisible();
			element(navBar).waitUntilEnabled();
		} catch (Exception e) {
		}
	}

	public void waitForNavBarClosedIfNeeded() {
		try {
			element(navBar).waitUntilNotVisible();
			element(navBar).waitUntilDisabled();
		} catch (Exception e) {
		}
	}

	public void clickHomeItem() {
		element(menuItemHome).waitUntilVisible();
		element(menuItemHome).waitUntilEnabled();
		element(menuItemHome).click();
	}

	public void clickUsersItem() {
		element(menuItemUsers).waitUntilVisible();
		element(menuItemUsers).waitUntilEnabled();
		element(menuItemUsers).click();
	}

	public void clickSettingsItem() {
		element(menuItemSettings).waitUntilVisible();
		element(menuItemSettings).waitUntilEnabled();
		element(menuItemSettings).click();
	}

}