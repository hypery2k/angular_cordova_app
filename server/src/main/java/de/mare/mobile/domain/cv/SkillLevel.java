/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: SkillLevel.java, 19.08.2014, 18:49:55, mreinhardt
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
package de.mare.mobile.domain.cv;

/**
 * 
 * @author mreinhardt
 *
 */
public enum SkillLevel {

	JUNIOR("junior"),
	INTERMEDIATE("intermediate"),
	SENIOR("senior");

	private static final String LOCALIZATION_KEY_PREFIX = "competency.level.";

	private final String localizationKeySuffix;

	private SkillLevel(String localizationKeySuffix) {
		this.localizationKeySuffix = localizationKeySuffix;
	}

	public String getLocalizationKey() {
		return LOCALIZATION_KEY_PREFIX + localizationKeySuffix;
	}
}
