/**
 * Cordova Angular JE22 Demo App
 *
 * File: ToggleConfiguration.java, 14.10.2014, 06:53:30, mreinhardt
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
package de.mare.mobile.config;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.user.UserProvider;
import org.togglz.servlet.user.ServletUserProvider;

/**
 * @author mreinhardt
 *
 */
@ApplicationScoped
public class ToggleConfiguration  implements TogglzConfig {
	
    @Resource(mappedName = "java:/jdbc/ChatDS")
    private DataSource dataSource;

	/**
	 * @see org.togglz.core.manager.TogglzConfig#getFeatureClass()
	 */
	@Override
	public Class<? extends Feature> getFeatureClass() {
		return AppFeatures.class;
	}

	/**
	 * @see org.togglz.core.manager.TogglzConfig#getStateRepository()
	 */
	@Override
	public StateRepository getStateRepository() {
        return new JDBCStateRepository(dataSource);
	}

	/**
	 * @see org.togglz.core.manager.TogglzConfig#getUserProvider()
	 */
	@Override
	public UserProvider getUserProvider() {
        return new ServletUserProvider("admin");
	}

}
