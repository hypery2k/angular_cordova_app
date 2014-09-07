/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: Study.java, 19.08.2014, 18:49:55, mreinhardt
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author mreinhardt
 *
 */
@Entity
@XmlRootElement
public class Study implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -1899405770753294216L;

	@Id
	@GeneratedValue
	@Column(name = "study_id")
	private Long id;

	@Column(name = "date_from")
	@NotNull
	private Date from;

	@Column(name = "date_to")
	@NotNull
	private Date to;

	@NotNull
	private String schoolName;

	private String specialization;

	private String reachedTitle;

	private String studyType;

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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getReachedTitle() {
		return reachedTitle;
	}

	public void setReachedTitle(String reachedTitle) {
		this.reachedTitle = reachedTitle;
	}

	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	/**
	 * Generated
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Study [");
		builder.append(from);
		builder.append(", ");
		builder.append(to);
		builder.append(", ");
		builder.append(schoolName);
		builder.append(", ");
		builder.append(specialization);
		builder.append(", ");
		builder.append(reachedTitle);
		builder.append(", ");
		builder.append(studyType);
		builder.append("]");
		return builder.toString();
	}
}
