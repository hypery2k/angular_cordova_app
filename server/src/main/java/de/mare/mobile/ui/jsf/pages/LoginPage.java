/**
 * Cordova Angular JE22 Demo App
 *
 * File: LoginPage.java, 14.10.2014, 07:32:43, mreinhardt
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
package de.mare.mobile.ui.jsf.pages;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mare.mobile.domain.User;
import de.mare.mobile.services.UserRepository;
import de.mare.mobile.ui.jsf.user.UserSession;

/**
 * @author mreinhardt
 *
 */
/**
 * JSF backing bean which handles all login and logout actions
 * 
 * @author mreinhardt
 * 
 */
@Named
@RequestScoped
public class LoginPage implements Serializable {

  /**
   * Serial ID
   */
  private static final long serialVersionUID = -2352059495629034319L;

  /**
   * Logger
   */
  private Logger LOG = LoggerFactory.getLogger(UserSession.class);

  @Inject
  private UserSession userSession;

  @Inject
  private UserRepository userRepository;

  // indicate correct initialization
  private boolean initialized = false;

  private String username;

  private String password;

  /**
   * init actions
   */
  @PostConstruct
  public void init() {
    if (!this.initialized) {
      this.initialized = true;
      LOG.debug("Init of login page complete");
    }
  }

  /**
   * Perform login of the user
   * 
   */
  public String loginAction() {

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    try {
      request.login(this.username, this.password);
      String username = request.getUserPrincipal().getName();
      User currentUser = userRepository.findUser(username);
      userSession.setUser(currentUser);
      LOG.info("username is: " + username);
    } catch (ServletException e) {
      if (StringUtils.contains(e.getMessage(), "User already logged in")) {
        String username = request.getUserPrincipal().getName();
        User currentUser = userRepository.findUser(username);
        userSession.setUser(currentUser);
        LOG.info("User already loggedn in");
        LOG.info("username is: " + username);
      } else {
        context.addMessage(null, new FacesMessage("Login failed."));
        return "login.error";
      }

    }
    return "portal.start";
  }
  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username
   *          the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password
   *          the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
