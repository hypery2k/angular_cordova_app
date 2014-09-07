package de.mare.mobile.domain.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.mare.mobile.domain.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2578312204233891431L;

	private String username;

	private String firstname;

	private String lastname;

	public UserDTO() {
	}

	public UserDTO(final User pUser) {
		this.username = pUser.getUsername();
		this.firstname = pUser.getFirstname();
		this.lastname = pUser.getLastname();
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

	@XmlTransient
	/**
	 * @return the user
	 */
	public User getUser() {
		User user = new User();
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		return user;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [");
		if (username != null) {
			builder.append("user=");
			builder.append(username);
		}
		builder.append("]");
		return builder.toString();
	}

}
