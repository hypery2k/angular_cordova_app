/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: Skill.java, 19.08.2014, 18:49:55, mreinhardt
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
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author mreinhardt
 *
 */
@Entity
@XmlRootElement
public class Skill implements Serializable {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 20901458460872271L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String knowledge;

	@Digits(integer = 2, fraction = 1)
	private BigDecimal experienceInYears;

	@Enumerated(EnumType.ORDINAL)
	private SkillLevel level;

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public BigDecimal getExperienceInYears() {
		return experienceInYears;
	}

	public void setExperienceInYears(BigDecimal experienceInYears) {
		this.experienceInYears = experienceInYears;
	}

	public SkillLevel getLevel() {
		return level;
	}

	public void setLevel(SkillLevel level) {
		this.level = level;
	}

	/**
	 * Generated
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Competency [");
		builder.append(knowledge);
		builder.append(", ");
		builder.append(experienceInYears);
		builder.append(", ");
		builder.append(level);
		builder.append("]");
		return builder.toString();
	}
}
