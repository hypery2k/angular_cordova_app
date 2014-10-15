/**
 * Cordova Angular JE22 Demo App
 *
 * File: ErrorHandler.java, 13.10.2014, 17:58:17, mreinhardt
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
package de.mare.mobile.ui.jsf;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJBAccessException;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mare.mobile.ui.util.MessageUtil;
import de.mare.mobile.ui.util.WebUIConstants;

/**
 * Our custom error handler: If an uncaught exception is thrown we redirect the
 * user to the error page.
 * 
 * @see <a
 *      href="https://cwiki.apache.org/confluence/display/MYFACES/Handling+Server+Errors">MyFaces
 *      Wiki</a>
 * 
 * 
 * @author mreinhardt
 * 
 */
public class ErrorHandler extends ExceptionHandlerWrapper {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

	private final ExceptionHandler wrapped;

	ErrorHandler(final ExceptionHandler exception) {
		this.wrapped = exception;
	}

	/**
	 * Add error code to HTTP session
	 * 
	 * @param pThrowable
	 *            - throwable to be handled
	 * @return the error code
	 */
	public static String addErrorCode(final Throwable pThrowable) {
		// generate an UID to identify the technical error
		final String errorID = UUID.randomUUID().toString();
		final FacesContext context = FacesContext.getCurrentInstance();
		final ExternalContext externalContext = context.getExternalContext();
		final Map<String, Object> sessionMap = externalContext.getSessionMap();

		// add some usefull properties
		if (pThrowable != null) {
			sessionMap.put("exceptionMessage", pThrowable.getMessage());
		}
		sessionMap.put(WebUIConstants.SESSION_ATTRIBUTE_ERRORCODE, errorID);
		return errorID;
	}

	/**
	 * 
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> i = this
				.getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			final ExceptionQueuedEvent event = i.next();
			final ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();

			// get the exception from context
			final Throwable t = context.getException();
			// here you do what ever you want with exception
			try {
				this.handleError(t);
			} finally {
				// remove it from queue
				i.remove();
			}
		}
		// parent handle
		this.getWrapped().handle();

	}

	/**
	 * handleException-method following the myfaces documentation, see
	 * 
	 * @see <a
	 *      href="http://wiki.apache.org/myfaces/Handling_Server_Errors">MyFaces
	 *      Wiki</a>
	 * 
	 * @param pThrowable
	 *            - throwable to be handled
	 */
	public void handleError(final Throwable pThrowable) {

		final String errorID = addErrorCode(pThrowable);

		final boolean isViewExpiredException = pThrowable instanceof ViewExpiredException;

		boolean isSecurityAccessError = false;

		// get HTTP session
		final HttpSession session = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);

		if (isViewExpiredException) {
			if (session != null) {
				session.invalidate();
			}
			LOGGER.debug("View expired: " + pThrowable, pThrowable);

		} else {
			if (pThrowable instanceof AbortProcessingException) {
				if (pThrowable.getCause() != null
						&& pThrowable.getCause() instanceof EJBAccessException) {
					// redirect to security error page
					if (session != null) {
						session.invalidate();
					}
					isSecurityAccessError = true;
					// log the error
					logError("Security access error '" + errorID + "': ",
							pThrowable);
				} else {

					// log the error
					logError("Technical error '" + errorID + "': ", pThrowable);
				}
			}
			// log the error
			logError("Technical error '" + errorID + "': ", pThrowable);
		}

		try {
			final FacesContext context = FacesContext.getCurrentInstance();
			final ExternalContext externalContext = context
					.getExternalContext();
			final NavigationHandler nav = context.getApplication()
					.getNavigationHandler();

			context.getPartialViewContext().setRenderAll(true);
			// redirect error page
			if (!externalContext.isResponseCommitted()) {
				final String outcome = isViewExpiredException
						|| isSecurityAccessError ? "login.technical_error"
						: "error";
				if (isViewExpiredException) {
					MessageUtil.addErrorMessage("general.session_timeout");
				} else {
					if (isSecurityAccessError) {
						MessageUtil
								.addErrorMessage("general.error.access_invalid");
					} else {
						MessageUtil.addErrorMessage("general.error");
					}
				}
				nav.handleNavigation(context, null, outcome);
				LOGGER.debug("Redirecting to " + outcome + " page ...");
				context.renderResponse();
			}

		} catch (final Exception e) {
			logError(
					"Could not redirect user to login page after error occured: ",
					e);
		}
	}

	/**
	 * Logs the error details
	 * 
	 * @param pMessage
	 *            - message to log
	 * @param pThrowable
	 *            - throwable to log
	 */
	private void logError(final String pMessage, final Throwable pThrowable) {
		// TODO add email logging here if enabled
		LOGGER.error(pMessage, pThrowable);

	}

	/**
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}
}
