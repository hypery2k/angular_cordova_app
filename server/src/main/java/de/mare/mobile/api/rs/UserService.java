/**
 * Cordova Angular JE22 Demo App
 *
 * File: UserService.java, 18.07.2014, 12:49:55, mreinhardt
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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.mare.mobile.domain.User;
import de.mare.mobile.domain.dto.UserDTO;
import de.mare.mobile.services.UserRepository;

/**
 * Simple user service
 * 
 * @author mreinhardt
 * 
 */
@Path("user")
@ManagedBean
public class UserService {

	@Inject
	private UserRepository userRepository;

	@POST
	@Consumes("application/json")
	@Path("add")
	public Response addUser(final UserDTO pUser) {
		userRepository.addUser(pUser.getUser());
		return Response.status(Status.OK).build();
	}

	@GET
	@Produces("application/json")
	@Path("test")
	public Response test() {
		return Response.status(Status.OK).entity("test").build();
	}

	@GET
	@Produces("application/json")
	@Path("all")
	public Response all() {
		final List<User> users = userRepository.getAllUsers();
		final List<UserDTO> result = new ArrayList<UserDTO>();
		for (User user : users) {
			result.add(new UserDTO(user));
		}
		return Response.status(Status.OK).entity(result).build();
	}

	/**
	 * @return the userRepository
	 */
	public UserRepository getUserRepository() {
		return userRepository;
	}

	/**
	 * @param userRepository
	 *          the userRepository to set
	 */
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
