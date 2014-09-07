/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: LanguageSkill.java, 19.08.2014, 18:49:55, mreinhardt
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
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
public class LanguageSkill implements Serializable {

	/**
	 * 
	 */
  private static final long serialVersionUID = 4284952954587172266L;

	@Id
	@GeneratedValue
	@Column(name = "langs_id")
	private Long id;

	@NotNull
	private Locale language;

	@Enumerated(EnumType.ORDINAL)
	private LanguageSkillLevel level;

	private String notes;

	/**
	 * Default constructor is necessary because of existing of the private
	 * "Builder" constructor.
	 */
	public LanguageSkill() {
	}

	/**
	 * Build with build pattern
	 * 
	 * @param builder
	 */
	private LanguageSkill(Builder builder) {
		language = builder.language;
		level = builder.level;
		notes = builder.notes;
	}

	public Locale getLocale() {
		return language;
	}

	public void setLocale(Locale language) {
		this.language = language;
	}

	public LanguageSkillLevel getLevel() {
		return level;
	}

	public void setLevel(LanguageSkillLevel level) {
		this.level = level;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Generated
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LocaleSkill [");
		builder.append(language);
		builder.append(", ");
		builder.append(level);
		builder.append(", ");
		builder.append(notes);
		builder.append("]");
		return builder.toString();
	}

	public static class Builder {

		private Locale language;
		private LanguageSkillLevel level;
		private String notes;

		public Builder withLocale(Locale language) {
			this.language = language;
			return this;
		}

		public Builder withLevel(LanguageSkillLevel level) {
			this.level = level;
			return this;
		}

		public Builder withNotes(String notes) {
			this.notes = notes;
			return this;
		}

		public LanguageSkill build() {
			LanguageSkill newSkill = new LanguageSkill(this);
			ValidationConditions.isValid(newSkill, ValidatorFactory.getValidator());
			return newSkill;
		}
	}
}
