/**
 * Cordova Angular JE22 Demo App
 *
 * File: SecurityRole.java, 01.09.2014, 20:55:39, mreinhardt
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
package de.mare.mobile.domain.enums;

/**
 * Type of security role for a User
 *
 * @author mreinhardt
 */
public enum SecurityRole {

	// @formatter:off
	ADMIN(0, false, "security.role.admin", "admin"), // admin user
	ANONYMOUS(1, true, "security.role.anonymous", "anonymous"), // anonym user
	USER(2, false, "security.role.user", "user"), // end user
	;
	// @formatter:on

	private final int id;

	private final boolean technicalRole;

	private final String resourceKey;

	private final String name;

	SecurityRole(final int pID, final boolean pTechnicalRole, final String pResourceKey,
	    final String pName) {
		this.id = pID;
		this.technicalRole = pTechnicalRole;
		this.resourceKey = pResourceKey;
		this.name = pName;
	}

	/**
	 *
	 * @return id of security role
	 */
	public int getId() {
		return this.id;
	}

	/**
	 *
	 * @return resourceKey, used for translation
	 */
	public String getResourceKey() {
		return this.resourceKey;
	}

	/**
	 *
	 * @return the name of the status
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the technicalRole
	 */
	public boolean isTechnicalRole() {
		return this.technicalRole;
	}

	/**
	 * Get the Security Role via id
	 *
	 * @param pID
	 *          desired id
	 * @return security role
	 */
	public static SecurityRole forId(final int pID) {
		for (final SecurityRole type : SecurityRole.values()) {
			if (type.getId() == pID) {
				return type;
			}
		}
		return null;
	}

	/**
	 * Get the Security Role via name
	 *
	 * @param pName
	 *          desired name
	 * @return security role
	 */
	public static SecurityRole getByName(final String pName) {
		if (pName == null) {
			return null;
		}
		final SecurityRole[] col = SecurityRole.values();
		for (final SecurityRole myEnum : col) {
			if (myEnum.getName().equalsIgnoreCase(pName)) {
				return myEnum;
			}
		}
		throw new IllegalArgumentException("No enum for the name " + pName + " exists.");
	}

}
