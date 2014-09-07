/**
 * Angular Cordova Demo using JEE7 backend
 *
 * File: ValidationConditions.java, 19.08.2014, 18:49:55, mreinhardt
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
package de.mare.mobile.domain.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 * 
 * @author mreinhardt
 *
 */
public final class ValidationConditions {

	private ValidationConditions() {
	}

	/**
	 * Triggers a JSR-303 validation process of the given bean.
	 *
	 * @param beanToBeValidated
	 *          Object of type {@code T} which is to be validated.
	 * @param validatorFactory
	 * @throws IllegalArgumentException
	 *           if {@code beanToBeValidated} is not valid. Error message is a
	 *           concatenation of messages of all constraint violations.
	 */
	public static <T> void isValid(T beanToBeValidated, Validator validator) {
		Set<ConstraintViolation<T>> violations = validator.validate(beanToBeValidated);

		if (!violations.isEmpty()) {
			StringBuilder errorMessage = new StringBuilder();
			for (ConstraintViolation<T> violation : violations) {
				errorMessage.append(String.format("%s: \"%s\" ... %s\n", violation.getPropertyPath(),
				    violation.getInvalidValue(), violation.getMessage()));
			}
			throw new IllegalArgumentException(errorMessage.toString());
		}
	}
}
