/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: Project.java, 19.08.2014, 18:49:55, mreinhardt
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
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import de.mare.mobile.domain.MultilingualString;
import de.mare.mobile.domain.validation.ValidationConditions;
import de.mare.mobile.domain.validation.ValidatorFactory;

/**
 * 
 * @author mreinhardt
 *
 */
@Entity
@XmlRootElement
public class Project implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -1712422886119266262L;

	@Id
	@GeneratedValue
	@Column(name = "project_id")
	private Long id;

	@NotNull
	private String projectName;
	
	@Column(name = "date_from")
	@NotNull
	private Date from;
	
	@Column(name = "date_to")
	@NotNull
	private Date to;

	@NotNull
	private MultilingualString description;

	private MultilingualString responsibility;

	@OneToMany
	private List<Skill> usedTechnologies;

	private String customer;

	/**
	 * Default constructor
	 */
	public Project() {
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	/**
	 * @return the description
	 */
	public MultilingualString getDescription() {
		return description;
	}

	/**
	 * @param description
	 *          the description to set
	 */
	public void setDescription(MultilingualString description) {
		this.description = description;
	}

	/**
	 * @return the responsibility
	 */
	public MultilingualString getResponsibility() {
		return responsibility;
	}

	/**
	 * @param responsibility
	 *          the responsibility to set
	 */
	public void setResponsibility(MultilingualString responsibility) {
		this.responsibility = responsibility;
	}

	public List<Skill> getUsedTechnologies() {
		return usedTechnologies;
	}

	public void setUsedTechnologies(List<Skill> usedTechnologies) {
		this.usedTechnologies = usedTechnologies;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Project [");
		if (projectName != null) {
			builder2.append("projectName=");
			builder2.append(projectName);
			builder2.append(", ");
		}
		if (from != null) {
			builder2.append("from=");
			builder2.append(from);
			builder2.append(", ");
		}
		if (to != null) {
			builder2.append("to=");
			builder2.append(to);
			builder2.append(", ");
		}
		if (description != null) {
			builder2.append("description=");
			builder2.append(description);
			builder2.append(", ");
		}
		if (responsibility != null) {
			builder2.append("responsibility=");
			builder2.append(responsibility);
			builder2.append(", ");
		}
		if (usedTechnologies != null) {
			builder2.append("usedTechnologies=");
			builder2.append(usedTechnologies);
			builder2.append(", ");
		}
		if (customer != null) {
			builder2.append("customer=");
			builder2.append(customer);
		}
		builder2.append("]");
		return builder2.toString();
	}

	private Project(Builder builder) {
		projectName = builder.projectName;
		from = builder.from;
		to = builder.to;
		description = builder.description;
		responsibility = builder.responsibility;
		usedTechnologies = builder.usedTechnologies;
		customer = builder.customer;
	}

	public static class Builder {

		private String projectName;
		private Date from;
		private Date to;
		private MultilingualString description;
		private MultilingualString responsibility;
		private List<Skill> usedTechnologies;
		private String customer;

		public Builder withProjectName(String projectName) {
			this.projectName = projectName;
			return this;
		}

		public Builder withFrom(Date from) {
			this.from = from;
			return this;
		}

		public Builder withTo(Date to) {
			this.to = to;
			return this;
		}

		public Builder withDescription(MultilingualString pDescription) {
			this.description = pDescription;
			return this;
		}

		public Builder withResponsibility(MultilingualString pResponsibility) {
			this.responsibility = pResponsibility;
			return this;
		}

		public Builder withUsedTechnologies(List<Skill> usedTechnologies) {
			this.usedTechnologies = usedTechnologies;
			return this;
		}

		public Builder withCustomer(String customer) {
			this.customer = customer;
			return this;
		}

		public Project build() {
			Project newProject = new Project(this);
			ValidationConditions.isValid(newProject, ValidatorFactory.getValidator());
			return newProject;
		}
	}
}
