/**
 * 
 */
package de.mare.mobile.pages;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ch.lambdaj.function.convert.Converter;
import static ch.lambdaj.Lambda.convert;

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
		boolean errMsgVisible = false;
		try {
			element(errorMsg).waitUntilVisible();
			if (element(errorMsg).isVisible()) {
				errMsgVisible = true;
			}
		} catch (Exception e) {
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
