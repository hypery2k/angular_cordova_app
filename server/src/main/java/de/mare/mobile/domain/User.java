/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: User.java, 30.07.2014, 12:49:55, mreinhardt
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

package de.mare.mobile.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.mare.mobile.domain.enums.SecurityRole;
import de.mare.mobile.domain.validation.ValidationConditions;
import de.mare.mobile.domain.validation.ValidatorFactory;

/**
 * User entity
 * 
 * @author mreinhardt
 *
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class User implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8879332249157192528L;

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@XmlElement
	@Column(name = "USERNAME")
	private String username;

	@XmlElement
	@Column(name = "FIRSTNAME")
	private String firstname;

	@XmlElement
	@Column(name = "LASTNAME")
	private String lastname;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE")
	@NotNull
	@XmlElement
	private String role;

	@Temporal(TemporalType.DATE)
	@Transient
	private Date loadDate = new Date();

	public User() {

	}

	/**
	 * Construct with builder
	 * 
	 * @param pBuilder
	 */
	public User(Builder pBuilder) {
		super();
		this.username = pBuilder.username;
		this.firstname = pBuilder.firstname;
		this.lastname = pBuilder.lastname;
		this.password = pBuilder.password;
		setRole(pBuilder.role);
	}

	public Long getId() {
		return id;
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *          the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *          the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	/**
	 * @return the securityRole
	 */
	public final SecurityRole getRole() {
		return SecurityRole.getByName(this.role);
	}

	/**
	 * @param pSecurityRole
	 *          the securityRole to set
	 */
	public final void setRole(final SecurityRole pSecurityRole) {
		if (pSecurityRole != null) {
			this.role = pSecurityRole.getName();
		} else {
			this.role = null;
		}
	}

	/**
	 * @return the loadDate
	 */
	public Date getLoadDate() {
		return loadDate;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (username != null) {
			builder.append("username=");
			builder.append(username);
			builder.append(", ");
		}
		if (firstname != null) {
			builder.append("firstname=");
			builder.append(firstname);
			builder.append(", ");
		}
		if (lastname != null) {
			builder.append("lastname=");
			builder.append(lastname);
		}
		builder.append("]");
		return builder.toString();
	}

	public static class Builder {

		private String username;

		private String firstname;

		private String lastname;

		private String password;

		private SecurityRole role;

		public Builder withUsername(String pUsername) {
			this.username = pUsername;
			return this;
		}

		public Builder withFirstname(String pFirstname) {
			this.firstname = pFirstname;
			return this;
		}

		public Builder withLastname(String pLastname) {
			this.lastname = pLastname;
			return this;
		}

		public Builder withPassword(String pPassword) {
			this.password = pPassword;
			return this;
		}

		public Builder withRole(SecurityRole pSecurityRole) {
			this.role = pSecurityRole;
			return this;
		}

		public User build() {
			User newUser = new User(this);
			ValidationConditions.isValid(newUser, ValidatorFactory.getValidator());
			return newUser;
		}
	}

}
