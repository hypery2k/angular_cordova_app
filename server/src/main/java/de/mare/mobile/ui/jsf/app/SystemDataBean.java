/**
 * Cordova Angular JE22 Demo App
 *
 * File: SystemDataBean.java, 13.10.2014, 20:41:00, mreinhardt
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
package de.mare.mobile.ui.jsf.app;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides SelectItems for user selection. Should only used in request scope.
 * (items are localised to the user selected language)
 * 
 * @author mreinhardt
 * 
 */
@ManagedBean(name = "systemData")
@ApplicationScoped
public class SystemDataBean implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5959402672521985879L;

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SystemDataBean.class);

	private Date startUpTime;

	@PostConstruct
	public void init() {
		LOG.info("Application startup complete");
		this.startUpTime = new Date();
	}

	/**
	 * @return the startUpTime
	 */
	public Date getStartUpTime() {
		return startUpTime;
	}

	/**
	 * @return true if the debug mode is enabled
	 */
	public boolean isDebugEnabled() {
		return false;
	}
}
