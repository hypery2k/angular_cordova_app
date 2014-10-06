/**
 * 
 */
package de.mare.mobile.tests;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

import de.mare.mobile.steps.AppSteps;

/**
 * @author mreinhardt
 *
 */
@RunWith(ThucydidesRunner.class)
public class UsersIT {

	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@ManagedPages
	public Pages pages;

	@Steps
	public AppSteps appSteps;

	public final static String LS_APP_SETTINGS_KEY = "ls.ngJEE_app.settings";
	public final static String LS_APP_SETTINGS_VALUE = "{\"username\":\"user1\",\"password\":\"user1\"}";

	@Test
	public void userListingWithoutErrors() {
		setupLS();
		appSteps.goToUserListing();
		appSteps.should_see_user_listings();
	}

	@Test
	public void shouldShowAllUsers() {
		setupLS();
		appSteps.goToUserListing();
		appSteps.should_show_correct_user_count(3);
	}

	public void setupLS() {
		try {
			appSteps.goToSettings();
			JavascriptExecutor js = (JavascriptExecutor) webdriver;
			js.executeScript("window.localStorage.clear()");
			js.executeScript(String.format(
			    "window.localStorage.setItem('%s','%s');", LS_APP_SETTINGS_KEY, LS_APP_SETTINGS_VALUE));
			webdriver.navigate().refresh();
			appSteps.goToSettings();
			appSteps.saveSettings();
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

}
