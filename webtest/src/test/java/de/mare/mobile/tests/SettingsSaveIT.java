/**
 * 
 */
package de.mare.mobile.tests;

import net.thucydides.core.annotations.Issues;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import de.mare.mobile.steps.AppSteps;

/**
 * @author mreinhardt
 *
 */
@RunWith(ThucydidesRunner.class)
public class SettingsSaveIT {

	@Managed
	public WebDriver webdriver;

	@ManagedPages
	public Pages pages;

	@Steps
	public AppSteps appSteps;

	@Test
	@Issues({ "#5" })
	public void showSettings() {
		appSteps.goToSettings();
	}

	@Test
	@Issues({ "#5", "#6" })
	public void userListingWithoutErrors() {
		appSteps.goToSettings();
		appSteps.saveNewSettings("user1", "user1");
		appSteps.goToUserListing();
		appSteps.should_see_user_listings();
	}

	@Test
	@Issues({ "#5", "#6" })
	public void shouldShowAllUsers() {
		appSteps.goToSettings();
		appSteps.saveNewSettings("user1", "user1");
		appSteps.goToUserListing();
		appSteps.should_show_correct_user_count(2);
	}

}
