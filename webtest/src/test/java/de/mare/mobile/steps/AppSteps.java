/**
 * 
 */
package de.mare.mobile.steps;

import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import de.mare.mobile.pages.App;
import de.mare.mobile.pages.Settings;
import de.mare.mobile.pages.Users;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * @author mreinhardt
 *
 */
public class AppSteps extends ScenarioSteps {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7256523275730288075L;

	public AppSteps(Pages pages) {
		super(pages);
	}

	public App app() {
		return getPages().currentPageAt(App.class);
	}

	public Settings settings() {
		return getPages().currentPageAt(Settings.class);
	}

	public Users users() {
		return getPages().currentPageAt(Users.class);
	}

	@Step
	public void goToSettings() {
		app().clickSettingsItem();
	}

	@Step
	public void goToUserListing() {
		app().clickUsersItem();
	}

	@Step
	public void inputLogin(final String pUsername) {
		settings().inputUsername(pUsername);
	}

	@Step
	public void inputPassword(final String pPassword) {
		settings().inputPassword(pPassword);
	}

	@Step
	public void saveSettings() {
		settings().clickOnSubmit();
	}

	@StepGroup
	public void saveNewSettings(final String pUsername, final String pPassword) {
		inputLogin(pUsername);
		inputPassword(pPassword);
		saveSettings();
	}

	@Issue("#6")
	@Step
	public void should_see_user_listings() {
		assertThat("Error message should not be visible", !users().isErrorMsgVisible());
	}

	@Issue("#6")
	@Step
	public void should_show_correct_user_count(final int pUserCount) {
		assertThat(users().getUsers(), hasSize(pUserCount));
	}
}
