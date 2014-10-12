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

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mare.mobile.domain.dto.UserDTO;
import de.mare.mobile.utils.RsBaseIT;
import de.mare.mobile.utils.RsUtil;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * @author mreinhardt
 * 
 */
public class UserRSIT extends RsBaseIT {
	/**
	 * Logger
	 */
	protected static final Logger LOG = LoggerFactory.getLogger(UserRSIT.class);

	@Test
	public void shouldListAllUsers() {
		final Response webRes = RsUtil.getRestRessource("user1", "user1", getRESTfullURI("/user/all"));
		final List<UserDTO> usersInfo = webRes.readEntity(new GenericType<List<UserDTO>>() {});
		assertThat(webRes.getStatus(), is(200));
		assertThat(usersInfo, notNullValue());
		LOG.debug("Result for user listing " + usersInfo);
	}

}
