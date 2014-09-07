/**
 * Cordova Angular JE22 Demo App
 *
 * File: MultilingualString.java, 20.08.2014, 16:31:48, mreinhardt
 *
 * https://www.martinreinhardt-online.de/apps
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mreinhardt
 *
 */
@Entity
@XmlRootElement
public class MultilingualString implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5135334292545778470L;

	@Id
	@GeneratedValue
	@Column(name = "string_id")
	private long id;

	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "lang")
	@CollectionTable(name = "multilingual_string_map",
	    joinColumns = @JoinColumn(name = "string_id"))
	private Map<Locale, LocalizedString> localizedMessages = new HashMap<Locale, LocalizedString>();

	public MultilingualString() {
	}

	public MultilingualString(Locale lang, String text) {
		addText(lang, text);
	}

	public void addText(Locale lang, String text) {
		localizedMessages.put(lang, new LocalizedString(lang, text));
	}

	public String getText(String lang) {
		if (localizedMessages.containsKey(lang)) {
			return localizedMessages.get(lang).getText();
		}
		return null;
	}

	/**
	 * @return the localizedMessages
	 */
	public Map<Locale, LocalizedString> getLocalizedMessages() {
		return localizedMessages;
	}

	/**
	 * @param localizedMessages
	 *          the localizedMessages to set
	 */
	public void setLocalizedMessages(Map<Locale, LocalizedString> localizedMessages) {
		this.localizedMessages = localizedMessages;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

}
