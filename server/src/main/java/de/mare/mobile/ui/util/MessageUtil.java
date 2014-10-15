/**
 * Cordova Angular JE22 Demo App
 *
 * File: MessageUtil.java, 13.10.2014, 18:00:17, mreinhardt
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
package de.mare.mobile.ui.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for generating FacesMessages and retrieving localized Strings
 * from a ResourceBundle.
 * 
 * @author mreinhardt
 * 
 */
public class MessageUtil {

	/** Write logging messages for this class. */
	private static final Logger LOG = LoggerFactory.getLogger(MessageUtil.class);

	private static final Object[] EMPTY_MESSAGE_FORMAT_PARAMS = new Object[0];

	/**
	 * HELPER TO INDICATE MISSING ressource bundle entries
	 */
	public static final String MISSING_RESOURCE_BUNDLE_ENTRY_PREFIX = "!! ";

	/**
	 * HELPER TO INDICATE MISSING ressource bundle entries
	 */

	public static final String MISSING_RESOURCE_BUNDLE_ENTRY_POSTFIX = " !!";

	/** Private Constructor for purely static helper class. */
	private MessageUtil() {
	}

	/**
	 * Prints out the given date to a readable locale aware String with a
	 * prefix, like 'am 01.01.2012'
	 * 
	 * @param pDate
	 *            to parse
	 * @return string value
	 */
	public static String getDisplayStringFromDateWithPrefix(final Date pDate) {
		final StringBuffer output = new StringBuffer();
		output.append(getDisplayString("general.date_prefix"));
		output.append(" ");
		output.append(getDisplayStringFromDate(pDate));
		return output.toString();
	}

	/**
	 * Prints out the given date to a readable locale aware String, like
	 * 01.01.2012
	 * 
	 * @param pDate
	 *            to parse
	 * @return string value
	 */
	public static String getDisplayStringFromDate(final Date pDate) {
		final DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
				MessageUtil.getLocale());
		final String formattedDate = df.format(pDate);

