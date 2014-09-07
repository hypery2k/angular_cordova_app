/**
 * Cordova Angular JE22 Demo App
 *
 * File: RsUtil.java, 07.08.2014, 15:36:03, mreinhardt
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

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 * @author mreinhardt
 *
 */
public class RsUtil {

	public static Response getRestRessource(final String pUsername, final String pPassword,
	    final String pURL) {
		final Client client = JerseyClientBuilder.newClient();
		if (pUsername != null && pPassword != null) {
			HttpAuthenticationFeature auth = getAuthFeature(pUsername, pPassword);
			client.register(auth);
		}
		Response baseWebRes = client.target(pURL).request().get();
		return baseWebRes;
	}

	public static HttpAuthenticationFeature getAuthFeature(final String pUsername,
	    final String pPassword) {
		HttpAuthenticationFeature auth = HttpAuthenticationFeature.basicBuilder()
		    .credentials(pUsername, pPassword).build();
		return auth;
	}

}
