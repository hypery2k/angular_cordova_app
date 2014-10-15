/**
 * Cordova Angular JE22 Demo App
 *
 * File: AuthFilter.java, 14.10.2014, 20:20:44, mreinhardt
 *
 * https://www.martinreinhardt-online.de/apps
 *
 * @project https://github.com/hypery2k/angular_cordova_app
 *
 * @copyright 2014 Martin Reinhardt contact@martinreinhardt-online.de
 *
 *            Permission is hereby granted, free of charge, to any person obtaining a copy
 *            of this software and associated documentation files (the "Software"), to deal
 *            in the Software without restriction, including without limitation the rights
 *            to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *            copies of the Software, and to permit persons to whom the Software is
 *            furnished to do so, subject to the following conditions:
 * 
 *            The above copyright notice and this permission notice shall be included in all
 *            copies or substantial portions of the Software.
 * 
 *            THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *            IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *            FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *            AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *            LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *            OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *            SOFTWARE.
 *
 */
package de.mare.mobile.ui.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mare.mobile.config.AppFeatures;
import de.mare.mobile.domain.User;
import de.mare.mobile.ui.jsf.user.UserSession;
import de.mare.mobile.utils.AppConstants;

/**
 * 
 * Tests if the user is logged in. If not, deny access. If the user is logged
 * in, pack the {@link User} in a thread local variable so that objects without
 * access to the http session can use it.
 * 
 * @author mreinhardt
 * 
 */
public class AuthFilter implements Filter {

  /**
   * Logger
   */
  private Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

  private String loginPage;

  private boolean redirectToLoginPage;

  private String[] excludeURIs;

  @Inject
  private UserSession userSession;

  /**
   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    final String responseCodeAsString = filterConfig.getInitParameter("redirectToLoginPage");
    if (StringUtils.isEmpty(responseCodeAsString)) {
      this.redirectToLoginPage = false;
    } else {
      this.redirectToLoginPage = Boolean.valueOf(responseCodeAsString).booleanValue();
    }
    final String loginPageString = filterConfig.getInitParameter("loginPage");
    if (StringUtils.isEmpty(loginPageString)) {
      this.loginPage = "login.xhtml";
    } else {
      this.loginPage = StringUtils.trim(loginPageString);
    }

    final String excludeURI = filterConfig.getInitParameter("excludeURIs");
    if (StringUtils.isNotEmpty(excludeURI)) {
      this.excludeURIs = excludeURI.split(",");
    }
  }

  /**
   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
   *      javax.servlet.FilterChain)
   */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    final HttpServletRequest request = (HttpServletRequest) servletRequest;
    final String requestURI = request.getRequestURL().toString();
    LOG.debug("Current request url for filtering is " + requestURI);
    this.doFilterInternal(servletRequest, response, chain);
  }

  /**
   * @see javax.servlet.Filter#destroy()
   */
  @Override
  public void destroy() {}

  public void doFilterInternal(final ServletRequest servletRequest, final ServletResponse servletResponse,
      final FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest request = (HttpServletRequest) servletRequest;
    final HttpServletResponse response = (HttpServletResponse) servletResponse;
    if (AppFeatures.PORTAL.isActive()) {
      final String requestURI = request.getRequestURI();
      User user = userSession.getUser();
      // area accessible without login
      boolean anonymousArea = false;
      if (this.excludeURIs != null) {
        for (final String excludeURI : this.excludeURIs) {
          String excludeURIWithContext = request.getContextPath() + excludeURI.trim();
          final boolean withStart = excludeURIWithContext.endsWith("*");
          final boolean isRequestURIexcluded;
          if (withStart) {
            // remove the star
            excludeURIWithContext = excludeURIWithContext.substring(0, excludeURIWithContext.length() - 1);
            isRequestURIexcluded = requestURI.startsWith(excludeURIWithContext);
          } else {
            isRequestURIexcluded = requestURI.equals(excludeURIWithContext);
          }
          if (isRequestURIexcluded) {
            anonymousArea = true;
            break;
          }
        }
      }
      if (anonymousArea || user != null) {
        // let the others work
        chain.doFilter(servletRequest, servletResponse);
      } else {
        if (this.redirectToLoginPage) {
          // save the requested URL
          final StringBuffer urlBuffer = request.getRequestURL();
          if (StringUtils.isNotEmpty(request.getQueryString())) {
            urlBuffer.append("?").append(request.getQueryString());
          }
          final String url = urlBuffer.toString();
          if (!url.contains("login") && !url.contains("error.")) {
            // does not redirect to a login page (like loginFailed)
            request.getSession().setAttribute(AppConstants.REDIRECT_AFTER_SUCCESSFUL_LOGIN, url);
          }
          request.getRequestDispatcher(this.loginPage).forward(servletRequest, servletResponse);
        } else {
          response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
      }
    } else {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}
