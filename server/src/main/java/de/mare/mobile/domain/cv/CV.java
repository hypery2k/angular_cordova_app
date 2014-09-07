/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: CV.java, 19.08.2014, 18:49:55, mreinhardt
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
import java.util.List;
import java.util.Locale;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import de.mare.mobile.domain.MultilingualString;
import de.mare.mobile.domain.User;
import de.mare.mobile.domain.validation.ValidationConditions;
import de.mare.mobile.domain.validation.ValidatorFactory;

/**
 * CV object
 * 
 * @author mreinhardt
 *
 */
@Entity
@XmlRootElement
public class CV implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5418845479111419180L;

	@Id
	@GeneratedValue
	@Column(name = "cv_id")
	private Long id;

	@NotNull
	@Valid
	private User person;

	@ElementCollection
	@CollectionTable(name = "locales", joinColumns = @JoinColumn(name = "locale_id"))
	private List<Locale> localizations;

	private MultilingualString summary;

	@OneToMany
	private List<Job> professionalExperience;

	@OneToMany
	private List<Project> projects;

	@OneToMany
	private List<Skill> competencies;

	@OneToMany
	private List<Study> studies;

	@OneToMany
	private List<LanguageSkill> languageSkills;

	@ElementCollection
	@CollectionTable(name = "interests", joinColumns = @JoinColumn(name = "interest_id"))
	private List<String> interests;

	@OneToMany
	private List<AcademicWork> academicWorks = new ArrayList<AcademicWork>();

	/**
	 * Default constructor is necessary because of existing of the private
	 * "Builder" constructor.
	 */
	public CV() {
	}

	private CV(Builder builder) {
		person = builder.person;
		localizations = builder.localizations;
		summary = builder.summary;
		professionalExperience = builder.professionalExperience;
		projects = builder.projects;
		competencies = builder.competencies;
		studies = builder.studies;
		languageSkills = builder.languageSkills;
		interests = builder.interests;
		academicWorks = builder.academicWorks;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return person;
	}

	public void setUser(User person) {
		this.person = person;
	}

	/**
	 * @return the person
	 */
	public User getPerson() {
		return person;
	}

	/**
	 * @param person
	 *          the person to set
	 */
	public void setPerson(User person) {
		this.person = person;
	}

	/**
	 * @return the localizations
	 */
	public List<Locale> getLocalizations() {
		return localizations;
	}

	/**
	 * @param localizations
	 *          the localizations to set
	 */
	public void setLocalizations(List<Locale> localizations) {
		this.localizations = localizations;
	}

	/**
	 * @return the summary
	 */
	public MultilingualString getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *          the summary to set
	 */
	public void setSummary(MultilingualString summary) {
		this.summary = summary;
	}

	public List<Job> getProfessionalExperience() {
		return professionalExperience;
	}

	public void setProfessionalExperience(List<Job> professionalExperience) {
		this.professionalExperience = professionalExperience;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Skill> getCompetencies() {
		return competencies;
	}

	public void setCompetencies(List<Skill> competencies) {
		this.competencies = competencies;
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}

	public List<LanguageSkill> getLanguageSkills() {
		return languageSkills;
	}

	public void setLanguageSkills(List<LanguageSkill> languageSkills) {
		this.languageSkills = languageSkills;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public List<AcademicWork> getAcademicWorks() {
		return academicWorks;
	}

	public void setAcademicWorks(List<AcademicWork> academicWorks) {
		this.academicWorks = academicWorks;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("CV [");
		if (id != null) {
			builder2.append("id=");
			builder2.append(id);
			builder2.append(", ");
		}
		if (person != null) {
			builder2.append("person=");
			builder2.append(person);
			builder2.append(", ");
		}
		if (localizations != null) {
			builder2.append("localizations=");
			builder2.append(localizations);
			builder2.append(", ");
		}
		if (summary != null) {
			builder2.append("summary=");
			builder2.append(summary);
			builder2.append(", ");
		}
		if (professionalExperience != null) {
			builder2.append("professionalExperience=");
			builder2.append(professionalExperience);
			builder2.append(", ");
		}
		if (projects != null) {
			builder2.append("projects=");
			builder2.append(projects);
			builder2.append(", ");
		}
		if (competencies != null) {
			builder2.append("competencies=");
			builder2.append(competencies);
			builder2.append(", ");
		}
		if (studies != null) {
			builder2.append("studies=");
			builder2.append(studies);
			builder2.append(", ");
		}
		if (languageSkills != null) {
			builder2.append("languageSkills=");
			builder2.append(languageSkills);
			builder2.append(", ");
		}
		if (interests != null) {
			builder2.append("interests=");
			builder2.append(interests);
			builder2.append(", ");
		}
		if (academicWorks != null) {
			builder2.append("academicWorks=");
			builder2.append(academicWorks);
		}
		builder2.append("]");
		return builder2.toString();
	}

	public static class Builder {

		private User person;
		private List<Locale> localizations;
		private MultilingualString summary;
		private List<Job> professionalExperience = new ArrayList<>();
		private List<Project> projects;
		private List<Skill> competencies = new ArrayList<>();
		private List<Study> studies = new ArrayList<>();
		private List<LanguageSkill> languageSkills = new ArrayList<>();
		private List<String> interests = new ArrayList<>();
		private List<AcademicWork> academicWorks;

		public Builder withUser(User person) {
			this.person = person;
			return this;
		}

		public Builder withLocalization(List<Locale> pLocalizations) {
			this.localizations = pLocalizations;
			if (pLocalizations != null) {
				if (this.summary != null && this.summary.getLocalizedMessages().size() > 0) {
					for (Locale locale : pLocalizations) {
						if (!this.summary.getLocalizedMessages().containsKey(locale)) {
							this.summary.addText(locale, "");
						}
					}
				} else {
					this.summary = new MultilingualString();
					for (Locale locale : pLocalizations) {
						this.summary.addText(locale, "");
					}
				}
			}
			// TODO add other localized strings to (maybe type interfer!)
			return this;
		}

		public Builder withSummary(MultilingualString summary) {
			this.summary = summary;
			return this;
		}

		public Builder withProfessionalExperience(List<Job> professionalExperience) {
			this.professionalExperience = professionalExperience;
			return this;
		}

		public Builder withProjects(List<Project> projects) {
			this.projects = projects;
			return this;
		}

		public Builder withCompetencies(List<Skill> competencies) {
			this.competencies = competencies;
			return this;
		}

		public Builder withStudies(List<Study> studies) {
			this.studies = studies;
			return this;
		}

		public Builder withLanguageSkills(List<LanguageSkill> languageSkills) {
			this.languageSkills = languageSkills;
			return this;
		}

		public Builder withInterests(List<String> interests) {
			this.interests = interests;
			return this;
		}

		public Builder withListAcademicWorks(List<AcademicWork> academicWorks) {
			this.academicWorks = academicWorks;
			return this;
		}

		public CV build() {
			CV newCV = new CV(this);
			ValidationConditions.isValid(newCV, ValidatorFactory.getValidator());
			return newCV;
		}
	}
}
