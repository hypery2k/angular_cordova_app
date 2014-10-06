/**
 * 
 */
package de.mare.mobile.util;

import net.thucydides.core.webdriver.DriverSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Local webdriver to debug UI tests without CORS errors <br>
 * <br>
 * Add <b>-Dwebdriver.chrome.driver=/opt/dev/chromedriver</b> as Parameter
 * 
 * @author mreinhardt
 *
 */
public class MyChromeDriver extends ChromeDriver implements DriverSource {
	@Override
	public WebDriver newDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-web-security");
		return new ChromeDriver(ChromeDriverService.createDefaultService(), options);
	}

	/**
	 * @see net.thucydides.core.webdriver.DriverSource#takesScreenshots()
	 */
	@Override
	public boolean takesScreenshots() {
		return true;
	}
}
