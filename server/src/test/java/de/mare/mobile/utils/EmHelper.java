/**
 * Cordova Angular JEE Demo App
 *
 * File: EmHelper.java, 30.07.2014, 17:01:38, mreinhardt
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

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

/**
 * 
 * @author mreinhardt
 * 
 */
public abstract class EmHelper {

	/**
	 * Set entity manager via reflection
	 * 
	 * @param emAware
	 *          bean class which neeeds entity manageer
	 * @param em
	 *          entity manager to set
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void setEntityManager(final Object emAware, final EntityManager em)
	    throws Exception {
		Field field = null;
		if (emAware != null) {
			boolean continueLoop = emAware.getClass() != null;
			Class parent = emAware.getClass();
			while (continueLoop) {
				try {
					field = parent.getDeclaredField("entityManager");
					// found value, return
					continueLoop = false;
				} catch (final Exception ex) {
					// ignore and continue
					parent = parent.getSuperclass();
					continueLoop = parent != null;
				}
			}
			field.setAccessible(true);
			field.set(emAware, em);
			field.setAccessible(false);
		}

	}

	public static void execute(final Runnable r, final EntityManager em) throws Exception {
		EntityTransaction trx = null;
		try {
			trx = em.getTransaction();
			trx.begin();
			r.execute(em);
			trx.commit();
		} catch (final RollbackException e) {
		} catch (final RuntimeException e) {
			if (trx != null && trx.isActive()) {
				trx.rollback();
			}
			throw e;
		} finally {
			if (trx.isActive()) {
				trx.commit();
			}
		}
	}

	public interface Runnable {

		void execute(EntityManager em) throws Exception;
	}
}
