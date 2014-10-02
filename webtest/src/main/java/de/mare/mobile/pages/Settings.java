/**
 * 
 */
package de.mare.mobile.pages;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

/**
 * @author mreinhardt
 *
 */
public class Settings extends PageObject {

	@FindBy(ngModel = "settings.username")
	private WebElement usernameInput;

	@FindBy(ngModel = "settings.password")
	private WebElement passwordInput;

	@FindBy(id = "saveSettings")
	private WebElement submitButton;

	public void inputUsername(final String pUsername) {
		element(usernameInput).waitUntilVisible();
		element(usernameInput).typeAndEnter(pUsername);
	}

	public void inputPassword(final String pPassword) {
		element(passwordInput).waitUntilVisible();
		element(passwordInput).typeAndEnter(pPassword);
	}

	public void clickOnSubmit() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}

}
