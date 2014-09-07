/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: AcademicWork.java, 19.08.2014, 18:49:55, mreinhardt
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import de.mare.mobile.domain.MultilingualString;

/**
 * 
 * @author mreinhardt
 *
 */
@Entity
@XmlRootElement
public class AcademicWork implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5711551967685760139L;

	private static final String WEB_SITE_URL_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	@Id
	@GeneratedValue
	@Column(name = "awork_id")
	private Long id;

	@Column(name = "date_from")
	@NotNull
	private Date from;

	@Column(name = "date_to")
	@NotNull
	private Date to;

	@OneToOne
	private MultilingualString description;

	@OneToMany
	private List<Skill> usedTechnologies = new ArrayList<Skill>();

	@Pattern(regexp = WEB_SITE_URL_REGEX)
	private String webSite;

	/**
	 * @return the from
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * @param from
	 *          the from to set
	 */
	public void setFrom(Date from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public Date getTo() {
		return to;
	}

	/**
	 * @param to
	 *          the to to set
	 */
	public void setTo(Date to) {
		this.to = to;
	}

	public List<Skill> getUsedTechnologies() {
		return usedTechnologies;
	}

	public void setUsedTechnologies(List<Skill> usedTechnologies) {
		this.usedTechnologies = usedTechnologies;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @return the description
	 */
	public MultilingualString getDescription() {
		return description;
	}

	/**
	 * @param description
	 *          the descriptions to set
	 */
	public void setDescription(MultilingualString pDescription) {
		this.description = pDescription;
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AcademicWork [");
		if (from != null) {
			builder.append("from=");
			builder.append(from);
			builder.append(", ");
		}
		if (to != null) {
			builder.append("to=");
			builder.append(to);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (usedTechnologies != null) {
			builder.append("usedTechnologies=");
			builder.append(usedTechnologies);
			builder.append(", ");
		}
		if (webSite != null) {
			builder.append("webSite=");
			builder.append(webSite);
		}
		builder.append("]");
		return builder.toString();
	}

}
