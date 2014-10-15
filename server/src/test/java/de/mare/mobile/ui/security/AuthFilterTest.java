/**
 * Cordova Angular JE22 Demo App
 *
 * File: AuthFilterTest.java, 15.10.2014, 17:46:59, mreinhardt
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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Rule;
import org.junit.Test;
import org.togglz.junit.TogglzRule;

import HttpServletResponse.HttpServletResponse;
import de.mare.mobile.config.AppFeatures;
import de.mare.mobile.domain.User;
import de.mare.mobile.domain.enums.SecurityRole;
import de.mare.mobile.ui.jsf.user.UserSession;

/**
 * @author mreinhardt
 *
 */
public class AuthFilterTest {

  private HttpServletRequest request;

  private HttpServletResponse response;

  @Rule
  public TogglzRule togglzRule = TogglzRule.allEnabled(AppFeatures.class);

  @Test
  public void testNoRedirectToLoginWithLoggedInUser() throws IOException, ServletException {
    // when
    final StringBuffer requestURL = new StringBuffer("abc/xyz");
    final User user = new User.Builder().withUsername("test").withFirstname("Max").withLastname("Mustermann").withRole(
        SecurityRole.USER).build();
    // given
    final UserSession userSession = mock(UserSession.class);
    final HttpSession httpSession = mock(HttpSession.class);
    final FilterChain chain = mock(FilterChain.class);
    request = mock(HttpServletRequest.class);
    response = new HttpServletResponse();
    when(request.getRequestURL()).thenReturn(requestURL);
    when(request.getSession()).thenReturn(httpSession);
    when(userSession.getUser()).thenReturn(user);
    // all features are active by default
    assertTrue(AppFeatures.PORTAL.isActive());
    AuthFilter filter = new AuthFilter();
    filter.setUserSession(userSession);
    filter.doFilter(request, response, chain);

    // then
    verify(chain).doFilter(request, response);
    assertThat(response.getStatus(), is(not(HttpServletResponse.SC_NOT_FOUND)));

  }

  @Test
  public void testRedirectToLoginWithNoUser() throws IOException, ServletException {
    // when
    final StringBuffer requestURL = new StringBuffer("abc/xyz");
    // given
    final UserSession userSession = mock(UserSession.class);
    final HttpSession httpSession = mock(HttpSession.class);
    final FilterChain chain = mock(FilterChain.class);
    request = mock(HttpServletRequest.class);
    response = new HttpServletResponse();
    when(request.getRequestURL()).thenReturn(requestURL);
    when(request.getSession()).thenReturn(httpSession);
    // all features are active by default
    assertTrue(AppFeatures.PORTAL.isActive());
    AuthFilter filter = new AuthFilter();
    filter.setUserSession(userSession);
    filter.doFilter(request, response, chain);
    // then
    verifyZeroInteractions(chain);
    assertThat(response.getStatus(), is(not(HttpServletResponse.SC_NOT_FOUND)));
  }

  // toggle off

  @Test
  public void testRedirectWithInactivePortalToggle() throws IOException, ServletException {
    // when
    final StringBuffer requestURL = new StringBuffer("abc/xyz");
    togglzRule.disable(AppFeatures.PORTAL);
    // given
    final UserSession userSession = mock(UserSession.class);
    final HttpSession httpSession = mock(HttpSession.class);
    final FilterChain chain = mock(FilterChain.class);
    request = mock(HttpServletRequest.class);
    response = new HttpServletResponse();
    when(request.getRequestURL()).thenReturn(requestURL);
    when(request.getSession()).thenReturn(httpSession);
    // all features are active by default
    assertFalse(AppFeatures.PORTAL.isActive());
    AuthFilter filter = new AuthFilter();
    filter.setUserSession(userSession);
    filter.doFilter(request, response, chain);
    // then
    verifyZeroInteractions(chain);
    assertThat(response.getStatus(), is(HttpServletResponse.SC_NOT_FOUND));
  }
}
