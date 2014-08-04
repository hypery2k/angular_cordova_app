/**
 * Cordova Angular JE22 Demo App
 *
 * File: SimpleRSTest.java, 18.07.2014, 12:49:55, mreinhardt
 *
 * https://www.martinreinhardt-online.de/apps
 *
 * @project https://github.com/hypery2k/angular_cordova_app
 *
 * @copyright 2014 Martin Reinhardt contact@martinreinhardt-online.de
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package de.mare.mobile.ws;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

import de.mare.mobile.domain.dto.AppInfo;

/**
 * @author mreinhardt
 * 
 */
public class AppRSTest extends RsHelper {

	// Base URL
	private final WebResource baseWebRes = getRestRessource(null, null, "app");

	@Test
	public void testMemoryInfo() {
		final WebResource webRes = this.baseWebRes.path("memory");
		LOG.debug("URL for RS-Webservice: " + this.baseWebRes.toString());
		final String memInfo = webRes.get(String.class);
		assertNotNull(memInfo);
		LOG.debug("Result for memory info " + memInfo);
	}

	@Test
	public void testInfo() {
		final WebResource webRes = this.baseWebRes.path("info");
		LOG.debug("URL for RS-Webservice: " + this.baseWebRes.toString());
		final AppInfo infoDtls = webRes.get(AppInfo.class);
		assertNotNull(infoDtls);
		LOG.debug("Result for memory info " + infoDtls);

	}

}
