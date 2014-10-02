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

import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Application;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mare.mobile.domain.User;
import de.mare.mobile.domain.enums.SecurityRole;
import de.mare.mobile.services.ConfigRepository;
import de.mare.mobile.utils.EmHelper;
import de.mare.mobile.utils.MockUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * @author mreinhardt
 *
 */
public abstract class RsTest<T> extends JerseyTest {
	/**
	 * Logger
	 */
	protected static final Logger LOG = LoggerFactory.getLogger(RsTest.class);

	private User validTestuser = null;

	protected static EntityManagerFactory emf;

	protected static EntityManager em;

	@Before
	public void before() throws Exception {
		EmHelper.execute(new EmHelper.Runnable() {

			@Override
			public void execute(final EntityManager em) throws Exception {
				// create valid test user before each test
				setValidTestuser(new User.Builder().withFirstname("Max").withLastname("Mustermann")
				    .withUsername("testuser" + UUID.randomUUID().toString()).withPassword("42")
				    .withRole(SecurityRole.USER).build());
				em.persist(validTestuser);
				assertThat(validTestuser.getId(), is(greaterThan(0l)));
			}
		}, em);
	}

	@BeforeClass
	public static void setup() throws Exception {
		// Get the entity manager for the tests.
		emf = Persistence.createEntityManagerFactory("chatTestPU");
		em = emf.createEntityManager();
	}

	public abstract Class<T> getTestResource();

	@After
	public void after() {

	}

	/**
	 * @return the validTestuser
	 */
	public User getValidTestuser() {
		return validTestuser;
	}

	/**
	 * @param validTestuser
	 *          the validTestuser to set
	 */
	public void setValidTestuser(User validTestuser) {
		this.validTestuser = validTestuser;
	}

	/**
	 * Register the Resource and TestBinder in the Application
	 */

	@Override
	protected Application configure() {
		return new ResourceConfig() {
			{
				register(new TestBinder());
				register(getTestResource());
			}
		};
	}

	/**
	 * This class will help Resource to set the @Inject fields.
	 */
	public static class TestBinder extends AbstractBinder {

		@Override
		protected void configure() {
			bindFactory(ConfigRepositoryProvider.class).to(ConfigRepository.class);
		}

	}

	@RequestScoped
	public static class ConfigRepositoryProvider implements Factory<ConfigRepository> {

		/**
		 * @see org.glassfish.hk2.api.Factory#dispose(java.lang.Object)
		 */
		@Override
		public void dispose(ConfigRepository arg0) {

		}

		/**
		 * @see org.glassfish.hk2.api.Factory#provide()
		 */
		@Override
		public ConfigRepository provide() {
			try {
				ConfigRepository instance = ConfigRepository.class.newInstance();
				MockUtil.setFieldStatic(instance, "entityManager", em);
				return instance;
			} catch (Exception e) {
				return null;
			}
		}

	}
}
