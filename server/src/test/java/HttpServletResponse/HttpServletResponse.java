/**
 * Cordova Angular JE22 Demo App
 *
 * File: HttpServletResponse.java, 15.10.2014, 18:31:32, mreinhardt
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
package HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

/**
 * Mock for Servlet Response
 * 
 * @author mreinhardt
 *
 */
public class HttpServletResponse implements javax.servlet.http.HttpServletResponse {

  private String characterEncoding;

  private String contentType;

  private int status;

  /**
   * @see javax.servlet.ServletResponse#getCharacterEncoding()
   */
  @Override
  public String getCharacterEncoding() {
    return characterEncoding;
  }

  /**
   * @see javax.servlet.ServletResponse#getContentType()
   */
  @Override
  public String getContentType() {
    return contentType;
  }

  /**
   * @see javax.servlet.ServletResponse#getOutputStream()
   */
  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return null;
  }

  /**
   * @see javax.servlet.ServletResponse#getWriter()
   */
  @Override
  public PrintWriter getWriter() throws IOException {
    return null;
  }

  /**
   * @see javax.servlet.ServletResponse#setCharacterEncoding(java.lang.String)
   */
  @Override
  public void setCharacterEncoding(String charset) {
    this.characterEncoding = charset;

  }

  /**
   * @see javax.servlet.ServletResponse#setContentLength(int)
   */
  @Override
  public void setContentLength(int len) {

  }

  /**
   * @see javax.servlet.ServletResponse#setContentLengthLong(long)
   */
  @Override
  public void setContentLengthLong(long len) {

  }

  /**
   * @see javax.servlet.ServletResponse#setContentType(java.lang.String)
   */
  @Override
  public void setContentType(String type) {}

  /**
   * @see javax.servlet.ServletResponse#setBufferSize(int)
   */
  @Override
  public void setBufferSize(int size) {

  }

  /**
   * @see javax.servlet.ServletResponse#getBufferSize()
   */
  @Override
  public int getBufferSize() {
    return 0;
  }

  /**
   * @see javax.servlet.ServletResponse#flushBuffer()
   */
  @Override
  public void flushBuffer() throws IOException {

  }

  /**
   * let.ServletResponse#resetBuffer()
   */
  @Override
  public void resetBuffer() {

  }

  /**
   * @see javax.servlet.ServletResponse#isCommitted()
   */
  @Override
  public boolean isCommitted() {
    return false;
  }

  /**
   * @see javax.servlet.ServletResponse#reset()
   */
  @Override
  public void reset() {

  }

  /**
   * @see javax.servlet.ServletResponse#setLocale(java.util.Locale)
   */
  @Override
  public void setLocale(Locale loc) {

  }

  /**
   * @see javax.servlet.ServletResponse#getLocale()
   */
  @Override
  public Locale getLocale() {
    return Locale.GERMAN;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#addCookie(javax.servlet.http.Cookie)
   */
  @Override
  public void addCookie(Cookie cookie) {

  }

  /**
   * @see javax.servlet.http.HttpServletResponse#containsHeader(java.lang.String)
   */
  @Override
  public boolean containsHeader(String name) {
    return false;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#encodeURL(java.lang.String)
   */
  @Override
  public String encodeURL(String url) {
    return null;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#encodeRedirectURL(java.lang.String)
   */
  @Override
  public String encodeRedirectURL(String url) {
    return null;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#encodeUrl(java.lang.String)
   */
  @Override
  public String encodeUrl(String url) {
    return null;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#encodeRedirectUrl(java.lang.String)
   */
  @Override
  public String encodeRedirectUrl(String url) {
    return null;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#sendError(int, java.lang.String)
   */
  @Override
  public void sendError(int sc, String msg) throws IOException {}

  /**
   * @see javax.servlet.http.HttpServletResponse#sendError(int)
   */
  @Override
  public void sendError(int sc) throws IOException {

  }

  /**
   * @see javax.servlet.http.HttpServletResponse#sendRedirect(java.lang.String)
   */
  @Override
  public void sendRedirect(String location) throws IOException {

  }

  /**
   * @see javax.servlet.http.HttpServletResponse#setDateHeader(java.lang.String, long)
   */
  @Override
  public void setDateHeader(String name, long date) {

  }

  /**
   * @see javax.servlet.http.HttpServletResponse#addDateHeader(java.lang.String, long)
   */
  @Override
  public void addDateHeader(String name, long date) {

  }

  /**
   * @see javax.servlet.http.HttpServletResponse#setHeader(java.lang.String, java.lang.String)
   */
  @Override
  public void setHeader(String name, String value) {

  }

  /**
   * @see javax.servlet.http.HttpServletResponse#addHeader(java.lang.String, java.lang.String)
   */
  @Override
  public void addHeader(String name, String value) {

  }

  /**
   * @see javax.servlet.http.HttpServletResponse#setIntHeader(java.lang.String, int)
   */
  @Override
  public void setIntHeader(String name, int value) {}

  /**
   * @see javax.servlet.http.HttpServletResponse#addIntHeader(java.lang.String, int)
   */
  @Override
  public void addIntHeader(String name, int value) {

  }

  /**
   * @see javax.servlet.http.HttpServletResponse#setStatus(int)
   */
  @Override
  public void setStatus(int sc) {
    this.status = sc;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#setStatus(int, java.lang.String)
   */
  @Override
  public void setStatus(int sc, String sm) {
    this.status = sc;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#getStatus()
   */
  @Override
  public int getStatus() {
    return status;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#getHeader(java.lang.String)
   */
  @Override
  public String getHeader(String name) {
    return null;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#getHeaders(java.lang.String)
   */
  @Override
  public Collection<String> getHeaders(String name) {
    return null;
  }

  /**
   * @see javax.servlet.http.HttpServletResponse#getHeaderNames()
   */
  @Override
  public Collection<String> getHeaderNames() {
    return null;
  }

}
