/**
 * Cordova Angular JE22 Demo App
 *
 * File: AppInfo.java, 04.08.2014, 16:07:28, mreinhardt
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
package de.mare.mobile.domain.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mreinhardt
 *
 */
@XmlRootElement
public class AppConfig {

	private String rsUrl;

	private String wsUrl;

	private boolean reloadConfig = false;

	private String freeMemory;

	public AppConfig() {

	}

	public AppConfig(final String rsUrl, final String wsUrl, final boolean reloadConfig,
	    final String freeMemory) {
		super();
		this.rsUrl = rsUrl;
		this.wsUrl = wsUrl;
		this.reloadConfig = reloadConfig;
		this.freeMemory = freeMemory;
	}

	/**
	 * @return the rsUrl
	 */
	public String getRsUrl() {
		return rsUrl;
	}

	/**
	 * @param rsUrl
	 *          the rsUrl to set
	 */
	public void setRsUrl(String rsUrl) {
		this.rsUrl = rsUrl;
	}

	/**
	 * @return the wsUrl
	 */
	public String getWsUrl() {
		return wsUrl;
	}

	/**
	 * @param wsUrl
	 *          the wsUrl to set
	 */
	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	/**
	 * @return the reloadConfig
	 */
	public boolean isReloadConfig() {
		return reloadConfig;
	}

	/**
	 * @param reloadConfig
	 *          the reloadConfig to set
	 */
	public void setReloadConfig(boolean reloadConfig) {
		this.reloadConfig = reloadConfig;
	}

	/**
	 * @return the freeMemory
	 */
	public String getFreeMemory() {
		return freeMemory;
	}

	/**
	 * @param freeMemory
	 *          the freeMemory to set
	 */
	public void setFreeMemory(String freeMemory) {
		this.freeMemory = freeMemory;
	}

}
