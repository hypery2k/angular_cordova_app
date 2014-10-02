/**
 * 
 */
package de.mare.mobile.pages;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static ch.lambdaj.Lambda.convert;
import ch.lambdaj.function.convert.Converter;

/**
 * @author mreinhardt
 *
 */
public class Users extends PageObject {

	@FindBy(id = "userErrorMsg")
	private WebElement errorMsg;

	@FindBy(id = "userListing")
	private WebElement userListings;

	public boolean isErrorMsgVisible() {
		element(userListings).waitUntilVisible();
		boolean errMsgVisible = false;
		if (element(userListings).isVisible()) {
			errMsgVisible = true;
		}
		return errMsgVisible;
	}

	public List<String> getUsers() {
		if (!isErrorMsgVisible()) {
			List<WebElement> users = userListings.findElements(By.tagName("a"));
			return convert(users, getLinks());
		} else {
			return null;
		}
	}

	private Converter<WebElement, String> getLinks() {
		return new Converter<WebElement, String>() {
			public String convert(WebElement from) {
				return from.getAttribute("href");
			}
		};
	}
}
