/**
 * Cordova Angular JE22 Demo App
 *
 * File: WebUIConstants.java, 13.10.2014, 17:59:44, mreinhardt
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
package de.mare.mobile.ui.util;

/**
 * Contains constants for the web app. Used by application classes and should be
 * used in the JSF config and JSF views.
 * 
 * @author mreinhardt
 * 
 */
public final class WebUIConstants {

	private WebUIConstants() {
	}

	// UI CONSTANTS

	public static final String EMPTY_TIMESTAMP = "00:00";

	public static final String USER_LOGIN_CONTEXT = "cv-login";

	public static final String SESSION_ATTRIBUTE_ERRORCODE = "errorID";

	// pages

	public static final String PAGES_URL_LOGIN = "/login.xhtml";

	public static final String PAGES_PORTAL_START = "/portal/start.xhtml";

	public static final String PAGES_ERROR = "/error.xhtml";

	// JSF navigation case

	public static final String NAV_OUTCOME_LOGIN = "login";

	public static final String NAV_OUTCOME_LOGIN_ERROR = "login.error";

	public static final String NAV_OUTCOME_LOGOUT = "logout";

}