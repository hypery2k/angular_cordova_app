/**
 * Cordova Angular JE22 Demo App
 *
 * File: RsHelper.java, 04.08.2014, 15:54:54, mreinhardt
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

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

/**
 * @author mreinhardt
 *
 */
public abstract class RsTest {
	/**
	 * Logger
	 */
	protected static final Logger LOG = LoggerFactory.getLogger(RsTest.class);

	public static final URI BASE_URI = URI.create("http://localhost:10080/");

	private HttpServer threadSelector;

	@Before
	public void before() {
		try {
			threadSelector = GrizzlyServerFactory.createHttpServer(BASE_URI);
			LOG.info("HTTP-Status (running: " + threadSelector.isStarted()
			    + ") ");
		} catch (IllegalArgumentException e) {
			LOG.error(
			    "HTTP-Server couldn't be started due to argurments error",
			    e);
		} catch (NullPointerException e) {
			LOG.error(
			    "HTTP-Server couldn't be started due to NullPointer error",
			    e);
		} catch (IOException e) {
			LOG.error("HTTP-Server already running", e);
		}
	}

	@After
	public void after() {
		// closing http server after test
		threadSelector.stop();
	}
}