		return formattedDate;
	}

	/**
	 * Prints out the given float number depending on locale and nicely, e.g 0.5
	 * becomes for Germany 0,5 and 1.0 becomse 1 01.01.2012
	 * 
	 * @param pNumber
	 *            to parse
	 * @param Locale
	 *            to be used
	 * @return string value
	 */
	public static String getDisplayStringFromFloat(final Float pNumber,
			final Locale pLocale) {
		DecimalFormat formatter = (DecimalFormat) DecimalFormat
				.getInstance(pLocale);
		char separator = formatter.getDecimalFormatSymbols()
				.getDecimalSeparator();
		String result = null;
		// check if it's an integer value
		if (pNumber == Math.round(pNumber)) {
			result = Integer.toString(pNumber.intValue());
		} else {
			result = Float.toString(pNumber).replace('.', separator);
		}
		return result;
	}

	/**
	 * Prints out the given date to a readable locale aware month and year
	 * String, like 01/2012
	 * 
	 * @param pDate
	 *            to parse
	 * @return string value
	 */
	public static String getDisplayStringFromDateMonthYear(final Date pDate) {
		String result = "";
		final SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy",
				MessageUtil.getLocale());
		result = formatter.format(pDate);
		return result;
	}

	/**
	 * Returns a localized String from the default ResourceBundle configured in
	 * faces-config.xml and replaces placeholders with parameters.
	 * 
	 * @param id
	 *            id of the String
	 * @param params
	 *            parameters for substitution
	 */
	public static String getDisplayString(final String id, final Object[] params) {
		return MessageUtil
				.getDisplayString(id, params, MessageUtil.getLocale());
	}

	/**
	 * Returns a localized String from the default ResourceBundle configured in
	 * faces-config.xml, if not found uses the provided fallback ResourceBundle
	 * 
	 * @param bundleName
	 *            name of fallback ResourceBundle
	 * @param id
	 *            of the String
	 */
	public static String getDisplayString(final String id) {
		return MessageUtil.getDisplayString(id,
				MessageUtil.EMPTY_MESSAGE_FORMAT_PARAMS);
	}

	/**
	 * Returns a localized String from the default ResourceBundle configured in
	 * faces-config.xml, if not found uses the provided fallback ResourceBundle
	 * and replaces placeholders with parameters.
	 * 
	 * @param bundleName
	 *            name of fallback ResourceBundle
	 * @param id
	 *            of the String
	 * @param params
	 *            parameters for substitution
	 * @param locale
	 *            to be used
	 */
	public static String getDisplayString(final String id,
			final Object[] params, final Locale locale) {
		String text = null;
		final FacesContext currentInstance = FacesContext.getCurrentInstance();
		Application application = currentInstance.getApplication();
		ResourceBundle bundle;
		if (currentInstance != null && application != null) {
			final String appbundle = application.getMessageBundle();

			if (appbundle != null) {
				bundle = ResourceBundle.getBundle(appbundle, locale, Thread
						.currentThread().getContextClassLoader());
				// try faces application bundle first -> user can override
				// messages
				try {
					text = bundle.getString(id);
				} catch (final MissingResourceException e) {
					// use default resource
				}
			}
		}
		// try supplied bundle
		if (text == null) {
			bundle = getResourceBundle(null);
			try {
				text = bundle.getString(id);
			} catch (final MissingResourceException e) {
				// use default resource
			}
		}

		// did not found any localized string
		if (text == null) {
			final String defaultString = MISSING_RESOURCE_BUNDLE_ENTRY_PREFIX
					+ id + MISSING_RESOURCE_BUNDLE_ENTRY_POSTFIX;

			return defaultString;
		}

		if (params != null) {
			final MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	/**
	 * @param object
	 * @return
	 */
	private static ResourceBundle getResourceBundle(final Locale locale) {
		if (locale != null) {
			final FacesContext currentInstance = FacesContext
					.getCurrentInstance();
			if (currentInstance != null) {
				final String appbundle = currentInstance.getApplication()
						.getMessageBundle();
				if (appbundle != null) {
					return ResourceBundle.getBundle(appbundle, locale, Thread
							.currentThread().getContextClassLoader());
				}
			}
		} else {
			return getFacesBundle(getLocale());
		}
		return null;
	}

	public static ResourceBundle getFacesBundle() {
		return getFacesBundle(getLocale());
	}

	public static ResourceBundle getFacesBundle(final Locale locale) {
		final FacesContext currentInstance = FacesContext.getCurrentInstance();
		if (currentInstance != null) {
			final String appbundle = currentInstance.getApplication()
					.getMessageBundle();
			if (appbundle != null) {
				return ResourceBundle.getBundle(appbundle, locale, Thread
						.currentThread().getContextClassLoader());
			}
		}
		return null;
	}

	/**
	 * Adds a localized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_ERROR.
	 * 
	 * @see MessageUtil#getDisplayString(String)
	 */
	public static void addErrorMessage(final String id) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MessageUtil.getDisplayString(id), null));
	}

	/**
	 * Adds a localized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_ERROR.
	 * 
	 * @see MessageUtil#getDisplayString(String, Object[])
	 */
	public static void addErrorMessage(final String id, final Object[] params) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MessageUtil.getDisplayString(id, params), null));
	}

	/**
	 * Adds an unlocalized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_ERROR
	 */
	public static void addErrorMessageUnlocalized(final String message) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, null));
	}

	/**
	 * Adds an unlocalized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_ERROR
	 */
	public static void addErrorMessageUnlocalized(final String message,
			final String cause) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				cause, message));
	}

	/**
	 * Adds a localized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_INFO.
	 * 
	 * @see MessageUtil#getDisplayString(String, String, Object[])
	 */
	public static void addInfoMessage(final String pID, final Object[] params) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				MessageUtil.getDisplayString(pID, params), null));
	}

	/**
	 * Adds a FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_INFO with the given string
	 * 
	 * @param pMessage
	 *            message text
	 */
	public static void addInfoMessageUnlocalized(final String pMessage) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				pMessage, null));
	}

	/**
	 * Adds a localized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_INFO.
	 * 
	 * @param pID
	 *            of the resource key
	 */
	public static void addInfoMessage(final String pID) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				MessageUtil.getDisplayString(pID), null));
	}

	/**
	 * Adds a localized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_WARN.
	 * 
	 * @param pID
	 *            of the resource key
	 */
	public static void addWarnMessage(final String pID) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
				MessageUtil.getDisplayString(pID), null));
	}

	/**
	 * Adds a localized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_WARN.
	 * 
	 * @see MessageUtil#getDisplayString(String, String, Object[])
	 */
	public static void addWarnMessage(final String id, final Object[] params) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
				MessageUtil.getDisplayString(id, params), null));
	}

	/**
	 * Adds a localized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_FATAL.
	 * 
	 * @see MessageUtil#getDisplayString(String, String, Object[])
	 */
	public static void addFatalMessage(final String id, final Object[] params) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
				MessageUtil.getDisplayString(id, params), null));
	}

	/**
	 * Adds a localized FacesMessage to the current FacesContext with
	 * FacesMessage.SEVERITY_FATAL.
	 * 
	 * @see MessageUtil#getDisplayString(String, String)
	 */
	public static void addFatalMessage(final String id) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
				MessageUtil.getDisplayString(id), null));
	}

	/**
	 * 
	 * @return current locale (stored in Session)
	 */
	public static Locale getLocale() {
		Locale locale = Locale.GERMANY;
		try {
			final FacesContext context = FacesContext.getCurrentInstance();
			if (context != null && context.getViewRoot() != null) {
				locale = context.getViewRoot().getLocale();
			} else {
				LOG.error("Cannot read locale, will return default value");
			}
		} catch (final Exception e) {
			LOG.debug("Cannot get locale, maybe the Session is timed out");
		}
		return locale;
	}

	// VALIDATION METHODS

	/**
	 * @ToDo-mreinhardt validation refactoring
	 * 
	 * @param e
	 * @param pPropertyName
	 */
	public static void addError(final Exception e, final String pPropertyName) {
		final String cause = "Fehlerhafte Eingabe";
		String message = " Bitte korrigieren.";
		// write error to log
		LOG.error("Error occurred", e);
		if (pPropertyName != null) {
			if (handleEntityExistsException(e) != null) {
				message = "data with title '" + pPropertyName
						+ "' ist bereits vorhanden.";
			} else if (handleConstraintViolationException(e) != null) {
				message = "Bezeichnung '" + pPropertyName
						+ "' ist nicht zuläsig";
			}
		}
		addErrorMessageUnlocalized(message, cause);

	}

	/**
	 * Internal helper to recursive handle exceptions for entities that already
	 * exists
	 * 
	 * @param t
	 *            to be used as starting point for recursion
	 * @return the found EntityExistsException or null if nothing is found
	 */
	private static EntityExistsException handleEntityExistsException(
			final Throwable t) {
		LOG.debug("Entity already exists.", t);
		if (t instanceof EntityExistsException) {
			return (EntityExistsException) t;
		}
		if (t.getCause() != null) {
			return handleEntityExistsException(t.getCause());
		}

		return null;
	}

	/**
	 * Internal helper to recursive handle exceptions for entities that have
	 * constraint violatios
	 * 
	 * @param t
	 *            to be used as starting point for recursion
	 * @return the found ConstraintViolationException or null if nothing is
	 *         found
	 */
	public static ConstraintViolationException handleConstraintViolationException(
			final Throwable t) {
		LOG.debug("Constraint violation occurred.", t);
		if (t instanceof ConstraintViolationException) {
			return (ConstraintViolationException) t;
		}
		if (t.getCause() != null) {
			return handleConstraintViolationException(t.getCause());
		}

		return null;
	}

	// HELPER METHODS

	public static void removeFacesMessages() {
		try {
			final FacesContext facesContext = FacesContext.getCurrentInstance();
			for (final Iterator<FacesMessage> iter = facesContext
					.getMessages(null); iter.hasNext();) {
				iter.remove();
			}
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
   * 
   */
	public static void addGeneralError() {
		addErrorMessage("general.error");
	}

	/**
	 * 
	 * @param pErrorCode
	 */
	public static void addGeneralError(final String pErrorCode) {
		final String msg = MessageUtil.getDisplayString("general.error") + " "
				+ pErrorCode;
		MessageUtil.addErrorMessageUnlocalized(msg);
	}

	/**
	 * handles EJB errors, like Constraint violations from the backend
	 * 
	 * @param e
	 */
	public static void handleEJBError(final Exception e) {
		final ConstraintViolationException constraintViolationException = findConstraintViolationException(e);
		if (constraintViolationException != null) {
			LOG.debug("Handling constraint violation error.");
			handleConstraintViolationException(constraintViolationException);
		} else {
			LOG.error("General technical error occurred", e);
			addGeneralError();
		}
	}

	/**
	 * Helper method to get constraint violation in exception tree
	 * 
	 * @param pThrowable
	 * @return
	 */
	private static ConstraintViolationException findConstraintViolationException(
			final Throwable pThrowable) {
		if (pThrowable instanceof ConstraintViolationException) {
			return (ConstraintViolationException) pThrowable;
		}
		final Throwable cause = pThrowable.getCause();
		if (cause instanceof ConstraintViolationException) {
			return (ConstraintViolationException) cause;
		}
		if (cause != null) {
			return findConstraintViolationException(cause);
		}
		return null;
	}
}