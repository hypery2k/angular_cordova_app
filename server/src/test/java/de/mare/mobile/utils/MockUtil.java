/**
 * Cordova Angular JE22 Demo App
 *
 * File: MockUtil.java, 07.09.2014, 14:34:29, mreinhardt
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
package de.mare.mobile.utils;

import java.lang.reflect.Field;

/**
 * @author mreinhardt
 *
 */
public class MockUtil {

	/**
	 * Set field, primary for injects
	 * 
	 * @param pBean
	 *          bean which holds the field
	 * @param fieldname
	 *          name of the field
	 * @param pInject
	 *          object to set (inject)
	 */
	public static void setFieldStatic(final Object pBean, final String fieldname, final Object pInject) {
		try {

			final Field field = pBean.getClass().getDeclaredField(fieldname);
			field.setAccessible(true);
			field.set(pBean, pInject);
			field.setAccessible(false);

		} catch (final Exception e) {
			try {
				final Field field = pBean.getClass().getSuperclass().getDeclaredField(fieldname);
				field.setAccessible(true);
				field.set(pBean, pInject);
				field.setAccessible(false);
			} catch (final Exception e2) {
				throw new RuntimeException(e2);
			}
		}
	}
}
