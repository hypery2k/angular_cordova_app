/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: Address.java, 19.08.2014, 18:49:55, mreinhardt
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
package de.mare.mobile.domain.cv;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import de.mare.mobile.domain.validation.ValidationConditions;
import de.mare.mobile.domain.validation.ValidatorFactory;

/**
 * 
 * @author mreinhardt
 *
 */
@Entity
@XmlRootElement
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7465268727514301485L;

	@Id
	@GeneratedValue
	@Column(name = "addr_id")
	private Long id;

	@Pattern(regexp = "[\\s]*.+([\\s]+[0-9]*[A-Za-z]?)?[\\s]*")
	private String street;

	@NotNull
	private Integer streetNumber;

	@Pattern(regexp = "[\\s]*.+([\\s]+[0-9]*[A-Za-z]?)?[\\s]*")
	private String city;

	@NotNull
	@Min(10000)
	@Max(99999)
	private Integer postCode;

	/**
	 * Default constructor is necessary because of existing of the private
	 * "Builder" constructor.
	 */
	public Address() {
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPostCode() {
		return postCode;
	}

	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}

	/**
	 * Generated
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [");
		builder.append(street);
		builder.append(", ");
		builder.append(streetNumber);
		builder.append(", ");
		builder.append(city);
		builder.append(", ");
		builder.append(postCode);
		builder.append("]");
		return builder.toString();
	}

	private Address(Builder builder) {
		street = builder.street;
		streetNumber = builder.streetNumber;
		city = builder.city;
		postCode = builder.postCode;
	}

	public static class Builder {

		private String street;
		private Integer streetNumber;
		private String city;
		private Integer postCode;

		public Builder withStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder withStreetNumber(Integer streetNumber) {
			this.streetNumber = streetNumber;
			return this;
		}

		public Builder withCity(String city) {
			this.city = city;
			return this;
		}

		public Builder withPostCode(Integer postCode) {
			this.postCode = postCode;
			return this;
		}

		public Address build() {
			Address newAddress = new Address(this);
			ValidationConditions.isValid(newAddress, ValidatorFactory.getValidator());
			return newAddress;
		}
	}
}
