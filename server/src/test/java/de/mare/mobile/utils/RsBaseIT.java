/**
 * Cordova Angular JE22 Demo App
 *
 * File: RSTestUtil.java, 12.10.2014, 22:16:54, mreinhardt
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
package de.mare.mobile.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mreinhardt
 *
 */
public class RsBaseIT {
	/**
	 * Logger
	 */
	protected static final Logger LOG = LoggerFactory.getLogger(RsBaseIT.class);

	/**
	 * Specify as -DSSBaseURL=http://... on command line to overwrite settings
	 * in wsclient.properties.
	 */
	private static final String JAVA_OPT_RSWEBSERVICE_URL = "rs.base.url";

	private static final String DEFAULT_RSWEBSERVICE_URL = "http://localhost:8080";

	private static final String RS_ROOT_PATH = "/cordova-server-backend/api";

	private static Properties configuration;

	private static synchronized Properties getConfiguration() {
		if (configuration == null) {
			configuration = new Properties();
			// The webservice URL is usually configured in the
			// wsclient.properties file:
			// webservice.baseurl = http://...
			// This value can be overwritten by a Java VM parameter (system
			// property):
			final String overwrittenWSUrl = System
					.getProperty(JAVA_OPT_RSWEBSERVICE_URL);
			LOG.debug("Using overwritten webservice url:" + overwrittenWSUrl);
			System.out.println("JAVA_OPT_WEBSERVICE_URL:" + overwrittenWSUrl);
			if (overwrittenWSUrl != null
					&& overwrittenWSUrl.trim().length() > 0) {
				configuration.setProperty("webservice.baseurl",
						overwrittenWSUrl.trim());
			}

			// The test resources path is usually configured in the
			// wsclient.properties file:
			// webservice.testresources.path = ...
			// This value can be overwritten by a Java VM parameter (system
			// property):
			final String overwrittenRStestResourcesPath = System
					.getProperty(JAVA_OPT_RSWEBSERVICE_URL);
			LOG.debug("Using overwritten test resources path for REST webservices:"
					+ overwrittenRStestResourcesPath);
			if (overwrittenRStestResourcesPath != null
					&& overwrittenRStestResourcesPath.trim().length() > 0) {
				configuration.setProperty(JAVA_OPT_RSWEBSERVICE_URL,
						overwrittenRStestResourcesPath.trim());
			}
		}
		return configuration;
	}

	/**
	 * BaseURL to access the REST webservice on the server. Overwrite if
	 * necessary.
	 */
	private static String getRESTWebserviceBaseURL() {
		final String defaultValue = DEFAULT_RSWEBSERVICE_URL;
		return getConfiguration().getProperty(JAVA_OPT_RSWEBSERVICE_URL,
				defaultValue);
	}

	/**
	 * 
	 * @return
	 */
	private static String getRESTWebserviceFullURL() {
		final StringBuffer url = new StringBuffer();
		url.append(getRESTWebserviceBaseURL());
		url.append(RS_ROOT_PATH);
		return url.toString();
	}

	public static String getRESTfullURI(final String pBaseUrl) {
		final StringBuffer url = new StringBuffer();
		url.append(getRESTWebserviceFullURL());
		url.append(pBaseUrl);
		return url.toString();
	}
}
