/**
 * Cordova Angular JE22 Demo App
 *
 * File: AppConstants.java, 18.07.2014, 12:49:55, mreinhardt
 *
 * https://www.martinreinhardt-online.de/apps
 *
 * @project https://github.com/hypery2k/angular_cordova_app
 *
 * @copyright 2014 Martin Reinhardt contact@martinreinhardt-online.de
 *
 *            Permission is hereby granted, free of charge, to any person obtaining a copy
 *            of this software and associated documentation files (the "Software"), to deal
 *            in the Software without restriction, including without limitation the rights
 *            to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *            copies of the Software, and to permit persons to whom the Software is
 *            furnished to do so, subject to the following conditions:
 * 
 *            The above copyright notice and this permission notice shall be included in all
 *            copies or substantial portions of the Software.
 * 
 *            THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *            IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *            FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *            AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *            LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *            OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *            SOFTWARE.
 *
 */
package de.mare.mobile.utils;

import javax.annotation.security.DeclareRoles;

/**
 * @author mreinhardt
 *
 */
@DeclareRoles({ "user" })
public class AppConstants {

  public final static String PU_NAME = "chatPU";

  // key to app config
  public final static String APP_CFGPARAM = "appConfig";

  // default config
  public final static String APP_CFG_DEFAULT = "{\"rsBackend\":\"http://localhost:8080/cordova-server-backend/api\",\"wsBackend\":\"ws://localhost:8080/cordova-server-backend/message\"}";

  public static final String REDIRECT_AFTER_SUCCESSFUL_LOGIN = "portal.start";
}
