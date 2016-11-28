package oonoz.util;

import org.springframework.stereotype.Component;

import oonoz.exception.WrongInformationException;

/**
 * The Class CheckThemeInformation.
 */
@Component
public class CheckThemeInformation {

	/**
	 * Check label.
	 *
	 * @param label the label
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkLabel(String label) throws WrongInformationException {
		if (label == null || label.length() < 4 || label.length() > 20) {
			throw new WrongInformationException("The label theme is invalid !");
		}
	}

	/**
	 * Check description.
	 *
	 * @param description the description
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkDescription(String description) throws WrongInformationException {
		if (description == null || description.length() < 4 
				|| description.length() > 20) {
			throw new WrongInformationException("The description theme is invalid !");
		}
	}
}
