/**
 * Cordova Angular JE22 Demo App
 *
 * File: ConfigRepository.java, 02.10.2014, 14:01:42, mreinhardt
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
package de.mare.mobile.services;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mare.mobile.domain.ConfigParameter;
import de.mare.mobile.utils.AppConstants;

/**
 * @author mreinhardt
 *
 */
@Stateless
@PermitAll
public class ConfigRepository {
	private Logger LOG = LoggerFactory.getLogger(ConfigRepository.class);

	@Inject
	private EntityManager entityManager;

	public ConfigParameter getAppConfig() {
		final TypedQuery<ConfigParameter> query = entityManager.createNamedQuery(
		    ConfigParameter.NAMED_QUERY_FIND_BY_KEY, ConfigParameter.class);
		query
		    .setParameter(ConfigParameter.NAMED_QUERY_FIND_BY_KEY_PARAM_KEY, AppConstants.APP_CFGPARAM);
		ConfigParameter appConfig = null;
		try {
			appConfig = query.getResultList().get(0);
		} catch (final Exception e) {
			LOG.error("error during search for app config. Using default.", e);
			appConfig = new ConfigParameter();
			appConfig.setKey(AppConstants.APP_CFGPARAM);
			appConfig.setValue(AppConstants.APP_CFG_DEFAULT);
			entityManager.persist(appConfig);
		}
		return appConfig;
	}

}
