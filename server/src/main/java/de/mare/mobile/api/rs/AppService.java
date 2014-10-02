/**
 * Cordova Angular JE22 Demo App
 *
 * File: SimpleRS.java, 18.07.2014, 12:49:55, mreinhardt
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
package de.mare.mobile.api.rs;

import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.mare.mobile.services.ConfigRepository;

/**
 * Sample Info REST service, showing system information
 * 
 * @author mreinhardt
 * 
 */
@Path("app")
public class AppService {

	@Inject
	private ConfigRepository configRepository;

	@Path("memory")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response freeMem() {
		final long memory = Runtime.getRuntime().freeMemory();
		final Response.ResponseBuilder response = Response.status(Response.Status.OK)
		    .entity(memory);
		return response.build();
	}

	@Path("config")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppConfig() throws UnknownHostException {
		Response.ResponseBuilder response = null;
		String config = configRepository.getAppConfig().getValue();
		response = Response.status(Response.Status.OK).entity(config);
		return response.build();
	}
}
